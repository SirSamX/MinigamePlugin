package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.ShopItems
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class OnInventoryClick : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val player = event.whoClicked as Player
        val inv = event.clickedInventory

        when (inv?.holder) {
            is ShopItems -> {
                when (event.slot) {
                    0 -> {
                        event.isCancelled = true
                        player.inventory.addItem(ItemStack(Material.TNT))
                        player.sendMessage(Component.text(">>", NamedTextColor.GREEN))
                    }
                }
            }
        }
    }
}