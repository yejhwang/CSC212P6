package edu.smith.cs.csc212.p6;

import java.util.Arrays;

import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}
	
	/**
	 * Allows "sys.out.println" to print the array.
	 * Used for debugging.
	 */
	public String toString(){
        return Arrays.toString(this.array);
   }
	
	@Override
	public T removeFront() {
		this.reduceIfBig();
		return removeIndex(0);
	}

	@Override
	public T removeBack() {
		if (fill == 0) {
			throw new EmptyListError();
		}
		this.reduceIfBig();
		T value = this.getIndex(fill-1);
		this.array[fill--] = null;
		return value; 
	}

	@Override
	public T removeIndex(int index) {
		if (fill == 0) {
			throw new EmptyListError();
		}
		this.reduceIfBig();
		T removed = this.getIndex(index);
		for (int i=index; i<(fill-1); i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill--] = null;
		return removed;
	}

	@Override
	/**
	 * O(n)
	 */
	public void addFront(T item) { 		
		if (fill >= this.size()) { 
			grow(); 
		}
		for (int i = fill-1; i>-1; i--) {
			this.array[i+1] = this.array[i];
		}
		this.array[0] = item;
		fill++;
		
	}

	@Override
	public void addBack(T item) {
		if (fill >= this.size()) { 
			grow();
		}
		this.array[fill++] = item; //TODO: why not just fill since [] refers to indices?
	}

	@Override
	public void addIndex(T item, int index) { 	
		if (fill >= this.size()) { 
			grow(); 
		}
		for(int i = fill; i>index; i--) {
			this.array[i] = this.array[i-1];
		}
		this.array[index] = item;
		fill++;
	}
	
	@Override
	public T getFront() {
		return this.getIndex(0);
	}

	@Override
	public T getBack() {
		return this.getIndex(this.fill-1);
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) {
		return (T) this.array[index];
	}

	@Override
	/**
	 * Distinguishing this.fill and this.size():
	 * Size of the array is the length or maximum capacity,
	 * and fill is how much of the array is filled.
	 * NOTE: Different from other P6 Lists.
	 * Note: separate getter method for fill created.
	 */
	public int size() {
		return this.array.length;	
	}
	
	public int getFill() {
		return fill;
	}

	@Override
	public boolean isEmpty() {
		return fill == 0;
	}


	/**
	 * O(n)
	 */
	public void grow() {		
		//create array double the size of original.
		 Object[] updArray = new Object[this.size()*2];		 
		 //transfer data
		 for(int i = 0; i<(fill-1); i++) {
			 updArray[i] = this.getIndex(i);
		 }
		 this.array = updArray;
	}
	
	public void reduceIfBig() {
		// Create array half the size of original if too big.
		// Minimum size is 32.
		if(fill<(this.size()/2) && (this.size()>32)) {
			 Object[] updArray = new Object[this.size()/2];		 
			 //transfer data
			 for(int i = 0; i<(fill-1); i++) {
				 updArray[i] = this.getIndex(i);
			 }
			 this.array = updArray;
		}
	}
}
