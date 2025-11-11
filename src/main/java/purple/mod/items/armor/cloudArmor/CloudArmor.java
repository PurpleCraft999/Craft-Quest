package purple.mod.items.armor.cloudArmor;

// import Type;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
// import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
// import purple.mod.CraftQuest;
import purple.mod.ModItems;
import purple.mod.items.armor.CraftQuestArmorMaterials;

public class CloudArmor extends ArmorItem {
    float currentFlightTime =0;
    final Type type;
    public CloudArmor(Type type){
        super(CraftQuestArmorMaterials.CLOUD, type, new FabricItemSettings());
        this.type = type;
    }
    public float getMaxFlyTime(PlayerEntity user){
        float maxTime  = 0;
        if (user.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.ClOUD_CHESTPLATE)){
            maxTime+=80*20;
        }
        if (user.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.ClOUD_HELMET)) {
            maxTime += 30 * 20;
        }
        if (user.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.ClOUD_LEGGINGS)) {
            maxTime += 50 * 20;
        }
        if (user.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.ClOUD_BOOTS)) {
            maxTime += 20 * 20;
        }


        


        return maxTime;
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity user&&!user.getAbilities().creativeMode){

            Item item = user.getEquippedStack(this.type.getEquipmentSlot()).getItem();

            if (user.getEquippedStack(this.type.getEquipmentSlot()) == stack){
            if (this.currentFlightTime<=getMaxFlyTime(user) && !entity.isOnGround() && !user.getItemCooldownManager().isCoolingDown(item)){
                user.getAbilities().allowFlying = true;
                user.getAbilities().flying = true;
                this.currentFlightTime += 1;
            } else
            // if (this.currentFlightTime>getMaxFlyTime(user))
            {
                user.getAbilities().allowFlying = false;
                user.getAbilities().flying = false;
                // user
            }}
            
            // CraftQuest.LOGGER.info("max flight time: "+((Float)getMaxFlyTime(user)).toString());

            if (entity.isOnGround() && this.currentFlightTime>1){
                if (this.currentFlightTime !=getMaxFlyTime(user)){
                    user.getItemCooldownManager().set(item, 8*20);
                } else if (this.currentFlightTime == getMaxFlyTime(user)){
                    
                    user.getItemCooldownManager().set(item, 15 * 20);

                }
                this.currentFlightTime = 0; 

            }
            // if(!user.getItemCooldownManager().isCoolingDown(item)){

            // }

        
        }

        // CraftQuest.LOGGER.info("current time: "+((Float)this.currentFlightTime).toString());
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
