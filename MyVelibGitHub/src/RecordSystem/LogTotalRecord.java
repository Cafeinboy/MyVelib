package RecordSystem;

import java.util.ArrayList;
import java.util.Arrays;

import Network.ParkingSlot;

public class LogTotalRecord {
	
	private ArrayList<Record> log;

	public LogTotalRecord() {
		super();
		this.log = new ArrayList<Record>();
	}

	public ArrayList<Record> getLog() {
		return log;
	}

	public void addRecord(Record record) {
		log.add(record);
	}
	
	public Record searchARecordInProgress(ParkingSlot park) {
		for (Record record : log) {
			if(record.getSlot().equals(park) && record.getEndingTime() == -1) {
				return record;
			}
		}
		return null;
	}
	
	public void putAnEndForARecord(ParkingSlot park, int endingTime) {
		for (Record record : log) {
			if(record.getSlot().equals(park) && record.getEndingTime() == Integer.MAX_VALUE) {
				record.setEndingTime(endingTime);
				break;
			}
		}
	}
	
	public ArrayList<Double> balanceStation(int minTime, int maxTime, int numberOfSlots) {
		int numberOfRentOperation = 0;
		int numberOfReturnOperation = 0;
		double averageRateOfOccupation = 0;
		int sumRateOfOccupation = 0;
		
		for (Record record : log) {
			int begin = Math.max(record.getBeginningTime(),minTime);
			int end = Math.min(record.getEndingTime(),maxTime);
			sumRateOfOccupation+= end-begin;
			
			numberOfRentOperation++;
			
			if (record.getEndingTime()!=Integer.MAX_VALUE) {
				numberOfReturnOperation++;
			}			
		}
		
		averageRateOfOccupation = (double) sumRateOfOccupation/numberOfSlots;
		
		return new ArrayList<Double>(Arrays.asList((double) numberOfRentOperation, (double) numberOfReturnOperation, averageRateOfOccupation ));		
	}

}
