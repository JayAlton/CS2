package MyLinkedList;
import java.lang.AssertionError;

public class MyLinkedList <T> {
	transient Node begin;
	transient Node end;
	int size;
	class Node {
		// FIXME add member variables
		T data;
		Node next;
		Node previous;
		
		Node(T data) { // Node inherits T from MyLinkedList
			this.data = data;
		}
	}

	// FIXME add member variables

	/**
	 * Construct an MyLinkedList.
	 */
	public MyLinkedList() {
		this.size = 0;
	}

	/**
	 * Return the number of elements in the MyLinkedList.
	 *
	 * @return The number of elements in the MyLinkedList.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Add an element to the end of the MyLinkedList.
	 *
	 * @param element The element to add.
	 */
	public void add(T element) {
		Node newNode = new Node(element);
		this.size++;
		if(this.begin == null) {
			this.begin = newNode;
			this.end = newNode;
		} else {
			this.end.next = newNode;
			newNode.previous = this.end;
			this.end = newNode;
		}
	}

	/**
	 * Get the element at the specified index.
	 *
	 * This function assumes that the index argument is within range of the MyLinkedList.
	 *
	 * @param index The index to get.
	 * @return The element at the specified index.
	 */
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node returnNode;
		int i = 0;
		returnNode = this.begin;
		while(i != index) {
			i++;
			returnNode = returnNode.next;
		}
		if(returnNode == null) {
			return null;
		}
		return returnNode.data;
	}

	/**
	 * Remove the element at the specified index.
	 *
	 * This function assumes that the index argument is within range of the MyLinkedList.
	 *
	 * @param index The index to remove.
	 */
	public void remove(int index) {
		// FIXME
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node curr = this.begin;
		if(index == 0) {
			this.begin = curr.next;
			if(this.begin != null) {
				this.begin.previous = null;
			}
			this.size--;
			return;
		}
		for(int i = 0; i < index - 1 && curr != null; i++) {
			curr = curr.next;
		}
		if(curr == null || curr.next == null) {
			return;
		}
		Node nextNode = curr.next.next;
		curr.next = nextNode;
		if(nextNode != null) {
			nextNode.previous = curr;
		}
		this.size--;
		curr = this.begin;
		for(int i = 0; i < this.size()-1; i++) {
			curr = curr.next;
		}
		this.end = curr;
	}

	/**
	 * Create a String representation of the MyLinkedList.
	 *
	 * @return A String representation of the MyLinkedList.
	 */
	public String toString() {
		String result = "{";
		if (this.size() > 0) {
			result += this.get(0);
		}
		for (int i = 1; i < this.size; i++) {
			result += ", " + this.get(i);
		}
		result += "}";
		return result;
	}

	/**
	 * Check that an MyLinkedList contains the same elements as an int array.
	 *
	 * If the list and the array are not the same, throw an AssertionError.
	 *
	 * @param list The MyLinkedList to check.
	 * @param answer The expected answer, in the form of an int array.
	 */
	public static void assertArraysEqual(MyLinkedList list, int[] answer) {
		if (list.size() != answer.length) {
			throw new AssertionError("Expected list of length " + answer.length + " but got " + list.size());
		}
		for (int i = 0; i < answer.length; i++) {
			if ((Integer)list.get(i) != answer[i]) {
				throw new AssertionError("Expected " + answer[i] + " but got " + list.get(i) + " at index " + i);
			}
		}
	}

	/*
	 * Test that the empty arraylist has size 0.
	 */
	public static void test1() {
		MyLinkedList<Integer> list = new MyLinkedList();
		int[] answer = new int[0];
		assertArraysEqual(list, answer);
	}

	/*
	 * Test insertion into an arraylist (without resizing).
	 */
	public static void test2() {
		MyLinkedList list = new MyLinkedList();
		for (int i = 0; i < 3; i++) {
			list.add(i * i);
		}
		int[] answer = {0, 1, 4};
		assertArraysEqual(list, answer);
	}

	/*
	 * Test deletion from an arraylist without emptying it.
	 */
	public static void test3() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}
		list.remove(1);
		list.remove(2);
		int[] answer = {0, 4, 16};
		MyLinkedList.assertArraysEqual(list, answer);
	}

	/*
	 * Test deletion from an arraylist and emptying it.
	 */
	public static void test4() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}

		list.remove(1);
		list.remove(2);

		// delete the final remaining numbers
		list.remove(0);
		list.remove(0);
		list.remove(0);
		int[] answer1 = {};
		MyLinkedList.assertArraysEqual(list, answer1);

		// check that there are no last-element issues
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}
		list.remove(4);
		list.add(-1);
		int[] answer2 = {0, 1, 4, 9, -1};
		MyLinkedList.assertArraysEqual(list, answer2);
	}

	/*
	 * Test insertion into an arraylist (with resizing).
	 */
	public static void test5() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 12; i++) {
			list.add(i * i);
		}
		int[] answer = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121};
		MyLinkedList.assertArraysEqual(list, answer);
	}

	/**
	 * Put the MyLinkedList through some simple tests.
	 *
	 * @param args Ignored command line arguments.
	 */
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();

		System.out.println("pass");
	}

}
