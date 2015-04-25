package com.example.earthmoontest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public static Context context;
	
	private OpenglSurfaceView gl_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		setContentView(R.layout.activity_main);
		
		context = this;
		gl_view = new OpenglSurfaceView(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearDisplay);
		ll.addView(gl_view);
		gl_view.requestFocus();
		gl_view.setFocusableInTouchMode(true);
	}
	
}
