/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan ƽ�������
 */
public class AVLTree {

	public int key; // ֵ
	public AVLTree leftChild; // ������
	public AVLTree rightChild; // ������
	
	public AVLTree(int key) {
		this.key = key;
	}

	public AVLTree add(int key) {
		AVLTree root = this;
		int rightHeight = 0;
		int leftHeight = 0;
		if (key <= this.key) {
			// ������
			if (this.leftChild != null) {
				this.leftChild.add(key);
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
				this.rightChild.add(key);
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


		return root;

	}

	private AVLTree rotateRR() {
		AVLTree top = this.rightChild;
		assert (this.rightChild != null);

		this.rightChild = top.leftChild;
		top.leftChild = this;
		
		return top;

	}

	private AVLTree rotateRL() {
		this.rightChild.rightChild = this.rightChild.rightChild.rotateLL();
		return this.rotateRR();
	}

	private AVLTree rotateLR() {
		this.leftChild.leftChild = this.leftChild.leftChild.rotateRR();
		return this.rotateLL();
	}

	private AVLTree rotateLL() {
		AVLTree top = this.leftChild;
		assert (this.leftChild != null);
		this.leftChild = top.rightChild;
		top.rightChild = this;
		return top;
	}

	public int getHeight() {
		if (this.leftChild == null && this.rightChild == null) {
			return 1;
		}
		int leftHeight = this.leftChild == null ? 0 : this.leftChild
				.getHeight();
		int rightHeight = this.rightChild == null ? 0 : this.rightChild
				.getHeight();

		return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree root = new AVLTree(1);
		root = root.add(2);
		root = root.add(3);
		root = root.add(4);
		System.out.println("over");
	}

}
