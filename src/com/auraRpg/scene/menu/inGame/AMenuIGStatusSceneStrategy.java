package com.auraRpg.scene.menu.inGame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.auraRpg.data.ASkills;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.saveGame.AuraSkills;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGStatusSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGStatusSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_STATUS);
	}

	private AuraButton btSkills;
	
	private AuraListViewMedium lvStatus;
	private VPageData<String> datas;
	public VPageData<String> getDatas() {
		if (datas == null) {
			List<String> st = new ArrayList<String>();
			AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
			AuraPersonnageInfo pi = sg.getPersonnageInfo();
			AuraSkills sk = sg.getSkills();

			st.add(AuraI18nChooser.GUI_STATUS_ROWDATA_HEALTH.getMessage(getEngine(), 
				""+new BigDecimal(pi.getHealth()).setScale(0, RoundingMode.DOWN).intValue(), 
				""+new BigDecimal(pi.getSanteMax()).setScale(0, RoundingMode.DOWN).intValue()));
			st.add("");
			st.add(AuraI18nChooser.GUI_STATUS_ROWDATA_STARTER_PROFESSION.getMessage(getEngine(), ""+sg.getSkills().getStarterProfession().getLibelle()));
			st.add(AuraI18nChooser.GUI_STATUS_ROWDATA_CREDITS.getMessage(getEngine(), ""+sg.getInventory().getCredits()));
			st.add("");
			st.add(AuraI18nChooser.GUI_STATUS_ROWDATA_SKILL_POINTS.getMessage(getEngine(), ""+sk.getSkillPoints()));
			for (ASkills s: ASkills.values()) {
				int value = sk.getSkillsInfo().get(s);
				st.add(AuraI18nChooser.GUI_STATUS_ROWDATA_SKILLS.getMessage(getEngine(),
					s.getLibelle(),
					""+value));
			}
			
			datas = new VPageData<String>(st.toArray(new String[0]), 14);
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		lvStatus = new AuraListViewMedium(getEngine(), 165, getTxTitre().getHeight()+5) {
			@Override public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override public String objectToString(Object o) { 
				return o.toString(); 
			}
			@Override public VTexture getTextureIcones() { return null; }
			@Override public void onTouchElement(Object o) {}
		};
		lvStatus.attach(getScene());
		this.btSkills = new AuraButton(getEngine(), getScene(), getMaxX(), 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getSkillsScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_STATUS_BT_SKILLS.getMessage(getEngine());
			}
		};
		btSkills.attach();
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
		datas = null;
		lvStatus.print(1);
	}
	@Override
	public void onRemove() {
		lvStatus.detach();
		btSkills.detach();
		super.onRemove();
	}
}