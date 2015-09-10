package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;

/*
 * Class for output field
 * ----------------------
 * Default behaviour is a JTextArea for output.
 * Alternative behaviour is a JTable for presenting multiple accounts and allow for choice.
 */

public class OutputPanel extends JPanel {

	private JTextArea txtAOutput;
	private JScrollPane scrTxtOut;
	private JTable tblOutput;
	private CustomerTableModel tblModel;
	private JScrollPane scrTblOut;

	public OutputPanel() {
				
		// Set size panel and add some padding
		setPreferredSize(new Dimension(770, 600));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 20, 0, 20));

	
		// Init the list output for search queries
		tblModel = new CustomerTableModel();
		tblOutput = new JTable(tblModel);
		tblOutput.setPreferredSize(new Dimension(700, 560));
		tblOutput.setVisible(true);
		add(tblOutput, BorderLayout.NORTH);
		
		scrTblOut = new JScrollPane(tblOutput);
		add(scrTblOut, BorderLayout.NORTH);
		scrTblOut.setVisible(false);

		// Init the text output area and set properties.
		txtAOutput = new JTextArea();
		txtAOutput.setEditable(false);
		
		add(txtAOutput, BorderLayout.SOUTH);
		
		scrTxtOut = new JScrollPane(txtAOutput);
		scrTxtOut.setPreferredSize(new Dimension(700,560));
		scrTxtOut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrTxtOut.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrTxtOut, BorderLayout.NORTH);
		txtAOutput.setVisible(true);
		scrTxtOut.setVisible(true);

	}
	
	public void tableView(){
		txtAOutput.setVisible(false);
		tblOutput.setVisible(true);
	}

	public void textAreaView() {
		txtAOutput.setVisible(true);
		tblOutput.setVisible(false);
	}
	
	public void putTextTxtArea(String outputText){
		txtAOutput.setText(outputText);
	}
	
	public void setTableDataModel(CustomerDB customerDB){
		tblModel.setData(customerDB);
	}
}
