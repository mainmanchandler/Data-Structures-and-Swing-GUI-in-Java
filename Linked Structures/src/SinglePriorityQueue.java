/**
 * A simple linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard stack methods. Extends the
 * <code>SingleLink</code> class, which already defines the head node, length,
 * peek, isEmpty, and iterator.
 *
 * @author Chandler Mayberry
 * @version 2021-02-05
 * @param <T> data type for base data structure.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Adds data to the SinglePriorityQueue. Data is stored in priority order, with
	 * highest priority data at the front of the SinglePriorityQueue, and lowest at
	 * the rear. Priority is determined by simple comparison - lower values have
	 * higher priority. For example, 1 has a higher priority than 2 because 1 is a
	 * lower value than 2. (Think of the phrase, "We're number one!" as an
	 * indication of priority.)
	 *
	 * When inserting data to the priority queue, the queue must remain sorted.
	 * Hence you need to find the proper location of inserting data. use the head
	 * pointer to go through the queue. e.g., use SingleNode<T> current = this.head;
	 *
	 * use current = current.getNext(); to traverse the queue.
	 *
	 * To get access to the value inside a node of queue use current.getValue().
	 *
	 * @param data data to insert in sorted order in priority queue.
	 */
	public void insert(final T data) {

		// lower values have higher priority (closer to front)

		if (this.front == null) { // if the list is empty
			this.front = new SingleNode<>(data, null);
			this.length++;

		} else {
			SingleNode<T> insert = new SingleNode<>(data, null);
			SingleNode<T> prev = null;
			SingleNode<T> curr = this.front;

			// iterate to correct position
			while (curr != null && data.compareTo(curr.getData()) > 0) {
				prev = curr;
				curr = curr.getNext();
			}

			// insert into the position
			insert.setNext(curr);

			if (prev != null)
				prev.setNext(insert);
			else
				this.front = insert; // value must be top priority


			this.length++;

		}


		return;
	}

	/**
	 * Returns the highest priority data in the SinglePriorityQueue. Decrements the
	 * count.
	 *
	 * @return the highest priority data currently in the SinglePriorityQueue.
	 */
	public T remove() {

		T removed = null;

		if (this.front != null) {
			this.length--;
			removed = this.front.getData();
			this.front = this.front.getNext();
		}

		return removed;

	}

	/**
	 * Splits the contents of this SinglePriorityQueue into the higher and lower
	 * SinglePriorityQueue. Moves nodes only - does not move data or call the
	 * high-level methods insert or remove. this SinglePriorityQueue is empty when
	 * done. Nodes with priority value higher than key are moved to the
	 * SinglePriorityQueue higher. NOdes with a priority value lower than or equal
	 * to key are moved to the SinglePriorityQueue lower.
	 *
	 * 1. if the queue is empty do nothing.
	 *
	 * 2. If the key is less than the value inside the head of the queue, the head
	 * of the lower queue should point where currently the head of the queue is
	 * pointing to, and the higher queue will be empty.
	 *
	 * 3. Otherwise, you go through the queue until you find the first node with a
	 * value less than the key. Once found, the head of the lower queue should point
	 * to that node. And the head of the higher pointer should point to the first
	 * node in the queue.
	 *
	 * 4. If all node values bigger than Key, the lower queue will be empty.
	 *
	 * Remember because the key is class type you need to use compreTo when
	 * comparing values. e.g., key.compareTo(this.head.getValue()) <= 0
	 *
	 * @param key    value to compare against node values in SinglePriorityQueue
	 * @param higher an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority higher than key.
	 * @param lower  an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority lower than or equal to key.
	 */
	public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {


		// if the queue is empty do nothing
		if (this.length != 0) {

			// if key is less than, put in lower queue
			// if the front is has less priority than the key, then its all lower
			if (this.front.getData().compareTo(key) >= 0) {
				lower.front = this.front;
				this.front = null;
				lower.length = this.length;
				this.length = 0;

			} else {

				// if key is more than, put in higher queue

				SingleNode<T> prev = null;
				SingleNode<T> curr = this.front;
				int left_length = 0;
				while (curr != null && curr.getData().compareTo(key) < 0) {
					left_length++;
					prev = curr;
					curr = curr.getNext();
				}

				higher.length = left_length; // set length of higher queue
				prev.setNext(null);
				higher.front = this.front;

				// set the lower queue
				lower.length = (this.length - left_length);
				lower.front = curr;

				// delete the original
				this.front = null;
				this.length = 0;

			}

		}


		return;

	}

}
