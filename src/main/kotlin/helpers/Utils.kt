@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package me.sirsam.minigameplugin.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.*
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta

object Utils {
    val prefix = Component.text("Bedwars", NamedTextColor.YELLOW).append(Component.text(" >> ", NamedTextColor.GREEN))

    fun sendMessage(player: Player, message: Component) { player.sendMessage(prefix.append(message)) }

    fun sendMessage(players: List<Player>, message: Component) { players.forEach { p ->  p.sendMessage(prefix.append(message)) } }

    fun broadcast(message: Component) { Bukkit.getOnlinePlayers().forEach { p -> p.sendMessage(prefix.append(message)) } }

    @Deprecated("Use Component instead of String!")
    fun broadcast(message: String) { Bukkit.getOnlinePlayers().forEach { p -> p.sendMessage(prefix.append(PlainTextComponentSerializer.plainText().deserialize(message))) } }

    fun receiveItemMessage(p: Player, a: Int, i: Component) { p.sendMessage(Component.text("You received $a ").append(i).append(Component.text("!"))) }

    fun formattingErrorMessage(p: Player) { p.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED)) }

    fun notPlayerMessage(p: CommandSender) { p.sendMessage(Component.text("Sender is not a player.")) }

    fun playerNotExistingMessage(p: CommandSender) { p.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED)) }

    fun noPermissionMessage(p: CommandSender) { p.sendMessage(Component.text("You don't have permission to do that!", NamedTextColor.RED)) }

    fun cooldownMessage(p: Player, eta: Long) { p.sendMessage(Component.text("Please wait ${"%.1f".format(eta.toFloat())}s before using this!", NamedTextColor.YELLOW)) }

    fun isNumeric(input: String): Boolean { return input.toDoubleOrNull() != null }

    fun changeLeatherArmourColor(meta: ItemMeta, color: Color): LeatherArmorMeta {
        val leatherArmorMeta = meta as LeatherArmorMeta
        leatherArmorMeta.setColor(color)
        return leatherArmorMeta
    }

    fun changeLeatherArmourColor(itemStack: ItemStack, color: Color): ItemStack {
        itemStack.itemMeta = changeLeatherArmourColor(itemStack.itemMeta, color)
        return itemStack
    }

    fun broadcastSound(sound: Sound, soundCategory: SoundCategory, volume: Float, pitch: Float) {
        Bukkit.getOnlinePlayers().forEach {
            it.playSound(it, sound, soundCategory, volume, pitch)
        }
    }

    fun playSound(sound: Sound, soundCategory: SoundCategory, volume: Float, pitch: Float) {
        Bukkit.getOnlinePlayers().forEach {
            it.playSound(it, sound, soundCategory, volume, pitch)
        }
    }

    fun destroy(item: ItemStack, quantity: Int) {
        if (item.amount <= quantity) {
            item.amount = 0
        } else {
            item.amount -= quantity
        }
    }

    fun isWool(material: Material): Boolean {
        return when (material) {
            Material.WHITE_WOOL,
            Material.ORANGE_WOOL,
            Material.MAGENTA_WOOL,
            Material.LIGHT_BLUE_WOOL,
            Material.YELLOW_WOOL,
            Material.LIME_WOOL,
            Material.PINK_WOOL,
            Material.GRAY_WOOL,
            Material.LIGHT_GRAY_WOOL,
            Material.CYAN_WOOL,
            Material.PURPLE_WOOL,
            Material.BLUE_WOOL,
            Material.BROWN_WOOL,
            Material.GREEN_WOOL,
            Material.RED_WOOL,
            Material.BLACK_WOOL -> true
            else -> false
        }
    }
}