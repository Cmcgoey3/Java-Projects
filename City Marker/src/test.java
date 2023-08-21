
public class test {
	
	public static void main(String[] args) {
		Program city1 = new Program("cities1.txt", false);
		Program city2 = new Program("cities2.txt", false);
		Program city3 = new Program("cities3.txt", false);
		Program city4 = new Program("cities4.txt", false);
		Program city5 = new Program("cities5.txt", false);
		
		city1.compareDistances();
		CompressedArray thing = city1.getArray();
		System.out.println(thing.getLength());
		System.out.println(thing.toString());
		//System.out.println(test.getElement(0));
		
	}
	
	
	
}


