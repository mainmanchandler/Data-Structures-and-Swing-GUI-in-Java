import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * View and update the display GUI
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class DisplayView extends JPanel {
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying vending display.
	 */

	/**
	 * Displays.
	 */
	JLabel priceDisplay = new JLabel();
	JLabel quantityDisplay = new JLabel();
	JLabel itemDisplay = new JLabel();

	@SuppressWarnings("unused")
	private final VendModel model;
	@SuppressWarnings("unused")
	private final VendView view;


	/**
	 * The view constructor for the button layout
	 * 
	 * @param vendView
	 * 
	 * @param newModel;
	 * 
	 */
	public DisplayView(VendView vendView, final VendModel newModel) {

		this.model = newModel;
		this.view = vendView;
		this.buttonLayoutView();
		this.setBackground(Color.PINK);
		// Border blackline = BorderFactory.createLineBorder(Color.black);
		// this.setBorder(blackline);
	}


	/**
	 * Grid Layout for the product input buttons.
	 */
	private void buttonLayoutView() {


		/* set elements on display */
		priceDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		quantityDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		itemDisplay.setHorizontalAlignment(SwingConstants.CENTER);


		priceDisplay.setText("$0.00");
		quantityDisplay.setText("Choose-Items");
		itemDisplay.setText("Item-Choice:");


		priceDisplay.setPreferredSize(new Dimension(400, 90));
		quantityDisplay.setPreferredSize(new Dimension(400, 90));
		itemDisplay.setPreferredSize(new Dimension(400, 90));


		priceDisplay.setOpaque(true);
		quantityDisplay.setOpaque(true);
		itemDisplay.setOpaque(true);


		priceDisplay.setBackground(Color.black);
		quantityDisplay.setBackground(Color.black);
		itemDisplay.setBackground(Color.black);


		priceDisplay.setForeground(Color.white);
		quantityDisplay.setForeground(Color.white);
		itemDisplay.setForeground(Color.white);


		// priceDisplay.setFont(priceDisplay.getFont().deriveFont(52.0f));
		priceDisplay.setFont(new Font("verdana", Font.BOLD, 25));

		// quantityDisplay.setFont(priceDisplay.getFont().deriveFont(35.0f));
		quantityDisplay.setFont(new Font("verdana", Font.BOLD, 20));

		itemDisplay.setFont(new Font("verdana", Font.BOLD, 20));


		/* layout the buttons */
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 5, 10);

		c.gridx = 1;
		c.gridy = 0;

		this.add(priceDisplay, c);

		c.gridy = 1;
		c.gridx = 1;

		this.add(itemDisplay, c);

		c.gridy = 2;
		c.gridx = 1;

		this.add(quantityDisplay, c);


	}


	/**
	 * Getters for the display JLabels();
	 * 
	 * @return JLabel
	 */
	public JLabel getPriceDisplay() {
		return priceDisplay;
	}

	public JLabel getQuantityDisplay() {
		return quantityDisplay;
	}

	public JLabel getItemDisplay() {
		return itemDisplay;
	}

}