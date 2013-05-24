package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

public class AConflict {
	private ACorporation corpoA;
	public static final String PROP_CORPO_A = "a"; //$NON-NLS-1$
	
	private ACorporation corpoB;
	public static final String PROP_CORPO_B = "b"; //$NON-NLS-1$
	
	private long maxTime;
	public static final String PROP_MAX_TIME = "mt"; //$NON-NLS-1$
	
	public AConflict(ACorporation corpoA, ACorporation corpoB, long maxTime) {
		this.corpoA = corpoA;
		this.corpoB = corpoB;
		this.maxTime = maxTime;
	}
	
	public ACorporation getCorpoA() {
		return corpoA;
	}
	public ACorporation getCorpoB() {
		return corpoB;
	}
	public long getMaxTime() {
		return maxTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corpoA == null) ? 0 : corpoA.hashCode());
		result = prime * result + ((corpoB == null) ? 0 : corpoB.hashCode());
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
		AConflict other = (AConflict) obj;
		if (corpoA != other.corpoA && corpoA != other.corpoB)
			return false;
		if (corpoB != other.corpoA && corpoB != other.corpoB)
			return false;
		return true;
	}
	
	public int getID() {
		return genererID(this);
	}
	public static int genererID(AConflict c) {
		if (c.getCorpoA().getId() > c.getCorpoB().getId())
			return c.getCorpoB().getId() + 100 + c.getCorpoA().getId();
		return c.getCorpoA().getId() + 100 + c.getCorpoB().getId();
	}
	public static Integer[] getAllIdPossible() {
		List<Integer> ids = new ArrayList<Integer>();
		for (ACorporation a: ACorporation.values()) {
			for (ACorporation b: ACorporation.getTousSauf(a.getFaction())) {
				AConflict c = new AConflict(a, b, 0);
				ids.add(c.getID());
			}
		}
		return ids.toArray(new Integer[0]);
	}
}