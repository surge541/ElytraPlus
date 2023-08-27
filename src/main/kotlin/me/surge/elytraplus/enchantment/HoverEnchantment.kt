package me.surge.elytraplus.enchantment

import me.surge.elytraplus.key.EPKeybinds
import me.surge.elytraplus.util.IEntityData
import me.surge.elytraplus.util.PlayerManager
import me.surge.elytraplus.util.mc
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ElytraItem
import net.minecraft.item.ItemStack

class HoverEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)) {

    override fun getMaxLevel() = 3

    override fun isAcceptableItem(stack: ItemStack) = stack.item is ElytraItem

    companion object {

        // 10 Seconds (20 ticks/second, 200)
        private const val COOLDOWN_ORIGINAL = 200

        fun updateHoverStatus(player: PlayerEntity) {
            PlayerManager.setHovered(player as IEntityData, canHover(player) && EPKeybinds.HOVER.isPressed)

            if (PlayerManager.isHovered(player)) {
                PlayerManager.setHoverCooldown(player, 0)
            } else {
                PlayerManager.setHoverCooldown(player, (PlayerManager.getHoverCooldown(player) + 1).coerceAtMost(COOLDOWN_ORIGINAL))
            }

            //PlayerManager.setHoverCooldown(player, if (canHover) (220 - (50 * EnchantmentHelper.getEquipmentLevel(EPEnchantments.HOVER, player))) else 0.coerceAtLeast(PlayerManager.getHoverCooldown(player) - 1))
        }

        fun calculateRequiredCooldown(player: PlayerEntity): Int {
            // remove 2.5 seconds per enchantment level
            return COOLDOWN_ORIGINAL - (50 * EnchantmentHelper.getEquipmentLevel(EPEnchantments.HOVER, player))
        }

        fun canHover(player: PlayerEntity) = player.isAlive && player.isFallFlying && EnchantmentHelper.getEquipmentLevel(EPEnchantments.HOVER, player) > 0 && (PlayerManager.getHoverCooldown(player) >= calculateRequiredCooldown(player) || PlayerManager.isHovered(player))

    }

}
