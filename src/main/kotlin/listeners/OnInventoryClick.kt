package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.ShopItems
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType.SlotType
import org.bukkit.inventory.ItemStack

class OnInventoryClick : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val items = ShopItems().items
        val player = event.whoClicked as Player
        val inventory = player.inventory
        val inv = event.clickedInventory
        val slot = event.slot

        fun buy() {

            event.isCancelled = true

            for (i in items) {
                if (i.slot == slot) {
                    val paymentMaterial = i.paymentMethod.material
                    val paymentAmount = i.priceAmount

                    if (inventory.containsAtLeast(ItemStack(paymentMaterial), paymentAmount)) {
                        inventory.removeItem(ItemStack(paymentMaterial, paymentAmount))
                        inventory.addItem(i.item)
                        Utils.broadcastSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, .5f, 1f)
                        Utils.sendMessage(player, Component.text("You received ", NamedTextColor.GREEN).append(i.item.displayName().append(Component.text("!", NamedTextColor.GREEN))))
                    }
                }
            }
        }

        if (event.slotType == SlotType.ARMOR) {
            event.isCancelled = true
        }

        when (inv?.holder) {
            is ShopItems -> {
                buy()
            }
        }
    }
}