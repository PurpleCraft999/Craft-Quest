package purple.mod.mixin;


import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;
import purple.mod.CraftQuest;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    
    private MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world){
        super(entityType,world);
        CraftQuest.LOGGER.info("Started mob mixin");
    }


    @Inject(method = "setTarget",at=@At("HEAD"),cancellable = true)
    public void TagTargeter(@Nullable LivingEntity target,CallbackInfo ci){
        if (hasTeamTag(target)){
            ci.cancel();
            
        }
    }
    @Override
    public boolean canTarget(LivingEntity target) {
        
        if (hasTeamTag(target)){
            return false;
        }


        return super.canTarget(target);
    }

    boolean hasTeamTag(@Nullable LivingEntity target){
        if (target instanceof PlayerEntity) {
            Team myTeam = target.getWorld().getScoreboard().getPlayerTeam(this.getUuidAsString());
            if (myTeam != null && target.getCommandTags().contains(myTeam.getName())) {

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isTeammate(Entity other) {
        if (hasTeamTag(attackingPlayer)){
            return true;
        }


        return super.isTeammate(other);
    }

    




}
