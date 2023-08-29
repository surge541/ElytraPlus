package me.surge.elytraplus.util

import dev.emi.trinkets.api.TrinketsApi
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import org.joml.Vector3f
import kotlin.random.Random

object ElytraTrailHandler {

    val trailItems = listOf(
        Items.REDSTONE,
        Items.GLOWSTONE_DUST,
        Items.SLIME_BALL,
        Items.TORCH,
        Items.SOUL_TORCH,
        Items.DRAGON_BREATH,
        Items.INK_SAC
    )

    private val glowStoneColours = listOf(
        Vector3f(198 / 255f, 172 / 255f, 92 / 255f),
        Vector3f(161 / 255f, 106 / 255f, 66 / 255f),
        Vector3f(108 / 255f, 82 / 255f, 45 / 255f),
        Vector3f(202 / 255f, 190 / 255f, 172 / 255f),
        Vector3f(114 / 255f, 77 / 255f, 38 / 255f)
    )

    fun spawn(player: Player, world: Level) {
        if (!TrinketsApi.getTrinketComponent(player).isEmpty) {
            val inventory = TrinketsApi.getTrinketComponent(player).get().inventory

            if (inventory["chest"]?.containsKey("trail") == true) {
                val trinket = inventory["chest"]!!["trail"] ?: return

                if (world is ServerLevel && player.isFallFlying) {
                    val particle = when (trinket.getItem(0).item) {
                        Items.REDSTONE -> DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, 2f)
                        Items.GLOWSTONE_DUST -> DustParticleOptions(glowStoneColours[Random.nextInt(glowStoneColours.size)], 2f)
                        Items.SLIME_BALL -> ParticleTypes.ITEM_SLIME
                        Items.TORCH -> ParticleTypes.FLAME
                        Items.SOUL_TORCH -> ParticleTypes.SOUL_FIRE_FLAME
                        Items.DRAGON_BREATH -> ParticleTypes.DRAGON_BREATH
                        Items.INK_SAC -> ParticleTypes.SQUID_INK

                        else -> null
                    }

                    if (particle != null) {
                        world.sendParticles(particle, player.x, player.y, player.z, 1, 0.0, 0.0, 0.0, 0.0)
                    }
                }
            }
        }
    }

}