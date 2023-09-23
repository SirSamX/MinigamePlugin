package me.sirsam.minigameplugin

import me.sirsam.minigameplugin.commands.Reload
import me.sirsam.minigameplugin.commands.ShopItems
import me.sirsam.minigameplugin.commands.Start
import me.sirsam.minigameplugin.listeners.OnJoin
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        lateinit var instance: Main
    }

    override fun onEnable() {
        instance = this

        registerEvents()
        registerCommands()

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        logger.info("Plugin disabled!")
    }

    private fun registerCommands() {
        getCommand("start")?.setExecutor(Start())
        getCommand("rl")?.setExecutor(Reload())
        getCommand("shop")?.setExecutor(ShopItems())
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
    }
}