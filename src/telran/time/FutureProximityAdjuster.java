package telran.time;

import java.util.Comparator;

import telran.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster {
	TimePoint[] timePoints;
	public FutureProximityAdjuster(TimePoint[] timePoints ) {
		this.timePoints = Arrays.copy(timePoints);
		Arrays.bubbleSort(this.timePoints);
	}
	@Override
	public TimePoint adjust(TimePoint point) {
		int searchResult = Arrays.binarySearch(timePoints, point, Comparator.naturalOrder());
		TimePoint result = null;
		if ( searchResult > -1 ) {
			while ( ++searchResult < timePoints.length && timePoints[ searchResult ].equals( point )  );
		}
		else {
			searchResult = -(searchResult + 1);
		}	
		if ( searchResult < timePoints.length )
			result = timePoints[ searchResult ];
		return result;
	}
}
