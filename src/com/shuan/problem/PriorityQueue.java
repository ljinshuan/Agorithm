/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuan
 *
 */
public class PriorityQueue {

	/**
	 * 数据
	 */
	private ArrayList<Integer> datas=new ArrayList<Integer>();
	
	public void Eequeue(int level){
		//添加到尾部
		datas.add(level);
		//向上调整
		if (datas.size()==1) {
			return;
		}
		int parentIndex=(datas.size())/2-1;
		while(parentIndex>=0){
			int left=parentIndex*2+1;
			int right=left+1;
			int max=left;  
			//是否有右边孩子
			if (right<datas.size()) {
				//获取左右节点哪个大
				max=datas.get(left)<datas.get(right)?right:left;
			}
			if (datas.get(max)>datas.get(parentIndex)) {
				//调整
				int temp=datas.get(parentIndex);
				datas.set(parentIndex, datas.get(max));
				datas.set(max, temp);
				parentIndex=(parentIndex+1)/2-1;
			}else{
				break;
			}
		}
	}
	
	public int Dequeue(){
		int result=datas.get(0);
		datas.set(0, datas.get(datas.size()-1));
		datas.remove(datas.size()-1);
		
		//向下调整
		int parent=0;
		int left=1;
		int right=2;
		int max=left;
		while(parent*2+1<datas.size()){
			left=parent*2+1;
			right=left+1;
			max=left;
			if(right<datas.size()){
				//找到左右节点比较大那个
				max=datas.get(left)<datas.get(right)?right:left;
			}
			if (datas.get(max)>datas.get(parent)) {
				//调整
				int temp=datas.get(parent);
				datas.set(parent, datas.get(max));
				datas.set(max, temp);
				parent=max;
			}else{
				break;
			}
			
		}
		
		return result;
	}
	
	public boolean isEmpty(){
		
		return datas.isEmpty();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue priorityQueue=new PriorityQueue();
		
		priorityQueue.Eequeue(10);
		priorityQueue.Eequeue(20);
		priorityQueue.Eequeue(9);
		priorityQueue.Eequeue(11);
		priorityQueue.Eequeue(100);
		
		while(!priorityQueue.isEmpty()){
			
			System.out.println(priorityQueue.Dequeue());
		}
	}

}
