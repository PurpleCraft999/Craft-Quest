package purple.mod;
import java.util.UUID;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
// import net.minecraft.entity.effect.StatusEffect;
// import net.minecraft.entity.effect.StatusEffectCategory;
// import net.minecraft.entity.effect.StatusEffectInstance;
// import net.minecraft.entity.effect.StatusEffectUtil;
// import net.minecraft.entity.effect.StatusEffects;
// import net.minecraft.entity.attribute.EntityAttributes;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerInventory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CraftQuestAttributeHandler {
    static HashMap<UUID,EntityAttribute> effectuuids = new HashMap<>();
    public CraftQuestAttributeHandler(){


    }
    public void addAtribute(LivingEntity entity,double effectStrength,Operation operation,UUID effectUuid){
        
        EntityAttribute atribute = getAttributeUuid(effectUuid);
        if (atribute ==null){
            return;
        }
        EntityAttributeInstance atter = entity.getAttributeInstance(atribute);

        
        if (atter.getModifier(effectUuid) == null) {
            
            
        EntityAttributeModifier mod =   new EntityAttributeModifier(
                effectUuid, CraftQuest.MOD_ID+atribute.toString()+entity.toString(), effectStrength, operation);


        atter.addPersistentModifier(mod);
        }
    }

    public void removeAtribute( LivingEntity entity,UUID effectUuid){
        EntityAttribute atribute = getAttributeUuid(effectUuid);
        if (atribute==null){
            return;
        }
        EntityAttributeInstance health = entity.getAttributeInstance(atribute);
       

        health.tryRemoveModifier(effectUuid);
        entity.heal(0.0f);
    
    }

     public void addEffect(UUID u,EntityAttribute e){
        effectuuids.put(u, e);
    }
    public void logic(Entity entity,boolean keepCon,@Nullable Predicate<Entity> dontRemove,UUID uuid, double effectStrength,Operation operation){
        // EntityAttribute atter = getAttributeUuid(uuid);



        if (entity instanceof LivingEntity living) {
            //slot<=8
            if (keepCon){

                
                CraftQuest.CraftQuestEffectHandler.addAtribute(living, effectStrength, operation, uuid);
                
            }  else{
                if (dontRemove !=null){
                if (dontRemove.test(living)){
                    return;
                }
                }
            CraftQuest.CraftQuestEffectHandler.removeAtribute(living,
                    uuid);
            
            }
        }
    }




    @Nullable
    EntityAttribute getAttributeUuid(UUID uuid){
        EntityAttribute atter = effectuuids.get(uuid);
        if (atter==null){
            CraftQuest.LOGGER.error("could not get uuid"+uuid);
            return null;
        }
        return atter;   
    } 
    

    

    

public static byte[] ToBytes(UUID obj) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            
            oos.writeObject(obj);
            oos.flush();

            
            return bos.toByteArray();
        } 
            
        catch (IOException e) {
            return new byte[0];
        }
        
        
        
    }
}
