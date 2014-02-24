package com.parse.starter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	private AsyncImageLoader imageLoader = new AsyncImageLoader();
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
    	Toast.makeText(this, "搜索Brand!", Toast.LENGTH_SHORT).show();

        /* 关闭当前的Activity */
        //MainActivity.this.finish();
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
    	EditText e = (EditText)findViewById(R.id.editText1);    	
    	String input_brand = e.getText().toString();
    	try{
	    	query.whereContains("brandName", input_brand);
	    	query.findInBackground(new FindCallback<ParseObject>() {
	    	    public void done(List<ParseObject> productList, ParseException e) {
	    	    	TextView t = (TextView)findViewById(R.id.textView1);
	    	        if (e == null) {
	    	            Log.d("product", "Retrieved " + productList.size() + " products");
	    	            ParseObject p = productList.get(0);
	    	    		t.setText(p.getString("description"));	
	    	    		
	    	    		final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	    				imageLoader.loadDrawable(p.getString("brandLogoUrl"), new com.parse.starter.AsyncImageLoader.ImageCallback() {

	    					public void imageLoaded(Drawable imageDrawable, String imageUrl) {
	    						imageView.setImageDrawable(imageDrawable);
	    					}
	    				});
	    	    		
	    	    		
	    	        } else {
	    	            Log.d("product", "Error: " + e.getMessage());
	    	            t.setText("无此Brand");
	    	        }
	    	    }
	    	});
	    } catch (Exception e1) {
		
	    }

    }
}
