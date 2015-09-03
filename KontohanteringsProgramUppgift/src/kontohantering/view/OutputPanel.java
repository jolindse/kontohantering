package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class OutputPanel extends JPanel {

	private JTextArea txtAOutput;
	private JTable	tblOutput;
	
	public OutputPanel (){

		setPreferredSize(new Dimension(650,600));
		setLayout(new BorderLayout());
		
		
		txtAOutput = new JTextArea();
		txtAOutput.setPreferredSize(new Dimension(700,450));
		add(txtAOutput,BorderLayout.SOUTH);
		
		
	}
	
}
