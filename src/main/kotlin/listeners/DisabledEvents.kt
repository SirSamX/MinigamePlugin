package me.sirsam.minigameplugin.listeners

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerBedEnterEvent


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
    fun onEnchantEvent(event: EnchantItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockBreak(event: BlockDamageEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerSleep(event: PlayerBedEnterEvent) {
        event.isCancelled = true
    }
}