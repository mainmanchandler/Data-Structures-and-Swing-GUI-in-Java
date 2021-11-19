/**
 * A simple linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, peek, isEmpty, and iterator.
 *
 * @author Chandler Mayberry
 * @version 2021-02-05
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

	/**
	 * Returns the top data of the stack and removes that data from the stack. The
	 * next node in the stack becomes the new top node. Decrements the stack length.
	 *
	 * @return The data at the top of the stack.
	 */
	public T pop() {

		T popped = null;

		if (this.front != null) {
			popped = this.front.getData();
			this.front = this.front.getNext();
			this.length--;
		}

		return popped;

	}

	/**
	 * Adds data to the top of the stack. Increments the stack length.
	 *
	 * @param data The data to add to the top of the stack.
	 */
	public void push(final T data) {

		SingleNode<T> pushed = new SingleNode<>(data, this.front);
		this.front = pushed;
		this.length++;

		return;

	}
}