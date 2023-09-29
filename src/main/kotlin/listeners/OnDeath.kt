package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.game.controllers.PlayerController
import org.bukkit.Sound

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class OnDeath : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val player = event.player

        event.deathSound = Sound.ENTITY_WOLF_HOWL
        PlayerController(player).playerDie(player.killer)

        event.isCancelled = true
    }
}