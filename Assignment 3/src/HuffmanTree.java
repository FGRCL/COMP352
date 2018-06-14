
public class HuffmanTree extends Tree<Integer>{
	
	public Node<Integer> merge(Node<Integer> left, Node<Integer> right){
		int sum = left.getElement() + right.getElement();
		root = new Node<Integer>(sum, left, right);
		return root;
	}
	
	public CharacterEncodingHash createHash() {
		CharacterEncodingHash hash = new CharacterEncodingHash();
		createHash(root, "", hash);
		return hash;
	}
	
	private void createHash(Node<Integer> curr, String encoding, CharacterEncodingHash hash) {
		if(curr.isLeaf()) {
			WeightedLeafNode leaf = (WeightedLeafNode) curr;
			hash.insert(leaf.getCharacter(), encoding);
		}else {
			createHash(curr.getLeft(), encoding+"0", hash);
			createHash(curr.getRight(), encoding+"1", hash);
		}
	}
}
