package purple.mod.items.swords;

import java.util.UUID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.attribute.EntityAttribute;
// import net.minecraft.entity.attribute.EntityAttribute;
// import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
// import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
// import net.minecraft.entity.effect.StatusEffectInstance;
// import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
// import purple.mod.StatusEffectAtter;

// the healing is done by the damage listener
public class LifeBlade extends SwordItem {
    final static UUID healthUUID = UUID.fromString("7020cf19-b501-4515-a244-42dd1da281e6");
    static {
        CraftQuest.CraftQuestEffectHandler.initAttribute(UUID.fromString("7020cf19-b501-4515-a244-42dd1da281e6"),
                EntityAttributes.GENERIC_MAX_HEALTH);
    }

    public LifeBlade() {
        super(ToolMaterials.IRON, 3, ModItems.SWORD_SPEED, new FabricItemSettings());

    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient())
            return;

        if (entity instanceof LivingEntity living) {
            // if (slot<=8){

            // // living.addStatusEffect(new
            // StatusEffectInstance(StatusEffects.HEALTH_BOOST, 0, 0, false, false, false));
            // //
            // CraftQuest.CraftQuestEffectHandler.addAtribute(EntityAttributes.GENERIC_MAX_HEALTH,
            // living, 1, Operation.MULTIPLY_TOTAL,healthUUID);

            // }
            CraftQuest.CraftQuestEffectHandler.logic(living, keepEffect(entity, slot), healthUUID,1,Operation.MULTIPLY_TOTAL);
        }
    
        // else{
        // if (entity instanceof PlayerEntity user){
        // PlayerInventory inventory =user.getInventory();
        // for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
        // if (inventory.getStack(i).isOf(ModItems.LIFE_BLADE)){
        // return;
        // }
        // }

        // }
        // CraftQuest.CraftQuestEffectHandler.removeAtribute(EntityAttributes.GENERIC_MAX_HEALTH,
        // living,
        // healthUUID);

        // }
        // }
    }

    boolean keepEffect(Entity entity,int slot) {
        boolean inInvintory = false;
        if (entity instanceof PlayerEntity user) {
            PlayerInventory inventory = user.getInventory();
            for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
                if (inventory.getStack(i).isOf(ModItems.LIFE_BLADE)) {
                    return true;
                }
            }

        }
        return inInvintory || slot<8;
    }

}
