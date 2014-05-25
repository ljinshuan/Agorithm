/**
 * 
 */
package com.shuan.problem;

import java.util.TreeMap;

/**
 * @author Shuan
 *�����
 */
public class RedBlackTree {

	private TreeNode root;
	
	/**
	 * ����һ���ڵ�
	 * @param key
	 */
	public void add(int key){
		if (root==null) {
			//���ڵ�
			root=new TreeNode(key);
			return;
		}
		root.add(key);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	private  class TreeNode{
		public int Key;
		NodeColor color;
		TreeNode leftChild;
		TreeNode rightChild;
		
		public TreeNode(int key){
			this.Key=key;
			this.leftChild=null;
			this.rightChild=null;
			this.color=NodeColor.Red;
		}

		public TreeNode add(int key) {
			TreeNode root=this;
			if (key<this.Key) {
				//left
				if (this.leftChild!=null) {
					this.leftChild=this.leftChild.add(key);
				}else{
					this.leftChild=new TreeNode(key);
				}
				if (this.color==NodeColor.Red&&this.leftChild.color==NodeColor.Red) {
					//��ǰ�ڵ��Լ���ߺ��ӽڵ㶼�Ǻ�ɫ
					
				}
			}else{
				//right
				if (this.rightChild!=null) {
					this.rightChild=this.rightChild.add(key);
				}else{
					this.rightChild=new TreeNode(key);
				}
			}
			
			return root;
		}
		
	}
	private enum NodeColor{
		Black,
		Red
	}
	
	

}
