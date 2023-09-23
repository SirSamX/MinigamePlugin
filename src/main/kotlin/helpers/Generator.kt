package me.sirsam.minigameplugin.helpers

import me.sirsam.minigameplugin.Main
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Item
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.scheduler.BukkitRunnable

class Generator(var loc: Location, val type: Type) {
    enum class Type {
        BASE,
        DIAMOND,
        EMERALD,
    }

    private val spawnUpdate = SpawnUpdate(type, loc)

    fun moveEvent(event: PlayerMoveEvent) {
        val player = event.player
        val pos = player.location
        pos.y += 5
        val block = pos.block
        block.type = Material.STONE
    }

    fun run() {
        spawnUpdate.runTaskTimer(Main.instance, 0, 10)
    }

    fun stop() {
        spawnUpdate.cancel()
    }

    private class SpawnUpdate(private val type: Type, private val loc: Location) : BukkitRunnable() {
        override fun run() {
            when (type) {
                Type.BASE -> {
                    val item = Item::class.java
                    item.

                    loc.world.spawn(loc, Item::class.java)
                }
                Type.DIAMOND -> {
                    loc.world.spawn(loc, Item::class.java)
                }
                Type.EMERALD -> {
                    loc.world.spawn(loc, Item::class.java)
                }
            }
        }
    }
}