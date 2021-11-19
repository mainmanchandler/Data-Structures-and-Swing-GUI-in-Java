import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//import java.util.Arrays;
//import java.util.Scanner;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Chandler Mayberry
 */
public class practice_main {
	// Constants
	public static final String SEP = "-".repeat(40);
	public static final String CIPHERTEXT = "AVIBROWNZCEFGHJKLMPQSTUXYD"; // for testing substitute method

	public static void main(String[] args) {

		// Testing for contains
		int[] test = { 2, 4, 6, 1, 7 };

		System.out.println(A01.contains(test, 7));
		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for isPalindrome
		String word = "Racecar";
		// String word = "race#$$@ca'''r";
		// String word = "raceCAr";
		// String word = "hello";

		System.out.println(A01.isPalindrome(word));
		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for isValid
		String name = "Banana";
		// String name = "_";
		// String name = "_hello";
		// String name = "12asas";
		// String name = "hel_12lo";

		System.out.println(A01.isValid(name));
		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for matrixStats
		// below is making a 2d double array and filling it with random numbers
		/*
		 * double[][] test1 = new double[3][3];
		 * 
		 * for (int i = 0; i < test1.length; i++) { for (int j = 0; j < test1[i].length;
		 * j++) { test1[i][j] = (int) (Math.random() * 15); System.out.print(test1[i][j]
		 * + " "); } }
		 */

		double[][] test2 = { { 4, 2, 6, 1 }, { 5, 7, 2, 32 }, { 9, 7, 2, .5 } }; // smallest = 0.5, largest = 32, total
																					// = 77.5, average = 6.45833...

		double[] result = A01.matrixStats(test2);

		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for sumPartialHarmonic

		System.out.println(A01.sumPartialHarmonic(5));
		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for validSn
		String serial = "SN/1234-123";
		// String serial = "2N/1234-123";
		// String serial = "SN/123f-123";
		// String serial = "SN/1234-1-3";
		// String serial = "SN/1234*123";

		System.out.println(A01.validSn(serial));
		System.out.println(SEP);

		// ------------------------------------------------------------------------------------------------------------
		// testing for validSnFile
		File textfile = new File("fileIn.txt");
		File goodserial = new File("goodSns.txt");
		File badserials = new File("badSns.txt");

		try {
			PrintStream goodserial1 = new PrintStream(goodserial);
			Scanner textfile1 = new Scanner(textfile);
			PrintStream badserials1 = new PrintStream(badserials);
			A01.validSnFile(textfile1, goodserial1, badserials1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
