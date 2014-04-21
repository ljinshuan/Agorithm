/**
 * 
 */
package com.shuan.sort;

import java.util.Date;
import java.util.Random;

/**
 * @author Shuan
 *
 */
public class App {

	/**
	 * 生成一个随机数组
	 * @param len
	 * @return
	 */
	public static int [] InitRandomArray(int len){
		int [] data=new int[len];
		Random random=new Random(new Date().getTime());
		for(int i=0;i<len;i++){
			data[i]=random.nextInt(10000);
		}
		return data;
	}
	/**
	 * 打印数组
	 * @param data
	 */
	public static void PrintArray(int [] data){
		for (int i = 0; i < data.length; i++) {
			System.out.printf("%d ", data[i]);
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data=InitRandomArray(20);
		PrintArray(data);
	}

}
