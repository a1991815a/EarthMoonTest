package com.example.earthmoontest;

import java.util.Calendar;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenglSurfaceView extends GLSurfaceView {

	private long delayTime = 16;
	private SceneRendener rendener;
	private updateThread update;
	private ESMain es_main;
	
	public OpenglSurfaceView(Context context) {
		super(context);
		es_main = new ESMain();
		setEGLContextClientVersion(2);
		rendener = new SceneRendener();
		setRenderer(rendener);
		setRenderMode(RENDERMODE_CONTINUOUSLY);
		update = new updateThread();
	}
	
	private class SceneRendener implements GLSurfaceView.Renderer{

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			es_main.start();
			update.start();
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			GLES20.glViewport(0, 0, width, height);
			es_main.viewChanged(width, height);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			es_main.draw();
		}
		
	}
	
	private class updateThread extends Thread{

		boolean flag = false;
		long pre_time = 0;
		
		@Override
		public void run() {
			while (flag) {
				long dt = Calendar.getInstance().getTimeInMillis() - pre_time;
				es_main.update(dt);
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public synchronized void start() {
			flag = true;
			pre_time = Calendar.getInstance().getTimeInMillis();
			super.start();
		}
		
		public synchronized void end(){
			flag = false;
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return es_main.onTouch(event);
	}

}
