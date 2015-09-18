package kontohantering.view.panels;

import static kontohantering.view.GuiConstants.BUTTONDIM;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kontohantering.logic.Controller;
import kontohantering.view.frames.BondsFrame;

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
	private Controller controller;

	public BottomButtonPanel() {

		// Set size
		this.setPreferredSize(new Dimension(900, 100));

		// Set refernce to controller for button action
		controller = Controller.getController();

		// Init button objects set properties
		btnDeposit = new JButton("S�tt in");
		btnWithdraw = new JButton("Ta ut");
		btnFunds = new JButton("Fonder");
		btnMortage = new JButton("L�n");

		btnDeposit.setFocusable(false);
		btnWithdraw.setFocusable(false);
		btnFunds.setFocusable(false);
		btnMortage.setFocusable(false);

		// Set layout and add buttons to panel
		// In order to make BoxLayout honor dim for buttons
		// you need to set all three properties.

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		btnDeposit.setPreferredSize(BUTTONDIM);
		btnDeposit.setMaximumSize(BUTTONDIM);
		btnDeposit.setMinimumSize(BUTTONDIM);
		btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = JOptionPane.showInputDialog("Ange belopp f�r ins�ttning");
				if (amount != null) {
					controller.addFunds(amount);
				}
			}
		});
		btnWithdraw.setPreferredSize(BUTTONDIM);
		btnWithdraw.setMaximumSize(BUTTONDIM);
		btnWithdraw.setMinimumSize(BUTTONDIM);
		btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = JOptionPane.showInputDialog("Ange uttagsbeloppet");
				if (amount != null) {
					controller.withdrawFunds(amount);
				}
			}
		});
		btnFunds.setPreferredSize(BUTTONDIM);
		btnFunds.setMaximumSize(BUTTONDIM);
		btnFunds.setMinimumSize(BUTTONDIM);
		btnFunds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BondsFrame bondsFrame= new BondsFrame();
			}
		});
		btnMortage.setPreferredSize(BUTTONDIM);
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
