package me.sirsam.minigameplugin.commands

import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.game.Team
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Shop : CommandExecutor, TabCompleter, InventoryHolder {
    private lateinit var player: Player

    class ShopItem(val slot: Int, val item: ItemStack, val paymentMethod: PaymentMethod, val priceAmount: Int)

    enum class PaymentMethod(val material: Material, val color: NamedTextColor, val priceName: String) {
        IRON(Material.IRON_INGOT, NamedTextColor.WHITE, "Iron"),
        GOLD(Material.GOLD_INGOT, NamedTextColor.GOLD, "Gold"),
        DIAMOND(Material.DIAMOND, NamedTextColor.AQUA, "Diamond"),
        EMERALD(Material.EMERALD, NamedTextColor.DARK_GREEN, "Emerald"),
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return true
        player = sender
        sender.openInventory(inventory)

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf()
    }

    fun getItems(team: Team?): List<ShopItem> {
        val speedPotion = ItemStack(Material.POTION).apply {
            val potionMeta = this.itemMeta as PotionMeta
            potionMeta.addCustomEffect(PotionEffect(PotionEffectType.SPEED, 900, 1), true)
            this.itemMeta = potionMeta
        }

        val invisibilityPotion = ItemStack(Material.POTION).apply {
            val potionMeta = this.itemMeta as PotionMeta
            potionMeta.addCustomEffect(PotionEffect(PotionEffectType.INVISIBILITY, 600, 0), true)
            this.itemMeta = potionMeta
        }

        val jumpPotion = ItemStack(Material.POTION).apply {
            val potionMeta = this.itemMeta as PotionMeta
            potionMeta.addCustomEffect(PotionEffect(PotionEffectType.JUMP, 900, 4), true)
            this.itemMeta = potionMeta
        }

        val woodenAxe = ItemStack(Material.WOODEN_AXE).apply {
            val axeMeta = this.itemMeta
            axeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true)
            this.itemMeta = axeMeta
        }

        return listOf(
            ShopItem(10, ItemStack(team?.woolType ?: Material.WHITE_WOOL, 16), PaymentMethod.IRON, 4),
            ShopItem(11, ItemStack(Material.STONE_SWORD), PaymentMethod.IRON, 10),
            ShopItem(12, ItemStack(Material.CHAINMAIL_BOOTS), PaymentMethod.IRON, 30),
            ShopItem(13, ItemStack(Material.WOODEN_PICKAXE), PaymentMethod.IRON, 10),
            ShopItem(14, ItemStack(Material.BOW), PaymentMethod.GOLD, 12),
            ShopItem(15, speedPotion, PaymentMethod.EMERALD, 1),
            ShopItem(16, ItemStack(Material.TNT), PaymentMethod.GOLD, 4),
            ShopItem(19, ItemStack(Material.OAK_PLANKS), PaymentMethod.GOLD, 4),
            ShopItem(20, ItemStack(Material.IRON_SWORD), PaymentMethod.GOLD, 7),
            ShopItem(21, ItemStack(Material.IRON_BOOTS), PaymentMethod.GOLD, 12),
            ShopItem(22, ItemStack(Material.SHEARS), PaymentMethod.IRON, 20),
            ShopItem(23, ItemStack(Material.ARROW), PaymentMethod.GOLD, 2),
            ShopItem(24, invisibilityPotion, PaymentMethod.EMERALD, 2),
            ShopItem(25, ItemStack(Material.WATER_BUCKET), PaymentMethod.GOLD, 3),
            ShopItem(28, ItemStack(Material.GOLDEN_APPLE), PaymentMethod.GOLD, 3),
            ShopItem(29, jumpPotion, PaymentMethod.EMERALD, 1),
            ShopItem(30, ItemStack(Material.GLASS), PaymentMethod.IRON, 12),
            ShopItem(31, ItemStack(Material.END_STONE), PaymentMethod.IRON, 24),
            ShopItem(32, woodenAxe, PaymentMethod.IRON, 10),
            ShopItem(33, ItemStack(Material.BOW), PaymentMethod.GOLD, 20),
            ShopItem(34, ItemStack(Material.FIRE_CHARGE), PaymentMethod.IRON, 40),
        )
    }

    override fun getInventory(): Inventory {
        val gui = Bukkit.createInventory(this, 45, Component.text("Shop", NamedTextColor.DARK_PURPLE))

        for (item in getItems(Game.getTeamByPlayer(player))) {
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