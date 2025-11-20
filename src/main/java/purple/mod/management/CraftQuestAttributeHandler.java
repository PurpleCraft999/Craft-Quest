package purple.mod.management;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

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
import purple.mod.CraftQuest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class CraftQuestAttributeHandler {
    static HashMap<UUID,EntityAttribute> effectuuids = new HashMap<>();
    public CraftQuestAttributeHandler(){


    }
    public void createAttribute(LivingEntity entity,double effectStrength,Operation operation,UUID effectUuid){
        
        EntityAttribute atribute = getAttributeUuid(effectUuid);
        if (atribute ==null){
            return;
        }
        EntityAttributeInstance atter = entity.getAttributeInstance(atribute);

        
        if (atter.getModifier(effectUuid) == null) {
            
            
        EntityAttributeModifier mod =   new EntityAttributeModifier(
                effectUuid, CraftQuest.MOD_ID+atribute.toString()+entity.toString(), effectStrength, operation);
        CraftQuest.LOGGER.info("added effect "+effectUuid+" to "+getName(entity));

        // atter.addPersistentModifier(mod);
        atter.addTemporaryModifier(mod);
        }
    }


    public void removeAttribute( LivingEntity entity,UUID effectUuid){

        EntityAttribute atribute = getAttributeUuid(effectUuid);

        if (atribute==null || !entity.getAttributes().hasModifierForAttribute(atribute, effectUuid)){
            return;
        }
        EntityAttributeInstance instance = entity.getAttributeInstance(atribute);
       
        instance.clearModifiers();
        if (instance.tryRemoveModifier(effectUuid)){
            CraftQuest.LOGGER.info("Effect "+effectUuid+" cleared from "+getName(entity));
        } 
        // else{
        //     CraftQuest.LOGGER.info("Effect "+effectUuid+" failed to clear from "+getName(entity));
        // }
                
    
    }

     public void addAttribute(UUID u,EntityAttribute e){
        effectuuids.put(u, e);
    }
    public void logic(LivingEntity entity,boolean keepCon,UUID uuid, double effectStrength,Operation operation){
        if (entity.getWorld().isClient()){
            return;
        }


        // CraftQuest.LOGGER.info("running");

            //slot<=8
            if (keepCon){
                CraftQuest.CraftQuestEffectHandler.createAttribute(entity, effectStrength, operation, uuid);  
            }  else{
                // CraftQuest.LOGGER.info("remove effect");
            CraftQuest.CraftQuestEffectHandler.removeAttribute(entity,uuid);
            }
        
    }
    private String getName(LivingEntity entity){
        return TeamManager.getName(entity);
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
    

    

    

public static byte[] UUIDToBytes(UUID obj) {
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
