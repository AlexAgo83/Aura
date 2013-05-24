
package com.auraRpg;

import android.content.SharedPreferences.Editor;

import com.auraRpg.gui.AuraCheckBox;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.saveGame.AuraOptions;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;

public class AMenuSystemOptionSceneStrategy extends AMenuSystemSceneStrategy {
	public AMenuSystemOptionSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_OPTION);
	}
	
	private AuraCheckBox ckMusic;
	private AuraCheckBox ckSound;
	private AuraCheckBox ckVibratorAndFeedback;
	private AuraCheckBox ckBigTextHitbox;
	private AuraCheckBox ckShowPlayerName;
	
	@Override
	public void onCreate() {
		super.onCreate();
		float nextY = getTxTitre().getHeight() + 5;
		this.ckMusic = new AuraCheckBox(getEngine(), getScene(), 165, nextY, 400, 40) {
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_OPTION_CK_MUSIC.getMessage(getEngine());
			}
			@Override
			public boolean onTouchRelease(float x, float y) {
				super.onTouchRelease(x, y);
				AuraOptions o = getAuraPreferences().getOption();
				o.setMusic(ckMusic.isCheckedValue());
				Editor e = getAuraPreferences().getPreferences().edit();
				o.save(e);
				e.commit();
				return true;
			}
		};
		ckMusic.attach();
		nextY += 55;
		this.ckSound = new AuraCheckBox(getEngine(), getScene(), 165, nextY, 400, 40) {
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_OPTION_CK_SOUND.getMessage(getEngine());
			}
			@Override
			public boolean onTouchRelease(float x, float y) {
				super.onTouchRelease(x, y);
				AuraOptions o = getAuraPreferences().getOption();
				o.setSound(ckSound.isCheckedValue());
				Editor e = getAuraPreferences().getPreferences().edit();
				o.save(e);
				e.commit();
				return true;
			}
		};
		ckSound.attach();
		nextY += 55;
		this.ckVibratorAndFeedback = new AuraCheckBox(getEngine(), getScene(), 165, nextY, 400, 40) {
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_OPTION_CK_VIBRATOR_FEEDBACK.getMessage(getEngine());
			}
			@Override
			public boolean onTouchRelease(float x, float y) {
				super.onTouchRelease(x, y);
				AuraOptions o = getAuraPreferences().getOption();
				o.setVibratorFeedback(ckVibratorAndFeedback.isCheckedValue());
				Editor e = getAuraPreferences().getPreferences().edit();
				o.save(e);
				e.commit();
				return true;
			}
		};
		ckVibratorAndFeedback.attach();
		nextY += 55;
		this.ckBigTextHitbox = new AuraCheckBox(getEngine(), getScene(), 165, nextY, 400, 40) {
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_OPTION_CK_BIG_TEXT.getMessage(getEngine());
			}
			@Override
			public boolean onTouchRelease(float x, float y) {
				super.onTouchRelease(x, y);
				AuraOptions o = getAuraPreferences().getOption();
				o.setBigTextHitbox(ckBigTextHitbox.isCheckedValue());
				Editor e = getAuraPreferences().getPreferences().edit();
				o.save(e);
				e.commit();
				return true;
			}
		};
		ckBigTextHitbox.attach();
		nextY += 55;
		this.ckShowPlayerName = new AuraCheckBox(getEngine(), getScene(), 165, nextY, 400, 40) {
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_OPTION_CK_SHOW_PLAYERS_NAME.getMessage(getEngine());
			}
			@Override
			public boolean onTouchRelease(float x, float y) {
				super.onTouchRelease(x, y);
				AuraOptions o = getAuraPreferences().getOption();
				o.setShowPlayerName(ckShowPlayerName.isCheckedValue());
				Editor e = getAuraPreferences().getPreferences().edit();
				o.save(e);
				e.commit();
				return true;
			}
		};
		ckShowPlayerName.attach();
	}
	@Override
	public void onRefresh() {
		AuraOptions o = getAuraPreferences().getOption();
		ckMusic.setCheckedValue(o.isMusic());
		ckSound.setCheckedValue(o.isSound());
		ckVibratorAndFeedback.setCheckedValue(o.isVibratorFeedback());
		ckBigTextHitbox.setCheckedValue(o.isBigTextHitbox());
		ckShowPlayerName.setCheckedValue(o.isShowPlayerName());
		super.onRefresh();
	}
	@Override
	public void onRemove() {
		ckMusic.detach();
		ckSound.detach();
		ckVibratorAndFeedback.detach();
		ckBigTextHitbox.detach();
		ckShowPlayerName.detach();
		super.onRemove();
	}
}