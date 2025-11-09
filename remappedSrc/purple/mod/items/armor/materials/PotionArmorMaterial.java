package purple.mod.items.armor.materials;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import purple.mod.CraftQuest;

public class PotionArmorMaterial implements ArmorMaterial {
    public static final PotionArmorMaterial INSTANCE = new PotionArmorMaterial();
    @Override
    public int getDurability(Type type) {
        return ArmorMaterials.IRON.getDurability(type);
    }
    @Override
    public int getEnchantability() {
        return ArmorMaterials.IRON.getEnchantability();
    }
    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.IRON.getEquipSound();
    }
    @Override
    public String getName() {
        return CraftQuest.MOD_ID + ":" +"potion";
    }
    @Override
    public float getKnockbackResistance() {
        return ArmorMaterials.IRON.getKnockbackResistance();
    }
    @Override
    public int getProtection(Type type) {
        return ArmorMaterials.IRON.getProtection(type);
    }
    @Override
    public float getToughness() {
        return ArmorMaterials.IRON.getToughness();
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.GLASS_BOTTLE);
    }
}
