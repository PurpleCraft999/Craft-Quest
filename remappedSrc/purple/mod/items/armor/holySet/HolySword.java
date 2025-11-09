package purple.mod.items.armor.holySet;

import java.util.List;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
// import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
import java.util.ArrayList;
import java.util.HashSet;
/*
 Holy sword: traits of a diamond sword, damage dealt to players deal 20% of the target player max health,
  damage dealt to mobs deal 40% max health, 
  right clicking causes a shockwave dealing 10% max health of all entities in a ten block radius,
   10 second cooldown once ability is used.
 */
public class HolySword extends SwordItem{
    ArrayList<Integer> enemies = new ArrayList<Integer>();
    public HolySword(){
        super(new HolyToolMaterial(), -3, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.enemies.add(target.getId());
        CraftQuest.LOGGER.info("size: "+((Integer) this.enemies.size()).toString());
        return super.postHit(stack, target, attacker);
    }
    public static float calcCustomDamage(LivingEntity target,float percent){
        float maxHealth = target.getMaxHealth();
        float damageAmount = maxHealth * percent;
        if (damageAmount < 1) {
            damageAmount = 1f;
        }
        return damageAmount;
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (context.isAdvanced()){
        tooltip.add(Text.translatable("Does 20% of the players's max health and 40% to mobs").formatted(Formatting.YELLOW));
        } else{
            tooltip.add(Text.translatable("Strike down your foes with gods wrath")
                    .formatted(Formatting.YELLOW));
        }
        tooltip.add(Text.translatable("When right clicked gods lightning strikes on your foes "));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        CraftQuest.LOGGER.info("use start");
        ItemStack stack  = user.getMainHandStack();
        if (world.isClient()) {
            
		    return TypedActionResult.pass(stack);
            
	    }
        CraftQuest.LOGGER.info("server");
        
        HashSet<Integer> usedIds= new HashSet<Integer>();
        for (int id:this.enemies){
            if (!usedIds.contains(id)){
            Entity enemy = world.getEntityById(id);
            usedIds.add(id);
            CraftQuest.LOGGER.info("entity"+id);
            if (enemy != null){
                LightningEntity bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                CraftQuest.LOGGER.info("Summoned lightning");
                bolt.setPosition(enemy.getPos());
                world.spawnEntity(bolt);
            }
            }
        }
        return TypedActionResult.success(stack);


        // return super.use(world, user, hand);
    }
    
}
