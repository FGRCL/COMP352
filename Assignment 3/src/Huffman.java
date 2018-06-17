import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
	public static void main(String[] args) {
		//get the frequency of characters
		Scanner file = null;
		ArrayListCharacterFrequency characterFrequency = new ArrayListCharacterFrequency();
		try {
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
		System.out.println(characterFrequency);
		
		//build the priority queue of nodes
		NodePriorityQueue nodes = new NodePriorityQueue();
		while(!characterFrequency.isEmpty()) {
			nodes.push(characterFrequency.popToNode());
		}
		
		
		//build the HuffmanTree
		HuffmanTree huffmanTree = new HuffmanTree();
		while(nodes.getLength()>1) {
			nodes.insert(huffmanTree.merge(nodes.pop(), nodes.pop()));
		}
		
		//create the encoding table
		CharacterEncodingHash encodings = huffmanTree.createHash();		
		Scanner input = null;
		System.out.println(encodings);
		
		//get input to encode
		input = new Scanner(System.in);
		String toEncode;
		String encoded = "";
		toEncode = input.nextLine();
		for(int i=0; i<toEncode.length(); i++) {
			encoded += encodings.get(toEncode.charAt(i));
		}
		input.close();
		System.out.println(encoded);
	}
}
