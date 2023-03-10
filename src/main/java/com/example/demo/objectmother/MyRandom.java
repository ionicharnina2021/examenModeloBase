package com.example.demo.objectmother;

import java.util.Random;

public class MyRandom {
	public int nextInt(int min, int max) {
		if(min>=max) return -1;
		return new Random().nextInt(max-min)+min;
	}

	public float nextFloat(float min, float max) {
		if(min>=max) return -1f;
		return (new Random().nextFloat()*(max-min))+min;
	}

}
