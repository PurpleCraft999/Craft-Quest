package purple.mod.items.swords;

import java.util.List;
import java.util.UUID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
import purple.mod.management.CraftQuestToolMaterials;

public class SoulSword1000 extends SwordItem{
    // int killcount=0;
    ItemStack thisItem;
    final int maxLevel = 10;
    static final UUID uuid= UUID.fromString("da0b7a22-7066-44c6-b361-6cf3a060eb2b");
    static{
        CraftQuest.CraftQuestEffectHandler.initAttribute(uuid, EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }
    public SoulSword1000(){
        super(CraftQuestToolMaterials.SOUL, 12-5, 1.6f-4, new FabricItemSettings().maxDamage(6482));
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {


        thisItem = stack;
        if (target.isDead()){
            if (getKillCount(stack)==0){
                setLifeForce(stack, 1.5);
                addKillCount(stack, 1);
                // setFirstKill(stack, false);
            }
            else{
                if (getKillCount(stack)<maxLevel ){
                    // setLifeForce(stack, 1000f);

                addLifeForce(stack, .2);
                addKillCount(stack, 1);
                
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    void setLifeForce(ItemStack stack,double value){
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putDouble("life force", value);
    }
    double getLifeForce(ItemStack stack){
        double force =    stack.getOrCreateNbt().getDouble("life force");
        return force != 0.0 ? force:1;
    }
    void addLifeForce(ItemStack stack,double value){
        setLifeForce(stack,getLifeForce(stack)+value);
    }
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        String lifeForce = "Life Force: " +  String.format("%.1f",getLifeForce(stack));
        tooltip.add(Text.translatable(lifeForce));
        // tooltip.add(Text.translatable(((Integer)getKillCount(stack)).toString()));

        super.appendTooltip(stack, world, tooltip, context);
    }
    int getKillCount(ItemStack stack){
        return stack.getOrCreateNbt().getInt("kills");
    }
    void setKillCount(ItemStack stack,int value){
        stack.getOrCreateNbt().putInt("kills", value);
    }
    void addKillCount(ItemStack stack,int add){
        setKillCount(stack, getKillCount(stack)+add);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof LivingEntity livingEntity){
        CraftQuest.CraftQuestEffectHandler.logic(livingEntity, livingEntity.getMainHandStack().isOf(ModItems.SWORD_OF_SOULS),
                 uuid, getLifeForce(stack), Operation.MULTIPLY_TOTAL);
                }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    



}
