package com.parse.starter;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "rHbqdGAlsbn31Z7KdIHhQ2PruGDZksLyiLZcmzfj", "hRfCYWfV92qKf2LJFyxCyprPKUc2DGK0PITuhwmm");


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		
		
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
	}

}
