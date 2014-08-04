package com.kevwong.few;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class AttackConvert {

	public static void main(int[] attack, View v, Object[] p, boolean slow) {
		
		for(int i = 0; i <= attack.length-1; i++){
			
			if(attack[i] == 0) {
				
				final Drawable d = v.getResources().getDrawable(R.drawable.sbtn_fire);
				final ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				if(slow) {
					
					// Create a Delay for View to Appear
					
					image.postDelayed(new Runnable() {
				        public void run() {
				        	image.setImageDrawable(d);
				        }
				    }, i * 1000+500);
				} else {
					image.setImageDrawable(d);
				}
				
			} else if(attack[i] == 1) {
				
				final Drawable d = v.getResources().getDrawable(R.drawable.sbtn_earth);
				final ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				if(slow) {
					
					// Create a Delay for View to Appear
					
					image.postDelayed(new Runnable() {
				        public void run() {
				        	image.setImageDrawable(d);
				        }
				    }, i * 1000+500);
				} else {
					image.setImageDrawable(d);
				}
				
			} else if(attack[i] == 2) {
				
				final Drawable d = v.getResources().getDrawable(R.drawable.sbtn_water);
				final ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				if(slow) {
					
					// Create a Delay for View to Appear
					
					image.postDelayed(new Runnable() {
				        public void run() {
				        	image.setImageDrawable(d);
				        }
				    }, i * 1000+500);
				} else {
					image.setImageDrawable(d);
				}
				
			}
			
		}
		
	}
	
}
