package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.Shop
import me.sirsam.minigameplugin.helpers.Utils
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.LargeFireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

class OnInteract : Listener {
    @EventHandler
    fun throwFireball(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.type != Material.FIRE_CHARGE) return

        val strength = 2
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            Utils.destroy(item, 1)
        }
        val fireball = player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))
        fireball.setIsIncendiary(false)
    }

    @EventHandler
    fun onRightClickShop(event: PlayerInteractEntityEvent) {
        val player = event.player

        if (event.rightClicked.type == EntityType.VILLAGER) {
            player.openInventory(Shop().inventory)
            event.isCancelled = true
        } else if (event.rightClicked.type == EntityType.WANDERING_TRADER) {
            player.openInventory(Shop().inventory)
            event.isCancelled = true
        }
    }
}