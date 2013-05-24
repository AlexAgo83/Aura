package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.ressources.VTileTexture;

public enum AItemWeapon {
	CLAW(   1, 	0.5f,  100,  500, 0.25f, false, "Claw", 0, 
		AuraTileTextureChooser.ENTITY_PROJECTILE_CLAW.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_1.getTexture(),
		0),
	FIST(   2, 	1.0f,  100,  250, 0.25f, false, "Fist", 0,
		AuraTileTextureChooser.ENTITY_PROJECTILE_FIST.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_1.getTexture(),
		0),
	PIST_1( 3, 	1.5f, 3000, 1000, 1.00f, true, "Pistol CIV", 0,
		AuraTileTextureChooser.ENTITY_PROJECTILE_BULLET.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_2.getTexture(),
		5000),
	RIFL1(  4, 	1.5f, 1500,  500, 1.00f, true, "Rifle-U3", 0,
		AuraTileTextureChooser.ENTITY_PROJECTILE_RIFLE.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_3.getTexture(),
		15000),
	SG1(	5, 	1.75f, 750,  750, 1.25f, true, "SG-V", 1,
		AuraTileTextureChooser.ENTITY_PROJECTILE_BULLET.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_4.getTexture(),
		16000),
	ELEC1(  6, 	1.5f,  100,  250, 0.25f, true, "Taz-Orb", 2,
		AuraTileTextureChooser.ENTITY_PROJECTILE_ELEC.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_5.getTexture(),
		18000),
	CALC1(  7, 	1.75f, 1500, 500, 1.10f, true, "LK-20", 2,
		AuraTileTextureChooser.ENTITY_PROJECTILE_RIFLE.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_6.getTexture(),
		20000),
	CALC2(  8, 	2.0f, 1500,  450, 1.10f, true, "LK-50", 3,
		AuraTileTextureChooser.ENTITY_PROJECTILE_RIFLE.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_7.getTexture(),
		25000),
	SG2(	9, 	2.1f,  650,  700, 1.25f, true, "SG-VII", 3,
		AuraTileTextureChooser.ENTITY_PROJECTILE_BULLET.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_4.getTexture(),
		27500),
	RIFL2(  10, 2.1f, 1500,  500, 1.00f, true, "Rifle-X", 5,
		AuraTileTextureChooser.ENTITY_PROJECTILE_RIFLE.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_3.getTexture(),
		29000),
	ELEC2(  11, 2.1f,  125,  250, 0.25f, true, "Taz-Ark", 6,
		AuraTileTextureChooser.ENTITY_PROJECTILE_ELEC.getTileTexture(), 
		AuraTileTextureChooser.ENTITY_PROJECTILE_EXPLOSION.getTileTexture(),
		AuraTextureChooser.GUI_PUCE_WEAPON_5.getTexture(),
		31000);
	
	private int id;
	private float degatModifier;
	private long projectileLifeTime;
	private long projectileRate;
	private float projectileSpeedModifier;
	private boolean buyable;
	private String libelle;
	private int rank;
	private VTileTexture projectileTexture;
	private VTileTexture projectileDecals;
	private VTexture logo;
	private long basePrice;
	
	private AItemWeapon(int id, float degatModifier, 
			long projectileLifeTime, long projectileRate, float projectileSpeedModifier,
			boolean buyable, String libelle, int rank,
			VTileTexture projectileTexture, VTileTexture projectileDecals, VTexture logo,
			long basePrice) {
		this.id = id;
		this.degatModifier = degatModifier;
		this.projectileLifeTime = projectileLifeTime;
		this.projectileRate = projectileRate;
		this.projectileSpeedModifier = projectileSpeedModifier;
		this.buyable = buyable;
		this.libelle = libelle;
		this.rank = rank;
		this.projectileTexture = projectileTexture;
		this.projectileDecals = projectileDecals;
		this.logo = logo;
		this.basePrice = basePrice;
	}
	
	public int getId() {
		return id;
	}
	public float getDegatModifier() {
		return degatModifier;
	}
	public long getProjectileLifeTime() {
		return projectileLifeTime;
	}
	public long getProjectileRate() {
		return projectileRate;
	}
	public String getProjectileRateFormat() {
		return ""+getProjectileRate()/100;
	}
	public float getProjectileSpeedModifier() {
		return projectileSpeedModifier;
	}
	
	public boolean isBuyable() {
		return buyable;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public int getRank() {
		return rank;
	}
	public String getDescription() {
		return "D:" + getDegatModifier() + " R:" + getProjectileRateFormat();
	}
	
	public VTileTexture getProjectileTexture() {
		return projectileTexture;
	}
	public VTileTexture getProjectileDecals() {
		return projectileDecals;
	}
	public VTexture getLogo() {
		return logo;
	}
	
	public long getBasePrice() {
		return basePrice;
	}
	
	public static AItemWeapon getById(int id) {
		for (AItemWeapon w: AItemWeapon.values()) {
			if (id == w.getId())
				return w;
		}
		return null;
	}

	public static AItemWeapon[] getBuyable() {
		List<AItemWeapon> ms = new ArrayList<AItemWeapon>();
		for (AItemWeapon w: AItemWeapon.values()) {
			if (w.isBuyable())
				ms.add(w);
		}
		return ms.toArray(new AItemWeapon[0]);
	}
}