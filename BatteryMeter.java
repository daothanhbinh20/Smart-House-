
public class BatteryMeter extends Meter
{
	Battery battery = new Battery(batteryLimit);
	
	public BatteryMeter(String ultilityName, double unitCost, int meterReading, float limitbattery)
	{
		super(ultilityName, unitCost, meterReading);
		batteryLimit = limitbattery;
	}
	
	protected float electricityStored;
	public double report()
	{
		//print out the name of ultility
		System.out.println("the ultility consumed is " + ultilityName);
		
		double cost;
		//calculate the cost of usage
		if (this.meterReading <= 0)
		{
			cost = 0;
			electricityStored = battery.storeUltility(this.meterReading) + electricityStored;
			if (electricityStored >= batteryLimit)
			{
				electricityStored = batteryLimit;
			}
			
			else
			{
				//ELECTRICITY STORED IS THE SAME AS ABOVE
				//electricityStored = battery.storeUltility(this.meterReading) + electricityStored
			}
			
			System.out.println("the amount of electricity stored in battery is " + electricityStored + " unit");
		}
		
		else
		{	
			if (electricityStored == 0)
			{
				cost = this.meterReading * unitCost;
			}
			
			//when electricityStored > 0 
			else
			{
				if (electricityStored >= this.meterReading)
				{
					electricityStored = electricityStored - this.meterReading;
					cost = 0;
				}
				
				else
				{
					this.meterReading = this.meterReading - electricityStored;
					cost = this.meterReading * unitCost;
				}
			}
		}
		
		//print out meter reading
		System.out.println("the meter reading is " + this.meterReading);
		
		//print out the cost of usage
		System.out.println("the cost of usage is " + cost);
		
		//reset the meterReading to 0
		this.meterReading = 0;
		
		return cost;
	}
}
