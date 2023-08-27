package me.surge.elytraplus

import me.surge.elytraplus.enchantment.EPEnchantments
import me.surge.elytraplus.enchantment.HoverEnchantment
import me.surge.elytraplus.key.EPKeybinds
import me.surge.elytraplus.util.mc
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.world.ClientWorld
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier

object ElytraPlus : ModInitializer {

    const val MOD_ID = "elytraplus"

    /**
     * Runs the mod initializer.
     */
    override fun onInitialize() {
        Registry.register(Registries.ENCHANTMENT, Identifier(MOD_ID, "hover"), EPEnchantments.HOVER)
    }

}
