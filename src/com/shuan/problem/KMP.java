/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *KMPMatch
 */
public class KMP {

	public static int match(String mainString,String subString){
		int [] next=getNext(subString);
		
		int i=0,j=0;
		while(i<mainString.length()){
			if(j==-1||mainString.charAt(i)==subString.charAt(j)){
				i++;
				j++;
			}else{
				j=next[j];
			}
			if(j==subString.length()){
				return i-j;
			}
		}
		return -1;
	}
	/**
	 * ���յ��Ƶ�˼�룺

   		���ݶ���next[0]=-1������next[j]=k, ��P[0...k-1]==P[j-k,j-1]

   1)��P[j]==P[k]������P[0..k]==P[j-k,j]������Ȼ��next[j+1]=next[j]+1=k+1;

   2)��P[j]!=P[k]������԰��俴��ģʽƥ������⣬��ƥ��ʧ�ܵ�ʱ��kֵ����ƶ�����Ȼk=next[k]��
	 * @param subString
	 * @return
	 */
	private static int[] getNext(String subString) {
		int [] next=new int[subString.length()];
		next[0]=-1;
		int j=0,k=-1;
		while(j<subString.length()-1){
			if(k==-1||subString.charAt(j)==subString.charAt(k)){
				j++;
				k++;
				next[j]=k;
			}else{
				k=next[k];
			}
		}
		
		return next;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result=match("ljinshuan", "shuan");
		System.out.println(result);
	}

}
