package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.Shop
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType.SlotType
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class OnInventoryClick : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val items = Shop().items
        val player = event.whoClicked as Player
        val inventory = player.inventory
        val inv = event.clickedInventory
        val slot = event.slot

        fun buyArmour(name: String, color: NamedTextColor, paymentMaterial: Material, paymentAmount: Int) {
            Utils.sendMessage(player, Component.text("You received ", NamedTextColor.GREEN).append(Component.text(name + " Armour", color).append(Component.text("!", NamedTextColor.GREEN))))
            Utils.playSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, .5f, 1f)
            inventory.removeItem(ItemStack(paymentMaterial, paymentAmount))
        }

        fun buy() {
            event.isCancelled = true

            for (i in items) {
                if (i.slot == slot) {
                    val paymentMaterial = i.paymentMethod.material
                    val paymentAmount = i.priceAmount

                    when (i.item.type) {
                        Material.CHAINMAIL_BOOTS -> {
                            player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.CHAINMAIL_BOOTS))
                            player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.CHAINMAIL_LEGGINGS))
                            buyArmour("Chainmail", NamedTextColor.GRAY, paymentMaterial, paymentAmount)
                            return
                        }

                        Material.IRON_BOOTS -> {
                            player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.IRON_BOOTS))
                            player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.IRON_LEGGINGS))
                            buyArmour("Iron", NamedTextColor.WHITE, paymentMaterial, paymentAmount)
                            return
                        }

                        Material.DIAMOND_BOOTS -> {
                            player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.DIAMOND_BOOTS))
                            player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.DIAMOND_LEGGINGS))
                            buyArmour("Diamond", NamedTextColor.AQUA, paymentMaterial, paymentAmount)
                            return
                        }

                        Material.NETHERITE_BOOTS -> {
                            player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.NETHERITE_BOOTS))
                            player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.NETHERITE_LEGGINGS))
                            buyArmour("Netherite", NamedTextColor.DARK_GRAY, paymentMaterial, paymentAmount)
                            return
                        }
                        else -> {}
                    }

                    if (inventory.containsAtLeast(ItemStack(paymentMaterial), paymentAmount)) {
                        inventory.removeItem(ItemStack(paymentMaterial, paymentAmount))
                        inventory.addItem(i.item)
                        Utils.broadcastSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, .5f, 1f)
                        Utils.sendMessage(player, Component.text("You received ", NamedTextColor.GREEN).append(i.item.displayName().append(Component.text("!", NamedTextColor.GREEN))))
                    }
                }
            }
        }

        if (event.slotType == SlotType.ARMOR) {
            event.isCancelled = true
        }

        when (inv?.holder) {
            is Shop -> {
                buy()
            }
        }
    }
}