package com.parse.starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
    	
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("yhd_data");
    	EditText e = (EditText)findViewById(R.id.editText1);    	
    	String input_brand = e.getText().toString();
    	try{
    		Log.d("product", input_brand);
	    	query.whereContains("brandName", input_brand);
	    	query.setLimit(3);
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
    	ListView list = (ListView)findViewById(R.id.listView1);
    	//生成动态数组，加入数据  
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<10;i++)  
        {  
            HashMap<String, Object> map = new HashMap<String, Object>();  
            map.put("ItemImage", R.drawable.ic_hahaha);//图像资源的ID  
            map.put("ItemTitle", "Level "+i);  
            map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");  
            listItem.add(map);  
        }  
        //生成适配器的Item和动态数组对应的元素  
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源   
            R.layout.list_items,//ListItem的XML实现  
            //动态数组与ImageItem对应的子项          
            new String[] {"ItemImage","ItemTitle", "ItemText"},   
            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}  
        );  
         
        //添加并且显示  
        list.setAdapter(listItemAdapter);  
          
        //添加点击  
        list.setOnItemClickListener(new OnItemClickListener() {  
  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3) {  
                setTitle("点击第"+arg2+"个项目");  
            }  
        });  
        
        //添加长按点击
        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            
            @Override  
            public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
                menu.setHeaderTitle("长按菜单-ContextMenu");     
                menu.add(0, 0, 0, "弹出长按菜单0");  
                menu.add(0, 1, 0, "弹出长按菜单1");     
            }  
        });        
        

    }
    //长按菜单响应函数  
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
        setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");   
        return super.onContextItemSelected(item);  
    }  
}
