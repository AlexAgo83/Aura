package com.auraRpg.saveGame;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AAgent;
import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AMap;
import com.auraRpg.data.AMission;
import com.auraRpg.data.AMissionBounty;
import com.auraRpg.data.AMissionDelivery;
import com.auraRpg.data.AMissionType;
import com.auraRpg.data.ANpc;
import com.auraRpg.ressources.AuraSpeechChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VSpeech;

public class AuraMissions {
	private final AuraSaveGame sg;
	
	private List<Integer> ciclers;
	public static final String PROP_CICLERS = "ciclers";
	
	private List<AMission> missions;
	public static final String PROP_MISSIONS = "miss"; //$NON-NLS-1$
	
	public AuraMissions(AuraSaveGame sg) {
		this.sg = sg;
	}

	public List<Integer> getCiclers() {
		return ciclers;
	}
	public void setCiclers(List<Integer> ciclers) {
		this.ciclers = ciclers;
	}
	public void addCicle(Integer newId) {
		Integer[] ccs = getCiclers().toArray(new Integer[0]);
		List<Integer> newCCS = new ArrayList<Integer>();
		if (ccs.length >= AuraSaveGame.BASE_CICLER_MAX) {
			for (int i=1; i<AuraSaveGame.BASE_CICLER_MAX; i++) {
				newCCS.add(ccs[i]);
			}
		}
		newCCS.add(newId);
		setCiclers(newCCS);
	}
	
	public void setMissionsTab(List<AMission> missions) {
		this.missions = missions;
	}
	public List<AMission> getMissionsTab() {
		return missions;
	}
	public int getMissionsSlotLeft() {
		return AuraSaveGame.BASE_MISSIONS_MAX_SLOT-getMissionsTab().size();
	}
	public void ajouterMission(VAbstractEngine engine, AMission m) {
		getMissionsTab().add(m);
		m.onGive(engine);
	}
	public void terminerMission(VAbstractEngine engine, AMission m) {
		getMissionsTab().remove(m);
		m.onFinish(engine);
		
		// REPUTATIONS
		sg.getPolitics().getReputationCorporations().put(m.getGiver().getCorporation(), 
			sg.getPolitics().getReputationCorporations().get(m.getGiver().getCorporation()).intValue() + 3);
		sg.getPolitics().getReputationFactions().put(m.getGiver().getCorporation().getFaction(), 
			sg.getPolitics().getReputationFactions().get(m.getGiver().getCorporation().getFaction()).intValue() + 1);
		for (ACorporation c: sg.getPolitics().getInConflictWith(m.getGiver().getCorporation())) {
			sg.getPolitics().getReputationCorporations().put(c, 
				sg.getPolitics().getReputationCorporations().get(c).intValue() - 1);
		}
		
		// SKILL POINTS
		AuraSkills sk = sg.getSkills();
		sk.setSkillPoints(sk.getSkillPoints()+1);
		
		// REWARDS
		int bonusCds = (int) (Math.log(sg.getPolitics().getReputationCorporations()
				.get(m.getGiver().getCorporation()).intValue()) * 100);
		if (bonusCds < -AuraSaveGame.BASE_REWARD_MISSION) bonusCds = -AuraSaveGame.BASE_REWARD_MISSION;
		sg.getInventory().setCredits(sg.getInventory().getCredits()+AuraSaveGame.BASE_REWARD_MISSION+bonusCds);
		
		// ACHIEVEMENT
		sg.getAchievement().increaseMissioneTerminee();
	}
	public void supprimerMission(AMission m) {
		getMissionsTab().remove(m);
		sg.getPolitics().getReputationCorporations().put(m.getGiver().getCorporation(), 
			sg.getPolitics().getReputationCorporations().get(m.getGiver().getCorporation()).intValue() - 1);
		sg.getAchievement().increaseMissioneAnnulee();
	}
	public AMission[] getMissionsByType(AMissionType type) {
		List<AMission> ms = new ArrayList<AMission>();
		for (AMission m: getMissionsTab()) {
			if (m.getType().getId() == type.getId())
				ms.add(m);
		}
		return ms.toArray(new AMission[0]);
	}

	public void load(SharedPreferences p, int id) {
		// Ciclers
		setCiclers(new ArrayList<Integer>());
		for (int i=0; i<AuraSaveGame.BASE_CICLER_MAX; i++) {
			getCiclers().add(p.getInt(id+"_"+PROP_CICLERS+"_"+i, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// Missions
		setMissionsTab(new ArrayList<AMission>());
		for (int i=0; i<AuraSaveGame.BASE_MISSIONS_MAX_SLOT; i++) {
			AMissionType type = AMissionType.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_TYPE, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (type != null) {
				AAgent giver = AAgent.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_GIVER, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				VSpeech startSpeech = AuraSpeechChooser.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_START_SPEECH, -1)).getSpeech(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				VSpeech endSpeech = AuraSpeechChooser.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_END_SPEECH, -1)).getSpeech(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				AMap plaCible = AMap.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_PLANETE_CIBLE, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				AMap escaCible = AMap.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_ESCA_CIBLE, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				long timeEnd = p.getLong(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_TIME_END, -1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				switch (type) {
					case DELIVERY:
						AAgent targetAgent = AAgent.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionDelivery.PROP_TARGET, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						AMissionDelivery delivery = new AMissionDelivery(
							type,
							giver,
							startSpeech,
							endSpeech,
							timeEnd,
							plaCible,
							escaCible,
							targetAgent);
						getMissionsTab().add(delivery);
						break;
					case BOUNTY:
						ANpc targetNpc = ANpc.getById(p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_TARGET, -1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						int count = p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_COUNT, -1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						int maxCount = p.getInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_MAX_COUNT, -1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						AMissionBounty bounty = new AMissionBounty(
							type,
							giver,
							startSpeech,
							endSpeech,
							timeEnd,
							plaCible,
							escaCible,
							targetNpc,
							count,
							maxCount);
						getMissionsTab().add(bounty);
						break;
				}
			}
		}
	}

	public void save(Editor e, int id) {
		// Ciclers
		Integer[] ccs = getCiclers().toArray(new Integer[0]);
		for (int i=0; i<AuraSaveGame.BASE_CICLER_MAX; i++) {
			int val = -1;
			if (i < ccs.length)
				val = ccs[i];
			e.putInt(id+"_"+PROP_CICLERS+"_"+i, val);
		}
		// Missions
		AMission[] missions = getMissionsTab().toArray(new AMission[0]);
		for (int i=0; i<AuraSaveGame.BASE_MISSIONS_MAX_SLOT; i++) {
			if (i<missions.length) {
				AMission m = missions[i];
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_TYPE, m.getType().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_GIVER, m.getGiver().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_START_SPEECH, m.getSpeechStartDescription().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_END_SPEECH, m.getSpeechEndDescription().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				e.putLong(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_TIME_END, m.getTimeEnd()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_PLANETE_CIBLE, m.getPlaneteCible() != null ? m.getPlaneteCible().getId() : -1);
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_ESCA_CIBLE, m.getEscaCible() != null ? m.getEscaCible().getId() : -1);
				switch (m.getType()) {
					case DELIVERY:
						e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionDelivery.PROP_TARGET, ((AMissionDelivery) m).getTarget().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						break;
					case BOUNTY:
						e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_TARGET, ((AMissionBounty) m).getTarget().getId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_COUNT, ((AMissionBounty) m).getCount()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMissionBounty.PROP_MAX_COUNT, ((AMissionBounty) m).getMaxCount()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						break;
				}
			} else {
				e.putInt(id+"_"+PROP_MISSIONS+"_"+i+"_"+AMission.PROP_TYPE, -1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
	}

	public void delete(Editor e, int id) {
		for (int i=0; i<AuraSaveGame.BASE_CICLER_MAX; i++) {
			e.putInt(id+"_"+PROP_CICLERS+"_"+i, -1); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (int i=0; i<AuraSaveGame.BASE_MISSIONS_MAX_SLOT; i++) {
			e.putInt(id+"_"+PROP_MISSIONS+"_"+AMission.PROP_TYPE, -1); //$NON-NLS-1$ //$NON-NLS-2$
		}		
	}
	
	public AMission[] run(long currentMillis) {
		AMission[] ms = getMissionsTab().toArray(new AMission[0]);
		List<AMission> mReturn = new ArrayList<AMission>();
		for (AMission m: ms) {
			if (m.getTimeEnd()<currentMillis) {
				mReturn.add(m);
				getMissionsTab().remove(m);
				if (m.getGiver() != null && m.getGiver().getCorporation() != null) {
					ACorporation c = m.getGiver().getCorporation();
					sg.getPolitics().getReputationCorporations().put(c, 
						sg.getPolitics().getReputationCorporation(c) - 1);
				}
			}
		}
		return mReturn.toArray(new AMission[0]);
	}
}