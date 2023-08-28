package me.surge.elytraplus.util

import net.minecraft.nbt.CompoundTag

interface IEntityData {

    fun getPersistentData(): CompoundTag

}