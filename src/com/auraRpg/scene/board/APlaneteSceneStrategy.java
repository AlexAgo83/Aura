package com.auraRpg.scene.board;

import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;

import android.opengl.GLES10;

import com.auraRpg.AuraEntity;
import com.auraRpg.AuraEntityProjectile;
import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AItemArmor;
import com.auraRpg.data.AItemWeapon;
import com.auraRpg.data.AMap;
import com.auraRpg.data.AMapType;
import com.auraRpg.data.ANpc;
import com.auraRpg.data.ANpcSpawner;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AAbstractBoardSceneStrategy;
import com.auraRpg.scene.AContextSceneMemory;
import com.badlogic.gdx.physics.box2d.Body;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.map.VTile;
import com.voidEngine.engine.ressources.VTexture;

public class APlaneteSceneStrategy extends AAbstractBoardSceneStrategy {
	public APlaneteSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AMap map) {
		super(engine, contextMemory, map);
	}
	
	private AnalogOnScreenControl aoscMovement;
	private AnalogOnScreenControl aoscAction;
	
	@Override
	public AuraPersonnageInfo getSuperNpcInfo(ANpcSpawner spawner, ANpc npc) {
		AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
		ANpc npcToSpawn = npc;
		if (Math.random() > .25d && getMapPrinter().getMap().getType().equals(AMapType.SHIP)) {
			ACorporation c = sg.getPolitics().getRandomBadReputation();
			if (c != null) {
				ANpc[] npcByC = ANpc.getByCorporation(c);
				if (npcByC != null && npcByC.length > 0)
					npcToSpawn = npcByC[(int) Math.round((npcByC.length-1)*Math.random())];
			}
		}
		
		float maxHealth = npcToSpawn.getMaxHealth() + sg.getRatioNPCModifier();
		AItemWeapon w = npcToSpawn.getWeapon();
		AItemArmor a = AItemArmor.BASIC;
		return new AuraPersonnageInfo(npcToSpawn.getName(), maxHealth, maxHealth, w, a);
	}
	
	@Override
	public boolean onSuperTouchTile(VTile t) {
		return false;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	@Override
	public VTexture getTextureCadre() {
		return AuraTextureChooser.GUI_CADRE.getTexture();
	}
	
	@Override
	public void onRefresh() {
		initControler();
		super.onRefresh();
	}
	
	@Override
	public void onRemove() {
		super.onRemove();
		aoscMovement.detachChild(aoscAction);
		getHUD().detachChild(aoscMovement);
	}
	
	@Override
	public void onContact(final Body eA, final Body eB) {
		AuraEntity entityA = null;
		AuraEntity entityB = null;
		
		if (eA != null && eA.getUserData() instanceof AuraEntity)
			entityA = (AuraEntity) eA.getUserData();
		if (eB != null && eB.getUserData() instanceof AuraEntity)
			entityB = (AuraEntity) eB.getUserData();
		
		// PROJECTILES
		AuraPersonnageInfo projOwner = null;
		AuraEntity entityShot = null;
		if (entityB != null && entityB instanceof AuraEntityProjectile && entityB.isAlive()) {
			projOwner = ((AuraEntityProjectile) entityB).getOwner();
			entityB.hibernate();
			if (entityA != null && entityA.isAlive())
				entityShot = entityA;
		} 
		if (entityA != null && entityA instanceof AuraEntityProjectile && entityA.isAlive()) {
			projOwner = ((AuraEntityProjectile) entityA).getOwner();
			entityA.hibernate();
			if (entityB != null && entityB.isAlive())
				entityShot = entityB;
		}
		if (projOwner != null && entityShot != null) {
			getEngine().log("PROJECTILE HIT ENTITY!"); //$NON-NLS-1$
			getMapPrinter().projectileHitEntity(projOwner, entityShot);
		}
	}
	
	private IAnalogOnScreenControlListener iasAction;
	private IAnalogOnScreenControlListener iasMovement;
	
	private void initControler() {
		if (aoscAction!=null) {
			aoscMovement.detachChild(aoscAction);
			aoscAction.clearUpdateHandlers();
			aoscAction.getControlKnob().clearUpdateHandlers();
		}
		if (aoscAction!=null) {
			getHUD().detachChild(aoscMovement);
			aoscMovement.clearUpdateHandlers();
			aoscMovement.getControlKnob().clearUpdateHandlers();
		}
		
		// ACTION
		this.iasAction = new IAnalogOnScreenControlListener() {
			private long lastTick;
			@Override
			public void onControlChange(BaseOnScreenControl pBosc, float pValueX, float pValueY) {
				long curr = System.currentTimeMillis();
				AItemWeapon w = getMapPrinter().getPlayer().getPersonnageInfo().getWeapon();
				if (lastTick <= curr) {
					lastTick = w.getProjectileRate() + curr;
					getMapPrinter().shotEntity(getMapPrinter().getPlayer(), pValueX, pValueY);
				}
			}
			@Override
			public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {}
		};
		this.aoscAction = new AnalogOnScreenControl(
			0, 0, getEngine().getVCamera(),
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_ANALOG_BG.getTexture()),
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_ANALOG_KNOB.getTexture()),
			.1f, iasAction);
		this.aoscAction.setPosition(
			getEngine().getVProperties().getCameraScreenWidth()-(aoscAction.getControlBase().getWidth()+16), 
			getEngine().getVProperties().getCameraScreenHeight()-(aoscAction.getControlBase().getHeight()+16));
		this.aoscAction.getControlKnob().setAlpha(.5f);
		this.aoscAction.getControlKnob().setZIndex(1000);
		this.aoscAction.getControlKnob().setBlendFunction(GLES10.GL_SRC_ALPHA, GLES10.GL_ONE_MINUS_SRC_ALPHA);
		
		// MOVEMENT
		this.iasMovement = new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(BaseOnScreenControl pBosc, float pValueX, float pValueY) {
				getMapPrinter().moveEntity(getMapPrinter().getPlayer(), pValueX, pValueY);
			}
			@Override
			public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {}
		};
		this.aoscMovement = new AnalogOnScreenControl(
			0, 0, getEngine().getVCamera(),
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_ANALOG_BG.getTexture()),
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_ANALOG_KNOB.getTexture()),
			.1f, iasMovement);
		this.aoscMovement.setPosition(
			16, 
			getEngine().getVProperties().getCameraScreenHeight()
				-(aoscMovement.getControlBase().getHeight()+16));
		this.aoscMovement.getControlKnob().setAlpha(.5f);
		this.aoscMovement.getControlKnob().setZIndex(1000);
		this.aoscMovement.getControlKnob().setBlendFunction(GLES10.GL_SRC_ALPHA, GLES10.GL_ONE_MINUS_SRC_ALPHA);
		
		getHUD().setChildScene(aoscMovement);
		aoscMovement.setChildScene(aoscAction);
		
		aoscMovement.refreshControlKnobPosition();
		aoscAction.refreshControlKnobPosition();
	}
}