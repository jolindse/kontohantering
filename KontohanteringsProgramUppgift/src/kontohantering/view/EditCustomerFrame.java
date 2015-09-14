package kontohantering.view;

import static kontohantering.view.GuiConstants.BANKBLUE;
import static kontohantering.view.GuiConstants.BUTTONDIM;
import static kontohantering.view.GuiConstants.FONTMEDIUM;
import static kontohantering.view.GuiConstants.LOGOSMALL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
/*
 * Edit account info class
 * -----------------------
 * Small JFrame class with a couple of panels
 * that uses the second constructor of InputForm
 * for editing an existing account.
 */
public class EditCustomerFrame extends JFrame {

	private Customer currCustomer;
	private Controller controller;
	
	public EditCustomerFrame(Customer currCustomer) {
		super("Redigera kund");
		// Give a handler for closing
		JFrame editFrame = this;
		
		controller = Controller.getController();
		this.currCustomer = currCustomer;
		
		
		setLayout(new BorderLayout());
		setSize(600, 500);
		setResizable(false);

		// Set native platform look And feel.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Setup top panel

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(500, 50));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

		JLabel lblTopName = new JLabel("Kunddetaljer");
		lblTopName.setForeground(BANKBLUE);
		lblTopName.setFont(FONTMEDIUM);

		topPanel.add(Box.createGlue());
		topPanel.add(lblTopName);

		// Setup side panel

		JPanel sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(76, 200));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(LOGOSMALL);
		sidePanel.add(lblLogo);

		// Setup inputform panel

		InputForm inputForm = new InputForm(currCustomer);

		// Setup bottom panel and buttons that resides in it.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JButton btnAddCustomer = new JButton("Spara");
		JButton btnCancel = new JButton("Avbryt");

		btnAddCustomer.setPreferredSize(BUTTONDIM);
		btnAddCustomer.setMaximumSize(BUTTONDIM);
		btnAddCustomer.setMinimumSize(BUTTONDIM);
		btnCancel.setPreferredSize(BUTTONDIM);
		btnCancel.setMaximumSize(BUTTONDIM);
		btnCancel.setMinimumSize(BUTTONDIM);
		btnAddCustomer.setFocusable(false);
		btnCancel.setFocusable(false);

		buttonPanel.add(Box.createGlue());
		buttonPanel.add(btnAddCustomer);
		buttonPanel.add(Box.createRigidArea(new Dimension(50, 40)));
		buttonPanel.add(btnCancel);
		buttonPanel.add(Box.createGlue());

		// Add panels and set visible

		add(topPanel, BorderLayout.NORTH);
		add(sidePanel, BorderLayout.WEST);
		add(inputForm, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);

		// ACTIONLISTENERS
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editFrame.dispose();
				
			}
		});
	}
	
}
