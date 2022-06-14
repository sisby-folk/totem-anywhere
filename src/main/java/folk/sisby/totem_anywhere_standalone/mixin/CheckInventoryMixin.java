package folk.sisby.totem_anywhere_standalone.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
        method = "checkTotemDeathProtection",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"
        )
    )
    private ItemStack hookCheckTotemDeathProtection(LivingEntity livingEntity, InteractionHand hand) {
        return TotemAnywhereStandalone.tryUsingDeathTotemFromInventory(livingEntity, hand);
    }
}
