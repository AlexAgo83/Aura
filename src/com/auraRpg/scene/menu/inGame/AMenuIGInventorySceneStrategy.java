package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.AItemGoods;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGInventorySceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGInventorySceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_INVENTORY);
	}

	private ChangeableText txWeaponInUse;
	private ChangeableText txDamage;
	private ChangeableText txArmorInUse;
	private ChangeableText txResistance;
	private ChangeableText txInventorySlotsUsed;
	
	private AuraButton btWeapon;
	private AuraButton btArmor;
	
	private AuraListViewMedium lvInventory;
	private AItemGoodsData datas;
	public AItemGoodsData getDatas() {
		if (datas == null) {
			datas = new AItemGoodsData(AItemGoods.values());
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight() + 5;
		this.txWeaponInUse = new ChangeableText(getMinX()+5, nextY,
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txWeaponInUse);
		nextY += txWeaponInUse.getHeight() + 5;
		this.txDamage = new ChangeableText(getMinX()+5, nextY,
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txDamage);
		nextY += txDamage.getHeight() + 5;
		this.txArmorInUse = new ChangeableText(getMinX()+5, nextY,
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txArmorInUse);
		nextY += txArmorInUse.getHeight() + 5;
		this.txResistance = new ChangeableText(getMinX()+5, nextY,
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txResistance);
		nextY += txResistance.getHeight() + 10;
		this.txInventorySlotsUsed = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txInventorySlotsUsed);
		nextY += txInventorySlotsUsed.getHeight() + 10;
		this.lvInventory = new AuraListViewMedium(getEngine(), getMinX()+5, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public String objectToString(Object o) {
				AItemGoods i = (AItemGoods) o;
				return i.getLibelle() + " : " + getAuraPreferences().getCurrentSaveGame().getInventory().getItemQuantity(i);
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_ITEM_MINERAL.getTexture();
			}
			@Override
			public VTexture getTextureBy(Object o) {
				AItemGoods i = (AItemGoods) o;
				return i.getLogo();
			}
			@Override public void onTouchElement(Object o) {}
		};
		lvInventory.attach(getScene());
		this.btWeapon = new AuraButton(getEngine(), getScene(), getMaxX(), 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getWeaponScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INVENTORY_BT_WEAPON.getMessage(getEngine());
			}
		};
		btWeapon.attach();
		this.btArmor = new AuraButton(getEngine(), getScene(), getMaxX(), 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getArmorScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INVENTORY_BT_ARMOR.getMessage(getEngine());
			}
		};
		btArmor.attach();
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
		// WEAPONS
		txWeaponInUse.setText(
			AuraI18nChooser.GUI_INVENTORY_WEAPON_IN_USE.getMessage(getEngine(), 
				sg.getPersonnageInfo().getWeapon().getLibelle()));
		txDamage.setText(
			AuraI18nChooser.GUI_INVENTORY_DEGATS.getMessage(getEngine(), 
				""+sg.getPersonnageInfo().getDegats()));
		// ARMORS
		txArmorInUse.setText(
			AuraI18nChooser.GUI_INVENTORY_ARMOR_IN_USE.getMessage(getEngine(), 
				sg.getPersonnageInfo().getArmor().getLibelle()));
		txResistance.setText(
			AuraI18nChooser.GUI_INVENTORY_RESISTANCE.getMessage(getEngine(), 
				""+sg.getPersonnageInfo().getResistance()));
		
		AuraInventory i = sg.getInventory();
		txInventorySlotsUsed.setText(
			AuraI18nChooser.GUI_INVENTORY_SLOT_USED.getMessage(getEngine(), 
				""+i.getSlotsUsed(), ""+i.getMaxSlots()));
		lvInventory.print(1);
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txWeaponInUse);
		getScene().detachChild(txDamage);
		getScene().detachChild(txArmorInUse);
		getScene().detachChild(txResistance);
		getScene().detachChild(txInventorySlotsUsed);
		lvInventory.detach();
		btWeapon.detach();
		btArmor.detach();
		super.onRemove();
	}
	
	public class AItemGoodsData extends VPageData<AItemGoods> {
		public AItemGoodsData(AItemGoods[] datas) {
			super(datas, 9);
		}
	}
}