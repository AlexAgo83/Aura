package com.auraRpg.saveGame;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AuraAchievement {
	private long played;
	public static final String PROP_PLAYED = "played"; //$NON-NLS-1$
	
	private long playerDeath;
	public static final String PROP_PLAYER_DEATH = "playerDeath"; //$NON-NLS-1$
	
	private long missionTerminee;
	public static final String PROP_MISSION_TERMINEE = "missionTerminee"; //$NON-NLS-1$
	
	private long missionAnnulee;
	public static final String PROP_MISSION_ANNULEE = "missionAnnulee"; //$NON-NLS-1$
	
	private long missionFailed;
	public static final String PROP_MISSION_FAILED = "missionFailed"; //$NON-NLS-1$
	
	private long meterRun;
	public static final String PROP_METER_RUN = "meterRun"; //$NON-NLS-1$
	
	protected AuraAchievement() {}
	
	public long getPlayed() {
		return played;
	}
	public void setPlayed(long played) {
		this.played = played;
	}
	public void increasePlayed(long i) {
		setPlayed(getPlayed()+i);
	}
	
	public long getPlayerDeath() {
		return playerDeath;
	}
	public void setPlayerDeath(long playerDeath) {
		this.playerDeath = playerDeath;
	}
	public void increasePlayerDeath() {
		setPlayerDeath(getPlayerDeath()+1);
	}
	
	public long getMissionTerminee() {
		return missionTerminee;
	}
	public void setMissionTerminee(long missionTerminee) {
		this.missionTerminee = missionTerminee;
	}
	public void increaseMissioneTerminee() {
		setMissionTerminee(getMissionTerminee() + 1);
	}
	
	public long getMissionAnnulee() {
		return missionAnnulee;
	}
	public void setMissionAnnulee(long missionAnnulee) {
		this.missionAnnulee = missionAnnulee;
	}
	public void increaseMissioneAnnulee() {
		setMissionAnnulee(getMissionAnnulee()+1);
	}
	
	public long getMissionFailed() {
		return missionFailed;
	}
	public void setMissionFailed(long missionFailed) {
		this.missionFailed = missionFailed;
	}
	public void increaseMissioneFailed() {
		setMissionFailed(getMissionFailed()+1);
	}
	
	public long getMeterRun() {
		return meterRun;
	}
	public void setMeterRun(long meterRun) {
		this.meterRun = meterRun;
	}
	public void increaseMeterRun(long meterRun) {
		setMeterRun(getMeterRun()+meterRun);
	}
	
	public void load(SharedPreferences p) {
		setPlayed(p.getLong(PROP_PLAYED, 0));
		setPlayerDeath(p.getLong(PROP_PLAYER_DEATH, 0));
		setMissionTerminee(p.getLong(PROP_MISSION_TERMINEE, 0));
		setMissionAnnulee(p.getLong(PROP_MISSION_ANNULEE, 0));
		setMissionFailed(p.getLong(PROP_MISSION_FAILED, 0));
		setMeterRun(p.getLong(PROP_METER_RUN, 0));
	}
	public void save(Editor e) {
		e.putLong(PROP_PLAYED, getPlayed());
		e.putLong(PROP_PLAYER_DEATH, getPlayerDeath());
		e.putLong(PROP_MISSION_TERMINEE, getMissionTerminee());
		e.putLong(PROP_MISSION_ANNULEE, getMissionAnnulee());
		e.putLong(PROP_MISSION_FAILED, getMissionFailed());
		e.putLong(PROP_METER_RUN, getMeterRun());
	}
}