package RecordSystem;

import Network.ParkingSlot;

/**
 * This class provides record to give a summary about the characteristics of station, if the bike is here since the creation of a slot the beginning time is -1
 * @author admin
 *
 */
public class Record {
	
	private int beginningTime;
	private int endingTime;
	private ParkingSlot slot;
	private boolean add;
	
	public Record() {
		super();
		this.beginningTime = 0;
		this.endingTime = Integer.MAX_VALUE;
		this.slot = new ParkingSlot();
	}

	public Record(int beginningTime, ParkingSlot slot, Boolean add) {
		super();
		this.beginningTime = beginningTime;
		this.endingTime = Integer.MAX_VALUE;
		this.slot = slot;
		this.add = add;
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

	public boolean isAdd() {
		return add;
	}

}
