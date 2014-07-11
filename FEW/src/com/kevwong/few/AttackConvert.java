package com.kevwong.few;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class AttackConvert {

	public static void main(int[] attack, View v, Object[] p, boolean slow) {
		
		for(int i = 0; i <= attack.length-1; i++){
			
			if(attack[i] == 0) {
				
				Drawable d = v.getResources().getDrawable(R.drawable.sbtn_fire);
				ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				image.setImageDrawable(d);
				
			} else if(attack[i] == 1) {
				
				Drawable d = v.getResources().getDrawable(R.drawable.sbtn_earth);
				ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				image.setImageDrawable(d);
				
			} else if(attack[i] == 2) {
				
				Drawable d = v.getResources().getDrawable(R.drawable.sbtn_water);
				ImageView image = (ImageView) v.findViewById((Integer) p[i]);
				image.setImageDrawable(d);
				
			}
			
		}
		
	}
	
}
