package me.sirsam.minigameplugin.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(Component.text("Welcome to the MiniGames Server!", NamedTextColor.GREEN))
    }
}