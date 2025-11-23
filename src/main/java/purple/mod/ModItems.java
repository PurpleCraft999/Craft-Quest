package purple.mod;


import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

// import net.minecraft.item.ToolMaterials;

import net.minecraft.registry.Registries;


// import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import purple.mod.items.armor.PotionChestplate;
import purple.mod.items.armor.SlimeBoots;
import purple.mod.items.armor.cloudArmor.CloudBoots;
import purple.mod.items.armor.cloudArmor.CloudChestplate;
import purple.mod.items.armor.cloudArmor.CloudHelmet;
import purple.mod.items.armor.cloudArmor.CloudLeggings;
import purple.mod.items.armor.cloudArmor.CloudSword;
// import purple.mod.items.armor.cloudArmor.CloudLeggins;
import purple.mod.items.armor.holySet.HolyBoots;
import purple.mod.items.armor.holySet.HolyChestplate;
import purple.mod.items.armor.holySet.HolyHelmet;
import purple.mod.items.armor.holySet.HolyLeggings;
import purple.mod.items.armor.holySet.HolySword;
import purple.mod.items.other.WeavingBow;
import purple.mod.items.pickaxes.BoomPickaxe;
import purple.mod.items.pickaxes.MinerPickaxe;
import purple.mod.items.pickaxes.NetherStarPickaxe;
import purple.mod.items.pickaxes.ObsidianPickaxe;
import purple.mod.items.swords.BitterBlade;
import purple.mod.items.swords.EnderSword;
import purple.mod.items.swords.IceBlade;
import purple.mod.items.swords.LifeBlade;
import purple.mod.items.swords.LooterSword;
import purple.mod.items.swords.SoulSword1000;
import purple.mod.items.swords.UndeadSword;
import purple.mod.items.swords.VampSword;
import purple.mod.items.swords.WildCardSword;
public class ModItems {

        public static final float SWORD_SPEED = -2.4f;
        public static final float PICKAXE_SPEED = -2.5f;



        public static final Item OBSIDIAN_PICKAXE = registerItem(new ObsidianPickaxe(), "obsidian_pickaxe");
        public static final Item ICE_BLADE = registerItem(new IceBlade(), "ice_blade");
        public static final Item NETHER_STAR_PICKAXE = registerItem(new NetherStarPickaxe(), "nether_star_pickaxe");
        public static final Item BITTER_BLADE = registerItem(new BitterBlade(), "bitter_blade");
        public static final Item LIFE_BLADE = registerItem(new LifeBlade(), "life_blade");
        public static final Item POTION_CHESTPLATE = registerItem(new PotionChestplate(), "potion_chestplate");
        public static final Item BOOM_PICKAXE = registerItem(new BoomPickaxe(), "boom_pickaxe");
        public static final Item MINER_PICKAXE = registerItem(new MinerPickaxe(), "miner_pickaxe");
        public static final Item UNDEAD_SWORD = registerItem(new UndeadSword(), "undead_sword");
        //holy set
        public static final Item HOLY_SWORD = registerItem(new HolySword(), "holy_sword");
        public static final Item HOLY_CHESTPLATE = registerItem(new HolyChestplate(), "holy_chestplate");
        public static final Item HOLY_HELMET = registerItem(new HolyHelmet(), "holy_helmet");
        public static final Item HOLY_LEGGINGS = registerItem(new HolyLeggings(), "holy_leggings");
        public static final Item HOLY_BOOTS = registerItem(new HolyBoots(), "holy_boots");
        
        public static final Item SLIME_BOOTS = registerItem(new SlimeBoots(), "slime_boots");

        public static final Item ClOUD_CHESTPLATE = registerItem(new CloudChestplate(), "cloud_chestplate");
        public static final Item ClOUD_HELMET = registerItem(new CloudHelmet(), "cloud_helmet");
        public static final Item ClOUD_LEGGINGS = registerItem(new CloudLeggings(), "cloud_leggings");
        public static final Item ClOUD_BOOTS = registerItem(new CloudBoots(), "cloud_boots");
        public static final Item ClOUD_SWORD = registerItem(new CloudSword(), "cloud_sword");
        public static final Item LOOTER_SWORD = registerItem(new LooterSword(), "looter_sword");        
        public static final Item WILD_CARD_SWORD = registerItem(new WildCardSword(), "wild_card_sword");
        public static final Item WEAVING_BOW = registerItem(new WeavingBow(), "weaving_bow");
        public static final Item SWORD_OF_SOULS = registerItem(new SoulSword1000(), "sword_of_1000_souls");
        public static final Item ENDER_SWORD = registerItem(new EnderSword(), "ender_sword");

        public static final Item VAMPIRE_SWORD = registerItem(new VampSword(), "vampire_sword");


        public static Item registerItem(Item item, String id) {
                // Create the identifier for the item.
                Identifier itemID = new Identifier(CraftQuest.MOD_ID, id);

                // Register the item.
                Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

                // Return the registered item!
                return registeredItem;
        }
        

        public static void initialize() {
                
    

                // adds the tools to tool group
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                        .register((itemGroup) -> itemGroup.add(OBSIDIAN_PICKAXE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                        .register((itemGroup) -> itemGroup.add(NETHER_STAR_PICKAXE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                        .register((itemGroup) -> itemGroup.add(MINER_PICKAXE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                        .register((itemGroup) -> itemGroup.add(BOOM_PICKAXE));

                // adds weapons to combat group
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ICE_BLADE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(BITTER_BLADE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(LIFE_BLADE));
                
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(UNDEAD_SWORD));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(LOOTER_SWORD));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(WILD_CARD_SWORD));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(WEAVING_BOW));
                   ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                .register((itemGroup) -> itemGroup.add(SWORD_OF_SOULS));     
                                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                   .register((itemGroup) -> itemGroup.add(ENDER_SWORD));
                                   ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                                .register((itemGroup) -> itemGroup.add(VAMPIRE_SWORD));
                //adds armor to group
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                .register((itemGroup) -> itemGroup.add(POTION_CHESTPLATE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                .register((itemGroup) -> itemGroup.add(SLIME_BOOTS));




                        //adds holy items to group
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                .register((itemGroup) -> itemGroup.add(HOLY_SWORD));
               
                
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                                .register((itemGroup) -> itemGroup.add(HOLY_HELMET));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(HOLY_CHESTPLATE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(HOLY_LEGGINGS));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(HOLY_BOOTS));

                // adds cloud stuff to the group
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ClOUD_HELMET));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ClOUD_CHESTPLATE));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ClOUD_LEGGINGS));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ClOUD_BOOTS));
                ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                        .register((itemGroup) -> itemGroup.add(ClOUD_SWORD));




                

        }
}
