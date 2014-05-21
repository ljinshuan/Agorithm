/**
 * 
 */
package com.shuan.problem;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 * 字符串相似度
 * 
 * 1: 当 Xi = Yi 时，则C[i,j]=C[i-1,j-1]；
 * 2: 当 Xi != Yi 时， 则C[i,j]=Min{C[i-1,j-1],C[i-1,j],C[i,j-1]}+1；
 * 动态规划
 */
public class StringLike {

	public static int LD(char [] str1,char [] str2){
		int [][] matrix=new int[str1.length+1][str2.length+1];
		//初始化边界
		for (int i = 0; i < str1.length+1; i++) {
			matrix[i][0]=0;
		}
		for (int i = 0; i < str2.length; i++) {
			matrix[0][i]=0;
		}
		
		int m=str1.length,n=str2.length;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1[i-1]==str2[j-1]) {
					matrix[i][j]=matrix[i-1][j-1];
				}else{
					int min=Math.min(matrix[i][j-1], matrix[i-1][j]);
					min=Math.min(min, matrix[i-1][j-1]);
					matrix[i][j]=min+1;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			CommonUtil.PrintArray(matrix[i]);
		}
		return matrix[m][n];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result=LD("abcd".toCharArray(),"egxg".toCharArray());
		System.out.println(result);
	}

}
