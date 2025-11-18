package purple.mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import purple.mod.items.other.WeavingBow;

@Mixin(ArrowEntity.class)
public abstract class ArrowMixin {

    @Inject(method = "onHit", at = @At("RETURN"))
    void weavingHit(LivingEntity target, CallbackInfo cl) {
        if (target.getRecentDamageSource().getAttacker() instanceof LivingEntity attacker && attacker
                .getMainHandStack().getItem() instanceof WeavingBow weavingBow) {
                    
            World world = target.getWorld();
            if (world.isClient()){
                return;
                }
            BlockPos blockPos = target.getBlockPos();
            placeWebs(blockPos, world);
            
            if (world instanceof ServerWorld serverWorld){
                for (int i=0;i<2;i++){
                    SpiderEntity spider = new SpiderEntity(EntityType.SPIDER,world);
                    spider.initialize(serverWorld, world.getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                    spider.setPos(blockPos.getX(),blockPos.getY()+1,blockPos.getZ());
                    spider.setAttacking(false);
                    spider.setTarget(target);
                    weavingBow.WeaverTeam.addMember(spider);
                    serverWorld.spawnEntity(spider);
                }

            }
        
        
        
        }
    }






    void placeWebs(BlockPos blockPos, World world){
        if (world.canSetBlock(blockPos)) {

            BlockState state = Blocks.COBWEB.getDefaultState();
            world.setBlockState(blockPos, state);
            for (Direction direction : Direction.values()) {
                if (!direction.equals(Direction.DOWN) && !direction.equals(Direction.UP)) {
                    BlockPos newPos = blockPos.offset(direction);
                    if (world.getBlockState(newPos).getBlock().equals(Blocks.AIR)) {
                        world.setBlockState(newPos, state);
                    }
                }

            }
        }
    }
}
