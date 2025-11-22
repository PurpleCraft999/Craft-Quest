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
import net.minecraft.world.Heightmap;
public class EnderSword extends SwordItem {
    public EnderSword(){
        super(ToolMaterials.IRON, 2, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if ( !user.getItemCooldownManager().isCoolingDown(this)){
        double new_x = ((int)(Math.random()*(30+30+1)-30))+user.getX();
        double new_z = ((int)(Math.random()*(30+30+1)-30))+user.getZ();
        double new_y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, ((Double)new_x).intValue(), ((Double)new_z).intValue());
        user.teleport(new_x, new_y, new_z);
        user.getItemCooldownManager().set(this, 30*20);
        }

       

        return super.use(world, user, hand);
    }

}
