package com.auraRpg.saveGame;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.ASkills;
import com.auraRpg.data.AStarterProfession;

public class AuraSkills {
	private long skillPoints;
	public static final String PROP_SKILL_POINTS = "sp";
	
	private AStarterProfession starterProfession;
	public static final String PROP_STARTER_PROFESSION = "stProf";
	
	private Map<ASkills, Integer> skillsInfo;
	public static final String PROP_SKILLS = "skills";
	
	public AuraSkills() {}
	
	public long getSkillPoints() {
		return skillPoints;
	}
	public void setSkillPoints(long skillPoints) {
		this.skillPoints = skillPoints;
	}

	public AStarterProfession getStarterProfession() {
		return starterProfession;
	}
	public void setStarterProfession(AStarterProfession starterProfession) {
		this.starterProfession = starterProfession;
	}
	
	public Map<ASkills, Integer> getSkillsInfo() {
		return skillsInfo;
	}
	public void setSkillsInfo(Map<ASkills, Integer> skills) {
		this.skillsInfo = skills;
	}

	public long getNextLevelCost(ASkills s) {
		return 5 + (new BigDecimal((getSkillsInfo().get(s))/5).setScale(0, RoundingMode.DOWN).longValue()*2);
	}
	
	public float getAccurencyFactor() {
		return (float) (Math.log(getSkillsInfo().get(ASkills.ACCURENCY)) / 4);
	}
	public float getStealthFactor() {
		return (float) (Math.log(getSkillsInfo().get(ASkills.STEALTH)) / 4);
	}
	public float getNegociationFactor() {
		return (float) (Math.log(getSkillsInfo().get(ASkills.NEGOCIATION)) / 8);
	}
	public float getIntimidationFactor() {
		return (float) (Math.log(getSkillsInfo().get(ASkills.INTIMIDATION)) / 4);
	}
	public float getExplorationFactor() {
		return (float) (Math.log(getSkillsInfo().get(ASkills.EXPLORATION)) / 4);
	}
	
	public void load(SharedPreferences p, int id) {
		setSkillPoints(p.getLong(id+"_"+PROP_SKILL_POINTS, 0));
		setStarterProfession(AStarterProfession.getById(p.getInt(id+"_"+PROP_STARTER_PROFESSION, -1)));
		setSkillsInfo(new HashMap<ASkills, Integer>());
		for (ASkills s: ASkills.values()) {
			getSkillsInfo().put(s, p.getInt(id+"_"+PROP_SKILLS+"_"+s.getId(), 0));
		}
	}
	public void save(Editor e, int id) {
		e.putLong(id+"_"+PROP_SKILL_POINTS, getSkillPoints());
		e.putInt(id+"_"+PROP_STARTER_PROFESSION, getStarterProfession().getId());
		for (ASkills s: ASkills.values()) {
			e.putInt(id+"_"+PROP_SKILLS+"_"+s.getId(), getSkillsInfo().get(s));
		}
	}
	public void delete(Editor e, int id) {
		e.remove(id+"_"+PROP_SKILL_POINTS);
		e.remove(id+"_"+PROP_STARTER_PROFESSION);
		for (ASkills s: ASkills.values()) {
			e.remove(id+"_"+PROP_SKILLS+"_"+s.getId());
		}
	}
}