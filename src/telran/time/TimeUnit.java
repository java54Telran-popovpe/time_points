package telran.time;

public enum TimeUnit {
	
HOUR(3600), MINUTE(60), SECOND(1);
	int value;
	TimeUnit( int value ) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	
	public TimePoint between( TimePoint point1, TimePoint point2 ) {
		TimeUnit commonTimeUnit = getMinTimeUnit( point1.getTimeUnit(), point2.getTimeUnit());
		return new TimePoint( Math.abs(point1.compareTo(point2)), commonTimeUnit ).convert(this);
	}
	
	static public TimeUnit getMinTimeUnit( TimeUnit timeUnit1, TimeUnit timeUnit2) {
		return  timeUnit1.value <  timeUnit2.value ? timeUnit1 : timeUnit2;
	}
	
}
