package me.sirsam.minigameplugin.game.controllers

import org.bukkit.*

class WorldController(private val world: World) {
    fun setup() {
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
        world.setGameRule(GameRule.DO_ENTITY_DROPS, false)
        world.setGameRule(GameRule.DO_FIRE_TICK, false)
        world.setGameRule(GameRule.DO_MOB_LOOT, false)
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false)
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false)
        world.setGameRule(GameRule.KEEP_INVENTORY, false)
        world.setGameRule(GameRule.MOB_GRIEFING, false)
        world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false)
        world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false)
        world.setGameRule(GameRule.DISABLE_RAIDS, true)
        world.setGameRule(GameRule.DO_INSOMNIA, false)
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
        world.setGameRule(GameRule.DO_PATROL_SPAWNING, false)
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false)
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false)
        world.setGameRule(GameRule.BLOCK_EXPLOSION_DROP_DECAY, false)
        world.setGameRule(GameRule.MOB_EXPLOSION_DROP_DECAY, false)
        world.setGameRule(GameRule.TNT_EXPLOSION_DROP_DECAY, false)
        world.setGameRule(GameRule.WATER_SOURCE_CONVERSION, false)
        world.setGameRule(GameRule.LAVA_SOURCE_CONVERSION, false)
        world.setGameRule(GameRule.GLOBAL_SOUND_EVENTS, false)
        world.setGameRule(GameRule.DO_VINES_SPREAD, false)

        world.difficulty = Difficulty.HARD
        world.isHardcore = true
        world.isAutoSave = false
    }

    fun reset() {
        Bukkit.unloadWorld(world, false)
        Bukkit.createWorld(WorldCreator(world.name))
    }
}