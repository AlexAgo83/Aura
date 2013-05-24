package com.auraRpg.gui;

import org.anddev.andengine.entity.scene.Scene;

import com.auraRpg.ressources.AuraFontChooser;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.gui.VAbstractDialog;

public class AuraDialog extends VAbstractDialog {
	public AuraDialog(VAbstractEngine engine, String text) {
		super(engine, text, 50, 15, 3, AuraFontChooser.SYSTEM_FONT_BIG.getFont());
	}
	public static AuraDialog creerDialog(VAbstractEngine engine, String text, Scene scene) {
		AuraDialog d = new AuraDialog(engine, text);
		d.attach(scene);
		return d;
	}
}
