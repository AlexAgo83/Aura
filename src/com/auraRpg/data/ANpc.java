package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.ressources.VTileTexture;

public enum ANpc {
	// ALIEN
	GROTH(1, "Groth", AuraTileTextureChooser.ENTITY_ALIEN_VERT.getTileTexture(), 3, AItemWeapon.CLAW, null),
	GROTH_SWAMP(2, "Groth Swamp", AuraTileTextureChooser.ENTITY_ALIEN_JAUNE.getTileTexture(), 4, AItemWeapon.CLAW, null),
	GROTH_ELITE(3, "Groth Elite", AuraTileTextureChooser.ENTITY_ALIEN_BLEU.getTileTexture(), 5, AItemWeapon.CLAW, null),
	GROTH_SLAYER(4, "Groth Slayer", AuraTileTextureChooser.ENTITY_ALIEN_ROUGE.getTileTexture(), 6, AItemWeapon.CLAW, null),
	
	// PIRATE
	NPC_F1_C1(5, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F1_C1),
	NPC_F1_C2(6, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F1_C2),
	NPC_F1_C3(7, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F1_C3),
	NPC_F2_C1(8, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F2_C1),
	NPC_F2_C2(9, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F2_C2),
	NPC_F2_C3(10, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F2_C3),
	NPC_F3_C1(11, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F3_C1),
	NPC_F3_C2(12, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F3_C2),
	NPC_F3_C3(13, "Pirate", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), 5, AItemWeapon.PIST_1, ACorporation.CORPORATION_F3_C3);

	private int id;
	private String name;
	private VTileTexture skin;
	private int maxHealth;
	private AItemWeapon weapon;
	private ACorporation corporation;
	
	private ANpc(int id, String name, VTileTexture skin, int maxHealth, AItemWeapon weapon, ACorporation corporation) {
		this.id = id;
		this.name = name;
		this.skin = skin;
		this.maxHealth = maxHealth;
		this.weapon = weapon;
		this.corporation = corporation;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		if (getCorporation() != null)
			return name + " - " + getCorporation().getTag();
		return name;
	}
	public VTileTexture getSkin() {
		return skin;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public AItemWeapon getWeapon() {
		return weapon;
	}
	public ACorporation getCorporation() {
		return corporation;
	}
	
	public static ANpc getById(int id) {
		for (ANpc n: ANpc.values()) {
			if (n.getId() == id)
				return n;
		}
		return null;
	}
	public static ANpc[] getByCorporation(ACorporation c) {
		List<ANpc> ls = new ArrayList<ANpc>();
		for (ANpc npc: ANpc.values()) {
			if (npc.getCorporation() == c)
				ls.add(npc);
		}
		return ls.toArray(new ANpc[0]);
	}
	public static ANpc getRandom() {
		return ANpc.values()[(int) Math.round((ANpc.values().length-1)*Math.random())];
	}
	
	public static ANpc[] getAllPirate() {
		List<ANpc> npcs = new ArrayList<ANpc>();
		for (ANpc n: ANpc.values()) {
			if (n.name.equals("Pirate")) {
				npcs.add(n);
			}
		}
		return npcs.toArray(new ANpc[0]);
	}
}