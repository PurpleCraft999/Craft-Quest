package purple.mod.items.pickaxes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import purple.mod.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
public class ObsidianPickaxe extends PickaxeItem {
    public ObsidianPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, FabricItemSettings settings){
        super(material,attackDamage,attackSpeed,settings);
   }
   public ObsidianPickaxe(){
     super(ToolMaterials.DIAMOND, 1, ModItems.PICKAXE_SPEED-.3f,new FabricItemSettings().maxDamage(ToolMaterials.DIAMOND.getDurability() * 3));
   }





    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.OBSIDIAN || state.getBlock() ==Blocks.CRYING_OBSIDIAN) {
            return 150f;
        } else {
            return super.getMiningSpeedMultiplier(stack, state);
        }
        
    }

    @Override
    public boolean isFireproof() {
         return true;
    }




}
