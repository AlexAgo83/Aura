package com.auraRpg.gui;

import org.anddev.andengine.entity.scene.Scene;

import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractCheckBox;
import com.voidEngine.engine.ressources.VFont;
import com.voidEngine.engine.ressources.VTileTexture;

public abstract class AuraCheckBox extends VAbstractCheckBox {
	public AuraCheckBox(VAbstractEngine e, Scene s, float x, float y, float w, float h) {
		super(e, s, x, y, w, h);
	}
	@Override
	public VFont getTextBoxFont() {
		return AuraFontChooser.SYSTEM_FONT_BIG.getFont();
	}
	@Override
	public VTileTexture getCheckedTexture() {
		return AuraTileTextureChooser.GUI_CK_CHECKED.getTileTexture();
	}
	@Override
	public VTileTexture getUnCheckedTexture() {
		return AuraTileTextureChooser.GUI_CK_UNCHECKED.getTileTexture();
	}
	@Override public boolean onTouchCancel(float x, float y) { return false; }
	@Override public boolean onTouchMove(float x, float y) { return false; }
	@Override public boolean onTouchOutside(float x, float y) { return false; }
	@Override public boolean onTouchPressed(float x, float y) { return false; }
}
