package purple.mod.items.armor.holySet;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import purple.mod.items.armor.CraftQuestArmorMaterials;

public class HolyLeggings extends ArmorItem {

    public HolyLeggings() {
        super(CraftQuestArmorMaterials.HOLY,Type.LEGGINGS,new FabricItemSettings());
    }


}