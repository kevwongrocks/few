package com.kevwong.few;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartAttack extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.start_attack, container, false);
		
		Button btnAttack = (Button) myFragmentView.findViewById(R.id.buttonAttack);
		btnAttack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Create an intent
				Intent kevIntent = new Intent(v.getContext(), DisplayResult.class);
				
				// Start the created intent
				v.getContext().startActivity(kevIntent);
			}
		});
		
		return myFragmentView;
	}
	
}
