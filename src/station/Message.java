package station;

import java.nio.ByteBuffer;
import static station.PackageOrder.*; 
public class Message {
	
	// Stationklass
	private String stationClass; 
	// Nutzdaten
	private byte[] data; 
	// reservierte Slot im naechsten Frame
	private int reservedSlot; 
	// Zeitstemple, wann Packet abgeschickt wurde
	private long sendTime;
	
	
	public Message(String stationClass) {
		super();
		this.stationClass = stationClass;
	}
	
	
	public Message(byte[] messageInByteArray){

		ByteBuffer stationClassBuffer = ByteBuffer.wrap(messageInByteArray, STATION_CLASS.getOffset(), STATION_CLASS.getLength());
		stationClass=""; 
		for (int i = 0; i < STATION_CLASS.getLength(); i++) {
			stationClass += (char) stationClassBuffer.get();
		}
		
		
		
		
		System.out.println("*******");
		
	}

	
	public String getStationClass() {
		return stationClass;
	}


	public void setStationClass(String stationClass) {
		this.stationClass = stationClass;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	public int getReservedSlot() {
		return reservedSlot;
	}


	public void setReservedSlot(int reservedSlot) {
		this.reservedSlot = reservedSlot;
	}


	public long getSendTime() {
		return sendTime;
	}


	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	

	
}
