package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

/**
 * This is a data structure that has an array inside each node of a Linked List.
 * Therefore, we only make new nodes when they are full. Some remove operations
 * may be easier if you allow "chunks" to be partially filled.
 * 
 * @author jfoley
 * @param <T> - the type of item stored in the list.
 */
public class ChunkyLinkedList<T> implements P6List<T> {
	private int chunkSize;
	private SinglyLinkedList<FixedSizeList<T>> chunks;

	public ChunkyLinkedList(int chunkSize) {
		this.chunkSize = chunkSize;
		chunks.addBack(new FixedSizeList<>(chunkSize));
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
		throw new P6NotImplemented();
	}

	@Override
	public void addIndex(T item, int index) {
		throw new P6NotImplemented();
	}
	
	@Override
	public T getFront() {
		throw new P6NotImplemented();
	}

	@Override
	public T getBack() {
		throw new P6NotImplemented();
	}


	@Override
	public T getIndex(int index) {
		throw new P6NotImplemented();
	}

	@Override
	public int size() {
		throw new P6NotImplemented();
	}

	@Override
	public boolean isEmpty() {
		throw new P6NotImplemented();
	}
}
