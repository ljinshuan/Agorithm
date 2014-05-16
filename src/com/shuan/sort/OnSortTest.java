/**
 * 
 */
package com.shuan.sort;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.shuan.util.CommonUtil;

/**
 * @author Shuan
 *
 */
public class OnSortTest {

	private OnSort onSort=new OnSort();
	private int [] data;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		data=CommonUtil.InitRandomArray(20);
	}

	/**
	 * Test method for {@link com.shuan.sort.OnSort#baseSort(int[])}.
	 */
	@Test
	public void testBaseSort() {
		onSort.baseSort(data);
		CommonUtil.PrintArray(data);
	}
	
	@Test
	public void testCounterSort(){
		data=onSort.countSort(data);
		CommonUtil.PrintArray(data);
	}
	@Test
	public void testBucketSort(){
		onSort.bucketSort(data);
		CommonUtil.PrintArray(data);
	}

}
