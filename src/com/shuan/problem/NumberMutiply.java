/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *大于1的正整数 n 都可以分解为 n = x1 * x2 * ... * xm

例如：当n=12时，共有8种不同的分解式：
12 = 12
12 = 6*2
12 = 4*3
12 = 3*4
12 = 3*2*2
12 = 2*6
12 = 2*3*2
12 = 2*2*3

对于给定正整数n，计算n共有多少种不同的分解式。
 */
public class NumberMutiply {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int resul=getResult(12);
		System.out.println(resul);

	}

	public static int getResult(int num) {
		if (num==1) {
			return 1;
		}
		int result=0;
		for(int i=2;i<=num;i++){
			if(num%i==0){
				result+=getResult(num/i);
			}
		}
		return result;
	}

}
