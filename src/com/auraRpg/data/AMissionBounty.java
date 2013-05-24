package com.auraRpg.data;

import com.auraRpg.AuraEntity;
import com.auraRpg.saveGame.AuraSaveGame;
import com.auraRpg.scene.AContextSceneMemory;
import com.voidEngine.engine.VAbstractEngine;
import com.voidEngine.engine.ressources.VSpeech;

public class AMissionBounty extends AMission {
	private final ANpc target;
	public static String PROP_TARGET = "target"; //$NON-NLS-1$
	
	private int count;
	public static String PROP_COUNT = "count"; //$NON-NLS-1$
	
	private final int maxCount;
	public static String PROP_MAX_COUNT = "maxCount"; //$NON-NLS-1$
	
	public AMissionBounty(AMissionType type, AAgent giver, VSpeech startDesc, VSpeech endDesc, long timeEnd, AMap plaCible, AMap escaCible, ANpc target, int count, int maxCount) {
		super(type, giver, startDesc, endDesc, timeEnd, plaCible, escaCible);
		this.target = target;
		this.count = count;
		this.maxCount = maxCount;
	}
	
	public ANpc getTarget() {
		return target;
	}
	
	public int getCount() {
		return count;
	}
	public void addCount() {
		this.count += 1;
	}
	
	public int getMaxCount() {
		return maxCount;
	}
	
	@Override
	public String onRowDescription(String row) {
		row = row.replaceAll("<T>", getTarget().getName()); //$NON-NLS-1$
		row = row.replaceAll("<C>", ""+getCount()); //$NON-NLS-1$ //$NON-NLS-2$
		row = row.replaceAll("<M>", ""+getMaxCount()); //$NON-NLS-1$ //$NON-NLS-2$
		return row;
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

	public static void checker(VAbstractEngine engine, AContextSceneMemory ctx, AuraSaveGame sg, AuraEntity entity) {
		for (AMission m: sg.getMissions().getMissionsByType(AMissionType.BOUNTY)) {
			if (m.getEscaCible() == null || m.getEscaCible().equals(sg.getMap())) {
				AMissionBounty mission = (AMissionBounty) m;
				if (entity.getUserData() != null
						&& entity.getUserData() instanceof ANpc 
						&& ((ANpc) entity.getUserData()).getId() == mission.getTarget().getId()) {
					engine.log("Mission bounty: "+mission.getCount()+"/"+mission.getMaxCount()); //$NON-NLS-1$ //$NON-NLS-2$
					mission.addCount();
					if (mission.getCount() >= mission.getMaxCount()) {
						sg.getMissions().terminerMission(engine, m);
						engine.getVSceneManager().switchTo(
							ctx.getEndMissionScene(m), 
							false, true, false);
					}
					return;
				}
			}
		}
	}
}