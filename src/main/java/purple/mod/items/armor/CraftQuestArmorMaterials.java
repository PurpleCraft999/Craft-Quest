package purple.mod.items.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorItem.Type;

import java.util.EnumMap;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import purple.mod.CraftQuest;

public enum CraftQuestArmorMaterials implements ArmorMaterial {
   

    HOLY(ArmorMaterials.NETHERITE,"holy_armor",Ingredient.empty()),
    CLOUD(ArmorMaterials.CHAIN,"cloud_armor",Ingredient.empty()),
    SLIME(new int[]{0,0,0,300},new int[]{0,0,0,0},0,ArmorMaterials.TURTLE.getEquipSound(),Ingredient.ofItems(Items.SLIME_BALL),"slime_armor",0f,0f),
    POTION(ArmorMaterials.IRON,"potion",Ingredient.ofItems(Items.GLASS_BOTTLE));
    



    // EnumMap<ArmorItem.Type,Integer>, EnumMap<ArmorItem.Type,Integer>, int,SoundEvent, Ingredient, String, float, float
    private CraftQuestArmorMaterials(int[] durability, int[] protection,int enchantability,SoundEvent equipSound,Ingredient ingredient,String name,float toughness,float knockbackResistance){
        EnumMap<ArmorItem.Type, Integer> protectionMap = new EnumMap<>(Type.class);
        protectionMap.put(Type.HELMET, protection[0]);
        protectionMap.put(Type.CHESTPLATE, protection[1]);
        protectionMap.put(Type.LEGGINGS, protection[2]);
        protectionMap.put(Type.BOOTS, protection[3]);

        EnumMap<ArmorItem.Type, Integer> durabilityMap = new EnumMap<>(Type.class);
        durabilityMap.put(Type.HELMET, durability[0]);
        durabilityMap.put(Type.CHESTPLATE, durability[1]);
        durabilityMap.put(Type.LEGGINGS, durability[2]);
        durabilityMap.put(Type.BOOTS, durability[3]);

        this.SET_NAME = name;
        this.RepairIngredient = ingredient;
        this.protection = protectionMap;
        this.durability = durabilityMap;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        

    }
    
    CraftQuestArmorMaterials(ArmorMaterial base, String name, Ingredient ingredient) {
        EnumMap<ArmorItem.Type, Integer> durability = new EnumMap<>(Type.class);
        durability.put(Type.HELMET, base.getDurability(Type.HELMET));
        durability.put(Type.CHESTPLATE, base.getDurability(Type.CHESTPLATE));
        durability.put(Type.LEGGINGS, base.getDurability(Type.LEGGINGS));
        durability.put(Type.BOOTS, base.getDurability(Type.BOOTS));

        EnumMap<ArmorItem.Type, Integer> protection = new EnumMap<>(Type.class);
        protection.put(Type.HELMET, base.getProtection(Type.HELMET));
        protection.put(Type.CHESTPLATE, base.getProtection(Type.CHESTPLATE));
        protection.put(Type.LEGGINGS, base.getProtection(Type.LEGGINGS));
        protection.put(Type.BOOTS, base.getProtection(Type.BOOTS));

        this.RepairIngredient = ingredient;
        this.SET_NAME = name;
        this.durability = durability;
        this.protection = protection;
        this.enchantability = base.getEnchantability();
        this.equipSound = base.getEquipSound();
        this.knockbackResistance = base.getKnockbackResistance();
        this.toughness = base.getToughness();
    }

    
   




    private final String SET_NAME;
    private final Ingredient RepairIngredient;
    private final EnumMap<ArmorItem.Type, Integer> protection;
    private final EnumMap<ArmorItem.Type, Integer> durability;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    @Override
    public int getDurability(Type type) {
        return durability.get(type);
    }

    @Override
    public int getProtection(Type type) {
        return protection.get(type);
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
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
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
    
}
