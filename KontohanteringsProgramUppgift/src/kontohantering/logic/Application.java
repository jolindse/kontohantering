package kontohantering.logic;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;

import kontohantering.data.CustomerDB;
import kontohantering.view.StandardFrame;

public class Application {
	
	public static void main (String[] args) {
	
		// Load custom font
		
		try {
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("prgresources/Lato-Bold.ttf")));
		} catch (IOException|FontFormatException e) {
			System.out.println("Typsnitt Lato inte tillgängligt. " +e);			
		}
	
		/* Start swing code. Use method runApplication() to
		 * start view, model and controller.
		 */
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				runApplication();
			}
		});

	}

	public static void runApplication() {

		// Init controller
		Controller controller = new Controller();
		controller.initControllerHandler(controller);
		// Init model		
		CustomerDB customerDB = new CustomerDB();
		controller.initControllerDB(customerDB);
		// Init view
		StandardFrame view = new StandardFrame();
		controller.initControllerView(view);

		

	}
	
}
