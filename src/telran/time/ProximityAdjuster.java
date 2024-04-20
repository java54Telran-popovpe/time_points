package telran.time;

import java.util.Comparator;

import telran.util.Arrays;

public class ProximityAdjuster implements TimePointAdjuster {
	TimePoint[] timePoints;
	public ProximityAdjuster(TimePoint[] timePoints ) {
		this.timePoints = Arrays.copy(timePoints);
		Arrays.bubbleSort(this.timePoints);
	}
	@Override
	public TimePoint adjust(TimePoint point) {
		int searchResult = Arrays.binarySearch(timePoints, point, Comparator.naturalOrder());
		TimePoint result = null;
		if ( searchResult > -1 ) 
			result = timePoints[ searchResult ];
		else {
			int indexToInsert = -(searchResult + 1);
			if ( indexToInsert < timePoints.length )
				result = timePoints[ indexToInsert ];
			}
		return result;
	}

}
