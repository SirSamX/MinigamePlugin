package me.sirsam.minigameplugin.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(Component.text("Welcome to the MiniGames Server!", NamedTextColor.GREEN))
        event.player.inventory.clear()
        event.player.inventory.addItem(ItemStack(Material.RED_WOOL))
        event.player.inventory.addItem(ItemStack(Material.GREEN_WOOL))
        event.player.inventory.addItem(ItemStack(Material.BLUE_WOOL))
        event.player.inventory.addItem(ItemStack(Material.YELLOW_WOOL))
    }
}