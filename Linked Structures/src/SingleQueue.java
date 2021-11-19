package cp213;

/**
 * A simple linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, peek, isEmpty, and iterator.
 *
 * @author Chandler Mayberry
 * @version 2021-02-05
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

	/**
	 * Combines the contents of the left and right SingleQueues into the current
	 * SingleQueue. Moves nodes only - does not move data or call the high-level
	 * methods insert or remove. left and right SingleQueues are empty when done.
	 * Nodes are moved alternately from left and right to this SingleQueue.
	 *
	 * You have two queues called left and right queues. You remove nodes from these
	 * two queues to create a new queue (current queue).
	 *
	 * If the current queue is empty or not, it should not make a difference to you.
	 * you just get nodes from the right and left queues and add them to the current
	 * queue. Do not use insert and remove methods. . Use moveFront method included
	 * in the class.
	 *
	 * Remember to remove a node from the list you have to update the reference or
	 * the pointer that is pointing to the current node.
	 *
	 * The getNext () and setNext() methods from SingleNode class can be used for
	 * these purpose.
	 *
	 * Do not assume that both right and left queues are of the same length.
	 *
	 * @param left  The first SingleQueue to extract nodes from.
	 * @param right The second SingleQueue to extract nodes from.
	 */
	public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

		// 'this' is current queue
		// similar to combine already written in singlelist
		// helper method does all of the legwork

		// alternate nodes

		// if elements still in both
		while (right.front != null && left.front != null) {
			this.movefront(right);
			this.movefront(left);
		}

		// if elements only in right
		while (right.front != null)
			this.movefront(right);

		// if elements only in left
		while (left.front != null)
			this.movefront(left);

		return;
	}

	/**
	 * Helper method described in the combine description, moves node from original
	 * to front of other
	 * 
	 * @param original queue
	 */
	private void movefront(SingleQueue<T> original) {

		SingleNode<T> curr = original.front;
		original.length--;
		original.front = original.front.getNext();

		// clean up the source if its empty
		if (original.front == null)
			original.rear = null;

		curr.setNext(null); // this deals with the duplicate at the end of split

		/* update the target queue */
		if (this.front == null)
			this.front = curr;
		else
			this.rear.setNext(curr); // add to the end of the target

		// move the pointer to the new end
		this.rear = curr;
		this.length++; // added one to the target queue

		return;
	}

	/**
	 * Adds data to the rear of the queue. Increments the queue length.
	 *
	 * @param data The data to added to the rear of the queue.
	 */
	public void insert(final T data) {

		SingleNode<T> new_node = new SingleNode<>(data, null);

		if (this.length == 0) {
			this.front = new_node;
			this.length++;
		} else {
			this.rear.setNext(new_node);
			this.length++;
		}
		this.rear = new_node; // point to the new rear

		return;

	}

	/**
	 * Returns the front data of the queue and removes that data from the queue. The
	 * next node in the queue becomes the new first node. Decrements the queue
	 * length.
	 *
	 * @return The data at the front of the queue.
	 */
	public T remove() {

		T removed = null;

		if (this.length == 1) { // if there is only one node then the rear needs to be set to null to.
			removed = this.front.getData();
			this.front = null;
			this.rear = null;
			this.length--;
		} else if (this.length != 0) {
			removed = this.front.getData();
			this.front = this.front.getNext();
			this.length--;
		}

		return removed;

	}

	/**
	 * Splits the contents of the current SingleQueue into the left and right
	 * SingleQueues. Moves nodes only - does not move data or call the high-level
	 * methods insert or remove. this SingleQueue is empty when done. Nodes are
	 * moved alternately from this SingleQueue to left and right.
	 *
	 * This is the opposite of the combine method.
	 *
	 * @param left  The first SingleQueue to move nodes to.
	 * @param right The second SingleQueue to move nodes to.
	 */
	public void split(final SingleQueue<T> left, final SingleQueue<T> right) {

		// alt between each of the queue lists
		boolean altn = true;

		while (this.front != null) {
			if (altn) {

				left.movefront(this); // helper method for combine does the legwork

			} else {

				right.movefront(this);

			}

			altn = !altn; // switch
		}

		return;


	}

}
