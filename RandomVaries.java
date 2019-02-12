
public class RandomVaries extends Appliance
{
	Toolbox toolbox = new Toolbox();
	private int probabilityCoefficient;
	
	//instantiate a CyclicFixed appliance
	public RandomVaries(String name, int probabilityCoefficient) 
	{
		super(name);
		this.probabilityCoefficient = probabilityCoefficient;
	}
	
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
	
	//Generate random probability of switching on appliance
	int probability;

	//calculate total unit used over the time period
	public float timepass() 
	{
		probability = toolbox.getRandomInteger(this.probabilityCoefficient - 1);
		if (probability == 1)
		{
			totalUnitConsumed = this.unitConsumed;
			tellMeterToConsumeUnits(meter);
		}
		
		return totalUnitConsumed;
	}
}