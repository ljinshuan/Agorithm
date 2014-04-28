/**
 * 
 */
package com.shuan.search;

import java.util.ArrayList;
import java.util.List;

import com.shuan.sort.Sort;
import com.shuan.util.CommonUtil;

/**
 * @author ljinshuan
 *
 */
public class Search {

	/**
	 * À≥–Ú≤È’“
	 * @param data
	 * @return
	 */
	public static int SequenceSearch(int [] data,int key){
		for (int i = 0; i < data.length; i++) {
			if(key==data[i]){
				return i;
			}
		}
		return -1;
	}
	/**
	 * ’€∞Î≤È’“
	 * @param data
	 * @param key
	 * @return
	 */
	public static int BinarySearch(int [] data,int key){
		int left=0;
		int right=data.length-1;
		
		while(left<right){
			int middle=(left+right)/2;
			if(data[middle]==key){
				return middle;
			}else {
				if(data[middle]>key){
					right=middle;
				}else{
					left=middle;
				}
			}
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data=CommonUtil.InitRandomArray(10);
		BSTree root=BSTree.Create(data);
		List<Integer> result=new ArrayList<Integer>();
		root.LDR_BST(result);
		for (Integer integer : result) {
			System.out.printf("%d ", integer);
		}
		
	}

}
