package kontohantering.view.frames;
import static kontohantering.view.GuiConstants.LOGOFULL;
import static kontohantering.view.GuiConstants.SOFTWARE_VERSION;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class AboutFrame extends KontoFrame {

	private JLabel lblInfo;
	private JLabel lblVersion;
	private JLabel lblAuthor;
	private JLabel lblLogo;
	
	public AboutFrame(){
		super("Om [b]ank Kontohantering",300,400);
		AboutFrame frameHandler = this;
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		String info = "<html>[b]ank Kontohantering ger möjlighet att utföra alla<br> former av bankärenden på bankens kunder.</html>";
		String version = "Mjukvaruversion "+SOFTWARE_VERSION;
		String autor = "Utvecklare Johan Lindström 2015";
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(LOGOFULL);
		lblInfo = new JLabel(info);
		lblVersion = new JLabel(version);
		lblAuthor = new JLabel(autor);
		lblAuthor.setForeground(Color.DARK_GRAY);
			
		// Logo row
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.3;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.CENTER;
		add(lblLogo,gc);
		
		// Info row
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.CENTER;
		add(lblInfo,gc);
		
		// Version row
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.CENTER;
		add(lblVersion,gc);
		
		// Author row
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.CENTER;
		add(lblAuthor,gc);
		
	}
	
	@Override
	void closureBehaviour() {
		this.dispose();
	}

}
