
public class Tree<E extends Comparable<E>> {
	private class Node<E extends Comparable<E>>{
		private E element;
		private Node<E> left;
		private Node<E> right;
		
		public Node() {
			element = null;
			left = null;
			right = null;
		}
		
		public Node(E value) {
			element = value;
			left = null;
			right = null;
		}
		
		public Node(E value, Node left, Node right) {
			element = value;
			this.left = left;
			this.right = right;
		}	
		
	}
	private Node<E> root;
	
	public Tree() {
		root = null;
	}
	
	public void insert(E elem) {
		insert(root, elem);
	}
	
	private void insert(Node<E> curr, E elem) {
		if(curr == null) {
			curr = new Node<E>(elem);
		}else if(curr.element.compareTo(elem)>1) {
			insert(curr.left, elem);
		}else if(curr.element.compareTo(elem)<1) {
			insert(curr.right, elem);
		}
	}
	
	public void printInorder() {
		printInorder(root);
	}
	
	public void printInorder(Node curr) {
		if(curr !=null) {
			printInorder(curr.left);
			System.out.println(curr.element);
			printInorder(curr.right);
		}
	}
}
