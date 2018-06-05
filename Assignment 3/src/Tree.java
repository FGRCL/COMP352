
public class Tree {
	public class Node<E> {
		private E element;
		private Node left;
		private Node right;
		
		public Node() {
			element = null;
			left = null;
			right = null;
		}
	}	
	
	private Node root;
	
	private Tree() {
		root = new Node();
	}
}
