package com.example.earthmoontest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public static Context context;
	
	private OpenglSurfaceView gl_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		
		context = this;
		gl_view = new OpenglSurfaceView(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linearDisplay);
		ll.addView(gl_view);
		gl_view.requestFocus();
		gl_view.setFocusableInTouchMode(true);
	}
	
}
