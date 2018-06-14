import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Huffman {
	public static void main(String[] args) {
		Scanner file = null;
		ArrayListCharacterFrequency characterFrequency = new ArrayListCharacterFrequency(255);
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
		} 
		characterFrequency.sort();
		System.out.println(characterFrequency);
		NodePriorityQueue nodes = new NodePriorityQueue();
		while(!characterFrequency.isEmpty()) {
			nodes.push(characterFrequency.popToNode());
		}
		HuffmanTree huffmanTree = new HuffmanTree();
		while(nodes.getLength()>1) {
			nodes.insert(huffmanTree.merge(nodes.pop(), nodes.pop()));
			System.out.println(nodes);
		}
		huffmanTree.printTree();
	}
}
