package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.game.controllers.PlayerController
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerController = PlayerController(player)

        event.joinMessage(Component.text(""))
        event.player.sendMessage(Component.text("Welcome to the MiniGames Server!", NamedTextColor.GREEN))

        playerController.reset()
        playerController.giveWool()
    }
}