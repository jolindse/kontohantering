package kontohantering.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import kontohantering.data.Customer;

public class InputForm extends JPanel {

	private GridBagConstraints gc;

	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblPersNumber;
	private JLabel lblInitialDeposit;
	private JLabel lblAccountNumber;
	private JLabel lblAccountNumberValue;
	private JLabel lblAssets;
	private JLabel lblAssetsValue;
	private JLabel lblBonds;
	private JLabel lblBondsValue;
	private JLabel lblDebt;
	private	JLabel lblDebtValue;
	private JLabel lblCustomerRating;
	private JLabel lblCustomerRatingValue;
	
	private JCheckBox chkbxInitialDeposit;

	private JTextField fieldName;
	private JTextField fieldLastName;
	private JFormattedTextField fieldPersNumber;
	private JFormattedTextField fieldInitialDeposit;

	public InputForm() {
		/*
		 * This constructor is used when a new account will be setup
		 */
		initialSetup();
		newAccount();
	}
	
	public InputForm(int i) {
	//public InputForm(Customer currCustomer) {
		/*
		 * This constructor is used when a existing account is being edited.
		 */
		initialSetup();
		editAccount();
	}

	private void initialSetup() {
	
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		
		// Init components of form

		lblName = new JLabel("Förnamn:");
		lblLastName = new JLabel("Efternamn:");
		lblPersNumber = new JLabel("Personnummer:");
		lblInitialDeposit = new JLabel("Summa:");
		lblInitialDeposit.setEnabled(false);

		fieldName = new JTextField(20);
		fieldLastName = new JTextField(20);

		fieldPersNumber = new JFormattedTextField();
		fieldPersNumber.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("########-####"))));
		fieldPersNumber.setColumns(11);

		// First row

		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(1, 10, 10, 10);
		add(lblName, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldName, gc);

		// Second row

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblLastName, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldLastName, gc);

		// Third row

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblPersNumber, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldPersNumber, gc);

	}

	private void newAccount() {
		/*
		 *  New account elements
		 */

		chkbxInitialDeposit = new JCheckBox("Initial insättning?");

		fieldInitialDeposit = new JFormattedTextField();
		fieldInitialDeposit.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
		fieldInitialDeposit.setColumns(20);
		fieldInitialDeposit.setEnabled(false);
		chkbxInitialDeposit.setFocusable(false);

		chkbxInitialDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean deposit = chkbxInitialDeposit.isSelected();
				fieldInitialDeposit.setEnabled(deposit);
				lblInitialDeposit.setEnabled(deposit);

			}
		});

		// Checkbox initial deposit

		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(chkbxInitialDeposit, gc);

		// Inputfield initial deposit

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblInitialDeposit, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldInitialDeposit, gc);
	}
	
	private void editAccount() {
		/*
		 *  Edit account elements
		 */
	
		lblAccountNumber = new JLabel("Kontonummer:");
		lblAccountNumberValue = new JLabel("");
		lblAssets = new JLabel("Saldo:");
		lblAssetsValue = new JLabel("");
		lblBonds = new JLabel("Fondvärde:");
		lblBondsValue = new JLabel("");
		lblDebt = new JLabel("Skulder:");
		lblDebtValue = new JLabel("");
		lblCustomerRating = new JLabel("Kundklass");
		lblCustomerRatingValue = new JLabel("");

		// Account number

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblAccountNumber, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblAccountNumberValue, gc);
		
		// Account balance

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblAssets, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblAssetsValue, gc);
		
		// Bonds

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblBonds, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblBondsValue, gc);
		
		// Debts

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblDebt, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblDebtValue, gc);
		
		// Customer rating

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblCustomerRating, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblCustomerRatingValue, gc);
	}
}
