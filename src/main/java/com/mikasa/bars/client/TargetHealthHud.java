package com.mikasa.bars.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Draws a static top-center name + HP bar + Current/Max for the look target.
 * Author: Mikasa
 */
public final class TargetHealthHud {
	private static final int BAR_WIDTH = 90;
	private static final int BAR_HEIGHT = 8;
	/** Extra padding from the top of the screen. */
	private static final int TOP_MARGIN = 8;
	/** Display range multiplier vs vanilla entity interaction range. */
	private static final double RANGE_MULTIPLIER = 2.0;

	private TargetHealthHud() {
	}

	public static void render(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null || mc.level == null || mc.gui.hud.isHidden()) {
			return;
		}

		LivingEntity living = findTarget(mc);
		if (living == null) {
			return;
		}

		float health = living.getHealth();
		float maxHealth = Math.max(0.1f, living.getMaxHealth());
		float ratio = Mth.clamp(health / maxHealth, 0f, 1f);

		String name = living.getDisplayName().getString();
		String hpText = formatHp(health) + "/" + formatHp(maxHealth);

		int screenX = graphics.guiWidth() / 2;
		int nameY = TOP_MARGIN;
		int barTop = nameY + (name != null && !name.isBlank() ? 12 : 0);
		int barLeft = screenX - BAR_WIDTH / 2;

		graphics.fill(barLeft - 1, barTop - 1, barLeft + BAR_WIDTH + 1, barTop + BAR_HEIGHT + 1, 0xC0000000);
		graphics.fill(barLeft, barTop, barLeft + BAR_WIDTH, barTop + BAR_HEIGHT, 0xFF333333);
		int fillWidth = Math.max(0, Math.round(BAR_WIDTH * ratio));
		graphics.fill(barLeft, barTop, barLeft + fillWidth, barTop + BAR_HEIGHT, 0xFFE74C3C);

		int textColor = 0xFFFFFFFF;
		if (name != null && !name.isBlank()) {
			graphics.centeredText(mc.font, name, screenX, nameY, textColor);
		}
		graphics.centeredText(mc.font, hpText, screenX, barTop + BAR_HEIGHT + 2, textColor);
	}

	/**
	 * Crosshair living entity within 2x vanilla entity interaction range.
	 */
	private static LivingEntity findTarget(Minecraft mc) {
		double range = mc.player.entityInteractionRange() * RANGE_MULTIPLIER;
		HitResult hit = ProjectileUtil.getHitResultOnViewVector(
				mc.player,
				entity -> entity instanceof LivingEntity living
						&& living.isAlive()
						&& living != mc.player
						&& !living.isSpectator(),
				range
		);
		if (!(hit instanceof EntityHitResult entityHit)) {
			return null;
		}
		if (!(entityHit.getEntity() instanceof LivingEntity living)) {
			return null;
		}
		return living;
	}

	private static String formatHp(float value) {
		if (Math.abs(value - Math.rint(value)) < 0.05f) {
			return Integer.toString(Math.round(value));
		}
		return String.format("%.1f", value);
	}
}
