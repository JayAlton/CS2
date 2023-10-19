package RecursiveList;
public class RecursiveList {

    private class Node {
        int data;
        Node next;

        public Node(int data) {
            // call the other constructor
            this(data, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node head = null;

    public RecursiveList() {
        this.head = null;
    }

    public void buildFromArray(int[] numbers) {
        this.head = null;
        for (int i = numbers.length - 1; i >= 0; i--) {
            this.head = new Node(numbers[i], this.head);
        }
    }

    public int loopSum() {
        Node currNode = this.head;
        int total = 0;
        while (currNode != null) {
            total += currNode.data;
            currNode = currNode.next;
        }
        return total;
    }

    public int recurSum() {
    	int count = 0;
    	if(this.head != null) {
    		count = recurSum(this.head, count);
    	}
        return count; // FIXME for Exploration Q1-6
    }

    public int recurSum(Node currNode, int total) {
    	int count = total + currNode.data;
    	if(currNode.next != null) {
    		count = recurSum(currNode.next, count);
    	}
        return count; // FIXME for Exploration Q1-6
    }

    public int loopSize() {
        Node currNode = this.head;
        int count = 0;
        while (currNode != null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    }

    public int recurSize() {
    	int count = 0;
    	if(this.head != null) {
    		count = recurSize(this.head, count);
    	}
        return count; // FIXME for Exploration Q1-6
    }

    public int recurSize(Node currNode, int total) {
    	int count = total + 1;
    	if(currNode.next != null) {
    		count = recurSize(currNode.next, count);
    	}
        return count;
    }

    public void loopPrint() {
        Node currNode = this.head;
        while (currNode != null) {
            System.out.println(currNode.data);
            currNode = currNode.next;
        }
    }

    public void recurPrint() {
        if(this.head != null) {
        	System.out.println(this.head.data);
        	recurPrint(this.head);
        }
    }

    public void recurPrint(Node currNode) {
    	if(currNode.next != null) {
    		System.out.println(currNode.next.data);
    		recurPrint(currNode.next);
    	}
    }

    public static void main(String[] args) {
        RecursiveList list = new RecursiveList();
        int[] numbers = {4, 8, 15, 16, 23, 42};
        list.buildFromArray(numbers);

        // Exploration Q1-6
        System.out.println("sum:");
        System.out.println(list.loopSum());
        System.out.println(list.recurSum());
        System.out.println();

        // Exploration Q7
        System.out.println("size:");
        System.out.println(list.loopSize());
        System.out.println(list.recurSize());
        System.out.println();

        // Exploration Q8
        System.out.println("print:");
        list.loopPrint();
        System.out.println();
        list.recurPrint();
    }


    /*

    ******************************************************
    * BELOW IS THE SOLUTION TO EXPLORATION QUESTIONS 1-6 *
    *         AVOID LOOKING AT THIS IF POSSIBLE          *
    ******************************************************
    public int recurSum() {
        return this.recurSum(this.head, 0);
    }
    public int recurSum(Node currNode, int total) {
        if (currNode != null) {
            return recurSum(currNode.next, total + currNode.data);
        } else {
            return total;
        }
    }
    */

}