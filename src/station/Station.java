package station;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import station.datasource.DataSourceReader;

public class Station extends Thread {

	/** 
	 * Time To Life in sekunden 
	 */
	private final int TTL_IN_SEC = 1;
	
	/** 
	 * Wartezeit des Senders in millisekunden 
	 */
	private final long SENDER_WAIT_TIME_IN_MILLISEC = 1000;

	/** 
	 * Netzwerkinterfacename 
	 */
	private String interfaceName;
	
	/** 
	 * Multicast Adresse
	 */
	private String mcastAddress;
	
	/** 
	 * Port auf den gelauscht wird
	 */
	private int receivePort;
	
	/** 
	 * Stationklasse
	 */
	private String stationClass;

	/** 
	 * DatenSourceReader
	 */
	private DataSourceReader dataSourceReader;
	
	/** 
	 * Sender
	 */
	private Sender sender;
	
	/** 
	 * Multicastsocket
	 */
	private MulticastSocket multicastSocket;

	/**
	 * Empfänger
	 */
	private Receiver receiver;

	
	public Station(String interfaceName, String mcastAddress, int receivePort,
			String stationClass) {
		this.interfaceName = interfaceName;
		this.mcastAddress = mcastAddress;
		this.receivePort = receivePort;
		this.stationClass = stationClass;

		// Create DataSourceReader
		this.dataSourceReader = new DataSourceReader(this);
		this.dataSourceReader.start();

		// Create Sender
		try {
			multicastSocket = new MulticastSocket(receivePort);
			multicastSocket.setTimeToLive(TTL_IN_SEC);
			multicastSocket.setNetworkInterface(NetworkInterface
					.getByName(interfaceName));
			multicastSocket.joinGroup(InetAddress.getByName(mcastAddress));
			this.sender = new Sender(multicastSocket,
					SENDER_WAIT_TIME_IN_MILLISEC, mcastAddress, receivePort);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create Receiver
		this.receiver = new Receiver(multicastSocket);
		
		// TODO: Create Output (Datensenke)
	}

	@Override
	public void run() {
		this.sender.start();
		
		this.receiver.start();
	}
}
