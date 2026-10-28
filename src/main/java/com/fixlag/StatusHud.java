package com.fixlag;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

/** Dòng chữ nhỏ góc trái trên: mức FixLag đang bật + FPS hiện tại. */
public final class StatusHud {
    private StatusHud() {}

    public static void register() {
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath(FixLagClient.MOD_ID, "status"),
                StatusHud::render);
    }

    private static void render(GuiGraphicsExtractor g, DeltaTracker deltaTracker) {
        LagLevel level = FixLagManager.level();
        Minecraft mc = Minecraft.getInstance();
        if (level == LagLevel.OFF || mc.options.hideGui) {
            return;
        }
        String text = "FixLag: " + level.label() + "  |  FPS: " + mc.getFps();
        int w = mc.font.width(text);
        g.fill(2, 2, w + 8, 14, 0x90000000);
        g.text(mc.font, text, 5, 4, 0xFF55FF55, true);
    }
}
