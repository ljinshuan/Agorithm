/**
 * 
 */
package com.shuan.search;

import java.util.List;
import java.util.TreeMap;

/**
 * @author ljinshuan
 * 二叉排序树
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
	 * 插入一个节点
	 * @param key
	 */
	public void insertTree(int key){
		if(key<=data){
			//左边
			if(leftChild==null){
				leftChild=new BSTree(key, null, null);
				leftChild.parentNode=this;
			}else {
				leftChild.insertTree(key);
			}
		}else{
			//右边
			if(rightChild==null){
				rightChild=new BSTree(key, null, null);
				rightChild.parentNode=this;
			}else{
				rightChild.insertTree(key);
			}
		}
	}
	/**
	 * 中序遍历
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
	 * 查找元素
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
	 * 删除一个节点 不能删除自身
	 * @param treeNode
	 */
	public void remove(BSTree treeNode){
		if(this==treeNode){
			return;
		}
		BSTree parent=treeNode.parentNode;
		//删除叶子节点
		if(treeNode.leftChild==null&&treeNode.rightChild==null){
			if (parent.leftChild==treeNode) {
				parent.leftChild=null;
			}else{
				parent.rightChild=null;
			}
		}
		if(treeNode.leftChild!=null&&treeNode.rightChild==null){
			//左子树不为空
			if (parent.leftChild==treeNode) {
				parent.leftChild=treeNode.leftChild;
			}else{
				parent.rightChild=treeNode.leftChild;
			}
		}
		if(treeNode.leftChild==null&&treeNode.rightChild!=null){
			//右子树不为空
			if (parent.leftChild==treeNode) {
				parent.leftChild=treeNode.rightChild;
			}else{
				parent.rightChild=treeNode.rightChild;
			}
		}
		if(treeNode.leftChild!=null&&treeNode.rightChild!=null){
			//两边都不为空
			//找到右边孩子的最左节点
			BSTree mostLeftChild=treeNode.rightChild;
			while(mostLeftChild.leftChild!=null){
				mostLeftChild=mostLeftChild.leftChild;
			}
			
			mostLeftChild.leftChild=treeNode.leftChild;
			
			//重新赋值
			if (parent.leftChild==treeNode) {
				parent.leftChild=mostLeftChild;
			}else{
				parent.rightChild=mostLeftChild;
			}
		}
		
	}
	/**
	 * 创建一个二叉排序树
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
