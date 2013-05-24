package com.auraRpg.ressources;

import com.voidEngine.engine.ressources.VSpeech;

public enum AuraSpeechChooser {
	CHANGE_LOGS(1, "speechs/changeLogs.txt"), //$NON-NLS-1$
	ABOUT(2, "speechs/about.txt"), //$NON-NLS-1$
	
	MISSION_DELIVERY_A_LETTER_START(10, "speechs/md_aLetter_start.txt"), //$NON-NLS-1$
	MISSION_DELIVERY_A_LETTER_END(11, "speechs/md_aLetter_end.txt"), //$NON-NLS-1$
	
	MISSION_DELIVERY_SOME_GOODS_START(12, "speechs/md_someGoods_start.txt"), //$NON-NLS-1$
	MISSION_DELIVERY_SOME_GOODS_END(13, "speechs/md_someGoods_end.txt"), //$NON-NLS-1$
	
	MISSION_DELIVERY_FALSE_ACCUSATION_START(14, "speechs/md_falseAccusation_start.txt"), //$NON-NLS-1$
	MISSION_DELIVERY_FALSE_ACCUSATION_END(15, "speechs/md_falseAccusation_end.txt"), //$NON-NLS-1$
	
	MISSION_DELIVERY_OUTBREAK_START(16, "speechs/md_outbreak_start.txt"), //$NON-NLS-1$
	MISSION_DELIVERY_OUTBREAK_END(17, "speechs/md_outbreak_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_ALIEN_INTRUSION_START(100, "speechs/mb_alienIntrusion_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_ALIEN_INTRUSION_END(101, "speechs/mb_alienIntrusion_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_PIRATE_STRIKE_START(102, "speechs/mb_pirateStrike_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_PIRATE_STRIKE_END(103, "speechs/mb_pirateStrike_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_AMBUSH_START(104, "speechs/mb_ambush_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_AMBUSH_END(105, "speechs/mb_ambush_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_GONE_BERZERK_START(106, "speechs/mb_goneBerzerk_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_GONE_BERZERK_END(107, "speechs/mb_goneBerzerk_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_ESCAPADE_START(108, "speechs/mb_escapade_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_ESCAPADE_END(109, "speechs/mb_escapade_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_PRESENCE_START(110, "speechs/mb_presence_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_PRESENCE_END(111, "speechs/mb_presence_end.txt"), //$NON-NLS-1$
	
	MISSION_BOUNTY_STOP_THIEF_START(110, "speechs/mb_stopThief_start.txt"), //$NON-NLS-1$
	MISSION_BOUNTY_STOP_THIEF_END(111, "speechs/mb_stopThief_end.txt"); //$NON-NLS-1$
	
	private int id;
	private String fileName;
	
	private AuraSpeechChooser(int id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}
	
	public int getId() {
		return id;
	}
	public String getFileName() {
		return fileName;
	}
	
	private VSpeech speech;
	public VSpeech getSpeech() {
		if (speech == null) {
			speech = new VSpeech(id, fileName);
		}
		return speech;
	}
	
	public static AuraSpeechChooser getById(int id) {
		for (AuraSpeechChooser s: AuraSpeechChooser.values()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
}