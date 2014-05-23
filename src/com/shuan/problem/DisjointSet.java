/**
 * 
 */
package com.shuan.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuan
 * http://www.cnblogs.com/huangxincheng/archive/2012/12/16/2820519.html
 * �������ϲ����ཻ
 * ���鼯
 */
public class DisjointSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] data={1,4,6,8,2,3,5,7};
		// M={1,4,6,8} N={2,5,4,7}
		DisjointSet disjointSet=new DisjointSet();
		disjointSet.init(data);
		
		disjointSet.union(1, 4);
		disjointSet.union(1, 6);
		disjointSet.union(1, 8);
		
		disjointSet.union(2,3);
		disjointSet.union(2, 5);
		disjointSet.union(2, 7);
		
		System.out.println(disjointSet.isSameSet(1, 4));
		System.out.println(disjointSet.isSameSet(1, 10));

	}
	
	
	private Map<Integer, Node> dic=new HashMap<Integer,Node>();
	
	/**
	 * ��ʼ��
	 * @param datas
	 */
	public void init(int [] datas){
		for (int i : datas) {
			dic.put(i, new Node(i,0));
		}
	}
	/**
	 * �Ƿ���ͬһ������
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isSameSet(int a,int b){
		if (find(a)==find(b)) {
			return true;
		}
		return false;
	}
	
	/**
	 * ���Ҹü��ϵĴ���Ԫ��
	 * @param key
	 * @return
	 */
	public int find(int key){
		if (dic.get(key).parent==key) {
			//��ǰֵΪ�Ǹ����ϵĸ��ڵ�
			return key;
		}
		dic.get(key).parent=find(dic.get(key).parent);
		return dic.get(key).parent;
	}
	/**
	 * �ϲ���������
	 * @param a
	 * @param b
	 */
	public void union(int a,int b){
		int rootA=find(a);
		int rootB=find(b);
		
		if (rootA==rootB) {
			//��ͬһ������ ���úϲ�
			return;
		}
		if (dic.get(rootA).rank<dic.get(rootB).rank) {
			//AС��B����� A�ϲ����ұ�
			dic.get(rootA).parent=rootB;
		}else{
			if(dic.get(rootA).rank==dic.get(rootB).rank){
				dic.get(rootA).rank++;
			}
			//B�ϲ���A
			dic.get(rootB).parent=rootA;
		}
		
	}
	class Node{
		public int parent;
		public int rank;
		
		public Node(int parent,int rank){
			this.parent=parent;
			this.rank=rank;
		}
	}
	
	

}
