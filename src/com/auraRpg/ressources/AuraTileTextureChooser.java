package com.auraRpg.ressources;

import com.voidEngine.engine.ressources.VTileTexture;


public enum AuraTileTextureChooser {
	GUI_SPLASH_SCREEN(1, 800, 480, 1024, 512, false, 0, 0, 1, 1, 0, false, 0, "textures/gui_splashScreen.png"), //$NON-NLS-1$
	GUI_BUTTON(2, 160, 60, 256, 64, true, 0, 0, 1, 1, 0, false, 0, "textures/gui_button.png"), //$NON-NLS-1$
	GUI_PREV(3, 32, 32, 32, 32, true, 0, 0, 1, 1, 0, false, 0, "textures/gui_prev.png"), //$NON-NLS-1$
	GUI_NEXT(4, 32, 32, 32, 32, true, 0, 0, 1, 1, 0, false, 0, "textures/gui_next.png"), //$NON-NLS-1$
	GUI_KEY_BG(5, 64, 64, 64, 64, true, 0, 0, 1, 1, 0, false, 0, "textures/gui_key_background.png"), //$NON-NLS-1$
	GUI_CK_CHECKED(6, 32, 32, 64, 32, true, 1, 0, 2, 1, 0, false, 0, "textures/gui_checkbox.png"), //$NON-NLS-1$
	GUI_CK_UNCHECKED(7, 32, 32, 64, 32, true, 0, 0, 2, 1, 0, false, 0, "textures/gui_checkbox.png"), //$NON-NLS-1$
	
	ENTITY_PLAYER_BLEU(10, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_bleu.png"), //$NON-NLS-1$
	ENTITY_PLAYER_GRIS(11, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_gris.png"), //$NON-NLS-1$
	ENTITY_PLAYER_JAUNE(12, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_jaune.png"), //$NON-NLS-1$
	ENTITY_PLAYER_ROUGE(13, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_rouge.png"), //$NON-NLS-1$
	ENTITY_PLAYER_VERT(14, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_vert.png"), //$NON-NLS-1$
	ENTITY_PLAYER_GRIS_B(15, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_gris_b.png"), //$NON-NLS-1$
	ENTITY_PLAYER_GRIS_C(15, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_gris_c.png"), //$NON-NLS-1$
	ENTITY_PLAYER_GRIS_D(15, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_gris_d.png"), //$NON-NLS-1$
	ENTITY_PLAYER_GRIS_E(15, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_player_gris_e.png"), //$NON-NLS-1$
	
	ENTITY_ALIEN_VERT(20, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_alien_vert.png"), //$NON-NLS-1$
	ENTITY_ALIEN_JAUNE(21, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_alien_jaune.png"), //$NON-NLS-1$
	ENTITY_ALIEN_BLEU(22, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_alien_bleu.png"), //$NON-NLS-1$
	ENTITY_ALIEN_ROUGE(23, 32, 32, 128, 32, true, 0, 0, 4, 1, 100, false, 1, "textures/entity_alien_rouge.png"), //$NON-NLS-1$
	
	ENTITY_PROJECTILE_EXPLOSION(50, 32, 32, 32, 32, false, 0, 0, 1, 1, 0, false, 0, "textures/decal_explosion.png"), //$NON-NLS-1$
	ENTITY_PROJECTILE_BLOOD(51, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/decal_blood.png"), //$NON-NLS-1$
	
	ENTITY_PROJECTILE_FIST(100, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/entity_projectile_fist.png"), //$NON-NLS-1$
	ENTITY_PROJECTILE_CLAW(101, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/entity_projectile_claw.png"), //$NON-NLS-1$
	ENTITY_PROJECTILE_BULLET(102, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/entity_projectile_bullet.png"), //$NON-NLS-1$
	ENTITY_PROJECTILE_RIFLE(103, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/entity_projectile_rifle.png"), //$NON-NLS-1$
	ENTITY_PROJECTILE_ELEC(104, 16, 16, 16, 16, false, 0, 0, 1, 1, 0, false, 0, "textures/entity_projectile_elec.png"); //$NON-NLS-1$
	
	private int id;
	private int width;
	private int height;
	private int bitmapWidth;
	private int bitmapHeight;
	private boolean unique;
	private int xPosition;
	private int yPosition;
	private int xSegment;
	private int ySegment;
	private long frameRate;
	private boolean loop;
	private int loopTurn;
	private String fileName;
	
	private AuraTileTextureChooser(int id, 
			int width, int height, int bitmapWidth, int bitmapHeight, 
			boolean unique,
			int xPosition, int yPosition, int xSegment, int ySegment,
			long frameRate, boolean loop, int loopTurn,
			String fileName) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.bitmapWidth = bitmapWidth;
		this.bitmapHeight = bitmapHeight;
		this.unique = unique;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xSegment = xSegment;
		this.ySegment = ySegment;
		this.frameRate = frameRate;
		this.loop = loop;
		this.loopTurn = loopTurn;
		this.fileName = fileName;
	}
	
	private VTileTexture tileTexture;
	public VTileTexture getTileTexture() {
		if (tileTexture == null) {
			tileTexture = new VTileTexture(id, 
				width, height, bitmapWidth, bitmapHeight, unique,
				xPosition, yPosition, xSegment, ySegment,
				frameRate, loop, loopTurn, fileName);
		}
		return tileTexture;
	}
}