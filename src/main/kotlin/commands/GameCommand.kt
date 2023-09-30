package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.game.Team
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class GameCommand : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (args.isNullOrEmpty()) return true

        when (args[0].lowercase()) {
            "start" -> {
                Game.start()
            }

            "stop" -> {
                Game.win(Team.RED)
            }

            "team" -> {

            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        return mutableListOf("start", "stop", "team")
    }
}