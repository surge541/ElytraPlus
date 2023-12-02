package me.surge.elytraplus

import dev.emi.trinkets.api.TrinketsApi
import me.surge.elytraplus.enchantment.EPEnchantments
import me.surge.elytraplus.enchantment.WindRiderEnchantment
import me.surge.elytraplus.util.ElytraTrailHandler
import me.surge.elytraplus.util.PlayerManager.isHovered
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.util.TriState
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.phys.Vec3
import org.apache.logging.log4j.LogManager

object ElytraPlus : ModInitializer {

    const val MOD_ID = "elytraplus"
    val LOGGER = LogManager.getLogger(MOD_ID)

    val elytraSpeedModifiers: Array<(LivingEntity, Vec3) -> Vec3> = arrayOf(
        { entity, vec3d ->
            return@arrayOf if (isHovered(entity)) {
                vec3d.multiply(0.1, 0.1, 0.1)
            } else {
                vec3d
            }
        },

        { entity, vec3d ->
            return@arrayOf if (EnchantmentHelper.getEnchantmentLevel(EPEnchantments.WIND_RIDER, entity) > 0) {
                val factor = WindRiderEnchantment.calculateSpeedMultiplier(entity).toDouble()
                vec3d.multiply(factor, factor, factor)
            } else {
                vec3d
            }
        }
    )

    /**
     * Runs the mod initializer.
     */
    override fun onInitialize() {
        ServerTickEvents.START_WORLD_TICK.register { level ->
            level.players().forEach { ElytraTrailHandler.spawn(it, level) }
        }

        EPEnchantments.EP_ENCHANTMENTS.forEach {
            Registry.register(BuiltInRegistries.ENCHANTMENT, ResourceLocation(MOD_ID, it.translationName), it as Enchantment)
        }

        TrinketsApi.registerTrinketPredicate(ResourceLocation(MOD_ID, "elytra_trail_predicate")) { stack, slot, entity ->
            if (stack.item in ElytraTrailHandler.trailItems) {
                TriState.TRUE
            } else {
                TriState.FALSE
            }
        }
    }

}
