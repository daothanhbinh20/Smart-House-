all part from 1 to 4, i followed the instructions. 
In part 5, if we use the active(int time) method, it will print out the meter reading of each meter after each hour of the day.
In part 6, to read the Config.txt file , i implement all method insides House.java . The method readLine will read 1 line at a time then separate it into 2 parts, then match the variable of appliance to the second part of the sentence.

In the extension, i create a randomvalue for unitCost of the meter at each hour and the Feed- in - tariff accordingly. if the profir = feed - in - tariff - unitCost is positive, we will sell everything inside the battery. And also if the battery overFlow, we will sell excess unit.

To run the file, type: java Main Config.txt 13
where "Config.txt" is the name of the file to read from
"13" is the number of hour the appliance will run