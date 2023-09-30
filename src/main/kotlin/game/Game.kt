package me.sirsam.minigameplugin.game

import me.sirsam.minigameplugin.Main
import me.sirsam.minigameplugin.game.controllers.PlayerController
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

object Game {
    enum class State {
        INACTIVE,
        STARTING,
        RUNNING,
        DEATHMATCH; // TODO: add deathmatch
    }

    var state = State.INACTIVE
    var teams = mutableMapOf<UUID, Team>()

    fun getTeamByPlayer(player: Player): Team? {
        return teams[player.uniqueId]
    }

    fun getPlayersByTeam(targetTeam: Team): List<UUID> {
        return teams.entries
            .filter { it.value == targetTeam }
            .map { it.key }
    }

    fun start() {
        if (state != State.INACTIVE) return
        state = State.STARTING

        Utils.broadcast(Component.text("To enter a team hold the right wool color in you hand!", NamedTextColor.RED))

        object : BukkitRunnable() {
            var i = 5

            override fun run() {
                if (i <= 0) {
                    Utils.broadcast(Component.text("Game starts now!", NamedTextColor.YELLOW))
                    var foundMatch = false

                    for (player in Bukkit.getOnlinePlayers()) {
                        for (team in Team.values()) {
                            val item = player.inventory.itemInMainHand.type
                            if (item == team.woolType) {
                                teams[player.uniqueId] = team
                                Utils.sendMessage(player, Component.text("You were assigned to team ", NamedTextColor.YELLOW).append(team.getTeamComponeant()).append(Component.text("!", NamedTextColor.YELLOW)))
                                PlayerController(player).setup()
                                foundMatch = true
                                break
                            }
                        }
                        if (!foundMatch) {
                            Utils.sendMessage(player, Component.text("You were not assigned to a team!", NamedTextColor.YELLOW))
                            PlayerController(player).spectator()
                        }
                    }

                    state = State.RUNNING

                    cancel()
                } else {
                    Utils.broadcast(Component.text("Game starts in ${i}s!", NamedTextColor.YELLOW))
                }

                i--
            }
        }.runTaskTimer(Main.instance, 0, 20)
    }

    fun win(team: Team) {
        Utils.broadcast(
            Component.text(team.teamName, team.chatColor).decorate(TextDecoration.BOLD)
                .append(Component.text(" team won the game!", NamedTextColor.GOLD))
        )
        Utils.broadcastSound(Sound.ITEM_GOAT_HORN_SOUND_0, SoundCategory.MASTER, 1f, 1f)
        Utils.broadcastSound(Sound.ITEM_GOAT_HORN_SOUND_1, SoundCategory.MASTER, 1f, 1f)
    }
}