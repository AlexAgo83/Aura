package com.auraRpg.data;

import java.util.ArrayList;
import java.util.List;

import com.auraRpg.ressources.AuraTextureChooser;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.auraRpg.scene.board.APlaneteSceneStrategy;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.map.VMap;
import com.voidEngine.engine.ressources.VTexture;

public enum AMap {
	PLANETE_A( 1, AMapType.PLANETE, "Alambra", 	
		AuraTextureChooser.MAP_ICONE_A.getTexture(), 
		AuraTextureChooser.MAP_FOND_A.getTexture(), 
		"PAEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_A;
		}
	},
	PLANETE_B( 2, AMapType.PLANETE, "Barathon",
		AuraTextureChooser.MAP_ICONE_B.getTexture(), 
		AuraTextureChooser.MAP_FOND_B.getTexture(), 
		"PBEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_B;
		}
	},
	PLANETE_C( 3, AMapType.PLANETE, "Chen Advar",
		AuraTextureChooser.MAP_ICONE_C.getTexture(), 
		AuraTextureChooser.MAP_FOND_C.getTexture(), 
		"PCEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_C;
		}
	},
	PLANETE_D( 4, AMapType.PLANETE, "Deinos",
		AuraTextureChooser.MAP_ICONE_D.getTexture(), 
		AuraTextureChooser.MAP_FOND_D.getTexture(), 
		"PDEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_D;
		}
	},
	PLANETE_E( 5, AMapType.PLANETE, "Eyfir", 		
		AuraTextureChooser.MAP_ICONE_E.getTexture(), 
		AuraTextureChooser.MAP_FOND_E.getTexture(), 
		"PEEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_E;
		}
	},
	PLANETE_F( 6, AMapType.PLANETE, "Fo Ming", 	
		AuraTextureChooser.MAP_ICONE_F.getTexture(), 
		AuraTextureChooser.MAP_FOND_F.getTexture(), 
		"PFEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_F;
		}
	},
	PLANETE_G( 7, AMapType.PLANETE, "Grevislat", 	
		AuraTextureChooser.MAP_ICONE_G.getTexture(), 
		AuraTextureChooser.MAP_FOND_A.getTexture(), 
		"PGEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_G;
		}
	},
	PLANETE_H( 8, AMapType.PLANETE, "Hoctum", 	
		AuraTextureChooser.MAP_ICONE_H.getTexture(), 
		AuraTextureChooser.MAP_FOND_B.getTexture(), 
		"PHEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_H;
		}
	},
	PLANETE_I( 9, AMapType.PLANETE, "Idvyr", 		
		AuraTextureChooser.MAP_ICONE_I.getTexture(), 
		AuraTextureChooser.MAP_FOND_C.getTexture(), 
		"PIEA", null, 16, 16, null) {
		@Override
		public AMap getShip() {
			return AMap.SHIP_I;
		}
	},
		
	SPACE_MAP_TRAVEL(10, AMapType.SPACE, "Galactic Map",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(), 
		"SPACE", null, 0, 0, null) {
		@Override public AMap getShip() { return null; }
	},
		
	SHIP_A(11, AMapType.SHIP, "Covert InterBus",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_A) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_B(12, AMapType.SHIP, "Royal Air Transit",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_B) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_C(13, AMapType.SHIP, "ECO-Logistics",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_C) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_D(14, AMapType.SHIP, "U-Wing",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_D) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_E(15, AMapType.SHIP, "InterCOM",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_E) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_F(16, AMapType.SHIP, "EXEC-Stars",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_F) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_G(17, AMapType.SHIP, "NOLINE",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_G) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_H(18, AMapType.SHIP, "Barathos III",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_H) {
		@Override public AMap getShip() { return null; }
	},
	SHIP_I(19, AMapType.SHIP, "S-CAP",
		null,
		AuraTextureChooser.MAP_FOND_X.getTexture(),
		"SHIPA", null, 0, 0, AMap.PLANETE_I) {
		@Override public AMap getShip() { return null; }
	},
	
	ESC_A(20, AMapType.ESCARMOUCHE, "ESCA", null, null, "ESCA", null, 0, 0, null) {
		@Override public AMap getShip() { return null; }
	}
//	,ESC_B(20, AMapType.ESCARMOUCHE, "ESCARMOUCHE-B", null, null, "ESCB", null, 0, 0, null) {
//		@Override public AMap getShip() { return null; }
//	}
	;
	
	private int id;
	private AMapType type;
	private String name;
	private VTexture planeteIcone;
	private VTexture background;	
	private String fileName;
	private String pathSD;
	private float xPosition;
	private float yPosition;
	private AMap planeteRef;
	
	public abstract AMap getShip();
	
	private AMap(int id, AMapType type, String name, 
			VTexture planeteIcone, VTexture background, String fileName, String pathSD, 
			float xPosition, float yPosition, AMap planeteRef) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.planeteIcone = planeteIcone;
		this.background = background;
		this.fileName = fileName;
		this.pathSD = pathSD;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.planeteRef = planeteRef;
	}
	
	public int getId() {
		return id;
	}
	public AMapType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public VTexture getPlaneteIcone() {
		if (planeteIcone == null && getPlaneteRef() != null) {
			return getPlaneteRef().getPlaneteIcone();
		}
		return planeteIcone;
	}
	public VTexture getBackground() {
		if (background == null && getPlaneteRef() != null) {
			return getPlaneteRef().getBackground();
		}
		return background;
	}
	public String getFileName() {
		return fileName;
	}
//	@Deprecated
	public String getPathSD() {
//		return "voidEngine/";
		return pathSD;
	}
	public float getXPosition() {
		return xPosition;
	}
	public float getYPosition() {
		return yPosition;
	}
	public AMap getPlaneteRef() {
		return planeteRef;
	}

	public VMap load(VAbstractEngine e) {
		VMap m = null;
		if (getPathSD() != null)
			m = VMap.loadFromSD(e, getPathSD(), getFileName());
		else
			m = VMap.loadFromAsset(e, getFileName());
		return m;
	}
	
	public static AMap getById(int id) {
		for (AMap m: AMap.values()) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	public static AMap getByName(String name) {
		for (AMap m: AMap.values()) {
			if (m.getName().equals(name))
				return m;
		}
		return null;
	}
	
	public static AMap[] getByType(AMapType t) {
		List<AMap> ms = new ArrayList<AMap>();
		for (AMap m: AMap.values()) {
			if (m.getType() == t)
				ms.add(m);
		}
		return ms.toArray(new AMap[0]);
	}
	
	public static AMap getRandom(AMapType t) {
		AMap[] ms = getByType(t);
		if (ms != null && ms.length > 0)
			return ms[(int) Math.round((ms.length-1)*Math.random())];
		return null;
	}
	
	public static void launchMap(VAbstractEngine e, AContextSceneMemory ctx, AuraSaveGame sg, AMap m) {
		if (sg.getMap() != null && sg.getMap().getType() == AMapType.PLANETE)
			sg.setLastMap(sg.getMap());
		sg.setMap(m);
		sg.setX(m.getXPosition());
		sg.setY(m.getYPosition());
		sg.save();
		APlaneteSceneStrategy newBoard = new APlaneteSceneStrategy(e, ctx, m);
		ctx.setLastPlaneteBoard(newBoard);
		if (e.getVSceneManager().getLast() != null)
			e.getVSceneManager().getLast().onUnload();
		e.getVSceneManager().switchTo(newBoard, false, false, false);
	}
}