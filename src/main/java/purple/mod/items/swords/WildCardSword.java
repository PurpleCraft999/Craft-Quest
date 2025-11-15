package purple.mod.items.swords;

import java.util.ArrayList;
import java.util.Iterator;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import purple.mod.ModItems;
import java.util.Random;

public class WildCardSword extends SwordItem{
    static final ArrayList<StatusEffect> BadEffects;
    static final ArrayList<StatusEffect> GoodEffects;

    private Random randomGenerator = new Random();
static{
    Iterator<StatusEffect> statuses = Registries.STATUS_EFFECT.iterator();

    ArrayList<StatusEffect> bad = new ArrayList<>();
    ArrayList<StatusEffect> good = new ArrayList<>();

    while (statuses.hasNext()) {
        StatusEffect effect = statuses.next();
        if (effect.isBeneficial()) {
            good.add(effect);
        }
        if (effect.getCategory().equals(StatusEffectCategory.HARMFUL)) {
            bad.add(effect);
        }

    }
    BadEffects = bad;
    GoodEffects = good;
}

    public WildCardSword(){
        super(ToolMaterials.DIAMOND, 3,ModItems.SWORD_SPEED,  new FabricItemSettings());
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        int length = randomGenerator.nextInt(20);
        int amp = randomGenerator.nextInt(2);
        int rand = randomGenerator.nextInt(11)+1;
        //bad effects
        if (rand<=1){
            int index = randomGenerator.nextInt(BadEffects.size());

            StatusEffect effect = BadEffects.get(index);
            StatusEffectInstance effectInstance = new StatusEffectInstance(effect,(length+1)*20 , amp+1);
            target.addStatusEffect(effectInstance);
        }
        //good effects
        else if (rand>=9){
            int index = randomGenerator.nextInt(GoodEffects.size());

            StatusEffect effect = GoodEffects.get(index);
            StatusEffectInstance effectInstance = new StatusEffectInstance(effect, (length + 1) * 20, amp + 1);
            attacker.addStatusEffect(effectInstance);
        }




        return super.postHit(stack, target, attacker);
    }
}
