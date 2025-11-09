package purple.mod.items.armor;

import java.util.List;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
// import purple.mod.CraftQuest;
import purple.mod.ModItems;
import purple.mod.items.armor.materials.SlimeArmorMaterial;


public class SlimeBoots extends ArmorItem{
    float reverseFall = 0.0f;
    // boolean doBounce = true;
    public SlimeBoots(){
        super(new SlimeArmorMaterial(), Type.BOOTS, new FabricItemSettings());
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world.isClient()) return;
        
        if(entity instanceof LivingEntity livingEntity){
            boolean wearingBoots = livingEntity.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.SLIME_BOOTS);

        if (livingEntity.fallDistance>.5 && wearingBoots){
            this.reverseFall = (float)livingEntity.getVelocity().getY();


            // if(!this.isFallingFromBoots){
            // this.reverseFall-=livingEntity.fallDistance;
            // }
                
            
        if (!entity.bypassesLandingEffects()){
           livingEntity.fallDistance = 0;
           }
            //  livingEntity.getVelocity();
        }

        if (this.reverseFall <0 && livingEntity.isOnGround()&& wearingBoots){
            // CraftQuest.LOGGER.info(entity.getVelocity().toString());
            Vec3d velocity = entity.getVelocity();
            if (Math.abs(velocity.y) > 0.001){
                this.reverseFall= this.reverseFall*.95f;
            }


            // this.isFallingFromBoots = false;
            if (!entity.bypassesLandingEffects()){
            bounce(livingEntity);
            // this.isFallingFromBoots =true;
            } else{
                // this.isFallingFromBoots = false;
            }
            
            
            livingEntity.handleFallDamage(livingEntity.fallDistance, 0.0f, world.getDamageSources().fall());
            

            
            this.reverseFall =0;
            // this.isFallingFromBoots = false;
        }

        // if(this.isFallingFromBoots && this.reverseFall==0 && livingEntity.fallDistance==0){
        //     this.isFallingFromBoots = false;
        // }
        
        
        
        }



        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (this.reverseFall < 0.0) {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setVelocity(vec3d.x, -this.reverseFall * d, vec3d.z);
            entity.velocityModified = true;
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("Bouncy Bounce"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    
}
