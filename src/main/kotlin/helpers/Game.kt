package me.sirsam.minigameplugin.helpers

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
    var teams = mutableMapOf<Player, Teams>()
}