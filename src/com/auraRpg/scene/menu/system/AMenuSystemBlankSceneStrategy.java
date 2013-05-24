package com.auraRpg.scene.menu.system;

import com.auraRpg.gui.AuraDialog;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;

public class AMenuSystemBlankSceneStrategy extends AMenuSystemSceneStrategy {
	private boolean firstPrint;
	public AMenuSystemBlankSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, null);
		this.firstPrint = true;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
	}
	@Override
	public void onRemove() {
		super.onRemove();
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
		if (firstPrint) {
			AuraI18nChooser.checker(getEngine());
			registerDialog(AuraDialog.creerDialog(getEngine(), 
				AuraI18nChooser.DIALOG_WELCOME_MESSAGE.getMessage(getEngine()), getScene()));
			firstPrint = false;
		}
//		getEngine().log("ALLID="+AConflict.getAllIdPossible().length);
//		getEngine().log("SG="+getAuraPreferences().getSaveGames().keySet().size());
//		for (Integer sgId: getAuraPreferences().getSaveGames().keySet()) {
//			AuraSaveGame sg = getAuraPreferences().getSaveGames().get(sgId);
//			getEngine().log("SG_CONFLICTS=" + sg.getPolitics().getConflits().size());
//			for (AConflict c: sg.getPolitics().getConflits()) {
//				getEngine().log(sg.getPersonnageInfo().getName() + " " + c.getCorpoA().getName() + " " + c.getCorpoB().getName()); 
//			}
//		}
	}
}