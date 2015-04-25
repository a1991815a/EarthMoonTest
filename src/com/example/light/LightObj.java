package com.example.light;

import java.nio.FloatBuffer;

import android.opengl.GLES20;

import com.example.caller.SpriteCaller;
import com.example.sprite.SpriteObj;
import com.example.sprite.SpriteObj.dataType;

public class LightObj {
	public float[] lightPosition = new float[3];
	public float[] eyePosition = new float[3];
	FloatBuffer normalBuf;
	SpriteObj sprite;
	
	public LightObj(SpriteObj obj,float[] lightPosition, float[] eyePosition) {
		sprite = obj;
		setLightPosition(lightPosition);
		setEyePosition(eyePosition);
		
		
		initCaller();
		
		sprite.caller.initData();
		
		initShader();
	}
	
	private void initShader(){
		sprite.createProgram("light.vshader", "light.fshader");
	}
	
	private void initCaller(){
		sprite.caller = new SpriteCaller(){
			@Override
			public void initData() {
				normalBuf = sprite.getvBuf();
				normalBuf.position(0);
			}

			@Override
			public void visit() {
				GLES20.glVertexAttribPointer(sprite.getHandler("aNormal"), 3, GLES20.GL_FLOAT, false, 3*4, normalBuf);
				GLES20.glUniformMatrix4fv(sprite.getHandler("uMMatrix"), 1, false, sprite.getM_Matrix(), 0);
				GLES20.glUniform3f(sprite.getHandler("aLightPosition"), lightPosition[0], lightPosition[1], lightPosition[2]);
				GLES20.glUniform3f(sprite.getHandler("aEyePosition"), eyePosition[0], eyePosition[1], eyePosition[2]);
			}

			@Override
			public void initDataHandler() {
				sprite.pushHandler(dataType.attribute, "aNormal");
				sprite.pushHandler(dataType.uniform, "aLightPosition");
				sprite.pushHandler(dataType.uniform, "aEyePosition");
				sprite.pushHandler(dataType.uniform, "uMMatrix");
			}

			@Override
			public void openDataHandler() {
				GLES20.glEnableVertexAttribArray(sprite.getHandler("aNormal"));
			}
		};
	}
	
	public final void setLightPosition(float[] lightPosition) {
		for (int i = 0; i < 3; i++) {
			this.lightPosition[i] = lightPosition[i];
		}
	}

	public final void setEyePosition(float[] eyePosition) {
		for (int i = 0; i < 3; i++) {
			this.eyePosition[i] = eyePosition[i];
		}
	}
	
}
