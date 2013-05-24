package com.auraRpg.ressources;

import com.voidEngine.engine.VAbstractEngine;

public enum AuraI18nChooser {
	AGENT_TYPE_VENDOR("agentType.vendor"), //$NON-NLS-1$
	AGENT_TYPE_MISSION_AGENCY("agentType.missionAgency"), //$NON-NLS-1$
	AGENT_TYPE_TRAVEL_AGENCY("agentType.travelAgency"), //$NON-NLS-1$
	AGENT_TYPE_LIFT_MASTER("agentType.liftMaster"), //$NON-NLS-1$
	AGENT_TYPE_DOCTOR("agentType.doctor"), //$NON-NLS-1$
	
	MISSION_TYPE_DELIVERY("missionType.delivery"), //$NON-NLS-1$
	MISSION_TYPE_BOUNTY("missionType.bounty"), //$NON-NLS-1$
	
	DIALOG_FLEE_FAILED("dialog.flee.failed"), //$NON-NLS-1$
	DIALOG_LOOT("dialog.loot"), //$NON-NLS-1$
	DIALOG_TRAVEL_COST("dialog.travelCost"), //$NON-NLS-1$
	DIALOG_NOT_ENOUGH_CREDITS("dialog.notEnoughCredits"), //$NON-NLS-1$
	DIALOG_NOT_ENOUGH_STANDING("dialog.notEnoughStanding"), //$NON-NLS-1$
	DIALOG_NOT_ENOUGH_SLOTS("dialog.notEnoughSlots"), //$NON-NLS-1$
	DIALOG_NOT_ENOUGH_SP("dialog.notEnoughSp"), //$NON-NLS-1$
	DIALOG_EXPLORATION_TOO_SOON_MIN("dialog.exploration.tooSoon.min"), //$NON-NLS-1$
	DIALOG_EXPLORATION_TOO_SOON_SEC("dialog.exploration.tooSoon.sec"), //$NON-NLS-1$
	DIALOG_EXPLORATION_ECHEC("dialog.exploration.echec"), //$NON-NLS-1$
	DIALOG_WELCOME_MESSAGE("dialog.welcome.message"), //$NON-NLS-1$
	DIALOG_SAVEGAME_LOAD("dialog.saveGame.load"), //$NON-NLS-1$
	DIALOG_SAVEGAME_DELETE("dialog.saveGame.delete"), //$NON-NLS-1$
	DIALOG_SAVEGAME_NEW_NAME_NULL("dialog.saveGame.newNameNull"),  //$NON-NLS-1$
	DIALOG_SAVEGAME_INFO("dialog.saveGame.info"), //$NON-NLS-1$
	DIALOG_ENTITY_CLICK_TOO_FAR("dialog.entity.click.tooFar"), //$NON-NLS-1$
	DIALOG_ENTITY_CLICK_REP_NEGATIVE("dialog.entity.click.repNegative"), //$NON-NLS-1$
	DIALOG_MISSION_NO_MORE_SLOT("dialog.mission.noMoreSlot"), //$NON-NLS-1$
	DIALOG_MISSION_LOCATE_ESCA("dialog.mission.locateEsca"), //$NON-NLS-1$
	DIALOG_MISSION_LOCATE_PLANETE("dialog.mission.locatePlanete"), //$NON-NLS-1$
	DIALOG_MISSION_LOCATE_KO("dialog.mission.locate.ko"), //$NON-NLS-1$
	DIALOG_MISSION_EXECUTE_KO("dialog.mission.execute.ko"), //$NON-NLS-1$
	DIALOG_MISSION_FAILED("dialog.mission.failed"), //$NON-NLS-1$
	DIALOG_POLITICS_RANK_AVAILABLE("dialog.politics.rankAvailable"), //$NON-NLS-1$
	DIALOG_CONFLIT_NEW("dialog.conflit.new"), //$NON-NLS-1$
	
	TITRE_ABOUT("titre.about"), //$NON-NLS-1$
	TITRE_ARMOR("titre.armor"), //$NON-NLS-1$
	TITRE_ACHIEVEMENT("titre.achievement"), //$NON-NLS-1$
	TITRE_CHANGE_LOGS("titre.changeLogs"), //$NON-NLS-1$
	TITRE_DOCTOR("titre.doctor"), //$NON-NLS-1$
	TITRE_INVENTORY("titre.inventory"), //$NON-NLS-1$
	TITRE_LIFT("titre.lift"), //$NON-NLS-1$
	TITRE_MISSION("titre.mission"), //$NON-NLS-1$
	TITRE_MILITARY_WEAPON("titre.military.weapon"), //$NON-NLS-1$
	TITRE_MILITARY_ARMOR("titre.military.armor"), //$NON-NLS-1$
	TITRE_NEW_MISSION("titre.newMission"), //$NON-NLS-1$
	TITRE_OPTION("titre.option"), //$NON-NLS-1$
	TITRE_POLITICS_CONFLICT("titre.politicsConflict"), //$NON-NLS-1$
	TITRE_POLITICS_REPUTATION("titre.politicsReputation"), //$NON-NLS-1$
	TITRE_SAVE_GAME("titre.saveGame"), //$NON-NLS-1$
	TITRE_SKILLS("titre.skills"), //$NON-NLS-1$
	TITRE_STARTER("titre.starter"), //$NON-NLS-1$
	TITRE_STATUS("titre.status"), //$NON-NLS-1$
	TITRE_VENDORS("titre.vendors"), //$NON-NLS-1$
	TITRE_WARNING("titre.warning"), //$NON-NLS-1$
	TITRE_WEAPON("titre.weapon"), //$NON-NLS-1$
	TITRE_WELL_DONE("titre.wellDone"), //$NON-NLS-1$
	
	GUI_ACHIEVEMENT_ROWDATA_TIME_PLAYED("gui.achievement.rowData.timePlayed"), //$NON-NLS-1$
	GUI_ACHIEVEMENT_ROWDATA_PLAYED_DEATH("gui.achievement.rowData.playerDeath"), //$NON-NLS-1$
	GUI_ACHIEVEMENT_ROWDATA_MISSION_FINISHED("gui.achievement.rowData.missionFinished"), //$NON-NLS-1$
	GUI_ACHIEVEMENT_ROWDATA_MISSION_REMOVED("gui.achievement.rowData.missionRemoved"), //$NON-NLS-1$
	GUI_ACHIEVEMENT_ROWDATA_MISSION_FAILED("gui.achievement.rowData.missionFailed"), //$NON-NLS-1$
	GUI_ACHIEVEMENT_ROWDATA_PEDOMETER("gui.achievement.rowData.pedometer"), //$NON-NLS-1$

	GUI_DOCTOR_BT_HEAL("gui.doctor.btHeal"), //$NON-NLS-1$
	GUI_DOCTOR_INFO_COST("gui.doctor.infoCost"), //$NON-NLS-1$
	GUI_DOCTOR_NO_NEED("gui.doctor.noNeed"), //$NON-NLS-1$
	
	GUI_INGAME_BT_STATUS("gui.inGame.btStatus"), //$NON-NLS-1$
	GUI_INGAME_BT_INVENTORY("gui.inGame.btInventory"), //$NON-NLS-1$
	GUI_INGAME_BT_RESUME("gui.inGame.btResume"), //$NON-NLS-1$
	GUI_INGAME_BT_SAVE_QUIT("gui.inGame.btSaveQuit"), //$NON-NLS-1$
	GUI_INGAME_BT_MISSION("gui.inGame.btMission"), //$NON-NLS-1$
	GUI_INGAME_BT_POLITICS("gui.inGame.btPolitics"), //$NON-NLS-1$
	GUI_INGAME_BT_LEAVE_ESCA("gui.inGame.btLeaveEsca"), //$NON-NLS-1$
	GUI_INGAME_BT_FLEE("gui.inGame.btFlee"), //$NON-NLS-1$
	GUI_INGAME_BT_EXPLORATION("gui.inGame.btExploration"), //$NON-NLS-1$

	GUI_INVENTORY_DEGATS("gui.inventory.degats"), //$NON-NLS-1$
	GUI_INVENTORY_RESISTANCE("gui.inventory.resistance"), //$NON-NLS-1$
	GUI_INVENTORY_SLOT_USED("gui.inventory.slotUsed"), //$NON-NLS-1$
	GUI_INVENTORY_WEAPON_IN_USE("gui.inventory.weaponInUse"), //$NON-NLS-1$
	GUI_INVENTORY_ARMOR_IN_USE("gui.inventory.armorInUse"), //$NON-NLS-1$
	GUI_INVENTORY_BT_WEAPON("gui.inventory.btWeapon"), //$NON-NLS-1$
	GUI_INVENTORY_BT_ARMOR("gui.inventory.btArmor"), //$NON-NLS-1$

	GUI_MILITARY_BT_GOODS("gui.military.btGoods"), //$NON-NLS-1$
	
	GUI_OPTION_CK_MUSIC("gui.option.ckMusic"), //$NON-NLS-1$
	GUI_OPTION_CK_SOUND("gui.option.ckSound"), //$NON-NLS-1$
	GUI_OPTION_CK_VIBRATOR_FEEDBACK("gui.option.ckVibratorFeedBack"), //$NON-NLS-1$
	GUI_OPTION_CK_BIG_TEXT("gui.option.ckBigText"), //$NON-NLS-1$
	GUI_OPTION_CK_SHOW_PLAYERS_NAME("gui.option.ckShowPlayersName"), //$NON-NLS-1$
	
	GUI_POLITICS_BT_REPUTATION("gui.politics.btReputation"), //$NON-NLS-1$
	GUI_POLITICS_BT_CONFLICT("gui.politics.btConflict"), //$NON-NLS-1$
	GUI_POLITICS_BT_PROMOTE("gui.politics.btPromote"), //$NON-NLS-1$
	GUI_POLITICS_ROWDATA_TITRE_DESC_FACTION("gui.politics.rowData.titreDescFaction"), //$NON-NLS-1$
	GUI_POLITICS_ROWDATA_DESC_FACTION("gui.politics.rowData.descFaction"), //$NON-NLS-1$
	GUI_POLITICS_ROWDATA_TITRE_DESC_CORP("gui.politics.rowData.titreDescCorp"), //$NON-NLS-1$
	GUI_POLITICS_ROWDATA_DESC_CORP("gui.politics.rowData.descCorp"), //$NON-NLS-1$
	GUI_POLITICS_ROWDATA_CONFLICT_INFO("gui.politics.rowData.conflictInfo"), //$NON-NLS-1$
	
	GUI_SAVEGAME_LV_ROW("gui.saveGame.lv.row"), //$NON-NLS-1$
	GUI_SAVEGAME_BT_NEW("gui.saveGame.bt.new"), //$NON-NLS-1$
	GUI_SAVEGAME_BT_LOAD("gui.saveGame.bt.load"), //$NON-NLS-1$
	GUI_SAVEGAME_BT_DELETE("gui.saveGame.bt.delete"), //$NON-NLS-1$

	GUI_STATUS_ROWDATA_HEALTH("gui.status.rowData.health"), //$NON-NLS-1$
	GUI_STATUS_ROWDATA_STARTER_PROFESSION("gui.status.rowData.starterProfession"), //$NON-NLS-1$
	GUI_STATUS_ROWDATA_CREDITS("gui.status.rowData.credits"), //$NON-NLS-1$
	GUI_STATUS_ROWDATA_SKILL_POINTS("gui.status.rowData.skillPoints"), //$NON-NLS-1$
	GUI_STATUS_ROWDATA_SKILLS("gui.status.rowData.skills"), //$NON-NLS-1$
	GUI_STATUS_BT_SKILLS("gui.status.btSkills"), //$NON-NLS-1$
	
	GUI_SKILLS_ROWDATA_SKILLS("gui.skills.rowData.skills"), //$NON-NLS-1$
	
	GUI_SYSTEM_BT_QUIT("gui.system.btQuit"), //$NON-NLS-1$
	GUI_SYSTEM_BT_PLAY("gui.system.btPlay"), //$NON-NLS-1$
	GUI_SYSTEM_BT_OPTION("gui.system.btOption"), //$NON-NLS-1$
	GUI_SYSTEM_BT_ACHIEVEMENT("gui.system.btAchievement"), //$NON-NLS-1$
	GUI_SYSTEM_BT_CHANGE_LOGS("gui.system.btChangeLogs"), //$NON-NLS-1$
	GUI_SYSTEM_BT_ABOUT("gui.system.btAbout"), //$NON-NLS-1$
	
	GUI_TRANSITION_BT_CONTINUE("gui.transition.btContinue"), //$NON-NLS-1$
	
	GUI_TRAVEL_INFO_ATTACK_A("gui.travel.attack.a"), //$NON-NLS-1$
	GUI_TRAVEL_INFO_ATTACK_B("gui.travel.attack.b"), //$NON-NLS-1$
	GUI_TRAVEL_INFO_DONE("gui.travel.done"), //$NON-NLS-1$
	
	GUI_VENDOR_ROWDATA_ITEM("gui.vendor.rowData.item"), //$NON-NLS-1$
	GUI_VENDOR_BT_BUY("gui.vendor.btBuy"), //$NON-NLS-1$
	GUI_VENDOR_BT_SELL("gui.vendor.btSell"), //$NON-NLS-1$
	GUI_VENDOR_BT_TRANSACTION("gui.vendor.btTransaction"), //$NON-NLS-1$
	GUI_VENDOR_BT_MILITARY_WEAPON("gui.vendor.btMilitary.weapon"), //$NON-NLS-1$
	GUI_VENDOR_BT_MILITARY_ARMOR("gui.vendor.btMilitary.armor"), //$NON-NLS-1$
	GUI_VENDOR_CREDITS_LEFT("gui.vendor.creditsLeft"), //$NON-NLS-1$
	
	GUI_VIEW_MISSION_BT_ACCEPT("gui.viewMission.btAccept"), //$NON-NLS-1$
	GUI_VIEW_MISSION_BT_REJECT("gui.viewMission.btReject"), //$NON-NLS-1$
	GUI_VIEW_MISSION_BT_DELETE("gui.viewMission.btDelete"), //$NON-NLS-1$
	GUI_VIEW_MISSION_BT_EXECUTE("gui.viewMission.btExecute"), //$NON-NLS-1$
	GUI_VIEW_MISSION_BT_LOCATE("gui.viewMission.btLocate"); //$NON-NLS-1$
	
	private String i18nId;
	private AuraI18nChooser(String i18nId) {
		this.i18nId = i18nId;
	}
	private String getI18nId() {
		return i18nId;
	}
	public String getMessage(VAbstractEngine engine) {
		return engine.getVRessourceManager().getI18nMessage(getI18nId());
	}
	public String getMessage(VAbstractEngine engine, String...args) {
		return engine.getVRessourceManager().getI18nMessage(getI18nId(), args);
	}
	
	public static void checker(VAbstractEngine engine) {
		for (AuraI18nChooser id: AuraI18nChooser.values()) {
			if (id.getMessage(engine) == null)
				throw new RuntimeException("Fichier incomplet, id="+id.getI18nId()); //$NON-NLS-1$
		}
	}
}