package com.auraRpg.saveGame;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.auraRpg.data.AItemGoods;
import com.voidEngine.engine.VAbstractEngine;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AuraItemInfo {
	private long quantity;
	public static final String PROP_QUANTITY = "qte";
	
	private long sellPrice;
	public static final String PROP_SELL_PRICE = "sellPrice";
	
	private long buyPrice;
	public static final String PROP_BUY_PRICE = "buyPrice";
	
	protected AuraItemInfo() {}
	
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	public long getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(long sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public long getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(long buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public void load(SharedPreferences p, String baseQuery) {
		setQuantity(p.getLong(baseQuery+PROP_QUANTITY, 0));
		setSellPrice(p.getLong(baseQuery+PROP_SELL_PRICE, 0));
		setBuyPrice(p.getLong(baseQuery+PROP_BUY_PRICE, 0));
	}
	public void save(Editor e, String baseQuery) {
		e.putLong(baseQuery+PROP_QUANTITY, getQuantity());
		e.putLong(baseQuery+PROP_SELL_PRICE, getSellPrice());
		e.putLong(baseQuery+PROP_BUY_PRICE, getBuyPrice());
	}
	public void delete(Editor e, String baseQuery) {
		e.remove(baseQuery+PROP_QUANTITY);
		e.remove(baseQuery+PROP_SELL_PRICE);
		e.remove(baseQuery+PROP_BUY_PRICE);
	}

	public void generer(VAbstractEngine engine, AItemGoods it) {
		AuraSaveGame sg = ((AuraPreferences) engine.getVPreferences()).getCurrentSaveGame();
		setQuantity(10 + 
			new BigDecimal(Math.random()*100).setScale(0, RoundingMode.DOWN).longValue());
		setSellPrice(new BigDecimal((it.getMaxSellPrice()-it.getMinSellPrice())*Math.random())
			.setScale(0, RoundingMode.DOWN).longValue()
			+ it.getMinSellPrice());
		setSellPrice(getSellPrice()-new BigDecimal(getSellPrice()*sg.getSkills().getNegociationFactor()).setScale(0, RoundingMode.DOWN).longValue());
		setBuyPrice(new BigDecimal((it.getMaxBuyPrice()-it.getMinBuyPrice())*Math.random())
			.setScale(0, RoundingMode.DOWN).longValue()
			+ it.getMinBuyPrice());
		setBuyPrice(getBuyPrice()+new BigDecimal(getBuyPrice()*sg.getSkills().getNegociationFactor()).setScale(0, RoundingMode.DOWN).longValue());
		if (getSellPrice() < getBuyPrice())
			setSellPrice(getBuyPrice());
	}
}
