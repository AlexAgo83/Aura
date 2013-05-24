package com.auraRpg;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.auraRpg.data.AItemWeapon;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.badlogic.gdx.math.Vector2;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VFont;
import com.voidEngine.engine.ressources.VTexture;

public class AuraEntityProjectile extends AuraEntity {
	private AuraPersonnageInfo owner;
	private TimerHandler thMovement;
	private float vx;
	private float vy;
	private long lifeTime;
	
	public AuraEntityProjectile(
			VAbstractEngine e, Scene s, PhysicsWorld ph,
			float x, float y, final Vector2 v, AuraPersonnageInfo owner) {
		super(e, s, ph, x-1, y-1, 2, 2);
		
		this.setOwner(owner);
		this.setVector(v);
		
		this.thMovement = new TimerHandler(0.1f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				if (isAlive())
					move(
						getVX()*getOwner().getWeapon().getProjectileSpeedModifier(), 
						getVY()*getOwner().getWeapon().getProjectileSpeedModifier());
				if (getLifeTime() < System.currentTimeMillis())
					hibernate();
			}
		});
		move(getVX(), getVY());
	}
	@Override public VTexture getFlagTexture() { return null; }
	@Override public AuraPersonnageInfo getPersonnageInfo() { return null; }
	public float getVX() {
		return vx;
	}
	public float getVY() {
		return vy;
	}
	
	public void setVector(Vector2 v) {
		this.vx = v.x;
		this.vy = v.y;
	}
	private long getLifeTime() {
		return lifeTime;
	}
	
	public AuraPersonnageInfo getOwner() {
		return owner;
	}
	public void setOwner(AuraPersonnageInfo owner) {
		this.owner = owner;
		this.lifeTime = System.currentTimeMillis()+owner.getWeapon().getProjectileLifeTime();
		for (AItemWeapon w: AItemWeapon.values()) {
			removeLayer(w.getProjectileTexture());
		}
		attachLayer(owner.getWeapon().getProjectileTexture(), 1);
	}
	
	@Override
	public void attach() {
		super.attach();
		getScene().registerUpdateHandler(thMovement);
	}
	
	@Override
	public void detach() {
		getScene().unregisterUpdateHandler(thMovement);
		super.detach();
	}
	
	@Override
	public void recycle() {
		super.recycle();
		getScene().registerUpdateHandler(thMovement);
	}
	
	@Override
	public void hibernate() {
		vx = 0; vy = 0;
		getScene().unregisterUpdateHandler(thMovement);
		super.hibernate();
	}
	
	@Override public float getMaxHealth() { return 0; }
	@Override public float getHealth() { return 0; }
	@Override public boolean isStatic() { return false; }
	@Override public VTexture getParticuleTexture() { return null; }
	@Override public VFont getTextFont() { return null; }
	@Override public String getName() { return null; }
	@Override public String getSupl() { return null; }
}