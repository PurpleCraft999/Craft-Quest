package purple.mod.items.swords;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import  net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.world.World;
import purple.mod.ModItems;
public class BitterBlade extends SwordItem{
    final int NEG_DURRATION_EFFECT = 2*20;
    public BitterBlade(){
        super(ToolMaterials.NETHERITE, 3, ModItems.SWORD_SPEED-.6f, new FabricItemSettings().fireproof());
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,NEG_DURRATION_EFFECT), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, NEG_DURRATION_EFFECT), attacker);

        return super.postHit(stack, target, attacker);
    }

    // @Override
    // public void onCraft(ItemStack stack, World world) {
    //     default_enchants(stack);
        

    // }
    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        default_enchants(stack);
        super.onCraft(stack, world, player);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!EnchantmentHelper.get(stack).containsKey(Enchantments.KNOCKBACK)){
            default_enchants(stack);
        }
    }

    void default_enchants(ItemStack stack){
        stack.addEnchantment(Enchantments.KNOCKBACK, 1);
    }


}
