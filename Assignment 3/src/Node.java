
public class Node<E> {
	private E element;
	private Node<E> left;
	private Node<E> right;

	public Node(E value) {
		element = value;
		left = null;
		right = null;
	}

	public Node(E value, Node<E> left, Node<E> right) {
		element = value;
		this.left = left;
		this.right = right;
	}	

	public Node<E> getRight() {
		return right;
	}
	
	public void setRight(Node<E> node) {
		right = node;
	}
	
	public Node<E> getLeft() {
		return left;
	}
	
	public void setLeft(Node<E> node) {
		left = node;
	}
	
	public E getElement() {
		return element;
	}
	
	public boolean isLeaf() {
		return (left == null && right == null);
	}
}
