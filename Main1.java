
import java.util.ArrayList;

public class Main1 
{
	public static void main(String[] args) throws Exception
	{			
		BatteryMeter electricBatteryMeter = new BatteryMeter("electric", 0.013, 0, 90);
		Meter waterMeter = new Meter("water", 0.002, 0);
		House house = new House(electricBatteryMeter, waterMeter);
		house.readLine("Config.txt");
		
		ArrayList<Integer> indexForElectric = new ArrayList<Integer>();
		ArrayList<Integer> indexForWater = new ArrayList<Integer>();
		ArrayList<Appliance> listOfAppliances = house.getListOfAppliacnces();
		ArrayList<String> listOfMeterType1 = house.getListOfMeterType();
		for (Appliance currentAppliance : listOfAppliances)
		{
			int currentIndex = listOfAppliances.indexOf(currentAppliance);
			if (listOfMeterType1.get(currentIndex).equals("electric"))
			{
				indexForElectric.add(currentIndex);
			}
			
			else if (listOfMeterType1.get(currentIndex).equals("water"))
			{
				indexForWater.add(currentIndex);
			}	
		}
		
		for (Integer i : indexForElectric)
		{
			house.addElectricAppliance(listOfAppliances.get(i));
		}
		
		for (Integer i : indexForWater)
		{
			house.addWaterAppliance(listOfAppliances.get(i));
		}
		
		System.out.println("the total cost is " + house.activate(24));
	}
		
		//EXTENSION
		/*
		House house = new House();
		house.readLine("Config.txt");
		ArrayList<Integer> indexForElectric = new ArrayList<Integer>();
		ArrayList<Integer> indexForWater = new ArrayList<Integer>();
		ArrayList<Appliance> listOfAppliances = house.getListOfAppliacnces();
		ArrayList<String> listOfMeterType = house.getListOfMeterType();
		for (Appliance currentAppliance : listOfAppliances)
		{
			int currentIndex = listOfAppliances.indexOf(currentAppliance);
			if (listOfMeterType.get(currentIndex).equals("electric"))
			{
				indexForElectric.add(currentIndex);
			}
			
			else if (listOfMeterType.get(currentIndex).equals("water"))
			{
				indexForWater.add(currentIndex);
			}	
		}
		
		for (Integer i : indexForElectric)
		{
			house.addElectricAppliance(listOfAppliances.get(i));
		}
		
		for (Integer i : indexForWater)
		{
			house.addWaterAppliance(listOfAppliances.get(i));
		}
		
		System.out.println("the total cost is " + house.activate(24));
	}*/
}
