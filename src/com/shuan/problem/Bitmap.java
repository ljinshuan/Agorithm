/**
 * 
 */
package com.shuan.problem;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 */
public class Bitmap {

	private byte [] bits=null;
	
	public Bitmap(int n) {
		int temp=n>>3;
		bits=new byte[temp+1]; //����ռ�
	}
	
	/**
	 * �����n���ֽ�
	 * @param n
	 */
	public void clear(int n){
		if (n>=bits.length) {
			return;
		}
		bits[n]&=0;
	}
	/**
	 * Add
	 * @param num
	 */
	public void add(int num){
		int shift=num&0x07; //���� �൱�� num%8
		int index=num>>3;  // ����  �൱�� num/8
		
		int temp=1<<shift;  //����λ��1
		bits[index]|=temp;
	}
	/**
	 * 
	 * @param num
	 */
	public void remove(int num){
		int shift=num&0x07; //���� �൱�� num%8
		int index=num>>3;  // ����  �൱�� num/8
		
		int temp=~(1<<shift);  //����λ��0
		bits[index]&=temp;
	}
	/**
	 * contains
	 * @param num
	 * @return
	 */
	public boolean contains(int num){
		int n=bits[num>>3]&(1<<(num&0x07));
		if(n==0)
			return false;
		return true;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bitmap bitmap=new Bitmap(10000); //�����Ϊ10000
		
		int [] data=CommonUtil.InitRandomArray(100);
		data[0]=data[99];
		
		for (int i = 0; i < data.length; i++) {
			bitmap.add(data[i]);
		}
		
		//�鿴�ظ�
		if (bitmap.contains(data[0])) {
			System.out.println("yes");
		}
	}

}
