package station;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {

	private java.util.logging.Logger logger;
	private FileHandler fh;

	public Logger() {
		logger = java.util.logging.Logger.getLogger("MyLog");
		try {
			fh = new FileHandler("MyLogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printMessages(List<Message> allReceivedMessage) {
		for (Message message : allReceivedMessage) {
			logger.info(message.toString());
		}
	}

}
