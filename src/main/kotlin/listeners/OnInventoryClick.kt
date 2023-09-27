package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.ShopItems
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
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

        fun buy(item: Material) {
            event.isCancelled = true
            player.inventory.addItem(ItemStack(item))
            Utils.broadcastSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, .5f, 1f)
            Utils.sendMessage(player, Component.text("You received ${item.name}!", NamedTextColor.GREEN))
        }

        when (inv?.holder) {
            is ShopItems -> {
                when (event.slot) {
                    0 -> {
                        buy(Material.TNT)
                    }

                    1 -> {
                        buy(Material.DIAMOND)
                    }
                }
            }
        }

    }
}