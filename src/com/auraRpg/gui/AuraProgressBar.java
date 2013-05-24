package com.auraRpg.gui;

import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractProgressBar;
import com.voidEngine.engine.ressources.VTexture;

public abstract class AuraProgressBar extends VAbstractProgressBar {
	public AuraProgressBar(VAbstractEngine e, float x, float y) {
		super(e, x, y, 480, 150);
		getBar().setColor(.2f, .4f, .4f);
		getCurseur().setColor(.2f, .4f, .4f);
	}
	@Override
	public VTexture getTextureBar() {
		return AuraTextureChooser.GUI_PROGRESS_BAR_BASE.getTexture();
	}
	@Override
	public VTexture getTextureCurseur() {
		return AuraTextureChooser.GUI_ANALOG_KNOB.getTexture();
	}
}
