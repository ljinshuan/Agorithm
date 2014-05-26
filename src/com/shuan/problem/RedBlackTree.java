/**
 * 
 */
package com.shuan.problem;

import java.util.TreeMap;

/**
 * @author Shuan 红黑树
 */
public class RedBlackTree {

	private TreeNode root;

	/**
	 * 新增一个节点
	 * 
	 * @param key
	 */
	public void add(int key) {
		if (root == null) {
			// 根节点
			root = new TreeNode(key, null);
			root.color = NodeColor.Black;
			root.parent = root;
			return;
		}
		root=root.add(key);
		root.color=NodeColor.Black;
		root.parent=root;
	}
	public void printTree(){
		root.LDR();
	}

	private class TreeNode {
		public int Key;
		NodeColor color;
		TreeNode parent;
		TreeNode leftChild;
		TreeNode rightChild;

		public TreeNode(int key, TreeNode parent) {
			this.Key = key;
			this.leftChild = null;
			this.rightChild = null;
			this.color = NodeColor.Red;
			this.parent = parent;
		}

		public void LDR() {
			
			if (this.leftChild!=null) {
				this.leftChild.LDR();
			}
			System.out.println(this.Key);
			if (this.rightChild!=null) {
				this.rightChild.LDR();
			}
		}

		public TreeNode add(int key) {
			TreeNode root = null;
			TreeNode parentNode=this.parent;
			if (key < this.Key) {
				// left
				if (this.leftChild != null) {
					root=this.leftChild.add(key);
					
				} else {
					this.leftChild = new TreeNode(key, this);
				}
				 root = fixAfterInsert(this.leftChild);
			} else {
				// right
				if (this.rightChild != null) {
					root=this.rightChild.add(key);
				} else {
					this.rightChild = new TreeNode(key, this);
				}
				 root = fixAfterInsert(this.rightChild);
			}
			if (root==RedBlackTree.this.root) {
				return root;
			}
			if (parentNode.parent.leftChild==parentNode) {
				parentNode.parent.leftChild=root;
			}else{
				parentNode.parent.rightChild=root;
			}
			root.parent=parentNode.parent;

			return root;
		}

		
		private TreeNode fixAfterInsert(TreeNode childNode) {

			if (this.color == NodeColor.Black) {
				// 黑色 不用调整
				return this.parent;
			}
			if (childNode.color == NodeColor.Black) {
				return this.parent;
			}
			TreeNode top = null;
			// 连续两个红色 需要调整
			// 判定需要怎样的旋转
			TreeNode parentNode=this.parent;
			if (parentNode.leftChild == this) {
				// 在祖父节点的左边
				TreeNode brotherNode = parentNode.rightChild;
				if (brotherNode != null && brotherNode.color == NodeColor.Red) {
					// 红 直接调整颜色
					this.color = NodeColor.Black;
					brotherNode.color = NodeColor.Black;
					parentNode.color = NodeColor.Red;
					top=this.parent;
				} else {
					// 黑或者空 需要旋转
					TreeNode rrTop=this;
					if (childNode == this.rightChild) {
						rrTop=childNode.RotateRR(); //这里会改变this状态
						parentNode.leftChild=rrTop;
						rrTop.parent=parentNode;
					}
					//调整颜色
					rrTop.color=NodeColor.Black;
					parentNode.color=NodeColor.Red;
					//旋转
					top = rrTop.RotateLL();
				}
			} else {
				// 在祖父节点的右边
				TreeNode brotherNode=parentNode.leftChild;
				if (brotherNode!=null&&brotherNode.color==NodeColor.Red) {
					// 红 直接调整颜色
					this.color = NodeColor.Black;
					brotherNode.color = NodeColor.Black;
					parentNode.color = NodeColor.Red;
					top=this.parent;
				}else{
					TreeNode llTop=this;
					if (childNode==this.leftChild) {
						llTop=childNode.RotateLL();
						parentNode.rightChild=llTop;
						llTop.parent=parentNode;
					}
					//调整颜色
					llTop.color=NodeColor.Black;
					parentNode.color=NodeColor.Red;
					//旋转
					top=parentNode.rightChild.RotateRR();
				}
			}

			return top;
		}

		private TreeNode RotateRR() {
			TreeNode top=this;
			TreeNode parent=this.parent;
			
			parent.rightChild=this.leftChild;
			if (this.leftChild!=null) {
				this.leftChild.parent=parent;
			}
			top.leftChild=parent;
			parent.parent=top;
			
			return this;
		}
		private TreeNode RotateLL() {
			TreeNode top=this;
			TreeNode parent=this.parent;
			
			parent.leftChild=this.rightChild;
			if (this.rightChild!=null) {
				this.rightChild.parent=parent;
			}
			top.rightChild=parent;
			parent.parent=top;
			
			return top;
		}

	}

	private enum NodeColor {
		Black, Red
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree redBlackTree=new RedBlackTree();
		redBlackTree.add(10);
		redBlackTree.add(5);
		redBlackTree.add(15);
		redBlackTree.add(2);
		redBlackTree.add(3);
		redBlackTree.printTree();
		System.out.println("over");
	}

}
