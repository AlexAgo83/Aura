package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.AMap;
import com.auraRpg.data.AMission;
import com.auraRpg.data.AMissionBounty;
import com.auraRpg.data.AMissionDelivery;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGViewMissionSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGViewMissionSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_MISSION);
	}
	
	private ChangeableText txTitreMission;
	private AuraListViewMedium lvDescription;
	
	private AuraButton btAccept;
	private AuraButton btReject;
	private AuraButton btDelete;
	private AuraButton btExecute;
	private AuraButton btLocate;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		float nextY = getTxTitre().getHeight()+10;
		this.txTitreMission = new ChangeableText(165, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			"", HorizontalAlign.LEFT, 60); //$NON-NLS-1$
		txTitreMission.setColor(1, 0, 0);
		
		nextY = nextY+txTitreMission.getHeight()+5;
		this.lvDescription = new AuraListViewMedium(getEngine(), 165, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getData();
			}
			@Override
			public VTexture getTextureIcones() {
				return null;
			}
			@Override public void onTouchElement(Object o) {}
			@Override
			public String objectToString(Object o) {
				return o.toString();
			}
		};
		
		this.btAccept = new AuraButton(getEngine(), getScene(), 640, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().getMissions().ajouterMission(getEngine(), getMission());
				getEngine().getVSceneManager().back();
				return true; 
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VIEW_MISSION_BT_ACCEPT.getMessage(getEngine());
			}
		};
		this.btDelete = new AuraButton(getEngine(), getScene(), 640, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getAuraPreferences().getCurrentSaveGame().getMissions().supprimerMission(mission);
				getEngine().getVSceneManager().back();
				return true; 
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VIEW_MISSION_BT_DELETE.getMessage(getEngine());
			}
		};
		this.btReject = new AuraButton(getEngine(), getScene(), 640, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().back();
				return true; 
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VIEW_MISSION_BT_REJECT.getMessage(getEngine());
			}
		};
		this.btExecute = new AuraButton(getEngine(), getScene(), 640, 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				if (getMission().getEscaCible() != null) {
					AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
					if (sg.getMap().equals(getMission().getPlaneteCible()) && getMission().getEscaCible() != null) {
						AMap.launchMap(getEngine(), getContextMemory(), sg, getMission().getEscaCible());
					} else {
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_MISSION_EXECUTE_KO.getMessage(getEngine()), 
							getScene()));
					}
				}				
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VIEW_MISSION_BT_EXECUTE.getMessage(getEngine());
			}
		};
		this.btLocate = new AuraButton(getEngine(), getScene(), 640, 180) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				switch (getMission().getType()) {
					case DELIVERY:
						AMissionDelivery d = (AMissionDelivery) getMission();
//						AMap m = d.getMapCible();
						AMap m = AMission.locateMapByAgent(getEngine(), d.getTarget());
						if (m != null) {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_MISSION_LOCATE_PLANETE.getMessage(getEngine(), m.getName()), 
								getHUD()));
						} else {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_MISSION_LOCATE_KO.getMessage(getEngine()), 
								getHUD()));
						}
						break;
					case BOUNTY:
						AMissionBounty b = (AMissionBounty) getMission();
//						ANpcSpawner spawnerFocus = ANpcSpawner.getByNpc(b.getTarget());
//						AMap am = AMission.locateMapBySpawner(getEngine(), spawnerFocus);
						AMap planete = b.getPlaneteCible();
						AMap esca = b.getEscaCible();
						if (planete != null) {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_MISSION_LOCATE_ESCA.getMessage(getEngine(), planete.getName(), esca.getName()), 
								getHUD()));
						} else {
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_MISSION_LOCATE_KO.getMessage(getEngine()), 
								getHUD()));
						}
						break;
				}
				return true; 
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_VIEW_MISSION_BT_LOCATE.getMessage(getEngine());
			}
		};
		
		getScene().attachChild(txTitreMission);
		lvDescription.attach(getScene());
	}

	private boolean addMode; 
	private AMission mission;
	public AMission getMission() {
		return mission;
	}
	public void setMission(AMission m, boolean addMode) {
		this.mission = m;
		this.data = null;
		this.addMode = addMode;
	}
	private VPageData<String> data;
	public VPageData<String> getData() {
		if (data == null) {
			data = new AMissionData(getMission().getStartDescription(getEngine()));	
		}
		return data;
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		txTitreMission.setText(getMission().getName(getEngine()));
		lvDescription.print(1);
		
		btAccept.detach();
		btReject.detach();
		btDelete.detach();
		btExecute.detach();
		btLocate.detach();
		
		if (addMode) {
			btAccept.attach();
			btReject.attach();
		} else {
			btDelete.attach();
			if (getMission().getEscaCible() != null)
				btExecute.attach();
		}
		btLocate.attach();
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txTitreMission);
		lvDescription.detach();
		btAccept.detach();
		btReject.detach();
		btDelete.detach();
		btExecute.detach();
		btLocate.detach();
		super.onRemove();
	}
	
	public class AMissionData extends VPageData<String> {
		public AMissionData(String[] datas) {
			super(datas, 12);
		}
	}
}