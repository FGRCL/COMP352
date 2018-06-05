package dataabstraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Point2D {
	
	private static ArrayList<Double[]> points;
	private static 
	
	public static void main(String[] args) {
		try {
			Point2D(Integer.parseInt(args[0]));
			for(int i=0; i<points.size(); i++) {
				System.out.println(Arrays.toString(points.get(i)));
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("missing argument");
		}
	}
	
	private static void Point2D(int n) {
		points = new ArrayList<Double[]>(n);
		Random rand = new Random();
		for(int i=0; i<n; i++) {
			Double[] point = new Double[2];
			point[0]= rand.nextDouble();
			point[1]= rand.nextDouble();
			points.add(point);
		}
	}
}
