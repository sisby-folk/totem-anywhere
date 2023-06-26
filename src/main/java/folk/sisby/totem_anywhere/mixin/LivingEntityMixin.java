package folk.sisby.totem_anywhere.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("ConstantConditions")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@ModifyVariable(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Hand;values()[Lnet/minecraft/util/Hand;", ordinal = 0), ordinal = 0)
	private ItemStack totemStackInventoryFallback(ItemStack totemStack) {
		if ((Object) this instanceof PlayerEntity player && player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack())) {
			return player.getInventory().getStack(player.getInventory().getSlotWithStack(Items.TOTEM_OF_UNDYING.getDefaultStack()));
		}
		return totemStack;
	}
}
