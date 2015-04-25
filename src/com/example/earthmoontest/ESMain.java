package com.example.earthmoontest;

import com.example.utils.MatrixUtils;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

public class ESMain {
	float pre_x,pre_y;
	
	public ESMain() {
		
	}

	public void start(){
		GLES20.glClearColor(0, 0, 0, 1);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_CULL_FACE);
	}
	
	public void viewChanged(int width, int height){
		float ratio = (float) width / height;
		MatrixUtils.setFrustum(-ratio, ratio, -1, 1, 20, 100);
		MatrixUtils.setCamera(0, 0, 30, 0, 0, 0, 0, 1, 0);
		float[] sss = new float[16];
		Matrix.setRotateM(sss, 0, 0, 0, 1, 0);
		for (int i = 0; i < sss.length; i++) {
			Log.i(String.valueOf(i), String.valueOf(sss[i]));
		}
	}
	
	public void draw(){
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

	}
	
	public void update(long dt){

	}
	
	public boolean onTouch(MotionEvent event){
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pre_x = event.getX();
			pre_y = event.getY();
			return true;
		case MotionEvent.ACTION_MOVE:
			float xx = event.getX();
			float yy = event.getY();
			pre_x = xx;
			pre_y = yy;
			return true;
		default:
			break;
		}
		return false;
	}
}
