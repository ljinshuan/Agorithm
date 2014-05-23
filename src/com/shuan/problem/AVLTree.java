/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuan ƽ�������
 */
public class AVLTree {

	public int key; // ֵ
	public AVLTree leftChild; // ������
	public AVLTree rightChild; // ������
	public int height;
	public AVLTree(int key) {
		this.key = key;
		height=1;
	}

	public AVLTree add(int key) {
		AVLTree root = this;
		int rightHeight = 0;
		int leftHeight = 0;
		if (key <= this.key) {
			// ������
			if (this.leftChild != null) {
				this.leftChild=this.leftChild.add(key);
			} else {
				leftChild = new AVLTree(key);
			}
			if (this.rightChild != null) {
				rightHeight = rightChild.getHeight();
			}
			leftHeight = this.leftChild.getHeight();
			if (leftHeight - rightHeight == 2) {
				// ���2 �Ͳ�ƽ��
				if (key < this.leftChild.key) {
					root = this.rotateLL();
				} else {
					root = this.rotateLR();
				}
			}

		} else {
			// ������
			if (this.rightChild != null) {
				this.rightChild=this.rightChild.add(key);
			} else {
				rightChild = new AVLTree(key);
			}

			if (this.leftChild != null) {
				leftHeight = leftChild.getHeight();
			}
			rightHeight = this.rightChild.getHeight();
			if (rightHeight - leftHeight == 2) {
				// ���2 �Ͳ�ƽ��
				if (key < this.rightChild.key) {
					root = this.rotateRL();
				} else {
					root = this.rotateRR();
				}
			}
		}

		this.updateHeight();
		
		return root;

	}

	private void updateHeight() {
		int leftHeight,rightHeight;
		leftHeight=this.leftChild==null?0:this.leftChild.getHeight();
		rightHeight=this.rightChild==null?0:this.rightChild.getHeight();
		this.height=(leftHeight>rightHeight?leftHeight:rightHeight)+1;
	}

	private AVLTree rotateRR() {
		AVLTree top = this.rightChild;
		assert (this.rightChild != null);

		this.rightChild = top.leftChild;
		top.leftChild = this;
		
		
		//����Height
		this.updateHeight();
		top.updateHeight();
		return top;

	}

	private AVLTree rotateRL() {
		this.rightChild = this.rightChild.rotateLL();
		return this.rotateRR();
	}

	private AVLTree rotateLR() {
		this.leftChild=this.leftChild.rotateRR();
		return this.rotateLL();
	}

	private AVLTree rotateLL() {
		AVLTree top = this.leftChild;
		assert (this.leftChild != null);
		this.leftChild = top.rightChild;
		top.rightChild = this;
		
		//����Height
		this.updateHeight();
		top.updateHeight();
		return top;
	}

	public int getHeight() {
		
		return height;
	/*	
		if (this.leftChild == null && this.rightChild == null) {
			return 1;
		}
		int leftHeight = this.leftChild == null ? 0 : this.leftChild
				.getHeight();
		int rightHeight = this.rightChild == null ? 0 : this.rightChild
				.getHeight();

		return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;*/
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(int key){
		if (this.key==key) {
			return true;
		}
		if (this.leftChild!=null) {
			return this.leftChild.contains(key);
		}
		if(this.rightChild!=null){
			return this.rightChild.contains(key);
		}
		return false;
	}
	
	/**
	 * �ڷ�Χ�ڲ���
	 * @param min
	 * @param max
	 */
	public void searchRange(int min,int max){
		
		if (this.key>min) {
			if (this.leftChild!=null) {
				this.leftChild.searchRange(min, max);
			}
		}
		if (this.key>=min&&this.key<=max) {
			System.out.println(this.key);
		}
		if (this.key<min||this.key<max) {
			if (this.rightChild!=null) {
				this.rightChild.searchRange(min, max);
			}
		}
	}
	/**
	 * �ҵ���С�ڵ�
	 * @return
	 */
	public AVLTree findMin(){
		if (this.leftChild==null) {
			return this;
		}
		return this.leftChild.findMin();
	}
	/**
	 * �ҵ����ڵ�
	 * @return
	 */
	public AVLTree findMax(){
		if (this.rightChild==null) {
			return this;
		}
		return this.rightChild.findMax();
	}
	
	/**
	 * �ݹ�ɾ��
	 * @param parentNode ���׽ڵ�
	 * @param key  key
	 * @return  ���ڵ�
	 */
	public AVLTree remove(int key) {
		AVLTree node=this;
		
		if (key<this.key) {
			//���
			if (this.leftChild!=null) {
				this.leftChild=this.leftChild.remove(key);
				//�����߶�
				int rightHeight = 0;
				int leftHeight = 0;
				if (this.rightChild != null) {
					rightHeight = rightChild.getHeight();
				}
				if (this.leftChild!=null) {
					leftHeight = this.leftChild.getHeight();
				}
				
				if (rightHeight - leftHeight == 2) {
					// ���2 �Ͳ�ƽ��
					if (key < this.leftChild.key) {
						node = this.rotateLL();
					} else {
						node = this.rotateLR();
					}
				}
			}
		}else if(key>this.key){
			//�ұ�
			if (this.rightChild!=null) {
				node.rightChild=this.rightChild.remove(key);
			
				//�����߶�
				int rightHeight = 0;
				int leftHeight = 0;
				if (this.leftChild != null) {
					leftHeight = this.leftChild.getHeight();
				}
				if (this.rightChild!=null) {
					rightHeight = this.rightChild.getHeight();
				}
				if (leftHeight - rightHeight == 2) {
					// ���2 �Ͳ�ƽ��
					if (key < this.rightChild.key) {
						node = this.rotateRL();
					} else {
						node = this.rotateRR();
					}
				}
			}
		}else{
			//��ǰ�ڵ�
			node=this.realRemove();
		}
		//���¼���߶�
		this.updateHeight();
		return node;
		
	}

	private AVLTree realRemove() {
		
		if (this.leftChild==null&&this.rightChild==null) {
			return null;
		}else if(this.leftChild==null){
			//ֻ���Һ���
			return this.rightChild;
		}else if(this.rightChild==null){
			//ֻ������
			return this.leftChild;
		}else{
			//����������
			AVLTree nextNode=this.findLDRNext(); //�����������һ���ڵ� ����ڵ�һ���Ǹ�Ҷ�ӽڵ�
			//ֱ�ӻ�ֵ
			this.key=nextNode.key;
			this.rightChild=this.rightChild.remove(this.key);
			return this;
		}
	}

	/**
	 * ��ȡ��������ĺ�һ���ڵ�
	 * @return
	 */
	private AVLTree findLDRNext() {
		AVLTree node=null;
		node=this.rightChild;
		while(node!=null&&node.leftChild!=null){
			node=node.leftChild;
		}
		return node;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree root = new AVLTree(10);
		/*root = root.add(2);
		root = root.add(3);
		root = root.add(4);
		root=root.add(5);*/
		
		root = root.add(5);
		root = root.add(15);
		root=root.add(2);
		root=root.add(3);
		root.searchRange(1, 3);
		
		//ɾ��
		
		root=root.remove(10);
		System.out.println("over");
	}

}
