package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.helpers.Game
import me.sirsam.minigameplugin.helpers.Team
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShopItems : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true
        Game.win(Team.RED) //TEMPORARY JUST TO TEST SRY MORITZ

        val gui = Bukkit.createInventory(null, 45, Component.text("Shop", NamedTextColor.DARK_PURPLE))
        sender.openInventory(gui)
        gui.setItem(0, ItemStack(Material.TNT, 1))

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf()
    }
}