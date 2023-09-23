package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.Main
import me.sirsam.minigameplugin.helpers.Game
import me.sirsam.minigameplugin.helpers.Teams
import me.sirsam.minigameplugin.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class Start : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { /*utils.notPlayerMessage(sender)*/; return true }
        if (Game.state != Game.State.INACTIVE) { return true }

        Game.state = Game.State.STARTING
        Countdown().runTaskTimer(Main.instance, 20, 20)

        return true
    }

    private class Countdown : BukkitRunnable() {
        var i = 5

        override fun run() {
            if (i <= 0) {
                Utilities.broadcast(Component.text("To enter a team hold the right wool color in you hand!", NamedTextColor.RED))
                for (team in Teams.values()) {
                    for (player in Bukkit.getOnlinePlayers()) {
                        val item = player.inventory.itemInMainHand.type
                        if (item == team.woolType) {
                            Game.teams[player] = team
                            Utilities.sendMessage(player, Component.text("You were assigned to team ", NamedTextColor.YELLOW).append(team.getTeamComponeant()).append(Component.text("!", NamedTextColor.YELLOW)))
                            player.inventory.clear()
                        } else {
                            Utilities.sendMessage(player, Component.text("You were not assigned to a team!", NamedTextColor.YELLOW))
                        }
                    }
                }
                Utilities.broadcast(Component.text("Game starts now!", NamedTextColor.YELLOW))

                cancel()
            } else {
                Utilities.broadcast(Component.text("Game starts in ${i}s!", NamedTextColor.YELLOW))
            }

            i--
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf()
    }
}