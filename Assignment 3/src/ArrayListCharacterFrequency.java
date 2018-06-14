
public class ArrayListCharacterFrequency {
	char[] characters;
	int[] frequencies;
	int size;
	
	public ArrayListCharacterFrequency() {
		characters = new char[255];
		frequencies = new int[255];
		size = -1;
	}
	
	public ArrayListCharacterFrequency(int length) {
		characters = new char[length];
		frequencies = new int[length];
		size = -1;
	}
	
	public void insert(char character) {
		for(int i=0; i<=size; i++) {
			if(characters[i]==character) {
				frequencies[i]++;
				return;
			}
		}
		push(character);
	}
	
	private void push(char character) {
		size++;
		characters[size] = character;
		frequencies[size] = 1;
	}
	
	public WeightedLeafNode popToNode() {
		WeightedLeafNode node =  new WeightedLeafNode(frequencies[size], characters[size]);
		size--;
		return node;
	}
	
	public boolean isEmpty() {
		return (size == -1);
	}
	
	//reusing insertion sort from assignment 1
	public void sort() {
		char charToSort;
		int frequency;
		int insertIndex;
		for(int i=0; i<=size; i++) {
			charToSort = characters[i];
			frequency = frequencies[i];
			insertIndex=0;
			for(int j=i-1; j>=0; j--) {
				if(frequency>frequencies[j]) {
					insertIndex=j+1;
					break;
				}
			}
			for(int k = i-1; k>=insertIndex; k--) {
				characters[k+1] = characters[k];
			}
			characters[insertIndex] = charToSort;
			for(int k = i-1; k>=insertIndex; k--) {
				frequencies[k+1] = frequencies[k];
			}
			frequencies[insertIndex] = frequency;
		}
	}
	
	public String toString() {
		String string = "";
		string += "Character:\t";
		for(int i=0; i<=size; i++) {
			string += characters[i]+"\t";
		}
		string += "\nFrequencies:\t";
		for(int i=0; i<=size; i++) {
			string += frequencies[i]+"\t";
		}
		return string;
	}
}


