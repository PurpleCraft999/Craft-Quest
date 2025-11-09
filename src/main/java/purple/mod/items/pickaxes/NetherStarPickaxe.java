package purple.mod.items.pickaxes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.world.World;
import purple.mod.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
public class NetherStarPickaxe extends PickaxeItem{
    public NetherStarPickaxe(){

        super(ToolMaterials.NETHERITE, 1, ModItems.PICKAXE_SPEED, new FabricItemSettings());
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (slot<=8){
            if (entity instanceof LivingEntity livingEntity){
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,1,1,true,false,false));
            }
        }
    }
    @Override
    public boolean isFireproof() {
        return true;
    }
    

}
