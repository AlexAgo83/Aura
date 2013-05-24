package com.auraRpg.saveGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AItemArmor;
import com.auraRpg.data.AItemWeapon;
import com.auraRpg.data.AMap;
import com.auraRpg.data.ASkills;
import com.auraRpg.data.AStarterProfession;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.VAbstractPreferences;

public class AuraPreferences extends VAbstractPreferences {
	private Map<Integer, AuraSaveGame> saveGames = new HashMap<Integer, AuraSaveGame>();
	private final static String SLOTS_LIST = "SG_SLOTS";  //$NON-NLS-1$

	private AuraSaveGame currentSaveGame;
	public AuraSaveGame getCurrentSaveGame() {
		return currentSaveGame;
	}
	public void setCurrentSaveGame(AuraSaveGame currentSaveGame) {
		this.currentSaveGame = currentSaveGame;
	}
	
	public AuraPreferences(VAbstractEngine engine) {
		super(engine);
	}
	
	private void addIds(Integer id) {
		String slotsStr = getPreferences().getString(SLOTS_LIST, ""); //$NON-NLS-1$
		if (slotsStr.trim().length()==0) {
			slotsStr += id.intValue();
		} else {
			slotsStr += ";"+id.intValue(); //$NON-NLS-1$
		}
		Editor e = getPreferences().edit();
		e.putString(SLOTS_LIST, slotsStr);
		e.commit();
	}
	
	private Integer[] grabIds() {
		List<Integer> ids = new ArrayList<Integer>();
		String slotsStr = getPreferences().getString(SLOTS_LIST, ""); //$NON-NLS-1$
		String[] slots = slotsStr.split(";"); //$NON-NLS-1$

		getEngine().log("IDS: " + slotsStr); //$NON-NLS-1$
		
		for (String sl: slots) {
			if (sl.trim().length() > 0) {
				ids.add(Integer.parseInt(sl));
			}
		}
		
		return ids.toArray(new Integer[0]);
	}
	
	@Override
	public void load() {
		saveGames.clear();
		Integer[] ids = grabIds();
		for (Integer id: ids) {
			if (id != null) {
				AuraSaveGame sg = new AuraSaveGame(this, id);
				sg.load();
				saveGames.put(sg.getId(), sg);
			}
		}
	}
	
	public Map<Integer, AuraSaveGame> getSaveGames() {
		return saveGames;
	}
	
	public boolean newSaveGame(String name, AStarterProfession st) {
		if (name == null || name.trim().length() == 0) 
			return false;
		Integer[] ids = grabIds();
		Integer max = 0;
		for (Integer id: ids) {
			if (id.compareTo(max) > 0)
				max = id;
		}
		
		Integer newId = max+1;
		AuraSaveGame sg = new AuraSaveGame(this, newId);
		sg.load();
		sg.setTime(0l);
		sg.setMap(AMap.PLANETE_E);
		sg.getPersonnageInfo().setName(name);
		sg.getPersonnageInfo().setMaxHealth(AuraSaveGame.BASE_HEALTH);
		
		// WEAPONS
		for (AItemWeapon w: AItemWeapon.values()) {
			sg.getInventory().getWeapons().put(w.getId(), false);
		}
		sg.getInventory().getWeapons().put(AItemWeapon.FIST.getId(), true);
		sg.getPersonnageInfo().setWeapon(AItemWeapon.FIST);
		switch (st) {
			case SOLDAT:
			case ADVENTURER:
			case PIRATE:
				sg.getInventory().getWeapons().put(AItemWeapon.PIST_1.getId(), true);
				sg.getPersonnageInfo().setWeapon(AItemWeapon.PIST_1);
				break;
			case TRADER:
				break;
		}
		
		// ARMORS
		for (AItemArmor a: AItemArmor.values()) {
			sg.getInventory().getArmors().put(a.getId(), false);
		}
		sg.getInventory().getArmors().put(AItemArmor.BASIC.getId(), true);
		sg.getPersonnageInfo().setArmor(AItemArmor.BASIC);
		
		sg.getInventory().setCredits(AuraSaveGame.BASE_CREDITS);
		sg.getInventory().setMaxSlots(AuraSaveGame.BASE_MAX_SLOTS);
		sg.getSkills().setSkillPoints(AuraSaveGame.BASE_SKILL_POINT);
		for (ASkills s: ASkills.values()) 
			sg.getSkills().getSkillsInfo().put(s, 1);
		sg.getSkills().setStarterProfession(st);
		switch (st) {
			case SOLDAT :
				sg.getSkills().getSkillsInfo().put(ASkills.ACCURENCY, 4);
				sg.getSkills().getSkillsInfo().put(ASkills.INTIMIDATION, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.NEGOCIATION, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.STEALTH, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.STAMINA, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.EXPLORATION, 1);
				break;
			case TRADER:
				sg.getSkills().getSkillsInfo().put(ASkills.ACCURENCY, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.INTIMIDATION, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.NEGOCIATION, 4);
				sg.getSkills().getSkillsInfo().put(ASkills.STEALTH, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.STAMINA, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.EXPLORATION, 1);
				break;
			case PIRATE:
				sg.getSkills().getSkillsInfo().put(ASkills.ACCURENCY, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.INTIMIDATION, 4);
				sg.getSkills().getSkillsInfo().put(ASkills.NEGOCIATION, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.STEALTH, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.STAMINA, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.EXPLORATION, 2);
				break;
			case ADVENTURER :
				sg.getSkills().getSkillsInfo().put(ASkills.ACCURENCY, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.INTIMIDATION, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.NEGOCIATION, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.STEALTH, 1);
				sg.getSkills().getSkillsInfo().put(ASkills.STAMINA, 2);
				sg.getSkills().getSkillsInfo().put(ASkills.EXPLORATION, 4);
				break; 
		}
		sg.getPersonnageInfo().setHealth(sg.getPersonnageInfo().getSanteMax());
		sg.setX(32);
		sg.setY(32);
		sg.save();
		addIds(newId);
		saveGames.put(newId, sg);
		return true;
	}

	public void remove(AuraSaveGame auraSaveGame) {
		saveGames.remove(auraSaveGame.getId());
		Integer[] ids = grabIds();
		String slotsStr = ""; //$NON-NLS-1$
		boolean first = true;
		for (Integer id : ids) {
			if (id.compareTo(auraSaveGame.getId()) != 0) {
				slotsStr += (!first ? ";" : "") + id.intValue(); //$NON-NLS-1$ //$NON-NLS-2$
				first = false;
			}
		}
		Editor e = getPreferences().edit();
		e.putString(SLOTS_LIST, slotsStr);
		e.commit();
	}
	
	public AuraAchievement getAchievement() {
		if (getCurrentSaveGame() != null) {
			return getCurrentSaveGame().getAchievement();
		}
		AuraSaveGame sg = new AuraSaveGame(this, -1);
		sg.load();
		return sg.getAchievement();
	}
	public AuraOptions getOption() {
		if (getCurrentSaveGame() != null) {
			return getCurrentSaveGame().getOption();
		}
		AuraSaveGame sg = new AuraSaveGame(this, -1);
		sg.load();
		return sg.getOption();
	}
}