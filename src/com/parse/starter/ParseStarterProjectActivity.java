package com.parse.starter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    	((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        /* 关闭当前的Activity */
        //MainActivity.this.finish();
    	// 一号店数据data
    	/*
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("data");
    	EditText e = (EditText)findViewById(R.id.editText1);    	
    	String input_brand = e.getText().toString();
    	try{
	    	query.whereContains("brandName", input_brand);
	    	query.findInBackground(new FindCallback<ParseObject>() {
	    	    public void done(List<ParseObject> productList, ParseException e) {
	    	    	TextView t = (TextView)findViewById(R.id.textView1);
	    	        if (e == null) {
	    	            Log.d("product", "Retrieved " + productList.size() + " products");
	    	            
	    	            try{
		    	            ParseObject p = productList.get(0);
		    	    		t.setText(p.getString("companyName") + '\n' + p.getString("brandName")+ '\n' + p.getString("lastCategory")+ '\n' + p.getString("provinceCnName"));	
		    	    		
		    	    		final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		    	    		String url = p.getString("newBrandLogoUrl");
		    	    		if (url != null){
			    				imageLoader.loadDrawable(url, new com.parse.starter.AsyncImageLoader.ImageCallback() {
		
			    					public void imageLoaded(Drawable imageDrawable, String imageUrl) {
			    						imageView.setImageDrawable(imageDrawable);
			    					}
			    				});
		    	    		}
	    	            }
		    	    	catch (Exception e1) {}
	    	    		
	    	    		
	    	        } else {
	    	            Log.d("product", "Error: " + e.getMessage());
	    	            t.setText("无此Brand");
	    	        }
	    	    }
	    	});
	    } catch (Exception e1) {
		
	    }
	    */
    	//京东数据
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("jd_data");
    	EditText e = (EditText)findViewById(R.id.editText1);    	
    	String input_brand = e.getText().toString();
    	try{
	    	query.whereContains("title", input_brand);
	    	query.findInBackground(new FindCallback<ParseObject>() {
	    	    public void done(List<ParseObject> productList, ParseException e) {
	    	    	TextView t = (TextView)findViewById(R.id.textView1);
	    	    	final ImageView imageView = (ImageView) findViewById(R.id.imageView1);

	    	    	if (productList.isEmpty() || productList==null)
	    	    		t.setText("无此Brand");
	    	    		imageView.setImageResource(R.drawable.ic_launcher);
	    	    	
	    	        if (e == null) {
	    	            Log.d("product", "Retrieved " + productList.size() + " products");
	    	            
	    	            try{
		    	            ParseObject p = productList.get(0);
		    	    		t.setText(p.getString("info").replace(';', '\n'));	
		    	    		
		    	    		//final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		    	    		String url = p.getString("img_url");
		    	    		if (url != null){
			    				imageLoader.loadDrawable(url, new com.parse.starter.AsyncImageLoader.ImageCallback() {
		
			    					public void imageLoaded(Drawable imageDrawable, String imageUrl) {
			    						imageView.setImageDrawable(imageDrawable);
			    					}
			    				});
		    	    		}
	    	            }
		    	    	catch (Exception e1) {}
	    	    		
	    	    		
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
