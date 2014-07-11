package com.kevwong.few;

import android.util.Log;

public class Damage {
	
	public static void main(int[] attackResult, Avatar p1, Avatar p2) {
	
		int basicDamage = 10;
		int streakDamage = 3;
		int lastValue = attackResult[0];
		int currentLength = 1;
		int[] p1Attack = p1.getAttack();
		
		boolean p1Healing = false;
		boolean p1Raging = false;
		
		if((p1Attack[0] == 2) && (p1Attack[1] == 2)){
			
			p1Healing = true;
			
		}
		
		if((p1Attack[0] == 0) && (p1Attack[1] == 0)){
			
			p1Raging = true;
			
		}
		
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
				
				if(p1Healing){
					
					p1.setHealth(p1.getHealth() + 10);
					
					System.out.println(Integer.toString(p1.getHealth()));
					
				} else if(p1Raging) {
					
					// Player 1 wins
					int finalDamage = (basicDamage + p1BonusDamage)*2;
					
					p2.setHealth(p2.getHealth() - finalDamage);
					
					Log.d("p1 does " + finalDamage + " Rage Damage ", "p2 Health = " + Integer.toString(p2.getHealth()));
					
					// Check p2 health
					if(p2.getHealth() <= 0) {
						Log.d("DEAD","Player 2 DEAD");
						break;
					}
				
					System.out.println("RAGING");
				
				} else {
					
					// Player 1 wins
					int finalDamage = basicDamage + p1BonusDamage;
					
					p2.setHealth(p2.getHealth() - finalDamage);
					
					Log.d("p1 does " + finalDamage + " Damage ", "p2 Health = " + Integer.toString(p2.getHealth()));
					
					// Check p2 health
					if(p2.getHealth() <= 0) {
						Log.d("DEAD","Player 2 DEAD");
						break;
					}
				
				}
				
			} else if(attackResult[i] == 2) {
				
				int finalDamage;
				
				// Player 2 wins
				if(p1Raging) {
					finalDamage = (basicDamage + p2BonusDamage)*2;
				} else {
					finalDamage = basicDamage + p2BonusDamage;
				}
				
				p1.setHealth(p1.getHealth() - finalDamage);
				
				if(p1Raging) {
					Log.d("p2 does " + finalDamage + " Double Damage ", "p1 Health = " + Integer.toString(p1.getHealth()));
				} else {
					Log.d("p2 does " + finalDamage + " Damage ", "p1 Health = " + Integer.toString(p1.getHealth()));
				}
				
				// Check p1 health
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

