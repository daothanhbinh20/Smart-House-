

public class CyclicFixed extends Appliance
{
	//instantiate a CyclicFixed appliance
	public CyclicFixed(String name) 
	{
		super(name);
	}

	//time used by the cyclic-fixed appliance
	//time period should be from 1 - 24
	public void timePeriod(int period) 
	{	
		try
		{
			if (period < 1 || period > 24)
			{
				throw new Exception("time of operation per day must be in range of 1 - 24");
			}
		}	
			
			
			catch (Exception e)
			{
				System.err.println(e);
			}
		
		this.period = period;
	}
	
	//return the number of unit consumed Per HOUR by the cyclicfixed appliance
	private float unitConsumed;
	public void unitConsumed(float unitConsumed)
	{
		this.unitConsumed = unitConsumed;
	}
	
	//calculate total unit used over the time period
	public float timepass() 
	{
		if (this.period > time)
		{
			totalUnitConsumed = this.unitConsumed;
			tellMeterToConsumeUnits(meter);			
		}
		
		else
		{
			
		}
		time ++;
		return totalUnitConsumed;
	}
}
