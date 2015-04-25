package com.example.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class BufferUtils {

	private static BufferUtils _instance = null;
	public static BufferUtils getInstance(){
		if (_instance == null) {
			_instance = new BufferUtils();
		}
		return _instance;
	}
	
	public FloatBuffer getBuf(float[] in_data){
		FloatBuffer fb = null;
		ByteBuffer bb = ByteBuffer.allocateDirect(in_data.length * 4);
		bb.order(ByteOrder.nativeOrder());
		fb = bb.asFloatBuffer();
		fb.put(in_data);
		fb.position(0);
		return fb;
	}
	
	public ByteBuffer getBuf(byte[] in_data){
		ByteBuffer bb = ByteBuffer.allocateDirect(in_data.length);
		bb.order(ByteOrder.nativeOrder());
		bb.put(in_data);
		bb.position(0);
		return bb;
	}
	
	public ShortBuffer getBuf(short[] in_data){
		ShortBuffer sb = null;
		ByteBuffer bb = ByteBuffer.allocateDirect(in_data.length * 2);
		bb.order(ByteOrder.nativeOrder());
		sb = bb.asShortBuffer();
		sb.put(in_data);
		sb.position(0);
		return sb;
	}
	
	public IntBuffer getBuf(int[] in_data){
		IntBuffer ib = null;
		ByteBuffer bb = ByteBuffer.allocateDirect(in_data.length * 4);
		bb.order(ByteOrder.nativeOrder());
		ib = bb.asIntBuffer();
		ib.put(in_data);
		ib.position(0);
		return ib;
	}

}
