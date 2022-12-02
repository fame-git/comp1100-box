package task2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ParcelTest {

	private Sender sender;
	private Recipient recipient;
	private PostOffice po;

	@Before
	public void init() {
		this.sender = new Sender();
		this.recipient = new Recipient();
		this.po = new PostOffice();
	}

	@Test
	public void test1() {

		Parcel parcel = sender.order(20, 3, recipient);
		assertEquals(State.ORDERED, parcel.getStatus());

		po.registerParcel(parcel);
		assertEquals(State.REGISTERED, parcel.getStatus());

		Transport transport = TransportOffice.getInstance().transitBy(parcel);
		assertEquals(State.IN_TRANSIT, parcel.getStatus());
		assertEquals(Transport.AIR_CARRIER, transport);

		recipient.receive(parcel);
		assertEquals(State.DELIVERED, parcel.getStatus());
	}

	@Test
	public void test2() {

		Parcel parcel = sender.order(70, 7, recipient);
		assertEquals(State.ORDERED, parcel.getStatus());

		po.registerParcel(parcel);
		assertEquals(State.REGISTERED, parcel.getStatus());

		Transport transport = TransportOffice.getInstance().transitBy(parcel);
		assertEquals(State.IN_TRANSIT, parcel.getStatus());
		assertEquals(Transport.TRAIN, transport);

		recipient.receive(parcel);
		assertEquals(State.DELIVERED, parcel.getStatus());
	}

	@Test
	public void test3() {

		Parcel parcel = sender.order(15, 8, recipient);
		assertEquals(State.ORDERED, parcel.getStatus());

		po.registerParcel(parcel);
		assertEquals(State.REGISTERED, parcel.getStatus());

		Transport transport = TransportOffice.getInstance().transitBy(parcel);
		assertEquals(State.IN_TRANSIT, parcel.getStatus());
		assertEquals(Transport.VAN, transport);

		recipient.receive(parcel);
		assertEquals(State.DELIVERED, parcel.getStatus());
	}

	@Test
	public void test4() {

		Parcel parcel = sender.order(15, 8, recipient);
		assertEquals(State.ORDERED, parcel.getStatus());

		po.registerParcel(parcel);
		assertEquals(State.REGISTERED, parcel.getStatus());

		Transport transport = TransportOffice.getInstance().transitBy(parcel);
		assertEquals(State.IN_TRANSIT, parcel.getStatus());
		assertEquals(Transport.VAN, transport);

		Recipient fake = new Recipient();
		fake.receive(parcel);
		assertEquals(State.RETURNED, parcel.getStatus());
	}

	@Test
	public void test5() {

		Parcel parcel = sender.order(60, 1, recipient);
		assertEquals(State.ORDERED, parcel.getStatus());

		po.registerParcel(parcel);
		assertEquals(State.REGISTERED, parcel.getStatus());

		Transport transport = TransportOffice.getInstance().transitBy(parcel);
		assertEquals(State.RETURNED, parcel.getStatus());
		assertNull(transport);
	}

	@Test
	public void test6() {
		assertSame(TransportOffice.getInstance(), TransportOffice.getInstance());
	}
}
