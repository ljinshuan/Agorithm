/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 * 求两个字符串的最长公共子序列
 * 
 * Answer :动态规划  http://www.cnblogs.com/huangxincheng/archive/2012/11/11/2764625.html
 * 
 * 
 */
public class LCS {

	public static String getLCS(String str1,String str2){
		int [] [] martix=new int [str1.length()+1][str2.length()+1];
		int [] [] flags=new int [str1.length()+1][str2.length()+1];
		//初始化边界
		for (int i = 0; i <=str1.length(); i++) {
			martix[i][0]=0;
		}
		for (int i = 0; i <=str2.length(); i++) {
			martix[0][i]=0;
		}
		//填充矩阵
		int m=str1.length();
		int n=str2.length();
		for (int i = 1; i <=m; i++) {
			for (int j = 1; j <=n; j++) {
				//相等
				if (str1.charAt(i-1)==str2.charAt(j-1)) {
					martix[i][j]=martix[i-1][j-1]+1;
					flags[i][j]=1;
				}
				else{
					if (martix[i-1][j]>=martix[i][j-1]) {
						martix[i][j]=martix[i-1][j];
						flags[i][j]=2;
					}else{
						martix[i][j]=martix[i][j-1];
						flags[i][j]=3;
					}
				}
			}
		}
		System.out.println(martix[m][n]);
		StringBuilder sb=new StringBuilder();
		subSequence(flags,sb,str1,str2,m,n);
		return sb.toString();
	}
	private static void subSequence(int [][] flags,StringBuilder sb,String str1,String str2, int i, int j) {
		if(i==0||j==0){
			return;
		}
		if(flags[i][j]==1){
			String result=String.format("%c {%d,%d}",str2.charAt(j-1),i-1,j-1);
			sb.append(str2.charAt(j-1));
			System.out.println(result);
			
			subSequence(flags, sb, str1, str2, i-1, j-1);
		}
		else if (flags[i][j]==2) {
			subSequence(flags, sb, str1, str2, i-1, j);
		}else{
			subSequence(flags, sb, str1, str2, i, j-1);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result=getLCS("cnblogs", "belong");
		System.out.println(result);
	}

}
