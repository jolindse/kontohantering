package kontohantering.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static kontohantering.view.GuiConstants.*;
/*
 *  Class for Logo panel on top of application
 */
public class TopLogoPanel extends JPanel {

	private JLabel lblBankName;
	private JLabel lblLogo;

	public TopLogoPanel() {
		setPreferredSize(new Dimension(900, 100));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 10, 20, 20));
		
		// Logo area init and properties


		lblLogo = new JLabel();
		lblLogo.setIcon(LOGOFULL);
		
		lblBankName = new JLabel("Kontohantering");
		lblBankName.setForeground(BANKBLUE);
		lblBankName.setFont(FONTLARGE);
		add(lblLogo, BorderLayout.LINE_START);
		add(lblBankName, BorderLayout.EAST);

	}

}
