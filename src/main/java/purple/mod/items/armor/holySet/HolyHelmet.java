package purple.mod.items.armor.holySet;



import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import purple.mod.management.CraftQuestArmorMaterials;

public class HolyHelmet extends ArmorItem{


    public HolyHelmet(){
        super(CraftQuestArmorMaterials.HOLY,Type.HELMET,new FabricItemSettings());
    }
}

