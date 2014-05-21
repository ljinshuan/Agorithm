/**
 * 
 */
package com.shuan.problem;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 * 
 */
public class BiggestSubSequenceSum {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static int getBiggestSum(int [] data){
		int [] tempSum=new int [data.length];
		tempSum[0]=data[0]>0?data[0]:0;
		int temp=tempSum[0];
		int tempEnd=0,tempBegin=0;
		int begin=0,end=0;
		for (int i = 1; i < data.length; i++) {
			if(temp>0){
				temp+=data[i];
				tempEnd=i;
			}else{
				temp=data[i];
				tempBegin=i;
				tempEnd=i;
			}
			
			if (temp>=tempSum[i-1]) {
				tempSum[i]=temp;
				begin=tempBegin;
				end=tempEnd;
				
			}else{
				tempSum[i]=tempSum[i-1];
			}
		}
		System.out.println(begin);
		System.out.println(end);
		CommonUtil.PrintArray(tempSum);
		return 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int [] data={-2,11,-4,13,-5,2,-100,23};
		getBiggestSum(data);
		
	}

}
