package com.auraRpg;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;

import com.auraRpg.data.ANpcSpawner;
import com.voidEngine.engine.VAbstractEngine;

public abstract class AuraSpawner {
	private boolean running;
	public boolean isRunning() {
		return running;
	}
	
	private VAbstractEngine engine;
	private Scene scene;
	
	private final float xPos;
	private final float yPos;
	private final boolean autoAgro;
	private int maxSpawn;
	private int maxCountSpawn;
	private final long timeBeforeSpawn;
	
	private TimerHandler thSpawner;
	private List<AuraEntity> spawnedEntities = new ArrayList<AuraEntity>();
	
	public AuraSpawner(VAbstractEngine e, Scene s, float xPos, float yPos, ANpcSpawner spawner) {
		this(e, s, xPos, yPos, spawner.isAutoAgro(), spawner.getMaxSpawn(), spawner.getMaxCountSpawn(), spawner.getTimeBeforeSpawn());
	}
	public AuraSpawner(VAbstractEngine e, Scene s, float xPos, float yPos, boolean autoAgro, int maxSpawn, final int maxCountSpawn, long timeBeforeSpawn) {
		this.engine = e;
		this.scene = s;
		this.xPos = xPos;
		this.yPos = yPos;
		this.autoAgro = autoAgro;
		this.maxSpawn = maxSpawn;
		this.maxCountSpawn = maxCountSpawn;
		this.timeBeforeSpawn = timeBeforeSpawn;
		
		initTimerHandler();
	}

	private void initTimerHandler() {
		this.thSpawner = new TimerHandler(timeBeforeSpawn, true, new ITimerCallback() {
//			private int maxCount = maxCountSpawn;
			private int counter = 0;
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				for (AuraEntity sae: spawnedEntities.toArray(new AuraEntity[0])) {
					if (!sae.isAlive())
						spawnedEntities.remove(sae);
				}
				if (counter < getMaxCountSpawn()) {
					if (spawnedEntities.size() < getMaxSpawn()) {
						counter++;
						AuraEntity e = initEntityToSpawn();
						if (isAutoAgro())
							e.setTarget(getEntityToTarget());
						spawnedEntities.add(e);
						e.attach();
						getEngine().log("Spawner: spawn 1 entity"); //$NON-NLS-1$
					}
				} else if (spawnedEntities.size() == 0) {
					doOnFinish();
					stop();
				}
			}
		});
	}
	
	public abstract void doOnFinish();
	
	public void start() {
		getScene().registerUpdateHandler(thSpawner);
		this.running = true;
	}
	public void stop() {
		getScene().unregisterUpdateHandler(thSpawner);
		this.running = false;
	}
	public void reset() {
		boolean lRun = isRunning();
		if (lRun) 
			stop();
		initTimerHandler();
		if (lRun)
			start();
	}

	public VAbstractEngine getEngine() {
		return engine;
	}
	public Scene getScene() {
		return scene;
	}
	
	public float getXPos() {
		return xPos;
	}
	public float getYPos() {
		return yPos;
	}
	public boolean isAutoAgro() {
		return autoAgro;
	}
	
	public int getMaxSpawn() {
		return maxSpawn;
	}
	public void setMaxSpawn(int maxSpawn) {
		this.maxSpawn = maxSpawn;
	}
	
	public int getMaxCountSpawn() {
		return maxCountSpawn;
	}
	public void setMaxCountSpawn(int maxCountSpawn) {
		this.maxCountSpawn = maxCountSpawn;
	}
	
	public long getTimeBeforeSpawn() {
		return timeBeforeSpawn;
	}
	
	public abstract AuraEntity initEntityToSpawn();
	public abstract AuraEntity getEntityToTarget();
}