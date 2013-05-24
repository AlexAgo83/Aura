package com.auraRpg.data;

import com.auraRpg.ressources.AuraSpeechChooser;
import com.auraRpg.saveGame.AuraMissions;
import com.auraRpg.saveGame.AuraPreferences;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VSpeech;

public enum AMissionCatalog {
	A_LETTER(1, AMissionType.DELIVERY, 
		AuraSpeechChooser.MISSION_DELIVERY_A_LETTER_START.getSpeech(),
		AuraSpeechChooser.MISSION_DELIVERY_A_LETTER_END.getSpeech(),
		(1000 * 60 * 60) * 6 + (1000 * 60 * (long) (Math.random() * 60)),
		AAgent.values()),
	SOME_GOODS(2, AMissionType.DELIVERY, 
		AuraSpeechChooser.MISSION_DELIVERY_SOME_GOODS_START.getSpeech(),
		AuraSpeechChooser.MISSION_DELIVERY_SOME_GOODS_END.getSpeech(),
		(1000 * 60 * 60) * 3 + (1000 * 60 * (long) (Math.random() * 60)),
		AAgent.values()),
	FALSE_ACCUSATION(3, AMissionType.DELIVERY, 
		AuraSpeechChooser.MISSION_DELIVERY_FALSE_ACCUSATION_START.getSpeech(),
		AuraSpeechChooser.MISSION_DELIVERY_FALSE_ACCUSATION_END.getSpeech(),
		(1000 * 60 * 60) * 3 + (1000 * 60 * (long) (Math.random() * 60)),
		AAgent.values()),
	OUTBREAK(4, AMissionType.DELIVERY, 
		AuraSpeechChooser.MISSION_DELIVERY_OUTBREAK_START.getSpeech(),
		AuraSpeechChooser.MISSION_DELIVERY_OUTBREAK_END.getSpeech(),
		(1000 * 60 * 60) * 3 + (1000 * 60 * (long) (Math.random() * 60)),
		AAgent.values()),
	ALIEN_INTRUSION(5, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_ALIEN_INTRUSION_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_ALIEN_INTRUSION_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		new ANpc[]{ANpc.GROTH, ANpc.GROTH_SWAMP, ANpc.GROTH_ELITE, ANpc.GROTH_SLAYER}, 3),
	PIRATE_STRIKE(6, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_PIRATE_STRIKE_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_PIRATE_STRIKE_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		ANpc.getAllPirate(), 5),
	AMBUSH(7, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_AMBUSH_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_AMBUSH_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		ANpc.getAllPirate(), 4),
	GONE_BERZERK(8, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_GONE_BERZERK_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_GONE_BERZERK_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		ANpc.getAllPirate(), 5),
	ESCAPADE(9, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_ESCAPADE_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_ESCAPADE_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		ANpc.getAllPirate(), 3),
	PRESENCE(10, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_PRESENCE_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_PRESENCE_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		new ANpc[]{ANpc.GROTH, ANpc.GROTH_SWAMP, ANpc.GROTH_ELITE, ANpc.GROTH_SLAYER}, 5),
	STOP_THIEF(11, AMissionType.BOUNTY, 
		AuraSpeechChooser.MISSION_BOUNTY_STOP_THIEF_START.getSpeech(),
		AuraSpeechChooser.MISSION_BOUNTY_STOP_THIEF_END.getSpeech(),
		(1000 * 60 * 60) * 12 + (1000 * 60 * (long) (Math.random() * 60)),
		ANpc.getAllPirate(), 1);
	
	private int id;
	private AMissionType type;
	private VSpeech speechStart;
	private VSpeech speechEnd;
	private long time;
	private AAgent[] targetAgent;
	private ANpc[] targetNpc;
	private int count;
	
	private AMissionCatalog(int id, AMissionType type, VSpeech speechStart, VSpeech speechEnd, long time,
			ANpc[] targetNpc, int count) {
		this(id, type, speechStart, speechEnd, time, null, targetNpc, count);
	}
	private AMissionCatalog(int id, AMissionType type, VSpeech speechStart, VSpeech speechEnd, long time,
			AAgent[] targetAgent) {
		this(id, type, speechStart, speechEnd, time, targetAgent, null, 0);
	}
	private AMissionCatalog(int id, AMissionType type, VSpeech speechStart, VSpeech speechEnd, long time, 
			AAgent[] targetAgent, ANpc[] targetNpc, int count) {
		this.id = id;
		this.type = type;
		this.speechStart = speechStart;
		this.speechEnd = speechEnd;
		this.time = time;
		this.targetAgent = targetAgent;
		this.targetNpc = targetNpc;
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	public AMissionType getType() {
		return type;
	}
	public VSpeech getSpeechStart() {
		return speechStart;
	}
	public VSpeech getSpeechEnd() {
		return speechEnd;
	}
	public long getTime() {
		return time;
	}
	public AAgent[] getTargetAgent() {
		return targetAgent;
	}
	public AAgent getTargetAgentRandom() {
		return getTargetAgent()[(int) Math.round((getTargetAgent().length-1)*Math.random())];
	}
	public AAgent getTargetAgentRandomSauf(AAgent sauf) {
		AAgent a = getTargetAgentRandom();
		while (a == sauf) {
			a = getTargetAgentRandom();
		}
		return a;
	}
	public ANpc[] getTargetNpc() {
		return targetNpc;
	}
	public int getCount() {
		return count;
	}
	
	public static AMissionCatalog getById(int id) {
		for (AMissionCatalog mc : AMissionCatalog.values()) {
			if (mc.getId() == id)
				return mc;
		}
		return null;
	}
	public static AMissionCatalog getRandom() {
		return AMissionCatalog.values()[(int) Math.round((AMissionCatalog.values().length-1)*Math.random())];
	}
	public static AMissionCatalog getRandom(VAbstractEngine engine) {
		AuraMissions m = ((AuraPreferences) engine.getVPreferences()).getCurrentSaveGame().getMissions();
		AMissionCatalog cRan = getRandom();
		while (m.getCiclers().contains(cRan.getSpeechStart().getId())) {
			cRan = getRandom();
		}
		m.addCicle(cRan.getSpeechStart().getId());
		return cRan; 
	}
}