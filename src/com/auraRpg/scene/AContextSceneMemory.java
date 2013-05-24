package com.auraRpg.scene;

import com.auraRpg.data.ACorporation;
import com.auraRpg.data.AMap;
import com.auraRpg.data.AMission;
import com.auraRpg.scene.board.APlaneteSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGArmorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGDoctorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGEndMissionSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGInventorySceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGMilitaryArmorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGMilitaryWeaponSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGMissionSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGPoliticsConflictSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGPoliticsStandingSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGSkillsSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGStatusSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGTeleportSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGVendorSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGViewMissionSceneStrategy;
import com.auraRpg.scene.menu.inGame.AMenuIGWeaponSceneStrategy;
import com.auraRpg.scene.menu.system.AMenuSystemAboutSceneStrategy;
import com.auraRpg.scene.menu.system.AMenuSystemAchievementSceneStrategy;
import com.auraRpg.scene.menu.system.AMenuSystemChangeLogsSceneStrategy;
import com.auraRpg.scene.menu.system.AMenuSystemOptionSceneStrategy;
import com.auraRpg.scene.menu.system.AMenuSystemPlaySceneStrategy;
import com.voidEngine.engine.VAbstractEngine;

public class AContextSceneMemory {
	private final VAbstractEngine engine;
	
	private AMenuSystemPlaySceneStrategy playScene;
	private AMenuSystemOptionSceneStrategy optionScene;
	private AMenuSystemAchievementSceneStrategy achievementScene;
	private AMenuSystemChangeLogsSceneStrategy changeLogsScene;
	private AMenuSystemAboutSceneStrategy aboutScene;
	
	private AMenuIGStatusSceneStrategy statusScene;
	private AMenuIGSkillsSceneStrategy skillsScene;
	private AMenuIGInventorySceneStrategy inventoryScene;
	private AMenuIGWeaponSceneStrategy weaponScene;
	private AMenuIGArmorSceneStrategy armorScene;
	
	private AMenuIGMissionSceneStrategy missionScene;
	private AMenuIGViewMissionSceneStrategy viewMissionScene;
	private AMenuIGEndMissionSceneStrategy endMissionScene;
	
	private AMenuIGPoliticsStandingSceneStrategy politicsReputationScene;
	private AMenuIGPoliticsConflictSceneStrategy politicsConflictScene;

	private AMenuIGDoctorSceneStrategy doctorScene;
	private AMenuIGVendorSceneStrategy vendorScene;
	private AMenuIGMilitaryWeaponSceneStrategy militaryWeaponScene;
	private AMenuIGMilitaryArmorSceneStrategy militaryArmorScene;
	private AMenuIGTeleportSceneStrategy teleportScene;
	
	private APlaneteSceneStrategy lastPlaneteBoard;
	
	public AContextSceneMemory(VAbstractEngine engine) {
		this.engine = engine;
	}
	
	public AMenuSystemPlaySceneStrategy getPlayScene() {
		if (playScene == null) {
			playScene = new AMenuSystemPlaySceneStrategy(engine, this);
		}
		return playScene;
	}
	
	public AMenuSystemOptionSceneStrategy getOptionScene() {
		if (optionScene == null) {
			optionScene = new AMenuSystemOptionSceneStrategy(engine, this);
		}
		return optionScene;
	}
	
	public AMenuSystemAchievementSceneStrategy getAchievementScene() {
		if (achievementScene == null) {
			achievementScene = new AMenuSystemAchievementSceneStrategy(engine, this);
		}
		return achievementScene;
	}
	
	public AMenuSystemChangeLogsSceneStrategy getChangeLogsScene() {
		if (changeLogsScene == null) {
			changeLogsScene = new AMenuSystemChangeLogsSceneStrategy(engine, this);
		}
		return changeLogsScene;
	}
	
	public AMenuSystemAboutSceneStrategy getAboutScene() {
		if (aboutScene == null) {
			aboutScene = new AMenuSystemAboutSceneStrategy(engine, this);
		}
		return aboutScene;
	}
	
	public AMenuIGStatusSceneStrategy getStatusScene() {
		if (statusScene == null) {
			statusScene = new AMenuIGStatusSceneStrategy(engine, this);
		}
		return statusScene;
	}
	
	public AMenuIGSkillsSceneStrategy getSkillsScene() {
		if (skillsScene == null) {
			skillsScene = new AMenuIGSkillsSceneStrategy(engine, this);
		}
		return skillsScene;
	}
	
	public AMenuIGInventorySceneStrategy getInventoryScene() {
		if (inventoryScene == null) {
			inventoryScene = new AMenuIGInventorySceneStrategy(engine, this);
		}
		return inventoryScene;
	}
	
	public AMenuIGWeaponSceneStrategy getWeaponScene() {
		if (weaponScene == null) {
			weaponScene = new AMenuIGWeaponSceneStrategy(engine, this);
		}
		return weaponScene;
	}
	
	public AMenuIGArmorSceneStrategy getArmorScene() {
		if (armorScene == null) {
			armorScene = new AMenuIGArmorSceneStrategy(engine, this);
		}
		return armorScene;
	}
	
	public AMenuIGMissionSceneStrategy getMissionScene() {
		if (missionScene == null) {
			missionScene = new AMenuIGMissionSceneStrategy(engine, this);
		}
		return missionScene;
	}
	
	public AMenuIGViewMissionSceneStrategy getViewMissionScene() {
		if (viewMissionScene == null) {
			viewMissionScene = new AMenuIGViewMissionSceneStrategy(engine, this);
		}
		return viewMissionScene;
	}
	
	public AMenuIGEndMissionSceneStrategy getEndMissionScene(AMission mission) {
		if (endMissionScene == null) {
			endMissionScene = new AMenuIGEndMissionSceneStrategy(engine, this);
		}
		endMissionScene.setMission(mission);
		return endMissionScene;
	}
	
	public AMenuIGTeleportSceneStrategy getTravelAgencyScene(AMap[] maps, String titre) {
		if (teleportScene == null) {
			teleportScene = new AMenuIGTeleportSceneStrategy(engine, this);
		}
		teleportScene.setMaps(maps);
		teleportScene.setTitre(titre);
		return teleportScene;
	}
	
	public AMenuIGPoliticsStandingSceneStrategy getPoliticsReputationsScene() {
		if (politicsReputationScene == null) {
			politicsReputationScene = new AMenuIGPoliticsStandingSceneStrategy(engine, this);
		}
		return politicsReputationScene;
	}
	
	public AMenuIGPoliticsConflictSceneStrategy getPoliticsConflictScene() {
		if (politicsConflictScene == null) {
			politicsConflictScene = new AMenuIGPoliticsConflictSceneStrategy(engine, this);
		}
		return politicsConflictScene;
	}
	
	public AMenuIGDoctorSceneStrategy getDoctorScene() {
		if (doctorScene == null) {
			doctorScene = new AMenuIGDoctorSceneStrategy(engine, this);
		}
		return doctorScene;
	}
	
	public AMenuIGVendorSceneStrategy getVendorScene(ACorporation corpOwner) {
		if (vendorScene == null) {
			vendorScene = new AMenuIGVendorSceneStrategy(engine, this);
		}
		vendorScene.setCorpOwner(corpOwner);
		return vendorScene;
	}
	
	public AMenuIGMilitaryWeaponSceneStrategy getMilitaryWeaponScene(ACorporation corpOwner) {
		if (militaryWeaponScene == null) {
			militaryWeaponScene = new AMenuIGMilitaryWeaponSceneStrategy(engine, this);
		}
		militaryWeaponScene.setCorpOwner(corpOwner);
		return militaryWeaponScene;
	}
	
	public AMenuIGMilitaryArmorSceneStrategy getMilitaryArmorScene(ACorporation corpOwner) {
		if (militaryArmorScene == null) {
			militaryArmorScene = new AMenuIGMilitaryArmorSceneStrategy(engine, this);
		}
		militaryArmorScene.setCorpOwner(corpOwner);
		return militaryArmorScene;
	}
	
	public APlaneteSceneStrategy getLastPlaneteBoard() {
		return lastPlaneteBoard;
	}
	public void setLastPlaneteBoard(APlaneteSceneStrategy lastPlaneteBoard) {
		this.lastPlaneteBoard = lastPlaneteBoard;
	}
}