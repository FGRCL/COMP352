
public class CharacterEncodingHash {
	private String[] encodings;
	
	public CharacterEncodingHash() {
		encodings = new String[255];
	}
	
	public void insert(char key, String value) {
		encodings[key] = value;
	}
	
	public String get(char key) {
		return encodings[key];
	}
	
	public String toString() {
		String string = "";
		for(int i=0; i<encodings.length; i++) {
			if(encodings[i]!=null) {
				string += ((char)i)+": "+encodings[i]+"\t";
			}
		}
		return string;
	}
}
