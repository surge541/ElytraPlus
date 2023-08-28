package me.surge.elytraplus.util

import me.surge.elytraplus.ElytraPlus.MOD_ID
import me.surge.elytraplus.duck.IInGameHud
import me.surge.elytraplus.enchantment.EPEnchantments
import me.surge.elytraplus.enchantment.HoverEnchantment
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.EnchantmentHelper

object ElytraPlusRenderer {

    val HOVER = ResourceLocation(MOD_ID, "textures/hover.png")
    val HOVER_BACKGROUND = ResourceLocation(MOD_ID, "textures/hover_background.png")
    val HOVER_READY = ResourceLocation(MOD_ID, "textures/hover_ready.png")

    fun renderHUD(context: GuiGraphics) {
        val scaledWidth = (mc.inGameHud as IInGameHud).scaledWidth
        val scaledHeight = (mc.inGameHud as IInGameHud).scaledHeight

        if (EnchantmentHelper.getEnchantmentLevel(EPEnchantments.HOVER, mc.player) > 0) {
            context.setColor(1f, 1f, 1f, 0.5f)
            context.blit(HOVER_BACKGROUND, scaledWidth / 2 + 16, scaledHeight / 2 + 16, 0f, 0f, 16, 16, 16, 16)

            context.setColor(1f, 1f, 1f, 0.5f)

            context.enableScissor(scaledWidth / 2 + 16, scaledHeight / 2 + 16, (scaledWidth / 2 + 16 + (16 * (PlayerManager.getHoverCooldown(mc.player!!) / HoverEnchantment.calculateRequiredCooldown(mc.player!!).toFloat()))).toInt(), scaledHeight / 2 + 32)
            context.blit(HOVER, scaledWidth / 2 + 16, scaledHeight / 2 + 16, 0f, 0f, 16, 16, 16, 16)
            context.disableScissor()

            if (HoverEnchantment.canHover(mc.player!!) && !PlayerManager.isHovered(mc.player!!)) {
                context.setColor(1f, 1f, 1f, 1f)
                context.blit(HOVER_READY, scaledWidth / 2 + 16, scaledHeight / 2 + 16, 0f, 0f, 16, 16, 16, 16)
            }

            context.setColor(1f, 1f, 1f, 1f)
        }
    }

}