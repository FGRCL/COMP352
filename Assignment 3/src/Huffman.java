import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Huffman {
	public static void main(String[] args) {
		Scanner file = null;
		ArrayListCharacterFrequency characterFrequency = new ArrayListCharacterFrequency();
		try {
			System.out.println("Start reading from: "+args[0]);
			file = new Scanner( new File(args[0]));
			file.useDelimiter("");
			while(file.hasNext()) {
				char currentCharacter = file.next().charAt(0);
				characterFrequency.insert(currentCharacter);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
		characterFrequency.sort();
		NodePriorityQueue nodes = new NodePriorityQueue();
		while(!characterFrequency.isEmpty()) {
			nodes.push(characterFrequency.popToNode());
		}
		HuffmanTree huffmanTree = new HuffmanTree();
		while(nodes.getLength()>1) {
			nodes.insert(huffmanTree.merge(nodes.pop(), nodes.pop()));
		}
		CharacterEncodingHash encodings = huffmanTree.createHash();		
		System.out.println(encodings);
		Scanner input = null;
		PrintWriter output = null;
		try {
			output = new PrintWriter("EncodedStrings.txt");
			input = new Scanner(new File("RandomStrings.txt"));
			input.useDelimiter("\t");
			String toEncode;
			String encoded;
			int ID;
			while(input.hasNext()) {
				ID = Integer.parseInt(input.next());
				toEncode = input.nextLine().substring(1);
				encoded = ID+"\t";
				for(int i=0; i<toEncode.length(); i++) {
					encoded += encodings.get(toEncode.charAt(i));
				}
				output.write(encoded+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			input.close();
			output.close();
		}
	}
}
