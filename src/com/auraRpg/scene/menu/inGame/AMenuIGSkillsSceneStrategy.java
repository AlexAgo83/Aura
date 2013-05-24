package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.ASkills;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraSkills;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGSkillsSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGSkillsSceneStrategy(VAbstractEngine e, AContextSceneMemory ctx) {
		super(e, ctx, AuraI18nChooser.TITRE_SKILLS);
	}
	
	private ASkillsData datas;
	public ASkillsData getDatas() {
		if (datas == null) {
			datas = new ASkillsData();
		}
		return datas;
	}
	
	private ChangeableText txSkillPointsAvailable;
	private AuraListViewLarge lvSkills;
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight() + 5;
		this.txSkillPointsAvailable = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txSkillPointsAvailable);
		nextY = nextY + txSkillPointsAvailable.getHeight() + 10;
		this.lvSkills = new AuraListViewLarge(getEngine(), getMinX()+5, nextY) {
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_TRAVEL.getTexture();
			}
			@Override
			public void onTouchElement(Object o) {
				AuraSkills sk = getAuraPreferences().getCurrentSaveGame().getSkills();
				ASkills s = (ASkills) o;
				long cost = sk.getNextLevelCost(s);
				if (sk.getSkillPoints()>=cost) {
					sk.getSkillsInfo().put(s, sk.getSkillsInfo().get(s)+1);
					sk.setSkillPoints(sk.getSkillPoints()-cost);
					lvSkills.print(1);
					txSkillPointsAvailable.setText(AuraI18nChooser.GUI_STATUS_ROWDATA_SKILL_POINTS.getMessage(getEngine(), 
						""+getAuraPreferences().getCurrentSaveGame().getSkills().getSkillPoints()));
				} else {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_NOT_ENOUGH_SP.getMessage(getEngine()), 
						getScene()));
				}
			}
			@Override
			public String objectToString(Object o) {
				AuraSkills sk = getAuraPreferences().getCurrentSaveGame().getSkills();
				ASkills s = (ASkills) o;
				return AuraI18nChooser.GUI_SKILLS_ROWDATA_SKILLS.getMessage(getEngine(),
					s.getLibelle(),
					""+sk.getSkillsInfo().get(s),
					""+sk.getNextLevelCost(s));
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvSkills.attach(getScene());
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
		lvSkills.print(1);
		txSkillPointsAvailable.setText(AuraI18nChooser.GUI_STATUS_ROWDATA_SKILL_POINTS.getMessage(getEngine(), 
			""+getAuraPreferences().getCurrentSaveGame().getSkills().getSkillPoints()));
	}
	@Override
	public void onRemove() {
		getScene().detachChild(txSkillPointsAvailable);
		lvSkills.detach();
		super.onRemove();
	}
	
	private class ASkillsData extends VPageData<ASkills> {
		public ASkillsData() {
			super(ASkills.values(), 8);
		}
	}
}