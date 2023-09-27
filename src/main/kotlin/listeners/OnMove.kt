package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.game.PlayerController
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class OnMove : Listener {
    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        val player = event.player
        val loc = player.location

        if (loc.y < 0) {
            PlayerController(player).playerDie(player.killer)
        }
    }
}