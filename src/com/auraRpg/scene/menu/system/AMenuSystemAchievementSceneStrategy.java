package com.auraRpg.scene.menu.system;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.saveGame.AuraAchievement;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuSystemAchievementSceneStrategy extends AMenuSystemSceneStrategy {
	public AMenuSystemAchievementSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_ACHIEVEMENT);
	}
	
	
	private AuraListViewMedium lvAchievements;
	private AAchievementData datas;
	public AAchievementData getDatas() {
		if (datas == null) {
			List<String> achs = new ArrayList<String>();
			AuraAchievement ach = getAuraPreferences().getAchievement();
			String HH = ""+new BigDecimal(ach.getPlayed() / 1000 / 60 / 60).setScale(0, RoundingMode.DOWN).intValue(); //$NON-NLS-1$
			
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_TIME_PLAYED.getMessage(getEngine(), HH, AuraSaveGame.SDF_MM_SS.format(ach.getPlayed())));
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_PLAYED_DEATH.getMessage(getEngine(), ""+ach.getPlayerDeath())); //$NON-NLS-1$
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_MISSION_FINISHED.getMessage(getEngine(), ""+ach.getMissionTerminee())); //$NON-NLS-1$
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_MISSION_REMOVED.getMessage(getEngine(), ""+ach.getMissionAnnulee())); //$NON-NLS-1$
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_MISSION_FAILED.getMessage(getEngine(), ""+ach.getMissionFailed())); //$NON-NLS-1$
			achs.add(AuraI18nChooser.GUI_ACHIEVEMENT_ROWDATA_PEDOMETER.getMessage(getEngine(), ""+ach.getMeterRun())); //$NON-NLS-1$
			
			datas = new AAchievementData(achs.toArray(new String[0]));
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.lvAchievements = new AuraListViewMedium(getEngine(), 165, getTxTitre().getHeight()) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override public String objectToString(Object o) {
				return o.toString();
			}
			@Override public VTexture getTextureIcones() { return null; }
			@Override public void onTouchElement(Object o) {}
		};
		lvAchievements.attach(getScene());
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
		datas = null;
		lvAchievements.print(1);
	}
	@Override
	public void onRemove() {
		super.onRemove();
		lvAchievements.detach();
	}
	
	private class AAchievementData extends VPageData<String> {
		public AAchievementData(String[] datas) {
			super(datas, 14);
		}
	}
}