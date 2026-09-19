package com.mikasa.bars;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mikasa-bars - target health bar overlay.
 * Author: Mikasa
 */
public final class MikasaBarsMod implements ModInitializer {
	public static final String AUTHOR = "Mikasa";
	public static final String MOD_ID = "mikasa-bars";
	public static final String MOD_NAME = "Mikasa-bars";
	public static final String ENGINE = "fabric";
	public static final String GAME_VERSION = "1.20.1";
	public static final String MOD_VERSION = "1.3";
	public static final String VERSION = ENGINE + "-" + GAME_VERSION + "-" + MOD_VERSION;
	public static final String LOG_PREFIX = "[MikasaBars]";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void log(String message) {
		LOGGER.info("{} {}", LOG_PREFIX, message);
	}

	@Override
	public void onInitialize() {
		log("starting " + MOD_NAME + " " + VERSION + " by " + AUTHOR);
	}
}
