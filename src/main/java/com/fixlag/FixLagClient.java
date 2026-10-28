package com.fixlag;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

/**
 * FixLag - nhấn B để chuyển vòng: TẮT -> NHẸ -> CỰC NHẸ -> TẮT.
 * Tự lưu và khôi phục cài đặt gốc của bạn.
 */
public class FixLagClient implements ClientModInitializer {
    public static final String MOD_ID = "fixlag";

    private KeyMapping cycleKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "main"));

        cycleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.fixlag.cycle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, category));

        StatusHud.register();
        ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
    }

    private void onTick(Minecraft client) {
        while (cycleKey.consumeClick()) {
            FixLagManager.cycle(client);
        }
    }
}
