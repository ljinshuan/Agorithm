/**
 * 
 */
package com.shuan.sort;

import java.util.Date;
import java.util.Random;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 */
public class Sort {

	
	
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
	 * 归并排序
	 * @param data
	 * @param tempData
	 * @param left
	 * @param right
	 */
	public static void MergeSort(int [] data,int [] tempData,int left,int right){
		if(left<right){
			int middle=(left+right)/2;
			MergeSort(data, tempData, left, middle);
			MergeSort(data, tempData, middle+1, right);
			MergeSort_Merge(data, tempData, left, middle+1, right);
		}
	}
	/**
	 * 合并两个数组
	 * @param data
	 * @param tempData
	 * @param middle
	 * @param left
	 * @param right
	 */
	public static void MergeSort_Merge(int [] data,int [] tempData,int left,int middle,int right){
		int leftEnd=middle-1;
		int rightStart=middle;
		
		int tempIndex=left;
		int tempLenght=right-left+1;
		
		while((left<=leftEnd)&&(rightStart<=right)){
			if(data[left]<data[rightStart]){
				tempData[tempIndex++]=data[left++];
			}else{
				tempData[tempIndex++]=data[rightStart++];
			}
		}
		//判断左边是否结束
		while(left<=leftEnd){
			tempData[tempIndex++]=data[left++];
		}
		//右边
		while(rightStart<=right){
			tempData[tempIndex++]=data[rightStart++];
		}
		
		//交换数据
		for (int i = 0; i < tempLenght; i++) {
			data[right]=tempData[right];
			right--;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data=CommonUtil.InitRandomArray(20);
		
		CommonUtil.PrintArray(data);
		int [] tempData=new int[data.length];
		MergeSort(data, tempData, 0, data.length-1);
	
		CommonUtil.PrintArray(data);
	}

}
