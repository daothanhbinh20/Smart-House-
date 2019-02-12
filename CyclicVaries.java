
public class CyclicVaries extends Appliance
{
	Toolbox toolbox = new Toolbox();
	
	//instantiate a CyclicFixed appliance
	public CyclicVaries(String name) 
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
	
	//return the number of unit consumed Per HOUR by the cyclicvaries appliance
	//return the number of unit consumed Per HOUR by the appliance
	float unitConsumed;
	public void unitConsumed(Integer max, Integer min)
	{
		if (max < 0 && min < 0)
		{
			max = min * -1;
			min = max * -1;
			do
			{
				unitConsumed = toolbox.getRandomInteger(max);
			} 
			while (unitConsumed < min);
			
			unitConsumed = unitConsumed * -1;
		}
		
		else
		{
			do
			{
				unitConsumed = toolbox.getRandomInteger(max);
			} 
			while (unitConsumed < min);
		}
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