package purple.mod.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
// import net.minecraft.client.render.entity.feature.FeatureRenderer;
// import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
// import net.minecraft.client.render.model.BakedModelManager;
// import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import purple.mod.ModItems;

@Mixin(ArmorFeatureRenderer.class)
public abstract class CloudArmorMixin { 



	@Inject(method = "renderArmor", at = @At("HEAD"),cancellable = true)
	private void cloudArmorInvisibility(
			MatrixStack matrices,
			VertexConsumerProvider vertexConsumers,
			LivingEntity entity,
			EquipmentSlot armorSlot, 
			int light,
			BipedEntityModel<?> model,
			CallbackInfo ci
	) {
		// CraftQuest.LOGGER.info("hello world help me please");
		if (entity.hasStatusEffect(StatusEffects.INVISIBILITY)){
			ItemStack armor = entity.getEquippedStack(armorSlot);
			if (armor.isOf(ModItems.ClOUD_HELMET) || armor.isOf(ModItems.ClOUD_CHESTPLATE) || armor.isOf(ModItems.ClOUD_LEGGINGS)  || armor.isOf(ModItems.ClOUD_BOOTS)){
				ci.cancel();
			}
		}
	}
}
