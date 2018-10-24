package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

public class GrowableList<T> implements P6List<T> {
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[32];
		this.fill = 0;
	}

	@Override
	public T removeFront() {
		throw new P6NotImplemented();
	}

	@Override
	public T removeBack() {
		throw new P6NotImplemented();
	}

	@Override
	public T removeIndex(int index) {
		throw new P6NotImplemented();
	}

	@Override
	public void addFront(T item) {
		throw new P6NotImplemented();
	}

	@Override
	public void addBack(T item) {
		// I've implemented part of this for you.
		if (fill >= this.array.length) { 
			throw new P6NotImplemented();
		}
		this.array[fill++] = item;
	}

	@Override
	public void addIndex(T item, int index) {
		throw new P6NotImplemented();
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
	public int size() {
		return fill;
	}

	@Override
	public boolean isEmpty() {
		return fill == 0;
	}

}
