package net.spellcraftgaming.rpghud.gui.hud.element.modern;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.spellcraftgaming.rpghud.gui.hud.HudModern;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

public class HudElementLevelModern extends HudElement {

	public HudElementLevelModern() {
		super(HudElementType.LEVEL, 0, 0, 0, 0, true);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return !this.mc.options.hideGui;
	}

	@Override
	public void drawElement(GuiGraphics gg, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		String level = String.valueOf(this.mc.player.experienceLevel);
		
		int xOffset = ((HudModern) this.rpgHud.huds.get("modern")).getPosX();
		int width = this.mc.font.width(level);
		if(width < 16) width = 16;
		if(width < xOffset) width = xOffset;
		else ((HudModern) this.rpgHud.huds.get("modern")).setPosX(width);
		
		if (this.mc.font.width(level) > (width + 2))
			width = this.mc.font.width(level) + 2;
		
		RenderSystem.disableBlend();
		
		int posX = (this.settings.getBoolValue(Settings.render_player_face) ? 23 : 2) + this.settings.getPositionValue(Settings.level_position)[0];
		int posY = ((this.settings.getBoolValue(Settings.show_numbers_health) && this.settings.getBoolValue(Settings.show_numbers_food)) ? 22 : 26) + this.settings.getPositionValue(Settings.level_position)[1];
		
		if (this.settings.getStringValue(Settings.clock_time_format) == "time.24" || !this.settings.getBoolValue(Settings.render_player_face)) {
			drawRect(gg, posX, posY, width, 7, 0xA0000000);
		} else {
			drawRect(gg, 26 + this.settings.getPositionValue(Settings.level_position)[0], posY, width, 7, 0xA0000000);
		}
		gg.pose().scale(0.5f, 0.5f, 0.5f);

		if (this.settings.getStringValue(Settings.clock_time_format) == "time.24" || !this.settings.getBoolValue(Settings.render_player_face)) {
			gg.drawCenteredString( this.mc.font, level, (posX * 2) + width, posY * 2 + 3, 0x80FF20);
		} else {
			gg.drawCenteredString( this.mc.font, level, 70 + this.settings.getPositionValue(Settings.level_position)[0] * 2, posY * 2 + 3, 0x80FF20);
		}
		gg.pose().scale(2.0f, 2.0f, 2.0f);
		RenderSystem.enableBlend();
	}

}
