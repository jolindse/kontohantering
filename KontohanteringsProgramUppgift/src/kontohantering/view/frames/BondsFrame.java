package kontohantering.view.frames;

import static kontohantering.view.GuiConstants.BANKBLUE;
import static kontohantering.view.GuiConstants.FONTMEDIUM;
import static kontohantering.view.GuiConstants.LOGOMINI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.IUpdateObserver;
import kontohantering.view.eventlistners.IUpdateSub;
import kontohantering.view.panels.BondsBuyPanel;
import kontohantering.view.panels.BondsCustomerInfoPanel;
import kontohantering.view.panels.BondsSellPanel;

public class BondsFrame extends KontoFrame implements IUpdateObserver {

	private Customer currCustomer;
	private JTabbedPane rightSidePanel;
	private	BondsSellPanel sellBondsPanel;
	private BondsBuyPanel buyBondsPanel;
	private boolean hasBonds;
	private boolean sellTabActive;
	private IUpdateSub updateSubject;
	private int tabIndex;
	
	public BondsFrame (Customer currCustomer) {

		super("Fonder",500,400);
		this.currCustomer = currCustomer;
		updateSubject = Controller.getController();
		updateSubject.registerObserver(this);

		sellTabActive = false;
		hasBonds = sellActivated();
				
		setLayout(new BorderLayout());
		
		
		// TOP LOGO PANEL
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(500, 50));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBorder(new EmptyBorder(20, 0, 0, 20));

		JLabel lblTopName = new JLabel("Fonder");
		lblTopName.setForeground(BANKBLUE);
		lblTopName.setFont(FONTMEDIUM);
		
		topPanel.add(Box.createGlue());
		topPanel.add(lblTopName);

		// LEFT SIDE PANEL - CUSTOMER INFO
		
		JPanel leftSidePanel = new BondsCustomerInfoPanel(currCustomer);
		
		// RIGHT SIDE PANEL 
		
	
		buyBondsPanel = new BondsBuyPanel(currCustomer);
		
		
		rightSidePanel = new JTabbedPane(JTabbedPane.TOP);
		rightSidePanel.setPreferredSize(new Dimension(380,300));
		rightSidePanel.addTab("Köp", buyBondsPanel);
		if (hasBonds){
			addSellTab();
		}
		rightSidePanel.setFocusable(false);
		rightSidePanel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				tabIndex = rightSidePanel.getSelectedIndex();
				System.out.println(tabIndex);
			}
		});
		
		add(topPanel,BorderLayout.NORTH);
		add(leftSidePanel,BorderLayout.CENTER);
		add(rightSidePanel,BorderLayout.EAST);
	
	}

	private void addSellTab() {
		sellBondsPanel = new BondsSellPanel(currCustomer);
		rightSidePanel.addTab("Sälj", sellBondsPanel);
		sellTabActive = true;
		
	}
	
	private boolean sellActivated(){
		boolean sell = false;
		if (currCustomer.getBonds().getNumberOfBondTypes() > 0){
			sell = true;
		} else {
			sell = false;
		}
		return sell;
	}
	
	@Override
	void closureBehaviour() {
		updateSubject.removeObserver(this);		
		this.dispose();
	}

	@Override
	public void updateGui() {
		hasBonds = sellActivated();
		if (hasBonds){
			if (tabIndex == 0){
				buyBondsPanel.updateInfo();
				if (!sellTabActive){
					addSellTab();
					rightSidePanel.setSelectedIndex(0);
				}
			}
			if (tabIndex == 1){
			sellBondsPanel.updateInfo();
			}
		} else {
			rightSidePanel.setSelectedIndex(0);
			buyBondsPanel.updateInfo();
			if (sellTabActive){
			rightSidePanel.removeTabAt(1);
			}
		}
	}
	
	
}
