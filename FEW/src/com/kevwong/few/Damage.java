package com.kevwong.few;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Damage {
	
	public static void main(int[] attackResult, Avatar p1, Avatar p2, ViewGroup v) {
		
		ViewGroup theLayout = v;
	
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
		
		// Check for Special Actions =================================================
		
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
				
				// Player 1 wins ===============================================================
				if(p1Healing) {
					// Healing
					p1.setHealth(p1.getHealth() + 10);
					System.out.println("Healing: p1 recovers Health : "+p1.getHealth());
					
					animateStatus(theLayout, 10, 1, 1);
				} 
				if(p1Raging) {
					// Rage Attack
					int finalDamage = (basicDamage + p1BonusDamage) * 2;
					
					if(p2Blocking){
						// Blocking
						if(p2.getArmour() <= 0) {
							finalDamage = (basicDamage + p1BonusDamage) * 2;
						} else {
							finalDamage = basicDamage + p1BonusDamage;
							p2.setArmour(p2.getArmour() - 15);
						}
					}
					
					animateStatus(theLayout, finalDamage, 1, 0);
					
					p2.setHealth(p2.getHealth() - finalDamage);
					
					Log.d("p1 does " + finalDamage + " Rage Damage ", "p2 Health = " + Integer.toString(p2.getHealth()));
					
					// Check p2 health
					if(p2.getHealth() <= 0) {
						Log.d("DEAD","Player 2 DEAD");
						break;
					}
					
					System.out.println("RAGING");
				
				} 
				if(!p1Healing && !p1Raging) {
					
					// Normal Attack
					int finalDamage;
					
					if(p2Blocking){
						// Blocking
						if(p2.getArmour() <= 0) {
							finalDamage = basicDamage + p1BonusDamage;
						} else {
							finalDamage = basicDamage/2 + p1BonusDamage/2;
							p2.setArmour(p2.getArmour() - 10);
						}
						
					} else {
						finalDamage = basicDamage + p1BonusDamage;
					}
					
					animateStatus(theLayout, finalDamage, 1, 0);
					
					// Reduce player Health
					p2.setHealth(p2.getHealth() - finalDamage);
					
					Log.d("p1 does " + finalDamage + " Damage ", "p2 Health = " + p2.getHealth());
					
					// Check p2 health
					if(p2.getHealth() <= 0) {
						Log.d("DEAD","Player 2 DEAD");
						break;
					}
				
				}
				
			} else if(attackResult[i] == 2) {
				
				// Player 2 wins ===============================================================
				if(p2Healing){
					// Healing
					p2.setHealth(p2.getHealth() + 10);
					System.out.println("Healing: p2 recovers Health : " + p2.getHealth());
					
					animateStatus(theLayout, 10, 1, 1);
				} 
				if(p2Raging) {
					// Rage Attack
					int finalDamage;
				
					if(p1Blocking){
						// Blocking
						if(p1.getArmour() <= 0) {
							finalDamage = (basicDamage + p2BonusDamage) * 2;
						} else {
							finalDamage = basicDamage + p2BonusDamage;
							p1.setArmour(p1.getArmour() - 15);
						}
					} else {
						finalDamage = (basicDamage + p2BonusDamage) * 2;
					}
					
					animateStatus(theLayout, finalDamage, 2, 0);
					
					p1.setHealth(p1.getHealth() - finalDamage);
					
					Log.d("p2 does " + finalDamage + " Rage Damage ", "p1 Health = " + p1.getHealth());
					
					// Check p1 health
					if(p1.getHealth() <= 0) {
						Log.d("DEAD","Player 1 DEAD");
						break;
					}
					
				}
				
				if(!p2Healing && !p2Raging) {
					
					// Normal Attack
					int finalDamage  = basicDamage + p2BonusDamage;
					
					if(p1Blocking){
						
						if(p1.getArmour() <= 0) {
							finalDamage = basicDamage + p2BonusDamage;
						} else {
							finalDamage = basicDamage/2 + p2BonusDamage/2;
							p1.setArmour(p1.getArmour() - 10);
						}
						
					}
					
					animateStatus(theLayout, finalDamage, 2, 0);
					
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
	
	public static void animateStatus(ViewGroup v, int damage, int player, int healing) {
		
		ViewGroup p1Results = (ViewGroup) v.findViewById(R.id.p1_results_area);
		ViewGroup p2Results = (ViewGroup) v.findViewById(R.id.p2_results_area);
		
		final TextView myText= new TextView(v.getContext());
		
		if(healing != 1) {
			myText.setText(Integer.toString(damage)+ " Damage");
			myText.setTextSize(18.0f);
			myText.setTypeface(null, Typeface.BOLD);
			myText.setTextColor(Color.parseColor("#FF4D4D"));
			myText.setGravity(Gravity.CENTER | Gravity.BOTTOM);
			myText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		} else {
			myText.setText("Heal "+Integer.toString(damage)+ " Life");
			myText.setTextSize(18.0f);
			myText.setTypeface(null, Typeface.BOLD);
			myText.setTextColor(Color.parseColor("#73DCFF"));
			myText.setGravity(Gravity.CENTER | Gravity.BOTTOM);
			myText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		
		if(healing != 1) {
		    if(player == 1){
		    	p2Results.addView(myText);
		    } else if(player == 2) {
		    	p1Results.addView(myText);
		    }
		} else {
			if(player == 1){
		    	p1Results.addView(myText);
		    } else if(player == 2) {
		    	p2Results.addView(myText);
		    }
		}
	    
	    final TranslateAnimation translateAnimation = new TranslateAnimation(
	    		Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0, 
                Animation.ABSOLUTE, -250);
        translateAnimation.setDuration(3000);
        
        final AlphaAnimation alphaAnimationReverse = new AlphaAnimation(1, 0);
        alphaAnimationReverse.setInterpolator(new AccelerateInterpolator());
        alphaAnimationReverse.setStartOffset(2000);
        alphaAnimationReverse.setDuration(1000);
        alphaAnimationReverse.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				myText.setAlpha(0);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        final AnimationSet setAnimation = new AnimationSet(true);
		setAnimation.addAnimation(translateAnimation);
	    setAnimation.addAnimation(alphaAnimationReverse);
	    
		setupAnimation(myText, setAnimation);
		
	}
	private static void setupAnimation(View v, final Animation animation) {
			
		v.startAnimation(animation);

	}
}

