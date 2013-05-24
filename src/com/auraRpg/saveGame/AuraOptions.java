package com.auraRpg.saveGame;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AuraOptions {
	private boolean music;
	public static final String PROP_MUSIC = "isMusic"; //$NON-NLS-1$
	
	private boolean sound;
	public static final String PROP_SOUND = "isSound"; //$NON-NLS-1$
	
	private boolean vibratorFeedback;
	public static final String PROP_VIBRATOR_FEEDBACK = "isVibFb"; //$NON-NLS-1$
	
	private boolean bigTextHitbox;
	public static final String PROP_BIG_TEXT_HITBOX = "isBgTextHB"; //$NON-NLS-1$
	
	private boolean showPlayerName;
	public static final String PROP_SHOW_PLAYER_NAME = "showPlayerName"; //$NON-NLS-1$
	
	public AuraOptions() {}
	
	public boolean isMusic() {
		return music;
	}
	public void setMusic(boolean music) {
		this.music = music;
	}
	
	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	
	public boolean isVibratorFeedback() {
		return vibratorFeedback;
	}
	public void setVibratorFeedback(boolean vibratorFeedback) {
		this.vibratorFeedback = vibratorFeedback;
	}
	
	public boolean isBigTextHitbox() {
		return bigTextHitbox;
	}
	public void setBigTextHitbox(boolean bigTextHitbox) {
		this.bigTextHitbox = bigTextHitbox;
	}
	
	public boolean isShowPlayerName() {
		return showPlayerName;
	}
	public void setShowPlayerName(boolean showPlayerName) {
		this.showPlayerName = showPlayerName;
	}

	public void load(SharedPreferences p) {
		setMusic(p.getBoolean(PROP_MUSIC, true));
		setSound(p.getBoolean(PROP_SOUND, true));
		setVibratorFeedback(p.getBoolean(PROP_VIBRATOR_FEEDBACK, true));
		setBigTextHitbox(p.getBoolean(PROP_BIG_TEXT_HITBOX, false));
		setShowPlayerName(p.getBoolean(PROP_SHOW_PLAYER_NAME, true));
	}

	public void save(Editor e) {
		e.putBoolean(PROP_MUSIC, isMusic());
		e.putBoolean(PROP_SOUND, isSound());
		e.putBoolean(PROP_VIBRATOR_FEEDBACK, isVibratorFeedback());
		e.putBoolean(PROP_BIG_TEXT_HITBOX, isBigTextHitbox());
		e.putBoolean(PROP_SHOW_PLAYER_NAME, isShowPlayerName());
	}	
}