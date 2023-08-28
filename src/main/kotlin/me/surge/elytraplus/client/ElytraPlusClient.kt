package me.surge.elytraplus.client

import me.surge.elytraplus.enchantment.HoverEnchantment
import me.surge.elytraplus.key.EPKeybinds
import me.surge.elytraplus.util.mc
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper

object ElytraPlusClient : ClientModInitializer {

    override fun onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(EPKeybinds.HOVER)

        ClientTickEvents.START_WORLD_TICK.register {
            HoverEnchantment.updateHoverStatus(mc.player!!)
        }
    }

}