/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuan
 *十字链表
 */
public class AcrossLinker {

	public List<AcrossNode> rowHeaders=new ArrayList<AcrossNode>();
	public List<AcrossNode> colHeaders=new ArrayList<AcrossNode>();
	public AcrossNode totalNode;
	
	private AcrossNode currentRowHeader=null;
	private AcrossNode currentColHeader=null;
	
	public void createAcrossLinker(int [][] data){
		int m=data.length;  //行 
		int n=data[0].length; //列
		totalNode=new AcrossNode(m,n,0);
		initHeaders(m,n);
		
		int count=0;
		for (int i = 0; i < m; i++) {
			currentRowHeader=rowHeaders.get(i);
			
			for (int j = 0; j < n; j++) {
				if (data[i][j]!=0) {
					//Add to
					currentColHeader=colHeaders.get(j);
					addNode(i,j,data[i][j]);
					count++;
					currentColHeader=null;
				}
			}
			currentRowHeader=null;
		}
		totalNode.value=count;
	}
	private void addNode(int i, int j, int value) {
		AcrossNode node=new AcrossNode(i,j,value);
		AcrossNode tail=currentRowHeader;
		while(tail.right!=null){
			tail=tail.right;
		}
		tail.right=node;
		if (node.col==totalNode.col-1) {
			node.right=currentRowHeader;
		}
		
		tail=currentColHeader;
		while(tail.down!=null){
			tail=tail.down;
		}
		tail.down=node;
		if (node.row==totalNode.row-1) {
			node.down=currentColHeader;
		}
		
	}
	private void initHeaders(int m, int n) {
		for (int i = 0; i <m; i++) {
			rowHeaders.add(new AcrossNode(0, 0, 0));
		}
		for(int i=0;i<n;i++){
			colHeaders.add(new AcrossNode(0,0,0));
		}
		
	}
	
	@Override
	public String toString() {
		
		for (AcrossNode node : rowHeaders) {
			AcrossNode temp=node;
			while(temp.right!=node&&temp.right!=null){
				temp=temp.right;
				System.out.println(String.format("Row %d,Col %d value:%d",temp.row,temp.col,temp.value));
			}
		}
		return super.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [][] data={{2,0,4},{0,3,0},{0,0,9}};
		AcrossLinker acrossLinker=new AcrossLinker();
		acrossLinker.createAcrossLinker(data);
		
		System.out.println(acrossLinker);
	}

}

class AcrossNode{
	public int row;  //行
	public int col;  //列
	
	public AcrossNode right; //右边节点
	public AcrossNode down;  //下边节点
	
	public int value;   //值
	
	public AcrossNode(int row,int col,int value){
		this.row=row;
		this.col=col;
		this.value=value;
		right=null;
		down=null;
	}
}
