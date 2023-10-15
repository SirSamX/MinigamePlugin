package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.game.controllers.PlayerController
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LobbyCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true
        PlayerController(sender).gotoLobby()
        return true
    }
}