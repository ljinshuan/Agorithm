package com.shuan.problem;

import java.util.Arrays;
import java.util.Random;

import com.shuan.util.CommonUtil;

/**
 * ���ظ�m��0����n-1�����
 * @author Shuan
 *
 */
public class NoRepeatRandomNumber {
	/**
	 * ��ȡm�������
	 * @param n  ��Χ
	 * @param m  ����
	 * @return ���������
	 */
	public static int [] getRandomNumbers(int n,int m){
		Random random=new Random();
		int [] all=new int[n];
		//��������
		for (int i = 0; i < all.length; i++) {
			all[i]=i;
		}
		//����ǰm����
		for (int i = 0; i < m; i++) {
			int index=random.nextInt(n-i)+i;  //�ؼ��� 
			int temp=all[index];
			all[index]=all[i];
			all[i]=temp;
		}
		
		return Arrays.copyOfRange(all, 0, m);
	
		
	}
	public static void main(String[] args) {
		int [] result =getRandomNumbers(100, 10);
		System.out.println(result.length);
		
		CommonUtil.PrintArray(result);
		
	}
}
