
public abstract class Appliance 
{
	private String name;	
	protected Meter meter;
	protected int period;	
	
	//set the meter reading the appliance to a specific meter (meterName)
	public void setMeter(Meter meterName)
	{
		meter = meterName;
	}
	
	//get the meter assigned for the appliance
	public Meter getMeter()
	{
		return meter;
	}
	
	//create an appliance with name being the parameter passed in
	public Appliance(String name)
	{
		this.name = name;
	}
	
	//return name of appliance
	public String getApplianceName()
	{
		return this.name;
	}
	
	//increment of time by 1 hour
	public int time = 0;
	public abstract float timepass();
	
	//call the method consumeUnit of the meter on the appliance
	protected float totalUnitConsumed = 0;
	protected void tellMeterToConsumeUnits(Meter meterInput)
	{
		meter.consumeUnits(totalUnitConsumed);
	}
}
