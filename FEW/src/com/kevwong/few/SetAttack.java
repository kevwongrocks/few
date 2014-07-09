package com.kevwong.few;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.ViewGroup;
import android.widget.Button;

public class SetAttack extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View setAttackView = inflater.inflate(R.layout.set_attack, container, false);

		Button buttonFire = (Button) setAttackView.findViewById(R.id.buttonFire);
		Button buttonWater = (Button) setAttackView.findViewById(R.id.buttonWater);
		Button buttonEarth = (Button) setAttackView.findViewById(R.id.buttonEarth);
		
		//final ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float)0.5, 1, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);
		//scaleAnimation.setRepeatCount(1);
		//scaleAnimation.setDuration(80);
		//scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

		//final AnimationSet setAnimation = new AnimationSet(true);
		//setAnimation.addAnimation(scaleAnimation);

		buttonFire.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//setupAnimation(v, scaleAnimation, R.anim.scale_anim);

				ViewPagerActivity.fireFragment();
			}
		});
		
		buttonEarth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//setupAnimation(v, scaleAnimation, R.anim.scale_anim);
				
				ViewPagerActivity.earthFragment();
			}
		});

		buttonWater.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//setupAnimation(v, scaleAnimation, R.anim.scale_anim);
				
				ViewPagerActivity.waterFragment();
			}
		});

		return setAttackView;
	}

	/*private void setupAnimation(View v, final Animation animation, final int animationID) {

		v.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				v.startAnimation(animation);
			}
		});
	}*/
}
