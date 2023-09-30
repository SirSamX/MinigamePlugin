package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class OnBlockPlace : Listener {
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val blockY = event.block.location.y
        val player = event.player

        if (event.player.gameMode != GameMode.CREATIVE) {
            if (blockY <=50) {
                event.isCancelled = true
                Utils.sendMessage(player, Component.text("You cant place this low!", NamedTextColor.RED))
            } else if (blockY >= 90) {
                event.isCancelled = true
                Utils.sendMessage(player, Component.text("You cant place this high!", NamedTextColor.RED))
            }
        }
    }
}