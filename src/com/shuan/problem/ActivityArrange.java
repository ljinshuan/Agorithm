/**
 * 
 */
package com.shuan.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 * 活动安排
 * 
 * 典型贪心算法
 */
public class ActivityArrange {

	public static List<ClassRoom> makeArrange(List<Activity> activities){
		//按照开始时间排序
		Collections.sort(activities, new Comparator<Activity>() {

			@Override
			public int compare(Activity o1, Activity o2) {
				return o1.startTime<=o2.startTime?-1:1;
			}
		});
		List<ClassRoom> rooms=new ArrayList<ClassRoom>();
		ClassRoom currentRoom=null;
		for (Activity activity : activities) {
			//找到一个合适的教室
			currentRoom=null;
			for (ClassRoom classRoom : rooms) {
				if (classRoom.endTime<=activity.startTime) {
					currentRoom=classRoom;
					break;
				}
			}
			if (currentRoom==null) {
				currentRoom=new ClassRoom();
				rooms.add(currentRoom);
			}
			//将活动加入到教室
			currentRoom.activities.add(activity);
			currentRoom.endTime=activity.endTime;
		}
		
		return rooms;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//测试数据
		int [] startTime=CommonUtil.InitRandomArray(100, 20);
		int [] duringTime=CommonUtil.InitRandomArray(30, 20);
		
		int [] endTime=Arrays.copyOf(startTime, startTime.length);
		List<Activity> activities=new ArrayList<Activity>();
		for(int i=0;i<duringTime.length;i++){
			endTime[i]+=duringTime[i];
			activities.add(new Activity(startTime[i],endTime[i] ));
		}
		
		
		List<ClassRoom> rooms=makeArrange(activities);
		
		for (ClassRoom classRoom : rooms) {
			System.out.println(classRoom);
		}
	}

}
class Activity{
	public int id;
	public int startTime;
	public int endTime;
	
	public Activity(int startTime,int endTime){
		this.startTime=startTime;
		this.endTime=endTime;
		this.id=++Activity.Id;
	}
	public static int Id=0;
}
class ClassRoom{
	public int Id;
	public List<Activity> activities=new ArrayList<Activity>();
	public int endTime=0;
	
	public ClassRoom(){
		this.Id=++RoomId;
	}
	public static int RoomId=0;
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("RoomId:"+this.Id+"\nActivities:\n");
		for (Activity activity : activities) {
			sb.append(String.format("Id:%d startTime:%d endTime:%d \n", activity.id,activity.startTime,activity.endTime));
		}
		return sb.toString();
	}
}
