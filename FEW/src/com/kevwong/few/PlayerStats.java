package com.kevwong.few;

public class PlayerStats {
	
	private static int p1Health;
	private static int p2Health;
	private static int p1Armour;
	private static int p2Armour;

	public static void p1setHealth(int health) {
		p1Health = health;
	}
	
	public static int p1getHealth() {
		return p1Health;
	}
	
	public static void p2setHealth(int health) {
		p2Health = health;
	}
	
	public static int p2getHealth() {
		return p2Health;
	}

	public static int p1getArmour() {
		return p1Armour;
	}

	public static void p1setArmour(int p1Armour) {
		PlayerStats.p1Armour = p1Armour;
	}

	public static int p2getArmour() {
		return p2Armour;
	}

	public static void p2setArmour(int p2Armour) {
		PlayerStats.p2Armour = p2Armour;
	}
	
}
