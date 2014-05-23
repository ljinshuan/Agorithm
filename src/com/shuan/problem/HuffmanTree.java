/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Shuan
 *
 */
public class HuffmanTree {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		List<HuffmanNode> nodes=new ArrayList<HuffmanNode>();
		nodes.add(new HuffmanNode(80, 'c'));
		nodes.add(new HuffmanNode(40, 'd'));
		nodes.add(new HuffmanNode(10, 'a'));
		nodes.add(new HuffmanNode(20, 'b'));
		
		HuffmanTree huffmanTree=new HuffmanTree();
		HuffmanNode huffmanNode=huffmanTree.BuildHuffmanTree(nodes);
		
		StringBuilder sb=new StringBuilder();
		huffmanNode.getEncodings(sb);
		
		
	}
	/**
	 * 生成哈夫曼树
	 * @param nodes
	 * @return
	 */
	public  HuffmanNode BuildHuffmanTree(List<HuffmanNode> nodes){	
		PriorityQueue<HuffmanNode> nodeQueue=new PriorityQueue<HuffmanNode>(10,new Comparator<HuffmanNode>() {

			@Override
			public int compare(HuffmanNode o1, HuffmanNode o2) {
				return o1.value<=o2.value?-1:1;
			}
		});
		for (HuffmanNode huffmanNode : nodes) {
			nodeQueue.add(huffmanNode);
		}
		HuffmanNode node1=null;
		HuffmanNode node2=null;
		HuffmanNode newNode=null;
		while(!nodeQueue.isEmpty()){
			node1=nodeQueue.poll();
			node2=nodeQueue.poll();
			if (node1.getCh()!=' ') {
				//有值 放左边
				node1.encodeChar='0';
				node2.encodeChar='1';
				newNode=new HuffmanNode(node1.value+node2.value, node1, node2);
			}else{
				node1.encodeChar='1';
				node2.encodeChar='0';
				newNode=new HuffmanNode(node1.value+node2.value, node2, node1);
			}
			if (nodeQueue.isEmpty()) {
				break;
			}
			nodeQueue.add(newNode);
			
		}
		return newNode;
	}
}

/**
 * 节点
 * @author Shuan
 *
 */
class HuffmanNode{
	private char ch;
	public int value;
	
	public HuffmanNode leftChild;
	public HuffmanNode rightChild;
	public char encodeChar;
	public HuffmanNode(int value, HuffmanNode leftChild,
			HuffmanNode rightChild) {
		super();
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.ch=' ';
		this.encodeChar=' ';
	}
	
	public void getEncodings(StringBuilder sb) {
		if (this.encodeChar!=' ') {
			sb.append(this.encodeChar);
		}
		if (this.leftChild==null&&this.rightChild==null) {
			System.out.println(sb.toString());
		}
		if (this.leftChild!=null) {
			this.leftChild.getEncodings(sb);
		}
		if (this.rightChild!=null) {
			this.rightChild.getEncodings(sb);
		}
		if (sb.length()!=0) {
			sb.deleteCharAt(sb.length()-1);
		}
		
	}
	public HuffmanNode(int value,char ch) {
		super();
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
		this.ch=ch;
		this.encodeChar=' ';
	}
	public char getCh() {
		return ch;
	}
	public void setCh(char ch) {
		this.ch = ch;
	}
}
