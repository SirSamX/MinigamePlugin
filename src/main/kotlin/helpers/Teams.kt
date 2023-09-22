package me.sirsam.minigameplugin.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class Teams(val teamName: String, val chatColor: NamedTextColor, val woolType: Material, val bedType: Material, val terracottaType: Material, val glassType: Material) {
    YELLOW("Yellow", NamedTextColor.YELLOW, Material.YELLOW_WOOL, Material.YELLOW_BED, Material.YELLOW_TERRACOTTA, Material.YELLOW_STAINED_GLASS),
    GREEN("Green", NamedTextColor.GREEN, Material.GREEN_WOOL, Material.GREEN_BED, Material.GREEN_TERRACOTTA, Material.GREEN_STAINED_GLASS),
    BLUE("Blue", NamedTextColor.BLUE, Material.BLUE_WOOL, Material.BLUE_BED, Material.BLUE_TERRACOTTA, Material.BLUE_STAINED_GLASS),
    RED("Red", NamedTextColor.RED, Material.RED_WOOL, Material.RED_BED, Material.RED_TERRACOTTA, Material.RED_STAINED_GLASS);

    companion object {
        fun getTeamByWoolType(woolType: Material): Teams? {
            for (team in Teams.values()) {
                if (team.woolType == woolType) {
                    return team
                }
            }
            return null
        }
    }

    fun getTeamComponent(): Component {
        return Component.text(teamName, chatColor)
    }
}