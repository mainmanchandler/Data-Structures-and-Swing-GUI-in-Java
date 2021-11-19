import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main method and table generation methods for Assignment 4.
 *
 * @author Chandler Mayberry
 * @version 2021-02-18
 */
public class TreeTestingMain {

	public static final NumberFormat NF = NumberFormat.getInstance();
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String POPULAR = "ETAOINSHRDLUCMPFYWGBVKJXZQ";
	public static final String SPLIT = "MFTCJPWADHKNRUYBEIGLOQSVXZ";
	public static final String SEPARATOR = "------------------------------";

	public static final String[] STRING_DATA = new String[] { ALPHABET, SPLIT, POPULAR };
	public static final String FILENAME = "decline.txt";

	/**
	 * Print a formatted table of character counts and percentages in the format:
	 *
	 * <pre>
	 * Character Table for Comparisons File
	 *
	 * Char    Count Percent
	 *    A  206,946    8.17
	 *    B   37,498    1.48
	 *    ...
	 * </pre>
	 *
	 * Note: your data may not match this as it is file dependent.
	 *
	 * @param tree The BST to generate the table from.
	 */
	private static void characterTable(final BST<Character> tree) {

		System.out.print("\nChar    Count Percent\n");

		double total_node_count = 0; // the total number of nodes in the tree
		int node_count; // the popularity count of the node
		double percentage; // percentage data to output

		ArrayList<CountedData<Character>> tree_array = tree.inOrder(); // the tree data to read from

		// get the total node count in the tree
		for (CountedData<Character> tree_node : tree_array) {
			total_node_count += (double) tree_node.getCount();
		}

		// print the output required
		for (CountedData<Character> tree_node : tree_array) {
			node_count = tree_node.getCount();
			percentage = (node_count / total_node_count) * 100;
			System.out.printf("%4c %,8d %7.2f\n", tree_node.getData(), node_count, percentage);
		}

	}

	/**
	 * Fill a tree by inserting all letters from a string into the tree. Letters
	 * must be converted to upper-case. Non-letters are ignored. The order that
	 * letters are inserted into the tree determine its shape.
	 *
	 * @param tree   The BST/AVL/PopularityTree to fill.
	 * @param string The string to read into the tree.
	 */
	private static void fillTree(final BST<Character> tree, final String string) {

		// we need to take every character from the string and insert it into the tree
		for (final char curr_char : string.toCharArray()) {
			CountedData<Character> char_data = new CountedData<>(Character.toUpperCase(curr_char)); // create a new object for the char
			tree.insert(char_data);
		}

	}

	/**
	 * Determine the number of comparisons to retrieve the contents of a file from a
	 * tree. Attempt to retrieve every letter in the file from tree. All letters
	 * must be converted to upper case.
	 *
	 * @param tree The BST to process.
	 * @param file The file to process.
	 * @return The number of comparisons necessary to find every letter in file in
	 *         tree.
	 * @throws FileNotFoundException Thrown if file not found.
	 */
	private static int retrieve(final BST<Character> tree, final Scanner fileScan) {

		// need to loop through every line in the file
		while (fileScan.hasNextLine()) {


			final String sentence = fileScan.nextLine(); // grab every sentence

			for (Character curr_char : sentence.toCharArray()) { // grab each character

				// if the next char in the txt is a letter then we want to perform a comparisons
				if (Character.isLetter(curr_char)) {
					CountedData<Character> key = new CountedData<>(Character.toUpperCase(curr_char)); // create a new object for the char
					tree.retrieve(key); // call the retrieve which will update the comparisons made to find that char
										// object
				}
			}
		}

		// get the comparision data
		int comparisions = tree.getComparisons();
		return comparisions;

	}

	/**
	 * Program for Assignment 4.
	 *
	 * @param args unused
	 * @throws IOException If error on files.
	 */
	public static void main(final String[] args) throws IOException {
		final File comparisonsFile = new File(FILENAME);

		for (final String string : STRING_DATA) {
			int minComparisons = Integer.MAX_VALUE;
			String treeType = null;
			String minTree = null;
			System.out.println("Data String: " + string);
			System.out.println();
			final ArrayList<BST<Character>> trees = new ArrayList<>();
			trees.add(new BST<Character>());
			trees.add(new PopularityTree<Character>());
			trees.add(new AVL<Character>());

			for (final BST<Character> tree : trees) {
				treeType = tree.getClass().getSimpleName();
				System.out.println("  Tree Type: " + treeType);
				TreeTestingMain.fillTree(tree, string);
				final Scanner fileScan = new Scanner(comparisonsFile);
				final int comparisons = TreeTestingMain.retrieve(tree, fileScan); // this is where the file is called and comparisions are made
				fileScan.close();
				System.out.println("  Height: " + tree.getHeight());
				System.out.println("  Comparisons: " + NF.format(comparisons));
				// System.out.println(" IS_VALID = " + tree.isValid()); // test the is_valid
				// functions

				if (comparisons < minComparisons) {
					minComparisons = comparisons;
					minTree = treeType;
				}
				System.out.println();
			}
			System.out.println("Tree with minimum comparisons: " + minTree);
			System.out.println(SEPARATOR);

		}


		final PopularityTree<Character> bst = new PopularityTree<>();
		TreeTestingMain.fillTree(bst, ALPHABET);
		final Scanner fileScan = new Scanner(comparisonsFile);
		TreeTestingMain.retrieve(bst, fileScan);
		fileScan.close();
		System.out.println("Character Table for Comparisons File");
		System.out.println();
		TreeTestingMain.characterTable(bst);

		/*------contains testing for lab------ */
		/*
		 * System.out.println(SEPARATOR);
		 * System.out.println("----CONTAINS TESTING----");
		 * 
		 * BST<Character> bst = new BST<Character>();
		 * 
		 * final String test = "BANANA"; TreeTestingMain.fillTree(bst, test);
		 * 
		 * System.out.print("\nThe test String is: BANANA\n"); CountedData<Character>
		 * char_data = new CountedData<>(Character.toUpperCase('A')); // create a new
		 * object for the char boolean found = bst.contains(char_data);
		 * System.out.print("\nA is inside of the bst, " + found);
		 * 
		 * CountedData<Character> char_data1 = new
		 * CountedData<>(Character.toUpperCase('b')); // create a new object for the
		 * char boolean found1 = bst.contains(char_data1);
		 * System.out.print("\nb is inside of the bst, " + found1);
		 * 
		 * CountedData<Character> char_data2 = new
		 * CountedData<>(Character.toUpperCase('!')); // create a new object for the
		 * char boolean found2 = bst.contains(char_data2);
		 * System.out.print("\n! is inside of the bst, " + found2);
		 * 
		 * CountedData<Character> char_data3 = new
		 * CountedData<>(Character.toUpperCase('z')); // create a new object for the
		 * char boolean found3 = bst.contains(char_data3);
		 * System.out.print("\nz is inside of the bst, " + found3);
		 * 
		 * System.out.println("\n" + SEPARATOR);
		 */

		/*- - - - - - - - - - - - - - - - - - - - - - - - -*/


		/*
		 * System.out.println(SEPARATOR); System.out.println("----EQUALS TESTING----");
		 * 
		 * BST<Character> bst4 = new BST<Character>(); BST<Character> bst3 = new
		 * BST<Character>(); BST<Character> bst2 = new BST<Character>();
		 * 
		 * final String test = "BANANA"; final String test1 = "BNANA";
		 * 
		 * TreeTestingMain.fillTree(bst3, test); TreeTestingMain.fillTree(bst2, test);
		 * TreeTestingMain.fillTree(bst4, test1);
		 * 
		 * 
		 * System.out.print("\nThe test String is: BANANA\n");
		 * System.out.println("equals: " + bst3.equals(bst2));
		 * System.out.print("equals: " + bst3.equals(bst4));
		 * 
		 * System.out.println("\n" + SEPARATOR);
		 */


	}
}
