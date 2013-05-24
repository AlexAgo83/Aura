package com.auraRpg.scene.menu.system;

import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraSpeechChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuSystemChangeLogsSceneStrategy extends AMenuSystemSceneStrategy {
	public AMenuSystemChangeLogsSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_CHANGE_LOGS);
	}

	private AuraListViewMedium lvChangeLogs;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.lvChangeLogs = new AuraListViewMedium(getEngine(), 165, getTxTitre().getHeight()) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public VTexture getTextureIcones() {
				return null;
			}
			@Override public void onTouchElement(Object o) {}
			@Override
			public String objectToString(Object o) {
				return o.toString();
			}
		};
		lvChangeLogs.attach(getScene());
		lvChangeLogs.print(1);
	}
	
	@Override
	public void onRemove() {
		super.onRemove();
		lvChangeLogs.detach();
	}
	
	private class ChangeLogData extends VPageData<String> {
		public ChangeLogData(String[] speech) {
			super(speech, 14);
		}
	}
	
	private ChangeLogData datas;
	public ChangeLogData getDatas() {
		if (datas == null) {
			datas = new ChangeLogData(getEngine().getVRessourceManager()
				.getSpeech(AuraSpeechChooser.CHANGE_LOGS.getSpeech()));
		}
		return datas;
	}
}