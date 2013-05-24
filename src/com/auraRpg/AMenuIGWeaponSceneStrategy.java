package com.auraRpg;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.data.AItemWeapon;
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

public class AMenuIGWeaponSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGWeaponSceneStrategy(VAbstractEngine e, AContextSceneMemory ctx) {
		super(e, ctx, AuraI18nChooser.TITRE_WEAPON);
	}
	
	private AItemWeaponData datas;
	public AItemWeaponData getDatas() {
		if (datas == null) {
			AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
			List<AItemWeapon> ws = new ArrayList<AItemWeapon>();
			for (AItemWeapon w: AItemWeapon.values()) {
				if (inv.isWeaponOwn(w))
					ws.add(w);
			}
			datas = new AItemWeaponData(ws.toArray(new AItemWeapon[0]));
		}
		return datas;
	}
	private AuraListViewLarge lvWeaponsToUse;
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight() + 10;
		this.lvWeaponsToUse = new AuraListViewLarge(getEngine(), getMinX()+5, nextY) {
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_WEAPON_1.getTexture();
			}
			@Override
			public VTexture getTextureBy(Object o) {
				return ((AItemWeapon) o).getLogo();
			}
			@Override
			public void onTouchElement(Object o) {
				AItemWeapon newW = (AItemWeapon) o;
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				sg.getPersonnageInfo().setWeapon(newW);
				datas = null;
				lvWeaponsToUse.print(1);
			}
			@Override
			public String objectToString(Object o) {
				AItemWeapon w = (AItemWeapon) o;
				AuraPersonnageInfo pi = getAuraPreferences().getCurrentSaveGame().getPersonnageInfo();
				return w.getLibelle() + 
					(w.equals(pi.getWeapon()) ? " [Selected]": "" + " ["+w.getDescription()+"]");  
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvWeaponsToUse.attach(getScene());
	}

	@Override
	public void onRefresh() {
		super.onRefresh();
		this.datas = null;
		lvWeaponsToUse.print(1);
	}
	
	@Override
	public void onRemove() {
		lvWeaponsToUse.detach();
		super.onRemove();
	}
	
	private class AItemWeaponData extends VPageData<AItemWeapon> {
		public AItemWeaponData(AItemWeapon[] datas) {
			super(datas, 8);
		}
	}
}