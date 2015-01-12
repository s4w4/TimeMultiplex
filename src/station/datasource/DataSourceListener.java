package station.datasource;

import java.io.IOException;
import java.io.InputStreamReader;

import station.DataManager;
import station.Station;

public class DataSourceListener extends Thread{
	 

	private DataManager dataManager;


	public DataSourceListener(DataManager dataManager ){ 
		this.dataManager = dataManager;
	}
	
	public void run() {		
		InputStreamReader input = new InputStreamReader(System.in);
		while(true){
			char[] dataBuffer = new char[24];
			try {
				
				input.read(dataBuffer);
				printCharArray(dataBuffer);	//ausgabe
				 
			} catch (IOException e) { 
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
