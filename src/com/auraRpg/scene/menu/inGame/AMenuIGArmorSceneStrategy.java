package com.auraRpg.scene.menu.inGame;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.AuraEntity;
import com.auraRpg.data.AItemArmor;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGArmorSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGArmorSceneStrategy(VAbstractEngine e, AContextSceneMemory ctx) {
		super(e, ctx, AuraI18nChooser.TITRE_ARMOR);
	}
	
	private AItemArmorData datas;
	public AItemArmorData getDatas() {
		if (datas == null) {
			AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
			List<AItemArmor> ws = new ArrayList<AItemArmor>();
			for (AItemArmor w: AItemArmor.values()) {
				if (inv.isArmorOwn(w))
					ws.add(w);
			}
			datas = new AItemArmorData(ws.toArray(new AItemArmor[0]));
		}
		return datas;
	}
	private AuraListViewLarge lvArmorsToUse;
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight() + 10;
		this.lvArmorsToUse = new AuraListViewLarge(getEngine(), getMinX()+5, nextY) {
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_WEAPON_1.getTexture();
			}
			@Override
			public VTexture getTextureBy(Object o) {
				return ((AItemArmor) o).getLogo();
			}
			@Override
			public void onTouchElement(Object o) {
				AItemArmor newA = (AItemArmor) o;
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				sg.getPersonnageInfo().setArmor(newA);
				datas = null;
				lvArmorsToUse.print(1);
				if (getContextMemory().getLastPlaneteBoard() != null 
						&& getContextMemory().getLastPlaneteBoard().getMapPrinter() != null) {
					AuraEntity e = getContextMemory().getLastPlaneteBoard().getMapPrinter().getPlayer();
					if (e != null) {
						for (AItemArmor a: AItemArmor.values()) {
							e.removeLayer(a.getSkin());
						}
						e.attachLayer(getAuraPreferences().getCurrentSaveGame()
							.getPersonnageInfo().getArmor().getSkin(), 10);
					}
				}
			}
			@Override
			public String objectToString(Object o) {
				AItemArmor a = (AItemArmor) o;
				AuraPersonnageInfo pi = getAuraPreferences().getCurrentSaveGame().getPersonnageInfo();
				return a.getLibelle() + 
					(a.equals(pi.getArmor()) ? " [Selected]": "" + " ["+a.getDescription()+"]");  
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvArmorsToUse.attach(getScene());
	}

	@Override
	public void onRefresh() {
		super.onRefresh();
		this.datas = null;
		lvArmorsToUse.print(1);
	}
	
	@Override
	public void onRemove() {
		lvArmorsToUse.detach();
		super.onRemove();
	}
	
	private class AItemArmorData extends VPageData<AItemArmor> {
		public AItemArmorData(AItemArmor[] datas) {
			super(datas, 8);
		}
	}
}