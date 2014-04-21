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
	 * 冒泡排序
	 * @param data
	 */
	public static int [] BubbleSort(int [] data){
		int temp=0;
		for (int i = 0; i < data.length; i++) {
			//后面的数已经有序 不用比较了
			for (int j = 0; j < data.length-i-1; j++) {
				//如果前面一个数大于后面的数 则交换
				if(data[j]>data[j+1]){
					temp=data[j];
					data[j]=data[j+1];
					data[j+1]=temp;
				}
			}
		}
		return data;
	} 
	/**
	 * 快速排序
	 * @param data
	 * @param left
	 * @param right
	 */
	public static void QuickSort(int [] data,int left,int right){
		if(left<right){
			int index=QuickSort_Division(data, left, right);
			QuickSort(data, left, index-1);
			QuickSort(data, index+1, right);
		}
	}
	/**
	 * 快排分割函数
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	public static int QuickSort_Division(int [] data,int left,int right){
		int baseNum=data[left];
		while(left<right){
			//从右边开始找到第一个比baseNum小的数
			while(left<right&&data[right]>=baseNum){
				right--;
			}
			//把这个数放到左边去
			data[left]=data[right];
			//把左边第一个比baseNum大的数放到右边
			while(left<right&&data[left]<=baseNum){
				left++;
			}
			data[right]=data[left];
		}
		data[left]=baseNum;
		return left;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data=InitRandomArray(20);
		PrintArray(data);
		QuickSort(data, 0, data.length-1);
		PrintArray(data);
	}

}
