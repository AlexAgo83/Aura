package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.ressources.VTileTexture;

public enum AItemArmor {
	BASIC(	1, false, 0.0f, "Leather Jacket", 0, 0, 
		AuraTileTextureChooser.ENTITY_PLAYER_GRIS.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_1.getTexture()),
	XSLIM(	2,  true, 0.1f, "X-Slim", 1, 10000, 
		AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_2.getTexture()),
	ZZSLIM(	3,  true, 0.15f, "Z-Slim", 2, 15000, 
		AuraTileTextureChooser.ENTITY_PLAYER_GRIS_B.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_3.getTexture()),
	RAPAC1(	4,  true, 0.2f, "Rapace I", 3, 18000, 
		AuraTileTextureChooser.ENTITY_PLAYER_GRIS_C.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_4.getTexture()),
	RAPAC2(	5,  true, 0.25f, "Rapace II", 5, 22000, 
		AuraTileTextureChooser.ENTITY_PLAYER_GRIS_D.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_5.getTexture()),
	TIGER(	6,  true, 0.3f, "Tigre", 6, 28000, 
		AuraTileTextureChooser.ENTITY_PLAYER_GRIS_E.getTileTexture(), 
		AuraTextureChooser.GUI_PUCE_ARMOR_6.getTexture());
	
	private int id;
	private boolean buyable;
	private float resistanceModifier;
	private String libelle;
	private int rank;
	private long basePrice;
	private VTileTexture skin;
	private VTexture logo;
	
	private AItemArmor(int id, boolean buyable, float resistanceModifier, String libelle, int rank, 
		long basePrice, VTileTexture skin, VTexture logo) {
		this.id = id;
		this.buyable = buyable;
		this.resistanceModifier = resistanceModifier;
		this.libelle = libelle;
		this.rank = rank;
		this.basePrice = basePrice;
		this.skin = skin;
		this.logo = logo;
	}
	
	public int getId() {
		return id;
	}
	public boolean isBuyable() {
		return buyable;
	}
	public float getResistanceModifier() {
		return resistanceModifier;
	}
	public String getLibelle() {
		return libelle;
	}
	public int getRank() {
		return rank;
	}
	public long getBasePrice() {
		return basePrice;
	}
	public String getDescription() {
		return "R:" + getResistanceModifier();
	}
	
	public VTileTexture getSkin() {
		return skin;
	}
	public VTexture getLogo() {
		return logo;
	}
	
	public static AItemArmor getById(int id) {
		for (AItemArmor a: AItemArmor.values()) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}
	
	public static AItemArmor[] getBuyable() {
		List<AItemArmor> ls = new ArrayList<AItemArmor>();
		for (AItemArmor a: AItemArmor.values()) {
			if (a.isBuyable())
				ls.add(a);
		}
		return ls.toArray(new AItemArmor[0]);
	}
}