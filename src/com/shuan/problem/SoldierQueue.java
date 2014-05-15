/**
 * 
 */
package com.shuan.problem;

import java.util.Arrays;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 *在一个划分成网格的操场上，n个士兵散乱地站在网格点上。网格点由整数坐标(x,y)表示。士兵们可以沿网格边上、下、左、右移动一步，
 *但在同一时刻任一网格点上只能有一名士兵。按照军官的命令，士兵们要整齐地列成一个水平队列，即排列成(x,y),(x+1,y),…,(x+n-1,y)。
 *如何选择x和y的值才能使士兵们以最少的总移动步数排成一列。
 *
 *Answer 分别求X和Y的中位数
 * 
排序输入的X[]，排序输入的Y[]，Y[]取中位数，对排序后X[]每一项做上面的线性变换（减去i),再对新的X[]排序，再取新X[]的中位数。
 */
public class SoldierQueue {

	public static int getMedian(int [] data){
		if(data.length%2==0){
			//偶数
			int middle=data.length/2;
			return (data[middle]+data[middle-1])/2;
		}else{
			return data[data.length/2];
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] coordinateX=CommonUtil.InitRandomArray(20);
		int [] coorrinateY=CommonUtil.InitRandomArray(20);
		
		//得到Y的中位数
		Arrays.sort(coorrinateY);
		int medianY=getMedian(coorrinateY);
		
		Arrays.sort(coordinateX);
		for (int i = 0; i < coordinateX.length; i++) {
			coordinateX[i]=coordinateX[i]-i;
		}
		Arrays.sort(coordinateX);
		int medianX=getMedian(coordinateX);
		
		
		
	}

}
