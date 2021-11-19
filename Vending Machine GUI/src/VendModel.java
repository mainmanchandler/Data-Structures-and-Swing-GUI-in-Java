import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//1. be able to store a limited number of different products. For example, four kinds of potato chips, six kinds of chocolate bars, eight types of gum, etc. and be limited to some arbitrary number of each item, for example, 10.
//2. reject requests for items that are out of stock, i.e. don't steal people's money.
//3. be able to take two kinds of payment, cash (coins or bills up to $10)
//4. give change. The vending machine should start with an arbitrary amount of change, be able to give more change as it sells more items for cash, and reject sales for which it cannot provide enough change.
//5. allow users to choose multiple items to buy and be charged a total price for all the items at once
//6. allow user to cancel an order at any time before their products are released


/**
 * Vending machine model. Contains the algorithms for vending products, handling
 * change and inventory, and working with credit. Should not perform any GUI
 * work of any kind.
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class VendModel {

	/* variables init */
	public enum Button_Input {
		CHARACTER, NUMBER, CANCEL, VEND, ADD
	};

	public enum Money_Type {
		NICKEL, DIME, QUARTER, LOONIE, TOONIE, FIVE, TEN, DONE
	};

	private ArrayList<ProductFactory> products_list;
	private ArrayList<ProductFactory> buy_list;
	private double vmMoney; // vending machine money
	private double transaction_cost; // transaction_cost of purchase
	private DecimalFormat price_format = new DecimalFormat("$#.00");


	/*
	 * constructor for the vending machine model
	 */
	public VendModel() {
		this.products_list = new ArrayList<>();
		this.buy_list = new ArrayList<>();
		this.init_products();
	}

	/*
	 * Used to initialize the products in the vending machine
	 */
	private void init_products() {
		products_list.add(new ProductFactory("A1", "CupCake", 5, 4.50));
		products_list.add(new ProductFactory("A2", "DabPug", 2, 10.00));
		products_list.add(new ProductFactory("A3", "Donut", 5, 3.25));
		products_list.add(new ProductFactory("A4", "Cone", 7, 7.75));
		products_list.add(new ProductFactory("B1", "Orange", 5, 3.50));
		products_list.add(new ProductFactory("B2", "HotDog", 10, 1.25));
		products_list.add(new ProductFactory("B3", "Pug", 1, 53.50));
		products_list.add(new ProductFactory("B4", "Pusheen", 4, 12.50));
		products_list.add(new ProductFactory("C1", "Ramen", 9, 8.50));
		products_list.add(new ProductFactory("C2", "Rice-Bowl", 11, 8.75));
		products_list.add(new ProductFactory("C3", "Tofu", 15, 6.50));
		products_list.add(new ProductFactory("C4", "Sushi", 3, 7.50));
	}

	/*
	 * this is to control the button actionListeners when a button on the keypad is
	 * pressed, functions.
	 */
	private String choice = "";

	public String keypadPress(Button_Input button, String... args) {

		switch (button) {

		case CHARACTER:

			// if a character was already input then stop.
			if (choice != "")
				choice = "";

			choice = choice + args[0];
			return "Item Choice: " + choice;


		case NUMBER:

			// if the choice length is >= 2 auto-reset
			if (choice.length() >= 2)
				choice = "";

			choice = choice + args[0];
			return "Item Choice: " + choice;


		case ADD:
			// get the first character, if its not a letter than input is wrong
			char first = choice.charAt(0);

			if (Character.isLetter(first) == false || choice.length() <= 1 || choice.length() > 2) {
				choice = "";
				return "::Invalid Selection::";


			} else {

				// find the index of the product we want to grab from the list
				char second = choice.charAt(1);
				int index = 0;

				if (first == 'A') {
					index = 0;
					if (second == '2')
						index = index + 1;
					else if (second == '3')
						index = index + 2;
					else if (second == '4')
						index = index + 3;

				} else if (first == 'B') {
					index = 4;
					if (second == '2')
						index = index + 1;
					else if (second == '3')
						index = index + 2;
					else if (second == '4')
						index = index + 3;


				} else if (first == 'C') {
					index = 8;
					if (second == '2')
						index = index + 1;
					else if (second == '3')
						index = index + 2;
					else if (second == '4')
						index = index + 3;

				}

				/* new product actions */

				ProductFactory product = products_list.get(index);
				if (product.getQuantity() == 0) // if the item is in stock perform actions, otherwise return out of stock
					return "::Item Is Out of Stock::";
				else {
					product.removeOneStock(); // remove one from its quantity
					buy_list.add(product); // add the product to the list of items you want to buy
					transaction_cost = transaction_cost + product.getPrice();

					return price_format.format(transaction_cost);
				}
			}

		case CANCEL:

			// reset transaction_cost and vending machine money
			transaction_cost = 0;
			vmMoney = 100;

			// reset the buy list:
			buy_list.clear();

			// reset all of the objects in the product list to their original quantity.
			int i = 0;
			while (products_list.size() > i) {
				ProductFactory temp = products_list.get(i);
				temp.setQuantity(temp.getOriginalQuantity());
				products_list.set(i, temp);
				i++;
			}

			break;

		case VEND:
			if (buy_list.size() == 0)
				return "Please add an item.";
			// else
			// cashPayment();

			return " ";

		default:
			break;

		}

		return null;

	}


	/*
	 * display method to show the quantity of recent item
	 */
	public String quantityDisplayChanger() {
		String quantity_text = "";

		if (buy_list.size() > 0) {

			quantity_text = "Quantity Left: ";
			ProductFactory newest_product = buy_list.get(buy_list.size() - 1);

			if (newest_product != null)
				quantity_text = quantity_text + newest_product.getQuantity();

		} else
			quantity_text = "::No Items Added::";

		return quantity_text;
	}

	/*
	 * display method to show the item that was just added
	 */
	public String itemAddedDisplayChanger() {
		String item_added = "";

		if (buy_list.size() > 0) {

			item_added = "Item Added: ";
			ProductFactory newest_product = buy_list.get(buy_list.size() - 1);

			if (newest_product != null)
				item_added = item_added + newest_product.getName();

		} else
			item_added = "::No Items Added::";

		return item_added;
	}


	public String cashPayment(Money_Type money, String... args) {
		switch (money) {

		case NICKEL:
			transaction_cost = transaction_cost - 0.05;
			return price_format.format(transaction_cost);

		case DIME:
			transaction_cost = transaction_cost - 0.10;

			return price_format.format(transaction_cost);

		case QUARTER:
			transaction_cost = transaction_cost - 0.25;

			return price_format.format(transaction_cost);

		case LOONIE:
			transaction_cost = transaction_cost - 1.00;

			return price_format.format(transaction_cost);

		case TOONIE:
			transaction_cost = transaction_cost - 2.00;

			return price_format.format(transaction_cost);

		case FIVE:
			transaction_cost = transaction_cost - 5.00;

			return price_format.format(transaction_cost);

		case TEN:
			transaction_cost = transaction_cost - 10.00;

			return price_format.format(transaction_cost);

		case DONE:
			if (transaction_cost > 0.00)
				return "Insufficent Funds";
			else {

				vmMoney = 10000; // $100 in cents

				// return change variables:
				int nickel = 0;
				int dime = 0;
				int quarter = 0;
				int loonie = 0;
				int toonie = 0;
				int five = 0;
				int ten = 0;

				// change the transaction_cost to positive cents
				int changeReturn = (int) (Math.abs(transaction_cost) * 100);

				// check to see if the vending machine will have enough change:
				if (changeReturn < vmMoney) {


					// now I gotta return the change to the user, working in cents
					ten = changeReturn / 1000;
					changeReturn = changeReturn % 1000;

					five = changeReturn / 500;
					changeReturn = changeReturn % 500;

					toonie = changeReturn / 200;
					changeReturn = changeReturn % 200;

					loonie = changeReturn / 100;
					changeReturn = changeReturn % 100;

					quarter = changeReturn / 25;
					changeReturn = changeReturn % 25;

					dime = changeReturn / 10;
					changeReturn = changeReturn % 10;

					nickel = changeReturn / 5;
					changeReturn = changeReturn % 5;

					String item_list = "";

					int i = 0;
					while (buy_list.size() > i) {
						item_list = item_list + "• " + buy_list.get(i).getName() + "\n";
						i++;
					}

					String change = "Here is your change:\nNickels: " + nickel + "\nDimes:" + dime + "\nQuarters:" + quarter + "\nLoonies: " + loonie + "\nToonies: " + toonie + "\nFives: " + five
							+ "\nTen: " + ten + "\n\nHere is your item(s): \n" + item_list;

					JOptionPane.showMessageDialog(null, change);
					System.exit(1);

				} else {
					JOptionPane.showMessageDialog(null, "::Vending Machine Has Insufficent Change::\n::Order Cancelled::");
					System.exit(0);
				}
				return "";

			}

		default:
			break;

		}


		return null;
	}


	/*
	 * setters and getters
	 */
	public double getTransaction_cost() {
		return transaction_cost;
	}

}
