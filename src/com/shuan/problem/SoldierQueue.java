/**
 * 
 */
package com.shuan.problem;

import java.util.Arrays;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 *��һ�����ֳ�����Ĳٳ��ϣ�n��ʿ��ɢ�ҵ�վ��������ϡ����������������(x,y)��ʾ��ʿ���ǿ�����������ϡ��¡������ƶ�һ����
 *����ͬһʱ����һ�������ֻ����һ��ʿ�������վ��ٵ����ʿ����Ҫ������г�һ��ˮƽ���У������г�(x,y),(x+1,y),��,(x+n-1,y)��
 *���ѡ��x��y��ֵ����ʹʿ���������ٵ����ƶ������ų�һ�С�
 *
 *Answer �ֱ���X��Y����λ��
 * 
���������X[]�����������Y[]��Y[]ȡ��λ�����������X[]ÿһ������������Ա任����ȥi),�ٶ��µ�X[]������ȡ��X[]����λ����
 */
public class SoldierQueue {

	public static int getMedian(int [] data){
		if(data.length%2==0){
			//ż��
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
		
		//�õ�Y����λ��
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
