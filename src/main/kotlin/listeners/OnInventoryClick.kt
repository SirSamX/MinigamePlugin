package me.sirsam.minigameplugin.listeners

import me.sirsam.minigameplugin.commands.Shop
import me.sirsam.minigameplugin.game.Game
import me.sirsam.minigameplugin.helpers.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.enchantments.Enchantment
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
        val player = event.whoClicked as Player
        val inventory = player.inventory
        val inv = event.clickedInventory
        val slot = event.slot
        val items = Shop().getItems(Game.getTeamByPlayer(player))

        fun buyArmour(name: String, color: NamedTextColor) {
            Utils.sendMessage(player, Component.text("You received ", NamedTextColor.GREEN).append(Component.text("$name Armour", color).append(Component.text("!", NamedTextColor.GREEN))))
        }

        fun buy() {
            event.isCancelled = true

            for (i in items) {
                if (i.slot == slot) {
                    val paymentMaterial = i.paymentMethod.material
                    val paymentAmount = i.priceAmount

                    if (inventory.containsAtLeast(ItemStack(paymentMaterial), paymentAmount)) {
                        inventory.removeItem(ItemStack(paymentMaterial, paymentAmount))

                        when (i.item.type) {
                            Material.CHAINMAIL_BOOTS -> {
                                player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.CHAINMAIL_BOOTS).apply {
                                    this.itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 1, false)
                                })
                                player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.CHAINMAIL_LEGGINGS))
                                buyArmour("Chainmail", NamedTextColor.GRAY)
                                return
                            }

                            Material.IRON_BOOTS -> {
                                player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.IRON_BOOTS).apply {
                                    this.itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 1, false)
                                })
                                player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.IRON_LEGGINGS))
                                buyArmour("Iron", NamedTextColor.WHITE)
                                return
                            }

                            Material.DIAMOND_BOOTS -> {
                                player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.DIAMOND_BOOTS).apply {
                                    this.itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 1, false)
                                })
                                player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.DIAMOND_LEGGINGS))
                                buyArmour("Diamond", NamedTextColor.AQUA)
                                return
                            }

                            Material.NETHERITE_BOOTS -> {
                                player.inventory.setItem(EquipmentSlot.FEET, ItemStack(Material.NETHERITE_BOOTS).apply {
                                    this.itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 1, false)
                                })
                                player.inventory.setItem(EquipmentSlot.LEGS, ItemStack(Material.NETHERITE_LEGGINGS))
                                buyArmour("Netherite", NamedTextColor.DARK_GRAY)
                                return
                            }

                            else -> {
                                inventory.addItem(i.item)
                                Utils.sendMessage(player, Component.text("You received ", NamedTextColor.GREEN).append(i.item.displayName().append(Component.text("!", NamedTextColor.GREEN))))
                            }
                        }

                        Utils.broadcastSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, .5f, 1f)
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