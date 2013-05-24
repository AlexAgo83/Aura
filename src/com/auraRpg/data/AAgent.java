package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.ressources.VTileTexture;

public enum AAgent {
	// PLANETE A
	M_PA_F1_C1(1, "Murdock", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F1_C1, new AMap[0]),
	T_PA_F1_C1(21, "Calvin", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		15.0f, 1.0f, ACorporation.CORPORATION_F1_C1, new AMap[] {AMap.PLANETE_B, AMap.PLANETE_C, AMap.PLANETE_D}),
	D_PA_F1_C1(30, "MEDIC Class IV", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135f, 1.0f, ACorporation.CORPORATION_F1_C1, new AMap[0]),
	V_PA_F1_C1(39, "Junk", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F1_C1, new AMap[0]),
		
	// PLANETE B
	M_PB_F3_C2(2, "Brice", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F3_C2, new AMap[0]),
	M_PB_F3_C3(3, "Kenneth", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		90.0f, 1.0f, ACorporation.CORPORATION_F3_C3, new AMap[0]),
	T_PB_F3_C2(22, "Andrew", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F3_C2, new AMap[] {AMap.PLANETE_A, AMap.PLANETE_C, AMap.PLANETE_E}),
	D_PB_F3_C2(31, "Azrim", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F3_C2, new AMap[0]),
	V_PB_F3_C2(40, "Testuo", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F3_C2, new AMap[0]),
		
	// PLANETE C
	M_PC_F1_C2(4, "Maxime", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F1_C2, new AMap[0]),
	T_PC_F1_C2(23, "Jean", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F1_C2, new AMap[] {AMap.PLANETE_A, AMap.PLANETE_B, AMap.PLANETE_F}),
	D_PC_F1_C2(32, "MEDIC Trooper", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F1_C2, new AMap[0]),
	V_PC_F1_C2(41, "Orochi", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F1_C2, new AMap[0]),
		
	// PLANETE D
	M_PD_F2_C3(5, "Mike", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F2_C3, new AMap[0]),
	T_PD_F2_C3(24, "Peter", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F2_C3, new AMap[] {AMap.PLANETE_A, AMap.PLANETE_E, AMap.PLANETE_G}),
	D_PD_F2_C3(33, "Dr. Alphonso", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F2_C3, new AMap[0]),
	V_PD_F1_C2(42, "Eskro", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F2_C3, new AMap[0]),
		
	// PLANETE E
	M_PE_F3_C1(6, "Olivia", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
	M_PE_F3_C2(7, "Roger", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		90.0f, 1.0f, ACorporation.CORPORATION_F3_C2, new AMap[0]),
	T_PE_F3_C1(25, "John", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[] {AMap.PLANETE_B, AMap.PLANETE_D, AMap.PLANETE_F, AMap.PLANETE_H}),
	D_PE_F3_C1(34, "Bravis", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
	V_PE_F3_C1(43, "Jar Banks", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
		
	// PLANETE F
	M_PF_F1_C3(8, "Franck", AuraTileTextureChooser.ENTITY_PLAYER_BLEU.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F1_C3, new AMap[0]),
	T_PF_F1_C3(26, "Rui", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F1_C3, new AMap[] {AMap.PLANETE_C, AMap.PLANETE_E, AMap.PLANETE_I}),
	D_PF_F1_C3(35, "MEDIC Class III", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F1_C3, new AMap[0]),
	V_PF_F1_C3(44, "Foley", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F1_C3, new AMap[0]),
	
	// PLANETE G
	M_PG_F2_C1(9, "Ben", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F2_C1, new AMap[0]),
	T_PG_F2_C1(27, "Judith", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F2_C1, new AMap[] {AMap.PLANETE_D, AMap.PLANETE_H, AMap.PLANETE_I}),
	D_PG_F2_C1(36, "Dr. Anto", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F2_C1, new AMap[0]),
	V_PG_F2_C1(45, "Hicks", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F2_C1, new AMap[0]),
	
	// PLANETE H
	M_PH_F3_C1(10, "Maxwell", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
	M_PH_F3_C3(11, "Dylan", AuraTileTextureChooser.ENTITY_PLAYER_JAUNE.getTileTexture(), AAgentType.MISSION_AGENCY,
		90.0f, 1.0f, ACorporation.CORPORATION_F3_C3, new AMap[0]),
	T_PH_F3_C1(28, "Cho Li", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[] {AMap.PLANETE_E, AMap.PLANETE_G, AMap.PLANETE_I}),
	D_PH_F3_C1(37, "Gibs", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
	V_PH_F3_C1(46, "Jason", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F3_C1, new AMap[0]),
	
	// PLANETE I
	M_PI_F2_C2(12, "Gordon", AuraTileTextureChooser.ENTITY_PLAYER_ROUGE.getTileTexture(), AAgentType.MISSION_AGENCY,
		180.0f, 1.0f, ACorporation.CORPORATION_F2_C2, new AMap[0]),
	T_PI_F2_C2(29, "Kim", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.TRAVEL_AGENCY,
		0, 1.0f, ACorporation.CORPORATION_F2_C2, new AMap[] {AMap.PLANETE_F, AMap.PLANETE_H, AMap.PLANETE_G}),
	D_PI_F2_C2(38, "Dr. Carlito", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.DOCTOR,
		135.0f, 1.0f, ACorporation.CORPORATION_F2_C2, new AMap[0]),
	V_PI_F3_C1(47, "Calwen", AuraTileTextureChooser.ENTITY_PLAYER_VERT.getTileTexture(), AAgentType.VENDOR,
		-15, 1.0f, ACorporation.CORPORATION_F2_C2, new AMap[0]);
		
	private int id;
	private String name;
	private VTileTexture skin;
	private AAgentType type;
	private float rotation;
	private float quality;
	private ACorporation corporation;
	private AMap[] mapSwitch;
	
	private AAgent(int id, String name, VTileTexture skin, AAgentType type, float rotation, float quality, ACorporation corporation, AMap[] mapSwitch) {
		this.id = id;
		this.name = name;
		this.skin = skin;
		this.type = type;
		this.rotation = rotation;
		this.quality = quality;
		this.corporation = corporation;
		this.mapSwitch = mapSwitch;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public VTileTexture getSkin() {
		return skin;
	}
	public AAgentType getType() {
		return type;
	}
	public float getRotation() {
		return rotation;
	}
	public float getQuality() {
		return quality;
	}
	public ACorporation getCorporation() {
		return corporation;
	}
	public AMap[] getMapSwitch() {
		return mapSwitch;
	}
	
	public static AAgent getById(int id) {
		for (AAgent a: AAgent.values()) {
			if (id == a.getId())
				return a;
		}
		return null;
	}
	public static AAgent[] getByType(AAgentType type) {
		List<AAgent> as = new ArrayList<AAgent>();
		for (AAgent a: AAgent.values()) {
			if (a.getType() == type)
				as.add(a);
		}
		return as.toArray(new AAgent[0]);
	}
}