/**
 * 
 */
package com.shuan.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuan
 * http://www.cnblogs.com/huangxincheng/archive/2012/12/16/2820519.html
 * 两个集合不能相交
 * 并查集
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
	 * 初始化
	 * @param datas
	 */
	public void init(int [] datas){
		for (int i : datas) {
			dic.put(i, new Node(i,0));
		}
	}
	/**
	 * 是否在同一个集合
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
	 * 查找该集合的代表元素
	 * @param key
	 * @return
	 */
	public int find(int key){
		if (dic.get(key).parent==key) {
			//当前值为那个集合的根节点
			return key;
		}
		dic.get(key).parent=find(dic.get(key).parent);
		return dic.get(key).parent;
	}
	/**
	 * 合并两个集合
	 * @param a
	 * @param b
	 */
	public void union(int a,int b){
		int rootA=find(a);
		int rootB=find(b);
		
		if (rootA==rootB) {
			//在同一个集合 不用合并
			return;
		}
		if (dic.get(rootA).rank<dic.get(rootB).rank) {
			//A小于B的深度 A合并到右边
			dic.get(rootA).parent=rootB;
		}else{
			if(dic.get(rootA).rank==dic.get(rootB).rank){
				dic.get(rootA).rank++;
			}
			//B合并到A
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
