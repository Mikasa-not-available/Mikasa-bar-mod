package com.mikasa.bars.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;

public final class TargetHealthHud {
	private static final int BAR_WIDTH = 90;
	private static final int BAR_HEIGHT = 8;
	private static final int TOP_MARGIN = 8;
	private static final double RANGE_MULTIPLIER = 2.0;

	private TargetHealthHud() {
	}

	public static void render(DrawContext context, RenderTickCounter tickCounter) {
		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc.player == null || mc.world == null || mc.options.hudHidden) {
			return;
		}

		LivingEntity living = findTarget(mc);
		if (living == null) {
			return;
		}

		float health = living.getHealth();
		float maxHealth = Math.max(0.1f, living.getMaxHealth());
		float ratio = MathHelper.clamp(health / maxHealth, 0f, 1f);

		String name = living.getDisplayName().getString();
		String hpText = formatHp(health) + "/" + formatHp(maxHealth);

		int screenX = context.getScaledWindowWidth() / 2;
		int nameY = TOP_MARGIN;
		int barTop = nameY + (name != null && !name.isBlank() ? 12 : 0);
		int barLeft = screenX - BAR_WIDTH / 2;

		context.fill(barLeft - 1, barTop - 1, barLeft + BAR_WIDTH + 1, barTop + BAR_HEIGHT + 1, 0xC0000000);
		context.fill(barLeft, barTop, barLeft + BAR_WIDTH, barTop + BAR_HEIGHT, 0xFF333333);
		int fillWidth = Math.max(0, Math.round(BAR_WIDTH * ratio));
		context.fill(barLeft, barTop, barLeft + fillWidth, barTop + BAR_HEIGHT, 0xFFE74C3C);

		int textColor = 0xFFFFFFFF;
		if (name != null && !name.isBlank()) {
			drawCentered(context, mc, name, screenX, nameY, textColor);
		}
		drawCentered(context, mc, hpText, screenX, barTop + BAR_HEIGHT + 2, textColor);
	}

	private static void drawCentered(DrawContext context, MinecraftClient mc,
									 String text, int centerX, int y, int color) {
		int width = mc.textRenderer.getWidth(text);
		context.drawText(mc.textRenderer, text, centerX - width / 2, y, color, false);
	}

	private static LivingEntity findTarget(MinecraftClient mc) {
		double range = 3.0D * RANGE_MULTIPLIER;

		HitResult hit = ProjectileUtil.getCollision(
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