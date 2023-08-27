package me.surge.elytraplus.mixin;

import me.surge.elytraplus.util.PlayerManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", ordinal = 2))
    public void redirectTravel(LivingEntity instance, MovementType movementType, Vec3d vec3d) {
        if (PlayerManager.INSTANCE.isHovered(instance)) {
            vec3d = vec3d.multiply(0.1, 0.1, 0.1);
        }

        instance.move(movementType, vec3d);
    }

}
