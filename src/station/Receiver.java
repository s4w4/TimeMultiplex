package station;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Receiver extends Thread{
    private static final int BYTE_LENGTH = 34;
    private DatagramPacket datagramPacket;
    private byte[] byteArray;
    private MulticastSocket multicastSocket;

    public Receiver(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
        this.byteArray = new byte[BYTE_LENGTH];
        this.datagramPacket = new DatagramPacket(byteArray, BYTE_LENGTH);
    }

    @Override
    public void run() {
        while (true){
            try {
            	//auf Nachricht warten
                multicastSocket.receive(datagramPacket);
                byte[] data = datagramPacket.getData();
                Message message = new Message(data); 
                System.out.println("Stationclass = " + message.getStationClass());
                System.out.println("Reservedport = " + message.getReservedSlot());
                System.out.println("SendTime=" + message.getSendTime());
                byte[] dataMessage = message.getData();
                System.out.println("Data");
                for (int i = 0; i < dataMessage.length; i++) {
					System.out.print(dataMessage[i]+ " ");
				}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
