package com.auraRpg.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.auraRpg.saveGame.AuraPreferences;
import com.auraRpg.saveGame.AuraSaveGame;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.map.VMap;
import com.voidEngine.engine.map.VPoint;
import com.voidEngine.engine.ressources.VSpeech;
import com.voidEngine.tools.VTextFormater;

public abstract class AMission {
	private AMissionType type;
	public static final String PROP_TYPE = "type"; //$NON-NLS-1$

	private AAgent giver;
	public static final String PROP_GIVER = "giver"; //$NON-NLS-1$
	
	private VSpeech speechStartDesc;
	public static final String PROP_END_SPEECH = "startDesc"; //$NON-NLS-1$
	
	private VSpeech speechEndDesc;
	public static final String PROP_START_SPEECH = "endDesc"; //$NON-NLS-1$
	
	private long timeEnd;
	public static final String PROP_TIME_END = "timeEnd"; //$NON-NLS-1$
	
	private AMap planeteCible;
	public static final String PROP_PLANETE_CIBLE = "pCible";
	
	private AMap escaCible;
	public static final String PROP_ESCA_CIBLE = "eCible";
	
	public AMission(AMissionType type, AAgent giver, VSpeech startDesc, VSpeech endDesc, long timeEnd, AMap plaCible, AMap escaCible) {
		this.type = type;
		this.giver = giver;
		this.speechStartDesc = startDesc;
		this.speechEndDesc = endDesc;
		this.timeEnd = timeEnd;
		this.planeteCible = plaCible;
		this.escaCible = escaCible;
	}
	
	public AMissionType getType() {
		return type;
	}
	public AAgent getGiver() {
		return giver;
	}
	public VSpeech getSpeechStartDescription() {
		return speechStartDesc;
	}
	public VSpeech getSpeechEndDescription() {
		return speechEndDesc;
	}
	public String getName(VAbstractEngine engine) {
		String[] datas = engine.getVRessourceManager().getSpeech(getSpeechStartDescription());
		return doRowDescription(engine, datas[0]);
	}
	public String[] getStartDescription(VAbstractEngine engine) {
		List<String> descriptions = new ArrayList<String>();
		String[] datas = engine.getVRessourceManager().getSpeech(getSpeechStartDescription());
		for (int i=1; i<datas.length; i++) {
			String[] nrs = VTextFormater.format(doRowDescription(engine, datas[i]), 60);
			for (String r: nrs) {
				descriptions.add(r);				
			}
		}
		return descriptions.toArray(new String[0]);
	}
	public String[] getEndDescription(VAbstractEngine engine) {
		List<String> descriptions = new ArrayList<String>();
		String[] datas = engine.getVRessourceManager().getSpeech(getSpeechEndDescription());
		for (int i=0; i<datas.length; i++) {
			String[] nrs = VTextFormater.format(doRowDescription(engine, datas[i]), 60);
			for (String r: nrs) {
				descriptions.add(r);				
			}
		}
		return descriptions.toArray(new String[0]);
	}

	public void setTimeEnd(long timeEnd) {
		this.timeEnd = timeEnd;
	}
	public long getTimeEnd() {
		return timeEnd;
	}
	public long getTimeEndFormat() {
		return timeEnd + AuraSaveGame.BASE_TIME;
	}
	public String getTimeLeft(VAbstractEngine engine) {
		float timeLeft = getTimeEnd() - ((AuraPreferences) engine.getVPreferences()).getCurrentSaveGame().getTime(); 
		return new BigDecimal(timeLeft/1000/60/60/24).setScale(0, RoundingMode.DOWN).intValue() + " day,"  //$NON-NLS-1$
			+ " " + new BigDecimal((timeLeft/1000/60/60) % 60).setScale(0, RoundingMode.DOWN).intValue() + "H" //$NON-NLS-1$ //$NON-NLS-2$
			+ new BigDecimal((timeLeft/1000/60) % 60).setScale(0, RoundingMode.DOWN).intValue();
	}
	
	public AMap getPlaneteCible() {
		return planeteCible;
	}
	public void setPlaneteCible(AMap planeteCible) {
		this.planeteCible = planeteCible;
	}
	
	public AMap getEscaCible() {
		return escaCible;
	}
	public void setEscaCible(AMap escaCible) {
		this.escaCible = escaCible;
	}
	
	public String doRowDescription(VAbstractEngine engine, String row) {
		row = row.replaceAll("<TL>", getTimeLeft(engine)); //$NON-NLS-1$
		return onRowDescription(row);
	}
	public abstract String onRowDescription(String row);
	public abstract void onFinish(VAbstractEngine engine);
	public abstract void onGive(VAbstractEngine engine);
	
	public static AMission genererMission(VAbstractEngine engine, AAgent giver, AMissionCatalog catalog) {
		long currTime = ((AuraPreferences) engine.getVPreferences()).getCurrentSaveGame().getTime();
		AMissionCatalog mc = catalog;
		AMap escaCible = AMap.getRandom(AMapType.ESCARMOUCHE);
		AMap plaCible = AMap.getRandom(AMapType.PLANETE);
		switch (catalog.getType()) {
			case DELIVERY :
				AAgent agent = mc.getTargetAgentRandomSauf(giver);
				plaCible = locateMapByAgent(engine, agent);
				AMissionDelivery m = new AMissionDelivery(
					mc.getType(), 
					giver,
					mc.getSpeechStart(), 
					mc.getSpeechEnd(),
					mc.getTime()+currTime,
					plaCible,
					escaCible,
					agent);
				return m;
			case BOUNTY :
				ANpc targetNpc = mc.getTargetNpc()[(int) Math.round((mc.getTargetNpc().length-1)*Math.random())];
				AMissionBounty b = new AMissionBounty(
					mc.getType(),
					giver,
					mc.getSpeechStart(), 
					mc.getSpeechEnd(),
					mc.getTime()+currTime,
					plaCible,
					escaCible,
					targetNpc,
					0, mc.getCount());
				return b;
		}
		return null;
	}
	
	public static AMap locateMapByAgent(VAbstractEngine engine, AAgent a) {
		for (AMap m : AMap.getByType(AMapType.PLANETE)) {
			VMap vm = m.load(engine);
			for (VPoint p: vm.getTiles().keySet()) {
				if (vm.getTiles().get(p).getDataEntity() == a.getId())
					return m;
			}
		}
		return null;
	}
	
	public static AMap locateMapBySpawner(VAbstractEngine engine, ANpcSpawner a) {
		for (AMap m : AMap.values()) {
			VMap vm = m.load(engine);
			for (VPoint p: vm.getTiles().keySet()) {
				if (vm.getTiles().get(p).getDataSpawner() == a.getId())
					return m;
			}
		}
		return null;
	}
}