package me.surge.elytraplus.util

import net.minecraft.nbt.NbtCompound

interface IEntityData {

    fun getPersistentData(): NbtCompound

}