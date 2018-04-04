package Time;

public class Time {
	
	private static int timeInMinuteSinceCreation = 0;
	
	public Time() {
		super();
	}
	
	public static void addTime(int addTime) {
		timeInMinuteSinceCreation+=addTime;
	}

	public static int getTimeInMinuteSinceCreation() {
		return timeInMinuteSinceCreation;
	}
	
	public static void setTimeInMinuteSinceCreation(int timeInMinuteSinceCreation) {
		Time.timeInMinuteSinceCreation = timeInMinuteSinceCreation;
	}

	public static int getDuration(int time1, int time2) {
		return Math.abs(time1-time2);
	}

}
