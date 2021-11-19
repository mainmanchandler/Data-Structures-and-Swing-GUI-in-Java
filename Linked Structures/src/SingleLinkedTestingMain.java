/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author Chandler Mayberry
 * @version 2021-02-05
 */
public class SingleLinkedTestingMain {

	/**
	 * Note that not all the data structure methods are called in this main. The
	 * main method is just an illustration of how you may test your code. Test your
	 * code thoroughly.
	 *
	 * When you start, comment out all code in the main, and un-comment as you add
	 * code to the class.
	 *
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		final String LINE = new String(new char[40]).replace("\0", "-");


		/* -----Test SingleList----- */
		SingleList<Integer> list = new SingleList<>();
		System.out.println(LINE);
		System.out.println("SingleList");
		System.out.println("Empty: " + list.isEmpty());
		System.out.println("Add values:");

		for (int i = 0; i < 6; i++) {
			list.append(i);
		}
		System.out.println("Empty: " + list.isEmpty());
		System.out.println("Peek: " + list.peek());

		System.out.println("\n---REMOVE TESTING---: ");

		System.out.println("\nPrint out List: ");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\n");

		System.out.println("Remove 0 from the list: " + list.remove(0));
		System.out.println("Remove 4 from the list: " + list.remove(4));
		System.out.println("Remove 15 (doesnt exist) from the list: " + list.remove(15));
		System.out.println("Remove 5 from the list: " + list.remove(5));

		System.out.println("\nPrint out List:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}
		System.out.println("\n\nClear list:");

		while (!list.isEmpty()) {
			System.out.println("Removed: " + list.removeFront());
		}

		System.out.println("\nPrint out List:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}
		System.out.println("\n");


		//////////////
		// testing clean/ insert
		System.out.println("\n--CLEAN/INSERT/COUNT/FIND/PREPEND--");

		for (int i = 0; i < 6; i++) {
			list.append(i);
		}

		list.append(8);
		list.insert(0, 3); // index first
		list.insert(3, 3); // index middle
		list.insert(5, 3); // index last
		list.insert(6, 3); // index last+1
		list.insert(100, 3); // index overflow
		list.prepend(15);

		System.out.println("Count how many 3's: " + list.count(3));

		System.out.println("Find 8: " + list.find(8));

		System.out.println("Print out List:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		list.clean();
		System.out.println("\nPrint out List:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		// testing split
		System.out.println("\n\n--SPLIT--");
		SingleList<Integer> left = new SingleList<>();
		SingleList<Integer> right = new SingleList<>();

		list.split(left, right);

		System.out.println("\nPrint out source:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\nPrint out left:");
		for (Integer data : left) {
			System.out.print(" " + data);
		}

		System.out.println("\nPrint out right:");
		for (Integer data : right) {
			System.out.print(" " + data);
		}

		// testing combine
		System.out.println("\n\n--COMBINE--");

		list.combine(left, right);

		System.out.println("\nPrint out source:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\nPrint out left:");
		for (Integer data : left) {
			System.out.print(" " + data);
		}

		System.out.println("Print out right:");
		for (Integer data : right) {
			System.out.print(" " + data);
		}

		// testing split alternate
		System.out.println("\n--SPLITALTERNATE--");
		list.splitAlternate(left, right);

		System.out.println("\nPrint out source:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\nPrint out left:");
		for (Integer data : left) {
			System.out.print(" " + data);
		}

		System.out.println("\nPrint out right:");
		for (Integer data : right) {
			System.out.print(" " + data);
		}


		// testing contains
		System.out.println("\n\n--CONTAINS (combined again)--");
		list.combine(left, right);

		boolean output = list.contains(8);
		System.out.println("\nContains 8: " + output);
		output = list.contains(200);
		System.out.println("\nContains 200: " + output);


		// testing get
		System.out.println("\n--GET--:");
		System.out.println("Source:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}
		System.out.println("\n index 0, 3, 6, !100:" + list.get(0) + list.get(3) + list.get(6));// + list.get(100));


		// testing identical
		System.out.println("\n--IDENTICAL--");
		SingleList<Integer> identical1 = new SingleList<>();
		SingleList<Integer> identical2 = new SingleList<>();
		for (int i = 0; i < 6; i++) {
			identical1.append(i);
			identical2.append(i);
		}

		System.out.println("identical: " + identical1.identical(identical2));
		identical1.append(2323);
		System.out.println("not identical: " + identical1.identical(identical2));


		// testing index
		System.out.println("\n--INDEX--");
		System.out.println("index of 3: " + list.index(3));
		System.out.println("index of 23223: " + list.index(23223));


		// testing max
		System.out.println("\n--MAX--");
		list.insert(5, 50);
		System.out.println("max: " + list.max());


		// testing min
		System.out.println("\n--MIN--");
		System.out.println("min: " + list.min());

		// testing reverse
		System.out.println("\n--REVERSE--");
		System.out.println("Source:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		list.reverse();

		System.out.println("\nSource Reversed:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		// testing union
		System.out.println("\n--UNION--");

		SingleList<Integer> left2 = new SingleList<>();
		SingleList<Integer> right2 = new SingleList<>();

		list.split(left2, right2);


		System.out.println("\nSource after split:");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\nleft before union: ");
		for (Integer data : left2) {
			System.out.print(" " + data);
		}

		System.out.println("\nright before union: ");
		for (Integer data : right2) {
			System.out.print(" " + data);
		}


		list.union(left2, right2);

		System.out.println("\n\n!!Source after union:!!");
		for (Integer data : list) {
			System.out.print(" " + data);
		}

		System.out.println("\nleft after union: ");
		for (Integer data : left2) {
			System.out.print(" " + data);
		}

		System.out.println("\nright after union: ");
		for (Integer data : right2) {
			System.out.print(" " + data);
		}

		System.out.println("\n" + LINE);


		/* -----Test SingleStack----- */
		SingleStack<Integer> stack = new SingleStack<>();
		System.out.println(LINE);
		System.out.println("SingleStack");
		System.out.println("Empty: " + stack.isEmpty());
		System.out.println("Add values: ");

		for (int i = 0; i < 6; i++) {
			stack.push(i);
		}
		System.out.println("Empty: " + stack.isEmpty());
		System.out.println("Peek: " + stack.peek());
		System.out.println("Clear stack:");

		while (!stack.isEmpty()) {
			System.out.println("Popped: " + stack.pop());
		}


		/* -----Test SingleQueue----- */
		SingleQueue<Integer> queue = new SingleQueue<>();
		System.out.println(LINE);
		System.out.println("SingleQueue");
		System.out.println("Empty: " + queue.isEmpty());
		System.out.println("Add values:");

		for (int i = 0; i < 6; i++) {
			queue.insert(i);
		}
		queue.insert(10);
		System.out.println("Empty: " + queue.isEmpty());
		System.out.println("Peek: " + queue.peek());

		// remove testing
		System.out.println("\n\nQueue before remove: ");
		for (Integer data : queue) {
			System.out.print(" " + data);
		}

		queue.remove();

		System.out.println("\n\nQueue after remove: ");
		for (Integer data : queue) {
			System.out.print(" " + data);
		}

		// split testing
		SingleQueue<Integer> leftq = new SingleQueue<>();
		SingleQueue<Integer> rightq = new SingleQueue<>();

		queue.split(leftq, rightq);

		System.out.println("\n\nQueue after split: ");
		for (Integer data : queue) {
			System.out.print(" " + data);
		}
		System.out.println("\n\nleftq after split: ");
		for (Integer data : leftq) {
			System.out.print(" " + data);
		}
		System.out.println("\n\nrightq after split: ");
		for (Integer data : rightq) {
			System.out.print(" " + data);
		}


		// combine testing
		queue.combine(leftq, rightq);

		System.out.println("\n\n!!Queue after combine:!! ");
		for (Integer data : queue) {
			System.out.print(" " + data);
		}
		System.out.println("\n\nleftq after combine: ");
		for (Integer data : leftq) {
			System.out.print(" " + data);
		}
		System.out.println("\nrightq after combine: ");
		for (Integer data : rightq) {
			System.out.print(" " + data);
		}


		/* -----Test SinglePriorityQueue----- */
		SinglePriorityQueue<Integer> pq = new SinglePriorityQueue<>();
		System.out.println("\n" + LINE);
		System.out.println("SinglePriorityQueue");
		System.out.println("Empty: " + pq.isEmpty());
		System.out.println("Add values:");

		for (int i = 5; i >= 0; i--) {
			pq.insert(i);
		}
		pq.insert(2);

		System.out.println("Empty: " + pq.isEmpty());
		System.out.println("Peek: " + pq.peek());

		System.out.println("\n PQ before clear: ");
		for (Integer data : pq) {
			System.out.print(" " + data);
		}

		System.out.println("\n\nClear queue:");

		// testing remove
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());
		System.out.println("Removed:" + pq.remove());


		// testing splitbykey
		SinglePriorityQueue<Integer> higher = new SinglePriorityQueue<>();
		SinglePriorityQueue<Integer> lower = new SinglePriorityQueue<>();

		pq.splitByKey(23, higher, lower); // do nothing

		for (int i = 10; i >= 1; i--) {
			pq.insert(i);
		}
		System.out.println("\n PQ before splitbykey: ");
		for (Integer data : pq) {
			System.out.print(" " + data);
		}

		/*
		 * TESTING WHEN KEY IS SMALLEST pq.splitByKey(0, higher, lower);
		 * 
		 * System.out.println("\n\n !!PQ after splitbykey smaller:!! "); for (Integer
		 * data : pq) { System.out.print(" " + data); }
		 * 
		 * System.out.println("\n higher after splitbykey smaller: "); for (Integer data
		 * : higher) { System.out.print(" " + data); }
		 * 
		 * System.out.println("\n lower after splitbykey smaller:  "); for (Integer data
		 * : lower) { System.out.print(" " + data); }
		 */

		pq.splitByKey(5, higher, lower);

		System.out.println("\n\n !!PQ after splitbykey middle:!! ");
		for (Integer data : pq) {
			System.out.print(" " + data);
		}

		System.out.println("\n higher after splitbykey middle: ");
		for (Integer data : higher) {
			System.out.print(" " + data);
		}

		System.out.println("\n lower after splitbykey middle:  ");
		for (Integer data : lower) {
			System.out.print(" " + data);
		}

	}


}
