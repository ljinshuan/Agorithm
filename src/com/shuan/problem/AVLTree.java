/**
 * 
 */
package com.shuan.problem;

/**
 * @author Shuan 平衡二叉树
 */
public class AVLTree {

	public int key; // 值
	public AVLTree leftChild; // 左子树
	public AVLTree rightChild; // 右子树
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
			// 左子树
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
				// 相差2 就不平衡
				if (key < this.leftChild.key) {
					root = this.rotateLL();
				} else {
					root = this.rotateLR();
				}
			}

		} else {
			// 右子树
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
				// 相差2 就不平衡
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
		
		
		//更新Height
		//this.updateHeight();
		top.updateHeight();
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
		
		//更新Height
		//this.updateHeight();
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
	 * 在范围内查找
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
	 * 删除值为key的node
	 * @param key
	 * @return
	 */
	public AVLTree remove(int key){
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AVLTree root = new AVLTree(1);
		root = root.add(2);
		root = root.add(3);
		root = root.add(4);
		root.searchRange(1, 3);
		System.out.println("over");
	}

}
