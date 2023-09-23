package me.sirsam.minigameplugin.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack


class PlayerController(val player: Player) {
    val inventory = player.inventory

    fun reset() {
        player.exp = 0f
        player.totalExperience = 0
        player.health = 20.0
        player.foodLevel = 20
        player.gameMode = GameMode.SURVIVAL
        player.allowFlight = false
        player.inventory.clear()
    }

    fun bedwarsSetup() {
        reset()

        val sword = ItemStack(Material.WOODEN_SWORD)
        sword.itemMeta.isUnbreakable = true
        player.inventory.addItem(ItemStack(Material.WOODEN_SWORD))

        val leatherHelmet = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_HELMET), Game.getTeamByPlayer(player)!!.color)
        leatherHelmet.itemMeta.isUnbreakable = true
        leatherHelmet.addEnchantment(Enchantment.WATER_WORKER, 1)
        player.inventory.setItem(EquipmentSlot.HEAD, leatherHelmet)

        val leatherChestplate = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_CHESTPLATE), Game.getTeamByPlayer(player)!!.color)
        leatherChestplate.itemMeta.isUnbreakable = true
        player.inventory.setItem(EquipmentSlot.CHEST, leatherChestplate)

        val leatherLeggings = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_LEGGINGS), Game.getTeamByPlayer(player)!!.color)
        leatherLeggings.itemMeta.isUnbreakable = true
        player.inventory.setItem(EquipmentSlot.LEGS, leatherLeggings)

        val leatherBoots = Utils.changeLeatherArmourColor(ItemStack(Material.LEATHER_BOOTS), Game.getTeamByPlayer(player)!!.color)
        leatherBoots.itemMeta.isUnbreakable = true
        leatherBoots.addEnchantment(Enchantment.PROTECTION_FALL, 1)
        player.inventory.setItem(EquipmentSlot.FEET, leatherBoots)
    }

    fun playerDie(killer: Player?) {
        if (killer == null) {
            Utils.broadcast(Component.text(player.name, NamedTextColor.RED).append(Component.text(" died!", NamedTextColor.GRAY)))
        } else {
            Utils.broadcast(Component.text(player.name, NamedTextColor.RED).append(Component.text(" was killed by ${killer.name}", NamedTextColor.GRAY)))
        }
        reset()
        Utils.broadcastSound(Sound.ENTITY_WOLF_HOWL, SoundCategory.PLAYERS, 1f, 1f)
        player.gameMode = GameMode.SPECTATOR
        player.teleport(Location(player.world, 0.5, 95.0, 0.5))
    }

    fun giveWool() {
        inventory.addItem(ItemStack(Material.RED_WOOL))
        inventory.addItem(ItemStack(Material.GREEN_WOOL))
        inventory.addItem(ItemStack(Material.BLUE_WOOL))
        inventory.addItem(ItemStack(Material.YELLOW_WOOL))
    }
}