package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.game.Team
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class OnBlockBreak : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val material = block.type
        val player = event.player

        if (!Utils.isWoolOrBed(material) && player.gameMode != GameMode.CREATIVE) {
            event.isCancelled = true
            return
        }

        if (Utils.isBed(material)) {
            val team = Team.getTeamByBedType(block.type)
            if (team != null) {
                Game.getPlayersByTeam(team).forEach {
                    Bukkit.getPlayer(it)?.showTitle(Title.title(Component.text("Bed gone sucker!", NamedTextColor.RED), Component.text("")))
                }
            }

            event.isDropItems = false
            Utils.broadcastSound(Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER, 1f, 1f)
            Utils.broadcast(Component.text(team!!.teamName, team.chatColor).decorate(TextDecoration.BOLD).append(Component.text(" bed was broken by ${event.player.name}!", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)))
        }
    }
}