package net.spellcraftgaming.rpghud.gui.hud;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;

import static net.spellcraftgaming.rpghud.gui.hud.element.HudElementType.*;

public abstract class Hud {

	/** Hud key for registering*/
	public final String hudKey;
	/** Hud name for display in settings*/
	public final String hudName;
	
	protected Map<HudElementType, HudElement> elements = new HashMap<HudElementType, HudElement>();
	
	/** Minecraft instance*/
	protected Minecraft mc;
	
	public Hud(Minecraft mc, String hudKey, String hudName) {
		this.mc = mc;
		this.hudKey = hudKey;
		this.hudName = hudName;
		this.setElements();
	}

	/** Function to register all elements to this HUD*/
	public void setElements(){
		this.elements.put(HOTBAR, setElementHotbar());
		this.elements.put(CROSSHAIR, setElementCrosshair());
		this.elements.put(HEALTH, setElementHealth());
		this.elements.put(ARMOR, setElementArmor());
		this.elements.put(FOOD, setElementFood());
		this.elements.put(HEALTH_MOUNT, setElementHealthMount());
		this.elements.put(AIR, setElementAir());
		this.elements.put(JUMP_BAR, setElementJumpBar());
		this.elements.put(EXPERIENCE, setElementExperience());
		this.elements.put(LEVEL, setElementLevel());
		this.elements.put(CLOCK, setElementClock());
		this.elements.put(DETAILS, setElementDetails());
		
	}
	
	/** Function which returns a new element which is the crosshair element*/
	protected abstract HudElement setElementCrosshair();
	
	/** Function which returns a new element which is the hotbar element*/
	protected abstract HudElement setElementHotbar();
	
	/** Function which returns a new element which is the health element*/
	protected abstract HudElement setElementHealth();	
	
	/** Function which returns a new element which is the food element*/
	protected abstract HudElement setElementFood();	
	
	/** Function which returns a new element which is the armor element*/
	protected abstract HudElement setElementArmor();	
	
	/** Function which returns a new element which is the air element*/
	protected abstract HudElement setElementAir();	
	
	/** Function which returns a new element which is the experience element*/
	protected abstract HudElement setElementExperience();
	
	/** Function which returns a new element which is the experience level element*/
	protected abstract HudElement setElementLevel();
	
	/** Function which returns a new element which is the jump bar element*/
	protected abstract HudElement setElementJumpBar();
	
	/** Function which returns a new element which is the mount health element*/
	protected abstract HudElement setElementHealthMount();
	
	/** Function which returns a new element which is the clock element*/
	protected abstract HudElement setElementClock();
	
	/** Function which returns a new element which is the details element*/
	protected abstract HudElement setElementDetails();
	
	/** Draws the an element of the HudElementType type on the screen
	 * 
	 * @param type The type of the Element
	 * @param gui The gui to draw on
	 * @param zLevel The zLevel to draw at
	 * @param partialTicks The partialTicks for animations
	 */
	public void drawElement(HudElementType type, Gui gui, float zLevel, float partialTicks) {
		this.elements.get(type).drawElement(gui, zLevel, partialTicks);
	}
	
	public boolean checkElementConditions(HudElementType type) {
		return this.elements.get(type).checkConditions();
	}
}
