
public class FixedGen {
	
	public static int[] getInput(int n) {
		int[] numbers;
		numbers = new int[n];
		for(int i=0; i<numbers.length; i++) {
			numbers[i] = n-i;
		}
		return numbers;
	}
	

}
