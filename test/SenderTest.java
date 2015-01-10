import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import org.junit.Test;

import station.PackageOrder;
import station.Receiver;
import station.Sender;


public class SenderTest {
    private static final int PORT = 16000;
    private static final int TTL_IN_SEC = 1;
    private static final String INTERFACE_NAME = "eth0";
    private static final String MULTICASTADDRESS = "225.10.1.2";

	@Test
	public void test() {
		
	}
	
}
