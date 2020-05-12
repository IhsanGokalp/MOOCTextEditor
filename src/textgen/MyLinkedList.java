package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = null;
		tail  = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element != null){
			size++;
			LLNode<E> addedElem = new LLNode<E>(element);
			if (head == null && tail == null){
				head = addedElem;
				tail = addedElem;
				return true;
			}
			else{
				addedElem.prev = tail;
				tail.next = addedElem;
				tail = addedElem;
				return false;

			}
		}
		else
			throw new NullPointerException();
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		else {
			LLNode<E> result = head;
			int currPos = 0;
			while(currPos != index) {
				result = result.next;
				currPos++;
			}
			return result.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element != null) {
			if ((index >= size && size != 0) || index < 0){
				throw new IndexOutOfBoundsException();
			}
			else if(size == 0 && index == 0){
				LLNode<E> nodeToBeProcessed = new LLNode<E>(element);
				head = nodeToBeProcessed;
				tail = nodeToBeProcessed;
				size++;
			}
			else {
				LLNode<E> node = head;
				int count = -1;
				while (count != index - 1) {
					node = node.next;
					count++;
				}
				count++;
				LLNode<E> nodeToBeProcessed = new LLNode<E>(element);
				if (node.prev != null) {
					node.prev.next = nodeToBeProcessed;
					nodeToBeProcessed.prev = node.prev;
				}
				else {
					nodeToBeProcessed.prev = null;
					head = nodeToBeProcessed;
				}
				node.prev = nodeToBeProcessed;
				nodeToBeProcessed.next = node;
				size++;
			}
		}
		else {
			throw new NullPointerException();
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		E result = null;
		if (index >= size || index <0){
			throw new IndexOutOfBoundsException();
		}
		else {
			LLNode<E> node1 = head.prev;
			LLNode<E> node2 = head;
			LLNode<E> node3 = node2.next;
			int count = 0;
			while (count != index) {
				node1 = node2;
				node2 = node3;
				node3 = node2.next;
				count++;
			}
			if (index == 0){
				head = node3;
				if (node3 != null){
					node3.prev=null;
				}

				node2.next = null;
				node2.prev = null;
				result = node2.data;
			}

			else if (index == size-1){
				tail = node1;
				node1.next = null;
				node2.next = null;
				node2.prev = null;
				result = node2.data;
			}

			else {
				node1.next = node3;
				node3.prev = node1;
				node2.next = null;
				node2.prev = null;
				result = node2.data;
			}
			size--;
		}
		// TODO: Implement this method
		return result;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element != null){
			if (index>=size || index<0) {
				throw new IndexOutOfBoundsException();
			}
			else {
				E result = null;
				int count = 0;
				LLNode<E> dataNode = head;
				while (count != index) {
					dataNode = dataNode.next;
					count++;
				}
				LLNode<E> nodeProcessed = dataNode;
				result = dataNode.data;
				dataNode.data = element;
				return result;
			}
		}
		else {
			throw new NullPointerException();
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
