package com.example.sprite;

import java.nio.ShortBuffer;

import android.opengl.GLES20;
import android.util.Log;

import com.example.caller.SpriteCallerInterface;
import com.example.utils.BufferUtils;
import com.example.utils.MatrixUtils;

public class Circle3D extends SpriteObj {
	
	public float r;
	public float angleSpan = 10.0f;
	
	private ShortBuffer indsBuf;
	
	public Circle3D(float R) {
		super();
		r = R;
		initData();
	}

	@Override
	protected void initData() {
		int la_count,lo_count;
		la_count = (int) (180.0f/angleSpan + 1);
		lo_count = (int) (360.0f/angleSpan + 1);
		float[] vertixs = new float[la_count * lo_count * 3];

		int vertexs_index = 0;
		for (float la = -90; la <= 90; la+=angleSpan) {
			double la_radian = Math.toRadians(la);
			double la_cos = Math.cos(la_radian);
			double la_sin = Math.sin(la_radian);
			for (float lo = 0; lo <= 360; lo+=angleSpan) {
				double lo_radian = Math.toRadians(lo);
				double lo_cos = Math.cos(lo_radian);
				double lo_sin = Math.sin(lo_radian);
				
				vertixs[vertexs_index++] = (float) (r * la_cos * lo_cos);
				vertixs[vertexs_index++] = (float) (r * la_sin);
				vertixs[vertexs_index++] = (float) (r * la_cos * lo_sin);
				
				if(vertixs[vertexs_index - 3] < 0.000000001f && vertixs[vertexs_index - 3] > -0.000000001f) vertixs[vertexs_index - 3] = 0;
				if(vertixs[vertexs_index - 2] < 0.000000001f && vertixs[vertexs_index - 2] > -0.000000001f) vertixs[vertexs_index - 2] = 0;
				if(vertixs[vertexs_index - 1] < 0.000000001f && vertixs[vertexs_index - 1] > -0.000000001f) vertixs[vertexs_index - 1] = 0;
				
//				if(vertixs[vertexs_index - 3] >= r || vertixs[vertexs_index - 2] >= r || vertixs[vertexs_index - 1] >= r)
//				Log.i(String.valueOf(vertexs_index - 3), 
//						String.valueOf(vertixs[vertexs_index - 3]) + " , " +
//						String.valueOf(vertixs[vertexs_index - 2]) + " , " +
//						String.valueOf(vertixs[vertexs_index - 1])
//						);
			}
		}
		
		Log.e("count", String.valueOf(la_count));
		Log.e("count", String.valueOf(lo_count));
		
		short[] inds = new short[la_count * lo_count * 6];
		int inds_index = 0;
		for (int lo_index = 0; lo_index < lo_count - 1; lo_index++) {
			short loo = (short) lo_index;
			short loo_next = (short) (lo_index + 1);
			
			for (int la_index = 0; la_index < la_count - 1; la_index++) {
				short laa = (short) (la_index * lo_count);
				short laa_next = (short) ((la_index+1) * lo_count);
				
				inds[inds_index++] = (short) (laa + loo);
				inds[inds_index++] = (short) (laa + loo_next);
				inds[inds_index++] = (short) (laa_next + loo_next);
				
				inds[inds_index++] = (short) (laa_next + loo_next);
				inds[inds_index++] = (short) (laa_next + loo);
				inds[inds_index++] = (short) (laa + loo);
				vCount += 6;
			}
		}
		vBuf = BufferUtils.getInstance().getBuf(vertixs);
		indsBuf = BufferUtils.getInstance().getBuf(inds);
		if (caller != null) {
			caller.initData();
		}
	}

	@Override
	public void visit() {
		GLES20.glUseProgram(es_context.program);
		initM_Matrix();
		GLES20.glUniformMatrix4fv(getHandler("uMVPMatrix"), 1, false, MatrixUtils.getFinalMatrix(M_Matrix), 0);
		GLES20.glVertexAttribPointer(getHandler("aPosition"), 3, GLES20.GL_FLOAT, false, 3*4, vBuf);
		if (caller != null) {
			caller.visit();
		}
		openDataHandler();
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, vCount, GLES20.GL_UNSIGNED_SHORT, indsBuf);
	}

	@Override
	protected void initDataHandler() {
		if (caller != null) {
			caller.initDataHandler();
		}
		pushHandler(dataType.uniform, "uMVPMatrix");
		pushHandler(dataType.attribute, "aPosition");
	}

	@Override
	protected void openDataHandler() {
		GLES20.glEnableVertexAttribArray(getHandler("aPosition"));
		if (caller != null) {
			caller.openDataHandler();
		}
	}

}
