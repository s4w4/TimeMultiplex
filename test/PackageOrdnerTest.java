import static org.junit.Assert.*;

import org.junit.Test;

import station.PackageOrder;


public class PackageOrdnerTest {

	@Test
	public void lengthTest() {
		assertTrue(PackageOrder.STATION_CLASS.length() == 1);
		assertTrue(PackageOrder.DATA.length() == 24);
		assertTrue(PackageOrder.RESERVED_SLOT.length() == 1);
		assertTrue(PackageOrder.SEND_TIME.length() == 8);	
	}
	
	@Test
	public void fromTest() {
		assertTrue(PackageOrder.STATION_CLASS.from() == 0);
		assertTrue(PackageOrder.DATA.from() == 1);
		assertTrue(PackageOrder.RESERVED_SLOT.from() == 25);
		assertTrue(PackageOrder.SEND_TIME.from() == 26);	
	}
	
	@Test
	public void toTest() {
		assertTrue(PackageOrder.STATION_CLASS.to() == 1);
		assertTrue(PackageOrder.DATA.to() == 25);
		assertTrue(PackageOrder.RESERVED_SLOT.to() == 26);
		assertTrue(PackageOrder.SEND_TIME.to() == 34);	
	}

}
