package com.auraRpg;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;

import com.auraRpg.R;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraSpeechChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.ressources.AuraTileTextureChooser;
import com.auraRpg.saveGame.AuraPreferences;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.system.AMenuSystemBlankSceneStrategy;
import com.badlogic.gdx.math.Vector2;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.VAbstractPreferences;
import com.voidEngine.engine.VAbstractProperties;
import com.voidEngine.engine.VAbstractSceneManager;
import com.voidEngine.engine.entity.VAbstractEntity;
import com.voidEngine.engine.loader.VAbstractRessourceManager;
import com.voidEngine.engine.ressources.VFont;
import com.voidEngine.engine.ressources.VI18n;
import com.voidEngine.engine.ressources.VMusic;
import com.voidEngine.engine.ressources.VSound;
import com.voidEngine.engine.ressources.VSpeech;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.ressources.VTileTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;

// FIXME Ajouter terminal de hacking OU outil pour remonter sa réputation en agressant une autre.
// FIXME Entité chest, hacking pour l'ouvrir, stat hacking pour l'ouvrir [Hacking], loot sur certains cadavre?
// FIXME Conflit , dans genererConflit voir pour random ecraser ou non déjà existant
// FIXME Ajouter durabilité sur weapon/armor
// FIXME Permettre de choisir la courbe de difficulté, changer la courbe moins LOG plus EXP
// FIXME Item good ajouter antibiotics

// FIX Prix du voyage à réduire ou augmenter reward mission
// FIX Loot 1 à 3 loot max
// FIX Exploration, si temps < 1 afficher secondes
// FIX Ajouter plus d'info dans l'écran de transition
// FIX Exploration de planète , avec surement un skill en fonction [Exploration]
// FIX Rendre le jeu plus dur avec le temps
// FIX Split vendeur en Goods & Military Office
// FIX Decouper inventaire en deux sections, Upgrade / Cargo
// FIX Ajouter impact des projectiles
// FIX base weapon = poing , faire un projectile qui dure 1 sec
// FIX Probleme avec les conflits à voir
// FIX Mission qui pointe vers le GIVER
// FIX Mission sans target dans la desc.
// FIX Jamais 3 fois de suite la même quête
// FIX Completer les quêtes avec : http://eveinfo.com/missions/
// FIX indexer le max count du spawner sur le max count de la mission (pas evident)
// FIX Starter profession
// FIX Si reput trop basse le joueur peut pas interagir avec l'agent (SAUF mission)
// FIX bt return dans la map spatiale
// FIX fleee
// FIX Decouper status en deux sections Stats / Skills
// FIX Permettre l'achat de skills, Ex: skills + 1 = X pts d'exp
// FIX Terminer la page de ventes/achats
// FIX Faire une fiche de vendeur
// FIX Generer fiche par vendeur, et la mettre à jour tous les jours (prix et qte)
// FIX Ajouter écran d'achat/vente d'item-goods.
// FIX Faire une progress bar
// FIX Terminer notion de temps MAX pour une mission
// FIX Regarder si les Conflits fonctionnent bien
// FIX Ajouter notion de taile max d'inventaire. Voir peut être creer un système d'entrepot.
// FIX Ajouter flag de corpo à coté des noms d'agent
// FIX Ajouter notion de map par mission, lancer une mission débloque une map sur une planete.
// FIX Faire ecran de transition (à la manière du gameOver)
// FIX Ajouter les crédits dans le status

public class AuraEngine extends VAbstractEngine {
	@Override
	public VAbstractProperties initProperties() {
		return new VAbstractProperties() {
			public boolean isDebug() { return false; }
			public String getDebugTag() { return "aura"; } //$NON-NLS-1$
			public int getMaxLog() { return 15; }
			
			public VFont getBaseSystemFont() { return AuraFontChooser.SYSTEM_FONT_SMALL.getFont(); }
			public String getVersion() { return getResources().getString(R.string.versionName); }

			public String getSharedPropertiesName() { return "auraAELibBox"; } //$NON-NLS-1$
			
			public ScreenOrientation getScreenOrientation() { return ScreenOrientation.LANDSCAPE; }
			public int getCameraScreenWidth() { return 800; }
			public int getCameraScreenHeight() { return 480; }
			public int getMaxFps() { return 60; }
			
			public Vector2 getPhysicsGravity() { return new Vector2(0, 0);}
			public boolean isPhysicsSleepAllowed() { return false; }
			
			public boolean isNeedsMusic() { return false; }
			public boolean isNeedsSound() { return false; }

			public VSound getSwitchSceneSound() { return null; }
			
			public boolean isAttachSplash() { return true; }
			public VTileTexture getSplashTexture() { return AuraTileTextureChooser.GUI_SPLASH_SCREEN.getTileTexture(); }
			public float getSplashLifeTime() { return 10f; }
		};
	}
	
	@Override
	public VAbstractPreferences initPreferences() {
		return new AuraPreferences(this);
	}
	
	@Override
	public VAbstractRessourceManager initRessourceManager() {
		return new VAbstractRessourceManager() {
			@Override
			public VTileTexture[] initTileTexturesToLoad() {
				List<VTileTexture> tileTextures = new ArrayList<VTileTexture>();
				for (AuraTileTextureChooser t: AuraTileTextureChooser.values()) {
					tileTextures.add(t.getTileTexture());
				}
				return tileTextures.toArray(new VTileTexture[0]);
			}
			@Override
			public VTexture[] initTexturesToLoad() {
				List<VTexture> textures = new ArrayList<VTexture>();
				for (AuraTextureChooser t: AuraTextureChooser.values()) {
					textures.add(t.getTexture());
				}
				return textures.toArray(new VTexture[0]);
			}
			@Override
			public VSound[] initSoundsToLoad() {
				return null;
			}
			@Override
			public VMusic[] initMusicsToLoad() {
				return null;
			}
			@Override
			public VFont[] initFontsToLoad() {
				List<VFont> fonts = new ArrayList<VFont>();
				for (AuraFontChooser t: AuraFontChooser.values()) {
					fonts.add(t.getFont());
				}
				return fonts.toArray(new VFont[0]);
			}
			@Override
			public VSpeech[] initSpeechsToLoad() {
				List<VSpeech> speechs = new ArrayList<VSpeech>();
				for (AuraSpeechChooser t: AuraSpeechChooser.values()) {
					speechs.add(t.getSpeech());
				}
				return speechs.toArray(new VSpeech[0]);
			}
			private VI18n i18nData;
			@Override
			public VI18n initI18nMessagesToLoad() {
				if (i18nData == null) {
					i18nData = new VI18n("speechs/i18nMessages.txt");  //$NON-NLS-1$
				}
				return i18nData;
			}
		};
	}
	
	@Override
	public VAbstractSceneManager initSceneManager() {
		return new VAbstractSceneManager(this) {
			@Override
			public boolean onEntityClick(VAbstractEntity entity) {
				return false;
			}
			@Override
			protected VAbstractSceneStrategy initFirstScreen() {
				return new AMenuSystemBlankSceneStrategy(getEngine(), new AContextSceneMemory(getEngine()));
			}
		};
	}
	
	@Override
	protected void onDestroy() {
		AuraSaveGame sg = ((AuraPreferences) getVPreferences()).getCurrentSaveGame();
		if (sg != null)
			sg.save();
		super.onDestroy();
	}
	@Override
	public void vibrateQuick() {
		if (((AuraPreferences) getVPreferences()).getOption().isVibratorFeedback())
			super.vibrateQuick();
	}
	@Override
	public void vibrateNormal() {
		if (((AuraPreferences) getVPreferences()).getOption().isVibratorFeedback())
			super.vibrateNormal();
	}
	@Override
	public void vibrateLong() {
		if (((AuraPreferences) getVPreferences()).getOption().isVibratorFeedback())
			super.vibrateLong();
	}
}