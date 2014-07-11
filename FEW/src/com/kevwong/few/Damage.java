package com.kevwong.few;

import android.util.Log;

public class Damage {
	
	public static void main(int[] attackResult, Avatar p1, Avatar p2) {
	
		int basicDamage = 10;
		int streakDamage = 3;
		int lastValue = attackResult[0];
		int currentLength = 1;
		int[] p1Attack = p1.getAttack();
		int[] p2Attack = p2.getAttack();
		
		boolean p1Healing = false;
		boolean p1Raging = false;
		boolean p1Blocking = false;
		boolean p2Healing = false;
		boolean p2Raging = false;
		boolean p2Blocking = false;
		
		if((p1Attack[0] == 0) && (p1Attack[1] == 0)){
			p1Raging = true;
		} else if((p2Attack[0] == 0) && (p2Attack[1] == 0)) {
			p2Raging = true;
		}
		
		if((p1Attack[0] == 1) && (p1Attack[1] == 1)){
			p1Blocking = true;
		} else if((p2Attack[0] == 1) && (p2Attack[1] == 1)){
			p2Blocking = true;
		}
		
		if((p1Attack[0] == 2) && (p1Attack[1] == 2)){
			p1Healing = true;
		} else if((p2Attack[0] == 2) && (p2Attack[1] == 2)){
			p2Healing = true;
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
				
				// Player 1 wins
				
				if(p1Healing){
					
					p1.setHealth(p1.getHealth() + 10);
					
					System.out.println("Healing: p1 Health "+p1.getHealth());
					
				} else if(p1Raging) {
					
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
					
					int finalDamage = basicDamage + p1BonusDamage;
					
					if(p2Blocking){
						
						if(p2.getArmour() <= 0) {
							finalDamage = basicDamage + p1BonusDamage;
						} else {
							finalDamage = basicDamage/2 + p1BonusDamage/2;
							p2.setArmour(p2.getArmour() - 10);
						}
						
					}
					
					p2.setHealth(p2.getHealth() - finalDamage);
					
					Log.d("p1 does " + finalDamage + " Damage ", "p2 Health = " + p2.getHealth());
					
					// Check p2 health
					if(p2.getHealth() <= 0) {
						Log.d("DEAD","Player 2 DEAD");
						break;
					}
				
				}
				
			} else if(attackResult[i] == 2) {
				
				// Player 2 wins
				
				if(p2Healing){
					
					p2.setHealth(p2.getHealth() + 10);
					
					System.out.println("Healing: p2 Health "+p2.getHealth());
					
				} if(p2Raging) {
					
					int finalDamage = (basicDamage + p2BonusDamage)*2;
					
					p1.setHealth(p1.getHealth() - finalDamage);
					
					Log.d("p2 does " + finalDamage + " Rage Damage ", "p1 Health = " + p1.getHealth());
					
					// Check p1 health
					if(p1.getHealth() <= 0) {
						Log.d("DEAD","Player 1 DEAD");
						break;
					}
					
				} else {
					
					int finalDamage  = basicDamage + p2BonusDamage;
					
					if(p1Blocking){
						
						if(p1.getArmour() <= 0) {
							finalDamage = basicDamage + p2BonusDamage;
						} else {
							finalDamage = basicDamage/2 + p2BonusDamage/2;
							p1.setArmour(p1.getArmour() - 10);
						}
						
					}
					
					p1.setHealth(p1.getHealth() - finalDamage);
					
					Log.d("p2 does " + finalDamage + " Damage ", "p1 Health = " + p1.getHealth());
					
					// Check p1 health
					if(p1.getHealth() <= 0) {
						Log.d("DEAD","Player 1 DEAD");
						break;
					}
					
				}
				
			} else {
				
				// Tie
				System.out.println("No Damage");
				
			}
		
		}
		
	}
	
}

