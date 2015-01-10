package station;

public enum PackageOrder {

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
	
	STATION_CLASS(0,5),
	DATA(1,24), 
	RESERVED_SLOT(25,1),
	SEND_TIME(26,8);
	
	
	private int length;
	private int offset;

	private PackageOrder(int offset, int length) {
		this.offset = offset; 
		this.length = length; 
	}
	
	public int getLength() {
		return length;
	}
	public int getOffset() {
		return offset;
	}

}
