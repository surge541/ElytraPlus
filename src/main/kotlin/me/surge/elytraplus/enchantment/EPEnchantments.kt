package me.surge.elytraplus.enchantment

import me.surge.elytraplus.util.IEPEnchantment

object EPEnchantments {

    @JvmField
    val HOVER = HoverEnchantment()

    @JvmField
    val WIND_RIDER = WindRiderEnchantment()

    val EP_ENCHANTMENTS: Array<IEPEnchantment> = arrayOf(HOVER, WIND_RIDER)

}
