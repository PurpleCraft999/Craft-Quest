package purple.mod.items.other;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
public class WeavingBow extends BowItem{
    public TeamManager WEAVERTEAM = new TeamManager();
    Team team;
    public WeavingBow(){
        super(new FabricItemSettings().maxDamage(355));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.team ==null && entity instanceof LivingEntity livingEntity && entity.getWorld() instanceof ServerWorld serverWorld){
            team = WEAVERTEAM.makeFriendlyTeam(livingEntity, serverWorld, "GMS_CHILDREN");
        }
    }
    
    
    
    
}
