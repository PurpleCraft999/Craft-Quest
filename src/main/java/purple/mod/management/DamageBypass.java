package purple.mod.management;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;

public class DamageBypass extends DamageSource{

    // DamageBypass(RegistryKey<DamageType>)
    public DamageBypass(RegistryEntry<DamageType> type){
        super(type);
    }
    
    
}
