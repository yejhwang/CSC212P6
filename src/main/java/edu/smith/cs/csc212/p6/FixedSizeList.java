package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

public class FixedSizeList<T> implements P6List<T> {
	private Object[] array;
	private int fill;
	
	public FixedSizeList(int maximumSize) {
		this.array = new Object[maximumSize];
		this.fill = 0;
	}

	@Override
	public T removeFront() {
		return removeIndex(0);
	}

	@Override
	public T removeBack() {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T value = this.getIndex(fill-1); //subtract 1 for size because used as index.
		fill--;		
		this.array[fill] = null; //don't have to subtract 1 here bc fill-- was implemented prior.
		return value;
	}

	@Override
	public T removeIndex(int index) {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}

	@Override
	public void addFront(T item) {
		addIndex(item, 0);		
	}

	@Override
	public void addBack(T item) {
		if (fill < array.length) {
			array[fill++] = item;
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public void addIndex(T item, int index) {
		if (fill >= array.length) {
			throw new RanOutOfSpaceError();
		}
		// loop backwards, shifting items to the right.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) {
		if (index < 0 || index >= fill) {
			throw new BadIndexError();
		}
		return (T) this.array[index];
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public T getFront() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(0);
	}

	@Override
	public T getBack() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(this.size()-1);
	}
}
