package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.earthmoontest.ESContext;
import com.example.earthmoontest.MainActivity;

import android.opengl.GLES20;
import android.util.Log;

public class OpenglUtils {

	private static OpenglUtils _instance = null;
	public static OpenglUtils getInstance() {
		if (_instance == null) {
			_instance = new OpenglUtils();
		}
		return _instance;
	}
	
	public void initShader(int shaderType, String source, ESContext es_context){
		int shaderId = GLES20.glCreateShader(shaderType);
		if (shaderId == 0) {
			Log.e("glError", "glCreateShader");
			return;
		}
		GLES20.glShaderSource(shaderId, source);
		GLES20.glCompileShader(shaderId);
		int[] compileStatus = new int[1];
		GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
		if (compileStatus[0] == 0) {
			Log.e("glError", "glCompileShader");
			Log.e("glError", GLES20.glGetShaderInfoLog(shaderId));
			GLES20.glDeleteShader(shaderId);
			return;
		}
		
		if (shaderType == GLES20.GL_VERTEX_SHADER) {
			es_context.vShader = shaderId;
		}else if (shaderType == GLES20.GL_FRAGMENT_SHADER) {
			es_context.fShader = shaderId;
		}
	}
	
	private void loadShaderFromAsset(int shaderType, String path, ESContext es_context){
		InputStream in_is = null;
		InputStreamReader in_isr = null;
		ByteArrayOutputStream bos = null;
		String source = "";
		try {
			in_is = MainActivity.context.getAssets().open(path);
			in_isr = new InputStreamReader(in_is);
			bos = new ByteArrayOutputStream();
			int ch = -1;
			while ((ch = in_isr.read()) != -1) {
				bos.write(ch);
			}
			source = bos.toString();
			source.replace("\\r\\n", "\n");
			initShader(shaderType, source, es_context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if (bos != null)
					bos.close();
				if (in_isr != null)
					in_isr.close();
				if (in_is != null)
					in_is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void createProgram(String vPath, String fPath, ESContext es_context){
		loadShaderFromAsset(GLES20.GL_VERTEX_SHADER, vPath, es_context);
		loadShaderFromAsset(GLES20.GL_FRAGMENT_SHADER, fPath, es_context);
		if (es_context.vShader == 0 || es_context.fShader == 0) {
			Log.e("glError", "es_context.vShader == 0 || es_context.fShader == 0");
			return;
		}
		int programId = GLES20.glCreateProgram();
		if(programId == 0) Log.e("glError", "glCreateProgram");
		GLES20.glAttachShader(programId, es_context.vShader);
		GLES20.glAttachShader(programId, es_context.fShader);
		GLES20.glLinkProgram(programId);
		int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
		if (linkStatus[0] == 0) {
			Log.e("glError", "glLinkProgram");
			Log.e("glError", GLES20.glGetProgramInfoLog(programId));
			GLES20.glDeleteProgram(programId);
			return;
		}
		
		Log.e("program", String.valueOf(programId));
		
		es_context.program = programId;
	}
	
}
