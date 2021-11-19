import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Food objects.
 *
 * @author Chandler Mayberry
 */
public class FoodFunctions {

	/**
	 * Determines the average calories in a list of foods. No rounding necessary.
	 * Foods list parameter may be empty.
	 *
	 * @param foods a list of Food
	 * @return average calories in all Food objects
	 */
	public static int averageCalories(final ArrayList<Food> foods) {
		int avg_cal = 0;
		int food_num = foods.size();

		for (Food food_objects : foods) {
			avg_cal += food_objects.getCalories();
		}

		avg_cal = avg_cal / food_num;
		return avg_cal;
	}

	/**
	 * Determines the average calories in a list of foods for a particular origin.
	 * No rounding necessary. Foods list parameter may be empty.
	 *
	 * @param foods  a list of Food
	 * @param origin the origin of the Food
	 * @return average calories for all Foods of the specified origin
	 */
	public static int averageCaloriesByOrigin(final ArrayList<Food> foods, final int origin) {
		int avg_cal = 0;
		int food_num = 0;

		for (Food food_object : foods) {
			if (food_object.getOrigin() == origin) {
				food_num += 1;
				avg_cal += food_object.getCalories();
			}
		}

		avg_cal = avg_cal / food_num;
		return avg_cal;
	}

	/**
	 * Creates a list of foods by origin.
	 *
	 * @param foods  a list of Food
	 * @param origin a food origin
	 * @return a list of Food from origin
	 */
	public static ArrayList<Food> getByOrigin(final ArrayList<Food> foods, final int origin) {

		ArrayList<Food> food_by_origin = new ArrayList<>();

		for (Food food_object : foods) {
			if (food_object.getOrigin() == origin) {
				food_by_origin.add(food_object);
			}
		}

		return food_by_origin;
	}

	/**
	 * Creates a Food object by requesting data from a user. Uses the format:
	 * 
	 * <pre>
	Name: name
	Origins
	 0 Canadian
	 1 Chinese
	...
	11 English
	Origin: origin number
	Vegetarian (Y/N): Y/N
	Calories: calories
	 * </pre>
	 *
	 * @param keyboard a keyboard Scanner
	 * @return a Food object
	 */
	public static Food getFood(final Scanner keyboard) {

		System.out.print("Name: ");
		String name = keyboard.nextLine();

		System.out.println(Food.originsMenu());
		System.out.print("Origin: ");
		String origin = keyboard.nextLine();

		System.out.print("Vegetarian (Y/N): ");
		String isveg = keyboard.nextLine();
		boolean isvegetarian = false;
		if (isveg.toLowerCase().equals("y")) // check to see if the input is Y or y
			isvegetarian = true;

		System.out.print("Calories: ");
		String calories = keyboard.nextLine();

		Food new_food = new Food(name, Integer.parseInt(origin), isvegetarian, Integer.parseInt(calories));

		return new_food;
	}

	/**
	 * Creates a list of vegetarian foods.
	 *
	 * @param foods a list of Food
	 * @return a list of vegetarian Food
	 */
	public static ArrayList<Food> getVegetarian(final ArrayList<Food> foods) {
		ArrayList<Food> food_by_veg = new ArrayList<>();

		for (Food food_object : foods) {
			if (food_object.isVegetarian() == true) {
				food_by_veg.add(food_object);
			}
		}

		return food_by_veg;
	}

	/**
	 * Creates and returns a Food object from a line of string data.
	 *
	 * @param line a vertical bar-delimited line of food data in the format
	 *             name|origin|isVegetarian|calories
	 * @return the data from line as a Food object
	 */
	public static Food readFood(final String line) {
		String[] food_data = line.split("\\|");

		/*
		 * testing: for (int i = 0; i < food_data.length; i++) {
		 * System.out.println(food_data[i]); }
		 */

		String name = food_data[0];
		int origin = Integer.parseInt(food_data[1]);
		boolean isvegetarian = Boolean.parseBoolean(food_data[2]);
		int calories = Integer.parseInt(food_data[3]);

		Food food = new Food(name, origin, isvegetarian, calories);

		return food;
	}

	/**
	 * Reads a file of food strings into a list of Food objects.
	 *
	 * @param fileIn a Scanner of a Food data file in the format
	 *               name|origin|isVegetarian|calories
	 * @return a list of Food
	 */
	public static ArrayList<Food> readFoods(final Scanner fileIn) {

		ArrayList<Food> food_from_file = new ArrayList<>();

		while (fileIn.hasNextLine()) {
			String current_line = fileIn.nextLine();
			Food make_foodobject_from_line = readFood(current_line);
			food_from_file.add(make_foodobject_from_line);
		}

		return food_from_file;

	}

	/**
	 * Searches for foods that fit certain conditions.
	 *
	 * @param foods        a list of Food
	 * @param origin       the origin of the food; if -1, accept any origin
	 * @param maxCalories  the maximum calories for the food; if 0, accept any
	 * @param isVegetarian whether the food is vegetarian or not; if false accept
	 *                     any
	 * @return a list of foods that fit the conditions specified
	 */
	public static ArrayList<Food> foodSearch(final ArrayList<Food> foods, final int origin, final int maxCalories, final boolean isVegetarian) {
		ArrayList<Food> foodsearch = new ArrayList<>();

		for (Food food_object : foods) {
			if ((food_object.getOrigin() == origin) || (origin == -1))
				if ((maxCalories == 0) || (food_object.getCalories() <= maxCalories))
					if ((isVegetarian == false) || (isVegetarian == true && food_object.isVegetarian()))
						foodsearch.add(food_object);
		}

		return foodsearch;

	}

	/**
	 * Writes the contents of a list of Food to a PrintStream.
	 *
	 * @param foods a list of Food
	 * @param ps    the PrintStream to write to
	 */
	public static void writeFoods(final ArrayList<Food> foods, PrintStream ps) {

		for (Food food_object : foods) {
			ps.println(food_object.getName() + "|" + food_object.getOrigin() + "|" + food_object.isVegetarian() + "|" + food_object.getCalories());
		}

		return;
	}
}
