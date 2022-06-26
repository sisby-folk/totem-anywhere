package folk.sisby.totem_anywhere_standalone.mixin;

import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import folk.sisby.TotemAnywhereStandalone;

@Mixin(LivingEntity.class)
public class CheckInventoryMixin {
    /**
     * Defer to TotemWorksFromInventory.tryUsingDeathTotemFromInventory when checking if the entity is holding a totem.
     * If the check passes (the entity has one in inventory) then return true.
     */
    @Redirect(
        method = "tryUseTotem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"
        )
    )
    private ItemStack hookCheckTotemDeathProtection(LivingEntity livingEntity, Hand hand) {
        return TotemAnywhereStandalone.tryUsingDeathTotemFromInventory(livingEntity, hand);
    }
}
