package purple.mod.items.armor.holySet;

import java.util.UUID;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.attribute.EntityAttributeInstance;
// import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
import purple.mod.management.CraftQuestArmorMaterials;


public class HolyChestplate extends ArmorItem{
    public static final UUID HEALTH_UUID = UUID.fromString("4d064eb1-3f55-43b6-9a97-f32023b461e8");
    static{
        CraftQuest.CraftQuestEffectHandler.initAttribute(HEALTH_UUID, EntityAttributes.GENERIC_MAX_HEALTH);
    }
    public HolyChestplate(){
        super(CraftQuestArmorMaterials.HOLY,Type.CHESTPLATE,new FabricItemSettings());
    }

    // UUID getHealthUUID() {
    //     return HEALTH_UUID;
    // }
    public boolean wearingFullSet(LivingEntity entity){
        boolean head = entity.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.HOLY_HELMET);
        boolean chest = entity.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.HOLY_CHESTPLATE);
        boolean leg = entity.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.HOLY_LEGGINGS);
        boolean feet = entity.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.HOLY_BOOTS);


        return head && chest && leg && feet;
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient())
            return;

        if (entity instanceof LivingEntity user) {
            if (wearingFullSet(user)) {
                // addHealth(user, getHealthUUID());
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 1,true,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1, 0,true,false,false));
            } else {
                // removeHealth(user, getHealthUUID());
            }
            if (entity instanceof LivingEntity livingEntity){
            CraftQuest.CraftQuestEffectHandler.logic(livingEntity, wearingFullSet(user), HEALTH_UUID,80,Operation.ADDITION);
            }

        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // void addHealth(LivingEntity livingEntity, UUID uuid) {
    //     EntityAttributeInstance health = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

    //     if (health.getModifier(uuid) == null) {

    //         EntityAttributeModifier healthMod = new EntityAttributeModifier(
    //                 uuid, CraftQuest.MOD_ID + " holy armor health modifier ", 20*4, Operation.ADDITION);

    //         health.addPersistentModifier(healthMod);
    //     }
    // }

    // void removeHealth(LivingEntity livingEntity, UUID uuid) {
    //     EntityAttributeInstance health = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
    //     health.tryRemoveModifier(uuid);
    //     livingEntity.heal(0.0f);
    // }


}
