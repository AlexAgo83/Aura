package com.auraRpg.scene.menu.inGame;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AFaction;
import com.auraRpg.gui.AuraButton;
import com.auraRpg.gui.AuraDialog;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraInventory;
import com.auraRpg.saveGame.AuraPolitics;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGPoliticsStandingSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGPoliticsStandingSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_POLITICS_REPUTATION);
	}
	
	private ChangeableText txTitreFaction;
	private ChangeableText txTitreCorporation;
	private AuraListViewMedium lvDescriptionFaction;
	private AuraListViewLarge lvDescriptionCorporation;
	
	private AuraButton btConflict;
	private AuraButton btPromote;
	private ACorporation corporationFocus;
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		
		this.btConflict = new AuraButton(getEngine(), getScene(), 160+480, 20) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				getEngine().getVSceneManager().switchTo(getContextMemory().getPoliticsConflictScene(), false, true, true);
				return true;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_POLITICS_BT_CONFLICT.getMessage(getEngine());
			}
		};
		btConflict.attach();
		
		float nextY = getTxTitre().getHeight()+10;
		this.txTitreFaction = new ChangeableText(165, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			AuraI18nChooser.GUI_POLITICS_ROWDATA_TITRE_DESC_FACTION.getMessage(getEngine()), 
			HorizontalAlign.LEFT, 60);
		txTitreFaction.setColor(1, 0, 0);
		getScene().attachChild(txTitreFaction);
		
		nextY = nextY+txTitreFaction.getHeight()+5;
		this.lvDescriptionFaction = new AuraListViewMedium(getEngine(), 165, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getDataFaction();
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_POLITICS.getTexture();
			}
			@Override public void onTouchElement(Object o) {}
			@Override
			public String objectToString(Object o) {
				AuraPolitics p = getAuraPreferences().getCurrentSaveGame().getPolitics();
				AFaction f = ((AFaction) o);
				return AuraI18nChooser.GUI_POLITICS_ROWDATA_DESC_FACTION.getMessage(getEngine(), 
					f.getTag(), 
					f.getName(), 
					""+p.getReputationFaction(f)); //$NON-NLS-1$
			}
			public VTexture getTextureBy(Object o) {
				return ((AFaction) o).getFlag(); 
			}
		};
		lvDescriptionFaction.attach(getScene());
		
		nextY = 190;
		this.txTitreCorporation = new ChangeableText(165, nextY, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_MEDIUM.getFont()), 
			AuraI18nChooser.GUI_POLITICS_ROWDATA_TITRE_DESC_CORP.getMessage(getEngine()), 
			HorizontalAlign.LEFT, 60);
		txTitreCorporation.setColor(1, 0, 0);
		getScene().attachChild(txTitreCorporation);
		
		nextY = nextY+txTitreCorporation.getHeight()+5;
		this.lvDescriptionCorporation = new AuraListViewLarge(getEngine(), 165, nextY) {
			@Override
			public VPageData<?> getPageData() {
				return getDataCorporation();
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_POLITICS.getTexture();
			}
			@Override public void onTouchElement(Object o) {
				corporationFocus = null;
				btPromote.detach();
				AuraPolitics p = getAuraPreferences().getCurrentSaveGame().getPolitics();
				ACorporation c = (ACorporation) o;
				if (p.isPromoteAvailable(c)) {
					registerDialog(AuraDialog.creerDialog(getEngine(), 
						AuraI18nChooser.DIALOG_POLITICS_RANK_AVAILABLE.getMessage(getEngine(), 
							""+p.getRankValueAtStanding(c),
							""+p.getRankCostAtStanding(c)), 
						getScene()));
				}
				corporationFocus = c;
				btPromote.attach();
			}
			@Override
			public String objectToString(Object o) {
				AuraPolitics p = getAuraPreferences().getCurrentSaveGame().getPolitics();
				ACorporation c = ((ACorporation) o);
				return AuraI18nChooser.GUI_POLITICS_ROWDATA_DESC_CORP.getMessage(getEngine(), 
					c.getFaction().getTag(),
					c.getName(),
					""+p.getReputationCorporation(c),
					(p.isPromoteAvailable(c)?"*NEW*":""+p.getRankCorporation(c))) //$NON-NLS-1$
					;
			}
			public VTexture getTextureBy(Object o) {
				return ((ACorporation) o).getFlag(); 
			}
		};
		lvDescriptionCorporation.attach(getScene());
		
		this.btPromote = new AuraButton(getEngine(), getScene(), getMaxX(), 100) {
			@Override
			public boolean onTouchRelease(float x, float y) {
				if (corporationFocus != null) {
					AuraSaveGame sg = getAuraPreferences().getCurrentSaveGame();
					AuraPolitics p = sg.getPolitics();
					AuraInventory inv = sg.getInventory();
					if (p.isPromoteAvailable(corporationFocus)) {
						long cost = p.getRankCostAtStanding(corporationFocus);
						int newRank = p.getRankValueAtStanding(corporationFocus);
						if (inv.getCredits() >= cost) {
							inv.setCredits(inv.getCredits()-cost);
							p.getRankCorporations().put(corporationFocus, newRank);
							corporationFocus = null;
							lvDescriptionCorporation.print(1);
							btPromote.detach();
						} else {
							// pas assez de credits
							registerDialog(AuraDialog.creerDialog(getEngine(), 
								AuraI18nChooser.DIALOG_NOT_ENOUGH_CREDITS.getMessage(getEngine()), 
								getScene()));
						}
					} else {
						// up your standing
						registerDialog(AuraDialog.creerDialog(getEngine(), 
							AuraI18nChooser.DIALOG_NOT_ENOUGH_STANDING.getMessage(getEngine()),
							getScene()));
					}
					return true;
				} 
				return false;
			}
			@Override
			public String getTextBoxMessage() {
				return AuraI18nChooser.GUI_POLITICS_BT_PROMOTE.getMessage(getEngine());
			}
		};
	}

	private AFactionData dataFaction;
	public AFactionData getDataFaction() {
		if (dataFaction == null) {
			dataFaction = new AFactionData(AFaction.values());	
		}
		return dataFaction;
	}
	
	private ACorporationData dataCorporation;
	public ACorporationData getDataCorporation() {
		if (dataCorporation == null) {
			dataCorporation = new ACorporationData(ACorporation.values());	
		}
		return dataCorporation;
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		lvDescriptionFaction.print(1);
		lvDescriptionCorporation.print(1);
		btPromote.detach();
		corporationFocus = null;
	}
	
	@Override
	public void onRemove() {
		getScene().detachChild(txTitreFaction);
		getScene().detachChild(txTitreCorporation);
		lvDescriptionFaction.detach();
		lvDescriptionCorporation.detach();
		btConflict.detach();
		btPromote.detach();
		super.onRemove();
	}
	
	public class AFactionData extends VPageData<AFaction> {
		public AFactionData(AFaction[] datas) {
			super(datas, 3);
		}
	}
	public class ACorporationData extends VPageData<ACorporation> {
		public ACorporationData(ACorporation[] datas) {
			super(datas, 5);
		}
	}
}