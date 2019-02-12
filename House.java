
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class House
{	
	ArrayList<Appliance> listOfAppliances = new ArrayList<Appliance>();
	private Meter electricMeter;
	private Meter waterMeter;
	private BatteryMeter batteryMeter;
	ExtensionBatteryMeter batteryMeter1 = new ExtensionBatteryMeter("electricity", 0, 0, 90);
	
	
	public House(BatteryMeter electric, Meter water)
	{
		this.batteryMeter = electric;
		this.waterMeter = water;
		this.electricMeter = batteryMeter;
	}
	
	public House() //Extension bullet 3rd bullet point
	{
		electricMeter = batteryMeter1;
		waterMeter = new Meter("water", 0.002, 0);
	}
	
	//add appliance to the house and change Meter to Electric
	public void addElectricAppliance(Appliance appliance) throws Exception
	{
		try
		{
			appliance.setMeter(electricMeter);
		}
		
		catch (Exception e)
		{
			System.err.println("Water appliance should consume water");			
		}
		
	}
	
	//add appliance to the house and change Meter to Water
	public void addWaterAppliance(Appliance appliance) throws Exception
	{
		try
		{
			appliance.setMeter(waterMeter);
		}
		
		catch (Exception e)
		{
			System.err.println("Electric appliance should consume electricity");
		}
		
	}
	
	public ArrayList<Appliance> getListOfAppliacnces()
	{
		return listOfAppliances;
	}
	
	public ArrayList<String> getListOfMeterType()
	{
		return listOfMeterType;
	}
	
	//remove appliance from the house
	public void removeAppliance(Appliance appliance)
	{
		ArrayList<Integer> index = new ArrayList<Integer>(); 
		for (Appliance currentAppliance : listOfAppliances)
		{
			//Appliance currentAppliance = iter.next();
			if (currentAppliance.getApplianceName().equals(appliance.getApplianceName()))
			{
				index.add(listOfAppliances.indexOf(currentAppliance));
			}	
		}
		
		for (Integer i : index)
		{
			listOfAppliances.remove(i);
		}
	}
	
	//return the total number of appliances in the house
	public int numAppliances()
	{
		return listOfAppliances.size();
	}
	
	//activate the appliances in the house in 1 hour 
	// return the sum of cost
	Appliance currentAppliance;
	public double activate()
	{		
		for(Appliance currentAppliance : listOfAppliances)
		{
			System.out.println(currentAppliance.getApplianceName() + "  " + currentAppliance.totalUnitConsumed);
			currentAppliance.timepass();
			System.out.println(currentAppliance.getApplianceName() + "  " + currentAppliance.totalUnitConsumed);
		}
		double electricCost = electricMeter.report();
		double waterCost = waterMeter.report();
		double sum = electricCost + waterCost;
		return sum;
	}
	
	private int count = 0;
	private int countDay;
	private int countHour;
	//activate all appliances in a given hour and return the total cost over the period
	public double activate(int hour) throws Exception
	{
		countHour = 1;
		countDay = 1;
		double sum = 0;
		System.out.println("====DAY" + countDay + "====");
		while (count < hour)
		{
			try
			{
				count ++;
				countHour = count;
				
				if (countHour > 24)
				{
					countHour = countHour - 24;
					countDay ++;
					System.out.println("");
					System.out.println("");
					System.out.println("====DAY" + countDay + "====");
				}
				System.out.println("");
				System.out.println("== Hour" + countHour + " ==");
				
				for(Appliance currentAppliance : listOfAppliances)
				{
					currentAppliance.timepass();
					if (countHour == 24)
					{
						currentAppliance.time = 0;
					}
				}
			}
			
			catch (Exception e)
			{
				System.err.println("You cant activate TimePass. Please check if you have set meter to the " + currentAppliance.getApplianceName());
			}

			sum = electricMeter.report() + waterMeter.report() + sum;
		}	
		return sum;
	}
	
	//READ CONFIG.TXT FILE
	//READ CONGIG.TXT FILE
	private BufferedReader reader;
	
	public boolean fileIsReady()
	{
		try
		{
			if (reader != null)
			{
				return reader.ready();
			}
		}
		
		catch (IOException e)
		{
			System.err.println("File not found");
		}
		
		return false;	
	}
	
	private String[] arr;
	private String[] arrProbability;
	private String[] arrCycleLength;
	private ArrayList<String> listOfMeterType = new ArrayList<String>();
	private String name, applianceType, meterType, maxUnit, minUnit, fixedUnit, probabilityString, cycleLength;
	private int probability, length;
	private float fixedunit;
	private Appliance appliance;
	private int numOfConsecutiveEmptyLine = 0;
	
	public void readLine(String fileName) throws IOException
	{
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		while (fileIsReady() != false)
		{
			String currentLine = reader.readLine();
			arr = currentLine.split(":");
			
			//if the currentLine is an EMPTY LINE
			if (currentLine.equals(""))
			{		
				numOfConsecutiveEmptyLine ++;
				
				//If there are 2 or more consecutive empty lines
				if (numOfConsecutiveEmptyLine > 1)
				{
					//DO NOTHING
					//THIS IS END OF FILE
				}
				
				//if there is only 1 empty line between worded line
				//create  the appliance with information read from config.txt
				//add appliance to array list
				else
				{	
					if (applianceType.equals("CyclicFixed"))
					{
						appliance = new CyclicFixed(name);
						((CyclicFixed) appliance).timePeriod(length);
						((CyclicFixed) appliance).unitConsumed(fixedunit);		
					}
					
					else if (applianceType.equals("CyclicVaries"))
					{
						appliance = new CyclicVaries(name);
						((CyclicVaries) appliance).unitConsumed(Integer.parseInt(maxUnit), Integer.parseInt(minUnit));
						((CyclicVaries) appliance).timePeriod(length);
					}
					
					else if (applianceType.equals("RandomFixed"))
					{
						appliance = new RandomFixed(name, probability);
						((RandomFixed) appliance).unitConsumed(length);
					}
					
					else if (applianceType.equals("RandomVaries"))
					{
						appliance = new RandomVaries(name, probability);
						((RandomVaries) appliance).unitConsumed(Integer.parseInt(maxUnit), Integer.parseInt(minUnit));
					}	
			
				listOfAppliances.add(appliance);
				}
			}
			
			//if currentLine is NOT EMPTY
			else
			{	
				numOfConsecutiveEmptyLine = 0;
				if (arr[0].equals("name"))
				{
					name = arr[1];
				}
				
				else if (arr[0].equals("subclass"))
				{
					applianceType = arr[1];
					String[] arr2 = applianceType.split(" ");
					applianceType = arr2[1];
				}
				
				else if (arr[0].equals("meter"))
				{
					meterType = arr[1];
					String[] arr2 = meterType.split(" ");
					meterType =  arr2[1];
					listOfMeterType.add(meterType);
				}
				
				else if (arr[0].equals("Min units consumed"))
				{
					if (arr.length == 2)
					{
						// if there is a Space after ":"
						if (arr[1].equals(" "))
						{
							//DO NOTHING
						}
						
						//if there is actual information after ":"
						else
						{	
							minUnit = arr[1];
							String[] arr2 = minUnit.split(" ");
							minUnit = arr2[1];
						}
					}
				}
				
				else if (arr[0].equals("Max units consumed"))
				{
					if (arr.length == 2)
					{
						// if there is a Space after ":"
						if (arr[1].equals(" "))
						{
							//DO NOTHING
						}
						else
						{
							maxUnit = arr[1];
							String[] arr2 = maxUnit.split(" ");
							maxUnit = arr2[1];
						}
					}
				}
				
				else if (arr[0].equals("Fixed units consumed"))
				{
					// if there is a Space after ":"
					if (arr[1].equals(" "))
					{
						//DO NOTHING
					}
						
					else	
					{
						fixedUnit = arr[1];
						String[] arr2 = fixedUnit.split(" ");
						fixedUnit = arr2[1];
						
						try
						{
							fixedunit = Float.parseFloat(fixedUnit);
						}
						catch (Exception e)
						{
							System.err.println("You need to type a number value for Fixed Unit Consumed");
						}
					}
				}
				
				else if (arr[0].equals("Probability switched on"))
				{
					if (arr.length == 2)
					{
						// if there is a Space after ":"
						if (arr[1].equals(" "))
						{
							//DO NOTHING
						}
						
						else
						{
							probabilityString = arr[1];
							arrProbability = probabilityString.split(" in ");
							probabilityString = arrProbability[1];
							
							try
							{
								probability = Integer.parseInt(probabilityString);
							}
							catch (Exception e)
							{
								System.err.println("Your value for probability need to be in form: int/int");
							}
						}
					}
				}
				
				else if(arr[0].equals("Cycle length"))
				{
					if (arr.length == 2)
					{
						// if there is a Space after ":"
						if (arr[1].equals(" "))
						{
							//DO NOTHING
						}
						
						else
						{
							cycleLength = arr[1];
							arrCycleLength = cycleLength.split("/");
							cycleLength = arrCycleLength[0];
							String[] arr2 = cycleLength.split(" ");
							cycleLength = arr2[1];
							length = Integer.parseInt(cycleLength);
						}
					}
				}
			}	
		}	
	}
}
