/**
 * A simple linked list structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the stack is visible through the standard
 * list methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, iterator, and toArray.
 *
 * @author Chandler Mayberry
 * @version 2021-02-05
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The value to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {

		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		while (curr != null && curr.getData().compareTo(key) != 0) {
			prev = curr;
			curr = curr.getNext();
		}

		return prev; // return the node previous to the key node

	}

	/**
	 * Appends data to the end of this SingleList.
	 *
	 * @param data The data to append.
	 */
	public void append(final T data) {

		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		while (curr != null) {
			prev = curr;
			curr = curr.getNext();
		}

		// if prev is null then the list is empty
		if (prev == null) {
			this.front = new SingleNode<>(data, null);
			this.length++; // add another count to the list
		} else {
			prev.setNext(new SingleNode<>(data, null));
			this.length++; // add another count to the list
		}

	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each value formerly present in this SingleList. The first occurrence of
	 * each value is preserved.
	 */
	public void clean() {
		SingleNode<T> prev = null; // we need a prev that can replace the link if there is a dupe
		SingleNode<T> curr = this.front; // we need that goes through each node every iteration
		SingleNode<T> comparing_node = this.front; // we need a node that is a comparison

		while (comparing_node != null) {

			// shift the list "forward" to compare a smaller subset of the list (nodes
			// already met by the comparing node
			// have already been checked in previous iterations)
			prev = comparing_node;
			curr = comparing_node.getNext();

			while (curr != null) {

				// if there is a dupe then you need to delete
				if (curr.getData().compareTo(comparing_node.getData()) == 0) {

					prev.setNext(curr.getNext()); // remove the node
					this.length--;

				} else {
					prev = curr;
				}

				// move to the next node to compare against the comparing node
				curr = curr.getNext();
			}

			// go to the next node in the list to compare
			comparing_node = comparing_node.getNext();
		}


		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: data must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {

		while (right.length > 0 && left.length > 0) {
			// remove the nodes
			final SingleNode<T> adder_node_one = right.front;
			right.front = right.front.getNext();
			right.length--;

			final SingleNode<T> adder_node_two = left.front;
			left.front = left.front.getNext();
			left.length--;


			// put the nodes in this list
			adder_node_one.setNext(this.front);
			this.front = adder_node_one;
			this.length++;

			adder_node_two.setNext(this.front);
			this.front = adder_node_two;
			this.length++;
		}

		// elements still left in right list
		while (right.length > 0) {
			// remove the nodes
			final SingleNode<T> adder_node = right.front;
			right.front = right.front.getNext();
			right.length--;

			// put the nodes in this list
			adder_node.setNext(this.front);
			this.front = adder_node;
			this.length++;
		}

		// elements still left in left list
		while (left.length > 0) {
			// remove the nodes
			final SingleNode<T> adder_node = left.front;
			left.front = left.front.getNext();
			left.length--;

			// put the nodes in this list
			adder_node.setNext(this.front);
			this.front = adder_node;
			this.length++;
		}


	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key value to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {

		boolean has = true;
		SingleNode<T> curr = this.front;

		while (curr != null && curr.getData().compareTo(key) != 0) {
			curr = curr.getNext();
		}

		if (curr == null) {
			has = false;
		}

		return has;

	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The value to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {

		int count = 0;
		SingleNode<T> curr = this.front;

		while (curr != null) {
			if (curr.getData().compareTo(key) == 0)
				count++;

			curr = curr.getNext();
		}

		return count;


	}

	/**
	 * Finds and returns the value in list that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value that matches key, null otherwise.
	 */
	public T find(final T key) {
		T found = null;

		if (this.length != 0) {
			final SingleNode<T> prev = this.linearSearch(key); // returns the prev to the key

			if (prev != null)
				found = prev.getNext().getData();

			else if (prev == null) {
				found = this.front.getData();
			}
		}

		return found;
	}

	/**
	 * Get the nth item in this SingleList.
	 *
	 * @param n The index of the item to return.
	 * @return The nth item in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {

		SingleNode<T> curr = this.front;
		T gets = null;

		if (n >= this.length)
			throw new ArrayIndexOutOfBoundsException("Index is too large for list");

		for (int i = 0; i < n && curr != null; i++) {
			curr = curr.getNext();
		}

		gets = curr.getData();

		return gets;
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same values in the same order as
	 *         source, false otherwise.
	 */
	public boolean identical(final SingleList<T> source) {

		boolean same = true;

		SingleNode<T> curr1 = this.front;
		SingleNode<T> curr2 = source.front;

		if (source.length == this.length) {
			// need to check and iterate through each value in both lists at the same time
			while (curr1 != null && curr2 != null && curr1.getData().compareTo(curr2.getData()) == 0) {
				curr1 = curr1.getNext();
				curr2 = curr2.getNext();
			}

			// if they both arent at the end of the list then we know it left the loop
			// prematurely
			if (curr1 != null && curr2 != null)
				same = false;

		} else {
			same = false; // different lengths
		}

		return same;

	}

	/**
	 * Finds the first location of a value by key in this SingleList.
	 *
	 * @param key The value to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {
		SingleNode<T> curr = this.front;
		int i = 0;

		while (curr != null && curr.getData().compareTo(key) != 0) {
			curr = curr.getNext();
			i++; // find the index
		}

		if (curr == null) {
			i = -1; // key not found
		}

		return i;

	}

	/**
	 * Inserts data into this SingleList at index i. If i greater than the length of
	 * this SingleList, append value to the end of this SingleList.
	 *
	 * @param i    The index to insert the new value at.
	 * @param data The new value to insert into this SingleList.
	 */
	public void insert(int i, final T data) {

		if (i == 0) { // if the index is the first value insert at the front
			this.prepend(data);
		} else if (i >= this.length) { // if i is bigger then append to the end
			this.append(data);
		} else {

			SingleNode<T> prev = null;
			SingleNode<T> curr = this.front;


			for (int j = 0; j < i; j++) {
				prev = curr;
				curr = curr.getNext();
			}

			prev.setNext(new SingleNode<>(data, curr)); // insert at i location

			this.length++;
		}


	}

	/**
	 * Inserts data into the front of this SingleList.
	 *
	 * @param data The value to insert into the front of this SingleList.
	 */
	public void prepend(final T data) {

		if (this.length > 0) {
			SingleNode<T> temp = this.front;
			this.front = new SingleNode<T>(data, temp);

		} else { // when there is an empty list
			SingleNode<T> temp = new SingleNode<T>(data, null);
			this.front = temp;
			this.rear = temp;
		}

		this.length++;

		return;
	}

	/**
	 * Finds the maximum value in this SingleList.
	 *
	 * @return The maximum value.
	 */
	public T max() {

		SingleNode<T> curr = this.front;
		T max = this.front.getData(); // get the first value in the list

		while (curr != null) {
			if (max.compareTo(curr.getData()) < 0) {
				max = curr.getData();
			}
			curr = curr.getNext();
		}

		return max;

	}

	/**
	 * Finds the minimum value in this SingleList.
	 *
	 * @return The minimum value.
	 */
	public T min() {

		SingleNode<T> curr = this.front;
		T min = this.front.getData(); // get the first value in the list

		while (curr != null) {
			if (min.compareTo(curr.getData()) > 0) {
				min = curr.getData();
			}
			curr = curr.getNext();
		}

		return min;

	}

	/**
	 * Finds, removes, and returns the value in this SingleList that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value matching key, null otherwise.
	 */
	public T remove(final T key) {
		SingleNode<T> prev = this.linearSearch(key); // linear search returns the prev node

		T match = null;

		if (this.length > 0) {

			if (prev == null) {// if prev is null that means the key is at the front
				match = this.front.getData(); // get the front
				this.front = this.front.getNext(); // move the front
				this.length--;

			} else if (prev.getNext() != null) { // somewhere in the list, but getnext as it may be at the end of the list
				match = prev.getNext().getData(); // the next node is the key
				prev.setNext(prev.getNext().getNext()); // set previous to the next next node
				this.length--;

			}
		}

		return match;
	}

	/**
	 * Removes the value at the front of this SingleList.
	 *
	 * @return The value at the front of this SingleList.
	 */
	public T removeFront() {

		final T deletion = this.front.getData(); // return the deleted value

		if (this.front.getNext() != null) {
			this.front = this.front.getNext(); // set the front to the next node
		} else {
			this.front = this.rear = null; // if there is no next value then delete the only node
		}
		this.length--;

		return deletion;

	}

	/**
	 * Reverses the order of the values in this SingleList.
	 */
	public void reverse() {

		// make a swap
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;
		SingleNode<T> hold = null;

		// run to the end of the list
		while (curr != null) {
			hold = curr.getNext(); // grab the next from the left
			curr.setNext(prev); // set the next to null (rewind time)
			prev = curr; // prev = curr (floating-non-linked on first iteration)
			curr = hold; // grab next value
		}
		this.front = prev; // reassign front after reversal

	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move data or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * item than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {

		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		// need to find the middle of the list
		int i = this.length % 2 + this.length / 2; // modding gets the odd length if the length is odd

		// we just need pointers to the nodes left up to or equal to i
		for (int j = 0; j < i; j++) {
			prev = curr;
			curr = curr.getNext();
		}

		// assign new pointers to each left and right into right
		if (prev != null) {
			left.front = this.front;
			prev.setNext(null);

			right.front = curr;
			this.front = null;

			left.length = i;
			right.length = this.length / 2;
			this.length = 0;
		}

	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move data or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

		if (this.length == 1) {
			left.front = this.front;
		} else {

			// set the first value to left
			left.front = this.front;
			this.front = this.front.getNext();
			// set the second value to right
			right.front = this.front;
			this.front = this.front.getNext();

			// make node pointers to get the rest of the list
			SingleNode<T> leftn = left.front;
			SingleNode<T> rightn = right.front;

			// cycle through all of the values in the list, alternating from left to right
			while (this.front != null) {
				leftn.setNext(this.front);
				leftn = leftn.getNext();
				this.front = this.front.getNext();

				if (this.front != null) {
					rightn.setNext(this.front);
					rightn = rightn.getNext();
					this.front = this.front.getNext();
				}

			}

			leftn.setNext(null); // set the end of the list to null (separate them)
			rightn.setNext(null);

		}


		left.length = this.length % 2 + this.length / 2;
		right.length = this.length / 2;
		this.length = 0;
		this.front = null;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies data to
	 * this list. left and right SingleLists are unchanged.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {


		// start with the left list and insert it into the linked list
		SingleNode<T> curr = left.front;

		while (curr != null) {
			if (this.contains(curr.getData()) == false) {
				this.append(curr.getData());
			}

			curr = curr.getNext();
		}

		// after inserting the left list insert the right list
		curr = right.front;

		while (curr != null) {
			if (this.contains(curr.getData()) == false) {
				this.append(curr.getData());
			}

			curr = curr.getNext();
		}
	}

}
