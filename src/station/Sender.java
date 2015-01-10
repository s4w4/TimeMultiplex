package station;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Sender extends Thread{

    private static final int BYTE_LENGTH = 34;
    private MulticastSocket multicastSocket;
    private long waitTime;
    private DatagramPacket datagramPacket;

    public Sender(MulticastSocket multicastSocket, long waitTime, String multicastaddress, int port) {
        try {
            this.waitTime = waitTime;
            this.multicastSocket = multicastSocket;
            this.datagramPacket = new DatagramPacket(new byte[BYTE_LENGTH], BYTE_LENGTH, InetAddress.getByName(multicastaddress),port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
            
            byte[] data = new byte[PackageOrder.DATA.length()];
            for (int i = 0; i < data.length; i++){
                data[i] = (byte) 0;
                System.out.print(data[i]);
            }
            System.out.println("");
            Message message = new Message('B');
            message.setData(data);
            message.setReservedSlot((byte) 25);
            message.setSendTime(System.currentTimeMillis());
            datagramPacket.setData(message.toByteArray());
            multicastSocket.send(datagramPacket);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
