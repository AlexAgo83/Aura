package com.auraRpg.scene.menu.inGame;

import com.auraRpg.data.AMission;
import com.auraRpg.data.AMissionBounty;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGMissionSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGMissionSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_MISSION);
	}

	private AuraListViewLarge lvMissions;
	private AMissionData datas;
	public AMissionData getDatas() {
		if (datas == null) {
			datas = new AMissionData(getAuraPreferences().getCurrentSaveGame().getMissions().getMissionsTab().toArray(new AMission[0]));
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.lvMissions = new AuraListViewLarge(getEngine(), 165, getTxTitre().getHeight()) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_MISSION.getTexture();
			}
			@Override
			public VTexture getTextureBy(Object o) {
				AMission m = (AMission) o;
				return m.getType().getLogo();
			}
			@Override
			public void onTouchElement(Object o) {
				AMission mission = (AMission) o;
				AMenuIGViewMissionSceneStrategy m = getContextMemory().getViewMissionScene();
				m.setMission(mission, false);
				getEngine().getVSceneManager().switchTo(m, false, true, true);
			}
			@Override
			public String objectToString(Object o) {
				AMission m = (AMission) o;
				String msg = 
					m.getTimeLeft(getEngine())  //$NON-NLS-1$
					+ " : "; //$NON-NLS-1$
				switch (m.getType()) {
					case DELIVERY:
						msg = msg + m.getName(getEngine());
						break;
					case BOUNTY:
						msg = msg + m.getName(getEngine()) 
							+ " " + ((AMissionBounty) m).getCount() //$NON-NLS-1$
							+ "/" + ((AMissionBounty) m).getMaxCount() + ""; //$NON-NLS-1$ //$NON-NLS-2$
						break;
				}
				return msg;
			}
		};
		super.onCreate();
		lvMissions.attach(getScene());
	}

	@Override
	public void onRefresh() {
		super.onRefresh();
		datas = null;
		lvMissions.print(1);
	}
	
	@Override
	public void onRemove() {
		lvMissions.detach();
		super.onRemove();
	}
	
	private class AMissionData extends VPageData<AMission> {
		public AMissionData(AMission[] datas) {
			super(datas, 9);
		}
	}
}