package com.auraRpg.ressources;

import com.voidEngine.engine.ressources.VFont;

public enum AuraFontChooser {
	SYSTEM_FONT_X_SMALL(1, 10, "fonts/system.ttf"), //$NON-NLS-1$
	SYSTEM_FONT_SMALL(2, 12, "fonts/system.ttf"), //$NON-NLS-1$
	SYSTEM_FONT_MEDIUM(3, 15, "fonts/system.ttf"), //$NON-NLS-1$
	SYSTEM_FONT_BIG(4, 20, "fonts/system.ttf"), //$NON-NLS-1$
	SYSTEM_FONT_X_BIG(5, 30, "fonts/system.ttf"); //$NON-NLS-1$
	
	private int id;
	private int size;
	private String fileName;
	
	private AuraFontChooser(int id, int size, String fileName) {
		this.id = id;
		this.size = size;
		this.fileName = fileName;
	}
	
	private VFont font;
	public VFont getFont() {
		if (font == null) {
			font = new VFont(id, size, fileName);
		}
		return font;
	}
}
