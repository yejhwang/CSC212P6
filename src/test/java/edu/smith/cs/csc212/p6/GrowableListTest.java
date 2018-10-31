package edu.smith.cs.csc212.p6;

import org.junit.Test;

import edu.smith.cs.csc212.p6.errors.EmptyListError;

import org.junit.Assert;
//NEXT TIME EXTEND ONE OF THE TESTS? OR COPY AND REPLACE NAMES
public class GrowableListTest {
	@Test
	public void testEmpty() {
		GrowableList<String> data = new GrowableList<String>();
		Assert.assertEquals(0, data.getFill());
	}
	
	@Test(expected = EmptyListError.class)
	public void testRemoveFrontCrash() {
		P6List<String> data = new GrowableList<String>();
		data.removeFront();
	}
	
	@Test(expected = EmptyListError.class)
	public void testRemoveBackCrash() {
		P6List<String> data = new GrowableList<String>();
		data.removeBack();
	}
	
	@Test(expected = EmptyListError.class) 
	public void testRemoveIndexCrash() {
		P6List<String> data = new GrowableList<String>();
		data.removeIndex(3);
	}
	
	@Test
	public void testAddToFront() {
		GrowableList<String> data = new GrowableList<String>();
		data.addFront("1");
		Assert.assertEquals(1, data.getFill());
		Assert.assertEquals("1", data.getIndex(0));
		data.addFront("0");
		Assert.assertEquals(2, data.getFill());
		Assert.assertEquals("0", data.getIndex(0));
		Assert.assertEquals("1", data.getIndex(1));
		data.addFront("-1");
		Assert.assertEquals(3, data.getFill());
		Assert.assertEquals("-1", data.getIndex(0));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(2));
		data.addFront("-2");
		Assert.assertEquals("-2", data.getIndex(0));
		Assert.assertEquals("-1", data.getIndex(1));
		Assert.assertEquals("0", data.getIndex(2));
		Assert.assertEquals("1", data.getIndex(3));
	}

	@Test
	public void testAddToBack() {
		GrowableList<String> data = new GrowableList<String>();
		data.addBack("1");
		Assert.assertEquals(1, data.getFill());
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("0");
		Assert.assertEquals(2, data.getFill());
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-1");
		Assert.assertEquals(3, data.getFill());
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-2");
		Assert.assertEquals("-2", data.getIndex(3));
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
	}
	
	/**
	 * Helper method to make a full list.
	 * @return
	 */
	public GrowableList<String> makeNotFullList() {
		GrowableList<String> data = new GrowableList<String>();
		data.addBack("a");
		data.addBack("b");
		data.addBack("c");
		data.addBack("d");
		return data;
	}

	//Growable lists don't run out of space.
	//Those tests have been removed.
	
	@Test
	public void testRemoveFront() {
		GrowableList<String> data = makeNotFullList();
		Assert.assertEquals(4, data.getFill());
		Assert.assertEquals("a", data.removeFront());
		Assert.assertEquals(3, data.getFill());
		Assert.assertEquals("b", data.removeFront());
		Assert.assertEquals(2, data.getFill());
		Assert.assertEquals("c", data.removeFront());
		Assert.assertEquals(1, data.getFill());
		Assert.assertEquals("d", data.removeFront());
		Assert.assertEquals(0, data.getFill());
	}
	@Test
	public void testRemoveBack() {
		GrowableList<String> data = makeNotFullList();
		Assert.assertEquals(4, data.getFill());
		Assert.assertEquals("d", data.removeBack());
		Assert.assertEquals(3, data.getFill());
		Assert.assertEquals("c", data.removeBack());
		Assert.assertEquals(2, data.getFill());
		Assert.assertEquals("b", data.removeBack());
		Assert.assertEquals(1, data.getFill());
		Assert.assertEquals("a", data.removeBack());
		Assert.assertEquals(0, data.getFill());
	}
	
	@Test
	public void testRemoveIndex() {
		GrowableList<String> data = makeNotFullList();
		Assert.assertEquals(4, data.getFill());
		Assert.assertEquals("c", data.removeIndex(2));
		Assert.assertEquals(3, data.getFill());
		Assert.assertEquals("d", data.removeIndex(2));
		Assert.assertEquals(2, data.getFill());
		Assert.assertEquals("b", data.removeIndex(1));
		Assert.assertEquals(1, data.getFill());
		Assert.assertEquals("a", data.removeIndex(0));
		Assert.assertEquals(0, data.getFill());
	}
	//Additional Tests
	@Test
	public void testAddIndex() {
		GrowableList<String> data = makeNotFullList();		
		Assert.assertEquals(4, data.getFill());
		data.addIndex("q", 2);		
		Assert.assertEquals(5, data.getFill());
		Assert.assertEquals("q", data.getIndex(2));		
		Assert.assertEquals("c", data.getIndex(3));
	}
	
	@Test
	public void testGetIndex() {
		GrowableList<String> data = makeNotFullList();
		Assert.assertEquals(4, data.getFill());
		Assert.assertEquals("c", data.getIndex(2));
	}
	
	@Test
	public void testSize() {
		P6List<String> data = makeNotFullList();
		Assert.assertEquals(32, data.size());
	}
	
	@Test
	public void testGetFill() {
		GrowableList<String> data = makeNotFullList();
		Assert.assertEquals(4, data.getFill());
	}
	
	@Test
	public void testIsEmpty() {
		P6List<String> data = new FixedSizeList<String>(0);
		Assert.assertEquals(true, data.isEmpty());

		data = makeNotFullList();
		Assert.assertEquals(false, data.isEmpty());
	}
	
	@Test
	public void testGetFront() {
		P6List<String> data = makeNotFullList();
		Assert.assertEquals("a", data.getFront());
	}
	
	@Test
	public void testGetBack() {
		P6List<String> data = makeNotFullList();
		Assert.assertEquals("d", data.getBack());
	}

	@Test
	public void testGrowReduce() {
		P6List<String> data = new GrowableList<String>();
		Assert.assertEquals(32, data.size());
		//Testing Grow ----------------------------------
		int bigger = data.size() + 10;
		for(int i = 0; i <bigger; i++) {
			data.addBack("a"); 
		}
		Assert.assertEquals(64, data.size());
		bigger += 40;
		for(int i = 42; i <bigger; i++) {
			data.addFront("b"); //TODO: add index
		}
		Assert.assertEquals(128, data.size());
		for(int i = 82; i <131; i++) {
			data.addIndex("c", i);
		}
		Assert.assertEquals(256, data.size());
		//Testing Reduce --------------------------------
		for(int i = 130; i>121; i--) {
			data.removeFront();
		}
		Assert.assertEquals(128, data.size());
		for(int i = 120; i>51; i--) {
			data.removeBack();
		}
		Assert.assertEquals(64, data.size());
		//50 of 64 slots filled up to this point
		for(int i = 5; i<36; i++) {
			data.removeIndex(5);
		}
		Assert.assertEquals(32, data.size());
	}
	


}
