import java.util.Arrays;


public class HeapSort {
	public static void sort(int[] array, int trace) {
		
		heapify(array);
		
		//Poorman's trace (trace 0)
		//System.out.println("Heapified: " + Arrays.toString(array));
		
		int movingNodeHolder = 0;
		int primeLeaf;
		for(int i = (array.length-1); i>0; i--) {
			primeLeaf = findPrimeLead(array, i);
			
			if(primeLeaf==i) {
				//Do something easy
				movingNodeHolder=array[0];
				shiftUp(array, primeLeaf);
				array[i]=movingNodeHolder;
			} else {
				movingNodeHolder=array[i];
				array[i]=array[0];
				siftUp(array, movingNodeHolder, primeLeaf);
			}
			//Poorman's trace, 0-based
			//if((array.length-1 - trace) == i)System.out.println("store at end " + i + ": " + Arrays.toString(array));
			
		}
		
	}
	
	private static void siftUp(int[] array, int movingNodeHolder, int target) {
		while(target > 0) {
			if(movingNodeHolder > array[target]) target = getParent(target); 
			else {
				shiftUp(array, target);
				break;
			}
		}
		array[target]=movingNodeHolder;
	}

	private static void shiftUp(int[] array, int target) {
		int parent = 0;
		int temp = array[target];
		while((parent=getParent(target)) != 0) {
			int temp2 = array[parent];
			array[parent]=temp;
			temp = temp2;
			target=parent;
		}
		array[0]=temp;

	}

	private static int findPrimeLead(int[] array, int last) {
		int left=0, right=0, current=0;
		while((left=getLeftChild(current, last)) > 0) {
			right=getRightChild(current, last);
			if(right<0) return left;
			if(array[left]<array[right]) current = right;
			else current=left;
		}
		return current;
	}

	private static void heapify(int[] array) {
		for(int i = (array.length>>1)-1; i >=0; i--) {
			siftDown(array, i, array.length-1);
		}
		
		
	}

	private static void siftDown(int[] array, int i, int last) {
		
		int left = getLeftChild(i, last);
		int right = getRightChild(i, last);
		if(left < 0) return;

		if(right < 0) { //one child
			if(array[i] < array[left]) {
				swap(array,i,left);
				siftDown(array,left, last);
			}
		} else { //both children
			if(array[left] < array[right]) {
				if(array[i] < array[right]) {
					swap(array,i,right);
					siftDown(array,right, last);
				}
			} else {
				if(array[i] < array[left]) {
					swap(array,i,left);
					siftDown(array,left, last);
				}
			}
		}
				
	}

	private static void swap(int[] array, int i, int left) {
		int tmp =array[i];
		array[i]=array[left];
		array[left]=tmp;
	}

	public static int getParent(int current) {
		return (current-1)>>1;
	}
	
	public static int getLeftChild(int current, int last) {
		int left = (current<<1)+1;
		if(left > last) return -1;
		return left;
	}
	
	public static int getRightChild(int current, int last) {
		int right = (current<<1)+2;
		if(right > last) return -1;
		return right;
	}
	
}