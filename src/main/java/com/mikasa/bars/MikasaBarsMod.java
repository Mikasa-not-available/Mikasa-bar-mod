package com.mikasa.bars;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mikasa-bars - target health bar overlay.
 * Author: Mikasa
 * Version: fabric-26.2-1.2
 */
public final class MikasaBarsMod implements ModInitializer {
	public static final String AUTHOR = "Mikasa";
	public static final String MOD_ID = "mikasa-bars";
	public static final String MOD_NAME = "Mikasa-bars";
	public static final String VERSION = "fabric-26.2-1.2";
	public static final String ENGINE = "fabric";
	public static final String GAME_VERSION = "26.2";
	public static final String MOD_VERSION = "1.2";
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
