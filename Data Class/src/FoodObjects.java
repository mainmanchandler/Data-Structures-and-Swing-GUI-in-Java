import java.io.PrintStream;

/**
 * Food class definition.
 *
 * @author Chandler Mayberry 
 */
public class Food implements Comparable<Food> {

	// Constants
	public static final String ORIGINS[] = { "Canadian", "Chinese", "Indian", "Ethiopian", "Mexican", "Greek", "Japanese", "Italian", "Moroccan", "Scottish", "Columbian", "English" };

	/**
	 * Creates a string of food origins in the format:
	 *
	 * <pre>
	Origins
	0 Canadian
	1 Chinese
	...
	11 English
	 * </pre>
	 *
	 * @return A formatted numbered string of valid food origins.
	 */
	public static String originsMenu() {

		String origin_list = "";
		origin_list += "Origins\n";
		for (int i = 0; i < ORIGINS.length; i++) {
			if (i <= 9) {
				origin_list += " ";
				origin_list += (i + " " + ORIGINS[i] + "\n");
			} else if (i == 10) {
				origin_list += (i + " " + ORIGINS[i] + "\n");
			} else if (i == 11) {
				origin_list += (i + " " + ORIGINS[i]);
			}
		}

		// System.out.print(origin_list); // testing

		return origin_list;

	}

	// Attributes
	private String name = null;
	private int origin = 0;
	private boolean isVegetarian = false;
	private int calories = 0;

	/**
	 * Food constructor.
	 *
	 * @param name         food name
	 * @param origin       food origin code
	 * @param isVegetarian whether food is vegetarian
	 * @param calories     caloric content of food
	 */
	public Food(final String name, final int origin, final boolean isVegetarian, final int calories) {

		this.name = name;
		this.origin = origin;
		this.isVegetarian = isVegetarian;
		this.calories = calories;

	}


	/*
	 * (non-Javadoc) Compares this food against another food.
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	/**
	 * Foods are compared by name, then by origin if the names match. Must ignore
	 * case.
	 * 
	 * return value less than 0: current Food comes before the target Food. return
	 * value greater than 0: current Food comes after the target Food. return value
	 * equal to 0: the two food contain the same key values.
	 * 
	 */
	@Override
	public int compareTo(final Food target) {
		int result = this.name.compareToIgnoreCase(target.name);

		if (result == 0) {
			if (this.origin == target.origin)
				result = 0;
			else if (this.origin < target.origin)
				result = -1;
			else if (this.origin > target.origin)
				result = 1;

		} else if (result < 0) {
			result = -1;
		} else {
			result = 1;
		}


		return result;
	}

	/**
	 * Getter for calories attribute.
	 *
	 * @return calories
	 */
	public int getCalories() {
		return this.calories;
	}

	/**
	 * Getter for name attribute.
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for origin attribute.
	 *
	 * @return origin
	 */
	public int getOrigin() {
		return this.origin;
	}

	/**
	 * Getter for string version of origin attribute.
	 *
	 * @return string version of origin
	 */
	public String getOriginString() {
		return ORIGINS[this.origin];
	}

	/*
	 * (non-Javadoc) Generates a hash value from a food name.
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;

		for (int i = 0; i < this.name.length(); i++) {
			hash += this.name.charAt(i);
		}
		return hash;
	}

	/**
	 * Getter for isVegetarian attribute.
	 *
	 * @return isVegetarian
	 */
	public boolean isVegetarian() {
		return this.isVegetarian;
	}

	/**
	 * Creates a formatted string of food key data.
	 *
	 * @return a food key as a string
	 */
	public String key() {
		return String.format("%s, %d", this.name, this.origin);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object//toString() Creates a formatted string of food data.
	 */
	/**
	 * Returns a string version of a Food object in the form:
	 * 
	 * <pre>
	Name:       name
	Origin:     origin string
	Vegetarian: true/false
	Calories:   calories
	 * </pre>
	 */
	@Override
	public String toString() {
		String food_object = "";
		food_object += "Name:       " + this.name + "\n";
		food_object += "Origin:     " + ORIGINS[this.origin] + "\n";
		food_object += "Vegetarian: " + this.isVegetarian + "\n";
		food_object += "Calories:   " + this.calories + "\n";

		return food_object;
	}

	/**
	 * Writes a single line of food data to an open PrintStream. The contents of
	 * food are written as a string in the format name|origin|isVegetarian|calories
	 * to ps.
	 *
	 * @param ps The PrintStream to write to.
	 */
	public void write(final PrintStream ps) {
		ps.println(this.name + "|" + this.origin + "|" + this.isVegetarian + "|" + this.calories);
		return;
	}

}
