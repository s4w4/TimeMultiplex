package station;

public class ClockManager {
 
	
	private final char CLASS_A = 'A';

	/**
	 * die Länge eines Slots in Millisekunden
	 */
	private final long SLOT_TIME_IN_MS = 40;

	/**
	 * der Korrekturwert in Millisekunden
	 */
	private long correctionInMS;

	/**
	 * Anzahl Slots in einem Frame
	 */
	private int slotCount =  (int) (1000 / SLOT_TIME_IN_MS);

	/**
	 * Summe aller Korrekturwerte im aktuellen Frame
	 */
	private long sumCorrectionInMS = 0;

	/**
	 * Korrekturwert des letzten Slots
	 */
	private long correctionLastSlotInMS = 0;

	/**
	 * Anzahl aller A Station im aktuellen Frame
	 */
	private int countStations = 0;

	/**
	 * Korrekturwert des aktuellen Frames
	 */
	private long correctionLastFrameInMS = 0;
	
	public ClockManager(long utcOffsetInMS) {
		this.correctionInMS = utcOffsetInMS;
		
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEOF() {
		return correctionLastFrameInMS >= 0;
	}

	/**
	 * liefert aktuellen Slot
	 * 		
	 * Slot beginnt bei 1
	 */
	public byte getCurrentSlot() {
		return (byte) (((getCorrectedTimeInMS() % 1000) / SLOT_TIME_IN_MS) + 1);
	}

	/**
	 * liefert die akutelle Systemzeit plus 
	 * den korregierten Wert in Millisekunden
	 * 
	 * @return
	 */
	private long getCorrectedTimeInMS() {
		return System.currentTimeMillis() + correctionInMS;
	}

	public int getSlotCount() {
		return this.slotCount ;
	}

	/**
	 * Berechnet neue Korrektur
	 * 
	 * @param message
	 */
	public void calcCorrection(Message message) {
		if ( message.getStationClass() == CLASS_A ){
			this.correctionLastSlotInMS =  message.getSendTime() - getCorrectedTimeInMS() ;
			this.sumCorrectionInMS  += correctionLastSlotInMS;
			this.countStations++; 
		}
	}

	/**
	 * Bei Kollision wird die letzte Korrektur zurückgesetzt
	 * @param message
	 */
	public void rollbackLastCorrection(Message message) {
		if ( message.getStationClass() == CLASS_A ){
			this.countStations--;
			this.sumCorrectionInMS -= correctionLastSlotInMS; 
			this.correctionLastSlotInMS = 0;
		}
	}

	/**
	 * Berechnet die Zeit wie lange es noch bis zum ende 
	 * des Frames dauert in Millisekunden
	 * @return
	 */
	public long calcToNextFrameInMS() {
		return 1000 - (getCorrectedTimeInMS() % 1000);
	}

	/**
	 * Synchronisiert Korregierte Stationszeit
	 */
	public void sync() {
		this.correctionLastFrameInMS  = this.sumCorrectionInMS / this.countStations;
		this.correctionInMS += correctionLastFrameInMS;
	}

	public boolean isStartFrame() {
		return this.getCurrentSlot() == 1;
	}

	/**
	 * Reseted den ClockManager
	 */
	public void resetFrame() {
		this.countStations = 0;
		this.sumCorrectionInMS = 0;
		this.correctionLastFrameInMS = 0;
		this.correctionLastSlotInMS = 0;
	}
 
	
}
