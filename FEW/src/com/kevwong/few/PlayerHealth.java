package com.kevwong.few;

public class PlayerHealth {
	
	private static int p1Health;
	private static int p2Health;

	public static void p1set(int health) {
		p1Health = health;
	}
	
	public static int p1get() {
		return p1Health;
	}
	
	public static void p2set(int health) {
		p2Health = health;
	}
	
	public static int p2get() {
		return p2Health;
	}
	
}
