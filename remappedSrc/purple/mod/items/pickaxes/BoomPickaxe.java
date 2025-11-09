package purple.mod.items.pickaxes;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;
// import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import purple.mod.ModItems;

// import net.minecraft.world.explosion.Explosion;

public class BoomPickaxe extends PickaxeItem{
    public BoomPickaxe(){
        super(ToolMaterials.DIAMOND, 1, ModItems.PICKAXE_SPEED, new FabricItemSettings().maxDamage(ToolMaterials.DIAMOND.getDurability()/3));
    }
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.isToolRequired()){
        explosion(miner, pos, world);
        }
        


        return super.postMine(stack, world, state, pos, miner);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        
        explosion(attacker, target.getBlockPos(), attacker.getWorld());

        return super.postHit(stack, target, attacker);
    }

    void explosion(LivingEntity miner, BlockPos pos, World world){
        world.createExplosion(miner, pos.getX(), pos.getY(), pos.getZ(), 10f,ExplosionSourceType.TNT);

    }
}
