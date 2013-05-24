package com.auraRpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.auraRpg.data.AMap;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListView;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.AKeyBoardSceneStrategy;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractButton;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;
import com.voidEngine.tools.VPageData;

public class AMenuSystemPlaySceneStrategy extends AMenuSystemSceneStrategy {
	public AMenuSystemPlaySceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_SAVE_GAME);
	}

	private AuraListView lvSaveGame;
	private VAbstractButton btNew;
	private VAbstractButton btLoad;
	private VAbstractButton btDelete;

	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.lvSaveGame = new AuraListViewLarge(getEngine(), 165, getTxTitre().getHeight()+10) {
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_PROF_TRADER.getTexture();
			}
			@Override
			public VTexture getTextureBy(Object o) {
				AuraSaveGame sg = (AuraSaveGame) o;
				return sg.getSkills().getStarterProfession().getLogo();
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public String objectToString(Object o) {
				AuraSaveGame sg = (AuraSaveGame) o;
				return AuraI18nChooser.GUI_SAVEGAME_LV_ROW.getMessage(getEngine(), ""+sg.getId(), sg.getPersonnageInfo().getName());  //$NON-NLS-1$
			}
			@Override
			public void onTouchElement(Object o) {
				getEngine().log("TYPE: "+type); //$NON-NLS-1$
				if (type == null) {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_SAVEGAME_INFO.getMessage(getEngine()), getScene()));
					return;
				}
				switch (type) {
					case LOAD:
						AuraSaveGame sg = (AuraSaveGame) o;
						getAuraPreferences().setCurrentSaveGame(sg);
						AMap.launchMap(getEngine(), getContextMemory(), sg, sg.getMap());
						break;
					case DELETE:
						((AuraSaveGame) o).delete();
						break;
				}
				type = null;
				resetLV();
			}
		};
		lvSaveGame.attach(getScene());
		btNew = new AuraButton(getEngine(), getScene(), 160, 420) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				final VAbstractSceneStrategy parent = getEngine().getVSceneManager().getActual();
				
				AKeyBoardSceneStrategy k = new AKeyBoardSceneStrategy(getEngine(), getContextMemory()) {
					@Override
					public void doOnClose(String text) {
						type = null;
						resetLV();
						if (text != null && text.length() > 0) {
							AMenuSystemStarterSceneStrategy st = new AMenuSystemStarterSceneStrategy(getEngine(), 
								getContextMemory(), text, parent);
							getEngine().getVSceneManager().switchTo(st, true, false, false);
						} else 
							getEngine().getVSceneManager().switchTo(parent, true, false, true);
					}
				};
				getEngine().getVSceneManager().switchTo(k, false, true, false);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SAVEGAME_BT_NEW.getMessage(getEngine());
			}
		};
		btNew.attach();
		
		btLoad = new AuraButton(getEngine(), getScene(), 320, 420) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				type = PlayActionType.LOAD;
				resetLV();
				registerDialog(AuraDialog.creerDialog(getEngine(), 
					AuraI18nChooser.DIALOG_SAVEGAME_LOAD.getMessage(getEngine()), 
					getScene()));
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SAVEGAME_BT_LOAD.getMessage(getEngine());
			}
		};
		btLoad.attach();
		
		btDelete = new AuraButton(getEngine(), getScene(), 480, 420) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				type = PlayActionType.DELETE;
				resetLV();
				registerDialog(AuraDialog.creerDialog(getEngine(), 
					AuraI18nChooser.DIALOG_SAVEGAME_DELETE.getMessage(getEngine()), 
					getScene()));
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_SAVEGAME_BT_DELETE.getMessage(getEngine());
			}
		};
		btDelete.attach();
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		resetLV();
	}
	
	@Override
	public void onRemove() {
		super.onRemove();
		lvSaveGame.detach();
		btNew.detach();
		btLoad.detach();
		btDelete.detach();
	}
	
	private void resetLV() {
		datas = null; 
		lvSaveGame.print(1);
	}
	
	private class SaveGameData extends VPageData<AuraSaveGame> {
		public SaveGameData(AuraSaveGame[] data) {
			super(data, 7);
		}
	}
	
	private SaveGameData datas;
	public SaveGameData getDatas() {
		if (datas == null) {
			getAuraPreferences().load();
			Map<Integer, AuraSaveGame> sgs = getAuraPreferences().getSaveGames();
			List<AuraSaveGame> sgList = new ArrayList<AuraSaveGame>();
			for (Integer i: sgs.keySet()) {
				sgList.add(sgs.get(i));
			}
			datas = new SaveGameData(sgList.toArray(new AuraSaveGame[0]));
		}
		return datas;
	}
	
	private PlayActionType type;
	private enum PlayActionType {
		LOAD,
		DELETE;
	}
}