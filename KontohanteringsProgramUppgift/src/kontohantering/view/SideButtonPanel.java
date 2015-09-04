package kontohantering.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public SideButtonPanel() {

		// Set size
		this.setPreferredSize(new Dimension(120, 600));
		
		// Init buttons set properties
		btnNewCustomer = new JButton("Ny kund");
		btnSearchCustomer = new JButton("Sök");

		Dimension buttonDim = new Dimension(110, 40);
		btnNewCustomer.setPreferredSize(buttonDim);
		btnSearchCustomer.setPreferredSize(buttonDim);
		
		btnNewCustomer.setFocusable(false);
		btnSearchCustomer.setFocusable(false);


		// ActionListeners
		
		btnNewCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewCustomerFrame newCustomer = new NewCustomerFrame();
				
			}
		});
		
		
		
		// Set up gridbaglayout with a handler.
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		/*
		 * Place items. 
		 * --------------------- 
		 * Starting with first row
		 */
		gc.gridy = 0;
		gc.gridx = 0;

		gc.weightx = 1;
		gc.weighty = 0;
		gc.insets = new Insets(0, 10, 30, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnSearchCustomer, gc);

		// Second row
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.insets = new Insets(0,10,0,0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnNewCustomer, gc);

	}

	
}
