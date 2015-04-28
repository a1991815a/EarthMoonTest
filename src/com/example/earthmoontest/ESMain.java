package com.example.earthmoontest;

import com.example.light.LightObj;
import com.example.sprite.Circle3D;
import com.example.sprite.Circle3DTexture;
import com.example.sprite.SpriteObj;
import com.example.utils.MatrixUtils;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

public class ESMain {
	float pre_x,pre_y;
	
	Circle3DTexture cle;
	LightObj light;
	public ESMain() {
		
	}

	public void start(){
		SpriteObj.createStaticProgram("vertex.shader", "fragment.shader");
		cle = new Circle3DTexture(0.5f);
		
		light = new LightObj(cle,new float[]{-1, 0, 1},new float[]{0, 0, 30});
		
		GLES20.glClearColor(0, 0, 0, 1);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
//		GLES20.glEnable(GLES20.GL_CULL_FACE);
	}
	
	public void viewChanged(int width, int height){
		GLES20.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		MatrixUtils.setCamera(0, 0, 30, 0, 0, 0, 0, 1, 0);
		MatrixUtils.setFrustum(-ratio, ratio, -1, 1, 20, 100);
	}
	
	public void draw(){
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
		cle.visit();
	}
	
	public void update(long dt){
//		cle.angle[0] += 5;
		cle.angle[1] += 5;
	}
	
	public boolean onTouch(MotionEvent event){
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pre_x = event.getX();
			pre_y = event.getY();
			return true;
		case MotionEvent.ACTION_MOVE:
			float dlt_xx = event.getX() - pre_x;
			float dlt_yy = event.getY() - pre_y;
			pre_x = event.getX();
			pre_y = event.getY();
//			cle.angle[1] += dlt_xx;
//			cle.angle[0] += dlt_yy;
			light.lightPosition[0] += (float) (dlt_xx / 10);
			light.lightPosition[1] -= (float) (dlt_yy / 10);
			return true;
		default:
			break;
		}
		return false;
	}
}
