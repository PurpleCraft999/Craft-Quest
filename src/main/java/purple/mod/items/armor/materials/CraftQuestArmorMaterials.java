package purple.mod.items.armor.materials;

import net.minecraft.item.ArmorItem.Type;

import org.jetbrains.annotations.Nullable;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import purple.mod.CraftQuest;

public enum CraftQuestArmorMaterials implements ArmorMaterial {
   

    HOLY(ArmorMaterials.NETHERITE,"holy_armor",Ingredient.empty()),
    CLOUD(ArmorMaterials.CHAIN,"cloud_armor",Ingredient.empty()),
    POTION(ArmorMaterials.IRON,"potion",Ingredient.ofItems(Items.GLASS_BOTTLE));


    private CraftQuestArmorMaterials(ArmorMaterial base,String name,Ingredient ingredient){
        this.BASE = base;
        this.SET_NAME = name;
        this.RepairIngredient= ingredient;
    }

    @Nullable
    private final ArmorMaterial BASE;
    private final String SET_NAME;
    private final Ingredient RepairIngredient;

    @Override
    public int getDurability(Type type) {
        return BASE.getDurability(type)+0;
    }

    @Override
    public int getProtection(Type type) {
        return BASE.getProtection(type);
    }

    @Override
    public int getEnchantability() {
        return BASE.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return RepairIngredient;
    }

    @Override
    public String getName() {
        return CraftQuest.MOD_ID + ":"+ SET_NAME;
    }

    @Override
    public float getToughness() {
        return BASE.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return BASE.getKnockbackResistance();
    }
    
}
