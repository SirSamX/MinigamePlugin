package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.helpers.Utils
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent
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
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerSleep(event: PlayerBedEnterEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) {
        Utils.broadcast(event.block.type.name)
        if (event.block.type !in listOf(Material.BLUE_BED, Material.RED_BED, Material.GREEN_BED, Material.YELLOW_BED)) return
        event.isCancelled = true
    }
}