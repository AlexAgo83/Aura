package com.auraRpg.scene;

import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public abstract class AAbstractTransitionSceneStrategy extends AAbstractMenuSceneStrategy {
	public AAbstractTransitionSceneStrategy(VAbstractEngine engine, AContextSceneMemory context, AuraI18nChooser i18nMessage) {
		super(engine, context, i18nMessage);
	}
	
	private AuraButton btContinue;
	private AuraListViewMedium lvInformation;
	
	public abstract boolean onContinue();
	public abstract String getDialogText();
	public abstract String[] getDescription();
	
	private VPageData<String> datas;
	public VPageData<String> getDatas() {
		if (datas == null) {
			datas = new VPageData<String>(
				getDescription() != null ? getDescription() : new String[0], 12);
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.btContinue = new AuraButton(getEngine(), getScene(), 160 + 480, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				return onContinue();
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_TRANSITION_BT_CONTINUE.getMessage(getEngine());
			}
		};
		btContinue.attach();
		this.lvInformation = new AuraListViewMedium(getEngine(), 165, getTxTitre().getHeight()+5) {
			@Override public String objectToString(Object o) {
				return o.toString();
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override public VTexture getTextureIcones() { return null; }
			@Override public void onTouchElement(Object o) {}
		};
		lvInformation.attach(getScene());
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
		if (getDialogText() != null) {
			registerDialog(AuraDialog.creerDialog(getEngine(), getDialogText(), getScene()));
		}
		datas = null;
		lvInformation.print(1);
	}
	@Override
	public void onRemove() {
		btContinue.detach();
		lvInformation.detach();
		super.onRemove();
	}
}