package me.surge.elytraplus.util

import me.surge.elytraplus.ElytraPlus.MOD_ID
import me.surge.elytraplus.duck.DIGui
import me.surge.elytraplus.enchantment.EPEnchantments
import me.surge.elytraplus.enchantment.HoverEnchantment
import me.surge.elytraplus.enchantment.ThermalAscensionEnchantment
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.EnchantmentHelper

object ElytraPlusRenderer {

    val HOVER = ResourceLocation(MOD_ID, "textures/hover.png")
    val HOVER_BACKGROUND = ResourceLocation(MOD_ID, "textures/hover_background.png")
    val HOVER_READY = ResourceLocation(MOD_ID, "textures/hover_ready.png")

    val THERMAL = ResourceLocation(MOD_ID, "textures/thermal.png")
    val THERMAL_BACKGROUND = ResourceLocation(MOD_ID, "textures/thermal_background.png")
    val THERMAL_READY = ResourceLocation(MOD_ID, "textures/thermal_ready.png")

    fun renderHUD(context: GuiGraphics) {
        val scaledWidth = (mc.gui as DIGui).scaledWidth
        val scaledHeight = (mc.gui as DIGui).scaledHeight

        var y = scaledHeight / 2 + 16

        if (EnchantmentHelper.getEnchantmentLevel(EPEnchantments.HOVER, mc.player) > 0) {
            context.setColor(1f, 1f, 1f, 0.5f)
            context.blit(HOVER_BACKGROUND, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)

            context.setColor(1f, 1f, 1f, 0.5f)

            context.enableScissor(scaledWidth / 2 + 16, y, (scaledWidth / 2 + 16 + (16 * (PlayerManager.getHoverCooldown(mc.player!!) / HoverEnchantment.calculateRequiredCooldown(mc.player!!).toFloat()))).toInt(), scaledHeight / 2 + 32)
            context.blit(HOVER, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
            context.disableScissor()

            if (HoverEnchantment.canHover(mc.player!!) && !PlayerManager.isHovered(mc.player!!)) {
                context.setColor(1f, 1f, 1f, 1f)
                context.blit(HOVER_READY, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
            }

            context.setColor(1f, 1f, 1f, 1f)
        }

        y += 16

        if (EnchantmentHelper.getEnchantmentLevel(EPEnchantments.THERMAL_ASCENSION, mc.player) > 0) {
            context.setColor(1f, 1f, 1f, 0.5f)
            context.blit(THERMAL_BACKGROUND, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)

            context.setColor(1f, 1f, 1f, 0.5f)

            context.enableScissor(scaledWidth / 2 + 16, y, (scaledWidth / 2 + 16 + (16 * (PlayerManager.getThermalAscensionCooldown(mc.player!!) / ThermalAscensionEnchantment.COOLDOWN_ORIGINAL.toFloat()))).toInt(), y + 32)
            context.blit(THERMAL, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
            context.disableScissor()

            if (ThermalAscensionEnchantment.canAscend(mc.player!!)) {
                context.setColor(1f, 1f, 1f, 1f)
                context.blit(THERMAL_READY, scaledWidth / 2 + 16, y, 0f, 0f, 16, 16, 16, 16)
            }

            context.setColor(1f, 1f, 1f, 1f)
        }
    }

}