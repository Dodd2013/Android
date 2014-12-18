package com.example.layoutapp;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	final int[] colors=new int[]{
			R.color.color1,
			R.color.color2,
			R.color.color3,
			R.color.color4,
			R.color.color5,
			R.color.color6
	};
	final int[] names=new int[]{
			R.id.view01,
			R.id.view02,
			R.id.view03,
			R.id.view04,
			R.id.view05,
			R.id.view06
	};
	int c=0;
	TextView[] views=new TextView[names.length];
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what==0x123){
				for(int i=0;i<names.length;i++){
					views[i].setBackgroundResource(colors[(i+c)%names.length]);
				}
				c++;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		for(int i=0;i<names.length;i++){
			views[i]=(TextView)findViewById(names[i]);
		}
		views[5].setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				new Timer().schedule(new TimerTask(){

					@Override
					public void run() {
						handler.sendEmptyMessage(0x123);
						
					}
					
				},0,200);
				return false;
			}
			
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}
	}

}
