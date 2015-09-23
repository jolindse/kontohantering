package kontohantering.view.panels;
import static kontohantering.view.GuiConstants.BUTTONDIM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kontohantering.data.Customer;
import kontohantering.data.Mortgage;

public class MortgagePanel extends JPanel {

	private Customer currCustomer;
	private Mortgage currMortgage;
	private boolean hasMortgage;
	private JLabel lblAmountInfo;
	private JLabel lblAmount;
	private JTextField fieldAmount;
	private JLabel lblYearsInfo;
	private JLabel lblYears;
	private JComboBox comboYears;
	private JLabel lblMonthPaymentInfo;
	private JLabel lblMonthPayment;
	private JLabel lblMaxAmmountInfo;
	private JLabel lblMaxAmmount;
	private JLabel lblInterestInfo;
	private JLabel lblInterest;
	private JLabel lblTotalCostInfo;
	private JLabel lblTotalCost;
	private JButton btnPay;
	private JButton btnLoan;
	private GridBagConstraints gc;
	private String[] yearArray;
	
	public MortgagePanel (Customer currCustomer, boolean hasMortgage){
		setPreferredSize(new Dimension(380,300));
		
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		
		// Get customerinfo
		this.hasMortgage = hasMortgage;
		this.currCustomer = currCustomer;
		currMortgage = currCustomer.getMortgage();
		
		// Components init
		
		lblAmountInfo = new JLabel("L�nesumma");
		lblYearsInfo = new JLabel("Antal �r");
		lblMonthPaymentInfo = new JLabel("M�nadskostnad");
		lblMaxAmmountInfo = new JLabel("Maximalt l�nebelopp");
		lblMaxAmmount = new JLabel(Double.toString(currMortgage.getMaxAmount()));
		lblInterestInfo = new JLabel("R�nta");
		lblInterest = new JLabel(Double.toString(currMortgage.getInterest()));
		lblTotalCostInfo = new JLabel("Total l�nekostnad");
		
		yearArray = new String[12];
		for (int i = 0; i < 12; i++){
			yearArray[i] = i+1 + " �r";
		}
		
		if (hasMortgage){
			mortgageInfo();
		} else {
			mortgageMake();
		}
	}
	
	private void mortgageInfo(){
		
		lblAmount = new JLabel(Double.toString(currMortgage.getAmount()).format("%.2f"));
		lblYears = new JLabel(Integer.toString(currMortgage.getYears()));
		lblMonthPayment = new JLabel(Double.toString(currMortgage.calculateMonthlyPayments(currMortgage.getYears(), currMortgage.getAmount())));
		lblTotalCost = new JLabel(Double.toString(currMortgage.calculateTotalCost(currMortgage.getYears(), currMortgage.getAmount())));
		btnPay = new JButton("Slutbetala l�n");
		btnPay.setPreferredSize(BUTTONDIM);
		btnPay.setMaximumSize(BUTTONDIM);
		btnPay.setMinimumSize(BUTTONDIM);
		
		// Amount row
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblAmountInfo, gc);
		
		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblAmount, gc);
		
		// Max amount row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblMaxAmmountInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblMaxAmmount, gc);
		
		
		// Interest row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblInterestInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblInterest, gc);
		
		// Year row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblYearsInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblYears, gc);
		
		// Monthly payment row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblMonthPaymentInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblMonthPayment, gc);
		
		// Total cost row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblTotalCostInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblTotalCost, gc);

		// Pay button row
		
		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(btnPay, gc);
		
	}
	
	private void mortgageMake(){
		
		fieldAmount = new JTextField(20);
		fieldAmount.setColumns(10);
		fieldAmount.setText("");
		fieldAmount.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				checkInput();
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		comboYears = new JComboBox(yearArray);
		comboYears.setFocusable(false);
		comboYears.setEditable(false);
		comboYears.setSelectedIndex(0);
		comboYears.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				checkInput();
				
			}
		});
		
		lblMonthPayment = new JLabel("");
		lblTotalCost = new JLabel("");
		btnLoan = new JButton("L�na");
		btnLoan.setPreferredSize(BUTTONDIM);
		btnLoan.setMaximumSize(BUTTONDIM);
		btnLoan.setMinimumSize(BUTTONDIM);
		
		// Amount row
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblAmountInfo, gc);
		
		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldAmount, gc);
		
		// Max amount row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblMaxAmmountInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblMaxAmmount, gc);
		
		
		// Interest row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblInterestInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblInterest, gc);
		
		// Year row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblYearsInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(comboYears, gc);
		
		// Monthly payment row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblMonthPaymentInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblMonthPayment, gc);
		
		// Total cost row
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblTotalCostInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblTotalCost, gc);

		// Pay button row
		
		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(btnLoan, gc);
		
	}
	
	private void checkInput(){
		boolean allOk = false;
		double currAmount = 0;
		int currYears = 0;
		
		if (fieldAmount.getText().length() > 0 && Pattern.matches("[a-zA-Z]+", fieldAmount.getText()) == false){
			currAmount = Double.parseDouble(fieldAmount.getText());
			if (currAmount <= currMortgage.getMaxAmount()) {
				currYears = comboYears.getSelectedIndex() + 1;
				fieldAmount.setBackground(Color.WHITE);
				allOk = true;
			} else {
				fieldAmount.setBackground(Color.RED);
				allOk = false;
			}
		}
		
		if(allOk){
			String currMonthPayment = Double.toString(currMortgage.calculateMonthlyPayments(currYears, currAmount));
			String currTotalCost = Double.toString(currMortgage.calculateTotalCost(currYears, currAmount));
			lblMonthPayment.setText(String.format("%.2f", currMonthPayment));
			lblTotalCost.setText(String.format("%.2f", currTotalCost));
		}
	}
}
