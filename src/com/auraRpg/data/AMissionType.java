package com.auraRpg.data;

import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;

public enum AMissionType {
	DELIVERY(1, AuraI18nChooser.MISSION_TYPE_DELIVERY, AuraTextureChooser.GUI_PUCE_MISSION_DELIVERY.getTexture()),
	BOUNTY(2, AuraI18nChooser.MISSION_TYPE_BOUNTY, AuraTextureChooser.GUI_PUCE_MISSION_BOUNTY.getTexture());
	
	private int id;
	private AuraI18nChooser i18nId;
	private VTexture logo;
	
	private AMissionType(int id, AuraI18nChooser i18nId, VTexture logo) {
		this.id = id;
		this.i18nId = i18nId;
		this.logo = logo;
	}
	
	public int getId() {
		return id;
	}
	private AuraI18nChooser getI18nId() {
		return i18nId;
	}
	public String getLibelle(VAbstractEngine engine) {
		return getI18nId().getMessage(engine);
	}
	public VTexture getLogo() {
		return logo;
	}
	
	public static AMissionType getById(int id) {
		for (AMissionType a: AMissionType.values()) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}
	public static AMissionType getRandom() {
		return AMissionType.values()[(int) Math.round((AMissionType.values().length-1)*Math.random())];
	}
}