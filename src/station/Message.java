package station;

public class Message {
	
	/**
	 * Format und Semangtik der Nachrichtenpackete
	 * 
	 * Byte 0:
	 * Stationsklasse ('A' oder 'B')
	 * 
	 * Byte 1 – 24:
	 * Nutzdaten. (Darin Byte 1 – 10: Name der sendenden Station.)
	 * 
	 * Byte 25:
	 * Nummer des Slots, in dem die Station im nächsten Frame senden wird.
	 * 
	 * Byte 26 – 33:
	 * Zeitpunkt, zu dem dieses Paket gesendet wurde. Einheit: Millisekunden
	 * seit dem 1.1.1970 als 8-Byte Integer, Big Endian.
	 * 
	 * 
	 * Gesamtlänge: 34 Bytes
	 * 
	 */
	
	// Stationklass
	private char stationClass; 
	// Nutzdaten
	private byte[] data; 
	// reservierte Slot im naechsten Frame
	private int reservedSlot; 
	// Zeitstemple, wann Packet abgeschickt wurde
	private long sendTime;
	
	
	public Message(char stationClass) {
		super();
		this.stationClass = stationClass;
	}


	public char getStationClass() {
		return stationClass;
	}


	public void setStationClass(char stationClass) {
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
