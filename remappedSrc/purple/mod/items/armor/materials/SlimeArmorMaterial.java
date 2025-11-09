package purple.mod.items.armor.materials;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import purple.mod.CraftQuest;

public class SlimeArmorMaterial implements ArmorMaterial{
    // static SlimeArmorMaterial Instance = new SlimeArmorMaterial();
    @Override
    public int getProtection(Type type) {
        

        return 0;
    }
    @Override
    public int getDurability(Type type) {
        
        return 195;
    }
    @Override
    public float getToughness() {
        return 0;
    }
    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.TURTLE.getEquipSound();
    }
    @Override
    public int getEnchantability() {
        return 9;
    }
    @Override
    public float getKnockbackResistance() {
        return 0;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.SLIME_BALL);
    }
    @Override
    public String getName() {
    return CraftQuest.MOD_ID + ":" +"slime";    
    }
}
