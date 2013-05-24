package com.auraRpg.scene.menu.inGame;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AItemWeapon;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraPolitics;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.saveGame.AuraSkills;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGMilitaryWeaponSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGMilitaryWeaponSceneStrategy(VAbstractEngine e, AContextSceneMemory ctx) {
		super(e, ctx, AuraI18nChooser.TITRE_MILITARY_WEAPON);
	}
	
	private AItemWeaponData datas;
	public AItemWeaponData getDatas() {
		if (datas == null) {
			datas = new AItemWeaponData(AItemWeapon.getBuyable());
		}
		return datas;
	}
	private AuraListViewLarge lvWeapons;
	private AuraButton btGoods;
	private AuraButton btArmor;
	private ChangeableText txCredits;
	
	private ACorporation corpOwner;
	public ACorporation getCorpOwner() {
		return corpOwner;
	}
	public void setCorpOwner(ACorporation corpOwner) {
		this.corpOwner = corpOwner;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight()+5;
		this.txCredits = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txCredits);
		nextY += txCredits.getHeight()+10;
		this.lvWeapons = new AuraListViewLarge(getEngine(), getMinX()+5, nextY) {
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
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				AuraSkills sk = sg.getSkills();
				AuraInventory inv = sg.getInventory();
				AItemWeapon w = (AItemWeapon) o;
				if (!inv.isWeaponOwn(w)) {
					long price = new BigDecimal(
							(w.getBasePrice() - (w.getBasePrice()*sk.getNegociationFactor())))
						.setScale(0, RoundingMode.DOWN)
						.longValue();
					if (inv.getCredits() >= price) {
						long rankCur = -1; 
						if (getCorpOwner() != null) {
							AuraPolitics p = sg.getPolitics();
							rankCur = p.getRankCorporation(getCorpOwner());
						}
						if (rankCur == -1 || (rankCur >= 0 && w.getRank() <= rankCur)) {
							inv.getWeapons().put(w.getId(), true);
							inv.setCredits(inv.getCredits()-price);
							lvWeapons.print(1);
							txCredits.setText(AuraI18nChooser.GUI_VENDOR_CREDITS_LEFT.getMessage(
									getEngine(),""+inv.getCredits()));
						} else {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_NOT_ENOUGH_STANDING.getMessage(getEngine()), 
								getScene()));
						}
					} else {
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_NOT_ENOUGH_CREDITS.getMessage(getEngine()), 
							getScene()));
					}
				}
			}
			@Override
			public String objectToString(Object o) {
				AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
				AuraSkills sk = getAuraPreferences().getCurrentSaveGame().getSkills();
				AItemWeapon w = (AItemWeapon) o;
				return w.getLibelle() + " [" + w.getDescription() + "]" +  
					(inv.isWeaponOwn(w) ?
							"":
							" $" + new BigDecimal(
								(w.getBasePrice() - (w.getBasePrice()*sk.getNegociationFactor())))
									.setScale(0, RoundingMode.DOWN)
									.intValue()
							+" Rank:" + w.getRank());
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvWeapons.attach(getScene());
		this.btGoods = new AuraButton(getEngine(), getScene(), getMaxX(), 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getVendorScene(getCorpOwner()), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_MILITARY_BT_GOODS.getMessage(getEngine());
			}
		};
		btGoods.attach();
		this.btArmor = new AuraButton(getEngine(), getScene(), getMaxX(), 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getMilitaryArmorScene(getCorpOwner()), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VENDOR_BT_MILITARY_ARMOR.getMessage(getEngine());
			}
		};
		btArmor.attach();
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		datas = null;
		lvWeapons.print(1);
		AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
		txCredits.setText(AuraI18nChooser.GUI_VENDOR_CREDITS_LEFT.getMessage(getEngine(),""+inv.getCredits()));
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txCredits);
		lvWeapons.detach();
		btArmor.detach();
		btGoods.detach();
		super.onRemove();
	}
	
	private class AItemWeaponData extends VPageData<AItemWeapon> {
		public AItemWeaponData(AItemWeapon[] datas) {
			super(datas, 8);
		}
	}
}