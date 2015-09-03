package kontohantering.view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomButtonPanel extends JPanel {

	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnFunds;
	private JButton btnMortage;
	
	public BottomButtonPanel (){
		
		// Set size 
		this.setPreferredSize(new Dimension(900,200));
		setMaximumSize(new Dimension(900,200));
		
		// Init button objects
		btnDeposit = new JButton("Sätt in");
		btnWithdraw = new JButton("Ta ut");
		btnFunds = new JButton("Fonder");
		btnMortage = new JButton("Lån");

		// Set layout and add buttons to panel
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		add(btnDeposit);
		add(Box.createHorizontalGlue());
		add(btnWithdraw);
		add(Box.createHorizontalGlue());
		add(btnFunds);
		add(Box.createHorizontalGlue());
		add(btnMortage);
		
	}
	
	
}
