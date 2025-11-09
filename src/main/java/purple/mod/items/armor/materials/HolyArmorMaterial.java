package purple.mod.items.armor.materials;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import purple.mod.CraftQuest;

public final class HolyArmorMaterial implements ArmorMaterial{

    HolyArmorMaterial(int durabilityMultiplier, int[] protectionAmounts, int enchantability,
            net.minecraft.recipe.Ingredient repairIngredient, float toughness, float knockbackResistance) {
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    public static final net.minecraft.item.ArmorMaterial INSTANCE = new HolyArmorMaterial(30, new int[] { 3, 6, 8, 3 }, 15,
            Ingredient.ofItems(Items.NETHERITE_INGOT), 1.0f, 0.0f);
    
    
    
        @Override
        public int getDurability(net.minecraft.item.ArmorItem.Type type) {
            return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
        }

        @Override
        public int getProtection(net.minecraft.item.ArmorItem.Type type) {
            return this.protectionAmounts[type.ordinal()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public net.minecraft.sound.SoundEvent getEquipSound() {
            return net.minecraft.sound.SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE; // Example sound
        }

        @Override
        public net.minecraft.recipe.Ingredient getRepairIngredient() {
            return this.repairIngredient;
        }

        @Override
        public String getName() {
            return CraftQuest.MOD_ID + ":" + "holy_armor";
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }


        
        private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
        private final int durabilityMultiplier;
        private final int[] protectionAmounts;
        private final int enchantability;
        private final net.minecraft.recipe.Ingredient repairIngredient;
        private final float toughness;
        private final float knockbackResistance;
    



    
}
