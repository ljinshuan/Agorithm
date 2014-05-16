/**
 * 
 */
package com.shuan.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan �㷨���Ӷ�ΪO(n)������ �������� �������� Ͱ����
 */
public class OnSort {
	/**
	 * ȡ��iλ��λ��
	 * 
	 * @param i
	 * @return
	 */
	private int getNBaseNum(int num, int i) {
		int n = i - 1;
		int x = 1;
		while (n > 0) {
			x *= 10;
			n--;
		}
		return (num / x) % 10;

	}

	/**
	 * �������� 1 ���䣺 �����ݰ���λ����ֵ���䵽���������� 2 �ռ�: ���ΰ�������ֵ�Ĵ�С�ռ�����
	 * 
	 * @param data
	 */
	public void baseSort(int[] data) {
		// ��ʼ������
		List<List<Integer>> container = new ArrayList<List<Integer>>();
		for (int i = 0; i < 10; i++) {
			container.add(new ArrayList<Integer>());
		}
		int k = 0, index;
		while (true) {
			// ����
			k++;
			for (int i = 0; i < data.length; i++) {
				index = getNBaseNum(data[i], k);
				container.get(index).add(data[i]);
			}
			if (container.get(0).size() == data.length) {
				// �����û�з��� ���˳� (���е����ݶ����䵽0��)
				break;
			}
			int j = 0;
			// �ռ�
			for (List<Integer> list : container) {
				for (int temp : list) {
					data[j++] = temp;
				}
				list.clear();
			}

		}
	}

	/**
	 * �������� ��������Ļ���˼���ǣ�ͳ��һ����������С��ĳ��Ԫ��a�ĸ���Ϊn,��ֱ�ӰѸ�Ԫ��a�ŵ���n+1��λ���ϡ�
	 * ��Ȼ�����м���Ԫ����ͬʱҪ���ʵ��ĵ�������Ϊ���ܰ����е�Ԫ�طŵ�ͬһ��λ���ϡ�����������������Ԫ�ض���0��k֮���������
	 * 
	 * @param data
	 */
	public int[] countSort(int[] data) {
		// ����һ���������� counter ����Ϊdata�����ֵ+1
		int[] counter = new int[10000];
		// ͳ��data��ֵΪi��Ԫ�صĸ���
		for (int i = 0; i < data.length; i++) {
			counter[data[i]] += 1;
		}
		// ����ͳ��data�У�С�ڵ���i��Ԫ�ظ���
		for (int i = 1; i < counter.length; i++) {
			counter[i] = counter[i] + counter[i - 1];
		}

		// ����һ����������Ž��
		int[] result = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			int temp = counter[data[i]]; // С��data[i]�����ݵĸ��� data[i]
											// ��resultӦ�ô�ŵ�λ��Ϊ temp-1
			result[temp - 1] = data[i];
			counter[data[i]]--;
		}
		return result;
	}

	/**
	 * Ͱ���� ����Ҫ�Դ�СΪ[1..1000]��Χ�ڵ�n������A[1..n]���򣬿��԰�Ͱ��Ϊ��СΪ10�ķ�Χ��
	 * ������ԣ��輯��B[1]�洢[1..10]������������B[2]�洢(10..20]����������������B[i]�洢((i-1)*10,
	 * i*10]��������i = 1,2,..100���ܹ���100��Ͱ�� Ȼ���A[1..n]��ͷ��βɨ��һ�飬��ÿ��A[i]�����Ӧ��ͰB[j]�С�
	 * Ȼ���ٶ���100��Ͱ��ÿ��Ͱ�������������ʱ����ð�ݣ�ѡ���������ţ�һ����˵�κ����򷨶����ԡ�
	 * ����������ÿ��Ͱ��������֣���ÿ��Ͱ�е����ִ�С��������������͵õ����������ź����һ�������ˡ�
	 * 
	 * @param data
	 */
	public void bucketSort(int[] data) {
		// ��ʼ������
		List<List<Integer>> container = new ArrayList<List<Integer>>();
		for (int i = 0; i < 10; i++) {
			//10��Ͱ  ÿ��Ͱ��1000����
			container.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < data.length; i++) {
			container.get(getBucketIndex(data[i])).add(data[i]);
		}
		//��ÿ��Ͱ�е����ݽ�������
		for(List<Integer> list:container){
			Collections.sort(list);
		}
		int j=0;
		for(List<Integer> list:container){
			for (int temp:list) {
				data[j++]=temp;
			}
		}
	}
	/**
	 * ��������ȡ��Ӧ�÷��õ�Ͱ������
	 * @param num
	 * @return
	 */
	private int getBucketIndex(int num){
		return num/1000;
	}
}
