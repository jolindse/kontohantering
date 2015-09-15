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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontohantering.logic.Controller;

import static kontohantering.view.GuiConstants.*;

/* JPanel class for the side panel.
 * --------------------------------
 * Buttons for core function - add and search
 * 
 */
public class SideButtonPanel extends JPanel {

	private JButton btnNewCustomer;
	private JButton btnSearchCustomer;
	private JButton btnEditCustomer;
	private JButton btnTerminateCustomer;

	private GridBagConstraints gc;

	private String strSearch;
	private Controller controller;
	
	public SideButtonPanel() {

		// Set size
		this.setPreferredSize(new Dimension(130, 600));
		SideButtonPanel currPanel = this;

		controller = Controller.getController();
		// Init buttons set properties
		btnNewCustomer = new JButton("Ny kund");
		btnSearchCustomer = new JButton("Sök");
		btnEditCustomer = new JButton("Ändra");
		btnTerminateCustomer = new JButton("Avsluta konto");

		btnNewCustomer.setPreferredSize(BUTTONDIM);
		btnSearchCustomer.setPreferredSize(BUTTONDIM);
		btnEditCustomer.setPreferredSize(BUTTONDIM);
		btnTerminateCustomer.setPreferredSize(BUTTONDIM);

		btnNewCustomer.setFocusable(false);
		btnSearchCustomer.setFocusable(false);
		btnEditCustomer.setFocusable(false);
		btnTerminateCustomer.setFocusable(false);

		// ACTIONLISTENERS
		
		// New customer button
		btnNewCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewCustomerFrame newCustomer = new NewCustomerFrame();

			}
		});
		
		// Search button
		btnSearchCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			strSearch = (String)JOptionPane.showInputDialog(getRootPane(), "Sök på namn, personnummer\n eller kontonummer", "Sök kund", JOptionPane.PLAIN_MESSAGE, LOGOMINI, null, null);
			System.out.println(strSearch);
			controller.SearchDB(strSearch);
			}
		});
		
		// Edit button
		
		btnEditCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.editCustomer();
			}
		});

		// Set up gridbaglayout with a handler.
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();

		// Set edit and terminate buttons non visible by default.

		setViewMode();
	}

	public void setEditMode() {

		// First row

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
		gc.weighty = 0;
		gc.insets = new Insets(0, 10, 30, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnNewCustomer, gc);

		// Third row
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0;
		gc.insets = new Insets(0, 10, 30, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnEditCustomer, gc);

		// Forth row
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.insets = new Insets(0, 10, 30, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnTerminateCustomer, gc);

		btnEditCustomer.setVisible(true);
		btnTerminateCustomer.setVisible(true);
	}

	public void setViewMode() {

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
		gc.insets = new Insets(0, 10, 30, 0);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnNewCustomer, gc);

		btnEditCustomer.setVisible(false);
		btnTerminateCustomer.setVisible(false);

	}

}
