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
	 * 选择排序
	 * @param data
	 */
	public static void SelectionSort(int [] data){
		int tempIndex=0;
		int tempData=0;
		for (int i = 0; i < data.length-1; i++) {
			//前面已经有序
			tempIndex=i;
			for (int j = i+1; j < data.length; j++) {
				//找到最小的数的索引
				if(data[tempIndex]>data[j]){
					tempIndex=j;
				}
			}
			
			//交换
			tempData=data[i];
			data[i]=data[tempIndex];
			data[tempIndex]=tempData;
		}
	}
	/**
	 * 堆排序
	 * @param data
	 */
	public static void HeapSort(int [] data){
		//构建堆
		for (int i = data.length/2-1; i >=0; i--) {
			HeapSort_HeapAdjust(data, i, data.length);
		}
		
		//每次堆顶和最后一个元素交换 
		for(int i=data.length-1;i>=0;i--){
			int temp=data[0]; //堆顶元素
			data[0]=data[i];
			data[i]=temp;
			
			//调整
			HeapSort_HeapAdjust(data, 0, i);
		}
	}
	/**
	 * 调整堆
	 * @param data
	 * @param parent 父节点编号
	 * @param lenght 数组的有效长度
	 */
	public static void HeapSort_HeapAdjust(int [] data,int parent,int lenght){
		int tempData=data[parent];
		int child=2*parent+1;  //左边孩子编号
		while(child<lenght){
			//比较左右孩子的大小
			if(child+1<lenght&&data[child]<data[child+1]){
				child++; //右孩子更大
			}
			if(tempData>=data[child]){
				break; //父节点更大 不用调整
			}
			data[parent]=data[child];
			parent=child;
			child=2*parent+1;
		}
		data[parent]=tempData;
	}
	/**
	 * 插入排序
	 * @param data
	 */
	public static void InsertSort(int [] data){
		int temp=0;
		int j=0;
		for(int i=1;i<data.length;i++){
			temp=data[i];
			
			for(j=i-1;j>=0&&temp<data[j];j--){
				data[j+1]=data[j];
			}
			data[j+1]=temp;
		}
	}
	/**
	 * 希尔排序
	 * @param data
	 */
	public static void ShellSort(int [] data){
		int step=data.length/2;
		int temp,j;
		while(step>=1){
			for (int i = step; i < data.length; i++) {
				temp=data[i];
				for (j=i-step; j >=0&&temp<data[j]; j=j-step) {
					data[j+step]=data[j];
				}
				data[j+step]=temp;
			}
			
			step=step/2;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data=InitRandomArray(20);
		PrintArray(data);
		ShellSort(data);
		PrintArray(data);
	}

}
