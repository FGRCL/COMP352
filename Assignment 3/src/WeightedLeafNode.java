
public class WeightedLeafNode extends Node<Integer>{
	private char character;
	
	public WeightedLeafNode(Integer weight, char character) {
		super(weight);
		this.character = character;
	}
	
	public char getCharacter() {
		return character;
	}
}
