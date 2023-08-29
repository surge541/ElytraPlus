package me.surge.elytraplus.enchantment

import me.surge.elytraplus.util.IEPEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ElytraItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.EnchantmentHelper

class WindRiderEnchantment : Enchantment(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, arrayOf(EquipmentSlot.CHEST)), IEPEnchantment {

    override val translationName = "wind_rider"
    override fun getMaxLevel() = 3
    override fun canEnchant(stack: ItemStack) = stack.item is ElytraItem
    override fun isTreasureOnly() = true
    override fun isTradeable() = false

    companion object {
        fun calculateSpeedMultiplier(entity: LivingEntity): Float {
            return 1f + when (EnchantmentHelper.getEnchantmentLevel(EPEnchantments.WIND_RIDER, entity)) {
                1 -> 0.1f
                2 -> 0.3f
                3 -> 0.5f
                else -> 0f
            }
        }
    }

}