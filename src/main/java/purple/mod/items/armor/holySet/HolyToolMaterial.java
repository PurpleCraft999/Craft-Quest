package purple.mod.items.armor.holySet;


import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;


public class HolyToolMaterial implements ToolMaterial{
    // public static final HolyMatrial INSTANCE = new HolyMatrial();
    @Override
    public float getAttackDamage() {
        return ToolMaterials.DIAMOND.getAttackDamage();
    }
    //tool
    @Override
    public int getDurability() {
        return ToolMaterials.DIAMOND.getDurability();
    }

    //armor
    @Override
    public int getEnchantability() {
        return ArmorMaterials.NETHERITE.getEnchantability();
    }

    @Override
    public int getMiningLevel() {
        return ToolMaterials.DIAMOND.getMiningLevel();
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.DIAMOND.getMiningSpeedMultiplier();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
    
}

