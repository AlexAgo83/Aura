package com.auraRpg;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.gui.AuraListViewMedium;
import com.auraRpg.ressources.AuraI18nChooser;
import com.auraRpg.ressources.AuraSpeechChooser;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.menu.AMenuSystemSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VTexture;
import com.voidEngine.tools.VPageData;
import com.voidEngine.tools.VTextFormater;

public class AMenuSystemAboutSceneStrategy extends AMenuSystemSceneStrategy {
	public AMenuSystemAboutSceneStrategy(VAbstractEngine engine, AContextSceneMemory contextMemory) {
		super(engine, contextMemory, AuraI18nChooser.TITRE_ABOUT);
	}
	
	private VPageData<String> datas;
	public VPageData<String> getDatas() {
		if (datas == null) {
			List<String> text = new ArrayList<String>();
			for (String row : AuraSpeechChooser.ABOUT.getSpeech().getSpeech()) {
				String[] rows = VTextFormater.format(row, 70);
				for (String r: rows) {
					text.add(r);
				}
			}
			datas = new VPageData<String>(text.toArray(new String[0]), 14);
		}
		return datas;
	}
	
	private AuraListViewMedium lvAbout;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.lvAbout = new AuraListViewMedium(getEngine(), getMinX()+5, getTxTitre().getHeight() + 10) {
			@Override public VTexture getTextureIcones() { return null; }
			@Override public void onTouchElement(Object o) {}
			@Override
			public String objectToString(Object o) {
				return o.toString();
			}
			@Override
			public VPageData<?> getPageData() {
				return getDatas();
			}
		};
		lvAbout.attach(getScene());
	}
	
	@Override
	public void onRefresh() {
		super.onRefresh();
		lvAbout.print(1);
	}
	
	@Override
	public void onRemove() {
		lvAbout.detach();
		super.onRemove();
	}
}
