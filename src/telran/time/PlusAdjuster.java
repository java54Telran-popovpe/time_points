package telran.time;

public class PlusAdjuster implements TimePointAdjuster {
	TimePoint timePoint;
	@Override
	public TimePoint adjust(TimePoint point) {
		TimePoint pointSec = point.convert( TimeUnit.SECOND );
		TimePoint timePointSec = timePoint.convert( TimeUnit.SECOND );
		int sumSecont = pointSec.getAmount() + timePointSec.getAmount();
		TimePoint resSec = new TimePoint(sumSecont, TimeUnit.SECOND);
		return resSec.convert( point.timeUnit);
	}
	public PlusAdjuster(TimePoint timePoint) {
		this.timePoint = timePoint;
	}
	
	
	
	

}
