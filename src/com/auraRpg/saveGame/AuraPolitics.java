package com.auraRpg.saveGame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AConflict;
import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AFaction;
import com.voidEngine.engine.VAbstractEngine;

public class AuraPolitics {
	private final Map<AFaction, Integer> reputationFactions;
	public static String PROP_REP_FACT = "rf"; //$NON-NLS-1$
	
	private final Map<ACorporation, Integer> reputationCorporations;
	public static String PROP_REP_CORP = "rec"; //$NON-NLS-1$
	
	private final Map<ACorporation, Integer> rankCorporations;
	public static String PROP_RANK_CORP = "rac"; //$NON-NLS-1$
	
	private Set<AConflict> conflits;
	public static String PROP_CONFLITS = "cts"; //$NON-NLS-1$
	
	private long nextTickConflit;
	public static String PROP_NEXT_TICK_CONFLIT = "nt"; //$NON-NLS-1$
	
	public AuraPolitics() {
		this.reputationFactions = new HashMap<AFaction, Integer>();
		this.reputationCorporations = new HashMap<ACorporation, Integer>();
		this.rankCorporations = new HashMap<ACorporation, Integer>();
		this.conflits = new HashSet<AConflict>();
	}
	
	// REPUTATIONS
	public Map<AFaction, Integer> getReputationFactions() {
		return reputationFactions;
	}
	public Integer getReputationFaction(AFaction faction) {
		return reputationFactions.get(faction);
	}
	
	public Map<ACorporation, Integer> getReputationCorporations() {
		return reputationCorporations;
	}
	public Integer getReputationCorporation(ACorporation corporation) {
		return reputationCorporations.get(corporation);
	}
	
	public Map<ACorporation, Integer> getRankCorporations() {
		return rankCorporations;
	}
	public Integer getRankCorporation(ACorporation c) {
		return rankCorporations.get(c);
	}
	public boolean isPromoteAvailable(ACorporation c) {
		Integer rank = getRankCorporation(c) != null ? getRankCorporation(c) : 0;
		return getRankValueAtStanding(c) > rank;
	}
	public int getRankValueAtStanding(ACorporation c) {
		Integer stan = getReputationCorporation(c) != null ? getReputationCorporation(c) : 0;
		return new BigDecimal(stan/10).setScale(0, RoundingMode.DOWN).intValue();
	}
	public long getRankCostAtStanding(ACorporation c) {
		return new BigDecimal(getRankValueAtStanding(c) * AuraSaveGame.BASE_RANK_COST)
			.setScale(0, RoundingMode.DOWN)
			.longValue();
	}
	
	public Set<AConflict> getConflits() {
		return conflits;
	}
	public AConflict getById(int id) {
		for (AConflict c: getConflits()) {
			if (c.getID() == id)
				return c;
		}
		return null;
	}
	
	public long getNextTickConflit() {
		return nextTickConflit;
	}
	public void setNextTickConflit(long nextTickConflit) {
		this.nextTickConflit = nextTickConflit;
	}

	public void load(SharedPreferences preferences, int id) {
		for (ACorporation c: ACorporation.values()) {
			if (getReputationCorporation(c) == null) {
				getReputationCorporations().put(c, 0);
			}
			if (getRankCorporation(c) == null) {
				getRankCorporations().put(c, 0);
			}
			getReputationCorporations().put(c, 
				preferences.getInt(id+"_"+PROP_REP_CORP+"_"+c.getId(), 0)); //$NON-NLS-1$ //$NON-NLS-2$
			getRankCorporations().put(c, 
				preferences.getInt(id+"_"+PROP_RANK_CORP+"_"+c.getId(), 0)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (AFaction f: AFaction.values()) {
			if (getReputationFaction(f) == null) {
				getReputationFactions().put(f, 0);
			}
			getReputationFactions().put(f, 
				preferences.getInt(id+"_"+PROP_REP_FACT+"_"+f.getId(), 0)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		setNextTickConflit(preferences.getLong(id+"_"+PROP_NEXT_TICK_CONFLIT, 0l)); //$NON-NLS-1$
		for (Integer cId: AConflict.getAllIdPossible()) {
			ACorporation a = ACorporation.getById(preferences.getInt(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_A, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			ACorporation b = ACorporation.getById(preferences.getInt(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_B, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			long maxTime = preferences.getLong(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_MAX_TIME, -1l); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (a != null && b != null && maxTime != -1) {
				AConflict c = new AConflict(a, b, maxTime);
				getConflits().add(c);
			}
		}
	}

	public void save(Editor e, int id) {
		for (ACorporation c: ACorporation.values()) {
			if (getReputationCorporation(c) == null) {
				getReputationCorporations().put(c, 0);
			}
			if (getRankCorporation(c) == null) {
				getRankCorporations().put(c, 0);
			}
			e.putInt(id+"_"+PROP_REP_CORP+"_"+c.getId(), getReputationCorporation(c)); //$NON-NLS-1$ //$NON-NLS-2$
			e.putInt(id+"_"+PROP_RANK_CORP+"_"+c.getId(), getRankCorporation(c)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (AFaction f: AFaction.values()) {
			if (getReputationFaction(f) == null) {
				getReputationFactions().put(f, 0);
			}
			e.putInt(id+"_"+PROP_REP_FACT+"_"+f.getId(), getReputationFaction(f)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		// Conflits
		e.putLong(id+"_"+PROP_NEXT_TICK_CONFLIT, getNextTickConflit()); //$NON-NLS-1$
		for (Integer cId: AConflict.getAllIdPossible()) {
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_A); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_B); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_MAX_TIME); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		for (AConflict c: getConflits()) {
			e.putInt(id+"_"+PROP_CONFLITS+"_"+c.getID()+"_"+AConflict.PROP_CORPO_A, c.getCorpoA().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.putInt(id+"_"+PROP_CONFLITS+"_"+c.getID()+"_"+AConflict.PROP_CORPO_B, c.getCorpoB().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.putLong(id+"_"+PROP_CONFLITS+"_"+c.getID()+"_"+AConflict.PROP_MAX_TIME, c.getMaxTime()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	public void delete(Editor e, int id) {
		e.remove(id+"_"+PROP_NEXT_TICK_CONFLIT);
		for (ACorporation c: ACorporation.values()) {
			e.remove(id+"_"+PROP_REP_CORP+"_"+c.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (AFaction f: AFaction.values()) {
			e.remove(id+"_"+PROP_REP_FACT+"_"+f.getId()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (Integer cId: AConflict.getAllIdPossible()) {
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_A); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_CORPO_B); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			e.remove(id+"_"+PROP_CONFLITS+"_"+cId+"_"+AConflict.PROP_MAX_TIME); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}
	
	private int MINUTE = 60*1000;
//	private int HOUR = 60*MINUTE;
//	private int ONE_DAY = 24*HOUR;
	private AConflict genererConflit(long currentTime) {
		ACorporation a = ACorporation.getRandom();
		ACorporation b = ACorporation.getRandomTousSauf(a.getFaction());
//		long time = currentTime + ONE_DAY;
		long time = currentTime + MINUTE * 30;
		AConflict c = new AConflict(a, b, time);
//		if (!getConflits().contains(c)) {
			getConflits().add(c);
			return c;
//		} 
//		return null;
	}
	
	private void checkConflicts(long currentMillis) {
		AConflict[] base = getConflits().toArray(new AConflict[0]);
		for (AConflict c: base) {
			if (c.getMaxTime()<currentMillis)
				getConflits().remove(c);
			if (c.getCorpoA() == c.getCorpoB())
				getConflits().remove(c);
		}
	}
	
	public AConflict run(VAbstractEngine engine, long currentMillis) {
		checkConflicts(currentMillis);
		if (getNextTickConflit() < currentMillis) {
			engine.log("TICK CONFLICT");
			AConflict newConflit = genererConflit(currentMillis);
			setNextTickConflit(currentMillis + (2*MINUTE) + (Math.round(Math.random() * 5 * MINUTE)));
//			setNextTickConflit(currentMillis + MINUTE);
			return newConflit;
		}
		return null;
	}
	
	public ACorporation[] getInConflictWith(ACorporation c) {
		List<ACorporation> cns = new ArrayList<ACorporation>();
		for (AConflict cn: getConflits()) {
			if (cn.getCorpoA().equals(c))
				cns.add(cn.getCorpoB());
		}
		return cns.toArray(new ACorporation[0]);
	}
	
	public ACorporation[] getBadRepuration() {
		List<ACorporation> ls = new ArrayList<ACorporation>();
		for (ACorporation c: getReputationCorporations().keySet()) {
			if (getReputationCorporations().get(c) < 0)
				ls.add(c);
		}
		return ls.toArray(new ACorporation[0]);
	}
	public ACorporation getRandomBadReputation() {
		ACorporation[] cs = getBadRepuration();
		if (cs.length > 0)
			return cs[(int) Math.round((cs.length-1)*Math.random())];
		return null;
	}
}