package me.sirsam.minigameplugin

import me.sirsam.minigameplugin.commands.GameCommand
import me.sirsam.minigameplugin.commands.LobbyCommand
import me.sirsam.minigameplugin.commands.Reload
import me.sirsam.minigameplugin.commands.Shop
import me.sirsam.minigameplugin.game.Generator
import me.sirsam.minigameplugin.game.controllers.WorldController
import me.sirsam.minigameplugin.game.map.MinigameWorld
import me.sirsam.minigameplugin.listeners.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.WorldCreator
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class Main : JavaPlugin() {
    companion object {
        lateinit var instance: Main
        lateinit var config: FileConfiguration
        lateinit var logger: Logger
    }

    override fun onEnable() {
        instance = this
        Main.config = config
        Main.logger = logger

        config.options().copyDefaults(true)
        saveDefaultConfig()

        Bukkit.createWorld(WorldCreator("bedwars"))

        val bedwarsWorld = MinigameWorld.BEDWARS.world
        Generator(Location(bedwarsWorld, 0.5, 66.0, -95.5), Generator.Type.BASE).start()
        Generator(Location(bedwarsWorld, 0.5, 66.0, 95.5), Generator.Type.BASE).start()
        Generator(Location(bedwarsWorld, -95.5, 66.0, 0.5), Generator.Type.BASE).start()
        Generator(Location(bedwarsWorld, 95.5, 66.0, 0.5), Generator.Type.BASE).start()

        server.clearRecipes()
        registerCommands()
        registerEvents()
        server.worlds.forEach {
            WorldController(it).apply {
                setup()
            }
        }

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        WorldController(MinigameWorld.BEDWARS.world).unload(false)
        logger.info("Plugin disabled!")
    }

    private fun registerCommands() {
        getCommand("rl")?.setExecutor(Reload())
        getCommand("shop")?.setExecutor(Shop())
        getCommand("game")?.setExecutor(GameCommand())
        getCommand("game")?.tabCompleter = GameCommand()
        getCommand("lobby")?.setExecutor(LobbyCommand())
        getCommand("lobby")?.tabCompleter = LobbyCommand()
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
        Bukkit.getPluginManager().registerEvents(DisabledEvents(), this)
        Bukkit.getPluginManager().registerEvents(OnDeath(), this)
        Bukkit.getPluginManager().registerEvents(OnBlockBreak(), this)
        Bukkit.getPluginManager().registerEvents(OnMove(), this)
        Bukkit.getPluginManager().registerEvents(OnInventoryClick(), this)
        Bukkit.getPluginManager().registerEvents(OnInteract(), this)
        Bukkit.getPluginManager().registerEvents(OnProjectileHit(), this)
        Bukkit.getPluginManager().registerEvents(OnBlockPlace(), this)
    }
}