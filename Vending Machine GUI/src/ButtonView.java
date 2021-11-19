import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cp213.VendModel.Button_Input;


/**
 * View and update the button GUI Operations
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class ButtonView extends JPanel {
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying vending input for output.
	 */

	/**
	 * Buttons.
	 */
	private final JButton buttonA = new JButton("A");
	private final JButton buttonB = new JButton("B");
	private final JButton buttonC = new JButton("C");
	private final JButton button1 = new JButton("1");
	private final JButton button2 = new JButton("2");
	private final JButton button3 = new JButton("3");
	private final JButton button4 = new JButton("4");
	private final JButton cancel = new JButton("Cancel");
	private final JButton vend = new JButton("Vend");
	private final JButton additem = new JButton("Add Item");


	private final VendView view;
	private final VendModel model;


	/**
	 * The view constructor for the button layout
	 * 
	 * @param model2   is the vendmodel that all derived classes use
	 * @param vendView is the view that all derived classes use
	 */
	public ButtonView(final VendView vendView, VendModel model2) {

		this.model = model2;
		this.view = vendView;
		this.buttonLayoutView();
		this.setActionListeners();

		this.setBackground(Color.PINK);
		// Border blackline = BorderFactory.createLineBorder(Color.black);
		// this.setBorder(blackline);

	}


	/*
	 * Set Action Listeners to all of the buttons in the keypad button panel
	 */
	private void setActionListeners() {
		buttonA.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.CHARACTER, buttonA.getText())));
		buttonB.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.CHARACTER, buttonB.getText())));
		buttonC.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.CHARACTER, buttonC.getText())));
		button1.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.NUMBER, button1.getText())));
		button2.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.NUMBER, button2.getText())));
		button3.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.NUMBER, button3.getText())));
		button4.addActionListener(event -> view.getDisplayView().getItemDisplay().setText(model.keypadPress(Button_Input.NUMBER, button4.getText())));

		// one actionlistener for price change, another action listener for the item
		// display, another for itemadded
		additem.addActionListener(event -> {

			String added = model.keypadPress(Button_Input.ADD);

			if (added.contains("::Item Is Out of Stock::"))
				view.getDisplayView().getItemDisplay().setText(added);
			else if (added.contains("::Invalid"))
				view.getDisplayView().getItemDisplay().setText(added);
			else {
				view.getDisplayView().getPriceDisplay().setText((added));
				view.getDisplayView().getQuantityDisplay().setText(model.quantityDisplayChanger());
				view.getDisplayView().getItemDisplay().setText(model.itemAddedDisplayChanger());
			}

		});


		vend.addActionListener(event -> {

			String vend_or_not = model.keypadPress(Button_Input.VEND);
			if (vend_or_not.contains("Please"))
				view.getDisplayView().getItemDisplay().setText(vend_or_not);
			else {
				view.getPaymentView().priceRemaining.setText(String.format("$%.2f", model.getTransaction_cost())); // have to have this here or it defaults to $0
				// JOptionPane.showMessageDialog(this, view.getPaymentView());
				JOptionPane.showOptionDialog(null, view.getPaymentView(), "::Payment Method:: ::Cash::", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
			}

		});


		cancel.addActionListener(event -> {
			// call the class to perform the cancel
			model.keypadPress(Button_Input.CANCEL);
			// change the displayviews when cancelled
			view.getDisplayView().getPriceDisplay().setText("$0.00");
			view.getDisplayView().getItemDisplay().setText("Transaction_Cancelled");
			view.getDisplayView().getQuantityDisplay().setText("");
		});
	}

	/**
	 * Grid Layout for the product input buttons.
	 */
	private void buttonLayoutView() {

		/* set fonts of the buttons */
		buttonA.setFont(new Font("verdana", Font.BOLD, 18));
		buttonB.setFont(new Font("verdana", Font.BOLD, 18));
		buttonC.setFont(new Font("verdana", Font.BOLD, 18));
		button1.setFont(new Font("verdana", Font.BOLD, 18));
		button2.setFont(new Font("verdana", Font.BOLD, 18));
		button3.setFont(new Font("verdana", Font.BOLD, 18));
		button4.setFont(new Font("verdana", Font.BOLD, 18));
		vend.setFont(new Font("verdana", Font.BOLD, 15));
		additem.setFont(new Font("verdana", Font.BOLD, 13));
		cancel.setFont(new Font("verdana", Font.BOLD, 15));

		/* set the size of the buttons */
		buttonA.setPreferredSize(new Dimension(110, 60));
		buttonB.setPreferredSize(new Dimension(110, 60));
		buttonC.setPreferredSize(new Dimension(110, 60));
		button1.setPreferredSize(new Dimension(110, 60));
		button2.setPreferredSize(new Dimension(110, 60));
		button3.setPreferredSize(new Dimension(110, 60));
		button4.setPreferredSize(new Dimension(110, 60));
		vend.setPreferredSize(new Dimension(110, 60));
		additem.setPreferredSize(new Dimension(110, 60));
		cancel.setPreferredSize(new Dimension(110, 60));


		/* layout the buttons */
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 8, 5, 8);

		c.gridx = 0;
		c.gridy = 0;

		this.add(buttonA, c);
		c.gridx++;
		this.add(buttonB, c);
		c.gridx++;
		this.add(buttonC, c);
		c.gridx++;

		c.gridy++;
		c.gridx = 0;

		this.add(button1, c);
		c.gridx++;
		this.add(button2, c);
		c.gridx++;
		this.add(button3, c);
		c.gridx++;

		c.gridy++;
		c.gridx = 0;

		this.add(button4, c);
		c.gridx++;
		this.add(additem, c);
		c.gridx++;
		this.add(vend, c);
		c.gridx++;


		c.gridy++;
		c.gridx = 1;

		this.add(cancel, c);


	}


}
