package purple.mod.items.swords;


import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.damage.DamageSource;
// import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.loot.LootTable;
// import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import purple.mod.ModItems;

public class LooterSword extends SwordItem{
    public LooterSword(){
        super(ToolMaterials.DIAMOND, 3, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (world.isClient()) return false;
        if (target.isDead()){
            MinecraftServer server = target.getServer();
            
            LootTable loot = server.getLootManager().getLootTable(target.getLootTable());
            
            LootContextParameterSet.Builder paramsBuilder = new LootContextParameterSet.Builder((ServerWorld)world).add(LootContextParameters.THIS_ENTITY, attacker)
            .add(LootContextParameters.ORIGIN, target.getPos()).add(LootContextParameters.DAMAGE_SOURCE,target.getDamageSources().mobAttack(attacker))
            .add(LootContextParameters.TOOL, stack).add(LootContextParameters.ORIGIN, attacker.getPos())
            .add(LootContextParameters.EXPLOSION_RADIUS, 0f).add(LootContextParameters.KILLER_ENTITY, attacker).add(LootContextParameters.LAST_DAMAGE_PLAYER,(PlayerEntity)target.getLastAttacker())
            .add(LootContextParameters.BLOCK_STATE,null);
            LootContextParameterSet  params =   paramsBuilder.build(LootContextTypes.GENERIC);
    

        ObjectArrayList<ItemStack> generatedLoot = loot.generateLoot(params);
        
        for (ItemStack item:generatedLoot){
            ItemEntity entity = new ItemEntity(world,target.getX(),target.getY(),target.getZ(),item);
            world.spawnEntity(entity);
        }
            
        }



        return super.postHit(stack, target, attacker);
    }
}
