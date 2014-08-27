
public class LactationRecord {
	// Forgot this existed, not really used as intended 
	//(Used for all LacationRecords instead of individual ones). Why?
	// Because I wrote something clever that I am proud of,
	// And in order for this class to work as intended
	// I would have to delete that
	// And there is no way that's happening.
	float lactation[][];
	
	
	private static int[] calvingDate = new int[2];
	private static int daysInMilk;
	private static int poundsOfMilk;
	private static float percentOfButterfat;
	private static float butterfat;
	
	public LactationRecord(float[][] _lactation)
	{
		lactation = _lactation;
	}
	
	public String toString()
	{
		String string = null;
		if (lactation.length == 0)
		{
			string = "No milk records \n ";
		}
		else{ 
			for(int i=0; i<= lactation.length-1;i++)
			{
				string += "Milk Records:  Year: "+ (int)lactation[i][0] +" Month: "+ (int)lactation[i][1] +"  \n Milked For: " + (int)lactation[i][2] +" days, yield:" + (int)lactation[i][3] +" pounds, and %" + lactation[i][4] +" butterfat. \n ";
			}
			}
		return string;
	}
}
