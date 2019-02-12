

import java.util.ArrayList;

public class ExtensionBatteryMeter extends BatteryMeter
{
	double maxFIT = 0.1;
	Integer max = (int) (maxFIT * 1000);
	double minFIT = 0.001;
	Integer min = (int) (minFIT * 1000);
	double profitPerUni, fit;  // fit = feed-in-tarrif
	int random;
	ArrayList<Boolean> sellOrNot = new ArrayList<Boolean>();
	ArrayList<Double> listOfUnitCost = new ArrayList<Double>();
	ArrayList<Double> listOfFIT = new ArrayList<Double>();
	Toolbox toolbox = new Toolbox();
			
	//create a Meter that read specific ultility, has unitcost, has initial reading, and limit for battery
	public ExtensionBatteryMeter(String ultilityName, double unitCost, int meterReading, float limitbattery) 
	{
		super(ultilityName, unitCost, meterReading, limitbattery);
	}
	
	//assign a random cost per unit for the meter
	public void setRandomUnitCost()
	{
		do
		{
			random = toolbox.getRandomInteger(max);

		} 
		while (random < min);
		unitCost = (double) random / 1000;
	}
	
	//get the random cost per unit for the meter
	public double getRandomUnitCost()
	{
		
		return unitCost;
	}
	
	//create a random feed in tariff 
	public void setRandomFIT()
	{
		double comparableUnitCost;
		do
		{
			random = toolbox.getRandomInteger(max);
			comparableUnitCost = getRandomUnitCost() * 1000;
		}
		while (random != max || random != min || random != comparableUnitCost);
		
		double fit = (double) random / 1000;
	}
	
	//get the random Fit
	public double getRandomFIT()
	{
		return fit;
	}
	
	//return a boolean for whether the cost for selling is higher than the cost for consuming AT 1 hour
	public boolean sellOrNotSell()
	{
		if ((getRandomFIT() - getRandomUnitCost()) >= 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	
	private double totalMoneyGain;
	//calculate the total cost of all appliance of the same Meter in 1 hour
	public double report(boolean sellOrNot)
	{
		double cost = 0;		
		//print out the name of ultility
		System.out.println("the ultility consumed is " + ultilityName);

		//print out meter reading
		System.out.println("the meter reading is " + this.meterReading);

		//calculate the cost of usage
		if (this.meterReading <= 0)
		{			
			cost = 0;
			electricityStored = battery.storeUltility(this.meterReading) + electricityStored;
			if (electricityStored >= batteryLimit)
			{
				totalMoneyGain = (electricityStored - batteryLimit) * fit;
				electricityStored = batteryLimit;

				if (sellOrNot == true)
				{
					totalMoneyGain = electricityStored * fit;
					electricityStored = 0;
				}
			}

			else
			{
				if (sellOrNot == true)
				{
					totalMoneyGain = electricityStored * fit;
					electricityStored = 0;
				}
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
				if (sellOrNot == true)
				{
					totalMoneyGain = electricityStored * fit;
					electricityStored = 0;
					cost = this.meterReading * unitCost;
				}

				else if (sellOrNot == false)
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
		}
		cost = cost - totalMoneyGain;
		//print out the cost of usage
		System.out.println("the cost of usage is " + cost);

		//reset the meterReading to 0
		this.meterReading = 0;
		return cost;
	}
}
