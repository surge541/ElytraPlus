package me.surge.elytraplus.util

import me.surge.elytraplus.ElytraPlus.MOD_ID
import me.surge.elytraplus.duck.DIGui
import me.surge.elytraplus.enchantment.EPEnchantments
import me.surge.elytraplus.enchantment.HoverEnchantment
import me.surge.elytraplus.enchantment.ThermalAscensionEnchantment
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper

object ElytraPlusRenderer {

    val HOVER = ResourceLocation(MOD_ID, "textures/hover.png")
    val THERMAL = ResourceLocation(MOD_ID, "textures/thermal.png")
    val ELYTRA = ResourceLocation("minecraft", "textures/item/elytra.png")

    fun renderHUD(context: GuiGraphics) {
        val scaledHeight = (mc.gui as DIGui).scaledHeight

        var y = scaledHeight / 2 + 16

        if (assertEnchantment(EPEnchantments.HOVER)) {
            drawEnchantment(
                context,
                y,
                HoverEnchantment.canHover(mc.player!!) && !PlayerManager.isHovered(mc.player!!),
                HOVER,
                PlayerManager.getHoverCooldown(mc.player!!) / HoverEnchantment.calculateRequiredCooldown(mc.player!!)
                    .toFloat()
            )

            y += 16
        }

        if (assertEnchantment(EPEnchantments.THERMAL_ASCENSION)) {
            drawEnchantment(
                context,
                y,
                ThermalAscensionEnchantment.canAscend(mc.player!!),
                THERMAL,
                PlayerManager.getThermalAscensionCooldown(mc.player!!) / ThermalAscensionEnchantment.COOLDOWN_ORIGINAL.toFloat()
            )
        }
    }

    private fun drawEnchantment(context: GuiGraphics, y: Int, ready: Boolean, icon: ResourceLocation, factor: Float) {
        val scaledWidth = (mc.gui as DIGui).scaledWidth

        context.setColor(0.2f, 0.2f, 0.2f, 0.5f)
        context.blit(ELYTRA, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)

        context.setColor(1f, 1f, 1f, if (ready) 1.0f else 0.5f)

        context.enableScissor(scaledWidth / 2 + 16, y, (scaledWidth / 2 + 16 + (16 * factor)).toInt(), y + 32)
        context.blit(ELYTRA, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
        context.blit(icon, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
        context.disableScissor()

        context.setColor(1f, 1f, 1f, 1f)
    }

    private fun assertEnchantment(enchantment: Enchantment): Boolean = EnchantmentHelper.getEnchantmentLevel(enchantment, mc.player) > 0

}