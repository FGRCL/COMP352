import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;


public class EmpiricalTesting {

	public static void main(String[] args) {
		long start;
		long duration;
		WritableWorkbook excelFile = null;
		try {
			excelFile = Workbook.createWorkbook(new File("Algorithm time measurement.xls"));
			WritableSheet excelSheet = excelFile.createSheet("Insertion sort time", 0);
			Label label = new Label(0, 0, "n");
			excelSheet.addCell(label);
			label = new Label(1, 0, "Time");
			excelSheet.addCell(label);
			Number number;
			for(int i=1; i<=1000; i++) {
				for(int k=0; k<20; k++) {
					int[] arr = new int[i];
					for(int j=0; j<arr.length; j++) {
						Random randInt = new Random();
						arr[j] = randInt.nextInt();
					}
					start = System.nanoTime();
					InsertionSort(arr, false);
					duration = System.nanoTime() - start;
					number = new Number(0, i, i);
					excelSheet.addCell(number);
					number = new Number(1+k, i, duration);
					excelSheet.addCell(number);
				}
			}
			excelFile.write();
			excelFile.close();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
	
	private static boolean isSorted(int[] array) {
		for(int i=0; i<array.length-1; i++) {
			if(array[i]>array[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	private static void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	
}

