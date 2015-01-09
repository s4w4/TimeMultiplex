package station.datasource;

import java.io.IOException;
import java.io.InputStreamReader;

public class DataSourceReader extends Thread{
	
	public void run() {		
		InputStreamReader input = new InputStreamReader(System.in);
		while(true){
			char[] dataBuffer = new char[24];
			try {
				
				input.read(dataBuffer);
				for (int i = 0; i < 24; i++)
					System.out.print(dataBuffer[i]);			
				System.out.println();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
}
