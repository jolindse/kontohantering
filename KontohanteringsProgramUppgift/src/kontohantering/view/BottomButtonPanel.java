package kontohantering.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Class for lower button panel
 * ----------------------------
 * 
 * All other buttons needed for operation.
 */
public class BottomButtonPanel extends JPanel {

	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnFunds;
	private JButton btnMortage;

	public BottomButtonPanel() {

		// Set size
		this.setPreferredSize(new Dimension(900, 100));

		// Init button objects set properties
		btnDeposit = new JButton("Sätt in");
		btnWithdraw = new JButton("Ta ut");
		btnFunds = new JButton("Fonder");
		btnMortage = new JButton("Lån");

		btnDeposit.setFocusable(false);
		btnWithdraw.setFocusable(false);
		btnFunds.setFocusable(false);
		btnMortage.setFocusable(false);

		// Set layout and add buttons to panel
		// In order to make BoxLayout honor dim for buttons
		// you need to set all three properties.
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		Dimension buttonDim = new Dimension(120, 40);

		btnDeposit.setPreferredSize(buttonDim);
		btnWithdraw.setPreferredSize(buttonDim);
		btnFunds.setPreferredSize(buttonDim);
		btnMortage.setPreferredSize(buttonDim);

		btnDeposit.setMaximumSize(buttonDim);
		btnDeposit.setMinimumSize(buttonDim);
		btnWithdraw.setMaximumSize(buttonDim);
		btnWithdraw.setMinimumSize(buttonDim);
		btnFunds.setMaximumSize(buttonDim);
		btnFunds.setMinimumSize(buttonDim);
		btnMortage.setMaximumSize(buttonDim);
		btnMortage.setMinimumSize(buttonDim);
		
		add(Box.createRigidArea(new Dimension(132, 100)));
		add(btnDeposit);
		add(Box.createHorizontalGlue());
		add(btnWithdraw);
		add(Box.createHorizontalGlue());
		add(btnFunds);
		add(Box.createHorizontalGlue());
		add(btnMortage);
		add(Box.createRigidArea(new Dimension(20, 100)));

	}

}
