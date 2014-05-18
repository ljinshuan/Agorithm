/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 * 整形转字符串
 * 关键为求整数长度  以及建立表来进行直接查找
 */
public class ToString {

	
	/**
	 * 整形转字符串
	 * @param value
	 * @return
	 */
	public static String toString(int value){
		if (value==Integer.MIN_VALUE) {
			return "-2147483648";
		}
		int size = (value < 0) ? stringSize(-value) + 1 : stringSize(value);
        char[] buf = new char[size];
        getChars(value, size, buf);
        return new String(buf);
	}
	final static char [] DigitOnes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	private static void getChars(int value, int index, char[] buf) {
		 char sign = 0;
		if(value<0){
			sign='-';
			value=-value;
		}
		//求个位数
		int pos=index;
		int q=value;
		int r=0;
		while(true){
			r=q%10;
			buf[--pos]=DigitOnes[r];
			q=q/10;
			if(q==0){
				break;
			}
		}
		if(sign!=0){
			buf[--pos]=sign;
		}
		
	}
	final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
         99999999, 999999999, Integer.MAX_VALUE }; 
	private static int stringSize(int i) {
		for (int j = 0; j < sizeTable.length; j++) {
			if (sizeTable[j]>=i) {
				return j+1;
			}
		}
		return 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(toString(Integer.MAX_VALUE));

	}

}
