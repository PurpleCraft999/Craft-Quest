package purple.mod.items.armor.cloudArmor;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.ModItems;

public class CloudSword extends SwordItem {
    public CloudSword(){

        //TODO make it cloud material
        super(ToolMaterials.DIAMOND,3, ModItems.SWORD_SPEED, new FabricItemSettings());
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)){
        user.getItemCooldownManager().set(this, 30*20);
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 10*20, 0, false, false));
        return TypedActionResult.success(user.getMainHandStack());
        }
        return super.use(world, user, hand);
    }
}
