package com.kevwong.few;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayResult extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_result);
        
        
        // User Selected Sequence
        int[] attackSequence = ViewPagerActivity.getAttackSequence();
        
        Avatar p1 = new Avatar();
        Avatar p2 = new Avatar();
        
		String newLine = System.getProperty("line.separator");
		
		if(MatchCount.getMatch() == 0) {
			p1.setHealth(100);
			p2.setHealth(100);
		} else {
			p1.setHealth(PlayerHealth.p1get());
			p2.setHealth(PlayerHealth.p2get());
		}
		
		MatchCount.setMatch(MatchCount.getMatch()+1);
		
		if((p1.getHealth() > 0) && (p2.getHealth() > 0)){
			
			// Players Random Attack Sequence
			p1.setAttack(attackSequence);
			p2.setAttack(RandomSequence.main());
			
			// Check Attack Sequence match Winner
			int[] attackResult =  CompareAttacks.main(p1.getAttack(), p2.getAttack());
			
			// Create Objects
			TextView playerAtk = (TextView) findViewById(R.id.attackSequence);
			TextView cpuAtk = (TextView) findViewById(R.id.cpuAttackSequence);
			TextView compareAtk = (TextView) findViewById(R.id.compareAtk);
			TextView p1Health = (TextView) findViewById(R.id.p1Health);
			TextView p2Health = (TextView) findViewById(R.id.p2Health);
			TextView status = (TextView) findViewById(R.id.status);
			Button restart = (Button) findViewById(R.id.restart);
			
			// Status
			status.setVisibility(View.INVISIBLE);
			restart.setVisibility(View.INVISIBLE);
			
			// Display Attacks
			playerAtk.setText(Arrays.toString(p1.getAttack()));
			cpuAtk.setText(Arrays.toString(p2.getAttack()));
			compareAtk.setText(Arrays.toString(attackResult));
			
			Log.d("", "======== ATTACKS ==========");
			Log.d("Random Attack p1", Arrays.toString(p1.getAttack()));
			Log.d("Random Attack p2", Arrays.toString(p2.getAttack()));
			
			Log.d("", "======== WINNERS ==========");
			Log.d("Compare Attack", Arrays.toString(attackResult));
			Log.d("", newLine+"======== DAMAGE ===========");
			
			// Do Attack Damage and Bonus Streak Damage
			Damage.main(attackResult, p1, p2);
			
			p1Health.setText(Integer.toString(p1.getHealth()));
			p2Health.setText(Integer.toString(p2.getHealth()));
			
			//Log.d("", newLine+"======== RESULT ===========");
			//Log.d("p1 Health", Integer.toString(p1.getHealth()));
			//Log.d("p2 Health", Integer.toString(p2.getHealth()));
			
			Button btnNextRound = (Button) findViewById(R.id.nextRound);
			
			btnNextRound.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// Create an intent
					Intent kevIntent = new Intent(v.getContext(), ViewPagerActivity.class);
					
					// Start the created intent
					v.getContext().startActivity(kevIntent);
				}
			});
		
		} 
		
		PlayerHealth.p1set(p1.getHealth());
		PlayerHealth.p2set(p2.getHealth());
        
		if((PlayerHealth.p1get() <= 0) || (PlayerHealth.p2get() <= 0)){
			
			Button btnNextRound = (Button) findViewById(R.id.nextRound);
			TextView status = (TextView) findViewById(R.id.status);
			Button restart = (Button) findViewById(R.id.restart);
			
			if(PlayerHealth.p1get() > PlayerHealth.p2get()){
			
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
					
					// Create an intent
					Intent kevIntent = new Intent(v.getContext(), ViewPagerActivity.class);
					
					// Start the created intent
					v.getContext().startActivity(kevIntent);
				}
			});
			
		}
		
	}
	
}
