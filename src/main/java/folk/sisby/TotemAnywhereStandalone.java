package folk.sisby;

import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TotemAnywhereStandalone {
    public static ItemStack tryUsingDeathTotemFromInventory(LivingEntity entity, Hand hand) {
        ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);

        if (entity instanceof PlayerEntity player) {
			PlayerInventory inventory = player.getInventory();

            if (inventory.contains(totem)) {
                if (player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
                    return player.getOffHandStack();
                } else {
                    return inventory.getStack(inventory.getSlotWithStack(totem));
                }
            }
        }

        return entity.getStackInHand(hand);
    }
}
