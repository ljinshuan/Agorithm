/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *����1�������� n �����Էֽ�Ϊ n = x1 * x2 * ... * xm

���磺��n=12ʱ������8�ֲ�ͬ�ķֽ�ʽ��
12 = 12
12 = 6*2
12 = 4*3
12 = 3*4
12 = 3*2*2
12 = 2*6
12 = 2*3*2
12 = 2*2*3

���ڸ���������n������n���ж����ֲ�ͬ�ķֽ�ʽ��
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
