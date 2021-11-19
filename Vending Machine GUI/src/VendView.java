import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

//1. provide a comprehensive front end for the vending model. All model capabilities must be available through the front end
//2. show products in a grid format as though you were looking through the glass front of an actual vending machine
//3. either label the products with text or display images (icons)
//4. label the product 'boxes' with a letter/number code for selection. Label the grid rows with letters and the columns with numbers. Thus selections might be C2, B4, A0, etc.
//5. allow users to enter coins and bills as buttons for 5 ¢, 10 ¢, 25 ¢, $1, $2, $5, and $10. Buttons may be pushed multiple times to enter multiple coins or bills
//6. have a display for the total cost of items chosen
//7. have a cancel button
//8. have a vend/finish button for the user to indicate they are finished choosing products
//9. have letter and digit buttons to allow users to choose their products.


/**
 * Handles the GUI portion of the vending machine. May use other GUI classes for
 * individual elements of the vending machine. Should use the VendModel for all
 * control logic.
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
@SuppressWarnings("serial")
public class VendView extends JPanel {

	// views for each of the things
	@SuppressWarnings("unused")
	private VendModel model = null;

	private ButtonView buttonView = null;
	private ProductView productView = null;
	private PaymentView paymentView = null;
	private DisplayView displayView = null;


	public VendView(VendModel model) {
		this.model = model;

		this.buttonView = new ButtonView(this, model);
		this.productView = new ProductView(this, model);
		this.paymentView = new PaymentView(this, model);
		this.displayView = new DisplayView(this, model);
		this.screenLayout();

	}

	/**
	 * screen layout of JFrame private method.
	 */
	private void screenLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		this.add(this.productView, c);

		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.displayView, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		this.add(this.buttonView, c);
	}


	/*
	 * Getters for the button class views
	 */
	public ButtonView getButtonView() {
		return buttonView;
	}

	public ProductView getProductView() {
		return productView;
	}

	public PaymentView getPaymentView() {
		return paymentView;
	}

	public DisplayView getDisplayView() {
		return displayView;
	}


}
