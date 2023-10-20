package StacksQueuesBinarySearch;
public class MyStack<T> extends MyLinkedList<T> {

	
	/**
	 * Push an element onto the Stack
	 *
	 * @param element The element to push.
	 */
	@Override
	public void push(T element) {
		Node newNode = new Node(element);
		newNode.next = this.head;
		this.head = newNode;
		this.size++;
	}

	/**
	 * Pop an element off the Stack
	 *
	 * @return The popped element
	 */
	@Override
	public T pop() {
		T popElement = this.get(0);
		this.remove(0);
		return popElement;
	}

	/**
	 * Peek at the next element in the Stack
	 *
	 * @return The peeked element
	 */
	@Override
	public T peek() {
		return this.get(this.size());
	}
	
	/**
	 * Put the MyStack through some simple tests.
	 *
	 * @param args Ignored command line arguments.
	 */
	public static void main(String[] args) {

		MyStack<Integer> list = new MyStack();

		// check that an initial arraylist has nothing in it.
		int[] answer1 = new int[0];
		assertIsEqual(list, answer1);

		// push some elements onto the Stack
		for (int i = 0; i < 5; i++) {
			list.push(new Integer (i * i));
		}
		int[] answer2 = {16, 9, 4, 1, 0};
		assertIsEqual(list, answer2);

		// delete some numbers in the middle
		list.remove(new Integer(1));
		list.remove(new Integer(2));
		int[] answer3 = {16, 4, 0};
		assertIsEqual(list, answer3);

		// pop some numbers from the stack
		list.pop();
		list.pop();
		int[] answer4 = {0};
		assertIsEqual(list, answer4);

		// delete the final remaining number
		list.remove(new Integer(0));
		int[] answer5 = {};
		assertIsEqual(list, answer5);

		// if everything is good, we passed
		System.out.println("pass");
	}


}