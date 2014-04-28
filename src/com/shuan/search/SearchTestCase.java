package com.shuan.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shuan.sort.Sort;
import com.shuan.util.CommonUtil;

public class SearchTestCase {

	int [] data;
	int key;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		data=CommonUtil.InitRandomArray(20);
		key=data[9];
	}

	@Test
	public void testSequenceSearch() {
		
		int index=Search.SequenceSearch(data, key);
		assertTrue(index>=0);
	}

	@Test
	public void testBinarySearch() {
		Sort.QuickSort(data, 0, data.length-1);
		int index=Search.BinarySearch(data, key);
		assertTrue(index>=0);
	}

}
