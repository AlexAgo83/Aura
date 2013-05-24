package com.auraRpg.saveGame;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AItemArmor;
import com.auraRpg.data.AItemGoods;
import com.auraRpg.data.AItemWeapon;

public class AuraInventory {
	private long credits;
	public static final String PROP_CREDITS = "cdt";
	
	private Map<Integer, Long> items;
	public static final String PROP_ITEMS = "items";

	private Map<Integer, Boolean> weapons;
	public static final String PROP_WEAPONS = "weapons";
	
	private Map<Integer, Boolean> armors;
	public static final String PROP_ARMORS = "armors";
	
	private long maxSlots;
	public static final String PROP_MAX_SLOTS = "mxSlots";
	
	public AuraInventory() {}
	
	public long getCredits() {
		return credits;
	}
	public void setCredits(long credits) {
		this.credits = credits;
	}
	
	public Map<Integer, Long> getItems() {
		return items;
	}
	private void setItems(Map<Integer, Long> inventory) {
		this.items = inventory;
	}
	public Long getItemQuantity(AItemGoods item) {
		return getItems().get(item.getId()) != null ? getItems().get(item.getId()) : 0;
	}
	
	public long getMaxSlots() {
		return maxSlots;
	}
	public void setMaxSlots(long maxSlots) {
		this.maxSlots = maxSlots;
	}
	public long getSlotsUsed() {
		int cpt = 0;
		for (AItemGoods i: AItemGoods.values()) {
			cpt += getItemQuantity(i);
		}
		return cpt;
	}
	public long getSlotsLeft() {
		return getMaxSlots() - getSlotsUsed(); 
	}
	
	public Map<Integer, Boolean> getWeapons() {
		return weapons;
	}
	public void setWeapons(Map<Integer, Boolean> weapons) {
		this.weapons = weapons;
	}
	public boolean isWeaponOwn(AItemWeapon w) {
		return getWeapons().get(w.getId()); 
	}

	public Map<Integer, Boolean> getArmors() {
		return armors;
	}
	public void setArmors(Map<Integer, Boolean> armors) {
		this.armors = armors;
	}
	public boolean isArmorOwn(AItemArmor a) {
		return getArmors().get(a.getId()); 
	}
	
	public void load(SharedPreferences p, int id) {
		setCredits(p.getLong(id+"_"+PROP_CREDITS, 0)); //$NON-NLS-1$
		setMaxSlots(p.getLong(id+"_"+PROP_MAX_SLOTS, 0));
		// Items
		setItems(new HashMap<Integer, Long>());
		for (AItemGoods i: AItemGoods.values()) {
			getItems().put(i.getId(), p.getLong(id+"_"+PROP_ITEMS+"_"+i.getId(), 0));
		}
		// Weapons
		setWeapons(new HashMap<Integer, Boolean>());
		for (AItemWeapon w: AItemWeapon.values()) {
			getWeapons().put(w.getId(), p.getBoolean(id+"_"+PROP_WEAPONS+"_"+w.getId(), false));
		}
		// Armors
		setArmors(new HashMap<Integer, Boolean>());
		for (AItemArmor a: AItemArmor.values()) {
			getArmors().put(a.getId(), p.getBoolean(id+"_"+PROP_ARMORS+"_"+a.getId(), false));
		}
	}

	public void save(Editor e, int id) {
		e.putLong(id+"_"+PROP_CREDITS, getCredits()); //$NON-NLS-1$
		e.putLong(id+"_"+PROP_MAX_SLOTS, getMaxSlots());
		// Items
		for (AItemGoods i: AItemGoods.values()) {
			e.putLong(id+"_"+PROP_ITEMS+"_"+i.getId(), getItemQuantity(i));
		}
		// Weapons
		for (AItemWeapon w: AItemWeapon.values()) {
			e.putBoolean(id+"_"+PROP_WEAPONS+"_"+w.getId(), isWeaponOwn(w));
		}
		// Armors
		for (AItemArmor a: AItemArmor.values()) {
			e.putBoolean(id+"_"+PROP_ARMORS+"_"+a.getId(), isArmorOwn(a));
		}
	}

	public void delete(Editor e, int id) {
		e.remove(id+"_"+PROP_CREDITS); //$NON-NLS-1$
		e.remove(id+"_"+PROP_MAX_SLOTS);
		// Items
		for (AItemGoods i: AItemGoods.values()) {
			e.remove(id+"_"+PROP_ITEMS+"_"+i.getId());
		}
		// Weapons
		for (AItemWeapon w: AItemWeapon.values()) {
			e.remove(id+"_"+PROP_WEAPONS+"_"+w.getId());
		}
		// Armors
		for (AItemArmor a: AItemArmor.values()) {
			e.remove(id+"_"+PROP_ARMORS+"_"+a.getId());
		}
	}
}