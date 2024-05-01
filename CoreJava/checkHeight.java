
public class checkHeight{

	public static void main(String[] args){
		checkHeight chkHt = new checkHeight();
		double dbHtInCm = chkHt.convertToCentimeters(6,7);
		System.out.println("Total height in Centimeter is : " + dbHtInCm);
	}

	private double convertToCentimeters(int htInInches){
		double htInCm = htInInches * 2.54;
		return htInCm;
	}

	private double convertToCentimeters(int htFt, int htInch){
		int totalHtInches = (htFt * 12) + htInch;
		System.out.println("Total height in Inches is : " + totalHtInches);
		double totalHtCm = convertToCentimeters(totalHtInches);
		return totalHtCm;
	}


}