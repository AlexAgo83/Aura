package com.auraRpg.gui;

import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractListView;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.ressources.VTileTexture;

public abstract class AuraListView extends VAbstractListView {
	public AuraListView(VAbstractEngine e, float x, float y) {
		super(e, x, y);
	}
	@Override
	public boolean isShowButtonWhenOnePage() {
		return true;
	}
	@Override
	public int getMaxWidth() {
		return 120;
	}
	@Override
	public VTileTexture getPrevTexture() {
		return AuraTileTextureChooser.GUI_PREV.getTileTexture();
	}
	@Override
	public VTileTexture getNextTexture() {
		return AuraTileTextureChooser.GUI_NEXT.getTileTexture();
	}
	@Override
	public VTexture getTextureBy(Object o) {
		return null;
	}
}