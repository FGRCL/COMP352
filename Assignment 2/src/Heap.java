import java.util.Arrays;

public class Heap {
	private int[] array;
	private int primeLeaf;
	private int size;
	
	public Heap(int[] array) {
		this.array = array.clone();
		this.size = array.length-1;
		for(int i=array.length-1; i>=0; i--) {
			heapify(i);
		}
		findPrimeLeaf(0);
	}
	
	public int removeMax() {
		int max = array[0];
		int i=primeLeaf;
		int childValue = 0;
		boolean swapped = false;
		while(!swapped && i!=0) {
			if(array[size]<=array[i]) {
				childValue=array[i];
				array[i]=array[size];
				array[size] = max;
				--size;
				swapped = true;
			}
			i=parent(i);
		}		
		int temp;
		int j;
		for(j=i; j!=0; j=parent(j)) {
			temp=array[j];
			array[j] = childValue;
			childValue = temp;
		}
		temp=array[j];
		array[j] = childValue;
		findPrimeLeaf(0);
		return max;
	}
	
	private void heapify(int i) {
		if(hasChild(i)) {
			if(rightChild(i)>size || array[leftChild(i)]>array[rightChild(i)]) {
				if(array[leftChild(i)]>array[i]) {
					swap(i, leftChild(i));					
				}
				heapify(leftChild(i));
			}else {
				if(array[rightChild(i)]>array[i]) {
					swap(i, rightChild(i));			
				}
				heapify(rightChild(i));
			}
		}
	}
	
	private void findPrimeLeaf(int i) {
		if(hasChild(i)) {
			if(rightChild(i)>size || array[leftChild(i)]>array[rightChild(i)]) {
				findPrimeLeaf(leftChild(i));
			}else {
				findPrimeLeaf(rightChild(i));
			}
		}else {
			primeLeaf = i;
		}
	}
	private int parent(int i) { 
		return ((i-1)/2);
	}
	
	private int leftChild(int i) {
		return ((2*i)+1);
	}
	
	private int rightChild(int i) {
		return ((2*i)+2);
	}
	
	private boolean hasChild(int i) {
		return (leftChild(i)<=size  || rightChild(i)<=size); 
	}
	
	private void swap(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public int getLevel(int x) {
		return (int) Math.ceil(Math.log(x+2)/Math.log(2));
	}
	
	public int level(int x) {
		return (int) Math.ceil(Math.log(x)/Math.log(2));
	}
	public String toString() {
		String string = "";
		for(int i=0; i<array.length; i++) {
			string += array[i]+" ";
		}
		return string;
	}
	
	public void printInorder(int i) {
		if(!hasChild(i)) {
			System.out.print(array[i]+" ");
		}else {
			printInorder(leftChild(i));
			System.out.print(array[i]+" ");
			printInorder(rightChild(i));
		}
	}
	
	public void traceLevels() {
		System.out.println(size);
		int[] levels = new int[size+1];
		for(int i=0; i<=size; i++) {
			levels[i]=getLevel(i+2);
		}
		System.out.println(Arrays.toString(levels));
	}
	
}