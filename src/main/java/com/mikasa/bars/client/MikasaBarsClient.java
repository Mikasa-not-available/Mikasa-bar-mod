package com.mikasa.bars.client;

import com.mikasa.bars.MikasaBarsMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.resources.Identifier;

/**
 * Client entry - registers target health HUD.
 * Author: Mikasa
 */
public final class MikasaBarsClient implements ClientModInitializer {
	private static final Identifier TARGET_HEALTH_HUD =
			Identifier.fromNamespaceAndPath(MikasaBarsMod.MOD_ID, "target_health");

	@Override
	public void onInitializeClient() {
		HudElementRegistry.addLast(TARGET_HEALTH_HUD, TargetHealthHud::render);
		MikasaBarsMod.log("client HUD registered");
	}
}
