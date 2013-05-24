package com.auraRpg.saveGame;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AMap;
import com.auraRpg.data.AMapType;

public class AuraSaveGame {
	public final static float BASE_HEALTH = 20f;
	public final static long BASE_CREDITS = 5000;
	public final static float BASE_RATIO_NPC_MODIFIER = .02f;
	public final static long BASE_RANK_COST = 1000;
	public final static long BASE_SKILL_POINT = 0;
	public final static int BASE_MAX_SLOTS = 40;
	public final static int BASE_REWARD_MISSION = 1000;
	public final static float BASE_INTERCEPTION_RATE = .5f;
	public final static float BASE_EXPLORATION_RATE = .3f;
	public final static long BASE_NEXT_EXPLORATION = 1000 * 60 * 15; 
	public final static float BASE_CATH_ON_FLEE_RATE = .25f;
	public final static long BASE_TRAVEL_COST = 75;
	public final static long BASE_HEAL_COST = 50;
	public final static int BASE_CICLER_MAX = 3;
	
	public final static int BASE_MISSIONS_MAX_SLOT = 10;
	public final static long BASE_TIME = 10000000000000l;

	public final static SimpleDateFormat SDF_DATE_LARGE = new SimpleDateFormat("MM/dd/yyyy - hh:mm"); //$NON-NLS-1$
	public final static SimpleDateFormat SDF_DATE = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
	public final static SimpleDateFormat SDF_MM_SS = new SimpleDateFormat("mm:ss"); //$NON-NLS-1$

	private final AuraPreferences pref;
	private final int id;
	public static final String PROP_ID = "id"; //$NON-NLS-1$
	
	// COMMUN
	private AuraAchievement achievement;
	public AuraAchievement getAchievement() {
		if (achievement == null) {
			achievement = new AuraAchievement();
		}
		return achievement;
	}
	
	private AuraOptions option;
	public AuraOptions getOption() {
		if (option == null) {
			option = new AuraOptions();
		}
		return option;
	}
	
	// PRIVATE
	private AuraPersonnageInfo personnageInfo;
	public AuraPersonnageInfo getPersonnageInfo() {
		if (personnageInfo == null) {
			personnageInfo = new AuraPersonnageInfo();
			personnageInfo.setSg(this);
		}
		return personnageInfo;
	}

	private AuraSkills skills;
	public AuraSkills getSkills() {
		if (skills == null) {
			skills = new AuraSkills();
		}
		return skills;
	}
	
	private AuraPolitics politics;
	public AuraPolitics getPolitics() {
		if (politics == null) {
			politics = new AuraPolitics();
		}
		return politics;
	}

	private AuraMissions missions;
	public AuraMissions getMissions() {
		if (missions == null) {
			missions = new AuraMissions(this);
		}
		return missions;
	}
	
	private AuraInventory inventory;
	public AuraInventory getInventory() {
		if (inventory == null) {
			inventory = new AuraInventory();
		}
		return inventory;
	}
	
	private AuraVendor vendorInfo;
	public AuraVendor getVendorInfo() {
		if (vendorInfo == null) {
			vendorInfo = new AuraVendor();
		}
		return vendorInfo;
	}

	private long time;
	public static final String PROP_TIME = "time"; //$NON-NLS-1$
	
	private Map<Integer, Long> lastExplorationTick;
	public static final String PROP_LAST_EXPL_TICK = "lastExplTicks"; //$NON-NLS-1$
	
	private AMap map;
	public static final String PROP_MAP = "map"; //$NON-NLS-1$
	
	private AMap lastMap;
	public static final String PROP_LAST_MAP = "lastMap"; //$NON-NLS-1$
	
	private float x;
	public static final String PROP_X = "x"; //$NON-NLS-1$
	
	private float y;
	public static final String PROP_Y = "y"; //$NON-NLS-1$
	
	public AuraSaveGame(AuraPreferences pref, int id) {
		this.pref = pref;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	public long getTime() {
		return time;
	}
	public long getTimeFormat() {
		return getTime()+BASE_TIME;
	}
	public String getTimeFormatString(boolean large) {
		return large ? 
			SDF_DATE_LARGE.format(new Date(getTimeFormat())) :
			SDF_DATE.format(new Date(getTimeFormat()));
	}
	public long getAvancement() {
		return new BigDecimal(getTime() / 12 / 60 / 60 / 1000).setScale(0, RoundingMode.DOWN).longValue();
	}
	public float getRatioNPCModifier() {
		return new BigDecimal(getAvancement()*BASE_RATIO_NPC_MODIFIER)
			.setScale(2, RoundingMode.DOWN)
			.floatValue();
	}

	public Map<Integer, Long> getLastExplorationTick() {
		return lastExplorationTick;
	}
	public void setLastExplorationTick(Map<Integer, Long> lastExplorationTick) {
		this.lastExplorationTick = lastExplorationTick;
	}
	public long getLastExplorationTickByMap(AMap m) {
		return getLastExplorationTick().get(m.getId()) != null ? getLastExplorationTick().get(m.getId()) : 0;
	}
	public long getLastExplorationTickMinLeftByMap(AMap m) {
		return new BigDecimal((getLastExplorationTickByMap(m) - getTime()) /60 /1000)
			.setScale(0, RoundingMode.DOWN)
			.longValue();
	}
	public long getLastExplorationTickSecLeftByMap(AMap m) {
		return new BigDecimal((getLastExplorationTickByMap(m) - getTime()) /1000)
			.setScale(0, RoundingMode.DOWN)
			.longValue();
	}
	
	public void setMap(AMap map) {
		this.map = map;
	}
	public AMap getMap() {
		return map;
	}
	
	public void setLastMap(AMap lastMap) {
		this.lastMap = lastMap;
	}
	public AMap getLastMap() {
		return lastMap;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	public float getY() {
		return y;
	}

	public void load() {
		// COMMUN
		getAchievement().load(pref.getPreferences());
		getOption().load(pref.getPreferences());
		if (id<0)
			return;
		
		// PERSONNAL
		setTime(pref.getPreferences().getLong(id+"_"+PROP_TIME, 0));
		setLastExplorationTick(new HashMap<Integer, Long>());
		for (AMap m: AMap.getByType(AMapType.PLANETE)) {
			getLastExplorationTick().put(m.getId(), pref.getPreferences().getLong(id+"_"+PROP_LAST_EXPL_TICK+"_"+m.getId(), 0));
		}
		setLastMap(AMap.getById(pref.getPreferences().getInt(id+"_"+PROP_LAST_MAP, -1))); //$NON-NLS-1$
		setMap(AMap.getById(pref.getPreferences().getInt(id+"_"+PROP_MAP, -1))); //$NON-NLS-1$
		setX(pref.getPreferences().getFloat(id+"_"+PROP_X, 0)); //$NON-NLS-1$
		setY(pref.getPreferences().getFloat(id+"_"+PROP_Y, 0)); //$NON-NLS-1$
		
		getPersonnageInfo().load(pref.getPreferences(), id);
		getInventory().load(pref.getPreferences(), id);
		getSkills().load(pref.getPreferences(), id);
		getPolitics().load(pref.getPreferences(), id);
		getMissions().load(pref.getPreferences(), id);
		getVendorInfo().load(pref.getPreferences(), id);
	}
	
	public void save() {
		Editor e = pref.getPreferences().edit();
		// COMMUN
		getAchievement().save(e);
		getOption().save(e);
		if (id<0) {
			e.commit();
			return;
		}
		
		// PERSONNAL SAVEGAME
		e.putLong(id+"_"+PROP_TIME, getTime()); //$NON-NLS-1$
		for (AMap m: AMap.getByType(AMapType.PLANETE)) {
			long last = getLastExplorationTick().get(m.getId()) != null ? getLastExplorationTick().get(m.getId()) : 0;
			e.putLong(id+"_"+PROP_LAST_EXPL_TICK+"_"+m.getId(), last); //$NON-NLS-1$
		}
		e.putInt(id+"_"+PROP_MAP, getMap().getId()); //$NON-NLS-1$
		e.putInt(id+"_"+PROP_LAST_MAP, getLastMap() != null ? getLastMap().getId() : -1); //$NON-NLS-1$
		e.putFloat(id+"_"+PROP_X, getX()); //$NON-NLS-1$
		e.putFloat(id+"_"+PROP_Y, getY()); //$NON-NLS-1$
		
		getPersonnageInfo().save(e, id);
		getInventory().save(e, id);
		getSkills().save(e, id);
		getPolitics().save(e, id);
		getMissions().save(e, id);
		getVendorInfo().save(e, id);
		e.commit();
	}
	
	public void delete() {
		Editor e = pref.getPreferences().edit();
		e.remove(id+"_"+PROP_TIME); //$NON-NLS-1$
		for (AMap m: AMap.getByType(AMapType.PLANETE)) {
			e.remove(id+"_"+PROP_LAST_EXPL_TICK+"_"+m.getId()); //$NON-NLS-1$
		}
		e.remove(id+"_"+PROP_MAP); //$NON-NLS-1$
		e.remove(id+"_"+PROP_LAST_MAP); //$NON-NLS-1$
		e.remove(id+"_"+PROP_MAP); //$NON-NLS-1$
		e.remove(id+"_"+PROP_X); //$NON-NLS-1$
		e.remove(id+"_"+PROP_Y); //$NON-NLS-1$
		getPersonnageInfo().delete(e, id);
		getInventory().delete(e, id);
		getSkills().delete(e, id);
		getPolitics().delete(e, id);
		getMissions().delete(e, id);
		getVendorInfo().delete(e, id);
		e.commit();
		pref.remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuraSaveGame other = (AuraSaveGame) obj;
		if (id != other.id)
			return false;
		return true;
	}
}