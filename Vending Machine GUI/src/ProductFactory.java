/*
 * Creates a product objects.
 * @author Chandler Mayberry
 * @version 2021-04-06
 */
public class ProductFactory implements Comparable<ProductFactory> {

	private String code;
	private double price;
	private String name;
	private int quantity;


	private int originalQuantity;


	public ProductFactory(String code, String name, int quantity, double price) {
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.originalQuantity = quantity;
	}

	/* setters and getters for food object */

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOriginalQuantity() {
		return originalQuantity;
	}

	public String getCode() {
		return code;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void removeOneStock() {
		this.quantity--;
	}

	@Override
	public int compareTo(ProductFactory o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
