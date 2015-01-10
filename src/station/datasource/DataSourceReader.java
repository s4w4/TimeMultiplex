package station.datasource;

import java.io.IOException;
import java.io.InputStreamReader;

import station.Station;

public class DataSourceReader extends Thread{
	
	/** 
	 * Station
	 */
	private Station station;

	public DataSourceReader(Station station){
		this.station = station;
	}
	
	public void run() {		
		InputStreamReader input = new InputStreamReader(System.in);
		while(true){
			char[] dataBuffer = new char[24];
			try {
				
				input.read(dataBuffer);
				printCharArray(dataBuffer);	//ausgabe
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	
	/**
	 * gibt ein CharArray in Console aus
	 * @param charArray
	 */
	private void printCharArray(char[] charArray){
		for (int i = 0; i < 24; i++)
			System.out.print(charArray[i]);			
		System.out.println();
	}
}
