package purple.mod.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
// import purple.mod.MattMod;
import purple.mod.items.armor.PotionChestplate;
@Mixin(CraftingResultSlot.class)
public class CraftingMixin extends Slot {
    @Shadow
    @Final
    private RecipeInputInventory input;
    @Shadow
    @Final
    private PlayerEntity player;
    public CraftingMixin(PlayerEntity player, RecipeInputInventory input, Inventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
      this.player = player;
      this.input = input;
   }



    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void potionChestplate(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        // MattMod.LOGGER.info("overide");

        if (stack.getItem() instanceof PotionChestplate chestplate){
            // MattMod.LOGGER.info("chestplate");
            for (int i = 0; i < this.input.size(); i++) {
                ItemStack ingredient = this.input.getStack(i);
                // MattMod.LOGGER.info(ingredient.toString());
                
                if (ingredient.isOf(Items.POTION)){
                    List<StatusEffectInstance> effects = PotionUtil.getPotionEffects(ingredient);
                    StatusEffectInstance effect = effects.get(0);
                    StatusEffectInstance finalEffect = new StatusEffectInstance(effect.getEffectType(),2,effect.getAmplifier(),false,true);
                    
                    chestplate.setEffectNbt(stack, finalEffect);
                    break;
                }
            }
        }

    }
}
