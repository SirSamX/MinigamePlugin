package me.sirsam.minigameplugin.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemStack

class ShopItems : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true

        val gui = Bukkit.createInventory(null, 5 * 9, Component.text("Shop", NamedTextColor.DARK_PURPLE))
        sender.openInventory(gui)
        gui.setItem(1, ItemStack(Material.TNT, 1))

        return true
    }
}