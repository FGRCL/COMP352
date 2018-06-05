import java.util.Arrays;

public class ISort {

	public static void main(String[] args) {
		long start;
		long duration;
		start = System.nanoTime();
		
		if(args.length>0) {
			int[] arr;
			if(args[0].equals("debug")) {
				arr = new int[args.length-1];
				for(int i=0; i<arr.length; i++) {
					arr[i] = Integer.parseInt(args[i+1]);
				}
				printArray(InsertionSort(arr, true));
			}else {
				arr = new int[args.length];
				for(int i=0; i<arr.length; i++) {
					arr[i] = Integer.parseInt(args[i]);
				}
				printArray(InsertionSort(arr, false));
			}
		}
		
		duration = System.nanoTime() - start;
		System.out.println("completed in "+duration+"ns");
	}
	
	private static int[] InsertionSort(int[] arr, boolean debug) {
		int nbToSort;
		int insertIndex;
		if(debug) {
			for(int i=0; i<arr.length; i++) {
				nbToSort = arr[i];
				insertIndex=0;
				for(int j=i-1; j>=0; j--) {
					if(nbToSort<arr[j]) {
						printArrayDebug(arr, i, j, "<");
					}else if(nbToSort==arr[j]){
						printArrayDebug(arr, i, j, "=");
					}else if(nbToSort>arr[j]){
						insertIndex=j+1;
						printArrayDebug(arr, i, j, ">");
						break;
					}
				}
				printArrayDebug(arr, i, i, "|");
				LeftShift(arr, insertIndex, i-1);
				arr[insertIndex] = nbToSort;
			}
		}else {
			for(int i=0; i<arr.length; i++) {
				nbToSort = arr[i];
				insertIndex=0;
				for(int j=i-1; j>=0; j--) {
					if(nbToSort>arr[j]) {
						insertIndex=j+1;
						break;
					}
				}
				LeftShift(arr, insertIndex, i-1);
				arr[insertIndex] = nbToSort;
			}
		}
		return arr;
	}
	
	private static void LeftShift(int[] arr, int start, int end) {
		for(int i = end; i>=start; i--) {
			arr[i+1] = arr[i];
		}
	}
	
	private static void printArrayDebug(int[] arr, int currentIndex, int comparingIndex, String compareResult) {
		for(int i=0; i<arr.length; i++) {
			if(i==currentIndex) {
				System.out.print("["+arr[i]+"] ");
			}else if(i==comparingIndex) {
				System.out.print("i"+arr[i]+"i ");
			}else {
				System.out.print(arr[i]+" ");
			}
		}
		System.out.println(compareResult);
	}
	
	private static void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

}
