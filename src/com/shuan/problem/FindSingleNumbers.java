/**
 * 
 */
package com.shuan.problem;

import java.util.Arrays;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 */
public class FindSingleNumbers {

	public static void find(int [] data){
		//���Ƚ������
		int result=0;
		for (int i = 0; i < data.length; i++) {
			result^=data[i];
		}
		//�ҳ�����ж����Ƶ�һ��1��λ��
		String binaryString=Integer.toBinaryString(result);
		//�ҵ���һ��1
		int n=binaryString.lastIndexOf('1');
		n=binaryString.length()-n;
		
		//�����ݰ��չ����Ϊ����
		int [] data1=new int[data.length];
		int [] data2=new int[data.length];
		int j=0,k=0;
		for (int i = 0; i < data.length; i++) {
			if(check(data[i],n)){
				data1[j++]=data[i];
			}else{
				data2[k++]=data[i];
			}
		}
		
		//����ֱ����
		result=0;
		for (int i = 0; i < data1.length; i++) {
			result^=data1[i];
		}
		System.out.println(result);
		result=0;
		for (int i = 0; i < data2.length; i++) {
			result^=data2[i];
		}
		System.out.println(result);
	}
	/**
	 * ������ݵĶ����Ƶ�nΪ�Ƿ�Ϊ1
	 * @param num
	 * @param n
	 * @return
	 */
	private static boolean check(int num, int n) {
		int temp=num;
		temp>>=n;
		if((temp&1)==1){
			return true;
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data={11,11,22,22,456,456,789,789,2345,2345,999,888};
		find(data);
	}

}
