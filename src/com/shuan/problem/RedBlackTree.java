/**
 * 
 */
package com.shuan.problem;

import java.util.TreeMap;

/**
 * @author Shuan 红黑树
 */
public class RedBlackTree {

	private enum NodeColor {
		Black, Red
	}

	private class TreeNode {
		public int Key;
		NodeColor color;
		TreeNode leftChild;
		TreeNode rightChild;

		public TreeNode(int key) {
			this.Key = key;
			this.leftChild = null;
			this.rightChild = null;
			this.color = NodeColor.Red;
		}

		public TreeNode add(int key) {
			TreeNode root = null;
			if (key < this.Key) {
				// left
				if (this.leftChild != null) {
					this.leftChild = this.leftChild.add(key);

				} else {
					this.leftChild = new TreeNode(key);
				}
				root = fixAfterInsert(this.leftChild);
			} else {
				// right
				if (this.rightChild != null) {
					this.rightChild = this.rightChild.add(key);
				} else {
					this.rightChild = new TreeNode(key);
				}
				root = fixAfterInsert(this.rightChild);
			}
			
			return root;
		}

		private TreeNode fixAfterInsert(TreeNode childNode) {
			if (childNode == null || childNode.color == NodeColor.Black) {
				return this;
			}
			TreeNode top = this;
			// 判断是否符合规则
			if (childNode == this.leftChild) {
				// 调整左子树
				TreeNode brotherNode = this.rightChild;
				if ((childNode.rightChild != null && childNode.rightChild.color == NodeColor.Red)
						|| (childNode.leftChild != null && childNode.leftChild.color == NodeColor.Red)) {
					// 需要调整
					if (brotherNode != null
							&& brotherNode.color == NodeColor.Red) {
						// 直接改变颜色
						this.color = NodeColor.Red;
						childNode.color = NodeColor.Black;
						brotherNode.color = NodeColor.Black;
					} else {
						// 叔节点为空或者为黑色 需要进行旋转
						// 以下两个条件只可能出现一种
						if (childNode.rightChild != null
								&& childNode.rightChild.color == NodeColor.Red) {
							// LR
							this.leftChild = childNode.rotateRR(); // childNode已经改变了
							childNode = this.leftChild;
						}
						if (childNode.leftChild != null
								&& childNode.leftChild.color == NodeColor.Red) {
							// LL
							childNode.color = NodeColor.Black;
							this.color = NodeColor.Red;
							top = this.rotateLL();
						}
					}
				}

			} else {
				// 调整右子树
				TreeNode brotherNode = this.leftChild;
				if ((childNode.rightChild != null && childNode.rightChild.color == NodeColor.Red)
						|| (childNode.leftChild != null && childNode.leftChild.color == NodeColor.Red)) {
					// 需要调整
					if (brotherNode != null
							&& brotherNode.color == NodeColor.Red) {
						// 直接改变颜色
						this.color = NodeColor.Red;
						childNode.color = NodeColor.Black;
						brotherNode.color = NodeColor.Black;
					} else {
						// 叔节点为空或者为黑色 需要进行旋转

						// 以下两个条件只可能出现一种
						if (childNode.leftChild != null
								&& childNode.leftChild.color == NodeColor.Red) {
							// RL
							this.rightChild = childNode.rotateLL(); // childNode已经改变了
							childNode = this.rightChild;
						}
						if (childNode.rightChild != null
								&& childNode.rightChild.color == NodeColor.Red) {
							// RR
							childNode.color = NodeColor.Black;
							this.color = NodeColor.Red;
							top = this.rotateRR();

						}
					}
				}
			}

			return top;
		}

		public void LDR() {

			if (this.leftChild != null) {
				this.leftChild.LDR();
			}
			System.out.println(this.Key);
			if (this.rightChild != null) {
				this.rightChild.LDR();
			}
		}

		/**
		 * 删除一个节点
		 * @param key
		 * @return
		 */
		public TreeNode remove(int key) {
			TreeNode root=null;
			if (key<this.Key&&this.leftChild!=null) {
				this.leftChild=this.leftChild.remove(key);
				root=this.fixAfterDelete(this.leftChild);
			}else if(key>this.Key&&this.rightChild!=null){
				this.rightChild=this.rightChild.remove(key);
				root=this.fixAfterDelete(this.rightChild);
			}else{
				root=this.realRemove();
			}
			return root;
		}

		private TreeNode fixAfterDelete(TreeNode childNode) {
			return fixAfterInsert(childNode);
		}

		private TreeNode realRemove() {
			if (this.leftChild==null&&this.rightChild==null) {
				//叶子节点
				return null;
			}else if (this.leftChild==null) {
				//只有右节点
				return this.rightChild;
			}else if(this.rightChild==null){
				//只有左边节点
				return this.leftChild;
			}
			
			TreeNode nextNode=this.findLDRNext();  //中序遍历的下一个节点 这个节点一定是个叶子节点
			this.Key=nextNode.Key;
			this.rightChild=this.rightChild.remove(nextNode.Key);
			return this;
		}

		private TreeNode findLDRNext() {
			TreeNode temp=this.rightChild;
			while(temp!=null&&temp.leftChild!=null){
				temp=temp.leftChild;
			}
			return temp;
		}

		private TreeNode rotateLL() {
			TreeNode top=this.leftChild;
			assert(top!=null);
			this.leftChild=top.rightChild;
			top.rightChild=this;
			return top;
		}

		private TreeNode rotateRR() {
			TreeNode top=this.rightChild;
			assert(top!=null);
			this.rightChild=top.leftChild;
			top.leftChild=this;
			return top;
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree redBlackTree = new RedBlackTree();
		redBlackTree.add(10);
		redBlackTree.add(5);
		redBlackTree.add(15);
		redBlackTree.add(2);
		redBlackTree.add(3);
		redBlackTree.add(7);
		redBlackTree.add(9);
		redBlackTree.add(12);
		redBlackTree.add(19);
		redBlackTree.printTree();
		System.out.println("over");
		
		//redBlackTree.remove(2);
		redBlackTree.remove(15);
		redBlackTree.printTree();
	}

	private TreeNode root;

	/**
	 * 新增一个节点
	 * 
	 * @param key
	 */
	public void add(int key) {
		if (root == null) {
			// 根节点
			root = new TreeNode(key);
			root.color = NodeColor.Black;
			return;
		}
		root = root.add(key);
		root.color = NodeColor.Black;
	}

	public void printTree() {
		root.LDR();
	}

	/**
	 * 删除节点
	 * @param key
	 */
	public void remove(int key){
		if (root!=null) {
			root=root.remove(key);
		}
	}

}
