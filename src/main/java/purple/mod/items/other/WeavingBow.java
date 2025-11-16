package purple.mod.items.other;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
public class WeavingBow extends BowItem{
    public WeavingBow(){
        super(new FabricItemSettings().maxDamage(355));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // CraftQuest.LOGGER.info("shoot");
        return super.use(world, user, hand);
    }
    
    
    
}
