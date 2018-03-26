package RecordSystem;

import Network.ParkingSlot;

public class Record {
	
	private int beginningTime;
	private int endingTime;
	private ParkingSlot slot;
	
	public Record() {
		super();
		this.beginningTime = 0;
		this.endingTime = Integer.MAX_VALUE;
		this.slot = new ParkingSlot();
	}

	public Record(int beginningTime, ParkingSlot slot) {
		super();
		this.beginningTime = beginningTime;
		this.endingTime = Integer.MAX_VALUE;
		this.slot = slot;
	}

	public int getBeginningTime() {
		return beginningTime;
	}

	public void setBeginningTime(int beginningTime) {
		this.beginningTime = beginningTime;
	}

	public int getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(int endingTime) {
		this.endingTime = endingTime;
	}

	public ParkingSlot getSlot() {
		return slot;
	}

	public void setSlot(ParkingSlot slot) {
		this.slot = slot;
	}	
}
