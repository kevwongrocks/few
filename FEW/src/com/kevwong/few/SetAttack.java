package com.kevwong.few;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SetAttack extends Fragment {
	
	static int[] attackSequence = new int[5];
	static int current = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View setAttackView = inflater.inflate(R.layout.set_attack, container, false);

		Button buttonFire = (Button) setAttackView.findViewById(R.id.buttonFire);
		Button buttonEarth = (Button) setAttackView.findViewById(R.id.buttonEarth);
		Button buttonWater = (Button) setAttackView.findViewById(R.id.buttonWater);
		
		TextView p1Health = (TextView) setAttackView.findViewById(R.id.p1_health);
		TextView p2Health = (TextView) setAttackView.findViewById(R.id.p2_health);
		
		TextView p1Armour = (TextView) setAttackView.findViewById(R.id.p1_armour);
		TextView p2Armour = (TextView) setAttackView.findViewById(R.id.p2_armour);
		
		Avatar p1 = new Avatar();
		Avatar p2 = new Avatar();
		
		if(MatchCount.getMatch() == 0) {
			p1.setHealth(100);
			p2.setHealth(100);
			p1.setArmour(100);
			p2.setArmour(100);
		} else {
			p1.setHealth(PlayerStats.p1getHealth());
			p2.setHealth(PlayerStats.p2getHealth());
			p1.setArmour(PlayerStats.p1getArmour());
			p2.setArmour(PlayerStats.p2getArmour());
		}
		
		p1Health.setText(Integer.toString(p1.getHealth()));
		p2Health.setText(Integer.toString(p2.getHealth()));
		
		p1Armour.setText(Integer.toString(p1.getArmour()));
		p2Armour.setText(Integer.toString(p2.getArmour()));
		
		mButton(buttonFire, 0, setAttackView);
		mButton(buttonEarth, 1, setAttackView);
		mButton(buttonWater, 2, setAttackView);
		
		return setAttackView;
	}
	
	public void mButton(Button button, final int element, final View view) {
		
		final ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float)0.85, 1, (float)0.85, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
		scaleAnimation.setRepeatCount(1);
		scaleAnimation.setDuration(80);
		scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

		final AnimationSet setAnimation = new AnimationSet(true);
		setAnimation.addAnimation(scaleAnimation);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				switch(element) {
					case 0:
						setupAnimation(v, scaleAnimation, R.anim.scale_anim);
						setAttack(getCurrent(), element);
						changeSequenceBtn(element, getCurrent(), view);
						setCurrent(getCurrent()+1);
						checkCurrent(v);
						
						break;
					case 1:
						setupAnimation(v, scaleAnimation, R.anim.scale_anim);
						setAttack(getCurrent(), element);
						changeSequenceBtn(element, getCurrent(), view);
						setCurrent(getCurrent()+1);
						checkCurrent(v);
						
						break;
					case 2:
						setupAnimation(v, scaleAnimation, R.anim.scale_anim);
						setAttack(getCurrent(), element);
						changeSequenceBtn(element, getCurrent(), view);
						setCurrent(getCurrent()+1);
						checkCurrent(v);
						
						break;
				
				}
				
				
			}
			
		});
		
	}
	
	public static void setAttack(int c, int element){
    	attackSequence[c] = element;	
    }
    
    public static int[] getAttackSequence() {
    	int[] send = attackSequence;  
    	return send;
    }
    
    public static void setCurrent(int num) {
    	current = num;
    }
    
	public static int getCurrent() {
		int c = current;
    	return c;	
    }
	
	public static void checkCurrent(View v) {
		if(getCurrent() == 5) {
			Intent kevIntent = new Intent(v.getContext(), DisplayResult.class);
			v.getContext().startActivity(kevIntent);
		}
    }
	
	public void changeSequenceBtn(int element, int current, View v) {
		
		Drawable d = null;
		ImageView image = null;
		
		if(element == 0) {
			d = getResources().getDrawable(R.drawable.sbtn_fire);
		} else if(element == 1) {
			d = getResources().getDrawable(R.drawable.sbtn_earth);
		} else if(element == 2) {
			d = getResources().getDrawable(R.drawable.sbtn_water);
		}
		
		Object[] kev = {R.id.ps1, R.id.ps2, R.id.ps3, R.id.ps4, R.id.ps5};
		
		if(current == 0) {
			image = (ImageView) v.findViewById((Integer) kev[0]);
		} else if(current == 1) {
			image = (ImageView) v.findViewById((Integer) kev[1]);
		} else if(current == 2) {
			image = (ImageView) v.findViewById((Integer) kev[2]);
		} else if(current == 3) {
			image = (ImageView) v.findViewById((Integer) kev[3]);
		} else if(current == 4) {
			image = (ImageView) v.findViewById((Integer) kev[4]);
		}	
		
		image.setImageDrawable(d);
		
	}
	
	private void setupAnimation(View v, final Animation animation, final int animationID) {
		
		v.startAnimation(animation);

	}
	
}
