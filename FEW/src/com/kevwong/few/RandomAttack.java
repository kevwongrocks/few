package com.kevwong.few;

import java.util.Random;

public class RandomAttack {
	
	public static int main(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
