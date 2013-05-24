package com.auraRpg.scene;

import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.badlogic.gdx.physics.box2d.Body;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractKeyboard;
import com.voidEngine.engine.ressources.VFont;
import com.voidEngine.engine.ressources.VTileTexture;

public abstract class AKeyBoardSceneStrategy extends AAbstractMenuSceneStrategy {
	public AKeyBoardSceneStrategy(VAbstractEngine engine, AContextSceneMemory context) {
		super(engine, context, null);
	}
	
	@Override
	public int getMinX() {
		return 0;
	}
	@Override
	public int getMaxX() {
		return 800;
	}

	private VAbstractKeyboard keyboard;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.keyboard = new VAbstractKeyboard(getEngine(), VAbstractKeyboard.KEY_ALPHA_AZERTY, 16) {
			@Override
			public void onOk() {
				doOnClose(keyboard.getText());
			}
			@Override public VTileTexture getTextBoxBackgroundTexture() { return null; }
			@Override public VTileTexture getKeyboardBackgroundTexture() { return null; }
			@Override
			public VFont getKeyFont() {
				return AuraFontChooser.SYSTEM_FONT_BIG.getFont();
			}
			@Override
			public boolean withBgKeyBoard() {
				return false;
			}
			@Override
			public VTileTexture getKeyBackgroundTexture() {
				return AuraTileTextureChooser.GUI_KEY_BG.getTileTexture();
			}
		};
		keyboard.attach(getScene());
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
	}
	@Override
	public void onRemove() {
		keyboard.detach();
		super.onRemove();
	}
	@Override public void onContact(Body eA, Body eB){}
	public abstract void doOnClose(String text);
}
