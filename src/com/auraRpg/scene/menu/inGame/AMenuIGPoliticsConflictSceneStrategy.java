package com.auraRpg.scene.menu.inGame;

import com.auraRpg.data.AConflict;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGPoliticsConflictSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGPoliticsConflictSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_POLITICS_CONFLICT);
	}
	
	private AuraListViewMedium lvConflicts;
	private AuraButton btReputation;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		
		this.btReputation = new AuraButton(getEngine(), getScene(), 160+480, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getPoliticsReputationsScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_POLITICS_BT_REPUTATION.getMessage(getEngine());
			}
		};
		btReputation.attach();
		
		float nextY = getTxTitre().getHeight()+10;
		this.lvConflicts = new AuraListViewMedium(getEngine(), 165, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getDataConflict();
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_POLITICS.getTexture();
			}
			@Override public void onTouchElement(Object o) {}
			@Override
			public String objectToString(Object o) {
				AConflict c = (AConflict) o;
				return AuraI18nChooser.GUI_POLITICS_ROWDATA_CONFLICT_INFO.getMessage(getEngine(),
					c.getCorpoA().getName(),
					c.getCorpoB().getName()
//					AuraSaveGame.SDF_DATE_LARGE.format(c.getMaxTime()+AuraSaveGame.BASE_TIME)
					);
			}
			public VTexture getTextureBy(Object o) {
				return ((AConflict) o).getCorpoA().getFaction().getFlag(); 
			}
		};
		lvConflicts.attach(getScene());
	}

	private AConflictData dataConflict;
	public AConflictData getDataConflict() {
		if (dataConflict == null) {
			AConflict[] cs = getAuraPreferences().getCurrentSaveGame().getPolitics().getConflits().toArray(new AConflict[0]);
			dataConflict = new AConflictData(cs);	
		}
		return dataConflict;
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		dataConflict = null;
		lvConflicts.print(1);
	}
	
	@Override
	public void onRemove() {
		lvConflicts.detach();
		btReputation.detach();
		super.onRemove();
	}
	
	public class AConflictData extends VPageData<AConflict> {
		public AConflictData(AConflict[] datas) {
			super(datas, 14);
		}
	}
}