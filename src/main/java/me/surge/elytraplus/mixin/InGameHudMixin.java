package me.surge.elytraplus.mixin;

import me.surge.elytraplus.duck.IInGameHud;
import me.surge.elytraplus.util.ElytraPlusRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin implements IInGameHud {

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.BEFORE))
    public void hookRenderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        ElytraPlusRenderer.INSTANCE.renderHUD(context);
    }

    @Override
    public int getScaledWidth() {
        return this.scaledWidth;
    }

    @Override
    public int getScaledHeight() {
        return this.scaledHeight;
    }

}
