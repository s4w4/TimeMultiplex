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
                multicastSocket.receive(datagramPacket);

                byte[] data = datagramPacket.getData();
                for (int i = 0 ; i < data.length; i++)
                    System.out.print(data[i]);
                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
