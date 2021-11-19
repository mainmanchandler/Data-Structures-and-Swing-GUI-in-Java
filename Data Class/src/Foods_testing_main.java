import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Chandler Mayberry
 * @version 2021-01-29
 */
public class Food_testing_main {
	// Constants
	public static final String SEP = "-".repeat(40);

	public static void main(String[] args) {

		/* testing objects */
		Food Banana = new Food("Banana", 0, true, 200);
		Food Apple = new Food("Apple", 2, true, 100);
		Food Meatloaf = new Food("Meatloaf", 5, false, 500);
		Food Borger = new Food("Borger", 9, false, 200);
		Food Carrot = new Food("Carrot", 9, true, 300);


		/* testing toString method */
		System.out.print("toString:\n");
		System.out.print(Banana.toString());
		System.out.print("\n");
		System.out.print(Apple.toString());


		/* testing compareTo method */
		System.out.print(SEP + "\ncompareTo\n");
		int compare_result = 0;

		compare_result = Banana.compareTo(Apple);
		System.out.println(compare_result); // should be 1

		compare_result = Banana.compareTo(Banana);
		System.out.println(compare_result); // should be 0

		compare_result = Apple.compareTo(Banana);
		System.out.println(compare_result); // should be -1

		// testing name is the same but different origin
		Food beeftest1 = new Food("beef", 3, true, 200);
		Food beeftest2 = new Food("beef", 4, true, 200);

		compare_result = beeftest1.compareTo(beeftest2);
		System.out.println(compare_result); // should be -1

		compare_result = beeftest2.compareTo(beeftest1);
		System.out.println(compare_result); // should be 1


		/* testing printstream write method */
		System.out.print(SEP + "\nprinting objects to test.txt\n");
		try {
			PrintStream ps = new PrintStream("test.txt");
			Banana.write(ps);
			Apple.write(ps);
			Borger.write(ps);
			Meatloaf.write(ps);
			Carrot.write(ps);
			ps.close(); // closes the printstream

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		/* testing readFoods/ readfood methods */
		System.out.print(SEP + "\nreadFoods/readfood:\n");
		// File test = new File("test.txt"); // make file object to send to scanner
		File test = new File("foods.txt");

		try {
			Scanner food_file = new Scanner(test);
			ArrayList<Food> food_test = FoodFunctions.readFoods(food_file); // make food array from file
			food_file.close();

			for (Food food : food_test) { // for all food objects in food array
				System.out.println(food);
			}

			/* testing averageCalories method */
			System.out.print(SEP + "\naverageCalories:\n");
			System.out.println(FoodFunctions.averageCalories(food_test));

			/* testing averageCaloriesByOrigin method */
			System.out.print(SEP + "\naverageCaloriesByOrigin:\n");
			System.out.println(FoodFunctions.averageCaloriesByOrigin(food_test, 9));

			/* testing getByOrigin method */
			System.out.print(SEP + "\ngetByOrigin:\n");
			ArrayList<Food> food_by_origin_list = FoodFunctions.getByOrigin(food_test, 9);
			for (Food food : food_by_origin_list) { // for all food objects in food array
				System.out.println(food);
			}

			/* testing getFood method */

			System.out.print(SEP + "\ngetFood:\n\n");
			Scanner keyboard = new Scanner(System.in);
			System.out.print("\n" + FoodFunctions.getFood(keyboard));
			keyboard.close();


			/* testing getVegetarian method */
			System.out.print(SEP + "\ngetVegetarian: \n");
			ArrayList<Food> food_by_veg = FoodFunctions.getVegetarian(food_test);
			for (Food food : food_by_veg) { // for all food objects in food array
				System.out.println(food);
			}

			/* testing foodSearch method */
			System.out.print(SEP + "\nFoodsearch: \n");
			ArrayList<Food> foodsearch = FoodFunctions.foodSearch(food_test, 0, 250, false);
			for (Food food : foodsearch) { // for all food objects in food array
				System.out.println(food);
			}

			/* testing writeFoods method */
			System.out.print(SEP + "\nwriteFoods: writing to writefood_test.txt \n");
			PrintStream ps = new PrintStream("writefood_test.txt");
			FoodFunctions.writeFoods(food_test, ps);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
}