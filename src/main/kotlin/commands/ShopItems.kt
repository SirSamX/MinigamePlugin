package me.sirsam.minigameplugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class ShopItems : CommandExecutor, TabCompleter, InventoryHolder {
    class ShopItem(val slot: Int, val item: ItemStack, val paymentMethod: PaymentMethod, val priceAmount: Int)
    enum class PaymentMethod(val material: Material, val color: NamedTextColor, val priceName: String) {
        IRON(Material.IRON_INGOT, NamedTextColor.WHITE, "Iron"),
        GOLD(Material.GOLD_INGOT, NamedTextColor.GOLD, "Gold"),
        DIAMOND(Material.DIAMOND, NamedTextColor.AQUA, "Diamond"),
        EMERALD(Material.EMERALD, NamedTextColor.DARK_GREEN, "Emerald")
    }

    private val speedPotion = ItemStack(Material.POTION).apply {
        val potion = this.itemMeta as PotionMeta
        potion.addCustomEffect(PotionEffect(PotionEffectType.SPEED, 900, 2), true)
    }

    val items = listOf(
        ShopItem(19, ItemStack(Material.WHITE_WOOL, 16), PaymentMethod.IRON, 4),
        ShopItem(20, ItemStack(Material.STONE_SWORD), PaymentMethod.IRON, 10),
        ShopItem(21, ItemStack(Material.CHAINMAIL_BOOTS), PaymentMethod.IRON, 30),
        ShopItem(22, ItemStack(Material.WOODEN_PICKAXE), PaymentMethod.IRON, 10),
        ShopItem(23, ItemStack(Material.BOW), PaymentMethod.GOLD, 12),
        ShopItem(24, speedPotion, PaymentMethod.EMERALD, 1),
        ShopItem(25, ItemStack(Material.TNT), PaymentMethod.GOLD, 4),
        ShopItem(28, ItemStack(Material.OAK_PLANKS), PaymentMethod.GOLD, 4),
        ShopItem(29, ItemStack(Material.IRON_SWORD), PaymentMethod.GOLD, 7),
        ShopItem(30, ItemStack(Material.IRON_BOOTS), PaymentMethod.GOLD, 12),
    )


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true
        sender.openInventory(inventory)

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String>? {
        return null
    }

    override fun getInventory(): Inventory {
        val gui = Bukkit.createInventory(this, 45, Component.text("Shop", NamedTextColor.DARK_PURPLE))

        for (item in items) {
            val displayItem = ItemStack(item.item)
            val lore = mutableListOf<Component>()
            lore.add(Component.text("Cost: ", NamedTextColor.GRAY)
                .append(Component.text(item.priceAmount, item.paymentMethod.color)
                    .append(Component.text(" ").append(
                        Component.text(item.paymentMethod.priceName)
                    ))).decoration(TextDecoration.ITALIC, false))
            displayItem.lore(lore)
            gui.setItem(item.slot, displayItem)
        }

        return gui
    }
}