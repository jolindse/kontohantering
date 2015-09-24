package kontohantering.view.panels;

import static kontohantering.view.GuiConstants.BUTTONDIM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kontohantering.data.Bonds;
import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.IBondsListener;
import kontohantering.view.frames.BondsFrame;

public class BondsSellPanel extends JPanel {

	private Map<String,Integer> currBondMap;
	private Bonds currBonds;
	private IBondsListener bondsListener;
	private JComboBox bondsBox;
	private JLabel lblPrice;
	private JLabel lblEmpty;
	private JLabel lblOwned;
	private JLabel lblOwnedInfo;
	private JLabel lblOwnedWorth;
	private JLabel lblOwnedWorthInfo;
	private JLabel lblNumberToSell;
	private JLabel lblNumberToSellWorth;
	private JLabel lblNumberToSellWorthInfo;
	private String currKey;
	private JButton btnSell;
	private JTextField fieldAmount;
	private BondsFrame parent;

	public BondsSellPanel(Customer currCustomer, BondsFrame parent) {

		setPreferredSize(new Dimension(380, 300));

		currBonds = currCustomer.getBonds();
		currBondMap = currCustomer.getBonds().getBondMap();
		
		bondsListener = Controller.getController();
		this.parent = parent;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Dimension boxDim = new Dimension(200, 40);

		bondsBox = new JComboBox<>(currBonds.getOwnedBondsNames());
		bondsBox.setSelectedIndex(0);
		bondsBox.setFocusable(false);
		bondsBox.setPreferredSize(boxDim);
		bondsBox.setMaximumSize(boxDim);
		bondsBox.setMinimumSize(boxDim);
		bondsBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currKey = (String) bondsBox.getSelectedItem();
				updatePriceLabel(currKey);
				updateNumOwned(currKey);
			}
		});
		currKey = (String) bondsBox.getSelectedItem();

		lblPrice = new JLabel();
		updatePriceLabel(currKey);
		
		fieldAmount = new JTextField();
		fieldAmount.setColumns(10);
		fieldAmount.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				checkAndUpdate();
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lblEmpty = new JLabel("");
		lblOwned = new JLabel("");
		lblOwnedInfo = new JLabel("Innehav");
		lblOwnedWorth = new JLabel("");
		lblOwnedWorthInfo = new JLabel("Innehavets totala värde");
		lblNumberToSell = new JLabel("Antal att sälja");
		lblNumberToSellWorth = new JLabel("");
		lblNumberToSellWorthInfo = new JLabel("Totalt sälj värde");

		btnSell = new JButton("Sälj");
		btnSell.setFocusable(false);
		btnSell.setPreferredSize(BUTTONDIM);
		btnSell.setMaximumSize(BUTTONDIM);
		btnSell.setMinimumSize(BUTTONDIM);
		btnSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkAndUpdate()){
				int currAmount = Integer.parseInt(fieldAmount.getText());
				if(bondsListener.bondSellEventOccured(currCustomer, currAmount, currKey)){
					JOptionPane.showMessageDialog(parent, "Försäljning av " + currAmount + " stycken " + currKey + " utfört.");
				} else {
					JOptionPane.showMessageDialog(parent, "Försäljning nekad. Kontrollera inmatning");
				}
				}
			}
		});

		// Checkbox and price

		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(bondsBox, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblPrice, gc);

		// Owned info

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblOwnedInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblOwned, gc);
		
		// Owned value
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblOwnedWorthInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblOwnedWorth, gc);

		// Number to sell

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblNumberToSell, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldAmount, gc);

		// Sell worth

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblNumberToSellWorthInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblNumberToSellWorth, gc);

		// Sell button

		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 20, 20, 0);
		add(btnSell, gc);

		// Empty filler

		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblEmpty, gc);
		
		updatePriceLabel(currKey);
		updateNumOwned(currKey);

	}
	
	private boolean checkAndUpdate(){
		boolean allOk = false;
		if (fieldAmount.getText().length() > 0 && Pattern.matches("[0-9]+", fieldAmount.getText()) == true){
			int currAmount = Integer.parseInt(fieldAmount.getText());
			String currKey = (String) bondsBox.getSelectedItem();
			if (currBondMap.containsKey(currKey) && currAmount <= currBonds.getBondsOwnedAmount(currKey)){
				fieldAmount.setBackground(Color.WHITE);
				updateTotalWorth(currAmount, currKey);
				allOk = true;
			} else {
				fieldAmount.setBackground(Color.RED);
				allOk = false;
			}
		} else {
			fieldAmount.setBackground(Color.RED);
		}
		return allOk;
	}
	


	private void updatePriceLabel(String key) {
		String price = Double.toString(currBonds.getBondPrice(key));
		String outPut = "a' " + price + " SEK";
		lblPrice.setText(outPut);
	}

	private void updateTotalWorth(int amount, String key) {
		double bondPrice = currBonds.getBondPrice(key);
		double totPrice = bondPrice * amount;
		lblNumberToSellWorth.setText(Double.toString(totPrice) + " SEK");
	}
	
	private void updateNumOwned(String key) {
		int numOwned = currBonds.getBondsOwnedAmount(key);
		double totWorth = currBonds.getBondPrice(key) * numOwned;
		lblOwned.setText(Integer.toString(numOwned));
		lblOwnedWorth.setText(Double.toString(totWorth));
	}

	public void updateInfo() {
		fieldAmount.setText("");;
		updateTotalWorth(0, currKey);
		updateNumOwned(currKey);
	}
}
