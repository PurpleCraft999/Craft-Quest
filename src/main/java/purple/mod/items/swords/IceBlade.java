package purple.mod.items.swords;

import java.util.List;
// import java.util.UUID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
// import net.minecraft.util.Hand;
// import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
// import purple.mod.CraftQuest;
import purple.mod.ModItems;
// import net.minecraft.item.ShieldItem;;
// import purple.mod.MattMod;



/*
Icicle blade: traits of a diamond sword, sword  deals 6 damage, 
give speed 1 when holding in main hand,
 hitting and target give slowness 1, 
 when user catches fire cause sword to become unusable for 6 seconds.
 */

public class IceBlade extends SwordItem {
    boolean melted = false;
    final int maxMeltTime = 120;
    // static{
        // CraftQuest.CraftQuestEffectHandler.addEffect(UUID.fromString("8b34055f-3574-49fe-aa5e-463b7d1141cc"), EntityAttributes.GENERIC_MOVEMENT_SPEED);
    // }
    
   
    public IceBlade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings){
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    public IceBlade(){
        super(ToolMaterials.DIAMOND, 3, ModItems.SWORD_SPEED+.1f, new FabricItemSettings());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        
        
        if (!this.melted){
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2*20,1),attacker);


        return super.postHit(stack, target, attacker);
        }
        else {
            return false;
        }
    }
    

    
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if (selected && entity instanceof LivingEntity livingEntity && !this.melted){
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,1,0,false,false));

        }
        if (entity.isOnFire()){
            this.melted = true;
            if (entity instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(this, maxMeltTime);
            }
            
            // MattMod.LOGGER.info("ice blade melted");
        } 
        if (this.melted && !entity.isOnFire() && entity instanceof PlayerEntity user){
            if (!user.getItemCooldownManager().isCoolingDown(this)){
                this.melted = false;
            }
            
            }
        
        



    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        String text;
        Formatting color;
        if (!this.melted){ 
            text = "Frozen";
            color = Formatting.AQUA;
            } else{
            text = "Melted";
            color = Formatting.YELLOW;
                };
        tooltip.add(Text.translatable(text).formatted(color));
    }

    @Override
    public float getAttackDamage() {
        if (this.melted){
            return 0;
        } else{
            return super.getAttackDamage();
        }
    }
    
    

    
    


    
    
    
    



}
