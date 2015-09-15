package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;

/*
 * Class for output field
 * ----------------------
 * Default behavior is a JTextArea for output.
 * Alternative behavior is a JTable for presenting multiple accounts and allow for choice.
 */

public class OutputPanel extends JPanel {

	private JTextArea txtAOutput;
	private JTable tblOutput;
	private CustomerTableModel tblModel;
	private JScrollPane scrPaneOut;
	private Controller controller;
	private boolean tableInit;
	private boolean tableMode;
	private String buttonAction;

	public OutputPanel() {

		// Set size panel and add some padding
		setPreferredSize(new Dimension(770, 600));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 20, 0, 20));

		// Get controller handler for events and send handler for currCustomer actions
		controller = Controller.getController();
		controller.setTable(this);
		
		// Make sure table model isn't initiated before data is available
		tableInit = false;
		tableMode = false;

		// Init the text output area and set properties.
		txtAOutput = new JTextArea();
		txtAOutput.setEditable(false);
		add(txtAOutput, BorderLayout.SOUTH);
		txtAOutput.setVisible(true);

		scrPaneOut = new JScrollPane();
		scrPaneOut.setPreferredSize(new Dimension(700, 560));
		scrPaneOut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPaneOut.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrPaneOut);

		scrPaneOut.setVisible(true);

		// Start with text view as default.
		textAreaView();
	}

	private void tableView() {
		if (tableInit) {
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
			tblOutput.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int row = tblOutput.getSelectedRow();
						controller.setSelectedCustomer(tblModel.getSelectedCustomer(row));
					}
				}
			});
			tableMode = true;
		}
	}

	public void textAreaView() {
		scrPaneOut.setViewportView(txtAOutput);
		tableMode = false;
	}

	public void putTextTxtArea(String outputText) {
		txtAOutput.setText(outputText);
	}

	public void showTableFull(ArrayList<Customer> customerArrayFull) {
		tableView();
		tblModel.setDB(customerArrayFull);
		tblModel.fireTableDataChanged();
	}
	
	public void showTablePart(ArrayList<Customer> customerArrayPart) {
		tableView();
		tblModel.setDB(customerArrayPart);
		tblModel.fireTableDataChanged();
	}
	
	public void dataChanged() {
		tblModel.fireTableDataChanged();
	}
	
	public boolean isTabelMode() {
		return tableMode;
	}

	public boolean setSelectedCustomer() {
		if (tableMode && tblOutput.getSelectedRow() >= 0) {
			int row = tblOutput.getSelectedRow();
			controller.setSelectedCustomer(tblModel.getSelectedCustomer(row));
			return true;
		} else {
			return false;
		}
	}

}
