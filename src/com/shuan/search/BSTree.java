/**
 * 
 */
package com.shuan.search;

import java.util.List;
import java.util.TreeMap;

/**
 * @author ljinshuan
 * ����������
 */
public class BSTree {
	public int data;
	public BSTree leftChild=null;
	public BSTree rightChild=null;
	public BSTree parentNode=null;
	
	public BSTree(int data, BSTree leftChild, BSTree rightChild) {
		super();
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	/**
	 * ����һ���ڵ�
	 * @param key
	 */
	public void insertTree(int key){
		if(key<=data){
			//���
			if(leftChild==null){
				leftChild=new BSTree(key, null, null);
				leftChild.parentNode=this;
			}else {
				leftChild.insertTree(key);
			}
		}else{
			//�ұ�
			if(rightChild==null){
				rightChild=new BSTree(key, null, null);
				rightChild.parentNode=this;
			}else{
				rightChild.insertTree(key);
			}
		}
	}
	/**
	 * �������
	 */
	public void LDR_BST(List<Integer> result){
		if(leftChild!=null){
			leftChild.LDR_BST(result);
		}
		result.add(data);
		
		if(rightChild!=null){
			rightChild.LDR_BST(result);
		}
		
	}
	/**
	 * ����Ԫ��
	 * @param key
	 * @return
	 */
	public BSTree Search(int key){
		if(data==key){
			return this;
		}
		if(key<=data){
			if(leftChild!=null)  
				return leftChild.Search(key);
		}else{
			if(rightChild!=null) 
				return rightChild.Search(key);
		}
		return null;
		
	}
	/**
	 * ɾ��һ���ڵ� ����ɾ������
	 * @param treeNode
	 */
	public void remove(BSTree treeNode){
		if(this==treeNode){
			return;
		}
		BSTree parent=treeNode.parentNode;
		//ɾ��Ҷ�ӽڵ�
		if(treeNode.leftChild==null&&treeNode.rightChild==null){
			if (parent.leftChild==treeNode) {
				parent.leftChild=null;
			}else{
				parent.rightChild=null;
			}
		}
		if(treeNode.leftChild!=null&&treeNode.rightChild==null){
			//��������Ϊ��
			if (parent.leftChild==treeNode) {
				parent.leftChild=treeNode.leftChild;
			}else{
				parent.rightChild=treeNode.leftChild;
			}
		}
		if(treeNode.leftChild==null&&treeNode.rightChild!=null){
			//��������Ϊ��
			if (parent.leftChild==treeNode) {
				parent.leftChild=treeNode.rightChild;
			}else{
				parent.rightChild=treeNode.rightChild;
			}
		}
		if(treeNode.leftChild!=null&&treeNode.rightChild!=null){
			//���߶���Ϊ��
			//�ҵ��ұߺ��ӵ�����ڵ�
			BSTree mostLeftChild=treeNode.rightChild;
			while(mostLeftChild.leftChild!=null){
				mostLeftChild=mostLeftChild.leftChild;
			}
			
			mostLeftChild.leftChild=treeNode.leftChild;
			
			//���¸�ֵ
			if (parent.leftChild==treeNode) {
				parent.leftChild=mostLeftChild;
			}else{
				parent.rightChild=mostLeftChild;
			}
		}
		
	}
	/**
	 * ����һ������������
	 * @param data
	 * @return
	 */
	public static BSTree Create(int [] data){
		BSTree root=new BSTree(data[0], null, null);
		for (int i = 1; i < data.length; i++) {
			root.insertTree(data[i]);
		}
		return root;
	}
	
}
