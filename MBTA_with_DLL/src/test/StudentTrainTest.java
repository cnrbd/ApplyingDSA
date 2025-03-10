package test;

import main.Train;
import main.Rider;
import static org.junit.jupiter.api.Assertions.*;

// import javax.sql.rowset.spi.SyncResolver;

import org.junit.jupiter.api.Test;

class StudentTrainTest {

	Train t;
	Rider r;

	@Test
	void initTest() {
		t = new Train("s", 1);

		assertFalse(t.goingNorth());

		assertEquals(0, t.passengerIndex);
		assertEquals(Train.TOTAL_PASSENGERS, t.passengers.length);
		assertEquals("s", t.getStation());

		t.updateStation("u");

		assertEquals("u", t.getStation());

	}

	@Test
	void passengerOperations() {
		t = new Train("s", 1);
		assertEquals("", t.currentPassengers());
		assertTrue(t.hasSpaceForPassengers());
		assertEquals("", t.disembarkPassengers());

		// rider is at same station, same direction
		r = new Rider("abc", "s", "t");
		assertTrue(t.addPassenger(r));
		assertEquals(1, t.passengerIndex);
		assertTrue(t.disembarkPassengers().length() == 0);

		// rider is at same station, same direction; different destination
		r = new Rider("abc", "s", "u");
		assertTrue(t.addPassenger(r));
		assertTrue(t.disembarkPassengers().length() == 0);
		t.updateStation("u");
		assertTrue(t.disembarkPassengers().length() > 0);

		// rider is at same station, different direction
		r = new Rider("abc", "s", "t");
		r.swapDirection();
		assertFalse(t.addPassenger(r));

		// rider is at different station, same direction
		r = new Rider("abc", "t", "u");
		assertFalse(t.addPassenger(r));

		// rider is at different station, different direction
		r = new Rider("abc", "t", "u");
		r.swapDirection();
		assertFalse(t.addPassenger(r));

	}

	@Test
	void testAddPassengers() {
		// Setup
		Train train = new Train("Central", 1);

		// Fill train with passengers
		for (int i = 0; i < Train.TOTAL_PASSENGERS; i++) {
			Rider rider = new Rider("R00" + i, "Central", "North Quincy");
			train.addPassenger(rider);
		}

		// Try to add one more rider
		Rider extraRider = new Rider("R011", "Central", "North Quincy");
		boolean result = train.addPassenger(extraRider);
		assertEquals(false, result);
	}
}
