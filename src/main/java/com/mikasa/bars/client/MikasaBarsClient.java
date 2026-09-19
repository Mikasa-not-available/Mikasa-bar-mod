package com.mikasa.bars.client;

import com.mikasa.bars.MikasaBarsMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * Client entry - registers target health HUD via legacy HudRenderCallback.
 * Author: Mikasa
 */
public final class MikasaBarsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(TargetHealthHud::render);
		MikasaBarsMod.log("client HUD registered (legacy callback)");
	}
}
