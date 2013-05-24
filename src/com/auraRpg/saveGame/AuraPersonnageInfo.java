package com.auraRpg.saveGame;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.AuraEntity;
import com.auraRpg.data.AItemArmor;
import com.auraRpg.data.AItemWeapon;
import com.auraRpg.data.ASkills;

public class AuraPersonnageInfo {
	private AuraSaveGame sg;
	private AuraEntity entityInGame;
	
	private String name;
	public final static String PROP_NAME = "name"; //$NON-NLS-1$
	
	private float health;
	public final static String PROP_HEALTH = "health"; //$NON-NLS-1$
	
	private float maxHealth;
	public final static String PROP_MAX_HEALTH = "maxHealth"; //$NON-NLS-1$

	private AItemWeapon weapon;
	public final static String PROP_WEAPON = "weapon";
	
	private AItemArmor armor;
	public final static String PROP_ARMOR = "arm";
	
	protected AuraPersonnageInfo() {}
	public AuraPersonnageInfo(String name) {
		this(name, 0, 0, AItemWeapon.FIST, AItemArmor.BASIC);
	}
	public AuraPersonnageInfo(String name, float health, float maxHealth, AItemWeapon weapon, AItemArmor armor) {
		this();
		setName(name);
		setHealth(health);
		setMaxHealth(maxHealth);
		setWeapon(weapon);
		setArmor(armor);
	}
	
	public AuraSaveGame getSg() {
		return sg;
	}
	public void setSg(AuraSaveGame sg) {
		this.sg = sg;
	}
	
	public AuraEntity getEntityInGame() {
		return entityInGame;
	}
	public void setEntityInGame(AuraEntity entityInGame) {
		this.entityInGame = entityInGame;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	
	public float getSanteMax() {
		return getMaxHealth() + (getSg() != null ? getSg().getSkills().getSkillsInfo().get(ASkills.STAMINA) : 0);
	}
	
	private float getMaxHealth() {
		return maxHealth;
	}
	protected void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public AItemWeapon getWeapon() {
		return weapon;
	}
	public void setWeapon(AItemWeapon weapon) {
		this.weapon = weapon;
	}
	
	public AItemArmor getArmor() {
		return armor;
	}
	public void setArmor(AItemArmor armor) {
		this.armor = armor;
	}
	
	public float getDegats() {
		return getWeapon().getDegatModifier();
	}
	public float getResistance() {
		return getArmor().getResistanceModifier();
	}
	
	public long getHealCostFactor(float ratio) {
		float toHeal = getHealValue(ratio);
		long value = new BigDecimal(toHeal * 1.5f).setScale(0, RoundingMode.DOWN).longValue();
		return value;
	}
	public long getHealCost(float ratio) {
		return new BigDecimal(getHealCostFactor(ratio) * AuraSaveGame.BASE_HEAL_COST)
			.setScale(0, RoundingMode.UP)
			.longValue();
	}
	public float getHealValue(float ratio) {
		return new BigDecimal((getSanteMax() - getHealth()) * ratio)
			.setScale(0, RoundingMode.UP)
			.longValue();
	}
	public boolean heal(float ratio) {
		float toHeal = getHealValue(ratio);
		if (toHeal <= 0)
			return false;
		long price = getHealCost(ratio);
		setHealth(getHealth()+toHeal);
		if (getSg() != null) {
			AuraInventory inv = getSg().getInventory();
			inv.setCredits(inv.getCredits()-price);
		}
		return true;
	}
	
	private long lastTick = 0;
	public boolean tickDamage() {
		long ct = System.currentTimeMillis();
		if (lastTick + weapon.getProjectileRate() < ct) {
			lastTick = ct;
			return true;
		}
		return false;
	}
	
	public void damage(float i) {
		setHealth(getHealth() - i);
		if (getHealth() < 0)
			setHealth(0);
	}
	
	public void load(SharedPreferences p, int id) {
		setName(p.getString(id+"_"+PROP_NAME, "")); //$NON-NLS-1$ //$NON-NLS-2$
		setHealth(p.getFloat(id+"_"+PROP_HEALTH, 0)); //$NON-NLS-1$
		setMaxHealth(p.getFloat(id+"_"+PROP_MAX_HEALTH, 0)); //$NON-NLS-1$
		setWeapon(AItemWeapon.getById(p.getInt(id+"_"+PROP_WEAPON, 0))); //$NON-NLS-1$
		setArmor(AItemArmor.getById(p.getInt(id+"_"+PROP_ARMOR, 0))); //$NON-NLS-1$
	}
	public void save(Editor e, int id) {
		e.putString(id+"_"+PROP_NAME, getName()); //$NON-NLS-1$
		e.putFloat(id+"_"+PROP_HEALTH, getHealth()); //$NON-NLS-1$
		e.putFloat(id+"_"+PROP_MAX_HEALTH, getMaxHealth()); //$NON-NLS-1$
		e.putInt(id+"_"+PROP_WEAPON, getWeapon().getId()); //$NON-NLS-1$
		e.putInt(id+"_"+PROP_ARMOR, getArmor().getId()); //$NON-NLS-1$
	}
	public void delete(Editor e, int id) {
		e.remove(id+"_"+PROP_NAME); //$NON-NLS-1$
		e.remove(id+"_"+PROP_HEALTH); //$NON-NLS-1$
		e.remove(id+"_"+PROP_MAX_HEALTH); //$NON-NLS-1$		
		e.remove(id+"_"+PROP_WEAPON); //$NON-NLS-1$
		e.remove(id+"_"+PROP_ARMOR); //$NON-NLS-1$
	}
}