/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *前缀树
 *http://www.cnblogs.com/huangxincheng/archive/2012/11/25/2788268.html
 *
 *用于最长前缀匹配和词频统计
 */
public class TrieTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrieNode root=new TrieNode();
		root.addWord("and");
		root.addWord("book");
		root.addWord("an");
		root.addWord("android");
		root.addWord("book");
		
		root.ListAllWords();
		//System.out.println(root.wordCount("book"));
	}
}

class TrieNode{
	public TrieNode [] childnodes=null; //26个字符 26叉树 
	public int freq; //词频
	public char nodeChar ; //该节点的内容
	
	public TrieNode(){
		
		freq=0;
	}
	
	/**
	 * 增加一个单词
	 * @param word
	 */
	public void addWord(String word){
		if(word.isEmpty()){
			return;
		}
		//取第一个字母
		char ch=word.charAt(0);
		//计算 位置
		int index=ch-'a';
		if(childnodes==null){
			childnodes=new TrieNode[26];
		}
		if(childnodes[index]==null){
			childnodes[index]=new TrieNode();
			childnodes[index].nodeChar=ch;
		}
		String nextWord=word.substring(1);
		if(nextWord.length()==0){
			childnodes[index].freq++;
		}
		this.childnodes[index].addWord(nextWord);
	}
	public  void ListAllWords(){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < childnodes.length; i++) {
			if(childnodes[i]!=null){
				childnodes[i].LDR(sb);
			}
		}
	}
	public int wordCount(String word){
		if(word.isEmpty()){
			return 0;
		}
		//取第一个字母
		char ch = word.charAt(0);
		// 计算 位置
		int index = ch - 'a';
		TrieNode node=childnodes[index];
		if (node == null) {
			return 0;
		}
		String nextWord = word.substring(1);
		
		if (nextWord.length() == 0) {
			return node.freq;
		}
		return node.wordCount(nextWord);
	}
	/**
	 * 先序遍历
	 * @param sb
	 */
	public void LDR(StringBuffer sb){
		sb.append(this.nodeChar);
		if(childnodes==null){
			System.out.println(sb.toString());
			sb.deleteCharAt(sb.length()-1);
			return;
		}
		if(this.freq!=0){
			System.out.println(sb.toString());
		}
		for (int i = 0; i < childnodes.length; i++) {
			if(childnodes[i]!=null){
				childnodes[i].LDR(sb);
			}
			
		}
		sb.deleteCharAt(sb.length()-1);
	}
	
}
