package purple.mod.items.swords;

import java.util.UUID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
// import net.minecraft.entity.damage.DamageSource;
// import net.minecraft.entity.damage.DamageSources;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
// import net.minecraft.loot.context.LootContextParameter;

import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;

public class LooterSword extends SwordItem{
    static final UUID baseUUID = UUID.fromString("5dca7b85-3618-49c3-a6ce-b683b772cd78");

    static final UUID effectUUID = UUID.fromString("bb5fe1c9-a10c-445c-8709-9897bb129028");

    static{
        CraftQuest.CraftQuestEffectHandler.initAttribute(baseUUID, EntityAttributes.GENERIC_LUCK);

        CraftQuest.CraftQuestEffectHandler.initAttribute(effectUUID, EntityAttributes.GENERIC_LUCK);
    }
    public LooterSword(){
        super(ToolMaterials.DIAMOND, 3, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();

        if (world.isClient()) return false;

        if (target.isDead()){
            // CraftQuest.LOGGER.info("dead");
            // target.onDeath(target.getRecentDamageSource());
            // MinecraftServer server = target.getServer();
            // DamageSource damageSource = target.getRecentDamageSource();

            // LootTable lootTable = server.getLootManager().getLootTable(target.getLootTable());
            
            // LootContextParameterSet.Builder builder = (new LootContextParameterSet.Builder(
            //         (ServerWorld) target.getWorld())).add(LootContextParameters.THIS_ENTITY, target)
            //         .add(LootContextParameters.ORIGIN, target.getPos())
            //         .add(LootContextParameters.DAMAGE_SOURCE, damageSource)
            //         .addOptional(LootContextParameters.KILLER_ENTITY, attacker)
            //         .addOptional(LootContextParameters.DIRECT_KILLER_ENTITY, damageSource.getSource());
            // if (attacker.isPlayer() && target.getAttacker() != null) {
            //     builder = builder.add(LootContextParameters.LAST_DAMAGE_PLAYER, (PlayerEntity)attacker).luck(((PlayerEntity)attacker).getLuck());
            // }

            // LootContextParameterSet lootContextParameterSet = builder.build(LootContextTypes.ENTITY);
            // lootTable.generateLoot(lootContextParameterSet, 0l, target::dropStack);
        // ObjectArrayList<ItemStack> generatedLoot = lootTable.generateLoot(params);
        // if (attacker instanceof PlayerEntity user){
        //     // user.getInventory().insertStack(generatedLoot.get(0));
        // }
        }


        // if (world instanceof ServerWorld sw){
        // for (ItemStack item:generatedLoot){
        //     ItemEntity entity = new ItemEntity(world,target.getX(),target.getY(),target.getZ(),item);
        //     sw.spawnEntity(entity);
            
        // }
            
        // }}



        return super.postHit(stack, target, attacker);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        // stack.addAttributeModifier(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, getOrCreateTranslationKey(), slot, null)EntityAttributeModifier, null);
            if (stack.isOf(ModItems.LOOTER_SWORD)&&EnchantmentHelper.get(stack).get(Enchantments.LOOTING)==null){
            stack.addEnchantment(Enchantments.LOOTING, 3);
            }

        
        
    
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
