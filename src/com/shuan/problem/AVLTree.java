/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.List;

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
				this.leftChild=this.leftChild.add(key);
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
				this.rightChild=this.rightChild.add(key);
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
		
		//更新Height
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
	 * 找到最小节点
	 * @return
	 */
	public AVLTree findMin(){
		if (this.leftChild==null) {
			return this;
		}
		return this.leftChild.findMin();
	}
	/**
	 * 找到最大节点
	 * @return
	 */
	public AVLTree findMax(){
		if (this.rightChild==null) {
			return this;
		}
		return this.rightChild.findMax();
	}
	
	/**
	 * 递归删除
	 * @param parentNode 父亲节点
	 * @param key  key
	 * @return  根节点
	 */
	public AVLTree remove(int key) {
		AVLTree node=this;
		
		if (key<this.key) {
			//左边
			if (this.leftChild!=null) {
				this.leftChild=this.leftChild.remove(key);
				//调整高度
				int rightHeight = 0;
				int leftHeight = 0;
				if (this.rightChild != null) {
					rightHeight = rightChild.getHeight();
				}
				if (this.leftChild!=null) {
					leftHeight = this.leftChild.getHeight();
				}
				
				if (rightHeight - leftHeight == 2) {
					// 相差2 就不平衡
					if (key < this.leftChild.key) {
						node = this.rotateLL();
					} else {
						node = this.rotateLR();
					}
				}
			}
		}else if(key>this.key){
			//右边
			if (this.rightChild!=null) {
				node.rightChild=this.rightChild.remove(key);
			
				//调整高度
				int rightHeight = 0;
				int leftHeight = 0;
				if (this.leftChild != null) {
					leftHeight = this.leftChild.getHeight();
				}
				if (this.rightChild!=null) {
					rightHeight = this.rightChild.getHeight();
				}
				if (leftHeight - rightHeight == 2) {
					// 相差2 就不平衡
					if (key < this.rightChild.key) {
						node = this.rotateRL();
					} else {
						node = this.rotateRR();
					}
				}
			}
		}else{
			//当前节点
			node=this.realRemove();
		}
		//重新计算高度
		this.updateHeight();
		return node;
		
	}

	private AVLTree realRemove() {
		
		if (this.leftChild==null&&this.rightChild==null) {
			return null;
		}else if(this.leftChild==null){
			//只有右孩子
			return this.rightChild;
		}else if(this.rightChild==null){
			//只有左孩子
			return this.leftChild;
		}else{
			//有两个孩子
			AVLTree nextNode=this.findLDRNext(); //中序遍历的下一个节点 这个节点一定是个叶子节点
			//直接换值
			this.key=nextNode.key;
			this.rightChild=this.rightChild.remove(this.key);
			return this;
		}
	}

	/**
	 * 获取中序遍历的后一个节点
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
		
		//删除
		
		root=root.remove(10);
		System.out.println("over");
	}

}
