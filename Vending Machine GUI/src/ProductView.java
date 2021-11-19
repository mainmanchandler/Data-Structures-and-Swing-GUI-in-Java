import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


/**
 * GUI for product view (left of side of the jframe)
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class ProductView extends JPanel {
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying vending input for output.
	 */


	/**
	 * The vending model.
	 */
	@SuppressWarnings("unused")
	private final VendModel model;


	/**
	 * The view constructor for the product layout
	 * 
	 * @param vendView is the view that all derived classes use
	 * 
	 * @param newModel is the model that all derived classes use;
	 * 
	 */
	public ProductView(VendView vendView, final VendModel newModel) {

		this.model = newModel;
		this.productLayoutView();
		this.setBackground(Color.cyan);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
	}

	/**
	 * Grid Layout for Products, images.
	 */
	private void productLayoutView() {

		// grid layout
		this.setLayout(new GridBagLayout());

		// Pull the images for all of the products inside of the vending machine
		JLabel cupcake = null;
		JLabel dabpug = null;
		JLabel donut = null;
		JLabel icecream = null;

		JLabel orange = null;
		JLabel pug = null;
		JLabel puggis = null;
		JLabel pusheen = null;

		JLabel ramen = null;
		JLabel rice_bowl = null;
		JLabel sushi = null;
		JLabel sushi1 = null;

		try {

			BufferedImage image1 = ImageIO.read(new File("images/cupcake.jpg"));
			cupcake = new JLabel(new ImageIcon(image1.getScaledInstance(100, 100, ABORT)));

			BufferedImage image2 = ImageIO.read(new File("images/dabpug.png"));
			dabpug = new JLabel(new ImageIcon(image2.getScaledInstance(100, 100, ABORT)));

			BufferedImage image3 = ImageIO.read(new File("images/donut.jpg"));
			donut = new JLabel(new ImageIcon(image3.getScaledInstance(100, 100, ABORT)));

			BufferedImage image4 = ImageIO.read(new File("images/icecream.jpg"));
			icecream = new JLabel(new ImageIcon(image4.getScaledInstance(100, 100, ABORT)));


			BufferedImage image5 = ImageIO.read(new File("images/orange.png"));
			orange = new JLabel(new ImageIcon(image5.getScaledInstance(100, 100, ABORT)));

			BufferedImage image6 = ImageIO.read(new File("images/pug.png"));
			pug = new JLabel(new ImageIcon(image6.getScaledInstance(100, 100, ABORT)));

			BufferedImage image7 = ImageIO.read(new File("images/puggis.png"));
			puggis = new JLabel(new ImageIcon(image7.getScaledInstance(100, 100, ABORT)));

			BufferedImage image8 = ImageIO.read(new File("images/pusheen.png"));
			pusheen = new JLabel(new ImageIcon(image8.getScaledInstance(100, 100, ABORT)));


			BufferedImage image9 = ImageIO.read(new File("images/ramen.png"));
			ramen = new JLabel(new ImageIcon(image9.getScaledInstance(100, 100, ABORT)));

			BufferedImage image10 = ImageIO.read(new File("images/rice_bowl.png"));
			rice_bowl = new JLabel(new ImageIcon(image10.getScaledInstance(100, 100, ABORT)));

			BufferedImage image11 = ImageIO.read(new File("images/sushi.png"));
			sushi = new JLabel(new ImageIcon(image11.getScaledInstance(100, 100, ABORT)));

			BufferedImage image12 = ImageIO.read(new File("images/sushi1.png"));
			sushi1 = new JLabel(new ImageIcon(image12.getScaledInstance(100, 100, ABORT)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Constraints for each
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 60, 5, 60);

		c.gridx = 0;
		c.gridy = 1;
		// c.weightx = 1;


		this.add(cupcake, c);
		c.gridy++;
		this.add(new JLabel("A1 : $4.50", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(dabpug, c);
		c.gridy++;
		this.add(new JLabel("A2 : $10.00", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(donut, c);
		c.gridy++;
		this.add(new JLabel("A3 : $3.25", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(icecream, c);
		c.gridy++;
		this.add(new JLabel("A4 : $7.75", SwingConstants.CENTER), c);
		c.gridy++;


		c.gridx++;
		c.gridy = 1;

		this.add(orange, c);
		c.gridy++;
		this.add(new JLabel("B1 : $3.50", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(pug, c);
		c.gridy++;
		this.add(new JLabel("B2 : $1.25", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(puggis, c);
		c.gridy++;
		this.add(new JLabel("B3 : $53.50", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(pusheen, c);
		c.gridy++;
		this.add(new JLabel("B4 : $12.50", SwingConstants.CENTER), c);
		c.gridy++;


		c.gridx++;
		c.gridy = 1;

		this.add(ramen, c);
		c.gridy++;
		this.add(new JLabel("C1 : $8.50", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(rice_bowl, c);
		c.gridy++;
		this.add(new JLabel("C2 : $8.75", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(sushi, c);
		c.gridy++;
		this.add(new JLabel("C3 : $6.50", SwingConstants.CENTER), c);
		c.gridy++;
		this.add(sushi1, c);
		c.gridy++;
		this.add(new JLabel("C4 : $7.50", SwingConstants.CENTER), c);
		c.gridy++;

	}
}
