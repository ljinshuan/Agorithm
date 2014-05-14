/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
/**
 * 电话号码对应英语单词
题目:

电话的号码盘一般可以用于输入字母。如5可以输入j、k、l。
对于号码596可以依次输出其代表的所有字母组合jwm,lzo,...
是否可以设计一个程序，根据该对应关系，尽快从这些字母组合中找出一个有意义的单词表示电话号码。对于一个电话号码，它是否可以用一个单词来表示呢?用最快的方法。

Answer:排列树 枚举所有可能 + 前缀树匹配单词词典
 */
import java.util.Map;

/**
 * @author Shuan
 *
 */
public class NumberToWord {

	public static String charsString="abcdefghijklmnopqrstuvwxyz";
	public static int charToNumber(char ch){
		int index=charsString.indexOf(ch);
		if(index<=14){
			return index/3+2;
		}else if(index<=18){
			return 7;
		}else if(index<=21){
			return 8;
		}else{
			return 9;
		}
		
	}
	public static char [] numberTochars(int num){
		char [] result=null;
		if(num>=2&&num<=6){
			result=new char[3];
			int start=3*(num-2);
			charsString.getChars(start, start+3, result, 0);
		}else if(num==7){
			result=new char[4];
			charsString.getChars(15, 19, result, 0);
		}else if(num==8){
			result=new char[3];
			charsString.getChars(19, 22, result, 0);
		}else if(num==9){
			result=new char[4];
			charsString.getChars(22, 26, result, 0);
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root=Node.createTree(new int []{2,5,3});
		root.getAllLeef();
	}

}

class Node{
	
	private char data;
	private Node []  children=null;
	/**
	 * 创建一般节点
	 * @param data
	 */
	public Node(char data){
		this.data=data;
	}
	/**
	 * 创建根节点
	 * @param num
	 */
	public Node(int num){
		this.data=' ';
		
	}
	
	public static Node createTree(int [] numberSequence){
		Node root=new Node(numberSequence[0]);
		for (int i = 0; i < numberSequence.length; i++) {
			root.insertWithNum(numberSequence[i]);
		}
		return root;
	}
	/**
	 * 根据数字插入
	 * @param num
	 */
	public void insertWithNum(int num) {
		if(children!=null){
			for (int i = 0; i < children.length; i++) {
				if(children[i]!=null){
					children[i].insertWithNum(num);
				}
			}
			return;
		}
		if(num==7||num==9){
			children=new Node[4];
		}else{
			children=new Node[3];
		}
		char [] chars=NumberToWord.numberTochars(num);
		for (int i = 0; i < chars.length; i++) {
			children[i]=new Node(chars[i]);
		}
	}
	/**
	 * 遍历叶子节点
	 */
	public void getAllLeef(){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < children.length; i++) {
			children[i].LDR(sb);
		}
	}
	
	private void LDR(StringBuffer sb){
		sb.append(this.data);
		if(children==null){
			System.out.println(sb.toString());
			sb.deleteCharAt(sb.length()-1);
			return;
		}
		
		for (int i = 0; i < children.length; i++) {
			children[i].LDR(sb);
		}
		sb.deleteCharAt(sb.length()-1);
	}
	
	
	
	
	
	
}