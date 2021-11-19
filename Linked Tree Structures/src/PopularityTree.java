/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Chandler Mayberry
 * @version 2021-02-18
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for {@code valid}. May force node rotation if the retrieval
	 * count of the located node value is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The value to search for. Count is updated to count in matching
	 *             node value if key is found.
	 * @return the updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {

		// this is where the rotations must happen, if a node keeps getting retrieved it
		// will rotate itself to a more "popular" location

		// as long as the node isnt null we can search the tree
		if (node != null) {

			// if curr node is greater than key then we want to go left, right otherwise
			this.comparisons++;
			int location = node.getValue().compareTo(key);

			// the data already exists
			if (location == 0) {
				node.getValue().incrementCount(); // increment the count of that value
				key.setCount(node.getValue().getCount()); // set the count of the key object
			}

			// left and right checks
			else if (location > 0) { // data is bigger than current node
				node.setLeft(this.retrieveAux(node.getLeft(), key));


				// if the left node count is greater than the parent, then rotate the parent to
				// the right
				int parent = node.getValue().getCount();
				int left_child = node.getLeft().getValue().getCount();
				if (node.getLeft() != null && parent < left_child)
					node = this.rotateRight(node);

				// update the height of the node after rotation
				node.updateHeight();

			} else { // data is smaller than current node
				node.setRight(this.retrieveAux(node.getRight(), key));

				// if the right node count is greater than the parent, then rotate the parent to
				// the left
				int parent = node.getValue().getCount();
				int right_child = node.getRight().getValue().getCount();
				if (node.getRight() != null && parent < right_child)
					node = this.rotateLeft(node);

				// update the height of the node after rotation
				node.updateHeight();
			}
		}

		return node;

	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

		TreeNode<T> rnode = parent.getRight(); // right node (to be the new parent)
		TreeNode<T> rlnode = rnode.getLeft(); // right-left node (to be new right child of node


		// perform the rotation,
		rnode.setLeft(parent);
		parent.setRight(rlnode);

		// update the height values
		parent.updateHeight();
		rnode.updateHeight();

		return rnode; // new root of the subtree

	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {

		TreeNode<T> lnode = parent.getLeft(); // left node (to be the new parent)
		TreeNode<T> lrnode = lnode.getRight(); // left-right node (to be new right child of node


		// perform the rotation,
		lnode.setRight(parent);
		parent.setLeft(lrnode);

		// update the height values
		parent.updateHeight();
		lnode.updateHeight();

		return lnode; // new root of the subtree

	}

	/**
	 * Replaces BST {@code insertAux} - does not increment count on repeated
	 * insertion. Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

		// will be the same as bst, just no == condition

		// if the node is null then this is where we insert, ie new root
		if (node == null) {
			// insert the new node and update the size
			this.size++;
			TreeNode<T> temp = new TreeNode<>(data);
			node = temp;

		} else {

			// if curr node is greater than key then we want to go left, right otherwise
			int location = node.getValue().compareTo(data);

			// recurse through the tree to find the location to insert
			if (location < 0) // data is bigger than current node
				node.setRight(this.insertAux(node.getRight(), data));
			else // data is smaller than current node
				node.setLeft(this.insertAux(node.getLeft(), data));

		}


		node.updateHeight(); // update the height of the tree once inserted
		return node;

	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An Popularity Tree must meet the BST validation conditions,
	 * and additionally the counts of any node data must be greater than or equal to
	 * the counts of its children.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {

		// need to compare the height of each node
		// need to compare the values of each node
		// math max is equivalent to a < b ? x : y;

		// one more condition is needed for pop tree, need to check against count

		boolean valid_tree = false;

		// if the node is null that means its met the bottom of the subtrees
		if (node == null)
			valid_tree = true;
		else {

			// check against count for popularity
			if (node.getRight() != null && node.getValue().getCount() < node.getRight().getValue().getCount()
					|| node.getLeft() != null && node.getValue().getCount() < node.getLeft().getValue().getCount())
				valid_tree = false;

			// this will test against the values in the subtree
			else if (node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) <= 0 || node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) >= 0)
				valid_tree = false;

			// this will test against height
			else if (Math.max(this.nodeHeight(node.getRight()), this.nodeHeight(node.getLeft())) != node.getHeight() - 1)
				valid_tree = false;

			// recursive call to more nodes in the tree
			else
				valid_tree = this.isValidAux(node.getRight()) && this.isValidAux(node.getLeft());
		}
		return valid_tree;
	}


	/**
	 * Very similar to the BST retrieve, but increments the data count here instead
	 * of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedData<T> retrieve(CountedData<T> key) {

		// call the retrieve function starting at the root
		this.root = this.retrieveAux(root, key);

		// if the key count was updated then send back a new object
		if (key.getCount() != 0)
			key = new CountedData<T>(key);

		return key;
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, value, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);
	}

}
