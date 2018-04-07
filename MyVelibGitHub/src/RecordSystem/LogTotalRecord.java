package RecordSystem;

import java.util.ArrayList;
import java.util.Arrays;

import Network.ParkingSlot;
import Time.Time;

public class LogTotalRecord {
	
	private ArrayList<Record> log = new ArrayList<Record>();

	public LogTotalRecord() {
		super();
	}

	public ArrayList<Record> getLog() {
		return log;
	}

	public void addRecord(Record record) {
		log.add(record);
	}
	
	public Record searchARecordInProgress(ParkingSlot park) {
		for (Record record : log) {
			if(record.getSlot().equals(park) && record.getEndingTime() == Integer.MAX_VALUE) {
				return record;
			}
		}
		return null;
	}
	
	public void putAnEndForARecord(ParkingSlot park) {
		int endingTime = Time.getTimeInMinuteSinceCreation();
		Record record = searchARecordInProgress(park);
		record.setEndingTime(endingTime);
	}
	
	public ArrayList<Double> balanceStation(int minTime, int maxTime, int numberOfSlots) {
		int numberOfRentOperation = 0;
		int numberOfReturnOperation = 0;
		double averageRateOfOccupation = 0.0;
		int sumRateOfOccupation = 0;
		
		for (Record record : log) {
			int begin = Math.max(record.getBeginningTime(),minTime);
			int end = Math.min(record.getEndingTime(),maxTime);
			sumRateOfOccupation+= (double) end-begin;
			
			if (record.getBeginningTime() >= minTime && !record.isAdd()) {
				numberOfReturnOperation++;
			}
			
			if (record.getEndingTime() <= maxTime) {
				numberOfRentOperation++;
			}			
		}
		
		averageRateOfOccupation = (double) sumRateOfOccupation/ (double) numberOfSlots;
		
		return new ArrayList<Double>(Arrays.asList((double) numberOfRentOperation, (double) numberOfReturnOperation, averageRateOfOccupation ));		
	}

}
