package com.auraRpg.scene.menu.inGame;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AItemGoods;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.gui.AuraProgressBar;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraItemInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.saveGame.AuraVendorInfo;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGVendorSceneStrategy extends AMenuInGameSceneStrategy {
	private AuraVendorInfo info;
	
	public AMenuIGVendorSceneStrategy(VAbstractEngine e, AContextSceneMemory ctx) {
		super(e, ctx, AuraI18nChooser.TITRE_VENDORS);
	}
	
	public AuraVendorInfo getInfo() {
		return info;
	}
	public void setInfo(AuraVendorInfo info) {
		this.info = info;
	}

	private AuraButton btMilitaryWeapon;
	private AuraButton btMilitaryArmor;
	private AuraButton btBuy;
	private AuraButton btSell;
	private AuraButton btTransaction;
	
	private ChangeableText txInventorySlotsUsed;
	private AuraListViewLarge lvInventory;
	private AItemGoodsData datas;
	public AItemGoodsData getDatas() {
		if (datas == null) {
			datas = new AItemGoodsData(AItemGoods.values());
		}
		return datas;
	}
	
	private AItemGoods itemFocus;
	private long qteTransaction;
	private AuraProgressBar pgItemFocus;
	private ChangeableText txItemFocus;
	private boolean modeBuy;
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
		float nextY = getTxTitre().getHeight() + 5;
		this.txInventorySlotsUsed = new ChangeableText(getMinX()+5, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		getScene().attachChild(txInventorySlotsUsed);
		nextY += txInventorySlotsUsed.getHeight() + 10;
		this.lvInventory = new AuraListViewLarge(getEngine(), getMinX()+5, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public String objectToString(Object o) {
				AItemGoods i = (AItemGoods) o;
				AuraItemInfo nf = getInfo().getByItem(i);
				AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
				String msg = AuraI18nChooser.GUI_VENDOR_ROWDATA_ITEM.getMessage(getEngine(), 
					i.getLibelle(), 
					""+nf.getQuantity(),
					""+inv.getItemQuantity(i),
					""+nf.getSellPrice(),
					""+nf.getBuyPrice());
				return msg;
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
			@Override public void onTouchElement(Object o) {
				pgItemFocus.detach();
				btTransaction.detach();
				btBuy.detach();
				btSell.detach();
				getScene().detachChild(txItemFocus);
				AItemGoods i = (AItemGoods) o;
				if (i != null) {
					itemFocus = i;
					btBuy.attach();
					btSell.attach();
				}
			}
		};
		lvInventory.attach(getScene());
		this.pgItemFocus = new AuraProgressBar(getEngine(), getMinX(), 340) {
			@Override
			public void onValueChange(float value) {
				long qteMaxToBuy = getInfo().getByItem(itemFocus).getQuantity();
				long qteToBuy = new BigDecimal(qteMaxToBuy*getValueRounded())
					.setScale(0, RoundingMode.HALF_EVEN).longValue();
				long qteMaxToSell = getAuraPreferences().getCurrentSaveGame().getInventory().getItemQuantity(itemFocus);
				long qteToSell = new BigDecimal(qteMaxToSell*getValueRounded())
					.setScale(0, RoundingMode.HALF_EVEN).longValue();
				qteTransaction = (modeBuy ? qteToBuy : qteToSell);
				long price = modeBuy ? 
					qteTransaction * getInfo().getByItem(itemFocus).getSellPrice():
					(qteMaxToSell - qteTransaction) * getInfo().getByItem(itemFocus).getBuyPrice();
				txItemFocus.setText(itemFocus.getLibelle()+" x "+qteTransaction +" = "+price);
				txItemFocus.setPosition(getMaxX()-txItemFocus.getWidth()-64, 340);
			}
		};
		this.txItemFocus = new ChangeableText(0, 0, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_BIG.getFont()), 
			"", HorizontalAlign.LEFT, 100);
		this.btMilitaryWeapon = new AuraButton(getEngine(), getScene(), getMaxX(), 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getMilitaryWeaponScene(getCorpOwner()), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VENDOR_BT_MILITARY_WEAPON.getMessage(getEngine());
			}
		};
		btMilitaryWeapon.attach();
		this.btMilitaryArmor = new AuraButton(getEngine(), getScene(), getMaxX(), 100) {
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
		btMilitaryArmor.attach();
		this.btTransaction = new AuraButton(getEngine(), getScene(), getMaxX(), 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				AuraItemInfo itemInfo = getInfo().getByItem(itemFocus);
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				AuraInventory inv = sg.getInventory();
				if (modeBuy) { // ACHAT
					long price = qteTransaction * itemInfo.getSellPrice();
					if (inv.getSlotsLeft() - qteTransaction < 0) {
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_NOT_ENOUGH_SLOTS.getMessage(getEngine()), 
							getScene()));
						return true;
					}
					if (price > inv.getCredits()) {
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_NOT_ENOUGH_CREDITS.getMessage(getEngine()), 
							getScene()));
						return true;
					}
					long newVendorQte = itemInfo.getQuantity()-qteTransaction;
					itemInfo.setQuantity(newVendorQte);
					long newInvQte = inv.getItemQuantity(itemFocus)+qteTransaction;
					inv.setCredits(inv.getCredits()-price);
					inv.getItems().put(itemFocus.getId(), newInvQte);
				} else { // VENTE
					qteTransaction = inv.getItemQuantity(itemFocus) - qteTransaction; 
					long price = qteTransaction * itemInfo.getBuyPrice();
					long newInvQte = inv.getItemQuantity(itemFocus)-qteTransaction;
					inv.setCredits(inv.getCredits()+price);
					inv.getItems().put(itemFocus.getId(), newInvQte);
				}
				
				// END
				txInventorySlotsUsed.setText(
					AuraI18nChooser.GUI_INVENTORY_SLOT_USED.getMessage(getEngine(), 
						""+inv.getSlotsUsed(), ""+inv.getMaxSlots())
					+ ", "+ AuraI18nChooser.GUI_VENDOR_CREDITS_LEFT.getMessage(getEngine(), 
						""+inv.getCredits()));
				lvInventory.print(1);
				btTransaction.detach();
				pgItemFocus.detach();
				getScene().detachChild(txItemFocus);
				itemFocus = null;
				
				getAuraPreferences().getCurrentSaveGame().save();
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VENDOR_BT_TRANSACTION.getMessage(getEngine());
			}
		};
		this.btBuy = new AuraButton(getEngine(), getScene(), getMaxX(), 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				modeBuy = true;
				btBuy.detach();
				btSell.detach();
				btTransaction.attach();
				getScene().attachChild(txItemFocus);
				pgItemFocus.attach(getScene());
				pgItemFocus.setValue(0);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VENDOR_BT_BUY.getMessage(getEngine());
			}
		};
		this.btSell = new AuraButton(getEngine(), getScene(), getMaxX(), 260) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				modeBuy = false;
				btBuy.detach();
				btSell.detach();
				btTransaction.attach();
				getScene().attachChild(txItemFocus);
				pgItemFocus.attach(getScene());
				pgItemFocus.setValue(1);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VENDOR_BT_SELL.getMessage(getEngine());
			}
		};
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		getScene().detachChild(txItemFocus);
		pgItemFocus.detach();
		btTransaction.detach();
		btBuy.detach();
		btSell.detach();
		itemFocus = null;
		AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
		txInventorySlotsUsed.setText(
			AuraI18nChooser.GUI_INVENTORY_SLOT_USED.getMessage(getEngine(), 
				""+inv.getSlotsUsed(), ""+inv.getMaxSlots())
			+ ", "+ AuraI18nChooser.GUI_VENDOR_CREDITS_LEFT.getMessage(getEngine(), 
				""+inv.getCredits()));
		lvInventory.print(1);
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txInventorySlotsUsed);
		getScene().detachChild(txItemFocus);
		lvInventory.detach();
		pgItemFocus.detach();
		btMilitaryWeapon.detach();
		btMilitaryArmor.detach();
		btTransaction.detach();
		btBuy.detach();
		btSell.detach();
		super.onRemove();
	}
	
	public class AItemGoodsData extends VPageData<AItemGoods> {
		public AItemGoodsData(AItemGoods[] datas) {
			super(datas, 5);
		}
	}
}