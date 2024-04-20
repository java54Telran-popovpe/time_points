package telran.time;

import java.util.Objects;

public class TimePoint implements Comparable<TimePoint> {
	
	int amount;
	TimeUnit timeUnit;
	
	public TimePoint(int amount, TimeUnit timeUnit) {
		this.amount = amount;
		this.timeUnit = timeUnit;
	}

	public int getAmount() {
		return amount;
	}
	
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	
	public TimePoint convert( TimeUnit unit) {
		int convertedAmount =   getTimeUnit() == unit ? amount : timeUnit.getValue() * amount / unit.getValue();
		return new TimePoint( convertedAmount, unit );
	}
	
	public TimePoint with( TimePointAdjuster adjuster) {
		return adjuster.adjust(this);
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(amount, timeUnit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass() )
			return false;

		TimePoint other = (TimePoint) obj;
		return compareTo(other) == 0;
	}
	

	@Override
	public int compareTo(TimePoint o) {
		TimeUnit commonTimeUnit = TimeUnit.getMinTimeUnit(getTimeUnit(), o.getTimeUnit());
		return convert(commonTimeUnit).getAmount() - o.convert(commonTimeUnit).getAmount();
	}


	
	

}
