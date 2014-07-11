package com.kevwong.few;

public class RandomSequence {
	
	public static int[] main() {
		
		int[] attack = new int[5];
		
		for(int i = 0; i <= attack.length-1; i++) {
			attack[i] = RandomAttack.main(0, 2);
		}
		
		return attack;
		
	}
	
}