package purple.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import purple.mod.items.armor.holySet.HolyChestplate;
import purple.mod.items.armor.holySet.HolySword;
import purple.mod.management.CraftQuestAttributeHandler;
import purple.mod.management.DamageBypass;
import purple.mod.management.TeamManager;

import java.util.HashMap;

import org.jetbrains.annotations.Nullable;

// import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraftQuest implements ModInitializer {
	public static final String MOD_ID = "craft-quest";
	public static CraftQuestAttributeHandler CraftQuestEffectHandler = new CraftQuestAttributeHandler();
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<DamageType> HOLY_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
			new Identifier(MOD_ID, "holy_damage"));

	public static HashMap<String, @Nullable TeamManager> TEAMS = new HashMap<>();
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
		// done for the team tagging
		ServerPlayConnectionEvents.JOIN.register(this::onJoin);
		ServerPlayerEvents.AFTER_RESPAWN.register(this::respawn);

		ModItems.initialize();

		LOGGER.info("Craft-Quest load finished");
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

	boolean onJoin(ServerPlayNetworkHandler network, PacketSender sender, MinecraftServer server) {

		ServerPlayerEntity player = network.getPlayer();
		HashMap<String, TeamManager> tempTeams = new HashMap<>(TEAMS.size());
		for (String key : TEAMS.keySet()) {
			tempTeams.put(key, TeamManager.makeFriendlyTeam(player, player.getServerWorld(), key));

		}
		TEAMS = tempTeams;

		

		return true;
	}


	void respawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive){


		CraftQuestEffectHandler.setAttribute(newPlayer, oldPlayer.getMaxHealth(), EntityAttributes.GENERIC_MAX_HEALTH);
	}


}