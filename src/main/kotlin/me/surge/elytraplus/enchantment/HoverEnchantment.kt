package me.surge.elytraplus.enchantment

import me.surge.elytraplus.key.EPKeybinds
import me.surge.elytraplus.util.IEPEnchantment
import me.surge.elytraplus.util.IEntityData
import me.surge.elytraplus.util.PlayerManager
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ElytraItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.EnchantmentHelper

class HoverEnchantment : Enchantment(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)), IEPEnchantment {

    override val translationName = "hover"
    override fun getMaxLevel() = 3

    override fun canEnchant(stack: ItemStack) = stack.item is ElytraItem

    companion object {

        // 10 Seconds (20 ticks/second, 200)
        private const val COOLDOWN_ORIGINAL = 200

        fun updateHoverStatus(player: Player) {
            PlayerManager.setHovered(player as IEntityData, canHover(player) && EPKeybinds.HOVER.isDown)

            if (PlayerManager.isHovered(player)) {
                PlayerManager.setHoverCooldown(player, 0)
            } else {
                PlayerManager.setHoverCooldown(player, (PlayerManager.getHoverCooldown(player) + 1).coerceAtMost(COOLDOWN_ORIGINAL))
            }
        }

        fun calculateRequiredCooldown(player: Player): Int {
            // remove 2.5 seconds per enchantment level
            return COOLDOWN_ORIGINAL - (50 * EnchantmentHelper.getEnchantmentLevel(EPEnchantments.HOVER, player))
        }

        fun canHover(player: Player) = player.isAlive && player.isFallFlying && EnchantmentHelper.getEnchantmentLevel(EPEnchantments.HOVER, player) > 0 && (PlayerManager.getHoverCooldown(player) >= calculateRequiredCooldown(player) || PlayerManager.isHovered(player))

    }

}
