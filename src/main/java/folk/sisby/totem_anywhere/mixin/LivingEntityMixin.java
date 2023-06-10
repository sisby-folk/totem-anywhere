package folk.sisby.totem_anywhere.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Redirect(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"))
	private ItemStack replaceHandStackWithInventoryStack(LivingEntity livingEntity, Hand hand) {
		return (livingEntity instanceof PlayerEntity player && player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack()) && player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING && player.getMainHandStack().getItem() != Items.TOTEM_OF_UNDYING) ? player.getInventory().getStack(player.getInventory().getSlotWithStack(Items.TOTEM_OF_UNDYING.getDefaultStack())) : livingEntity.getStackInHand(hand);
	}
}
