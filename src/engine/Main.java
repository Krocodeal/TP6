package engine;

import java.util.Arrays;

import objects.ArrayList;
import objects.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		//testArrayList();
		//testLinkedList();
		
	}
	
	
	
	public static void testArrayList(){
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("Hello","Talbot"));
		printAll(list);
		System.out.println(list.size());
		list.add(1,"Pierre");
		list.add("!");
		printAll(list);
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.indexOf("Pierre"));
		list.remove(1);
		list.remove("Talbot");
		System.out.println(list.size());
		printAll(list);
		
	}
	
	/**
	 * Prints every elements of the list separated with a white spaces.
	 * As it wasn't in Javadoc, I didn't add it in my ArrayList class.
	 * 
	 * @param list - an ArrayList Of Type T
	 */
	public static  void printAll(ArrayList<String> list){		for (String s : list){
			System.out.print(s + " ");
		}
		System.out.println();
	}
	
	public static void testLinkedList(){
		
		LinkedList<String> list = new LinkedList<String>(Arrays.asList("Pierre","Talbot"));
		printAll(list);
		System.out.println(list.size());
		list.addFirst("Hello");
		list.addLast("!");
		printAll(list);
		System.out.println(list.size());		
		System.out.println(list.pop());
		System.out.println(list.poll());
		printAll(list);
		list.addFirst("Monsieur");
		System.out.println(list.peekFirst());
		System.out.println(list.peekLast());
		System.out.println(list.size());
		printAll(list);
		System.out.println(list.remove("Talbot"));
		System.out.println(list.size());		
		list.addFirst("Hello");
		System.out.println(list.remove(1));
		printAll(list);
		
	}
	
	
	/**
	 * Prints every elements of the list separated with a white spaces.
	 * As it wasn't in Javadoc, I didn't add it in my ArrayList class.
	 * 
	 * @param list - an LinkedList Of Type T
	 */
	public static <T> void printAll(LinkedList<String> list){
		for (String s : list){
			System.out.print(s + " ");
		}
		System.out.println();
	}
}
