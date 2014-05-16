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
 * @author Shuan 算法复杂度为O(n)的排序 基数排序 计数排序 桶排序
 */
public class OnSort {
	/**
	 * 取第i位的位数
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
	 * 基数排序 1 分配： 将数据按照位数的值分配到各个容器中 2 收集: 依次按照容器值的大小收集数据
	 * 
	 * @param data
	 */
	public void baseSort(int[] data) {
		// 初始化容器
		List<List<Integer>> container = new ArrayList<List<Integer>>();
		for (int i = 0; i < 10; i++) {
			container.add(new ArrayList<Integer>());
		}
		int k = 0, index;
		while (true) {
			// 分配
			k++;
			for (int i = 0; i < data.length; i++) {
				index = getNBaseNum(data[i], k);
				container.get(index).add(data[i]);
			}
			if (container.get(0).size() == data.length) {
				// 如果都没有分配 则退出 (所有的数据都分配到0中)
				break;
			}
			int j = 0;
			// 收集
			for (List<Integer> list : container) {
				for (int temp : list) {
					data[j++] = temp;
				}
				list.clear();
			}

		}
	}

	/**
	 * 计数排序 计数排序的基本思想是：统计一个数序列中小于某个元素a的个数为n,则直接把该元素a放到第n+1个位置上。
	 * 当然当过有几个元素相同时要做适当的调整，因为不能把所有的元素放到同一个位置上。计数排序假设输入的元素都是0到k之间的整数。
	 * 
	 * @param data
	 */
	public int[] countSort(int[] data) {
		// 引入一个辅助数组 counter 长度为data的最大值+1
		int[] counter = new int[10000];
		// 统计data中值为i的元素的个数
		for (int i = 0; i < data.length; i++) {
			counter[data[i]] += 1;
		}
		// 继续统计data中，小于等于i的元素个数
		for (int i = 1; i < counter.length; i++) {
			counter[i] = counter[i] + counter[i - 1];
		}

		// 引入一个数组来存放结果
		int[] result = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			int temp = counter[data[i]]; // 小于data[i]的数据的个数 data[i]
											// 在result应该存放的位置为 temp-1
			result[temp - 1] = data[i];
			counter[data[i]]--;
		}
		return result;
	}

	/**
	 * 桶排序 例如要对大小为[1..1000]范围内的n个整数A[1..n]排序，可以把桶设为大小为10的范围，
	 * 具体而言，设集合B[1]存储[1..10]的整数，集合B[2]存储(10..20]的整数，……集合B[i]存储((i-1)*10,
	 * i*10]的整数，i = 1,2,..100。总共有100个桶。 然后对A[1..n]从头到尾扫描一遍，把每个A[i]放入对应的桶B[j]中。
	 * 然后再对这100个桶中每个桶里的数字排序，这时可用冒泡，选择，乃至快排，一般来说任何排序法都可以。
	 * 最后依次输出每个桶里面的数字，且每个桶中的数字从小到大输出，这样就得到所有数字排好序的一个序列了。
	 * 
	 * @param data
	 */
	public void bucketSort(int[] data) {
		// 初始化容器
		List<List<Integer>> container = new ArrayList<List<Integer>>();
		for (int i = 0; i < 10; i++) {
			//10个桶  每个桶放1000个数
			container.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < data.length; i++) {
			container.get(getBucketIndex(data[i])).add(data[i]);
		}
		//对每个桶中的数据进行排序
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
	 * 根据数字取得应该放置的桶的索引
	 * @param num
	 * @return
	 */
	private int getBucketIndex(int num){
		return num/1000;
	}
}
