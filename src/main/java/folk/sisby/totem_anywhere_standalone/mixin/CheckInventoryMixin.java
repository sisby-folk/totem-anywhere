package folk.sisby.totem_anywhere_standalone.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class CheckInventoryMixin {
    @Redirect(
        method = "tryUseTotem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"
        )
    )
    private ItemStack hookCheckTotemDeathProtection(LivingEntity livingEntity, Hand hand) {
		if (livingEntity instanceof PlayerEntity player) {
			if (player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack())) {
				if (player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
					return player.getOffHandStack();
				} else {
					return player.getInventory().getStack(player.getInventory().getSlotWithStack(Items.TOTEM_OF_UNDYING.getDefaultStack()));
				}
			}
		}
		return livingEntity.getStackInHand(hand);
    }
}
