/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *全排列
 */
public class AllSort {

	public static int count=0;
	public static void perm( char [] str,int start){
		if (start==str.length-1) {
			for (int i = 0; i < str.length; i++) {
				System.out.print(str[i]);
			}
			count++;
			System.out.println();
		
			return;
		}
		for (int i = start; i < str.length; i++) {
			//交换
			char temp=str[start];
			str[start]=str[i];
			str[i]=temp;
			
			perm( str, start+1);
			//还原
			temp=str[start];
			str[start]=str[i];
			str[i]=temp;
			
		}
		
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String abc="abcdef";
		StringBuilder sb=new StringBuilder();
		perm( abc.toCharArray(), 0);
		System.out.println(count);
	}

}
