package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.Main
import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.game.Team
import me.sirsam.minigameplugin.game.controllers.PlayerController
import me.sirsam.minigameplugin.game.controllers.WorldController
import me.sirsam.minigameplugin.game.map.MinigameWorld
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

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

            "reset" -> {
                Utils.broadcast(Component.text("Resetting world!", NamedTextColor.YELLOW))
                MinigameWorld.BEDWARS.world.players.forEach {
                    PlayerController(it).gotoLobby()
                }
                WorldController(MinigameWorld.BEDWARS.world).reset()
                object : BukkitRunnable() {
                    override fun run() {
                        MinigameWorld.LOBBY.world.players.forEach {
                            PlayerController(it).gotoSpawn(true)
                        }
                        Utils.broadcast(Component.text("Reset complete!", NamedTextColor.YELLOW))
                    }
                }.runTaskLater(Main.instance, 20L)
            }

            "warp" -> {
                if (sender !is Player) return true
                PlayerController(sender).gotoSpawn()
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        return mutableListOf("start", "stop", /*"team", */"reset", "warp")
    }
}