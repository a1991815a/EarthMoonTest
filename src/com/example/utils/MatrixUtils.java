package com.example.utils;

import android.opengl.Matrix;
import android.util.Log;

public class MatrixUtils {
	private static float[] V_Matrix = new float[16];
	private static float[] P_Matrix = new float[16];
	private static float[] MVP_Matrix = new float[16];
	private static float[][] Matrix_list = new float[10][16];
	private static int index = -1;
	
	public static float[] getUnitMatrix(){
		return new float[]{
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0, 
				0, 0, 0, 1
		};
	}
	
	public static float[] getFinalMatrix(float[] M_Matrix) {
		Matrix.multiplyMM(MVP_Matrix, 0, V_Matrix, 0, M_Matrix, 0);
		Matrix.multiplyMM(MVP_Matrix, 0, P_Matrix, 0, MVP_Matrix, 0);
		return MVP_Matrix;
	}
	
	public static float[] getMVPMatrix(){
		return MVP_Matrix;
	}
	
	public static void push(){
		if(index == 9) 
		{
			Log.e("overflow", "push()");
			return;
		}
		index++;
		for (int i = 0; i < 16; i++) {
			Matrix_list[index][i] = MVP_Matrix[i];
		}
	}

	public static void pop(){
		if(index == -1) return;
		for (int i = 0; i < 16; i++) {
			MVP_Matrix[i] = Matrix_list[index][i];
		}
		index--;
	}
	
	public static void setCamera(
			float cx, float cy, float cz,
			float ex, float ey, float ez,
			float ux, float uy, float uz
			){
		Matrix.setLookAtM(V_Matrix, 0, cx, cy, cz, ex, ey, ez, ux, uy, uz);
	}
	
	public static void setOrtho(
			float left, float right,
			float bottom, float top,
			float near, float far
			){
		Matrix.orthoM(P_Matrix, 0, left, right, bottom, top, near, far);
	}
	
	public static void setFrustum(
			float left, float right,
			float bottom, float top,
			float near, float far
			){
		Matrix.frustumM(P_Matrix, 0, left, right, bottom, top, near, far);
	}
	
}
