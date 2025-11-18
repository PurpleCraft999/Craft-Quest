package purple.mod.management;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public enum CraftQuestToolMaterials implements ToolMaterial{

    SOUL(ToolMaterials.NETHERITE,Ingredient.EMPTY),
    CLOUD(ToolMaterials.DIAMOND,Ingredient.EMPTY),
    HOLY(ToolMaterials.DIAMOND,Ingredient.EMPTY);
    

    CraftQuestToolMaterials(ToolMaterial base,Ingredient ingredient){
        this.RepairIngredient = ingredient;
        this.attack =base.getAttackDamage();
        this.durability = base.getDurability();
        this.enchantability =base.getEnchantability();
        this.miningLevel = base.getMiningLevel();
        this.miningSpeed= base.getMiningSpeedMultiplier();

    }

        @Override
    public float getAttackDamage() {
        return attack;
    }
    //tool
    @Override
    public int getDurability() {
        return durability;
    }

    //armor
    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return RepairIngredient;
    }


    private final Ingredient RepairIngredient;
    private final float attack;
    private final int durability;
    private final int enchantability;
    private final int miningLevel;
    private final float miningSpeed;
}
