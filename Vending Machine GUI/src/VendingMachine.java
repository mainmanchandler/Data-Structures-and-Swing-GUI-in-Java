import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Main class for the Vending Machine.
 *
 * @author Chandler Mayberry
 * @version 2021-03-19
 */
public class VendingMachine {

	public static void main(String[] args) {
		VendModel model = new VendModel();
		VendView vv = new VendView(model);

		final JFrame frame = new JFrame();
		frame.setContentPane(vv);
		frame.setMinimumSize(new Dimension(900, 600));
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false); // makes it so the user cant change the size of the application
		frame.setTitle("VendingMachine");

	}

}
