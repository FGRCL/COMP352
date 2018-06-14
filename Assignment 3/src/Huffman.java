import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
	public static void main(String[] args) {
		Scanner file = null;
		int max=0;
		int min=500;
		try {
			System.out.println("Start reading from: "+args[0]);
			file = new Scanner( new File(args[0]));
			file.useDelimiter("");
			while(file.hasNext()) {
				char currentCharacter = file.next().charAt(0);
				if(currentCharacter>max) max=currentCharacter;
				if(currentCharacter<min) min=currentCharacter;
				System.out.println(currentCharacter);
			}
			System.out.println(min);
			System.out.println(max);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}
}
