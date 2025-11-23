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


import java.util.HashMap;

public class CraftQuestAttributeHandler {
    static HashMap<UUID,EntityAttribute> effectuuids = new HashMap<>();
    public CraftQuestAttributeHandler(){


    }
    public void addAttribute(LivingEntity entity,double effectStrength,Operation operation,UUID effectUuid){
        
        EntityAttribute attribute = getAttribute(effectUuid);
        if (attribute ==null){
            // CraftQuest.LOGGER.warn("Could Not Get Attribute: "+effectUuid);
            return;
        }
        EntityAttributeInstance atter = entity.getAttributeInstance(attribute);

        
        if (atter.getModifier(effectUuid) == null) {
            
            
        EntityAttributeModifier mod =   new EntityAttributeModifier(
                effectUuid, CraftQuest.MOD_ID+attribute.toString()+entity.toString(), effectStrength, operation);
        CraftQuest.LOGGER.info("added effect "+effectUuid+" to "+getName(entity));

        // atter.addPersistentModifier(mod);
        atter.addTemporaryModifier(mod);
        }
    }
    public void setAttribute(LivingEntity entity,double effectStrength,EntityAttribute attribute){


        EntityAttributeInstance atter = entity.getAttributeInstance(attribute);
        atter.setBaseValue(effectStrength);


    }


    public void removeAttribute( LivingEntity entity,UUID effectUuid){

        EntityAttribute atribute = getAttribute(effectUuid);

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

     public void initAttribute(UUID u,EntityAttribute e){
        effectuuids.put(u, e);
    }
    public void logic(LivingEntity entity,boolean keepCon,UUID uuid, double effectStrength,Operation operation){
        if (entity.getWorld().isClient()){
            return;
        }


        // CraftQuest.LOGGER.info("running");

            //slot<=8
            if (keepCon){
                CraftQuest.CraftQuestEffectHandler.addAttribute(entity, effectStrength, operation, uuid);  
            }  else{
                // CraftQuest.LOGGER.info("remove effect");
            CraftQuest.CraftQuestEffectHandler.removeAttribute(entity,uuid);
            }
        
    }
    private String getName(LivingEntity entity){
        return TeamManager.getName(entity);
    }




    @Nullable
    EntityAttribute getAttribute(UUID uuid){
        EntityAttribute atter = effectuuids.get(uuid);
        
        if (atter==null){
            CraftQuest.LOGGER.error("Could not get attribute uuid: "+uuid);
            return null;
        }
        return atter;   
    } 

}
