package folk.sisby;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TotemAnywhereStandalone {
    public static ItemStack tryUsingDeathTotemFromInventory(LivingEntity entity, InteractionHand hand) {
        ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);

        if (entity instanceof Player player) {
            Inventory inventory = player.getInventory();

            if (inventory.contains(totem)) {
                if (player.getOffhandItem().getItem() == Items.TOTEM_OF_UNDYING) {
                    return player.getOffhandItem();
                } else {
                    return inventory.getItem(inventory.findSlotMatchingItem(totem));
                }
            }
        }

        return entity.getItemInHand(hand);
    }
}
