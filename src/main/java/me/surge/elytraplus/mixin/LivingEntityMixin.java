package me.surge.elytraplus.mixin;

import kotlin.jvm.functions.Function2;
import me.surge.elytraplus.ElytraPlus;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V", ordinal = 2))
    public void redirectTravel(LivingEntity instance, MoverType type, Vec3 vec3d) {
        for (Function2<LivingEntity, Vec3, Vec3> elytraSpeedModifier : ElytraPlus.INSTANCE.getElytraSpeedModifiers()) {
            vec3d = elytraSpeedModifier.invoke(instance, vec3d);
        }

        instance.move(type, vec3d);
    }

}
