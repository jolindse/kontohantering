package kontohantering.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.FeatureDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kontohantering.data.Bonds;
import kontohantering.data.BondsDB;
import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.IBondsListener;
import kontohantering.view.frames.BondsFrame;

import static kontohantering.view.GuiConstants.BUTTONDIM;

public class BondsBuyPanel extends JPanel {

	private JLabel lblPrice;
	private JLabel lblEmpty;
	private JLabel lblNumber;
	private JLabel lblTotPriceInfo;
	private JLabel lblTotPrice;
	private JTextField fieldAmount;
	private JButton btnBuy;
	private JComboBox bondsBox;
	private String currKey;
	private Bonds currBonds;
	private BondsFrame parent;
	
	private IBondsListener bondsListener;
	

	public BondsBuyPanel(Customer currCustomer, BondsFrame parent) {
		setPreferredSize(new Dimension(380, 300));

		currBonds = currCustomer.getBonds();
		bondsListener = Controller.getController();
		this.parent = parent;

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Dimension boxDim = new Dimension(200, 40);
		
		bondsBox = new JComboBox<>(currBonds.getBondsDBNames());
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
			}
		});

		currKey = (String) bondsBox.getSelectedItem();

		lblNumber = new JLabel("Antal");
		lblTotPrice = new JLabel();
		lblTotPriceInfo = new JLabel("Total kostnad");
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
		
		
		btnBuy = new JButton("K�p");
		btnBuy.setFocusable(false);
		btnBuy.setPreferredSize(BUTTONDIM);
		btnBuy.setMaximumSize(BUTTONDIM);
		btnBuy.setMinimumSize(BUTTONDIM);
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkAndUpdate()){
				int currAmount = Integer.parseInt(fieldAmount.getText());
				if(bondsListener.bondBuyEventOccured(currCustomer, currAmount, currKey)){
					JOptionPane.showMessageDialog(parent, "K�p av " + currAmount + " stycken " + currKey + " utf�rt.");
				} else {
					JOptionPane.showMessageDialog(parent, "K�p nekat. Saknas t�ckning f�r k�pet.");
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

		// Number
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblNumber, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldAmount, gc);
		
		// Total price 
		
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 20, 20, 0);
		add(lblTotPriceInfo, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblTotPrice, gc);

		
		// Buy button

		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 20, 20, 0);
		add(btnBuy, gc);
		
		// Empty filler
		
		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(lblEmpty, gc);
		

	}
	
	private boolean checkAndUpdate(){
		boolean allOk = false;
		if (fieldAmount.getText().length() > 0 && Pattern.matches("[0-9]+", fieldAmount.getText()) == true){
			fieldAmount.setBackground(Color.WHITE);
			int currAmount = Integer.parseInt(fieldAmount.getText());
			String currKey = (String) bondsBox.getSelectedItem();
			updateTotalPrice(currAmount, currKey);
			allOk = true;
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
	
	private void updateTotalPrice(int amount, String key){
		double bondPrice = currBonds.getBondPrice(key);
		double totPrice = bondPrice * amount;
		lblTotPrice.setText(Double.toString(totPrice) + " SEK");
	}
	
	public void updateInfo(){
		fieldAmount.setText("");;
		updateTotalPrice(0, currKey);
	}
}
