package com.auraRpg.data;

public enum ASkills {
	ACCURENCY(1, "Accurency"),
	STEALTH(2, "Stealth"),
	NEGOCIATION(3, "Negociation"),
	INTIMIDATION(4, "Intimidation"),
	STAMINA(5, "Stamina"),
	EXPLORATION(6, "Exploration");
	
	private int id;
	private String libelle;
	
	private ASkills(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	
	public int getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	
	public static ASkills getById(int id) {
		for (ASkills s: ASkills.values()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
}
