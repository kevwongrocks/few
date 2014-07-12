package com.kevwong.few;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayResult extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_result);
        
        
        // User Selected Sequence
        int[] attackSequence = SetAttack.getAttackSequence();
        
        Avatar p1 = new Avatar();
        Avatar p2 = new Avatar();
        
		String newLine = System.getProperty("line.separator");
		
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
		
		MatchCount.setMatch(MatchCount.getMatch()+1);
		
		if((p1.getHealth() > 0) && (p2.getHealth() > 0)){
			
			// Attack Sequence
			p1.setAttack(attackSequence);
			//p1.setAttack(RandomSequence.main());
			p2.setAttack(RandomSequence.main());
			
			View p1Seq = (View) findViewById(R.id.p1_seq);
			View p2Seq = (View) findViewById(R.id.p2_seq);
			
			Object[] p1Position = {R.id.ps1, R.id.ps2, R.id.ps3, R.id.ps4, R.id.ps5};
			Object[] p2Position = {R.id.es1, R.id.es2, R.id.es3, R.id.es4, R.id.es5};
			
			AttackConvert.main(p1.getAttack(), p1Seq, p1Position, false);
			AttackConvert.main(p2.getAttack(), p2Seq, p2Position, true);
			
			// Check Attack Sequence match Winner
			int[] winResult =  CompareAttacks.main(p1.getAttack(), p2.getAttack());
			
			
			TextView p1Health = (TextView) findViewById(R.id.p1_health);
			TextView p2Health = (TextView) findViewById(R.id.p2_health);
			TextView p1Armour = (TextView) findViewById(R.id.p1_armour);
			TextView p2Armour = (TextView) findViewById(R.id.p2_armour);
			TextView status = (TextView) findViewById(R.id.status);
			Button restart = (Button) findViewById(R.id.restart);
			
			// Status
			status.setVisibility(View.INVISIBLE);
			restart.setVisibility(View.INVISIBLE);
			
			Log.d("", "======== ATTACKS ==========");
			Log.d("Attack p1", Arrays.toString(p1.getAttack()));
			Log.d("Random Attack p2", Arrays.toString(p2.getAttack()));
			
			Log.d("", "======== WINNERS ==========");
			Log.d("Compare Attack", Arrays.toString(winResult));
			Log.d("", newLine+"======== DAMAGE ===========");
			
			// Damage and Bonus Streak ===============================================
			ViewGroup disResults = (ViewGroup) findViewById(R.id.top_frame);
			Damage.main(winResult, p1, p2, disResults);
			
			
			
			// SetText for Health and Armour =========================================
			p1Health.setText(Integer.toString(p1.getHealth()));
			p2Health.setText(Integer.toString(p2.getHealth()));
			p1Armour.setText(Integer.toString(p1.getArmour()));
			p2Armour.setText(Integer.toString(p2.getArmour()));
			
			Button btnNextRound = (Button) findViewById(R.id.nextRound);
			
			btnNextRound.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					SetAttack.setCurrent(0);
					
					Intent kevIntent = new Intent(v.getContext(), ViewPagerActivity.class);
					v.getContext().startActivity(kevIntent);
				}
			});
		
		} 
		
		PlayerStats.p1setHealth(p1.getHealth());
		PlayerStats.p2setHealth(p2.getHealth());
		
		PlayerStats.p1setArmour(p1.getArmour());
		PlayerStats.p2setArmour(p2.getArmour());
        
		if((PlayerStats.p1getHealth() <= 0) || (PlayerStats.p2getHealth() <= 0)){
			
			Button btnNextRound = (Button) findViewById(R.id.nextRound);
			TextView status = (TextView) findViewById(R.id.status);
			Button restart = (Button) findViewById(R.id.restart);
			
			if(PlayerStats.p1getHealth() > PlayerStats.p2getHealth()){
			
				status.setText("You Win");
			
			} else {
				
				status.setText("You Lose");
				
			}
			
			status.setVisibility(View.VISIBLE);
			btnNextRound.setVisibility(View.INVISIBLE);
			restart.setVisibility(View.VISIBLE);
			
			restart.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// Reset Variables
					MatchCount.setMatch(0);
					SetAttack.setCurrent(0);
					
					// Create an intent
					Intent kevIntent = new Intent(v.getContext(), ViewPagerActivity.class);
					
					// Start the created intent
					v.getContext().startActivity(kevIntent);
				}
			});
			
		}
		
	}
	
}
