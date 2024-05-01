public class MinutesToYearsDaysCalculator {
    // write code here

public static void main(String[] args){
	MinutesToYearsDaysCalculator mtydc = new MinutesToYearsDaysCalculator();
	mtydc.printYearsAndDays( 561600 );
}

    public void printYearsAndDays(long minutes){
	
	if(minutes < 0)
	{
		System.out.println("Invalid value of minutes");
	}

        int Days = (int)(minutes / 1440);
        printYearsAndDays(minutes, Days);

    }
    
    public void printYearsAndDays(long minutes, int Days){

        int Year = Days / 365;
        int daysLeft = Days % 365;

	System.out.println(minutes+" minutes = "+Year+"Years"+daysLeft+" Days");
        
    }
}