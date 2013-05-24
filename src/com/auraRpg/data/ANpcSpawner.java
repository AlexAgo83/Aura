package com.auraRpg.data;

public enum ANpcSpawner {
	BASE_ESCAPADE(1, new ANpc[] {ANpc.GROTH}, true, 1, 5, 6),
	BASE_INTENSIVE(2, new ANpc[] {ANpc.GROTH}, true, 3, 10, 3),
	
	PIRATE_SHIP(10, ANpc.values(), true, 1, 2, 2);
	
	private int id;
	private ANpc[] npcToSpawn;
	private boolean autoAgro;
	private int maxSpawn;
	private int maxCountSpawn;
	private long timeBeforeSpawn;
	
	private ANpcSpawner(int id, ANpc[] npcToSpawn, boolean autoAgro, int maxSpawn, int maxCountSpawn, long timeBeforeSpawn) {
		this.id = id;
		this.npcToSpawn = npcToSpawn;
		this.autoAgro = autoAgro;
		this.maxSpawn = maxSpawn;
		this.maxCountSpawn = maxCountSpawn;
		this.timeBeforeSpawn = timeBeforeSpawn;
	}
	
	public int getId() {
		return id;
	}
	public ANpc[] getNpcToSpawn() {
		return npcToSpawn;
	}
	public boolean isAutoAgro() {
		return autoAgro;
	}
	public int getMaxSpawn() {
		return maxSpawn;
	}
	public int getMaxCountSpawn() {
		return maxCountSpawn;
	}
	public long getTimeBeforeSpawn() {
		return timeBeforeSpawn;
	}
	
	public static ANpcSpawner getById(int id) {
		for (ANpcSpawner ns: ANpcSpawner.values()) {
			if (ns.getId() == id)
				return ns;
		}
		return null;
	}
	
	public static ANpcSpawner getByNpc(ANpc npc) {
		for (ANpcSpawner s : ANpcSpawner.values()) {
			for (ANpc n: s.getNpcToSpawn()) {
				if (n == npc) {
					return s;
				}
			}
		}
		return null;
	}
}