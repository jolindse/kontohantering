package kontohantering.view.panels;

import static kontohantering.view.GuiConstants.LOGOSMALL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kontohantering.data.Customer;

public class BondsCustomerInfoPanel extends JPanel {

	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblPersNumber;
	private JLabel lblAccountNumber;
	private JLabel lblEmpty;
	
	
	public BondsCustomerInfoPanel(Customer currCustomer){
		setMaximumSize(new Dimension(120,300));
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(LOGOSMALL);
		
		
		lblFirstName = new JLabel(currCustomer.getName());
		lblLastName = new JLabel(currCustomer.getLastName());
		lblPersNumber = new JLabel(Long.toString(currCustomer.getPersNumber()));
		lblAccountNumber = new JLabel(Long.toString(currCustomer.getAccountNumber()));
		lblEmpty = new JLabel("");
		
		// Logo
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblLogo, gc);
		
		// Name
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(40, 0, 0, 10);
		add(lblFirstName, gc);
		
		// Surname
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(lblLastName, gc);
		
		// Personal number
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(lblPersNumber, gc);
		
		// Account number
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(lblAccountNumber, gc);
	
		// Empty container

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lblEmpty, gc);
	}

}
