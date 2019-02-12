
public class Meter 
{
	protected String ultilityName;
	protected double unitCost;
	protected float meterReading;
	protected int storedUtility;
	protected float batteryLimit;
	
	//create a meter with ultility that it is reading, cost for one unit, and initial readiing
	public Meter(String ultilityName, double unitCost, int meterReading)
	{
		this.ultilityName = ultilityName;	
		this.unitCost = unitCost;
		this.meterReading = meterReading;
	}
	
	//Meter consumed unit equal to parameter passed in
	public void consumeUnits(float numberOfUnit)
	{
		this.meterReading = this.meterReading + numberOfUnit;
	}
	
	//calculate the total cost of all appliance of the same Meter in 1 hour
	public double report()
	{
		//print out the name of ultility
		System.out.println("the ultility consumed " + ultilityName);
		
		double cost;
		//calculate the cost of usage
		cost = this.meterReading * unitCost;
		
		//print out meter reading
		System.out.println("the meter reading is " + this.meterReading);
		
		//print out the cost of usage
		System.out.println("the cost of usage is " + cost);
		
		//reset the meterReading to 0
		this.meterReading = 0;
		
		return cost;
	}
}
