package com.kevwong.few;

public class MatchCount {

	private static int match2;
	private static int health2;
	private static int health3;

	public static void setMatch(int match) {
		match2 = match;
	}
	
	public static int getMatch() {
		return match2;
	}
	
	public static void p1setHealth(int health) {
		health2 = health;
	}
	
	public static int p1getHealth() {
		return health2;
	}
	
	public static void p2setHealth(int health) {
		health3 = health;
	}
	
	public static int p2getHealth() {
		return health3;
	}
	
}
