package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class Reload : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        Utils.broadcast(Component.text("RELOADING!", NamedTextColor.RED).decorate(TextDecoration.BOLD))
        sender.server.reload()
        Utils.broadcast(Component.text("RELOAD COMPLETE!", NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
     return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf()
    }
}