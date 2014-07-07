package com.kevwong.few;

public class CompareAttacks {

	public static int[] main(int[] array1, int[] array2) {
		
		int p1 = 0;
		int p2 = 0;
		int tie = 0;
		int[] attackCheck = new int[9];
		
		for(int i = 0; i <= attackCheck.length-1; i++) {
			
			if(array1[i] == array2[i]){
				tie++;
				attackCheck[i] = 0;
			} else if ((array1[i] == 0) && (array2[i] == 1)) {
				p1++;
				attackCheck[i] = 1;
			} else if ((array1[i] == 1) && (array2[i] == 2)) {
				p1++;
				attackCheck[i] = 1;
			} else if  ((array1[i] == 2) && (array2[i] == 0)) {
				p1++;
				attackCheck[i] = 1;
			} else if ((array1[i] == 0) && (array2[i] == 2)) {
				p2++;
				attackCheck[i] = 2;
			} else if ((array1[i] == 1) && (array2[i] == 0)) {
				p2++;
				attackCheck[i] = 2;
			} else if  ((array1[i] == 2) && (array2[i] == 1)) {
				p2++;
				attackCheck[i] = 2;
			}
		}
		
		return attackCheck;

	}
}

