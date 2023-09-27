package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.ShopItems
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class OnInventoryClick : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val inv = event.clickedInventory
        when (inv?.holder) {
            is ShopItems -> {
                when (event.slot) {
                    0 -> {
                        event.isCancelled = true
                    }
                }
            }
        }
    }
}