package com.auraRpg.scene.menu;

import com.auraRpg.gui.AuraButton;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.scene.AAbstractMenuSceneStrategy;
import com.auraRpg.scene.AContextSceneMemory;
import com.badlogic.gdx.physics.box2d.Body;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractButton;

public abstract class AMenuSystemSceneStrategy extends AAbstractMenuSceneStrategy {
	public AMenuSystemSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AuraI18nChooser i18nId) {
		super(engine, contextMemory, i18nId);
	}
	
	private VAbstractButton btQuit;
	private VAbstractButton btPlay;
	private VAbstractButton btOption;
	
	private VAbstractButton btAchievement;
	private VAbstractButton btChangeLogs;
	private VAbstractButton btAbout;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.btQuit = new AuraButton(getEngine(), getScene(), 0, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().finish();
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_QUIT.getMessage(getEngine());
			}
		};
		this.btPlay = new AuraButton(getEngine(), getScene(), 0, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getPlayScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_PLAY.getMessage(getEngine());
			}
		};
		this.btOption = new AuraButton(getEngine(), getScene(), 0, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getOptionScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_OPTION.getMessage(getEngine());
			}
		};
		this.btAchievement = new AuraButton(getEngine(), getScene(), 640, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getAchievementScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_ACHIEVEMENT.getMessage(getEngine());
			}
		};
		this.btChangeLogs = new AuraButton(getEngine(), getScene(), 640, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getChangeLogsScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_CHANGE_LOGS.getMessage(getEngine());
			}
		};
		this.btAbout = new AuraButton(getEngine(), getScene(), 640, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getAboutScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SYSTEM_BT_ABOUT.getMessage(getEngine());
			}
		};

		btQuit.attach();
		btPlay.attach();
		btOption.attach();
		btAchievement.attach();
		btChangeLogs.attach();
		btAbout.attach();
	}
	@Override
	public void onRefresh() {
		super.onRefresh();
	}
	@Override
	public void onRemove() {
		super.onRemove();
		btPlay.detach();
		btOption.detach();
		btQuit.detach();
		btAchievement.detach();
		btChangeLogs.detach();
		btAbout.detach();
	}
	@Override public void onContact(Body eA, Body eB){}
}