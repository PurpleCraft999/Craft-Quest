package purple.mod.items.other;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.management.TeamManager;
public class WeavingBow extends BowItem{
    public TeamManager WeaverTeam;

    public WeavingBow(){
        super(new FabricItemSettings().maxDamage(355));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.WeaverTeam ==null && entity instanceof PlayerEntity livingEntity && entity.getWorld() instanceof ServerWorld serverWorld){
            WeaverTeam = TeamManager.makeFriendlyTeam(livingEntity, serverWorld, "GMS_children");
            CraftQuest.LOGGER.info("added to team");
        }
    }
    
    
    
    
}
