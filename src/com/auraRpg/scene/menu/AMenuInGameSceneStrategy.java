package com.auraRpg.scene.menu;

import com.auraRpg.gui.AuraButton;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.scene.AAbstractMenuSceneStrategy;
import com.auraRpg.scene.AContextSceneMemory;
import com.voidEngine.engine.VAbstractEngine;

public abstract class AMenuInGameSceneStrategy extends AAbstractMenuSceneStrategy {
	public AMenuInGameSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AuraI18nChooser i18nId) {
		super(engine, contextMemory, i18nId);
	}
	
	private AuraButton btFAKE;
	private AuraButton btStatus;
	private AuraButton btInventory;
	private AuraButton btResume;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		
		this.btFAKE = new AuraButton(getEngine(), getHUD(), 0, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				return false;
			}
			@Override
			public String getTextBoxMessage() {
				return ""; //$NON-NLS-1$
			}
		};
		this.btStatus = new AuraButton(getEngine(), getHUD(), 0, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getStatusScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_STATUS.getMessage(getEngine());
			}
		};
		this.btInventory = new AuraButton(getEngine(), getHUD(), 0, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getInventoryScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_INVENTORY.getMessage(getEngine());
			}
		};
		this.btResume = new AuraButton(getEngine(), getHUD(), 0, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().back();
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_RESUME.getMessage(getEngine());
			}
		};
		
		// *ATTACH*
		btFAKE.attach();
		btResume.attach();
		btStatus.attach();
		btInventory.attach();
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
	}
	
	@Override
	public void onRemove() {
		super.onRemove();
		btFAKE.detach();
		btResume.detach();
		btStatus.detach();
		btInventory.detach();
	}
}