/**
 * 
 */
package com.shuan.problem;

import java.util.TreeMap;

/**
 * @author Shuan �����
 */
public class RedBlackTree {

	private TreeNode root;

	/**
	 * ����һ���ڵ�
	 * 
	 * @param key
	 */
	public void add(int key) {
		if (root == null) {
			// ���ڵ�
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

		public void LDR() {

			if (this.leftChild != null) {
				this.leftChild.LDR();
			}
			System.out.println(this.Key);
			if (this.rightChild != null) {
				this.rightChild.LDR();
			}
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
			// �ж��Ƿ���Ϲ���
			if (childNode == this.leftChild) {
				// ����������
				TreeNode brotherNode = this.rightChild;
				if ((childNode.rightChild != null && childNode.rightChild.color == NodeColor.Red)
						|| (childNode.leftChild != null && childNode.leftChild.color == NodeColor.Red)) {
					// ��Ҫ����
					if (brotherNode != null
							&& brotherNode.color == NodeColor.Red) {
						// ֱ�Ӹı���ɫ
						this.color = NodeColor.Red;
						childNode.color = NodeColor.Black;
						brotherNode.color = NodeColor.Black;
					} else {
						// ��ڵ�Ϊ�ջ���Ϊ��ɫ ��Ҫ������ת
						// ������������ֻ���ܳ���һ��
						if (childNode.rightChild != null
								&& childNode.rightChild.color == NodeColor.Red) {
							// LR
							this.leftChild = childNode.rotateRR(); // childNode�Ѿ��ı���
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
				// ����������
				TreeNode brotherNode = this.leftChild;
				if ((childNode.rightChild != null && childNode.rightChild.color == NodeColor.Red)
						|| (childNode.leftChild != null && childNode.leftChild.color == NodeColor.Red)) {
					// ��Ҫ����
					if (brotherNode != null
							&& brotherNode.color == NodeColor.Red) {
						// ֱ�Ӹı���ɫ
						this.color = NodeColor.Red;
						childNode.color = NodeColor.Black;
						brotherNode.color = NodeColor.Black;
					} else {
						// ��ڵ�Ϊ�ջ���Ϊ��ɫ ��Ҫ������ת

						// ������������ֻ���ܳ���һ��
						if (childNode.leftChild != null
								&& childNode.leftChild.color == NodeColor.Red) {
							// RL
							this.rightChild = childNode.rotateLL(); // childNode�Ѿ��ı���
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

	private enum NodeColor {
		Black, Red
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
		redBlackTree.printTree();
		System.out.println("over");
	}

}
