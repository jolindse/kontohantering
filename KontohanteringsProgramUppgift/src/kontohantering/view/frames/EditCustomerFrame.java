package kontohantering.view.frames;

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
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.FormEvent;
import kontohantering.view.eventlistners.IFormListener;
import kontohantering.view.formstables.InputForm;
/*
 * Edit account info class
 * -----------------------
 * Small JFrame class with a couple of panels
 * that uses the second constructor of InputForm
 * for editing an existing account.
 */
public class EditCustomerFrame extends KontoFrame {

	private Controller controller;
	private Customer currCustomer;
	private IFormListener formListener;
	
	public EditCustomerFrame(Customer currCustomer) {
		super("Redigera kund",600, 500);
		
		JFrame editFrame = this;
		controller = Controller.getController();
		formListener = controller;
		this.currCustomer = currCustomer;
		
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

		// Setup input form panel

		InputForm inputForm = new InputForm(currCustomer);

		// Setup bottom panel and buttons that resides in it.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 100));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JButton btnSaveCustomer = new JButton("Spara");
		JButton btnCancel = new JButton("Avbryt");

		btnSaveCustomer.setPreferredSize(BUTTONDIM);
		btnSaveCustomer.setMaximumSize(BUTTONDIM);
		btnSaveCustomer.setMinimumSize(BUTTONDIM);
		btnCancel.setPreferredSize(BUTTONDIM);
		btnCancel.setMaximumSize(BUTTONDIM);
		btnCancel.setMinimumSize(BUTTONDIM);
		btnSaveCustomer.setFocusable(false);
		btnCancel.setFocusable(false);

		buttonPanel.add(Box.createGlue());
		buttonPanel.add(btnSaveCustomer);
		buttonPanel.add(Box.createRigidArea(new Dimension(50, 40)));
		buttonPanel.add(btnCancel);
		buttonPanel.add(Box.createGlue());

		// Add panels and set visible

		add(topPanel, BorderLayout.NORTH);
		add(sidePanel, BorderLayout.WEST);
		add(inputForm, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);


		// ACTIONLISTENERS
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closureBehaviour();
				
			}
		});
		
		btnSaveCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputForm.userInputOk()){
				
					FormEvent ev = new FormEvent(this, currCustomer, inputForm.getFirstName(), inputForm.getLastName(), inputForm.getPersNumber());
					formListener.editUserFormEvent(ev);
					
				/*currCustomer.setLastName(inputForm.getLastName());
				currCustomer.setName(inputForm.getFirstName());
				currCustomer.setPersNumber(inputForm.getPersNumber());*/
				//controller.updateOutput();
				closureBehaviour();
				}
			}
		});
	
	}

	@Override
	void closureBehaviour() {
		this.dispose();
		
	}
	
}
