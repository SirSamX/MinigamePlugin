package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.Main
import me.sirsam.minigameplugin.helpers.Game
import me.sirsam.minigameplugin.helpers.Teams
import me.sirsam.minigameplugin.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class Start : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { /*utils.notPlayerMessage(sender)*/; return true }
        if (Game.state != Game.Gamestate.INACTIVE) { return true }

        sender.sendMessage(Component.text("Game starts!"))
        Teams.values().forEach {
            if (sender.inventory.itemInMainHand.type != it.woolType) { return true }

            Game.state = Game.Gamestate.STARTING
            Countdown.runTaskTimer(Main.getPlugin(), 0, 20)
        }

        return true
    }

    private object Countdown: BukkitRunnable() {
        var i = 5

        override fun run() {
            if (i <= 0) {
                cancel()
            }
            Utilities.broadcast(Component.text("Game starts in $i...", NamedTextColor.YELLOW))

            i--
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf()
    }
}