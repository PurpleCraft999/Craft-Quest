package purple.mod.items.armor.holySet;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import purple.mod.items.armor.materials.HolyArmorMaterial;

public class HolyBoots extends ArmorItem {

    public HolyBoots(){
        super(HolyArmorMaterial.INSTANCE,Type.BOOTS,new FabricItemSettings());
    }

}
