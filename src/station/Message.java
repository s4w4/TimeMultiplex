package station;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

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

		ByteBuffer stationClassBuffer = ByteBuffer.wrap(messageInByteArray, STATION_CLASS.from(), STATION_CLASS.length());
		stationClass=""; 
		for (int i = 0; i < STATION_CLASS.length(); i++) {
			stationClass += (char) stationClassBuffer.get();
		}
		
				
		ByteBuffer dataBuffer = ByteBuffer.wrap(messageInByteArray, DATA.from(), DATA.length());
		data = new byte[DATA.length()];
		for (int i = 0; i < DATA.length(); i++) {
			data[i] = (byte) dataBuffer.get(i);
		}
		
//		reservedSlot = ByteBuffer.wrap(messageInByteArray, RESERVED_SLOT.getOffset(), RESERVED_SLOT.getLength()).getInt();
		System.out.println(RESERVED_SLOT.from() + " " + messageInByteArray[RESERVED_SLOT.from()]);
		reservedSlot = (Byte) messageInByteArray[RESERVED_SLOT.from()]; 
					
		sendTime = ByteBuffer.wrap(messageInByteArray, SEND_TIME.from(), SEND_TIME.length()).asLongBuffer().get(); 
		System.out.println("reserved = " + reservedSlot);
		
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
