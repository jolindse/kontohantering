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
	private JScrollPane scrPaneOut;
	private JTable tblOutput;
	private CustomerTableModel tblModel;
	private boolean tableInit;

	public OutputPanel() {
				
		// Set size panel and add some padding
		setPreferredSize(new Dimension(770, 600));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 20, 0, 20));

		// Make sure tablemodell isn't initiated before data is available
		tableInit = false;

		// Init the text output area and set properties.
		txtAOutput = new JTextArea();
		txtAOutput.setEditable(false);
		add(txtAOutput, BorderLayout.SOUTH);
		txtAOutput.setVisible(true);
		
		scrPaneOut = new JScrollPane();
		scrPaneOut.setPreferredSize(new Dimension(700,560));
		scrPaneOut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPaneOut.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrPaneOut);

		scrPaneOut.setVisible(true);

		// Start with textview as default.
		textAreaView();
	}
	
	public void tableView(){
		if (tableInit){
		scrPaneOut.setViewportView(tblOutput);
		} else {
			// Init the table output for search queries
			tblModel = new CustomerTableModel();
			tblOutput = new JTable(tblModel);
			tblOutput.setPreferredSize(new Dimension(700, 560));
			tblOutput.setVisible(true);
			add(tblOutput, BorderLayout.SOUTH);
			scrPaneOut.setViewportView(tblOutput);
			tableInit = true;
		}
	}

	public void textAreaView() {
		scrPaneOut.setViewportView(txtAOutput);
	}
	
	public void putTextTxtArea(String outputText){
		txtAOutput.setText(outputText);
	}
	
	public void showTableFull() {
		tableView();
		System.out.println("Sätter table view!");
		tblModel.showFullCustBase();
		System.out.println("Kallar på showFullCustBase!");
		tblModel.fireTableDataChanged();
	}
}	

