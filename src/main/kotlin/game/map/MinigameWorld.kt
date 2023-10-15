package me.sirsam.minigameplugin.game.map

import org.bukkit.Bukkit
import org.bukkit.World

enum class MinigameWorld(val world: World) {
    LOBBY(Bukkit.getWorld("lobby")!!),
    BEDWARS(Bukkit.getWorld("bedwars")!!);
}