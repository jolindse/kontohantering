package kontohantering.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/* JPanel class for the side panel.
 * --------------------------------
 * Buttons for core function - add and search
 * 
 */
public class SideButtonPanel extends JPanel {
	
	private JButton btnNewCustomer;
	private JButton btnSearchCustomer;
	
	public SideButtonPanel () {
		
		// Set size
		this.setPreferredSize(new Dimension(120,600));
	

		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		// Init buttons set properties
		btnNewCustomer = new JButton("Ny kund");
		btnSearchCustomer = new JButton("Sök");
		
		Dimension buttonDim = new Dimension(110,40);
		btnNewCustomer.setMaximumSize(buttonDim);
		btnNewCustomer.setMinimumSize(buttonDim);
		btnSearchCustomer.setMaximumSize(buttonDim);
		btnSearchCustomer.setMinimumSize(buttonDim);
		
		btnNewCustomer.setFocusable(false);
		btnSearchCustomer.setFocusable(false);

		add(btnNewCustomer);
		add(Box.createRigidArea(new Dimension(40,40)));
		add(btnSearchCustomer);
		
	}
		
}
