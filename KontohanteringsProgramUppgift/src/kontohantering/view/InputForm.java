package kontohantering.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class InputForm extends JPanel {

	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblPersNumber;
	private JLabel lblInitialDeposit;
	
	private JCheckBox chkbxInitialDeposit;

	private JTextField fieldName;
	private JTextField fieldLastName;
	private JFormattedTextField fieldPersNumber;
	private JFormattedTextField fieldInitialDeposit;

	public InputForm() {

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// Init components of form

		lblName = new JLabel("Förnamn:");
		lblLastName = new JLabel("Efternamn:");
		lblPersNumber = new JLabel("Personnummer:");
		lblInitialDeposit = new JLabel("Summa:");
		lblInitialDeposit.setEnabled(false);
		
		fieldName = new JTextField(20);
		fieldLastName = new JTextField(20);

		fieldPersNumber = new JFormattedTextField();
		fieldPersNumber.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("########-####"))));
		fieldPersNumber.setColumns(11);
		
		chkbxInitialDeposit = new JCheckBox("Initial insättning?");
		
		fieldInitialDeposit = new JFormattedTextField();
		fieldInitialDeposit.setFormatterFactory(
				new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getCurrencyInstance())));
		fieldInitialDeposit.setColumns(20);
		fieldInitialDeposit.setEnabled(false);
		
		
		chkbxInitialDeposit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean deposit = chkbxInitialDeposit.isSelected();
				fieldInitialDeposit.setEnabled(deposit);
				lblInitialDeposit.setEnabled(deposit);
				
			}
		});
		
		// Output components
		// First row

		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(10, 10, 10, 10);
		add(lblName, gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldName, gc);

		// Second row

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblLastName, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldLastName, gc);

		// Third row

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblPersNumber, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldPersNumber, gc);

		// Forth row
		
		gc.gridy++;
		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(chkbxInitialDeposit, gc);
		
		// Fifth row

		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		add(lblInitialDeposit, gc);

		gc.gridx = 1;
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		add(fieldInitialDeposit, gc);

	}
}
