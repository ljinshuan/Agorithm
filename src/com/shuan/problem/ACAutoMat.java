/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *AC自动机 多模式匹配
 */
public class ACAutoMat{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ACNode ac=new ACNode();
		ac.addWord("say");
		ac.addWord("her");
		ac.addWord("shr");
		ac.addWord("he");
		ac.addWord("she");
		
		ac.initParentNode();
		ac.initFailNode(ac);
		ac.matchAC(ac,"yasherhs");
		ac.ListAllWords();
		System.out.println(ac.wordCount("say"));
		System.out.println(ac.wordCount("her"));
		System.out.println(ac.wordCount("shr"));
		System.out.println(ac.wordCount("he"));
		System.out.println(ac.wordCount("she"));
	}
}

class ACNode{
	public ACNode [] childnodes=null; //26个字符 26叉树 
	public int freq; //词频
	public char nodeChar ; //该节点的内容
	private ACNode parentNode=null;
	private ACNode failNode=null;
	
	public ACNode(){
		
		freq=0;
	}
	
	/**
	 * 初始化FailNode
	 */
	public void initParentNode(){
		
		if (childnodes==null) {
			return;
		}
		for(ACNode node:childnodes){
			if (node!=null) {
				node.parentNode=this;
				node.initParentNode();
			}
		}
	}
	/**
	 * 初始化FailNode
	 */
	public void initFailNode(ACNode root){
		if (this.parentNode==null||this.parentNode==root) {
			//root节点
			this.failNode=root;
		}else{
			char nodeChar=this.nodeChar;
			ACNode nextNode=this.parentNode;
			/**
			 * 此时就要走该节点的父节点的失败指针，一直回溯直到找到某个节点的孩子节点也是当初节点同样的字符，没有找到的话，其失败指针就指向root。
			 */
			while(nextNode!=null){
				nextNode=nextNode.failNode;
				if(nextNode.childnodes!=null){
					//从子节点中找到与初节点同样的字符
					for(ACNode node:nextNode.childnodes){
						if (node!=null&&node.nodeChar==this.nodeChar) {
							this.failNode=node;
							break;
						}
					}
					if (this.failNode!=null) {
						//找到了
						break;
					}
				}
				if (nextNode==root) {
					this.failNode=root;
					break;
				}
			}
		}
		if (childnodes!=null) {
			for(ACNode node:childnodes){
				if (node!=null) {
					node.initFailNode(root);
				}
			}
		}
	}
	/**
	 * 匹配主串
	 * @param mainStr
	 */
	public void matchAC(ACNode root,String mainStr){
		char [] datas=mainStr.toCharArray();
		ACNode head=root;
		int freq=0;
		for (int i = 0; i < datas.length; i++) {
			int index=datas[i]-'a';
			
			while(head.childnodes==null||(head.childnodes[index]==null&&head!=root)){
				//没有找到  
				head=head.failNode;
			}
			//获得匹配的那个节点
			head=head.childnodes[index];
			
			if (head==null) {
				head=root;
			}
			head.freq+=1;
			ACNode temp=head;
			
			//在trie树中匹配到了字符，标记当前节点为已访问，并继续寻找该节点的失败节点。
			/**
			 * 所有字符在匹配完后都必须要走failnode节点来结束自己的旅途,相当于一个回旋，这样做的目的防止包含节点被忽略掉。
			 * 比如：我匹配到了"she"，必然会匹配到该字符串的后缀”he"，要想在程序中匹配到，则必须节点要走失败指针来结束自己的旅途。
			 */
			while(temp!=root){
				temp=temp.failNode;
				temp.freq+=1;
			}
			
		}
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
			childnodes=new ACNode[26];
		}
		if(childnodes[index]==null){
			childnodes[index]=new ACNode();
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
		ACNode node=childnodes[index];
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
