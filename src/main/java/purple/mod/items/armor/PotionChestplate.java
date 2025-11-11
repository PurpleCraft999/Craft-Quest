package purple.mod.items.armor;

import java.util.List;
// import java.util.UUID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
// import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
// import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
// import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
// import net.minecraft.recipe.Recipe;;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
import purple.mod.items.armor.materials.CraftQuestArmorMaterials;


public class PotionChestplate extends ArmorItem{
    StatusEffectInstance effect = null;
    final String uuidKey = CraftQuest.MOD_ID + "UUID";
    public PotionChestplate(){
        super(CraftQuestArmorMaterials.POTION, Type.CHESTPLATE, new FabricItemSettings());
        // this.uuid = UUID.randomUUID();
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) {return;}
        // setEffectNbt(stack, new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,1,1,true,true,true));
        boolean wearingChestPlate = false;
        if (entity instanceof LivingEntity livingEntity){
        ItemStack chestplate =  livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        
        if (chestplate !=null &&   stack == chestplate){
                
            wearingChestPlate = true;

        }
        if (livingEntity instanceof PlayerEntity player){
            if (player.currentScreenHandler.getCursorStack().isOf(ModItems.POTION_CHESTPLATE)){
                wearingChestPlate = false;
            }
        }
        }

        if (wearingChestPlate){
            this.effect = getEffectNbt(stack);
            if (effect != null && entity instanceof LivingEntity livingEntity){
                livingEntity.addStatusEffect(this.effect);
                
            }
        }
        // if (stack.getNbt().getString("effect") != ""){
            // String item =     stack.getNbt().getString("potion effect");
            
        // }
        

    
    }
    public void setEffectNbt(ItemStack stack,StatusEffectInstance statusEffect){
        NbtCompound nbt = stack.getOrCreateNbt();
        // StatusEffectInstance.fromNbt(nbt)
        NbtCompound effectNbt = new NbtCompound();
        statusEffect.writeNbt(effectNbt);
        nbt.put("effect", effectNbt);

    }
    StatusEffectInstance getEffectNbt(ItemStack stack){
        NbtCompound effectNbt = stack.getOrCreateNbt().getCompound("effect");
        StatusEffectInstance effect =   StatusEffectInstance.fromNbt(effectNbt);
        // MattMod.LOGGER.info("effect is "+effect);
        return effect;
       

    }
    


    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (nbt.get("effect") !=null){
        tooltip.add(Text.translatable(nbt.getCompound("effect").get("Id").asString()));
        }
        
    }
    
    // UUID getUuid(){
    //     ItemStack stack = new ItemStack(this);
    //     NbtCompound nbt =  stack.getOrCreateNbt();
    //     if (!nbt.containsUuid(uuidKey)){
    //         UUID random = UUID.randomUUID();
    //         nbt.putUuid(uuidKey, random);
    //         return random;
    //     } else{
    //         return nbt.getUuid(uuidKey);
    //     }
    // }
    
    
    
    
    



}
