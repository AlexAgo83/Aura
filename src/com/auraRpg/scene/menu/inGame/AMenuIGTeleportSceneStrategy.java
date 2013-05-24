package com.auraRpg.scene.menu.inGame;

import com.auraRpg.data.AMap;
import com.auraRpg.gui.AuraListViewLarge;
import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuInGameSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;

public class AMenuIGTeleportSceneStrategy extends AMenuInGameSceneStrategy {
	public AMenuIGTeleportSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, null);
	}
	
	private AMap[] maps;
	public void setMaps(AMap[] maps) {
		this.maps = maps;
		this.datas = null;
	}

	private AuraListViewLarge lvMaps;
	private AMapData datas;
	public AMapData getDatas() {
		if (datas == null) {
			datas = new AMapData(maps);
		}
		return datas;
	}
	
	@Override
	public void onCreate() {
		if (isSuperLoaded())
			return;
		super.onCreate();
		this.lvMaps = new AuraListViewLarge(getEngine(), 165, getTxTitre().getHeight()) {
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
			@Override
			public VTexture getTextureIcones() {
				return AuraTextureChooser.GUI_PUCE_TRAVEL.getTexture();
			}
			@Override
			public void onTouchElement(Object o) {
				AMap newMap = (AMap) o;
				if (newMap != null) {
					AMap.launchMap(getEngine(), getContextMemory(), 
							getAuraPreferences().getCurrentSaveGame(), newMap);
				}
			}
			@Override
			public String objectToString(Object o) {
				AMap map = (AMap) o;
				return "- " + map.getName(); //$NON-NLS-1$
			}
		};
		lvMaps.attach(getScene());
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		lvMaps.print(1);
		getEngine().log("Travel agency, map available=" + maps.length + " DATA=" + datas.getCountTotalRow()); //$NON-NLS-1$ //$NON-NLS-2$
	}
	@Override
	public void onRemove() {
		lvMaps.detach();
		super.onRemove();
	}
	
	private class AMapData extends VPageData<AMap> {
		public AMapData(AMap[] datas) {
			super(datas, 9);
		}	
	}
}