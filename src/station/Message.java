package station;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static station.PackageOrder.*; 
public class Message {
	
	
    private static final int BYTE_LENGTH = 34;
	// Stationklass
	private char stationClass; 
	// Nutzdaten
	private byte[] data; 
	// reservierte Slot im naechsten Frame
	private byte reservedSlot; 
	// Zeitstemple, wann Packet abgeschickt wurde
	private long sendTime;
	private byte[] messageInByteArray = new byte[BYTE_LENGTH];;
	
	
	public Message(char stationClass) {
		super();
		setStationClass(stationClass);
	}
	
	
	public Message(byte[] messageInByteArray){
		this.messageInByteArray = messageInByteArray;

		stationClass = (char) this.messageInByteArray[STATION_CLASS.from()]; 
		
		data = new byte[DATA.length()];	
		for(int i=DATA.from(); i<DATA.length(); i++){
			data[i] = (byte) messageInByteArray[i];	
		}
					
		reservedSlot = (byte) this.messageInByteArray[RESERVED_SLOT.from()]; 
					
		sendTime = ByteBuffer.wrap(this.messageInByteArray, SEND_TIME.from(), SEND_TIME.length()).asLongBuffer().get(); 
		
	}
	
	public byte[] toByteArray(){
		return messageInByteArray; 
	}

	
	public char getStationClass() {
		return stationClass;
	}


	public void setStationClass(char stationClass) {
		messageInByteArray[STATION_CLASS.from()] = (byte) stationClass; 
		this.stationClass = stationClass;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		  for (int i = 0; i < DATA.length(); i++){
	        	messageInByteArray[DATA.from()+i] = (byte) data[i];
	        	System.out.print(messageInByteArray[i]);
		  }
		  System.out.println();
		this.data = data;
	}


	public int getReservedSlot() {
		return reservedSlot;
	}


	public void setReservedSlot(byte reservedSlot) {
        messageInByteArray[RESERVED_SLOT.from()] = reservedSlot;
		this.reservedSlot = reservedSlot;
	}


	public long getSendTime() {
		return sendTime;
	}


	public void setSendTime(long sendTime) {
        ByteBuffer.wrap(messageInByteArray, SEND_TIME.from(), SEND_TIME.length()).putLong(sendTime);
		this.sendTime = sendTime;
	}
	

	
}
