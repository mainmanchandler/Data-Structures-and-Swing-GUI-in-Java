/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Chandler Mayberry
 * @version 2021-02-18
 * @param bst
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance value of node. If greater than 1, then left heavy, if
	 * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
	 * is balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {

		int balanced = 0;

		// return the balancing factor
		if (node != null)
			balanced = this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());

		return balanced;

	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {

		TreeNode<T> lnode = node.getRight(); // right node (to be the new parent)
		TreeNode<T> lrnode = lnode.getLeft(); // right-left node (to be new right child of node


		// perform the rotation,
		lnode.setLeft(node);
		node.setRight(lrnode);

		// update the height values
		node.updateHeight();
		lnode.updateHeight();

		return lnode; // new root of the subtree
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {

		TreeNode<T> lnode = node.getLeft(); // left node (to be the new parent)
		TreeNode<T> lrnode = lnode.getRight(); // left-right node (to be new right child of node


		// perform the rotation,
		lnode.setRight(node);
		node.setLeft(lrnode);

		// update the height values
		node.updateHeight();
		lnode.updateHeight();

		return lnode; // new root of the subtree

	}

	/**
	 * Auxiliary method for {@code insert}. Inserts data into this AVL.
	 *
	 * @param node the current node (TreeNode)
	 * @param data Data to be inserted into the node
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

		// if the node is null, then we make a new root
		if (node == null) {
			// insert the new node and update the size
			this.size++;
			TreeNode<T> temp = new TreeNode<>(data);
			node = temp;

		} else {


			// if curr node is greater than key then we want to go left, right otherwise
			int location = node.getValue().compareTo(data);

			// the data already exists
			if (location == 0)
				node.getValue().incrementCount(); // increment the count of that value


			/* recurse through the tree to find the location to insert */

			// data is bigger than current node
			else if (location > 0) {

				// insert the new node to the right
				node.setLeft(this.insertAux(node.getLeft(), data));
				node.updateHeight();

				// check the balancing factor of the tree, if needed, rebalance
				int balance_factor = this.balance(node);

				if (balance_factor >= 2 && this.balance(node.getLeft()) >= 0) // case 1: do right rotation
					node = this.rotateRight(node);

				else if (balance_factor >= 2 && this.balance(node.getLeft()) < 0) { // case 2: do left-right rotation
					node.setLeft(this.rotateLeft(node.getLeft())); // send the left child of the root to the rotate left
					node = this.rotateRight(node); // then rotate right using the root to complete rotation
				}


			} else { // data is smaller than current node

				// insert the new node to the left
				node.setRight(this.insertAux(node.getRight(), data));
				node.updateHeight();

				// check the balancing factor of the tree, if needed, rebalance
				int balance_factor = this.balance(node);


				// check the balancing factor of the tree, if needed, rebalance

				if (balance_factor <= -2 && this.balance(node.getRight()) <= 0) // case 3: do left rotation
					node = this.rotateLeft(node);

				else if (balance_factor <= -2 && this.balance(node.getRight()) > 0) { // case 4: do right-left rotation
					node.setRight(this.rotateRight(node.getRight())); // send the right child of the root to the rotate right
					node = this.rotateLeft(node);
				} // then rotate left using the root to complete rotation

			}

		}


		return node;

	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An AVL must meet the BST validation conditions, and
	 * additionally be balanced in all its subtrees - i.e. the difference in height
	 * between any two children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {

		// must have the same conditions as bst, but we also must check height balancing
		boolean valid_tree = false;

		if (node == null)
			valid_tree = true;
		else {

			// get the height of the right and left subtrees
			int lheight = this.nodeHeight(node.getLeft());
			int rheight = this.nodeHeight(node.getRight());

			// check against height balancing (avl check)
			if (Math.abs(this.balance(node)) >= 2) // if the balanciung factor is >= 2 then avl is false
				valid_tree = false;

			// check against the values
			else if (node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) <= 0 || node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) >= 0)
				valid_tree = false;

			// check against the height for validity
			else if (Math.max(rheight, lheight) != node.getHeight() - 1)
				valid_tree = false;

			// else recurse to the next set of nodes
			else
				valid_tree = this.isValidAux(node.getLeft()) && this.isValidAux(node.getRight());

		}

		return valid_tree;

	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
