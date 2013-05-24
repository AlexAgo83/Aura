package com.auraRpg.data;

import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VSpeech;

public class AMissionDelivery extends AMission {
	private final AAgent target;
	public static String PROP_TARGET = "target"; //$NON-NLS-1$
	
	public AMissionDelivery(AMissionType type, AAgent giver, VSpeech startDesc, VSpeech endDesc, long timeEnd, AMap plaCible, AMap escaCible, AAgent target) {
		super(type, giver, startDesc, endDesc, timeEnd, plaCible, escaCible);
		this.target = target;
	}
	
	public AAgent getTarget() {
		return target;
	}
	
	@Override
	public String onRowDescription(String row) {
		return row.replaceAll("<T>", getTarget().getName()); //$NON-NLS-1$
	}
	@Override
	public void onGive(VAbstractEngine engine) {
		engine.log("Mission: give '"+getName(engine)+"'."); //$NON-NLS-1$ //$NON-NLS-2$
	}
	@Override
	public void onFinish(VAbstractEngine engine) {
		engine.log("Mission: finish '"+getName(engine)+"'."); //$NON-NLS-1$ //$NON-NLS-2$
	}
	public void onTargetReach(VAbstractEngine engine) {
		onFinish(engine);
	}
}