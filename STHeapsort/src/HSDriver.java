import java.util.Arrays;
import java.util.Random;


public class HSDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rnd = new Random();
		
		for(int i=0; i<100; i++) {
			int[] arr = new int[100];
			for(int j=0; j<arr.length; j++) {
				arr[i] = rnd.nextInt(1000);
			}
			System.out.println(Arrays.toString(arr));

			HeapSort.sort(arr,2);
			
			System.out.println(Arrays.toString(arr));

			for(int j=0; j<arr.length-1; j++) {
				if(arr[i]>arr[i+1]) {
					System.err.println("not sorted");
					System.exit(0);
				}
			}
		}
		
		
	}

}