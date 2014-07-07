package com.kevwong.few;

import android.util.Log;

public class Damage {
	
	public static void main(int[] attackResult, Avatar p1, Avatar p2) {
	
		int basicDamage = 10;
		int streakDamage = 3;
		int lastValue = attackResult[0];
		int currentLength = 1;
		
		for(int i = 0; i <= attackResult.length-1; i++) {
		
			int p1BonusDamage = 0;
			int p2BonusDamage = 0;
			
			// Skip the first win
			if(i != 0) {
				
				// Check Streak length
				if(lastValue == attackResult[i]){
					currentLength++;
				} else {
					currentLength = 1;
				}
				
				// Set the last attack
				lastValue = attackResult[i];
				
				// Calculate streak bonus damage
				if((currentLength >= 2) && (attackResult[i] != 0)){
					if(attackResult[i] == 1) {
						p1BonusDamage = (currentLength-1) * streakDamage;
					} else if(attackResult[i] == 2) {
						p2BonusDamage = (currentLength-1) * streakDamage;
					}
				}
				
			}
			
			if(attackResult[i] == 1) {
				
				// Player 1 wins
				int finalDamage = basicDamage + p1BonusDamage;
				
				p2.setHealth(p2.getHealth() - finalDamage);
				
				Log.d("p1 does " + finalDamage + " Damage ", "p2 Health = " + Integer.toString(p2.getHealth()));
				
				// Check health
				if(p2.getHealth() <= 0) {
					Log.d("DEAD","Player 2 DEAD");
					break;
				}
				
			} else if(attackResult[i] == 2) {
				
				// Player 2 wins
				int finalDamage = basicDamage + p2BonusDamage;
				
				p1.setHealth(p1.getHealth() - finalDamage);
				
				Log.d("p2 does " + finalDamage + " Damage ", "p1 Health = " + Integer.toString(p1.getHealth()));
				
				// Check health
				if(p1.getHealth() <= 0) {
					Log.d("DEAD","Player 1 DEAD");
					break;
				}
				
			} else {
				
				// Tie
				System.out.println("No Damage");
				
			}
		
		}
		
	}
	
}

