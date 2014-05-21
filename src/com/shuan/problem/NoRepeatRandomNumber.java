package com.shuan.problem;

import java.util.Arrays;
import java.util.Random;

import com.shuan.util.CommonUtil;

/**
 * 不重复m个0——n-1随机数
 * @author Shuan
 *
 */
public class NoRepeatRandomNumber {
	/**
	 * 获取m个随机数
	 * @param n  范围
	 * @param m  个数
	 * @return 随机数数组
	 */
	public static int [] getRandomNumbers(int n,int m){
		Random random=new Random();
		int [] all=new int[n];
		//生成数据
		for (int i = 0; i < all.length; i++) {
			all[i]=i;
		}
		//打乱前m个数
		for (int i = 0; i < m; i++) {
			int index=random.nextInt(n-i)+i;  //关键点 
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
