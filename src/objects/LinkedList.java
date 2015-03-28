package objects;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Once again, I developped everything myself. I just was lazy on the Javadoc
 * as it (should be) is exactly the same as the original.
 * 
 * I wasn't sure that the "head" could countain a data but I wasn't able to do otherwise. 
 * 
 * @author Rémi KOCI
 */

public class LinkedList<T> implements Iterable<T>{
	private Node <T > head;
	private int size;
	
	@SuppressWarnings("hiding")
	class Node <T > {
		private T data;
		private Node<T> next;
		public Node <T> previous;

		public Node (T element) { 
			this.data = element; 
		}
		
	}

	public LinkedList() {
	}
	
	/**
	 * Bonus method :D
	 * 
	 * Constructs a list containing the elements of the specified
	 * collection, in the order they are returned by the collection's iterator.
	 * 
	 * @param  collection the collection whose elements are to be placed into this list
	 * @throws NullPointerException if the specified collection is null
	 */
	public LinkedList(Collection<? extends T> collection) {
		ArrayList<T> list;
		if(collection.isEmpty())
			throw new NullPointerException("The specified collection cannot be null.");
		else
			list = new ArrayList<T>(collection);
			for (int i=list.size()-1; i>=0; i--){ // Reverse list for addFirst :D
				addFirst(list.get(i));
			}
	}
	
	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */
	public int size(){
		return size;
	}
	
	
	/**
	 * Inserts the specified element at the beginning of this list.
	 *  
	 * @param elementData - the element to add
	 */
	public void addFirst(T elementData) {
		Node<T> element = new Node<T>(elementData);

		if (size == 0){
			head = element;
		}	        
		else{
			element.next=head;
			head.previous = element;
			head = element;
		}
		size++;
	}
	
	public void addLast(T elementData) {
		Node<T> element = new Node<T>(elementData);

		if (size == 0){
			head = element;
		}	        
		else{
			Node<T> currentLastNode = this.getNode(size-1);
			currentLastNode.next=element;
			element.previous=currentLastNode;
		}
		size++;
	}
	
	/**
	 * Checks if the index is out of range.
	 * 
	 * @param index - specified by user
	 */
	private void checkRange (int index){
		if(index < 0 || index >= size ) 
			throw new IndexOutOfBoundsException("Index out of range. Index: "+ index +", Size: "+size);
	}
	
	Node<T> getNode(int index) {		
		Node<T> element;
		element = head;
		while (index > 0){
			element = element.next;
			index--;
		}
		return element;		    
	}
	
	public T get(int index){
		checkRange(index);
		return getNode(index).data;
	}
	
	public T pop() {
		if (size == 0)
			throw new NoSuchElementException();
		size--;
		T elementData = head.data;
		head = head.next;
		return elementData;
	}
	
	public T poll() {
		if (size == 0)
			return null;
		return pop();
	}
	
	public T peekFirst(){
		if (size == 0)
			return null;
		else
			return head.data;
	}
	
	public T peekLast() {
		if (size == 0)
			return null;
		return getNode(size-1).data;
	}
	
	public boolean remove(T elementData){
		boolean removed = false;
		for (int index = 0; index < size; index++){
			Node<T> elementCursor=getNode(index);
			if (elementCursor.data.equals(elementData)) {
				removeNode(index);
				removed = true;
			}
		}
		return removed;
	}
	
	void removeNode(int index){
		Node<T> element = getNode(index);
		if(element == head)
			pop();
		else if(index==size-1){
			element.previous.next= element.next;
			size--;
		}else{
			element.previous.next= element.next;
			element.next.previous=element.previous;
			size--;
		}
	}
	

	public T remove(int index) {
		checkRange(index);
		T elementData = getNode(index).data;
		removeNode(index);
		return elementData;
	}
	
	
	public Iterator<T> iterator() {
		return new IteratorLinkedList(head);
	}
	
	public class IteratorLinkedList implements Iterator<T> {

		Node<T> nodeCursor;

		public IteratorLinkedList(Node<T> head){
			nodeCursor = head;
		}

		public boolean hasNext() {
			return (nodeCursor != null);
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Node<T> t_node  = nodeCursor;
			nodeCursor=nodeCursor.next;
			return t_node.data;
		}
	}
}