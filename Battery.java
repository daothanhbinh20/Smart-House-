package CourseWork;

public class Battery 
{
	private float storedUltility = 0;
	private float consumptionUnit;
	private float batteryLimit;
	
	//create a battery with a capacity = limit (parameter passed-in)
	public Battery(float limit)
	{
		batteryLimit = limit;
	}
		
	//Store excess electricity into the battery
	//pass in the total number of unit consumed to check if battery can store excess electricity or not
	public float storeUltility(float meterReading)
	{
		consumptionUnit = meterReading;
		if (consumptionUnit <= 0)
		{
			storedUltility = consumptionUnit * -1;
		}
		
		else
		{
			storedUltility = 0;
		}
		
		return storedUltility;
	}
	
}
