package com.fixlag;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.server.level.ParticleStatus;

/**
 * Áp dụng / khôi phục các tùy chọn đồ họa tốn FPS.
 * Giá trị gốc của người chơi được lưu lại khi bật và trả về đúng như cũ khi tắt.
 */
public final class FixLagManager {
    private static LagLevel level = LagLevel.OFF;

    // Giá trị gốc
    private static int savedRenderDistance;
    private static boolean savedShadows;
    private static int savedBiomeBlend;
    private static double savedEntityScaling;
    private static ParticleStatus savedParticles;
    private static int savedFramerate;

    private FixLagManager() {}

    public static LagLevel level() {
        return level;
    }

    public static void cycle(Minecraft mc) {
        LagLevel next = level.next();
        Options o = mc.options;

        if (level == LagLevel.OFF) {
            // Bắt đầu bật -> lưu cài đặt gốc
            savedRenderDistance = o.renderDistance().get();
            savedShadows = o.entityShadows().get();
            savedBiomeBlend = o.biomeBlendRadius().get();
            savedEntityScaling = o.entityDistanceScaling().get();
            savedParticles = o.particles().get();
            savedFramerate = o.framerateLimit().get();
        }

        switch (next) {
            case OFF -> restore(o);
            case LOW -> apply(o, 10, 0.75, ParticleStatus.DECREASED, false);
            case ULTRA -> apply(o, 6, 0.5, ParticleStatus.MINIMAL, true);
        }
        level = next;
    }

    private static void apply(Options o, int maxRenderDistance, double entityScaling,
                              ParticleStatus particles, boolean unlimitedFps) {
        o.renderDistance().set(Math.min(savedRenderDistance, maxRenderDistance));
        o.entityShadows().set(false);
        o.biomeBlendRadius().set(0);
        o.entityDistanceScaling().set(entityScaling);
        o.particles().set(particles);
        o.framerateLimit().set(unlimitedFps ? 260 : savedFramerate);
    }

    private static void restore(Options o) {
        o.renderDistance().set(savedRenderDistance);
        o.entityShadows().set(savedShadows);
        o.biomeBlendRadius().set(savedBiomeBlend);
        o.entityDistanceScaling().set(savedEntityScaling);
        o.particles().set(savedParticles);
        o.framerateLimit().set(savedFramerate);
    }
}
