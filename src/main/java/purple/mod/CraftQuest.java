package purple.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
// import net.minecraft.entity.damage.DamageTypes;
// import net.minecraft.entity.damage.DamageTypes;
// import purple.mod.ModItems;
import net.minecraft.item.ItemStack;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import purple.mod.items.armor.holySet.HolyChestplate;
import purple.mod.items.armor.holySet.HolySword;

// import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraftQuest implements ModInitializer {
	public static final String MOD_ID = "craft-quest";
	public static CraftQuestAttributeHandler CraftQuestEffectHandler = new CraftQuestAttributeHandler();
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<DamageType> HOLY_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
			new Identifier(MOD_ID, "holy_damage"));

	// public RegistryEntry<DamageType> HOLY = RegistryEntry.Directnew
	// DamageType(MOD_ID+"Holy damage type")

	@Override
	public void onInitialize() {
		LOGGER.info("Craft-Quest load start");

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// this is done so multiple weapons can use their passive effects
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(this::damageListener);

		ModItems.initialize();

		LOGGER.info("Craft-Quest initialized");
	}



	private boolean damageListener(LivingEntity target, DamageSource source, float amount) {
		if (target.getWorld().isClient()) {
			return false;
		}

		if (source instanceof DamageBypass) {
			return true;
		}

		
		if (source.getAttacker() instanceof LivingEntity attacker) {
			ItemStack mainHand = attacker.getMainHandStack();
			// life blade lifeSteal
			if (mainHand.isOf(ModItems.LIFE_BLADE)) {
				attacker.heal(amount * .3f);
			}
			if (mainHand.getItem() instanceof HolySword holySword) {
				RegistryEntry<DamageType> type = target.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE)
						.getEntry(HOLY_DAMAGE).orElseThrow(() -> new IllegalStateException());
				DamageSource bypass = new DamageBypass(type);
				float mult = 1.0f;
				if (ModItems.HOLY_CHESTPLATE instanceof HolyChestplate c && c.wearingFullSet(attacker)) {
					mult = 1.25f;
				}
				if (target.isPlayer()) {

					float damageAmount = HolySword.calcCustomDamage(target, .2f * mult);
					target.damage(bypass, damageAmount);
					holySword.postHit(mainHand, target, attacker);
					// return false;

				} else {
					float damageAmount = HolySword.calcCustomDamage(target, .4f * mult);

					target.damage(bypass, damageAmount);
					holySword.postHit(mainHand, target, attacker);
					// return false;
				}

			}
			
		}

		return true;
	}

}