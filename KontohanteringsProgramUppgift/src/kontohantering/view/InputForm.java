package kontohantering.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import kontohantering.data.Customer;

/*
 *  Input form class 
 *  ---------------------
 *  Used in new customer and edit customer frame
 */

public class InputForm extends JPanel {

	Customer currCustomer;

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
	private JLabel lblDebtValue;
	private JLabel lblCustomerRating;
	private JLabel lblCustomerRatingValue;

	private JCheckBox chkbxInitialDeposit;

	private JTextField fieldName;
	private JTextField fieldLastName;
	private JTextField fieldInitialDeposit;

	private JFormattedTextField fieldPersNumber;
	

	public InputForm() {
		/*
		 * This constructor is used when a new account will be setup
		 */
		try {
			initialSetup();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newAccount();
	}

	public InputForm(Customer currCustomer) {
		// public InputForm(Customer currCustomer) {
		/*
		 * This constructor is used when a existing account is being edited.
		 */
		try {
			initialSetup();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.currCustomer = currCustomer;
		editAccount();
	}

	private void initialSetup() throws ParseException {

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

		/*
		 * Second attempt
		 */

		fieldPersNumber = new JFormattedTextField();
		MaskFormatter fieldPersNumberMask;
		fieldPersNumberMask = new MaskFormatter("########-####");
		fieldPersNumberMask.setValueClass(String.class);
		fieldPersNumberMask.setPlaceholderCharacter('*');
		DefaultFormatterFactory persNumberFormater = new DefaultFormatterFactory(fieldPersNumberMask);
		fieldPersNumber.setFormatterFactory(persNumberFormater);
		
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
		 * New account elements
		 */

		fieldInitialDeposit = new JTextField(20);

		chkbxInitialDeposit = new JCheckBox("Initial insättning?");
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
		 * Edit account elements
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

		populateFields();

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

	// GETTERS/SETTERS

	public String getFirstName() {
		return fieldName.getText();
	}

	public String getLastName() {
		return fieldLastName.getText();
	}

	public long getPersNumber() {
		try {
			fieldPersNumber.commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strPersNr = ((String) fieldPersNumber.getValue()).replaceAll("[-]", "");
		
		return Long.parseLong(strPersNr);
	}

	public boolean getInitialDeposit() {
		return chkbxInitialDeposit.isSelected();
	}

	public double getInitialDepositAmount() {
		return Double.parseDouble(fieldInitialDeposit.getText());
	}

	// METHODS
	public void clearFields() {
		fieldInitialDeposit.setText("");
		fieldLastName.setText("");
		fieldName.setText("");
		fieldPersNumber.setText("");
		chkbxInitialDeposit.setSelected(false);
	}

	public void populateFields() {
		fieldLastName.setText(currCustomer.getLastName());
		fieldName.setText(currCustomer.getName());
		fieldPersNumber.setText(Long.toString(currCustomer.getPersNumber()));
		lblAssetsValue.setText(Double.toString(currCustomer.getAccountBalance()));
		lblAccountNumberValue.setText(Long.toString(currCustomer.getAccountNumber()));
		lblBondsValue.setText(Double.toString(currCustomer.getBondsAmount()));
		lblDebtValue.setText(Double.toString(currCustomer.getMortgage()));
		String custRating = "" + currCustomer.getCustomerRating();
		lblCustomerRatingValue.setText(custRating);

	}
	
	public boolean userInputOk() {
		
		if(checkNameFields() && checkNumFields()){
			return true;
		} else {
			return false;
		}

	}
	
	public boolean newUserInputOK() {
		if (checkNameFields() && checkNumFields() && checkInitDeposit() ){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkNameFields() {
		boolean nameOK = false;
		
		if (fieldName.getText().length() > 0){
			fieldName.setBackground(Color.WHITE);
			nameOK = true;
		} else {
			fieldName.setBackground(Color.RED);
			return false;
		}
		
		if (fieldLastName.getText().length() > 0){
			fieldLastName.setBackground(Color.WHITE);
			nameOK = true;
		} else {
			fieldLastName.setBackground(Color.RED);
			return false;
		}
		
		return nameOK;
	}

	private boolean checkNumFields() {
		
		boolean numOK = false;
		
		try {
			fieldPersNumber.commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strPersNr = ((String) fieldPersNumber.getValue()).replaceAll("[-]", "");
	
		if (checkPersNr(strPersNr)) {
			fieldPersNumber.setBackground(Color.WHITE);
			numOK = true;
		}else{
			System.out.println("Retunerar kasst personnummer");
			fieldPersNumber.setBackground(Color.RED);
			numOK = false;
		}
		
		
		return numOK;
		
	}

	private boolean checkInitDeposit() {
		boolean depOK = true;
		
		if (chkbxInitialDeposit.isSelected()){
			String strInitDeposit = fieldInitialDeposit.getText();
			if(Pattern.matches("[a-zA-Z]+", strInitDeposit) == false && fieldInitialDeposit.getText().length() > 0){
				fieldInitialDeposit.setBackground(Color.WHITE);
				depOK = true;
			} else {
				fieldInitialDeposit.setBackground(Color.RED);
				depOK = false;
			}
		}
		
		return depOK;
	}
	
	private boolean checkPersNr(String strIn) {

		String strYear = strIn.substring(0, 4);
		String strMonth = strIn.substring(4, 6);
		String strDay = strIn.substring(6, 8);
		String number = strIn.substring(8, 12);
		String returnStr = "";

		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(strDay);

		
		if (year > 1890 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
			returnStr += strYear;
		}

		if (month > 0 && month < 13) {
			returnStr += strMonth;
		}
		
		if (day > 0 && day <= returnMonthDays(year, month)) {
			returnStr += strDay;
		}

		returnStr += number;

		if (returnStr.length() == 12) {
			return true;
		} else {
			return false;
		}

	}

	private int returnMonthDays(int year, int month) {
		int returnValue = 0;
		int[] longMonths = { 1, 3, 5, 7, 8, 10, 12 };
		int[] shortMonths = { 4, 6, 9, 11 };

		if (month == 2) {
			if (year % 4 != 0) {
				returnValue = 27;
			}

			if (year % 4 == 0 && year % 100 == 0 && year % 400 == 0) {
				returnValue = 28;
			}else{
				returnValue = 27;
			}
			
		}else{

		for (int checkMonthL : longMonths) {
			if (month == checkMonthL) {
				returnValue = 31;
			}
		}

		for (int checkMonthS : shortMonths) {
			if (month == checkMonthS && month != 2) {
				returnValue = 30;
			}

		}
		}
		return returnValue;
	}
}
