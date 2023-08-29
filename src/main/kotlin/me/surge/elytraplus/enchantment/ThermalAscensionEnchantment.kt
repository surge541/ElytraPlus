package me.surge.elytraplus.enchantment

import me.surge.elytraplus.key.EPKeybinds
import me.surge.elytraplus.util.IEPEnchantment
import me.surge.elytraplus.util.IEntityData
import me.surge.elytraplus.util.PlayerManager
import me.surge.elytraplus.util.mc
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ElytraItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.phys.Vec3
import kotlin.math.abs

class ThermalAscensionEnchantment : Enchantment(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)), IEPEnchantment {

    override val translationName = "thermal_ascension"
    override fun getMaxLevel() = 1

    override fun canEnchant(stack: ItemStack) = stack.item is ElytraItem
    override fun isTreasureOnly() = true
    override fun isTradeable() = false

    companion object {

        // 30 Seconds (20 ticks/second, 600)
        const val COOLDOWN_ORIGINAL = 600

        fun updateAscensionStatus(player: Player) {
            if (canAscend(player) && EPKeybinds.THERMAL_ASCENSION.isDown) {
                var delta = player.deltaMovement

                delta = Vec3(delta.x, abs(delta.y).coerceAtLeast(0.0) + 1.5f, delta.z)

                player.deltaMovement = delta

                PlayerManager.setThermalAscensionCooldown(player, 0)
            } else {
                PlayerManager.setThermalAscensionCooldown(player, (PlayerManager.getThermalAscensionCooldown(player) + 1).coerceAtMost(COOLDOWN_ORIGINAL))
            }
        }

        fun canAscend(player: Player) = player.isAlive && EnchantmentHelper.getEnchantmentLevel(EPEnchantments.THERMAL_ASCENSION, player) > 0 && PlayerManager.getThermalAscensionCooldown(player) >= COOLDOWN_ORIGINAL

    }

}
