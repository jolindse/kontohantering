package kontohantering.view.frames;

import static kontohantering.view.GuiConstants.BANKBLUE;
import static kontohantering.view.GuiConstants.FONTMEDIUM;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.IUpdateObserver;
import kontohantering.view.eventlistners.IUpdateSub;
import kontohantering.view.panels.CustomerInfoSidePanel;

public class MortgageFrame extends KontoFrame implements IUpdateObserver {
	
	private Customer currCustomer;
	private IUpdateSub updateSubject;
	private boolean hasMortage;
	
	public MortgageFrame(Customer currCustomer){
		super("Lån",500,400);
		this.currCustomer = currCustomer;
		updateSubject = Controller.getController();
		updateSubject.registerObserver(this);
		
		hasMortage = currCustomer.getMortgage().getHasMortgage();
		
		// TOP LOGO PANEL
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(500, 50));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBorder(new EmptyBorder(20, 0, 0, 20));

		JLabel lblTopName = new JLabel("Lån");
		lblTopName.setForeground(BANKBLUE);
		lblTopName.setFont(FONTMEDIUM);
		
		topPanel.add(Box.createGlue());
		topPanel.add(lblTopName);
		
		// LEFT SIDE PANEL - CUSTOMER INFO
		
		JPanel leftSidePanel = new CustomerInfoSidePanel(currCustomer);
				
		// RIGHT SIDE PANEL - MORTGAGE INFO/SETUP
		
		
	}

	@Override
	void closureBehaviour() {
		this.dispose();

	}

	@Override
	public void updateGui() {
		// TODO Auto-generated method stub
		
	}

}
