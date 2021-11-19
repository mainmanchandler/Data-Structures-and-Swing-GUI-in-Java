import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import cp213.VendModel.Money_Type;

/**
 * GUI for payment view (for popup payment)
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class PaymentView extends JPanel {
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying vending input for output.
	 */

	/**
	 * Payment Buttons
	 */
	private final JButton nickel = new JButton("Nickel");
	private final JButton dime = new JButton("Dime");
	private final JButton quarter = new JButton("Quarter");
	private final JButton loonie = new JButton("Loonie");
	private final JButton toonie = new JButton("Toonie");
	private final JButton five = new JButton("Five Dollar Bill");
	private final JButton ten = new JButton("Ten Dollar Bill");
	private final JButton done = new JButton("Done");

	/**
	 * Payment JLabel
	 */
	JLabel priceRemaining = new JLabel();
	static JLabel price = new JLabel();


	/**
	 * The vending model and view.
	 */
	@SuppressWarnings("unused")
	private final VendView view;
	private final VendModel model;

	/**
	 * The view constructor for the button layout
	 * 
	 * @param vendView  is the view that all derived classes use
	 * 
	 * @param newModel;
	 * 
	 */
	public PaymentView(VendView vendView, final VendModel newModel) {

		this.model = newModel;
		this.view = vendView;
		this.paymentLayoutView();
		this.setActionListeners();
		this.setBackground(Color.green);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
	}

	/*
	 * set action listeners for the cash payment method
	 */
	private void setActionListeners() {
		nickel.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.NICKEL)));
		dime.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.DIME)));
		quarter.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.QUARTER)));
		loonie.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.LOONIE)));
		toonie.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.TOONIE)));
		five.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.FIVE)));
		ten.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.TEN)));
		done.addActionListener(event -> priceRemaining.setText(model.cashPayment(Money_Type.DONE)));

	}


	/**
	 * Grid Layout for Products
	 */
	private void paymentLayoutView() {

		/* Set the payment JLabels */
		priceRemaining.setFont(new Font("verdana", Font.ROMAN_BASELINE, 25));
		price.setText("Remaining Payment: ");
		price.setFont(new Font("verdana", Font.ROMAN_BASELINE, 20));


		/* set fonts of buttons */
		nickel.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		dime.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		quarter.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		loonie.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		loonie.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		toonie.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		five.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		ten.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));
		done.setFont(new Font("verdana", Font.ROMAN_BASELINE, 18));


		/* set the size of the buttons */
		nickel.setPreferredSize(new Dimension(200, 40));
		dime.setPreferredSize(new Dimension(200, 40));
		quarter.setPreferredSize(new Dimension(200, 40));
		loonie.setPreferredSize(new Dimension(200, 40));
		toonie.setPreferredSize(new Dimension(200, 40));
		five.setPreferredSize(new Dimension(200, 40));
		ten.setPreferredSize(new Dimension(200, 40));
		done.setPreferredSize(new Dimension(200, 40));


		/* layout the buttons */
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 5, 10);

		c.gridx = 0;
		c.gridy = 0;
		this.add(price);
		c.gridx++;
		this.add(priceRemaining);

		c.gridy++;
		c.gridx = 0;

		this.add(nickel, c);
		c.gridx++;
		this.add(dime, c);
		c.gridx++;
		this.add(quarter, c);
		c.gridx++;

		c.gridy++;
		c.gridx = 0;

		this.add(loonie, c);
		c.gridx++;
		this.add(toonie, c);
		c.gridx++;
		this.add(five, c);
		c.gridx++;

		c.gridy++;
		c.gridx = 1;

		this.add(ten, c);
		c.gridx++;
		this.add(done, c);

	}
}
