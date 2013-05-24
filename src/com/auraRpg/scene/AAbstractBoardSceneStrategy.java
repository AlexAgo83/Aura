package com.auraRpg.scene;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.AuraEngine;
import com.auraRpg.AuraMapPrinter;
import com.auraRpg.data.AConflict;
import com.auraRpg.data.AMap;
import com.auraRpg.data.AMapType;
import com.auraRpg.data.AMission;
import com.auraRpg.data.ANpc;
import com.auraRpg.data.ANpcSpawner;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraPreferences;
import com.auraRpg.saveGame.AuraSaveGame;
import com.badlogic.gdx.math.Vector2;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.map.VTile;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;

public abstract class AAbstractBoardSceneStrategy extends VAbstractSceneStrategy {
	private final AContextSceneMemory contextMemory;
	private final AMap map;
	
	public AAbstractBoardSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AMap map) {
		super(engine, true);
		this.contextMemory = contextMemory;
		this.map = map;
	}
	
	public AContextSceneMemory getContextMemory() {
		return contextMemory;
	}
	protected AuraEngine getAuraEngine() {
		return (AuraEngine) getEngine();
	}
	protected AuraPreferences getAuraPreferences() {
		return (AuraPreferences) getAuraEngine().getVPreferences();
	}
	
	private Sprite cadre;

	private AuraButton btFAKE;
	private AuraButton btQuit;
	private AuraButton btStatus;
	private AuraButton btInventory;
	private AuraButton btMission;
	private AuraButton btPolitics;
	private AuraButton btLeaveEsca;
	private AuraButton btFlee;
	private AuraButton btExploration;
	
	private AuraMapPrinter mapPrinter;
	public AuraMapPrinter getMapPrinter() {
		return mapPrinter;
	}
	
	private TimerHandler thRunner;
	private ChangeableText txHorloge;
	private ChangeableText txBousole;
	
	public abstract AuraPersonnageInfo getSuperNpcInfo(ANpcSpawner spawner, ANpc npcSurcharge);
	public abstract boolean onSuperTouchTile(VTile t);
	
	@Override
	public void onCreate() {
		// *INIT BOARD*
		if (map != null) {
			this.mapPrinter = new AuraMapPrinter(getEngine(), this, map) {
				@Override
				public boolean onTouchTile(VTile t) {
					return onSuperTouchTile(t);
				}
				@Override
				public AuraPersonnageInfo getNpcInfo(ANpcSpawner spawner, ANpc npcSurcharge) {
					return getSuperNpcInfo(spawner, npcSurcharge);
				}
			};
			this.mapPrinter.load();
		}
		
		// * INIT IHM*
		this.cadre = new Sprite(0, 0, getEngine().getVRessourceManager()
				.getTexture(getTextureCadre()));
		this.btFAKE = new AuraButton(getEngine(), getHUD(), 0, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				return false;
			}
			@Override
			public String getTextBoxMessage() {
				return ""; //$NON-NLS-1$
			}
		};
		this.btStatus = new AuraButton(getEngine(), getHUD(), 0, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().switchTo(getContextMemory().getStatusScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_STATUS.getMessage(getEngine());
			}
		};
		this.btInventory = new AuraButton(getEngine(), getHUD(), 0, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().switchTo(getContextMemory().getInventoryScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_INVENTORY.getMessage(getEngine());
			}
		};
		this.btQuit = new AuraButton(getEngine(), getHUD(), 0, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().switchToFirstScreen();
				getAuraPreferences().setCurrentSaveGame(null);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_SAVE_QUIT.getMessage(getEngine());
			}
		};
		btQuit.majPosition();
		
		this.btMission = new AuraButton(getEngine(), getHUD(), 480+160, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().switchTo(getContextMemory().getMissionScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_MISSION.getMessage(getEngine());
			}
		};
		this.btPolitics = new AuraButton(getEngine(), getHUD(), 480+160, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().save();
				getEngine().getVSceneManager().switchTo(getContextMemory().getPoliticsReputationsScene(), false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_POLITICS.getMessage(getEngine());
			}
		};
		this.btLeaveEsca = new AuraButton(getEngine(), getHUD(), 480+160, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				switch (getMapPrinter().getMap().getType()) {
					case SPACE:
						getEngine().getVSceneManager().back();
						break;
					case ESCARMOUCHE:
					case SHIP:
					case PLANETE:
						break;
				}
				
				AMap m = getAuraPreferences().getCurrentSaveGame().getLastMap();
				if (m != null) {
					AMap.launchMap(getEngine(), getContextMemory(), getAuraPreferences().getCurrentSaveGame(), m);
				}
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_LEAVE_ESCA.getMessage(getEngine());
			}
		};
		this.btFlee = new AuraButton(getEngine(), getHUD(), 480+160, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				
				float chanceCatchOnFlee = AuraSaveGame.BASE_CATH_ON_FLEE_RATE 
					+ getAuraPreferences().getCurrentSaveGame().getSkills().getIntimidationFactor();
				if (Math.random() > chanceCatchOnFlee) {
					btFlee.detach();
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_FLEE_FAILED.getMessage(getEngine()), 
						getHUD()));
					return true;
				}
				
				AMap m = sg.getMap();
				if (m != null && m.getPlaneteRef() != null) {
					AMap.launchMap(
						getEngine(), getContextMemory(), 
						getAuraPreferences().getCurrentSaveGame(), m.getPlaneteRef());
				}
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_FLEE.getMessage(getEngine());
			}
		};
		this.btExploration = new AuraButton(getEngine(), getHUD(), 480+160, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
				long currTick = sg.getLastExplorationTickByMap(getMapPrinter().getMap());
				long currMin = sg.getLastExplorationTickMinLeftByMap(getMapPrinter().getMap());
				long currSec = sg.getLastExplorationTickSecLeftByMap(getMapPrinter().getMap());
				if (currTick < sg.getTime()) {
					long newTime = sg.getTime()+AuraSaveGame.BASE_NEXT_EXPLORATION;
					sg.getLastExplorationTick().put(getMapPrinter().getMap().getId(), newTime);
					float chanceDInterception = AuraSaveGame.BASE_EXPLORATION_RATE 
						+ getAuraPreferences().getCurrentSaveGame().getSkills().getExplorationFactor();
					if (Math.random() > chanceDInterception) {
						loadNewBoard(AMap.getRandom(AMapType.ESCARMOUCHE));
					} else {
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_EXPLORATION_ECHEC.getMessage(getEngine()), 
							getHUD()));
					}
				} else if (currMin>0) {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_EXPLORATION_TOO_SOON_MIN.getMessage(getEngine(), ""+currMin), 
						getHUD()));
				} else {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_EXPLORATION_TOO_SOON_SEC.getMessage(getEngine(), ""+currSec), 
						getHUD()));
				}
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_INGAME_BT_EXPLORATION.getMessage(getEngine());
			}
		};
		
		this.txHorloge = new ChangeableText(165, 0, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_SMALL.getFont()), "", //$NON-NLS-1$
			HorizontalAlign.LEFT, 100);
		this.txBousole = new ChangeableText(165, txHorloge.getHeight(), 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_SMALL.getFont()), "", //$NON-NLS-1$
			HorizontalAlign.LEFT, 100);

		// *ATTACH ALL*
		if (mapPrinter != null) {
			mapPrinter.initPlayer();
			mapPrinter.print();
		}
		
		getHUD().attachChild(cadre);
		
		majButton();	
		
		getHUD().attachChild(txHorloge);
		getHUD().attachChild(txBousole);
	}
	
	public abstract VTexture getTextureCadre();
	
	private static float TIMER_HORLOGE = .5f;
	@Override
	public void onRefresh() {
		if (mapPrinter != null)
			getEngine().getVCamera().setChaseEntity(mapPrinter.getPlayer().getHitBox());
		
		if (thRunner != null)
			getScene().unregisterUpdateHandler(thRunner);
		else 
			thRunner = new TimerHandler(TIMER_HORLOGE, true, new ITimerCallback() {
				private Vector2 lastPosition = null;
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					if (mapPrinter == null)
						return;
					// MAJ AFFICHAGES
					getAuraPreferences().getCurrentSaveGame().setTime(
						getAuraPreferences().getCurrentSaveGame().getTime() + 5000);
					txHorloge.setText(getAuraPreferences().getCurrentSaveGame().getTimeFormatString(true));
					Vector2 pos = mapPrinter.getPlayer().getPositionCenter();
					int x = new BigDecimal(pos.x/64).setScale(0, RoundingMode.DOWN).intValue();
					int y = new BigDecimal(pos.y/64).setScale(0, RoundingMode.DOWN).intValue();
					txBousole.setText(mapPrinter.getMap().getName() + " [" + x + "," + y + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					
					// PEDOMETER
					if (lastPosition != null) {
						int dist = new BigDecimal(lastPosition.dst(pos) / 36).setScale(0, RoundingMode.DOWN).intValue();
						getAuraPreferences().getCurrentSaveGame().getAchievement().increaseMeterRun(dist);
					} else {
						lastPosition = pos.cpy();
					}
					Vector2Pool.recycle(pos);
					
					getAuraPreferences().getCurrentSaveGame().getAchievement().increasePlayed(
							new BigDecimal(TIMER_HORLOGE*1000).setScale(0, RoundingMode.DOWN).longValue());
					
					// POLITICS
					AConflict c = getAuraPreferences().getCurrentSaveGame().getPolitics().run(
							getEngine(), getAuraPreferences().getCurrentSaveGame().getTime());
					if (c != null)
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_CONFLIT_NEW.getMessage(getEngine(), c.getCorpoA().getName(), c.getCorpoB().getName()),
							getHUD()));
					
					// MISSIONS
					AMission[] ms = getAuraPreferences().getCurrentSaveGame().getMissions().run(getAuraPreferences().getCurrentSaveGame().getTime());
					if (ms != null) {
						for (AMission m: ms) {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_MISSION_FAILED.getMessage(getEngine(), m.getName(getEngine())),
								getHUD()));
						}
					}
					
					// VENDORS
					getAuraPreferences().getCurrentSaveGame().getVendorInfo().run(
							getEngine(), 
							getAuraPreferences().getCurrentSaveGame().getTime());
				}
			});

		majButton();
		
		getScene().registerUpdateHandler(thRunner);
	}
	
	@Override
	public void onRemove() {
		getHUD().detachChild(cadre);
		getHUD().detachChild(txHorloge);
		getHUD().detachChild(txBousole);
		doImpliciteRemove();
		if (mapPrinter != null)
			mapPrinter.clear();
	}
	protected void doImpliciteRemove() {
		getScene().unregisterUpdateHandler(thRunner);
		
		btFAKE.detach();
		
		btStatus.detach();
		btInventory.detach();
		btQuit.detach();
		
		btMission.detach();
		btPolitics.detach();
		
		btLeaveEsca.detach();
		btFlee.detach();	
		btExploration.detach();
	}
	
	public void gameOver() {
		mapPrinter.getPlayer().detach();
		getAuraPreferences().getCurrentSaveGame().getAchievement().increasePlayerDeath();
		getAuraPreferences().getCurrentSaveGame().save();
		getAuraPreferences().getCurrentSaveGame().delete();
		getEngine().vibrateLong();
		final Sprite s = new Sprite(160, 0,
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_GAME_OVER.getTexture())) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				getEngine().getVSceneManager().switchToFirstScreen();
				return true;
			}
		};
		getHUD().attachChild(s);
		getHUD().registerTouchArea(s);
		doImpliciteRemove();
	}
	
	private void majButton() {
		btFAKE.detach();
		btStatus.detach();
		btInventory.detach();
		btQuit.detach();
		btMission.detach();
		btPolitics.detach();
		btLeaveEsca.detach();
		btFlee.detach();
		btExploration.detach();
		
		btFAKE.attach();
		btStatus.attach();
		btInventory.attach();
		btQuit.attach();
		btMission.attach();
		btPolitics.attach();
		
		switch (getMapPrinter().getMap().getType()) {
			case SPACE:
			case ESCARMOUCHE:
				btLeaveEsca.attach();
				break;
			case SHIP:
				btFlee.attach();
				break;
			case PLANETE:
				btExploration.attach();
				break;
		}
	}
	
	protected void loadNewBoard(AMap map) {
		AMap.launchMap(getEngine(), getContextMemory(), getAuraPreferences().getCurrentSaveGame(), map);
	}
}