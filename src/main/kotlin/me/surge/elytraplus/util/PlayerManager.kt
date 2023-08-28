package me.surge.elytraplus.util

import net.minecraft.world.entity.LivingEntity

object PlayerManager {

    fun getHoverCooldown(entity: LivingEntity): Int {
        if (!(entity as IEntityData).getPersistentData().contains("hoverCooldown")) {
            entity.getPersistentData().putInt("hoverCooldown", 0)
            return 0
        }

        return (entity as IEntityData).getPersistentData().getInt("hoverCooldown")
    }

    fun setHoverCooldown(entity: LivingEntity, hoverCooldown: Int) {
        if (!(entity as IEntityData).getPersistentData().contains("hoverCooldown")) {
            entity.getPersistentData().putInt("hoverCooldown", hoverCooldown)
        }

        (entity as IEntityData).getPersistentData().putInt("hoverCooldown", hoverCooldown)
    }

    fun isHovered(entity: LivingEntity): Boolean {
        if (!(entity as IEntityData).getPersistentData().contains("hovered")) {
            entity.getPersistentData().putBoolean("hovered", false)
            return false
        }

        return (entity as IEntityData).getPersistentData().getBoolean("hovered")
    }

    fun setHovered(player: IEntityData, hovered: Boolean): Boolean {
        val nbt = player.getPersistentData()

        nbt.putBoolean("hovered", hovered)

        return hovered
    }

}