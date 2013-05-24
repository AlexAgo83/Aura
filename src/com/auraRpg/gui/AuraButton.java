package com.auraRpg.gui;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractButton;
import com.voidEngine.engine.ressources.VFont;

public abstract class AuraButton extends VAbstractButton {
	public AuraButton(VAbstractEngine engine, Scene scene, float x, float y) {
		super(engine, scene, x, y, 160, 60, HorizontalAlign.CENTER);
		this.attachLayer(AuraTileTextureChooser.GUI_BUTTON.getTileTexture(), 0);
	}
	@Override
	public VFont getTextBoxFont() {
		return AuraFontChooser.SYSTEM_FONT_BIG.getFont();
	}
	@Override public boolean onTouchCancel(float x, float y) { return false; }
	@Override public boolean onTouchMove(float x, float y) { return false; }
	@Override public boolean onTouchOutside(float x, float y) { return false; }
	@Override public boolean onTouchPressed(float x, float y) { return false; }
}
