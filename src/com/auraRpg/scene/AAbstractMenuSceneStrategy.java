package com.auraRpg.scene;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.HorizontalAlign;

import com.auraRpg.AuraEngine;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraPreferences;
import com.badlogic.gdx.physics.box2d.Body;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.engine.scene.VAbstractSceneStrategy;

public abstract class AAbstractMenuSceneStrategy extends VAbstractSceneStrategy {
	private boolean loaded = false;
	public AAbstractMenuSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AuraI18nChooser i18nId) {
		super(engine, false);
		this.contextMemory = contextMemory;
		this.titre = i18nId != null ? i18nId.getMessage(getEngine()) : ""; //$NON-NLS-1$
	}
	
	private String titre;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
		if (txTitre != null)
			txTitre.setText(getTitre());
	}
	
	private final AContextSceneMemory contextMemory;
	protected AContextSceneMemory getContextMemory() {
		return contextMemory;
	}
	
	protected AuraEngine getAuraEngine() {
		return (AuraEngine) getEngine();
	}
	protected AuraPreferences getAuraPreferences() {
		return (AuraPreferences) getAuraEngine().getVPreferences();
	}
	
	private Sprite menuCadre;
	private Sprite menuFondA;
	private Sprite menuFondB;
	private ChangeableText txTitre;

	private TimerHandler thHUDMover;
	private Line lineA;
	private Line lineB;
	private Line lineC;
	private Line lineD;
	
	private Sprite gridAa;
	private Sprite gridAb;
	private Sprite gridBa;
	private Sprite gridBb;
	
	private Sprite logo;
	private Sprite logoRoundA;
	private Sprite logoRoundB;
	
	public ChangeableText getTxTitre() {
		return txTitre;
	}
	
	public int getMinX() {
		return 160;
	}
	public int getMaxX() {
		return 160 + 480;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		
		VTexture textureCadreMenu = AuraTextureChooser.GUI_MENU.getTexture();
		this.menuCadre = new Sprite(0, 0, 
			getEngine().getVRessourceManager().getTexture(textureCadreMenu));
		getScene().attachChild(menuCadre);
		
		final float xa = getMinX();
		final float xb = getMaxX();
		
		this.lineA = new Line(xa, 0, xb, 0); 
		lineA.setScaleY(.5f); 
		lineA.setColor(.2f, .4f, .4f);
		getScene().attachChild(lineA);
		
		this.lineB = new Line(xa, 0, xb, 0); 
		lineB.setScaleY(.5f); 
		lineB.setColor(.2f, .4f, .4f);
		getScene().attachChild(lineB); 
		
		this.lineC = new Line(xa, 0, xb, 0); 
		lineC.setScaleY(.5f); 
		lineC.setColor(.2f, .4f, .4f);
		getScene().attachChild(lineC);
		
		this.lineD = new Line(xa, 0, xb, 0); 
		lineD.setScaleY(.5f); 
		lineD.setColor(.2f, .4f, .4f);
		getScene().attachChild(lineD);
		
		this.gridAa = new Sprite(xa-1, -479, 
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_C.getTexture()));
		gridAa.setAlpha(.5f);
		gridAa.setColor(.2f, .4f, .4f);
		getScene().attachChild(gridAa);
		this.gridAb = new Sprite(xa-1, 0, 
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_C.getTexture()));
		gridAb.setAlpha(.5f);
		gridAb.setColor(.2f, .4f, .4f);
		getScene().attachChild(gridAb);
		
		this.gridBa = new Sprite(xb-7, -479, 
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_D.getTexture()));
		gridBa.setAlpha(.5f);
		gridBa.setColor(.2f, .4f, .4f);
		getScene().attachChild(gridBa);
		this.gridBb = new Sprite(xb-7, 0, 
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_D.getTexture()));
		gridBb.setAlpha(.5f);
		gridBb.setColor(.2f, .4f, .4f);
		getScene().attachChild(gridBb);

		VTexture textureCadreFondB = AuraTextureChooser.GUI_FOND_B.getTexture();
		this.menuFondB = new Sprite(160, 0, 
				getEngine().getVRessourceManager().getTexture(textureCadreFondB));
		menuFondB.setAlpha(.5f);
		menuFondB.setColor(.2f, .4f, .4f);
		
		this.logoRoundA = new Sprite(
			menuFondB.getX() + menuFondB.getWidth() / 2 - AuraTextureChooser.GUI_FOND_ROUND_A.getTexture().getWidth() / 2, 
			menuFondB.getY() + menuFondB.getHeight() / 2 - AuraTextureChooser.GUI_FOND_ROUND_A.getTexture().getHeight() / 2 - 15,
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_ROUND_A.getTexture()));
		logoRoundA.setAlpha(.5f);
		logoRoundA.setColor(.2f, .4f, .4f);
		logoRoundA.setScaleCenter(logoRoundA.getWidth()/2, logoRoundA.getHeight()/2);
		logoRoundA.setRotationCenter(logoRoundA.getWidth()/2, logoRoundA.getHeight()/2);
		logoRoundA.setScale(.80f);
		getScene().attachChild(logoRoundA);
		
		this.logoRoundB = new Sprite(
			logoRoundA.getX() + logoRoundA.getWidth() / 2 - AuraTextureChooser.GUI_FOND_ROUND_B.getTexture().getWidth() / 2, 
			logoRoundA.getY() + logoRoundA.getHeight() / 2 - AuraTextureChooser.GUI_FOND_ROUND_B.getTexture().getHeight() / 2,
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_ROUND_B.getTexture()));
		logoRoundB.setAlpha(.5f);
		logoRoundB.setColor(.2f, .4f, .4f);
		logoRoundB.setScale(.9f);
		logoRoundB.setScaleCenter(logoRoundB.getWidth()/2, logoRoundB.getHeight()/2);
		logoRoundB.setRotationCenter(logoRoundB.getWidth()/2, logoRoundB.getHeight()/2);
		getScene().attachChild(logoRoundB);
		
		getScene().attachChild(menuFondB);

		this.logo = new Sprite(
			menuFondB.getX() + menuFondB.getWidth() / 2 - AuraTextureChooser.GUI_FOND_E.getTexture().getWidth() / 2, 
			menuFondB.getY() + menuFondB.getHeight() / 2 - AuraTextureChooser.GUI_FOND_E.getTexture().getHeight() / 2 - 30,
			getEngine().getVRessourceManager().getTexture(AuraTextureChooser.GUI_FOND_E.getTexture()));
		logo.setAlpha(.5f);
		logo.setColor(.2f, .4f, .4f);
		logo.setScaleCenter(logo.getWidth()/2, logo.getHeight()/2);
		logo.setRotationCenter(logo.getWidth()/2, logo.getHeight()/2);
		getScene().attachChild(logo);
		
		thHUDMover = new TimerHandler(.05f, true, new ITimerCallback() {
			int yModifierA = (int) Math.floor(Math.random()*480); 
			int baseModifierA = (int) Math.floor((Math.random() * 3) + 1f);
			int yModifierB = (int) Math.floor(Math.random()*480);
			int baseModifierB = -(int) Math.floor((Math.random() * 3) + 1f);
			int yModifierC = (int) Math.floor(Math.random()*480);
			int baseModifierC = (int) Math.floor((Math.random() * 3) + 1f);
			int yModifierD = (int) Math.floor(Math.random()*480);
			int baseModifierD = -(int) Math.floor((Math.random() * 3) + 1f);
			int yModifierGAa = -480; int yModifierGAb = 0;
			int yModifierGBa = -480; int yModifierGBb = 0;
			float logoScale = 1; float logoScaleModifier = .002f;
			float logoScaleRound = 1; float logoRoundScaleModifier = .01f;
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				yModifierA += baseModifierA; 
				if (yModifierA > 480) yModifierA = 0; else if (yModifierA < 0) yModifierA = 480;
				yModifierB += baseModifierB; 
				if (yModifierB > 480) yModifierB = 0; else if (yModifierB < 0) yModifierB = 480;
				yModifierC += baseModifierC; 
				if (yModifierC > 480) yModifierC = 0; else if (yModifierC < 0) yModifierC = 480;
				yModifierD += baseModifierD; 
				if (yModifierD > 480) yModifierD = 0; else if (yModifierD < 0) yModifierD = 480;
				
				lineA.setPosition(xa, yModifierA, xb, yModifierA);
				lineB.setPosition(xa, yModifierB, xb, yModifierB);
				lineC.setPosition(xa, yModifierC, xb, yModifierC);
				lineD.setPosition(xa, yModifierD, xb, yModifierD);
				
				yModifierGAa += 1; 
				if (yModifierGAa > 480) yModifierGAa = -479;
				yModifierGAb += 1; 
				if (yModifierGAb > 480) yModifierGAb = -479;
				yModifierGBa += 1; 
				if (yModifierGBa > 480) yModifierGBa = -479;
				yModifierGBb += 1; 
				if (yModifierGBb > 480) yModifierGBb = -479;
				
				gridAa.setPosition(xa-1, yModifierGAa); 
				gridAb.setPosition(xa-1, yModifierGAb);
				gridBa.setPosition(xb-7, yModifierGBa); 
				gridBb.setPosition(xb-7, yModifierGBb);
				
				logoScale += logoScaleModifier;
				logoScaleRound += logoRoundScaleModifier;
				if (logoScale > 1.05 || logoScale < 0.95) logoScaleModifier = logoScaleModifier * -1;
				logo.setScale(logoScale);
				logo.setRotation((logoScale-1)*20);
				logoRoundA.setRotation((logoScaleRound-1)*20);
				logoRoundB.setRotation(-(logoScaleRound-1)*20);
			}
		});
		
		VTexture textureCadreFondA = AuraTextureChooser.GUI_FOND_A.getTexture();
		this.menuFondA = new Sprite(160, 0, 
			getEngine().getVRessourceManager().getTexture(textureCadreFondA));
		menuFondA.setAlpha(.5f);
		getScene().attachChild(menuFondA);
		
		this.txTitre = new ChangeableText(165, 0, 
			getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_BIG.getFont()), 
			titre, HorizontalAlign.LEFT, 100);
		txTitre.setColor(.4f, .85f, .75f);
		getScene().attachChild(txTitre);
		
		this.loaded = true;
	}
	@Override
	public void onRefresh() {
		getScene().unregisterUpdateHandler(thHUDMover);
		getScene().registerUpdateHandler(thHUDMover);
	}
	@Override
	public void onRemove() {
		getScene().unregisterUpdateHandler(thHUDMover);
		
		getScene().detachChild(lineA);
		getScene().detachChild(lineB);
		getScene().detachChild(lineC);
		getScene().detachChild(lineD);
		
		getScene().detachChild(gridAa);
		getScene().detachChild(gridAb);
		getScene().detachChild(gridBa);
		getScene().detachChild(gridBb);
		
		getScene().detachChild(logo);
		getScene().detachChild(logoRoundA);
		getScene().detachChild(logoRoundB);
		
		getScene().detachChild(menuCadre);
		getScene().detachChild(menuFondA);
		getScene().detachChild(menuFondB);
		getScene().detachChild(txTitre);
		
		this.loaded = false;
	}
	@Override public void onContact(Body eA, Body eB){}
	
	public boolean isSuperLoaded() {
		return loaded;
	}
}