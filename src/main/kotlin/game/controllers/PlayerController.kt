package me.sirsam.minigameplugin.game.controllers

import me.sirsam.minigameplugin.Main
import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable


class PlayerController(val player: Player) {
    private val inventory = player.inventory
    val team = Game.getTeamByPlayer(player)

    fun reset() {
        player.exp = 0f
        player.totalExperience = 0
        player.health = 20.0
        player.foodLevel = 20
        player.gameMode = GameMode.SURVIVAL
        player.allowFlight = false
        player.isInvulnerable = false
        player.inventory.clear()
    }

    fun changeName(name: Component) {
        player.playerListName(name)
        player.customName(name)
        player.displayName(name)
    }

    fun setup() {
        reset()
        gotoSpawnLocation()
        changeName(Component.text(player.name, team!!.chatColor))

        val sword = ItemStack(Material.WOODEN_SWORD)
        sword.itemMeta.isUnbreakable = true
        player.inventory.addItem(sword)

        val leatherHelmet = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_HELMET), team.color)
        leatherHelmet.itemMeta.isUnbreakable = true
        leatherHelmet.addEnchantment(Enchantment.WATER_WORKER, 1)
        player.inventory.setItem(EquipmentSlot.HEAD, leatherHelmet)

        val leatherChestplate = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_CHESTPLATE), team.color)
        leatherChestplate.itemMeta.isUnbreakable = true
        player.inventory.setItem(EquipmentSlot.CHEST, leatherChestplate)

        val leatherLeggings = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_LEGGINGS), team.color)
        leatherLeggings.itemMeta.isUnbreakable = true
        player.inventory.setItem(EquipmentSlot.LEGS, leatherLeggings)

        val leatherBoots = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_BOOTS), team.color)
        leatherBoots.itemMeta.isUnbreakable = true
        leatherBoots.addEnchantment(Enchantment.PROTECTION_FALL, 1)
        player.inventory.setItem(EquipmentSlot.FEET, leatherBoots)
    }

    fun playerDie(killer: Player?) {
        spectator()
        if (team == null) return

        if (killer == null) {
            Utils.broadcast(
                Component.text(player.name, NamedTextColor.RED).append(Component.text(" died!", NamedTextColor.GRAY))
            )
        } else {
            Utils.broadcast(
                Component.text(player.name, NamedTextColor.RED)
                    .append(Component.text(" was killed by ${killer.name}!", NamedTextColor.GRAY))
            )
        }
        Utils.broadcastSound(Sound.ENTITY_WOLF_HOWL, SoundCategory.PLAYERS, 1f, 1f)

        player.showTitle(Title.title(Component.text("You died!", NamedTextColor.RED), if (killer == null) Component.text("") else Component.text("You were killed by ${killer.name}!")))

        object : BukkitRunnable() {
            var i = 5
            override fun run() {
                if (i == 0) {
                    gotoSpawnLocation()
                    setup()
                    player.sendActionBar(Component.text(""))
                    cancel()
                    return
                }
                player.sendActionBar(Component.text("Respawning in $i secounds!", NamedTextColor.RED))
                i--
            }
        }.runTaskTimer(Main.instance, 0, 20)
    }

    fun spectator() {
        reset()
        player.gameMode = GameMode.SPECTATOR
        gotoSpawnLocation(true)
    }

    fun giveWool() {
        inventory.addItem(ItemStack(Material.RED_WOOL))
        inventory.addItem(ItemStack(Material.GREEN_WOOL))
        inventory.addItem(ItemStack(Material.BLUE_WOOL))
        inventory.addItem(ItemStack(Material.YELLOW_WOOL))
    }

    fun gotoSpawnLocation(globalSpawn: Boolean = false) {
        Bukkit.getLogger().info("Player: ${player.name}, Team: ${team?.name}")
        player.teleport(getSpawnLocation(globalSpawn))
    }

    fun getSpawnLocation(globalSpawn: Boolean = false): Location {
        return if (team != null && !globalSpawn) {
            Main.config.getLocation("spawnLocations.${team.name}")!!
        } else {
            Main.config.getLocation("spawnLocations.NONE")!!
        }
    }
}