package com.auraRpg.data;

import com.auraRpg.ressources.AuraI18nChooser;
import com.voidEngine.engine.VAbstractEngine;

public enum AAgentType {
	VENDOR(1, AuraI18nChooser.AGENT_TYPE_VENDOR),			
	MISSION_AGENCY(2, AuraI18nChooser.AGENT_TYPE_MISSION_AGENCY),
	TRAVEL_AGENCY(3, AuraI18nChooser.AGENT_TYPE_TRAVEL_AGENCY),
	LIFT_MASTER(4, AuraI18nChooser.AGENT_TYPE_LIFT_MASTER),
	DOCTOR(5, AuraI18nChooser.AGENT_TYPE_DOCTOR);
	
	private int id;
	private AuraI18nChooser i18nId;
	
	private AAgentType(int id, AuraI18nChooser i18nId) {
		this.id = id;
		this.i18nId = i18nId;
	}
	
	public int getId() {
		return id;
	}
	public AuraI18nChooser getI18nId() {
		return i18nId;
	}
	public String getLibelle(VAbstractEngine engine) {
		return getI18nId().getMessage(engine);
	}
}