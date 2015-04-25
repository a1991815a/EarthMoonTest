package com.example.sprite;

import java.nio.FloatBuffer;

import com.example.earthmoontest.ESContext;
import com.example.utils.MatrixUtils;

public abstract class SpriteObj {
	//Ĭ�ϵ� ȫ����ɫ��������
	public static ESContext static_es_context = new ESContext();
	
	//��������
	public float[] scale = new float[]{1,1,1};
	public float[] position = new float[]{0,0,0};
	public float[] angle = new float[]{0,0,0};
	protected float[] M_Matrix; 
	
	//��������
	protected FloatBuffer vBuf;
	
	//���ڶ������ɫ��������
	private ESContext es_context;
	
	public SpriteObj() {
		es_context = static_es_context;
		
		
	}
	
	private void initM_Matrix(){
		M_Matrix = MatrixUtils.getUnitMatrix();
		
		
	}
	
	
	

}
