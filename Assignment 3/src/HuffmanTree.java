
public class HuffmanTree extends Tree<Integer>{
	
	public Node<Integer> merge(Node<Integer> left, Node<Integer> right){
		int sum = left.getElement() + right.getElement();
		root = new Node<Integer>(sum, left, right);
		return root;
	}
	
	
}
