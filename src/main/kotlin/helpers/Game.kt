package me.sirsam.minigameplugin.helpers

object Game {
    enum class Gamestate {
        INACTIVE,
        WAITING,
        STARTING,
        WARMUP,
        RUNNING,
        DEATHMATCH,
        END;
    }

    var state = Gamestate.INACTIVE
}