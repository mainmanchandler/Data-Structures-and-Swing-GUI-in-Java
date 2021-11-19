import java.io.PrintStream;
/*
import java.util.Arrays;
import java.util.Random;
*/
import java.util.Scanner;

/**
 * @author Chandler Mayberry
 */
public class practice_functions {

	// Constants
	public static final String VOWELS = "aeiouAEIOU";
	public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int ALPHA_LENGTH = ALPHA.length();

	/**
	 * Looks for a integer in an array of integers.
	 *
	 * @param values array of int
	 * @param target value to search for
	 * @return true if target in values, false otherwise
	 */
	public static boolean contains(int[] values, int target) {

		boolean found = false;
		int num;

		for (int i = 0; i < values.length; i++) {
			num = values[i];

			if (target == num) {
				found = true;
			}
		}

		return found;

	}

	/**
	 * Determines if s is a palindrome. Ignores case, spaces, digits, and
	 * punctuation in the string parameter s.
	 *
	 * @param s a string
	 * @return true if s is a palindrome, false otherwise
	 */

	public static boolean isPalindrome(final String s) {

		boolean is_palindrome = true;

		// replaces everything that is not a a-z letter to "" (nothing)
		// char[] word = ... toCharArray(); converts all letters from the string into
		// the array
		char[] word = s.replaceAll("[[^A-Za-z]+]", "").toLowerCase().toCharArray();

		int beginning = 0;
		int end = word.length - 1;

		while (beginning < end) {

			if (word[beginning] != word[end]) {
				is_palindrome = false;
			}

			beginning++;
			end--;
		}

		return is_palindrome;

	}

	/**
	 * Determines if name is a valid Java variable name. Variables names must start
	 * with a letter or an underscore, but cannot be an underscore alone. The rest
	 * of the variable name may consist of letters, numbers and underscores.
	 *
	 * @param name a string to test as a Java variable name
	 * @return true if name is a valid Java variable name, false otherwise
	 */

	public static boolean isValid(final String name) {

		boolean isvalid = true;

		// must start with a letter or an underscore
		if (name != "_") {

			if (name.charAt(0) != '_' && Character.isAlphabetic(name.charAt(0)) == false) {
				isvalid = false;
			}

		} else {
			isvalid = false;
		}

		return isvalid;

	}

	/**
	 * Determines the smallest, largest, total, and average of the values in the 2D
	 * list a. You may assume there is at least one value in a and that a is a
	 * square matrix - i.e. the number of columns per row is the same. a must be
	 * unchanged.
	 *
	 * @param a - a 2D list of numbers (2D list of double)
	 *
	 * @return a list of four double values containing the smallest number in a,the
	 *         largest number in a, the total of all numbers in a, and the average
	 *         of all numbers in a, in that order.
	 */

	public static double[] matrixStats(double[][] a) {
		double smallest = 99999999;
		double largest = 0;
		double total = 0;
		double average = 0;
		int elements = 0;

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {

				if (smallest > a[i][j]) {
					smallest = a[i][j];
				}
				if (largest < a[i][j]) {
					largest = a[i][j];
				}

				total += a[i][j];
				elements++;
			}
		}

		average = total / elements;
		double[] return_list = new double[4];
		return_list[0] = smallest;
		return_list[1] = largest;
		return_list[2] = total;
		return_list[3] = average;

		return return_list;
	}

	/**
	 * Sums and returns the total of a partial harmonic series. This series is the
	 * sum of all terms 1/i, where i ranges from 1 to n (inclusive).
	 *
	 * @param n an integer
	 * @return sum of partial harmonic series from 1 to n
	 */

	// x = 1 + 1/2 + 1/3 + ... + 1/n
	public static double sumPartialHarmonic(int n) {

		double pharmonic = 0;

		if (n != 0) {
			for (double i = 1; i <= n; i++) {
				pharmonic += 1 / i;
			}
		}

		return pharmonic;

	}

	/**
	 * Determines if a string is a good serial number. Good serial numbers are of
	 * the form 'SN/nnnn-nnn', where 'n' is a digit.
	 *
	 * @param sn The serial number to test.
	 * @return true if the serial number is valid in form, false otherwise.
	 */

	public static boolean validSn(String sn) {
		boolean valid = true;

		if (sn.charAt(0) != 'S') {
			valid = false;
		} else if (sn.charAt(1) != 'N') {
			valid = false;
		} else if (sn.charAt(2) != '/') {
			valid = false;
		} else {
			for (int i = 3; i <= 6; i++) {
				if (Character.isDigit(sn.charAt(i)) == false) {
					valid = false;
				}
			}

			if (sn.charAt(7) != '-') {
				valid = false;
			} else {
				for (int i = 8; i <= 10; i++) {
					if (Character.isDigit(sn.charAt(i)) == false) {
						valid = false;
					}
				}
			}
		}
		return valid;

	}

	/**
	 * Evaluates serial numbers from a file. Writes valid serial numbers to
	 * good_sns, and invalid serial numbers to bad_sns.
	 *
	 * @param fileIn  a file already open for reading
	 * @param goodSns a file already open for writing
	 * @param badSns  a file already open for writing
	 */

	public static void validSnFile(Scanner fileIn, PrintStream goodSns, PrintStream badSns) {

		while (fileIn.hasNextLine()) {
			String newline = fileIn.nextLine();

			if (validSn(newline)) {
				goodSns.println(newline);
			} else {
				badSns.println(newline);
			}
		}

		fileIn.close();
		goodSns.close();
		badSns.close();

		return;
	}

}
