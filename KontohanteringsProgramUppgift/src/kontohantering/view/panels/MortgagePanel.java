package kontohantering.view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kontohantering.data.Customer;
import kontohantering.data.Mortgage;

public class MortgagePanel extends JPanel {

	private Customer currCustomer;
	private Mortgage currMortgage;
	private boolean hasMortgage;
	private JLabel lblAmountInfo;
	private JLabel lblAmount;
	private JFormattedTextField fieldAmount;
	private JLabel lblYearsInfo;
	private JLabel lblYears;
	private JFormattedTextField fieldYears;
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
	
	public MortgagePanel (Customer currCustomer, boolean hasMortgage){
		setPreferredSize(new Dimension(380,300));
		
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		
		// Get customerinfo
		this.hasMortgage = hasMortgage;
		this.currCustomer = currCustomer;
		currMortgage = currCustomer.getMortgage();
		
		// Components init
		
		lblAmountInfo = new JLabel("Lånesumma");
		lblYearsInfo = new JLabel("Antal år");
		lblMonthPaymentInfo = new JLabel("Månadskostnad");
		lblMaxAmmountInfo = new JLabel("Maximalt lånebelopp");
		lblInterestInfo = new JLabel("Ränta");
		lblInterest = new JLabel(Double.toString(currMortgage.getInterest()));
		lblTotalCostInfo = new JLabel("Total lånekostnad");
		
		if (hasMortgage){
			mortgageInfo();
		} else {
			mortgageMake();
		}
	}
	
	private void mortgageInfo(){
		
		lblAmount = new JLabel(Double.toString(currMortgage.getAmount()));
		lblYears = new JLabel(Integer.toString(currMortgage.getYears()));
		lblMonthPayment = new JLabel(Double.toString(currMortgage.calculateMonthlyPayments(currMortgage.getYears(), currMortgage.getAmount())));
		lblMaxAmmount = new JLabel(Double.toString(currMortgage.getMaxAmount()));
		lblTotalCost = new JLabel(Double.toString(currMortgage.calculateTotalCost(currMortgage.getYears(), currMortgage.getAmount())));
		btnPay = new JButton("Slutbetala lån");
		
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
		
	}
}
