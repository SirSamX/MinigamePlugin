package me.sirsam.minigameplugin.game

import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player

object Game {
    enum class State {
        INACTIVE,
        STARTING,
        RUNNING,
        DEATHMATCH,
        END;
    }

    var state = State.INACTIVE
    var teams = mutableMapOf<Player, Team>()

    fun getTeamByPlayer(player: Player): Team? {
        return teams[player]
    }

    fun getPlayersByTeam(targetTeam: Team): List<Player> {
        return teams.entries
            .filter { it.value == targetTeam }
            .map { it.key }
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