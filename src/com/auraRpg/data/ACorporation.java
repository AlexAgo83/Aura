package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.ressources.VTexture;

public enum ACorporation {
	CORPORATION_F1_C1(1, "FET", "Federal EK-Tronic", "blabla", AFaction.FACTION_1, AuraTextureChooser.GUI_PUCE_FLAG_F1_C1.getTexture()),
	CORPORATION_F1_C2(2, "IC", "Imperial Circle", "blabla", AFaction.FACTION_1, AuraTextureChooser.GUI_PUCE_FLAG_F1_C2.getTexture()),
	CORPORATION_F1_C3(3, "IC", "Internal Security", "blabla", AFaction.FACTION_1, AuraTextureChooser.GUI_PUCE_FLAG_F1_C3.getTexture()),
	
	CORPORATION_F2_C1(4, "CU", "Cyber Univ.", "blabla", AFaction.FACTION_2, AuraTextureChooser.GUI_PUCE_FLAG_F2_C1.getTexture()),
	CORPORATION_F2_C2(5, "ER", "Edge Raiders", "blabla", AFaction.FACTION_2, AuraTextureChooser.GUI_PUCE_FLAG_F2_C2.getTexture()),
	CORPORATION_F2_C3(6, "HK", "House of Knowledge", "blabla", AFaction.FACTION_2, AuraTextureChooser.GUI_PUCE_FLAG_F2_C3.getTexture()),
	
	CORPORATION_F3_C1(7, "DCM", "Deep Core Mining", "blabla", AFaction.FACTION_3, AuraTextureChooser.GUI_PUCE_FLAG_F3_C1.getTexture()),
	CORPORATION_F3_C2(8, "SC", "Space Cowboys", "blabla", AFaction.FACTION_3, AuraTextureChooser.GUI_PUCE_FLAG_F3_C2.getTexture()),
	CORPORATION_F3_C3(9, "ED", "Expert Distrib.", "blabla", AFaction.FACTION_3, AuraTextureChooser.GUI_PUCE_FLAG_F3_C3.getTexture());
	
	private int id;
	private String tag;
	private String name;
	private String description;
	private AFaction faction;
	private VTexture flag;
	
	private ACorporation(int id, String tag, String name, String description, AFaction faction, VTexture flag) {
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.description = description;
		this.faction = faction;
		this.flag = flag;
	}
	
	public int getId() {
		return id;
	}
	public String getTag() {
		return tag;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public AFaction getFaction() {
		return faction;
	}
	public VTexture getFlag() {
		return flag;
	}
	
	public static ACorporation getById(int id) {
		for (ACorporation c: ACorporation.values()) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}
	public static ACorporation[] getTousSauf(AFaction f) {
		List<ACorporation> l = new ArrayList<ACorporation>();
		for (ACorporation c: ACorporation.values()) {
			if (!c.getFaction().equals(f))
				l.add(c);
		}
		return l.toArray(new ACorporation[0]);
	}
	public static ACorporation getRandom() {
		return ACorporation.values()[(int) Math.round((ACorporation.values().length-1)*Math.random())];
	}
	public static ACorporation getRandomTousSauf(AFaction f) {
		ACorporation[] l = getTousSauf(f);
		return l[(int) Math.round((l.length-1)*Math.random())];
	}
}