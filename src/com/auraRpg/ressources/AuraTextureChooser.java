package com.auraRpg.ressources;

import com.voidEngine.engine.ressources.VTexture;

public enum AuraTextureChooser {
	GUI_MENU(1, 800, 480, 1024, 512, "textures/gui_menu.png"), //$NON-NLS-1$
	GUI_FOND_A(2, 480, 480, 512, 512, "textures/gui_fond_a.png"), //$NON-NLS-1$
	GUI_FOND_B(3, 480, 480, 512, 512, "textures/gui_fond_b.png"), //$NON-NLS-1$
	GUI_FOND_C(4, 8, 480, 8, 512, "textures/gui_fond_c.png"), //$NON-NLS-1$
	GUI_FOND_D(5, 8, 480, 8, 512, "textures/gui_fond_d.png"), //$NON-NLS-1$
	GUI_FOND_E(6, 250, 135, 256, 256, "textures/gui_fond_e.png"), //$NON-NLS-1$
	GUI_FOND_ROUND_A(7, 512, 512, "textures/gui_fond_round_a.png"), //$NON-NLS-1$
	GUI_FOND_ROUND_B(8, 470, 470, 512, 512, "textures/gui_fond_round_b.png"), //$NON-NLS-1$
	GUI_CADRE(9, 800, 480, 1024, 512, "textures/gui_cadre.png"), //$NON-NLS-1$
	GUI_GAME_OVER(10, 480, 480, 512, 512, "textures/gui_gameOver.png"), //$NON-NLS-1$
	
	GUI_ANALOG_BG(100, 128, 128, "textures/gui_analog_bg.png"), //$NON-NLS-1$
	GUI_ANALOG_KNOB(101, 64, 64, "textures/gui_analog_knob.png"), //$NON-NLS-1$
	
	GUI_PROGRESS_BAR_BASE(103, 480, 128, 512, 128, "textures/gui_progressBar_base.png"), //$NON-NLS-1$

	GUI_PUCE_SAVEGAME(200, 18, 15, 32, 16, "textures/gui_puce_saveGame.png"), //$NON-NLS-1$
	GUI_PUCE_MISSION(201, 18, 15, 32, 16, "textures/gui_puce_mission.png"), //$NON-NLS-1$
	GUI_PUCE_TRAVEL(202, 18, 15, 32, 16, "textures/gui_puce_travel.png"), //$NON-NLS-1$
	GUI_PUCE_POLITICS(203, 18, 15, 32, 16, "textures/gui_puce_politics.png"), //$NON-NLS-1$
	
	GUI_PUCE_FLAG_F1(210, 18, 15, 32, 16, "textures/gui_puce_flag_faction_1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F2(211, 18, 15, 32, 16, "textures/gui_puce_flag_faction_2.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F3(212, 18, 15, 32, 16, "textures/gui_puce_flag_faction_3.png"), //$NON-NLS-1$

	GUI_PUCE_FLAG_F1_C1(213, 18, 15, 32, 16, "textures/gui_puce_flag_f1_c1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F1_C2(214, 18, 15, 32, 16, "textures/gui_puce_flag_f1_c2.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F1_C3(215, 18, 15, 32, 16, "textures/gui_puce_flag_f1_c3.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F2_C1(216, 18, 15, 32, 16, "textures/gui_puce_flag_f2_c1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F2_C2(217, 18, 15, 32, 16, "textures/gui_puce_flag_f2_c1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F2_C3(218, 18, 15, 32, 16, "textures/gui_puce_flag_f2_c1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F3_C1(219, 18, 15, 32, 16, "textures/gui_puce_flag_f3_c1.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F3_C2(220, 18, 15, 32, 16, "textures/gui_puce_flag_f3_c2.png"), //$NON-NLS-1$
	GUI_PUCE_FLAG_F3_C3(221, 18, 15, 32, 16, "textures/gui_puce_flag_f3_c3.png"), //$NON-NLS-1$
	
	GUI_PUCE_MISSION_BOUNTY(222, 18, 15, 32, 16, "textures/gui_puce_mission_bounty.png"), //$NON-NLS-1$
	GUI_PUCE_MISSION_DELIVERY(223, 18, 15, 32, 16, "textures/gui_puce_mission_delivery.png"), //$NON-NLS-1$
	
	GUI_PUCE_WEAPON_1(224, 32, 32, "textures/gui_puce_weapon_1.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_2(225, 32, 32, "textures/gui_puce_weapon_2.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_3(226, 32, 32, "textures/gui_puce_weapon_3.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_4(227, 32, 32, "textures/gui_puce_weapon_4.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_5(228, 32, 32, "textures/gui_puce_weapon_5.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_6(229, 32, 32, "textures/gui_puce_weapon_6.png"), //$NON-NLS-1$
	GUI_PUCE_WEAPON_7(230, 32, 32, "textures/gui_puce_weapon_7.png"), //$NON-NLS-1$
	
	GUI_PUCE_ITEM_MINERAL(231, 32, 32, "textures/gui_puce_items_mineral.png"), //$NON-NLS-1$
	GUI_PUCE_ITEM_WATER(232, 32, 32, "textures/gui_puce_items_water.png"), //$NON-NLS-1$
	GUI_PUCE_ITEM_SPICE(233, 32, 32, "textures/gui_puce_items_spice.png"), //$NON-NLS-1$
	GUI_PUCE_ITEM_METAL(234, 32, 32, "textures/gui_puce_items_metal.png"), //$NON-NLS-1$
	GUI_PUCE_ITEM_WEAPON(235, 32, 32, "textures/gui_puce_items_weapon.png"), //$NON-NLS-1$
	
	GUI_PUCE_PROF_TRADER(236, 32, 32, "textures/gui_puce_prof_trader.png"), //$NON-NLS-1$
	GUI_PUCE_PROF_SOLDAT(237, 32, 32, "textures/gui_puce_prof_soldat.png"), //$NON-NLS-1$
	GUI_PUCE_PROF_MERCENARY(238, 32, 32, "textures/gui_puce_prof_mercenary.png"), //$NON-NLS-1$
	GUI_PUCE_PROF_PIRATE(239, 32, 32, "textures/gui_puce_prof_pirate.png"), //$NON-NLS-1$
	
	GUI_PUCE_ARMOR_1(240, 32, 32, "textures/gui_puce_armor_1.png"), //$NON-NLS-1$
	GUI_PUCE_ARMOR_2(241, 32, 32, "textures/gui_puce_armor_2.png"), //$NON-NLS-1$
	GUI_PUCE_ARMOR_3(242, 32, 32, "textures/gui_puce_armor_3.png"), //$NON-NLS-1$
	GUI_PUCE_ARMOR_4(243, 32, 32, "textures/gui_puce_armor_4.png"), //$NON-NLS-1$
	GUI_PUCE_ARMOR_5(244, 32, 32, "textures/gui_puce_armor_5.png"), //$NON-NLS-1$
	GUI_PUCE_ARMOR_6(245, 32, 32, "textures/gui_puce_armor_6.png"), //$NON-NLS-1$
	
	PARTICULE_SMOKE(800, 64, 64, "textures/particle_smoke.png"), //$NON-NLS-1$

	MAP_FOND_A(900, 512, 512, "textures/map_fond_a.png"), //$NON-NLS-1$
	MAP_FOND_B(901, 512, 512, "textures/map_fond_b.png"), //$NON-NLS-1$
	MAP_FOND_C(902, 512, 512, "textures/map_fond_c.png"), //$NON-NLS-1$
	MAP_FOND_D(903, 512, 512, "textures/map_fond_d.png"), //$NON-NLS-1$
	MAP_FOND_E(904, 512, 512, "textures/map_fond_e.png"), //$NON-NLS-1$
	MAP_FOND_F(905, 512, 512, "textures/map_fond_f.png"), //$NON-NLS-1$
	MAP_FOND_X(906, 512, 512, "textures/map_fond_x.png"), //$NON-NLS-1$
	
	MAP_TILE_G0(1000, "textures/mapTile_g0.png", false), //$NON-NLS-1$
	MAP_TILE_G1(1001, "textures/mapTile_g1.png", false), //$NON-NLS-1$
	MAP_TILE_G2(1002, "textures/mapTile_g2.png", false), //$NON-NLS-1$
	MAP_TILE_G3(1003, "textures/mapTile_g3.png", false), //$NON-NLS-1$
	MAP_TILE_G4E(1004, "textures/mapTile_g4E.png", false), //$NON-NLS-1$
	MAP_TILE_G4N(1005, "textures/mapTile_g4N.png", false), //$NON-NLS-1$
	MAP_TILE_G4O(1006, "textures/mapTile_g4O.png", false), //$NON-NLS-1$
	MAP_TILE_G4S(1007, "textures/mapTile_g4S.png", false), //$NON-NLS-1$
	MAP_TILE_G5(1008, "textures/mapTile_g5.png", false), //$NON-NLS-1$
	MAP_TILE_W0(1009, "textures/mapTile_w0.png", true), //$NON-NLS-1$
	MAP_TILE_W1(1010, "textures/mapTile_w1.png", true), //$NON-NLS-1$
	MAP_TILE_S1(1011, "textures/mapTile_s1.png", false), //$NON-NLS-1$
	MAP_TILE_G10(1012, "textures/mapTile_g10.png", false), //$NON-NLS-1$
	MAP_TILE_G11(1013, "textures/mapTile_g11.png", false), //$NON-NLS-1$
	MAP_TILE_WB0(1014, "textures/mapTile_wb0.png", true), //$NON-NLS-1$
	MAP_TILE_WB1(1015, "textures/mapTile_wb1.png", true), //$NON-NLS-1$
	MAP_TILE_WB2(1016, "textures/mapTile_wb2.png", true), //$NON-NLS-1$
	MAP_TILE_WP0(1017, "textures/mapTile_wp0.png", false), //$NON-NLS-1$
	MAP_TILE_WP1(1018, "textures/mapTile_wp1.png", false), //$NON-NLS-1$
	MAP_TILE_G10A(1019, "textures/mapTile_g10a.png", false), //$NON-NLS-1$
	MAP_TILE_BOX(1020, "textures/mapTile_box.png", false), //$NON-NLS-1$
	MAP_TILE_G11A(1021, "textures/mapTile_g11a.png", false), //$NON-NLS-1$
	MAP_TILE_G10B(1022, "textures/mapTile_g10b.png", false), //$NON-NLS-1$
	MAP_TILE_G10C(1023, "textures/mapTile_g10c.png", false), //$NON-NLS-1$
	MAP_TILE_G10D(1024, "textures/mapTile_g10d.png", false), //$NON-NLS-1$
	
	// ID MAP à respecter, très important MAP_ICONE_A = PLANETE_A, etc.
	MAP_ICONE_A(1101, "textures/map_icone_a.png", false), //$NON-NLS-1$
	MAP_ICONE_B(1102, "textures/map_icone_b.png", false), //$NON-NLS-1$
	MAP_ICONE_C(1103, "textures/map_icone_c.png", false), //$NON-NLS-1$
	MAP_ICONE_D(1104, "textures/map_icone_d.png", false), //$NON-NLS-1$
	MAP_ICONE_E(1105, "textures/map_icone_e.png", false), //$NON-NLS-1$
	MAP_ICONE_F(1106, "textures/map_icone_f.png", false), //$NON-NLS-1$
	MAP_ICONE_G(1107, "textures/map_icone_g.png", false), //$NON-NLS-1$
	MAP_ICONE_H(1108, "textures/map_icone_h.png", false), //$NON-NLS-1$
	MAP_ICONE_I(1109, "textures/map_icone_i.png", false); //$NON-NLS-1$
	
	private int id;
	private int width;
	private int height;
	private int bitmapWidth;
	private int bitmapHeight;
	private int xPosition;
	private int yPosition;
	private String fileName;
	private boolean solid;
	
	// TILE ONLY
	private AuraTextureChooser(
			int id, String fileName, boolean solid) {
		this(id, 64, 64, 64, 64, 0, 0, fileName, solid);
	}
	
	// NON TILE
	private AuraTextureChooser(
			int id, int width, int height, 
			String fileName) {
		this(id, width, height, width, height, 0, 0, fileName, false);
	}
	
	// NON TILE DERIVE
	private AuraTextureChooser(
			int id, int width, int height, 
			int bitmapWidth, int bitmapHeight, 
			String fileName) {
		this(id, width, height, bitmapWidth, bitmapHeight, 0, 0, fileName, false);
	}
	
	// GENERIC
	private AuraTextureChooser(
			int id, int width, int height, 
			int bitmapWidth, int bitmapHeight,
			int xPosition, int yPosition, 
			String fileName, boolean solid) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.bitmapWidth = bitmapWidth;
		this.bitmapHeight = bitmapHeight;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.fileName = fileName;
		this.solid = solid;
	}

	public int getId() {
		return id;
	}
	public boolean isSolid() {
		return solid;
	}
	
	private VTexture texture;
	public VTexture getTexture() {
		if (texture == null) {
			texture = new VTexture(
				id, width, height,
				bitmapWidth, bitmapHeight,
				xPosition, yPosition, fileName);
		}
		return texture;
	}
	
	public static AuraTextureChooser getById(int id) {
		for (AuraTextureChooser a: AuraTextureChooser.values()) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}
}