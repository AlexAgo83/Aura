package com.auraRpg.gui;

import com.auraRpg.ressources.AuraFontChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VFont;

public abstract class AuraListViewLarge extends AuraListView {
	public AuraListViewLarge(VAbstractEngine e, float x, float y) {
		super(e, x, y);
	}
	@Override
	public VFont getFont() {
		return AuraFontChooser.SYSTEM_FONT_BIG.getFont();
	}
	@Override
	public int getInterLigneHeight() {
		return 20;
	}
}
