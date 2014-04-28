package com.shuan.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shuan.util.CommonUtil;

public class BSTreeTestCase {

	private int [] data;
	private BSTree root;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		data=CommonUtil.InitRandomArray(20);
		root=BSTree.Create(data);
	}

	@Test
	public void testSearch() {
		int key=data[10];
		BSTree node=root.Search(key);
		assertEquals(key,node.data);
	}

	@Test
	public void testRemove() {
		BSTree node=root.leftChild;
		node=null;
		assertNotNull(root.leftChild);
	}

	@Test
	public void testCreate() {
		System.out.println(root.data);
	}
}
