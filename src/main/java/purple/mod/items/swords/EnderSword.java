package purple.mod.items.swords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.ModItems;
//TODO make this
public class EnderSword extends SwordItem {
    public EnderSword(){
        super(ToolMaterials.IRON, 2, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // user.teleport(ITEM_BAR_STEPS, DEFAULT_MAX_USE_TIME, DEFAULT_MAX_COUNT);

        return super.use(world, user, hand);
    }

}
