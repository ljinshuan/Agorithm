package com.shuan.util;

import java.util.Date;
import java.util.Random;

public class CommonUtil {
	/**
	 * ����һ���������
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
	 * ��ӡ����
	 * @param data
	 */
	public static void PrintArray(int [] data){
		for (int i = 0; i < data.length; i++) {
			System.out.printf("%d ", data[i]);
		}
		System.out.println();
	}
}
