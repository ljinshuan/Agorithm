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
	 * ����
	 */
	private ArrayList<Integer> datas=new ArrayList<Integer>();
	
	public void Eequeue(int level){
		//��ӵ�β��
		datas.add(level);
		//���ϵ���
		if (datas.size()==1) {
			return;
		}
		int parentIndex=(datas.size())/2-1;
		while(parentIndex>=0){
			int left=parentIndex*2+1;
			int right=left+1;
			int max=left;  
			//�Ƿ����ұߺ���
			if (right<datas.size()) {
				//��ȡ���ҽڵ��ĸ���
				max=datas.get(left)<datas.get(right)?right:left;
			}
			if (datas.get(max)>datas.get(parentIndex)) {
				//����
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
		
		//���µ���
		int parent=0;
		int left=1;
		int right=2;
		int max=left;
		while(parent*2+1<datas.size()){
			left=parent*2+1;
			right=left+1;
			max=left;
			if(right<datas.size()){
				//�ҵ����ҽڵ�Ƚϴ��Ǹ�
				max=datas.get(left)<datas.get(right)?right:left;
			}
			if (datas.get(max)>datas.get(parent)) {
				//����
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
