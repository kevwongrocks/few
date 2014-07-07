package com.kevwong.few;

public class RandomSequence {
	
	public static int[] main() {
		
		int[] attack = new int[9];
		
		for(int i = 0; i <= 8; i++) {
			attack[i] = RandomAttack.main(0, 2);
		}
		
		return attack;
		
	}
	
}