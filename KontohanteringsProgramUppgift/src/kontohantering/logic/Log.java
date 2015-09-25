package kontohantering.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Log {
	
	/*
	 *  Simple logging class
	 *  --------------------
	 *  Adds an entry to a queue that serves as eventlog for 
	 *  the application. Adding will be done by controller when action is
	 *  completed.
	 */

	private Queue <String> eventLog;
	private Date now;
	
	public Log(){
		eventLog = new LinkedList<>();
	}
	
	public void addLogEntry(String logEntry) {
		/*
		 * Adds a new entry to the log
		 */
		now = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String currTime = timeFormat.format(now);
		eventLog.add(currTime + " " + logEntry);
	}
	
	public String printLog() {
		/*
		 * Returns whole log as a string for printout.
		 */
		String outString = "";
		for(String currEntry: eventLog){
			outString += currEntry + "\n";
		}
		return outString;
	}
}
