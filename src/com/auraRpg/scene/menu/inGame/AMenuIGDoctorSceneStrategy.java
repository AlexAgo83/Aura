package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraProgressBar;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;

public class AMenuIGDoctorSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGDoctorSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_DOCTOR);
	}
	
	private AuraButton btHeal;
	private ChangeableText txCredits;
	private ChangeableText txCostInfo;
	private AuraProgressBar pgHeal;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.btHeal = new AuraButton(getEngine(), getScene(), getMaxX(), 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				AuraPersonnageInfo pi = sg.getPersonnageInfo();
				
				if (pi.getHealth()==pi.getSanteMax() || getPgHeal().getValueRounded() == 0) {
					// ...
				} else if (pi.getHealCost(getPgHeal().getValueRounded())<=sg.getInventory().getCredits()) {
					pi.heal(pgHeal.getValueRounded());
					getEngine().getVSceneManager().back();
				} else {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_NOT_ENOUGH_CREDITS.getMessage(getEngine()), 
						getScene()));					
				}
				maj(getPgHeal().getValueRounded());
				
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_DOCTOR_BT_HEAL.getMessage(getEngine());
			}
		};
		btHeal.attach();

		float nextY = getTxTitre().getHeight() + 5;
		this.txCredits = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_SMALL.getFont()), 
			"", 
			HorizontalAlign.LEFT, 100);
		getScene().attachChild(txCredits);
		
		nextY += txCredits.getHeight() + 10;
		this.txCostInfo = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_SMALL.getFont()), 
			"", 
			HorizontalAlign.LEFT, 100);
		getScene().attachChild(txCostInfo);
		
		nextY = 300;
		this.pgHeal = new AuraProgressBar(getEngine(), getMinX(), 340) {
			@Override
			public void onValueChange(float value) {
				maj(getValueRounded());
			}
		};
		pgHeal.setValue(1);
		pgHeal.attach(getScene());
	}
	
	public AuraProgressBar getPgHeal() {
		return pgHeal;
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		pgHeal.setValue(1);
		maj(1);
	}
	
	@Override
	public void onRemove() {
		pgHeal.detach();
		btHeal.detach();
		getScene().detachChild(txCredits);
		getScene().detachChild(txCostInfo);
		super.onRemove();
	}
	
	private void maj(float value) {
		AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
		AuraInventory inv = sg.getInventory();
		// Credits
		txCredits.setText(AuraI18nChooser.GUI_VENDOR_CREDITS_LEFT.getMessage(getEngine(),""+inv.getCredits()));
		// Heal cost
		AuraPersonnageInfo pi = sg.getPersonnageInfo();
		if (pi.getHealth() != pi.getSanteMax() && pi.getHealValue(value) > 0)
			txCostInfo.setText(
				AuraI18nChooser.GUI_DOCTOR_INFO_COST.getMessage(getEngine(),
					""+pi.getHealValue(value),
					""+pi.getHealCost(value)));
		else
			txCostInfo.setText(AuraI18nChooser.GUI_DOCTOR_NO_NEED.getMessage(getEngine()));
	}
}