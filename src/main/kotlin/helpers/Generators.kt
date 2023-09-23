package me.sirsam.minigameplugin.helpers

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class Generators : Listener {
    @EventHandler
    fun moveEvent(event: PlayerMoveEvent) {
        val player = event.player
        val pos = player.location
        pos.y -= 1
        val block = pos.block
        block.type = Material.STONE
    }

    @EventHandler
    fun sneakEvent(event: PlayerToggleSneakEvent) {
        val player = event.player
        val pos = player.location
        pos.y -= 1
        val block = pos.block
        block.type = Material.AIR
    }

}