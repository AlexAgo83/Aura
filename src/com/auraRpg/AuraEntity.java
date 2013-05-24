package com.auraRpg;

import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraPreferences;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.entity.VAbstractPhysicsEntity;
import com.voidEngine.engine.ressources.VFont;
import com.voidEngine.engine.ressources.VTexture;

public abstract class AuraEntity extends VAbstractPhysicsEntity {
	private float id;
	private TimerHandler thRecorder;
	private AuraEntity target;
	private Sprite flag;
	
	public AuraEntity(VAbstractEngine e, Scene s, PhysicsWorld ph, float x, float y, float w, float h) {
		super(e, s, ph, x, y, w, h);
		this.id = (float) ((Math.random()+.1)*1000+(Math.random()+.1)*1000);
		if (getFlagTexture() != null && getTextName() != null) {
			flag = new Sprite(
				0, 0, 
				getEngine().getVRessourceManager().getTexture(getFlagTexture()));
			flag.setScale(
				((AuraPreferences) getEngine().getVPreferences()).getOption().isBigTextHitbox() ?
					1 : .75f);
			flag.setPosition(
				getTextName().getX() - flag.getWidth() - 1, 
				getTextName().getY() 
					+ getTextName().getHeight() / 2
					- flag.getHeight() / 2);
		}
	}
	
	public float getId() {
		return id;
	}
	public AuraEntity getTarget() {
		return target;
	}
	public void setTarget(AuraEntity target) {
		this.target = target;
	}
	
	public Sprite getFlag() {
		return flag;
	}
	
	public abstract AuraPersonnageInfo getPersonnageInfo();
	public abstract VTexture getFlagTexture();
	
	@Override
	public String getName() {
		return getPersonnageInfo() != null ? getPersonnageInfo().getName() : null;
	}
	@Override
	public float getHealth() {
		return getPersonnageInfo() != null ? getPersonnageInfo().getHealth() : 0;
	}
	@Override
	public float getMaxHealth() {
		return getPersonnageInfo() != null ? getPersonnageInfo().getSanteMax() : 0;
	}
	
	@Override public boolean onTouchRelease(float x, float y) { return false; }
	@Override public boolean onTouchPressed(float x, float y) { return false; }
	@Override public boolean onTouchOutside(float x, float y) { return false; }
	@Override public boolean onTouchMove(float x, float y) { return false; }
	@Override public boolean onTouchCancel(float x, float y) { return false; }
	
	@Override public float getLinearDamping() { return 1f; }
	@Override public float getFriction() { return 1f; }
	@Override public float getElasticity() { return 0f; }
	@Override public float getDensity() { return 1f; }
	
	@Override public VTexture getParticuleTexture() { return AuraTextureChooser.PARTICULE_SMOKE.getTexture(); }
	@Override public VFont getTextFont() {
		if (((AuraPreferences) getEngine().getVPreferences()).getOption().isBigTextHitbox())
			return AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont();
		return AuraFontChooser.SYSTEM_FONT_X_SMALL.getFont(); 
	}
	
	public void setRecorder(TimerHandler thRecorder) {
		this.thRecorder = thRecorder;
	}
	public TimerHandler getRecorder() {
		return thRecorder;
	}
	
	@Override
	public void attach() {
		super.attach();
		if (getRecorder() != null)
			getScene().registerUpdateHandler(getRecorder());
		if (getFlag() != null)
			getHitBox().attachChild(getFlag());
	}
	
	@Override
	public void detach() {
		if (getRecorder() != null)
			getScene().unregisterUpdateHandler(getRecorder());
		if (getFlag() != null)
			getHitBox().detachChild(getFlag());
		super.detach();
	}
	
	@Override
	public void recycle() {
		super.recycle();
		if (getRecorder() != null)
			getScene().registerUpdateHandler(getRecorder());
	}
	
	@Override
	public void hibernate() {
		if (getRecorder() != null)
			getScene().unregisterUpdateHandler(getRecorder());
		super.hibernate();
	}
	
	@Override
	public boolean isAlive() {
		if (getMaxHealth() > 0 && getHealth() <= 0)
			return false;
		return super.isAlive();
	}
}