package me.sirsam.minigameplugin.game

import me.sirsam.minigameplugin.Main
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

class Generator(var loc: Location, val type: Type) {
    enum class Type {
        BASE,
        DIAMOND,
        EMERALD,
    }

    private val spawnerTasks = mutableListOf<BukkitRunnable>()

    private fun createSpawner(material: Material): BukkitRunnable {
        return object : BukkitRunnable() {
            override fun run() {
                if (Game.state == Game.State.INACTIVE) return
                loc.world.dropItem(loc, ItemStack(material))
            }
        }
    }

    fun start() {
        val plugin = Main.instance

        when (type) {
            Type.BASE -> {
                spawnerTasks.add(createSpawner(Material.IRON_INGOT).apply {
                    runTaskTimer(plugin, 0, 10)
                })
                spawnerTasks.add(createSpawner(Material.GOLD_INGOT).apply {
                    runTaskTimer(plugin, 0, 160)
                })
            }
            Type.DIAMOND -> {
                spawnerTasks.add(createSpawner(Material.DIAMOND).apply {
                    runTaskTimer(plugin, 0, 900)
                })
            }
            Type.EMERALD -> {
                spawnerTasks.add(createSpawner(Material.EMERALD).apply {
                    runTaskTimer(plugin, 0, 1400)
                })
            }
        }
    }

    fun stop() {
        spawnerTasks.forEach { task ->
            task.cancel()
        }
        spawnerTasks.clear()
    }
}
