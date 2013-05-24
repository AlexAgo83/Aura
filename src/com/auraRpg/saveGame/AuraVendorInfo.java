package com.auraRpg.saveGame;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AItemGoods;
import com.voidEngine.engine.VAbstractEngine;

public class AuraVendorInfo {
	private int entityId;
	public static final String PROP_ENTITY_ID = "entId";
	
	private long lastCheck;
	public static final String PROP_LAST_CHECK = "lastCheck";
	
	private Map<AItemGoods, AuraItemInfo> items;
	public static final String PROP_ITEMS = "items";
	
	protected AuraVendorInfo() {}

	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public Map<AItemGoods, AuraItemInfo> getItems() {
		return items;
	}
	public void setItems(Map<AItemGoods, AuraItemInfo> items) {
		this.items = items;
	}
	
	public long getLastCheck() {
		return lastCheck;
	}
	public void setLastCheck(long lastCheck) {
		this.lastCheck = lastCheck;
	}
	
	public boolean load(SharedPreferences p, String baseQuery) {
		int entId = p.getInt(baseQuery+PROP_ENTITY_ID, -1);
		if (entId != -1) {
			setEntityId(entId);
			setLastCheck(p.getLong(baseQuery+PROP_LAST_CHECK, 0));
			setItems(new HashMap<AItemGoods, AuraItemInfo>());
			for (AItemGoods it: AItemGoods.values()) {
				AuraItemInfo vi = new AuraItemInfo();
				vi.load(p, baseQuery+PROP_ITEMS+"_"+it.getId());
				getItems().put(it, vi);
			}
			return true;
		}
		return false;
	}
	public void save(Editor e, String baseQuery) {
		if (getEntityId() != -1) {
			e.putInt(baseQuery+PROP_ENTITY_ID, getEntityId());
			e.putLong(baseQuery+PROP_LAST_CHECK, getLastCheck());
			for (AItemGoods it: AItemGoods.values()) {
				AuraItemInfo vi = getItems().get(it);
				if (vi == null) {
					vi = new AuraItemInfo();
				}
				vi.save(e, baseQuery+PROP_ITEMS+"_"+it.getId());
			}
		}
	}
	public void delete(Editor e, String baseQuery) {
		e.remove(baseQuery+PROP_ENTITY_ID);
		e.remove(baseQuery+PROP_LAST_CHECK);
		for (AItemGoods it: AItemGoods.values()) {
			AuraItemInfo vi = getItems() != null ? getItems().get(it) : null;
			if (vi == null) {
				vi = new AuraItemInfo();
			}
			vi.delete(e, baseQuery+PROP_ITEMS+"_"+it.getId());
		}
	}

	public void generer(VAbstractEngine engine) {
		for (AItemGoods it: AItemGoods.values()) {
			AuraItemInfo i = new AuraItemInfo();
			i.generer(engine, it);
			getItems().put(it, i);
		}
	}
	
	public AuraItemInfo getByItem(AItemGoods it) {
		return getItems().get(it);
	}
}