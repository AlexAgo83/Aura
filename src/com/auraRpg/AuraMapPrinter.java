package com.auraRpg;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;

import com.auraRpg.data.AAgent;
import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AItemGoods;
import com.auraRpg.data.AMap;
import com.auraRpg.data.AMapType;
import com.auraRpg.data.AMission;
import com.auraRpg.data.AMissionBounty;
import com.auraRpg.data.AMissionCatalog;
import com.auraRpg.data.AMissionDelivery;
import com.auraRpg.data.AMissionType;
import com.auraRpg.data.ANpc;
import com.auraRpg.data.ANpcSpawner;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraPreferences;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.saveGame.AuraSkills;
import com.auraRpg.saveGame.AuraVendorInfo;
import com.auraRpg.scene.AAbstractBoardSceneStrategy;
import com.auraRpg.scene.AAbstractTransitionSceneStrategy;
import com.auraRpg.scene.board.APlaneteSceneStrategy;
import com.auraRpg.scene.board.ASpaceSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGDoctorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGVendorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGViewMissionSceneStrategy;
import com.badlogic.gdx.math.Vector2;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.entity.VAbstractEntity;
import com.voidEngine.engine.map.VAbstractMapPrinter;
import com.voidEngine.engine.map.VPointF;
import com.voidEngine.engine.map.VTile;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.ressources.VTileTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;

public abstract class AuraMapPrinter extends VAbstractMapPrinter {
	private AuraEntity player;
	private List<AuraEntity> entities = new ArrayList<AuraEntity>();
	private List<AuraSpawner> spawners = new ArrayList<AuraSpawner>();
	private List<AuraEntityProjectile> projectilesPool = new ArrayList<AuraEntityProjectile>();
	
	private final VAbstractSceneStrategy strategy;
	private final AMap map;
	public AuraMapPrinter(VAbstractEngine engine, VAbstractSceneStrategy strategy, AMap map) {
		super(engine, strategy.getScene(), strategy.getPhysicsWorld(), map.getFileName(), map.getPathSD());
		this.strategy = strategy;
		this.map = map;
	}

	@Override
	public VTexture getBackground() {
		getEngine().log("NEWMAP:"+map.getType());
		if (AMapType.ESCARMOUCHE.equals(map.getType())
				&& getAuraPreferences().getCurrentSaveGame().getLastMap() != null) {
			getEngine().log("LASTMAP:"+getAuraPreferences().getCurrentSaveGame().getLastMap().getName());
			getEngine().log("BG:"+getAuraPreferences().getCurrentSaveGame().getLastMap().getBackground());
			return getAuraPreferences().getCurrentSaveGame().getLastMap().getBackground();
		}
		return map.getBackground();
	}
	
	public AMap getMap() {
		return map;
	}
	
	public VAbstractSceneStrategy getStrategy() {
		return strategy;
	}
	
	public synchronized AuraEntity getPlayer() {
		return player;
	}
	public synchronized List<AuraEntity> getEntities() {
		return entities;
	}
	public synchronized List<AuraEntityProjectile> getProjectilesPool() {
		return projectilesPool;
	}
	
	protected AuraEngine getAuraEngine() {
		return (AuraEngine) getEngine();
	}
	protected AuraPreferences getAuraPreferences() {
		return (AuraPreferences) getAuraEngine().getVPreferences();
	}
	
	public abstract AuraPersonnageInfo getNpcInfo(ANpcSpawner spawner, ANpc surcharge);
	
	@Override
	public RectangularShape onDataCodeDecors(VTile t, float baseX, float baseY, int dataDecors) {
		int baseId = 0;
		switch (getMap().getType()) {
			case PLANETE:
			case ESCARMOUCHE:
			case SHIP:
				baseId = 1000;
				break;
			case SPACE:
				baseId = 1100;
				break;
		}
		AuraTextureChooser tr = AuraTextureChooser.getById(dataDecors+baseId);
		if (tr != null) {
			return creerTile(t, baseX, baseY, tr.getTexture(), tr.isSolid());
		}
		return new Rectangle(0, 0, 64, 64);
	}
	@Override
	public void onDataCodeSpawner(VTile t, float baseX, float baseY, int dataSpawner) {
		final ANpcSpawner nSpawner = ANpcSpawner.getById(dataSpawner);
		if (nSpawner == null)
			return;
		AuraSpawner sp = new AuraSpawner(getEngine(), getScene(), baseX, baseY, nSpawner) {
			@Override
			public AuraEntity getEntityToTarget() {
				return getPlayer();
			}
			@Override
			public void doOnFinish() {
				if (getMap().getType().equals(AMapType.SHIP)) {
					final AMap newMap = getMap().getPlaneteRef();
					getAuraPreferences().getCurrentSaveGame().setMap(newMap);
					getAuraPreferences().getCurrentSaveGame().setX(newMap.getXPosition());
					getAuraPreferences().getCurrentSaveGame().setY(newMap.getYPosition());
					getAuraPreferences().getCurrentSaveGame().save();
					AAbstractTransitionSceneStrategy tran = new AAbstractTransitionSceneStrategy(getEngine(), 
						((AAbstractBoardSceneStrategy) getStrategy()).getContextMemory(), AuraI18nChooser.TITRE_WELL_DONE) {
						@Override
						public boolean onContinue() {
							AMap.launchMap(getEngine(), getContextMemory(), getAuraPreferences().getCurrentSaveGame(), newMap);
							return true;
						}
						@Override
						public String[] getDescription() {
							return new String[] {
								AuraI18nChooser.GUI_TRAVEL_INFO_DONE.getMessage(getEngine(), newMap.getName()) 
							};
						}
						@Override public String getDialogText() { return null; }
					};
					if (getEngine().getVSceneManager().getLast() != null)
						getEngine().getVSceneManager().getLast().onUnload();
					getEngine().getVSceneManager().switchTo(tran, false, false, false);
				}
			}
			
			@Override
			public AuraEntity initEntityToSpawn() {
				ANpc[] npcs = nSpawner.getNpcToSpawn();
				ANpc npcToSpawn = npcs[(int) Math.round((npcs.length-1)*Math.random())];
				AMissionBounty bounty = null;
				if (getMap().getType() == AMapType.ESCARMOUCHE) {
					for (AMission m: getAuraPreferences().getCurrentSaveGame().getMissions().getMissionsByType(AMissionType.BOUNTY)) {
						if (getMap().equals(m.getEscaCible())) {
							bounty = ((AMissionBounty) m);
							npcToSpawn = ((AMissionBounty) m).getTarget();
							setMaxCountSpawn(bounty.getMaxCount());
							break;
						}
					}
				}
				final AuraPersonnageInfo pi = getNpcInfo(nSpawner, npcToSpawn);
				final AuraEntity e = new AuraEntity(
						getEngine(), getScene(), getPhysicsWorld(), 
						getXPos(), getYPos(), 32, 32) {
					@Override public VTexture getFlagTexture() { return null; }
					@Override public boolean isStatic() { return false; }
					@Override public AuraPersonnageInfo getPersonnageInfo() {
						pi.setEntityInGame(this);
						return pi; 
					}
					@Override public String getSupl() { return null; }
				};
				e.setUserData(npcToSpawn);
				e.attachLayer(npcToSpawn.getSkin(), 10);
				TimerHandler thRecorder = new TimerHandler(.1f, true, new ITimerCallback() {
					private long lastShotTick;
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						if (e.getTarget() != null) {
							if (!e.getTarget().isAlive()) {
								e.setTarget(null);
								return;
							}
							Vector2 target = e.getTarget().getPositionCenter();
							Vector2 entity = e.getPositionCenter();
							Vector2 v = target.sub(entity);
							float dist = target.dst(entity);
							float deg = VAbstractEntity.vectorToDeg(v.x, v.y);
							VPointF pt = e.getNearestPointTrigo(deg);
							if (dist > 100)
								moveEntity(e, pt.getX(), pt.getY());
							
							long curr = System.currentTimeMillis();
							if (lastShotTick < curr) {
								lastShotTick = curr + e.getPersonnageInfo().getWeapon().getProjectileRate() + 500;
								shotEntity(e, v.x, v.y);
							}
							
							Vector2Pool.recycle(target);
							Vector2Pool.recycle(entity);
						} else 
							moveEntity(e, 
								(float) (1-(Math.random()*2)), 
								(float) (1-(Math.random()*2)));
					}
				});
				e.setRecorder(thRecorder);
				entities.add(e);
				return e;
			}
		};
		spawners.add(sp);
		sp.start();
	}
	@Override
	public void onDataCodeEntity(VTile t, float baseX, float baseY, int dataEntity) {
		final AAgent a = AAgent.getById(dataEntity);
		if (a != null) {
			final AuraPersonnageInfo pi = new AuraPersonnageInfo(a.getName());
			final AuraEntity e = new AuraEntity(
					getEngine(), getScene(), getPhysicsWorld(), 
					baseX, baseY, 32, 32) {
				@Override
				public VTexture getFlagTexture() {
					return a.getCorporation() != null ? 
							a.getCorporation().getFlag() : null;
				}
				@Override
				public boolean onTouchRelease(float x, float y) {
					Vector2 va = getPositionCenter();
					Vector2 vb = getPlayer().getPositionCenter();
					float distance = va.dst(vb);
					Vector2Pool.recycle(va);
					Vector2Pool.recycle(vb);
					getEngine().log("Click on agent, distance="+distance); //$NON-NLS-1$
					getEngine().log("Mission: slots left="+getAuraPreferences().getCurrentSaveGame().getMissions().getMissionsSlotLeft()); //$NON-NLS-1$

					AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
					
					boolean allowedReputation = true;
					switch (a.getType()) {
						case MISSION_AGENCY:
						case TRAVEL_AGENCY:
							break;
						case DOCTOR:
						case LIFT_MASTER:
						case VENDOR:
							if (a.getCorporation() != null) {
								Integer reput = sg.getPolitics().getReputationCorporation(a.getCorporation());
								if (reput != null && reput < 0)
									allowedReputation = false;
							}
							break;
					}
					
					
					if (distance > 128) {
						getStrategy().registerDialog(
							AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_ENTITY_CLICK_TOO_FAR.getMessage(getEngine()), 
								getStrategy().getHUD())
						);
					} if (!allowedReputation) {
						getStrategy().registerDialog(
							AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_ENTITY_CLICK_REP_NEGATIVE.getMessage(getEngine()), 
								getStrategy().getHUD())
						);
					} else {
						// Missions
						for (AMissionType t: AMissionType.values()) {
							switch (t) {
								case DELIVERY:
									for (AMission m: getAuraPreferences().getCurrentSaveGame().getMissions().getMissionsByType(t)) {
										if (a.getId() == ((AMissionDelivery) m).getTarget().getId()) {
											getAuraPreferences().getCurrentSaveGame().getMissions().terminerMission(getEngine(), m);
											if (getStrategy() instanceof APlaneteSceneStrategy) {
												getEngine().getVSceneManager().switchTo(
													((APlaneteSceneStrategy) getStrategy()).getContextMemory().getEndMissionScene(m), 
													false, true, false);
												return true;
											}
											
										}
									}
									break;
								case BOUNTY:
									break;
							}
						}
						// Autres
						switch (a.getType()) {
							case MISSION_AGENCY:
								if (getAuraPreferences().getCurrentSaveGame().getMissions().getMissionsSlotLeft()>0) {
									AMenuIGViewMissionSceneStrategy m = ((APlaneteSceneStrategy) getStrategy()).getContextMemory().getViewMissionScene();
									m.setMission(AMission.genererMission(getEngine(), a, AMissionCatalog.getRandom(getEngine())), true);
									getEngine().getVSceneManager().switchTo(m, false, true, false);
								} else {
									getStrategy().registerDialog(AuraDialog.creerDialog(getEngine(), 
										AuraI18nChooser.DIALOG_MISSION_NO_MORE_SLOT.getMessage(getEngine()), 
										getStrategy().getHUD()));
								}
								break;
							case TRAVEL_AGENCY:
								if (sg.getInventory().getCredits() < AuraSaveGame.BASE_TRAVEL_COST) {
									getStrategy().registerDialog(
										AuraDialog.creerDialog(getEngine(), 
											AuraI18nChooser.DIALOG_NOT_ENOUGH_CREDITS.getMessage(getEngine()), 
											getStrategy().getHUD())
									);
								} else {
									if (a.getMapSwitch() != null && a.getMapSwitch().length > 0) {
										sg.getInventory().setCredits(sg.getInventory().getCredits()-AuraSaveGame.BASE_TRAVEL_COST);
										ASpaceSceneStrategy agency = new ASpaceSceneStrategy(getEngine(), 
												((APlaneteSceneStrategy) getStrategy()).getContextMemory(),
												a.getMapSwitch());
										getEngine().getVSceneManager().switchTo(agency, false, true, false);
										agency.registerDialog(
											AuraDialog.creerDialog(getEngine(), 
												AuraI18nChooser.DIALOG_TRAVEL_COST.getMessage(getEngine(), 
														""+AuraSaveGame.BASE_TRAVEL_COST), 
												agency.getHUD())
										);
									}
								}
								break;
							case LIFT_MASTER:
//								if (a.getMapSwitch() != null && a.getMapSwitch().length > 0) {
//									AMenuIGTeleportSceneStrategy agency = ((APlaneteSceneStrategy) getStrategy()).getContextMemory()
//										.getTravelAgencyScene(a.getMapSwitch(),
//										AuraI18nChooser.TITRE_LIFT.getMessage(getEngine()));
//									getEngine().getVSceneManager().switchTo(agency, false, true, false);
//								}
								break;
							case VENDOR:
								ACorporation corpOwner = a.getCorporation();
								AMenuIGVendorSceneStrategy vendors = ((APlaneteSceneStrategy) getStrategy()).getContextMemory().getVendorScene(corpOwner);
								AuraVendorInfo v = getAuraPreferences().getCurrentSaveGame().getVendorInfo().getByEntityId(a.getId());
								if (v == null)
									v = getAuraPreferences().getCurrentSaveGame().getVendorInfo()
										.genererVendor(getEngine(), a.getId(), false);
								getEngine().log("vendor info="+v);
								vendors.setInfo(v);
								getEngine().getVSceneManager().switchTo(vendors, false, true, false);
								break;
							case DOCTOR:
								AMenuIGDoctorSceneStrategy doc = ((APlaneteSceneStrategy) getStrategy()).getContextMemory().getDoctorScene();
								getEngine().getVSceneManager().switchTo(doc, false, true, false);
								break;
						}
						getAuraPreferences().getCurrentSaveGame().save();
					}
					return true;
				}
				@Override public String getSupl() { return a.getType().getLibelle(getEngine()); }
				@Override public boolean isStatic() { return true; }
				@Override public AuraPersonnageInfo getPersonnageInfo() { return pi; }
			};
			e.setUserData(a);
			e.attachLayer(a.getSkin(), 10);
			e.setRotation(a.getRotation());
			entities.add(e);
			e.attach();
		}
	}

	public AuraEntity initPlayer() {
		this.player = new AuraEntity(getEngine(), getScene(), getPhysicsWorld(), 
				getAuraPreferences().getCurrentSaveGame().getX(), 
				getAuraPreferences().getCurrentSaveGame().getY(),
				32, 32) {
			@Override public VTexture getFlagTexture() { return null; }
			@Override public boolean isStatic() { return false; }
			@Override public String getSupl() { return null; }
			@Override public String getName() {
				if (getAuraPreferences().getOption().isShowPlayerName())
					return super.getName();
				return null;
			}
			@Override public AuraPersonnageInfo getPersonnageInfo() {
				AuraPersonnageInfo pi = getAuraPreferences().getCurrentSaveGame().getPersonnageInfo();
				pi.setEntityInGame(this);
				return pi;
			}
		};
		player.attachLayer(getAuraPreferences().getCurrentSaveGame().getPersonnageInfo().getArmor().getSkin(), 10);
		player.getHitBox().setZIndex(10);
		player.attach();
		return player;
	}
	
	@Override
	public void clear() {
		player.detach();
		for (AuraSpawner sp: spawners) {
			sp.stop();
		}
		for (AuraEntity e: entities) {
			e.detach();
		}
		for (AuraEntityProjectile p: projectilesPool) {
			p.detach();
		}
		super.clear();
	}

	public void moveEntity(AuraEntity e, float pValueX, float pValueY) {
		e.move(pValueX*5, pValueY*5);
		if (pValueX != 0 && pValueY != 0) {
			e.startAnimateLayer(AuraTileTextureChooser.ENTITY_PLAYER_GRIS.getTileTexture());
			Vector2 positionNew = e.getPosition();
			getAuraPreferences().getCurrentSaveGame().setX(positionNew.x);
			getAuraPreferences().getCurrentSaveGame().setY(positionNew.y);
			Vector2Pool.recycle(positionNew);
		}
	}
	
	public void shotEntity(final AuraEntity e, final float pValueX, final float pValueY) {
		if (pValueX != 0 && pValueY != 0) {
			e.repeatLastAnimation();
			getScene().registerUpdateHandler(new TimerHandler(0.05f, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					final Float deg = e.shot(pValueX*5, pValueY*5);
					if (deg != null) {
						VPointF p = e.getNearestPoint(deg);
						Vector2 v = e.getNearestVector(deg);
						v.x = v.x * 10; 
						v.y = v.y * 10;
						obtainProjectile(p.getX(), p.getY(), v, e.getPersonnageInfo());
						Vector2Pool.recycle(v);
					}
				}
			}));
		}
	}
	
	public void projectileHitEntity(AuraPersonnageInfo owner, AuraEntity entity) {
		AuraPersonnageInfo piEntity = entity.getPersonnageInfo();
		AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
		
		if ((owner == null || owner.getSg()==null) && (piEntity == null || piEntity.getSg()==null))
			return; // FFA entre NPC
		
		float dm = owner.getDegats();
		if (owner.getSg() != null) {
			dm += dm * owner.getSg().getSkills().getAccurencyFactor();
		} else { // SI NPC alors on réduit un peu les dégats ET on ajoute le facteur de difficulte
			dm -= new BigDecimal((dm * .25) + (sg.getRatioNPCModifier() / 4))
				.setScale(1, RoundingMode.DOWN)
				.floatValue();
		}
		if (entity.getPersonnageInfo() != null) {
			dm -= new BigDecimal(dm*entity.getPersonnageInfo().getResistance())
				.setScale(1, RoundingMode.DOWN)
				.floatValue();
		}
		
		if (entity.isAlive() && entity.getPersonnageInfo() != null)
			entity.getPersonnageInfo().damage(dm);
		if (owner.getEntityInGame() != null)
			entity.setTarget(owner.getEntityInGame());
		if (!entity.isAlive()) {
			if (entity.equals(getPlayer())) {
				((AAbstractBoardSceneStrategy) getStrategy()).gameOver();
				return;
			}
			entity.detach();
			entities.remove(entity);
			
			// SKILL POINTS
			AuraSkills sk = getAuraPreferences().getCurrentSaveGame().getSkills();
			sk.setSkillPoints(sk.getSkillPoints()+1);
			// MISSIONS
			for (AMissionType t: AMissionType.values()) {
				switch (t) {
					case DELIVERY:
						break;
					case BOUNTY:
						AMissionBounty.checker(getEngine(), 
							((AAbstractBoardSceneStrategy) getStrategy()).getContextMemory(),
							getAuraPreferences().getCurrentSaveGame(),
							entity);
						break;
				}
			}			
			// LOOTS
			AuraInventory inv = getAuraPreferences().getCurrentSaveGame().getInventory();
			long left = inv.getSlotsLeft();
			if (Math.random() < (.1f + sk.getIntimidationFactor())  && inv.getSlotsLeft() > 0) {
				long cpt = new BigDecimal(Math.random()*4)
					.setScale(0, RoundingMode.DOWN)
					.longValue();
				if (cpt > left)
					cpt = left;
				if (cpt > 0) {
					AItemGoods item = AItemGoods.getRandom();
					inv.getItems().put(item.getId(), cpt);
					getStrategy().registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_LOOT.getMessage(getEngine(), 
							""+cpt, item.getLibelle()), 
						getStrategy().getHUD()));
				}
			}
		}
		// Decals
		VTileTexture t = owner.getWeapon().getProjectileDecals();
		if (t != null) {
			Vector2 v = entity.getPositionCenter();
			final AnimatedSprite as = new AnimatedSprite(0, 0, 
				getEngine().getVRessourceManager().getTileTexture(t));
			as.setPosition(v.x-as.getWidth()/2, v.y-as.getHeight()/2);
			as.setRotationCenter(as.getWidth()/2, as.getHeight()/2);
			as.setRotation((float) Math.random()*360);
			as.setAlpha(.5f);
			getScene().attachChild(as);
			getScene().registerUpdateHandler(new TimerHandler(.1f, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					getScene().detachChild(as);
				}
			}));
			Vector2Pool.recycle(v);
		}
	}
	
	public synchronized AuraEntityProjectile obtainProjectile(
			float pValueX, float pValueY, 
			Vector2 vector, AuraPersonnageInfo owner) {
		AuraEntityProjectile[] projs = getProjectilesPool().toArray(new AuraEntityProjectile[0]);
		for (AuraEntityProjectile p: projs) {
			if (!p.isAlive()) {
				p.teleport(pValueX, pValueY);
				p.setVector(vector);
				p.setOwner(owner);
				p.move(vector.x, vector.y);
				p.recycle();
				return p;
			}
		}
		AuraEntityProjectile proj = new AuraEntityProjectile(
			getEngine(), getScene(), getPhysicsWorld(), 
			pValueX, pValueY, vector, owner);
		getProjectilesPool().add(proj);
		proj.attach();
		getEngine().log("Init new projectile: "+proj); //$NON-NLS-1$
		return proj;
	}

//	public void npcHitPlayer(AuraPersonnageInfo npc, AuraEntity player) {
//		if (npc.tickDamage()) {
//			player.getPersonnageInfo().damage(1);
//			getEngine().vibrateQuick();
//		}
//		if (!player.isAlive() && getStrategy() instanceof APlaneteSceneStrategy)
//			((APlaneteSceneStrategy) getStrategy()).gameOver();
//	}
}