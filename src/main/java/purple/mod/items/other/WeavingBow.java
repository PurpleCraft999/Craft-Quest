package purple.mod.items.other;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
    final static String TeamName = "GMS_children";
    static{
        CraftQuest.TEAMS.put(TeamName, null);
    }
    public WeavingBow(){
        super(new FabricItemSettings().maxDamage(355));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.WeaverTeam ==null){
            WeaverTeam = CraftQuest.TEAMS.get(TeamName);
        }
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isAlive()){
            WeaverTeam.setTeamTarget(target);
        }
        return super.postHit(stack, target, attacker);
    }
    
    
    
}
