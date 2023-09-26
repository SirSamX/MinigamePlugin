package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.helpers.Team
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class OnBreak : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val player = event.player

        if (block.type in listOf(Material.BLUE_BED, Material.GREEN_BED, Material.YELLOW_BED, Material.RED_BED)) {
            val team = Team.getTeamByBedType(block.type)

            Utils.broadcastSound(Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1f, 1f)
            Utils.broadcast(Component.text(team!!.teamName, team.chatColor).decorate(TextDecoration.BOLD).append(Component.text(" bed was broken by ${event.player.name}!", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
        }

        if (block.type !in listOf(Material.BLUE_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.YELLOW_WOOL) && player.gameMode != GameMode.CREATIVE) {
            event.isCancelled = true
        }
    }
}