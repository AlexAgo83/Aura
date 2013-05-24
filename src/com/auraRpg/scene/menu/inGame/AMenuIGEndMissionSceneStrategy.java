package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.AMission;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGEndMissionSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGEndMissionSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_NEW_MISSION);
	}
	
	private ChangeableText txTitreMission;
	private AuraListViewMedium lvDescription;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		
		float nextY = getTxTitre().getHeight()+10;
		this.txTitreMission = new ChangeableText(165, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 60); //$NON-NLS-1$
		txTitreMission.setColor(1, 0, 0);
		
		nextY = nextY+txTitreMission.getHeight()+5;
		this.lvDescription = new AuraListViewMedium(getEngine(), 165, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getData();
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
		
		getScene().attachChild(txTitreMission);
		lvDescription.attach(getScene());
	}

	private AMission mission;
	public AMission getMission() {
		return mission;
	}
	public void setMission(AMission mission) {
		this.mission = mission;
		this.data = null;
	}
	private VPageData<String> data;
	public VPageData<String> getData() {
		if (data == null) {
			data = new AMissionData(getMission().getEndDescription(getEngine()));	
		}
		return data;
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		txTitreMission.setText(getMission().getName(getEngine()));
		lvDescription.print(1);
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txTitreMission);
		lvDescription.detach();
		super.onRemove();
	}
	
	public class AMissionData extends VPageData<String> {
		public AMissionData(String[] datas) {
			super(datas, 20);
		}
	}
}