package purple.mod.items.armor.holySet;



import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import purple.mod.items.armor.materials.HolyArmorMaterial;

public class HolyHelmet extends ArmorItem{


    public HolyHelmet(){
        super(HolyArmorMaterial.INSTANCE,Type.HELMET,new FabricItemSettings());
    }
}

