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
	
	/**
	 * ð������
	 * @param data
	 */
	public static int [] BubbleSort(int [] data){
		int temp=0;
		for (int i = 0; i < data.length; i++) {
			//��������Ѿ����� ���ñȽ���
			for (int j = 0; j < data.length-i-1; j++) {
				//���ǰ��һ�������ں������ �򽻻�
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
	 * ��������
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
	 * ���ŷָ��
	 * @param data
	 * @param left
	 * @param right
	 * @return
	 */
	public static int QuickSort_Division(int [] data,int left,int right){
		int baseNum=data[left];
		while(left<right){
			//���ұ߿�ʼ�ҵ���һ����baseNumС����
			while(left<right&&data[right]>=baseNum){
				right--;
			}
			//��������ŵ����ȥ
			data[left]=data[right];
			//����ߵ�һ����baseNum������ŵ��ұ�
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
