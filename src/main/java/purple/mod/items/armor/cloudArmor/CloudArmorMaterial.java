package purple.mod.items.armor.cloudArmor;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import purple.mod.CraftQuest;


public class CloudArmorMaterial implements ArmorMaterial,ToolMaterial {
    public static final CloudArmorMaterial INSTANCE = new CloudArmorMaterial();

    @Override
    public int getDurability(Type type) {
        return ArmorMaterials.CHAIN.getDurability(type);
    }

    @Override
    public int getEnchantability() {
        return ArmorMaterials.CHAIN.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.CHAIN.getEquipSound();
    }

    @Override
    public String getName() {
        return CraftQuest.MOD_ID + ":" + "cloud_armor";
    }

    @Override
    public float getKnockbackResistance() {
        return ArmorMaterials.CHAIN.getKnockbackResistance();
    }

    @Override
    public int getProtection(Type type) {
        return ArmorMaterials.CHAIN.getProtection(type);
    }

    @Override
    public float getToughness() {
        return ArmorMaterials.CHAIN.getToughness();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.WHITE_WOOL);
    }

    //tool stuff
    @Override
    public float getAttackDamage() {
        return ToolMaterials.DIAMOND.getAttackDamage();
    }
    @Override
    public int getDurability() {
        return ToolMaterials.DIAMOND.getDurability();
    }
    @Override
    public int getMiningLevel() {
        return ToolMaterials.DIAMOND.getMiningLevel();
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.DIAMOND.getMiningSpeedMultiplier();
    }
}
