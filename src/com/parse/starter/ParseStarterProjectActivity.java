package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView t = (TextView)findViewById(R.id.headText);
		t.setText("商品点评");		
		ParseAnalytics.trackAppOpened(getIntent());
		
		ParseObject gameScore = new ParseObject("GameScore");
		gameScore.put("score", 1337);
		gameScore.put("playerName", "Sean Plott");
		gameScore.put("cheatMode", false);
		gameScore.saveInBackground();
	}
	
    public void onSearchButtonClick(View view)
    {
    	Toast.makeText(this, "切换Activity!", Toast.LENGTH_SHORT).show();

        /* 关闭当前的Activity */
        //MainActivity.this.finish();
    }
}
