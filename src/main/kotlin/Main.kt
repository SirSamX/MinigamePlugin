package me.sirsam.minigameplugin

import me.sirsam.minigameplugin.commands.Start
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        private lateinit var instance: Main

        fun getPlugin(): Main {
            return instance
        }
    }

    override fun onEnable() {
        registerEvents()
        registerCommands()

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        logger.info("Plugin disabled!")
    }

    private fun registerCommands() {
        getCommand("start")?.setExecutor(Start())
    }

    private fun registerEvents() {
        //Bukkit.getPluginManager().registerEvents(OnInventoryClick(), this)
    }
}