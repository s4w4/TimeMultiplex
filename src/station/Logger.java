package station;

import java.util.List;

public class Logger {

	public Logger() {
	}
	
	public void printMessages(List<Message> allReceivedMessage) {
		for (Message message : allReceivedMessage) {
			System.out.println(message.toString());
		}
	}

}
