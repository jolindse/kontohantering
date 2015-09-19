package kontohantering.view.frames;

import static kontohantering.view.GuiConstants.BANKBLUE;
import static kontohantering.view.GuiConstants.BUTTONDIM;
import static kontohantering.view.GuiConstants.FONTMEDIUM;
import static kontohantering.view.GuiConstants.LOGOSMALL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.FormEvent;
import kontohantering.view.eventlistners.IFormListener;
import kontohantering.view.formstables.InputForm;

/*
 * Add a new customer frame class
 * ------------------------------
 * Window to pop up to put in user details adding a new customer.
 */

public class NewCustomerFrame extends KontoFrame {

	private static boolean isActive;
	private IFormListener formListener;

	public NewCustomerFrame() {

		super("Lägg till ny kund",600, 500);
	
			setLayout(new BorderLayout());

			// Set formlistener
			formListener = Controller.getController();
		
			// Setup top panel

			JPanel topPanel = new JPanel();
			topPanel.setPreferredSize(new Dimension(500, 50));
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			topPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

			JLabel lblTopName = new JLabel("Ny kund");
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

			// Setup input form panel

			InputForm inputForm = new InputForm();

			// Setup bottom panel and buttons that resides in it.

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(500, 100));
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

			JButton btnAddCustomer = new JButton("Lägg till kund");
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

			// ACTIONLISTENERS

			btnAddCustomer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (inputForm.newUserInputOK()) {
						FormEvent fevent;
						if (!inputForm.getInitialDeposit()) {
							fevent = new FormEvent(e.getSource(), inputForm.getFirstName(), inputForm.getLastName(),
									inputForm.getPersNumber(), 0, "newCustomer");
						} else {
							fevent = new FormEvent(e.getSource(), inputForm.getFirstName(), inputForm.getLastName(),
									inputForm.getPersNumber(), inputForm.getInitialDepositAmount(), "newCustomer");
						}
						formListener.formEventOccured(fevent);
						inputForm.clearFields();
						closureBehaviour();
					}
				}
			});

			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					closureBehaviour();

				}
			});

	}

	@Override
	void closureBehaviour() {
		this.dispose();		
	}

}
