package me.surge.elytraplus.mixin;

import me.surge.elytraplus.util.IEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.surge.elytraplus.ElytraPlus.MOD_ID;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityData {

    @Shadow
    public abstract Vec3 getDeltaMovement();

    @Shadow
    public abstract void setDeltaMovement(Vec3 velocity);

    private CompoundTag persistentData;

    @NotNull
    @Override
    public CompoundTag getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new CompoundTag();
        }

        return persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    protected void injectWriteMethod(CompoundTag nbt, CallbackInfoReturnable<CompoundTag> info) {
        if (persistentData != null) {
            nbt.put(MOD_ID, persistentData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    protected void injectReadMethod(CompoundTag nbt, CallbackInfo info) {
        if (nbt.contains(MOD_ID, 10)) {
            persistentData = nbt.getCompound(MOD_ID);
        }
    }

}
