package me.surge.elytraplus.mixin;

import me.surge.elytraplus.duck.DIGui;
import me.surge.elytraplus.util.ElytraPlusRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin implements DIGui {

    @Shadow private int screenWidth;

    @Shadow private int screenHeight;

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.BEFORE))
    public void hookRenderHotbar(float tickDelta, GuiGraphics context, CallbackInfo ci) {
        ElytraPlusRenderer.INSTANCE.renderHUD(context);
    }

    @Override
    public int getScaledWidth() {
        return this.screenWidth;
    }

    @Override
    public int getScaledHeight() {
        return this.screenHeight;
    }

}
