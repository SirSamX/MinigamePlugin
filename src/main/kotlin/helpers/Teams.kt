package me.sirsam.minigameplugin.helpers

import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material

enum class Teams(val chatColor: NamedTextColor, val woolType: Material, val bedType: Material, val terracottaType: Material, val glassType: Material) {
    YELLOW(NamedTextColor.YELLOW, Material.YELLOW_WOOL, Material.YELLOW_BED, Material.YELLOW_TERRACOTTA, Material.YELLOW_STAINED_GLASS),
    GREEN(NamedTextColor.GREEN, Material.GREEN_WOOL, Material.GREEN_BED, Material.GREEN_TERRACOTTA, Material.GREEN_STAINED_GLASS),
    BLUE(NamedTextColor.BLUE, Material.BLUE_WOOL, Material.BLUE_BED, Material.BLUE_TERRACOTTA, Material.BLUE_STAINED_GLASS),
    RED(NamedTextColor.RED, Material.RED_WOOL, Material.RED_BED, Material.RED_TERRACOTTA, Material.RED_STAINED_GLASS),
}