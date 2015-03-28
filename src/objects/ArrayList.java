package objects;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class has been developed following the lesson 6 and the Javadoc.
 * I developed everything myself but Google helped me a lot to find the methods from java.util.Arrays
 * and for the constructor with a Collection in param.
 * And I did copy/paste my Javadoc from the official Javadoc.
 * 
 * 
 * @author Rémi KOCI
 *
 */

public class ArrayList <T> implements Iterable<T>{

	static final int DEFAULT_CAPACITY = 10;
	private T[] data ;
	private int size = 0;
	
	
	
	/**
	 * Constructs an empty list with the value of the constant DEFAULT_CAPACITY as initial capacity.
	 * I had to remove the warnings to tell the compiler that I know what I'm doing with
	 * my casts in the three constructors.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList () { data = (T []) new Object [ DEFAULT_CAPACITY ]; }

	
	
	
	
	/**
	 * Constructs an empty list with the specified initial capacity.
	 * 
	 * @param capacity - the initial capacity of the list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList (int capacity) { 
		if (capacity < 0)
			throw new IllegalArgumentException("The specified capacity cannot be negative.");
		data = (T []) new Object [ capacity ];
	}
	
	

	
	
	/**
	 * Constructs a list containing the elements of the specified collection,
	 * in the order they are returned by the collection's iterator.
	 * 
	 * @param collection - the collection whose elements are to be placed into this list
	 * @throws NullPointerException if the specified collection is empty
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(Collection<? extends T> collection){
		if(collection.isEmpty())
			throw new NullPointerException("The specified collection cannot be null.");
		else
			data = (T[]) collection.toArray();
			size = data.length;
			if (data.getClass() != Object[].class)
				data = (T[]) Arrays.copyOf(data, size, Object[].class);
	}
	

	
	
	
	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return Returns the number of elements in this list.
	 */
	public int size () { return size ; }


	
	
	
	/**
	 * Increases the capacity of this ArrayList instance, if necessary,
	 * to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
	 * 
	 * @param minCapacity - the desired minimum capacity
	 */
	private void ensureCapacity(int minCapacity) {
		// TODO Auto-generated method stub
		if(minCapacity > size){
			data = Arrays.copyOf(data, minCapacity);
		}
	}
	
	
	
	
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param - element the element to be appended to this list. 
	 * @return true (as specified by Collection.add(E)).
	 */
	public boolean add (T element) {
		ensureCapacity(size+1);
		data [ size ] = element ;
		++ size ;
		return true;
	}
	
	
	
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 * 
	 * @param index - index at which the specified element is to be inserted 
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
	 */
	public void add(int index, T element) {
		checkRange(index);
		ensureCapacity(size + 1);
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;
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
	
	
	
	
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index - index of the element to return
	 * @return IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	 */
	public T get ( int index) {
		checkRange(index);
		return data [index];
	}
	
	
	
	
	
	/**
	 * Returns the index of the first occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.
	 * More formally, returns the lowest index i
	 * such that (o==null ? get(i)==null : o.equals(get(i))),
	 * or -1 if there is no such index.
	 * 
	 * @param element - element to search for
	 * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	public int indexOf ( T element) {
		int index = -1;
		for(int i=0; i<size; i++)
			if(data[i].equals(element))
				index = i;
		return index;
	}
	
	
	
	
	/**
	 * Removes the element at the specified position in this list.
	 * Shifts any subsequent elements to the left (subtracts one from their indices).
	 * 
	 * @param index - the index of the element to be removed
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	 */
	public T remove(int index) {
		checkRange(index);
		T oldValue = data[index];
		int indexOldValue = size - index - 1;
		if (indexOldValue > 0)
			System.arraycopy(data, index+1, data, index, indexOldValue);
		data[--size] = null;
		return oldValue;
	}
	
	
	
	
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * If the list does not contain the element, it is unchanged.
	 * More formally, removes the element with the lowest index i
	 * such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	 * Returns true if this list contained the specified element
	 * (or equivalently, if this list changed as a result of the call).
	 * 
	 * @param element - element to be removed from this list, if present
	 * @return true if this list contained the specified element
	 */
	public boolean remove ( T element) {
		boolean removed = false;
		for (int index = 0; index < size; index++)
			if (element.equals(data[index])) {
				remove(index);
				removed = true;
			}
		return removed;
	}
	
	public Iterator<T> iterator() {
		return new IteratorArrayList();
	}
	
	
	public class IteratorArrayList implements Iterator<T> {
	
		public int indexCursor;   
		
		public IteratorArrayList(){
			indexCursor=0;
		}
		
		public boolean hasNext() {
			return (indexCursor != size);
		}
		
		public T next() {
			 if (indexCursor >= size)
				 throw new NoSuchElementException();
			 return data[indexCursor++];
		}
	}
}
