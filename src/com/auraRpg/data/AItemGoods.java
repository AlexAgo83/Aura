package com.auraRpg.data;

import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.ressources.VTexture;

public enum AItemGoods {
	MINERALS(1, "Minerals", AuraTextureChooser.GUI_PUCE_ITEM_MINERAL.getTexture(), 30, 60, 20, 50),
	WATER(2, "Water", AuraTextureChooser.GUI_PUCE_ITEM_WATER.getTexture(), 20, 40, 10, 30),
	SPICE(3, "Spice", AuraTextureChooser.GUI_PUCE_ITEM_SPICE.getTexture(), 15, 25, 10, 20),
	METALS(4, "Metals", AuraTextureChooser.GUI_PUCE_ITEM_METAL.getTexture(), 80, 150, 60, 100),
	WEAPONS(5, "Weapons", AuraTextureChooser.GUI_PUCE_ITEM_WEAPON.getTexture(), 120, 250, 100, 200);
	
	private int id;
	private String libelle;
	private VTexture logo;
	private long minSellPrice;
	private long maxSellPrice;
	private long minBuyPrice;
	private long maxBuyPrice;
	
	private AItemGoods(int id, String libelle, VTexture logo, long minSellPrice, long maxSellPrice, long minBuyPrice, long maxBuyPrice) {
		this.id = id;
		this.libelle = libelle;
		this.logo = logo;
		this.minSellPrice = minSellPrice;
		this.maxSellPrice = maxSellPrice;
		this.minBuyPrice = minBuyPrice;
		this.maxBuyPrice = maxBuyPrice;
	}
	
	public int getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	public VTexture getLogo() {
		return logo;
	}
	
	public long getMinSellPrice() {
		return minSellPrice;
	}
	public long getMaxSellPrice() {
		return maxSellPrice;
	}
	
	public long getMinBuyPrice() {
		return minBuyPrice;
	}
	public long getMaxBuyPrice() {
		return maxBuyPrice;
	}
	
	public static AItemGoods getById(int id) {
		for (AItemGoods i: AItemGoods.values()) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}
	public static AItemGoods getRandom() {
		return AItemGoods.values()[(int) Math.round((AItemGoods.values().length-1)*Math.random())];
	}
}