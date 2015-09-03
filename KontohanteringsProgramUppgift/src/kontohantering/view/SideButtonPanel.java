package kontohantering.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SideButtonPanel extends JPanel {
	
	private JButton btnNewCustomer;
	private JButton btnSearchCustomer;
	
	public SideButtonPanel () {
		
		// Set size
		this.setPreferredSize(new Dimension(200,600));
		setMaximumSize(new Dimension(200,600));
		
		// Init buttons
		btnNewCustomer = new JButton("Ny kund");
		btnSearchCustomer = new JButton("Sök kund");
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(btnNewCustomer);
		add(Box.createVerticalGlue());
		add(btnSearchCustomer);
		
		
	}
		
}
