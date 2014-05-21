/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan
 *AC�Զ��� ��ģʽƥ��
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
	public ACNode [] childnodes=null; //26���ַ� 26���� 
	public int freq; //��Ƶ
	public char nodeChar ; //�ýڵ������
	private ACNode parentNode=null;
	private ACNode failNode=null;
	
	public ACNode(){
		
		freq=0;
	}
	
	/**
	 * ��ʼ��FailNode
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
	 * ��ʼ��FailNode
	 */
	public void initFailNode(ACNode root){
		if (this.parentNode==null||this.parentNode==root) {
			//root�ڵ�
			this.failNode=root;
		}else{
			char nodeChar=this.nodeChar;
			ACNode nextNode=this.parentNode;
			/**
			 * ��ʱ��Ҫ�߸ýڵ�ĸ��ڵ��ʧ��ָ�룬һֱ����ֱ���ҵ�ĳ���ڵ�ĺ��ӽڵ�Ҳ�ǵ����ڵ�ͬ�����ַ���û���ҵ��Ļ�����ʧ��ָ���ָ��root��
			 */
			while(nextNode!=null){
				nextNode=nextNode.failNode;
				if(nextNode.childnodes!=null){
					//���ӽڵ����ҵ�����ڵ�ͬ�����ַ�
					for(ACNode node:nextNode.childnodes){
						if (node!=null&&node.nodeChar==this.nodeChar) {
							this.failNode=node;
							break;
						}
					}
					if (this.failNode!=null) {
						//�ҵ���
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
	 * ƥ������
	 * @param mainStr
	 */
	public void matchAC(ACNode root,String mainStr){
		char [] datas=mainStr.toCharArray();
		ACNode head=root;
		int freq=0;
		for (int i = 0; i < datas.length; i++) {
			int index=datas[i]-'a';
			
			while(head.childnodes==null||(head.childnodes[index]==null&&head!=root)){
				//û���ҵ�  
				head=head.failNode;
			}
			//���ƥ����Ǹ��ڵ�
			head=head.childnodes[index];
			
			if (head==null) {
				head=root;
			}
			head.freq+=1;
			ACNode temp=head;
			
			//��trie����ƥ�䵽���ַ�����ǵ�ǰ�ڵ�Ϊ�ѷ��ʣ�������Ѱ�Ҹýڵ��ʧ�ܽڵ㡣
			/**
			 * �����ַ���ƥ����󶼱���Ҫ��failnode�ڵ��������Լ�����;,�൱��һ����������������Ŀ�ķ�ֹ�����ڵ㱻���Ե���
			 * ���磺��ƥ�䵽��"she"����Ȼ��ƥ�䵽���ַ����ĺ�׺��he"��Ҫ���ڳ�����ƥ�䵽�������ڵ�Ҫ��ʧ��ָ���������Լ�����;��
			 */
			while(temp!=root){
				temp=temp.failNode;
				temp.freq+=1;
			}
			
		}
	}
	/**
	 * ����һ������
	 * @param word
	 */
	public void addWord(String word){
		if(word.isEmpty()){
			return;
		}
		//ȡ��һ����ĸ
		char ch=word.charAt(0);
		//���� λ��
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
		//ȡ��һ����ĸ
		char ch = word.charAt(0);
		// ���� λ��
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
	 * �������
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
