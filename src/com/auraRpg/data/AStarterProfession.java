package com.auraRpg.data;

import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.ressources.VTexture;

public enum AStarterProfession {
	SOLDAT(1, "Soldat", AuraTextureChooser.GUI_PUCE_PROF_SOLDAT.getTexture()),
	TRADER(2, "Trader", AuraTextureChooser.GUI_PUCE_PROF_TRADER.getTexture()),
	PIRATE(3, "Pirate", AuraTextureChooser.GUI_PUCE_PROF_PIRATE.getTexture()),
	ADVENTURER(4, "Adventurer", AuraTextureChooser.GUI_PUCE_PROF_MERCENARY.getTexture());
	
	private int id;
	private String libelle;
	private VTexture logo;
	
	private AStarterProfession(int id, String libelle, VTexture logo) {
		this.id = id;
		this.libelle = libelle;
		this.logo = logo;
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
	
	public static AStarterProfession getById(int id) {
		for (AStarterProfession sp: AStarterProfession.values()) {
			if (sp.getId() == id)
				return sp;
		}
		return null;
	}
}