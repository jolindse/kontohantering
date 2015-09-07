package kontohantering.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import static kontohantering.view.GuiConstants.*;

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

		btnDeposit.setPreferredSize(BUTTONDIM);
		btnWithdraw.setPreferredSize(BUTTONDIM);
		btnFunds.setPreferredSize(BUTTONDIM);
		btnMortage.setPreferredSize(BUTTONDIM);
		btnDeposit.setMaximumSize(BUTTONDIM);
		btnDeposit.setMinimumSize(BUTTONDIM);
		btnWithdraw.setMaximumSize(BUTTONDIM);
		btnWithdraw.setMinimumSize(BUTTONDIM);
		btnFunds.setMaximumSize(BUTTONDIM);
		btnFunds.setMinimumSize(BUTTONDIM);
		btnMortage.setMaximumSize(BUTTONDIM);
		btnMortage.setMinimumSize(BUTTONDIM);
		
		add(Box.createRigidArea(new Dimension(142, 100)));
		add(btnDeposit);
		add(Box.createHorizontalGlue());
		add(btnWithdraw);
		add(Box.createHorizontalGlue());
		add(btnFunds);
		add(Box.createHorizontalGlue());
		add(btnMortage);
		add(Box.createRigidArea(new Dimension(20, 100)));

		// Set disabled as start behaviour
		setDisabled();
	}
	
	public void setActive() {
		btnDeposit.setEnabled(true);
		btnWithdraw.setEnabled(true);
		btnFunds.setEnabled(true);
		btnMortage.setEnabled(true);
	}
	
	public void setDisabled() {
		btnDeposit.setEnabled(false);
		btnWithdraw.setEnabled(false);
		btnFunds.setEnabled(false);
		btnMortage.setEnabled(false);
	}

}
