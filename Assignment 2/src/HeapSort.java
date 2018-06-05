import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {

	}
	
	public static void sort(int[] arr) {
		long start;
		long duration;
		System.out.println("sorting: "+printArray(arr));
		
		start = System.nanoTime();
		Heap heap = new Heap(arr);
		for(int i = 0; i<arr.length-1; i++) {
			heap.removeMax();
		}
		duration = System.nanoTime() - start;
		System.out.println("result: "+ heap);
		System.out.println("completed in "+duration+"ns");
	}
	
	public static void sort(int[] arr, int traceStep) {
		long start;
		long duration;
		System.out.println("sorting: "+printArray(arr));
		
		start = System.nanoTime();
		Heap heap = new Heap(arr);
		for(int i = 0; i<arr.length-1; i++) {
			if(traceStep != 1) {
				if(traceStep == i) {
					System.out.println("trace at step "+traceStep+": "+heap);
				}
			}
			heap.removeMax();
		}
		duration = System.nanoTime() - start;
		System.out.println("result: "+ heap);
		System.out.println("completed in "+duration+"ns");	
	}
	
	private static String printArray(int[] arr) {
		String string = "";
		for(int i=0; i<arr.length; i++) {
			string += arr[i]+" ";
		}
		return string;
	}
}
