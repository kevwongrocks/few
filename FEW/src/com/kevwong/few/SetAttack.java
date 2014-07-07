package com.kevwong.few;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SetAttack extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View setAttackView = inflater.inflate(R.layout.set_attack, container, false);
		
		Button buttonFire = (Button) setAttackView.findViewById(R.id.buttonFire);
		Button buttonWater = (Button) setAttackView.findViewById(R.id.buttonWater);
		Button buttonEarth = (Button) setAttackView.findViewById(R.id.buttonEarth);
		
		buttonFire.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				ViewPagerActivity.fireFragment();
			}
		});

		
		buttonWater.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				ViewPagerActivity.waterFragment();
			}
		});

		buttonEarth.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				ViewPagerActivity.earthFragment();
			}
		});
		
		
		return setAttackView;
	}

}
