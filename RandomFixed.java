
public class RandomFixed extends Appliance
{
	Toolbox toolbox = new Toolbox();
	private int probabilityCoefficient;
	
	//instantiate a CyclicFixed appliance
	public RandomFixed(String name, int probabilityCoefficient) 
	{
		super(name);
		this.probabilityCoefficient = probabilityCoefficient;
	}
	
	//return the number of unit consumed Per HOUR by the appliance
	float unitConsumed;
	public void unitConsumed(float unitConsumed)
	{
		this.unitConsumed = unitConsumed;
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
