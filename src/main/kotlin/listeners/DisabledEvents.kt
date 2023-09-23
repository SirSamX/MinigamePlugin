package me.sirsam.minigameplugin.listeners

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.inventory.CraftItemEvent

class DisabledEvents : Listener {
    @EventHandler
    fun onCraft(event: CraftItemEvent) {
        if (event.whoClicked.gameMode != GameMode.CREATIVE) {
            event.isCancelled = true
        }
    }

    /*
    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            event.isCancelled = true
        }
    }*/

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val player = event.player

        if (block.type !in listOf(Material.BLUE_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.YELLOW_WOOL) && player.gameMode != GameMode.CREATIVE) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onEnchantEvent(event: EnchantItemEvent) {
        event.isCancelled = true
    }
}