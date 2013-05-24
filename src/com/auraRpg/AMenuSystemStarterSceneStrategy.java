package com.auraRpg;

import com.auraRpg.data.AStarterProfession;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;
import com.voidEngine.tools.VPageData;

public class AMenuSystemStarterSceneStrategy extends AMenuSystemSceneStrategy {
	private final String name;
	private final VAbstractSceneStrategy parent;
	
	public AMenuSystemStarterSceneStrategy(
			VAbstractEngine engine, AContextSceneMemory contextMemory, String name,
			VAbstractSceneStrategy parent) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_STARTER);
		this.name = name;
		this.parent = parent;
	}

	private AStarterProfessionData datas;
	public AStarterProfessionData getDatas() {
		if (datas == null) {
			datas = new AStarterProfessionData();
		}
		return datas;
	}
	
	private AuraListViewLarge lvStarterProf;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.lvStarterProf = new AuraListViewLarge(getEngine(), getMinX()+5, getTxTitre().getHeight()+10) {
			@Override public VTexture getTextureIcones() { 
				return AuraTextureChooser.GUI_PUCE_PROF_TRADER.getTexture(); 
			}
			@Override
			public VTexture getTextureBy(Object o) {
				AStarterProfession sp = (AStarterProfession) o;
				return sp.getLogo();
			}
			@Override
			public void onTouchElement(Object o) {
				AStarterProfession stp = (AStarterProfession) o;
				boolean created = getAuraPreferences().newSaveGame(name, stp);
				getEngine().getVSceneManager().switchTo(parent, true, false, true);
				if (!created)
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_SAVEGAME_NEW_NAME_NULL.getMessage(getEngine()), parent.getScene()));
			}
			@Override
			public String objectToString(Object o) {
				AStarterProfession sp = (AStarterProfession) o;
				return sp.getLibelle();
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvStarterProf.attach(getScene());
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		this.datas = null;
		lvStarterProf.print(1);
	}
	
	@Override
	public void onRemove() {
		super.onRemove();
	}
	
	private class AStarterProfessionData extends VPageData<AStarterProfession> {
		public AStarterProfessionData() {
			super(AStarterProfession.values(), 8);
		}
	}
}