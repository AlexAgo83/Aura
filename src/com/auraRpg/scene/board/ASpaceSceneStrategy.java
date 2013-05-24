package com.auraRpg.scene.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.text.ChangeableText;

import com.auraRpg.data.AMap;
import com.auraRpg.data.ANpc;
import com.auraRpg.data.ANpcSpawner;
import com.auraRpg.ressources.AuraFontChooser;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraPersonnageInfo;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AAbstractBoardSceneStrategy;
import com.auraRpg.scene.AAbstractTransitionSceneStrategy;
import com.auraRpg.scene.AContextSceneMemory;
import com.badlogic.gdx.physics.box2d.Body;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.map.VMap;
import com.voidEngine.engine.map.VPoint;
import com.voidEngine.engine.map.VTile;
import com.voidEngine.engine.ressources.VTexture;

public class ASpaceSceneStrategy extends AAbstractBoardSceneStrategy {
	public ASpaceSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory, AMap[] available) {
		super(engine, contextMemory, AMap.SPACE_MAP_TRAVEL);
		this.available = available;
		this.shapes = new ArrayList<Shape>();
	}
	
	private final AMap[] available;
	private List<Shape> shapes;
	
	@Override
	public AuraPersonnageInfo getSuperNpcInfo(ANpcSpawner spawner, ANpc npcSurcharge) {
		// FIXME à confirmer !
//		final AuraPersonnageInfo pi = new AuraPersonnageInfo(npcSurcharge.getName(), 
//			npcSurcharge.getMaxHealth(), npcSurcharge.getMaxHealth(),
//			npcSurcharge.getWeapon());
//		return pi;
		return null;
	}
	
	@Override
	public boolean onSuperTouchTile(VTile t) {
		getEngine().log("Touch tile: "+t.toString()); //$NON-NLS-1$
		AMap newMap = AMap.getById(t.getDataDecors());
		boolean trouve = false;
		for (AMap m: available) {
			if (m.getId() == newMap.getId()) {
				trouve = true;
				break;
			}
		}
		if (newMap != null && (trouve || newMap.getId() == getAuraPreferences().getCurrentSaveGame().getMap().getId())) {
			boolean intercepted = false;
			float chanceDInterception = AuraSaveGame.BASE_INTERCEPTION_RATE 
				+ getAuraPreferences().getCurrentSaveGame().getSkills().getStealthFactor();
			if (Math.random() > chanceDInterception && newMap.getId() != getAuraPreferences().getCurrentSaveGame().getMap().getId()) {
				newMap = newMap.getShip();
				intercepted = true;
			}
			getAuraPreferences().getCurrentSaveGame().setMap(newMap);
			getAuraPreferences().getCurrentSaveGame().setX(newMap.getXPosition());
			getAuraPreferences().getCurrentSaveGame().setY(newMap.getYPosition());
			getAuraPreferences().getCurrentSaveGame().save();
			if (intercepted) {
				final AMap mapTmp = newMap;
				AAbstractTransitionSceneStrategy tran = new AAbstractTransitionSceneStrategy(
						getEngine(), getContextMemory(), AuraI18nChooser.TITRE_WARNING) {
					@Override
					public boolean onContinue() {
						loadNewBoard(mapTmp);
						return true;
					}
					@Override
					public String[] getDescription() {
						return new String[] {
							AuraI18nChooser.GUI_TRAVEL_INFO_ATTACK_A.getMessage(getEngine(), mapTmp.getName()),
							AuraI18nChooser.GUI_TRAVEL_INFO_ATTACK_B.getMessage(getEngine())
						};
					}
					@Override public String getDialogText() { return null; }
				};
				if (getEngine().getVSceneManager().getLast() != null)
					getEngine().getVSceneManager().getLast().onUnload();
				getEngine().getVSceneManager().switchTo(tran, false, false, false);
			} else {
				loadNewBoard(newMap);
			}
		}
		return false;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		VMap vm = getMapPrinter().getMap().load(getEngine());
		Map<AMap, VPoint> positionsPlanetes = new HashMap<AMap, VPoint>();
		for (VPoint p: vm.getTiles().keySet()) {
			VTile t = vm.getTiles().get(p);
			positionsPlanetes.put(AMap.getById(t.getDataDecors()), p);
		}
		VPoint positionActu = positionsPlanetes.get(getAuraPreferences().getCurrentSaveGame().getMap());
		getMapPrinter().getPlayer().teleport(positionActu.getX()*64, positionActu.getY()*64);
		for (AMap m: available) {
			VPoint p = positionsPlanetes.get(m);
			if (p != null) {
				Line l = new Line(
					positionActu.getX()*64, positionActu.getY()*64,
					p.getX()*64, p.getY()*64);
				shapes.add(l); getScene().attachChild(l);
			}
		}
		for (AMap m: AMap.values()) {
			VPoint p = positionsPlanetes.get(m);
			if (p != null) {
				ChangeableText t = new ChangeableText(p.getX()*64, p.getY()*64, 
					getEngine().getVRessourceManager().getFont(AuraFontChooser.SYSTEM_FONT_SMALL.getFont()),
					m.getName());
				Rectangle r = new Rectangle(p.getX()*64, p.getY()*64, t.getWidth(), t.getHeight());
				r.setColor(.4f, .4f, .4f);
				r.setAlpha(.4f);
				shapes.add(r); getScene().attachChild(r);
				shapes.add(t); getScene().attachChild(t);
			}
		}
	}
	@Override
	public VTexture getTextureCadre() {
		return AuraTextureChooser.GUI_MENU.getTexture();
	}
	
	@Override
	public void onRefresh() {
		getEngine().getVCamera().setChaseEntity(getMapPrinter().getPlayer().getHitBox());
		super.onRefresh();
	}
	
	@Override
	public void onRemove() {
		for (Shape l: shapes) {
			getScene().detachChild(l);
		}
		shapes.clear();
		super.onRemove();
	}
	
	@Override
	public void onContact(final Body eA, final Body eB) {}
}