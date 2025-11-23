package purple.mod.items.swords;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import purple.mod.CraftQuest;
import purple.mod.ModItems;

public class VampSword extends SwordItem {

    public VampSword() {
        super(ToolMaterials.NETHERITE, 3, ModItems.SWORD_SPEED, new FabricItemSettings());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (target instanceof PlayerEntity && target.isDead()) {

            vampEffect(target, false);

            vampEffect(attacker, true);

        }

        return super.postHit(stack, target, attacker);
    }

    void vampEffect(LivingEntity entity, boolean positive) {

        int effectStrength = positive ? 2 : -2;
        CraftQuest.CraftQuestEffectHandler.setAttribute(entity, entity.getMaxHealth() + effectStrength,
                EntityAttributes.GENERIC_MAX_HEALTH);
        entity.heal(0.0f);
        // UUID effectUuid = genUuidWithID();
        // CraftQuest.CraftQuestEffectHandler.initAttribute(effectUuid,
        // EntityAttributes.GENERIC_MAX_HEALTH);
        // CraftQuest.CraftQuestEffectHandler.addAttribute(entity, effectStrength,
        // Operation.ADDITION, effectUuid);
    }
}
