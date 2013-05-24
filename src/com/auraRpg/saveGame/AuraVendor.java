package com.auraRpg.saveGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.auraRpg.data.AItemGoods;
import com.voidEngine.engine.VAbstractEngine;

public class AuraVendor {
	public static final int MAX_SLOT_VENDOR = 50;
	public static final long TICK_CHECK_GEN = 1000*60*60*24;
	
	private List<AuraVendorInfo> vendors;
	public static final String PROP_VENDORS = "vds";

	protected AuraVendor() {}
	
	public List<AuraVendorInfo> getVendors() {
		return vendors;
	}
	public void setVendors(List<AuraVendorInfo> vendors) {
		this.vendors = vendors;
	}
	
	public void load(SharedPreferences p, int id) {
		setVendors(new ArrayList<AuraVendorInfo>());
		for (int i=0; i<MAX_SLOT_VENDOR; i++) {
			String baseQuery = id+"_"+PROP_VENDORS+"_"+i;
			AuraVendorInfo v = new AuraVendorInfo();
			if (v.load(p, baseQuery))
				getVendors().add(v);
		}
	}
	public void save(Editor e, int id) {
		AuraVendorInfo[] vs = getVendors().toArray(new AuraVendorInfo[0]);
		for (int i=0; i<MAX_SLOT_VENDOR; i++) {
			String baseQuery = id+"_"+PROP_VENDORS+"_"+i;
			AuraVendorInfo toSave = null;
			if (i < vs.length) {
				toSave = vs[i];
			} else {
				toSave = new AuraVendorInfo();
				toSave.setEntityId(-1);
				toSave.setItems(new HashMap<AItemGoods, AuraItemInfo>());
			}
			toSave.save(e, baseQuery);
		}
	}
	public void delete(Editor e, int id) {
		AuraVendorInfo[] vs = getVendors().toArray(new AuraVendorInfo[0]);
		for (int i=0; i<MAX_SLOT_VENDOR; i++) {
			String baseQuery = id+"_"+PROP_VENDORS+"_"+i;
			AuraVendorInfo toDelete = null;
			if (i < vs.length) {
				toDelete = vs[i];
			} else {
				toDelete = new AuraVendorInfo();
			}
			toDelete.delete(e, baseQuery);
		}
	}
	
	public AuraVendorInfo getByEntityId(int id) {
		for (AuraVendorInfo vi: getVendors()) {
			if (vi.getEntityId() == id)
				return vi;
		}
		return null;
	}
	
	public AuraVendorInfo genererVendor(VAbstractEngine engine, int entityId, boolean force) {
		engine.log("start gen vendor, id="+entityId);
		AuraVendorInfo v = getByEntityId(entityId);
		if (v == null) {
			v = new AuraVendorInfo();
			v.setEntityId(entityId);
			v.setItems(new HashMap<AItemGoods, AuraItemInfo>());
			getVendors().add(v);
		} else if (!force) {
			return v;
		}
		v.generer(engine);
		return v;
	}
	
	public void run(VAbstractEngine engine, long curMillis) {
		for (AuraVendorInfo v: getVendors()) {
			if (v.getLastCheck()+TICK_CHECK_GEN<curMillis) {
				v.generer(engine);
				v.setLastCheck(curMillis);
			}
		}
	}
}