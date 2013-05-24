package com.auraRpg.data;

import com.auraRpg.ressources.AuraTextureChooser;
import com.voidEngine.engine.ressources.VTexture;

public enum AFaction {
	FACTION_1(1, "UFI", "Union for Innovation", "blabla", AuraTextureChooser.GUI_PUCE_FLAG_F1.getTexture()),
	FACTION_2(2, "MP", "Mankind Project", "blabla", AuraTextureChooser.GUI_PUCE_FLAG_F2.getTexture()),
	FACTION_3(3, "IND", "Independantiste", "blabla", AuraTextureChooser.GUI_PUCE_FLAG_F3.getTexture());
	
	private int id;
	private String tag;
	private String name;
	private String description;
	private VTexture flag;
	
	private AFaction(int id, String tag, String name, String description, VTexture flag) {
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.description = description;
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
	public VTexture getFlag() {
		return flag;
	}
	
	public static AFaction getById(int id) {
		for (AFaction f: AFaction.values()) {
			if (f.getId() == id)
				return f;
		}
		return null;
	}
}