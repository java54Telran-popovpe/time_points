package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import telran.time.*;

import org.junit.jupiter.api.Test;

class TimePointTest {
	TimePoint[] timePointArray;
	@BeforeEach
	void init() {
		timePointArray = new TimePoint[] {
				// -3 -2 -1 -1m 0 1s 5s 1h 3h
			new TimePoint( 60, TimeUnit.MINUTE),
			new TimePoint( -120, TimeUnit.MINUTE),
			new TimePoint( -2, TimeUnit.HOUR),
			new TimePoint( -7200, TimeUnit.SECOND),
			new TimePoint( 0, TimeUnit.SECOND),
			new TimePoint( 0, TimeUnit.HOUR),
			new TimePoint( -180, TimeUnit.MINUTE),
			new TimePoint( 180, TimeUnit.MINUTE),
			new TimePoint( 3, TimeUnit.HOUR),
			new TimePoint( -1, TimeUnit.HOUR),
			new TimePoint( 1, TimeUnit.SECOND),
			new TimePoint( 5, TimeUnit.SECOND),
			new TimePoint( -1, TimeUnit.MINUTE),
			
		};
	}

	@Test
	void testBetween() {
		TimePoint point1 = new TimePoint(10, TimeUnit.HOUR);
		TimePoint point2 = new TimePoint(3600 * 20, TimeUnit.SECOND);
		TimePoint point3 = TimeUnit.MINUTE.between(point1, point2);
		assertEquals(600, point3.getAmount());
		TimePoint point4 = TimeUnit.SECOND.between(point1, point2);
		assertEquals(36000, point4.getAmount());
		TimePoint point5 = TimeUnit.HOUR.between(point1, point2);
		assertEquals(10, point5.getAmount());
	}
	
	@Test
	void convertTest() {
		TimePoint timePoint  = new TimePoint( 10, TimeUnit.HOUR);
		TimePoint point1Acrtual  = timePoint.convert(TimeUnit.SECOND);
		assertEquals( 36_000, point1Acrtual.getAmount());
		
		timePoint  = new TimePoint( 10, TimeUnit.MINUTE);
		point1Acrtual  = timePoint.convert(TimeUnit.SECOND);
		assertEquals( 600, point1Acrtual.getAmount());
		
		point1Acrtual  = timePoint.convert(TimeUnit.HOUR);
		assertEquals( 0, point1Acrtual.getAmount());
		
		timePoint  = new TimePoint( 150, TimeUnit.MINUTE);
		point1Acrtual  = timePoint.convert(TimeUnit.HOUR);
		assertEquals( 2, point1Acrtual.getAmount());
	}
	
	@Test
	void plusAdjusterTest() {
		TimePoint timePoint1 = new TimePoint( 10, TimeUnit.HOUR);
		TimePoint timePoint2 = new TimePoint( 60, TimeUnit.MINUTE);
		TimePoint actual = timePoint2.with( new PlusAdjuster(timePoint1));
		assertEquals(660, actual.getAmount());
	}
	@Test
	void timePointEqualsTest() {
		assertTrue(timePointArray[1].equals(timePointArray[2]));
		assertTrue(timePointArray[2].equals(timePointArray[3]));
		assertTrue(timePointArray[2].equals(timePointArray[3]));
		assertTrue(timePointArray[4].equals(timePointArray[5]));
		assertTrue(timePointArray[7].equals(timePointArray[8]));
		assertTrue(!timePointArray[6].equals(timePointArray[7]));
	}
	
	@Test
	void timePointCompareTo() {
		assertEquals( 0, timePointArray[1].compareTo(timePointArray[2]));
		assertEquals( 0, timePointArray[2].compareTo(timePointArray[3]));
		assertEquals( 0, timePointArray[2].compareTo(timePointArray[3]));
		assertEquals( 0, timePointArray[4].compareTo(timePointArray[5]));
		assertEquals( 0, timePointArray[7].compareTo(timePointArray[8]));
		
		assertEquals( 1, timePointArray[0].compareTo(timePointArray[1]));
		assertEquals( 1, timePointArray[4].compareTo(timePointArray[1]));
		assertEquals( 1, timePointArray[5].compareTo(timePointArray[1]));
		assertEquals( -1, timePointArray[6].compareTo(timePointArray[3]));
		assertEquals( 1, timePointArray[8].compareTo(timePointArray[2]));
		assertEquals( -1, timePointArray[12].compareTo(timePointArray[10]));
	}
	
	@Test
	void futureProximityAdjusterTest() {
		TimePointAdjuster timePointAdjuster = new ProximityAdjuster(timePointArray);
		
		TimePoint timePointToTest = new TimePoint(-181, TimeUnit.MINUTE).with(timePointAdjuster);
		assertEquals( new TimePoint(-3, TimeUnit.HOUR),timePointToTest);
		
		timePointToTest = new TimePoint(-150, TimeUnit.MINUTE).with(timePointAdjuster);
		assertEquals( new TimePoint(-2, TimeUnit.HOUR), timePointToTest);
		
		timePointToTest = new TimePoint(-600, TimeUnit.SECOND).with(timePointAdjuster);
		assertEquals( new TimePoint(-1, TimeUnit.MINUTE), timePointToTest);
		
		timePointToTest = new TimePoint(-60, TimeUnit.SECOND).with(timePointAdjuster);
		assertEquals( new TimePoint(-1, TimeUnit.MINUTE), timePointToTest);
		
		timePointToTest = new TimePoint(3, TimeUnit.SECOND).with(timePointAdjuster);
		assertEquals( new TimePoint(5, TimeUnit.SECOND), timePointToTest);
		
		
		timePointToTest = new TimePoint(10, TimeUnit.SECOND).with(timePointAdjuster);
		assertEquals( new TimePoint(1, TimeUnit.HOUR), timePointToTest);
		
		timePointToTest = new TimePoint(61, TimeUnit.MINUTE).with(timePointAdjuster);
		assertEquals( new TimePoint(3, TimeUnit.HOUR), timePointToTest);
		
		timePointToTest = new TimePoint(181, TimeUnit.MINUTE).with(timePointAdjuster);
		assertEquals( null, timePointToTest);
		
	}

}
