package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.helpers.Utils
import org.bukkit.Material
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent

class OnProjectileHit : Listener {
    private val explosionRadius = 5

    @EventHandler
    fun onProjectileHit(event: ProjectileHitEvent) {
        if (event.entity is Fireball) {
            val impactBlock = event.hitBlock

            if (impactBlock != null) {
                for (x in -explosionRadius..explosionRadius) {
                    for (y in -explosionRadius..explosionRadius) {
                        for (z in -explosionRadius..explosionRadius) {
                            val block = impactBlock.world.getBlockAt(impactBlock.x + x, impactBlock.y + y, impactBlock.z + z)
                            if (Utils.isWool(block.type)) {
                                block.type = Material.AIR
                            }
                        }
                    }
                }
            }
        }
    }
}