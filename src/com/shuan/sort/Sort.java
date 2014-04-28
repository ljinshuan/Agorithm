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
	 * ѡ������
	 * @param data
	 */
	public static void SelectionSort(int [] data){
		int tempIndex=0;
		int tempData=0;
		for (int i = 0; i < data.length-1; i++) {
			//ǰ���Ѿ�����
			tempIndex=i;
			for (int j = i+1; j < data.length; j++) {
				//�ҵ���С����������
				if(data[tempIndex]>data[j]){
					tempIndex=j;
				}
			}
			
			//����
			tempData=data[i];
			data[i]=data[tempIndex];
			data[tempIndex]=tempData;
		}
	}
	/**
	 * ������
	 * @param data
	 */
	public static void HeapSort(int [] data){
		//������
		for (int i = data.length/2-1; i >=0; i--) {
			HeapSort_HeapAdjust(data, i, data.length);
		}
		
		//ÿ�ζѶ������һ��Ԫ�ؽ��� 
		for(int i=data.length-1;i>=0;i--){
			int temp=data[0]; //�Ѷ�Ԫ��
			data[0]=data[i];
			data[i]=temp;
			
			//����
			HeapSort_HeapAdjust(data, 0, i);
		}
	}
	/**
	 * ������
	 * @param data
	 * @param parent ���ڵ���
	 * @param lenght �������Ч����
	 */
	public static void HeapSort_HeapAdjust(int [] data,int parent,int lenght){
		int tempData=data[parent];
		int child=2*parent+1;  //��ߺ��ӱ��
		while(child<lenght){
			//�Ƚ����Һ��ӵĴ�С
			if(child+1<lenght&&data[child]<data[child+1]){
				child++; //�Һ��Ӹ���
			}
			if(tempData>=data[child]){
				break; //���ڵ���� ���õ���
			}
			data[parent]=data[child];
			parent=child;
			child=2*parent+1;
		}
		data[parent]=tempData;
	}
	/**
	 * ��������
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
	 * ϣ������
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
	 * �鲢����
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
	 * �ϲ���������
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
		//�ж�����Ƿ����
		while(left<=leftEnd){
			tempData[tempIndex++]=data[left++];
		}
		//�ұ�
		while(rightStart<=right){
			tempData[tempIndex++]=data[rightStart++];
		}
		
		//��������
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
