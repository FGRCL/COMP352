
public class Tree<E extends Comparable<E>> {
	
	protected Node<E> root;
	
	public Tree() {
		root = null;
	}
	
	public void add(E elem) {
		if(root == null) {
			root = new Node<E>(elem);
		}else {
			add(root, elem);
		}
		
	}
	
	private void add(Node<E> curr, E elem) {
		if(curr.getElement().compareTo(elem)>0) {
			if(curr.getLeft() == null) {		
				curr.setLeft(new Node<E>(elem));
			}else {
				add(curr.getLeft(), elem);
			}		
		}else if(curr.getElement().compareTo(elem)<0) {
			if(curr.getRight() == null) {
				curr.setRight(new Node<E>(elem));
			}else {
				add(curr.getRight(), elem);
			}
		}
	}
	
	public void printInorder() {
		printInorder(root);
	}
	
	public void printInorder(Node<E> curr) {
		if(curr !=null) {
			printInorder(curr.getLeft());
			System.out.println(curr.getElement());
			printInorder(curr.getRight());
		}
	}
	
	public String postorder() {
		return postorder(root);
	}
	
	private String postorder(Node<E> curr) {
		if(curr != null) {
			if(curr.equals(root)) {
				return postorder(curr.getLeft()) + postorder(curr.getRight()) + curr.getElement();
			}else {
				return postorder(curr.getLeft()) + postorder(curr.getRight()) + curr.getElement()+",";
			}
		}
		return"";
	}
	
	public void printTree(Node<E> curr, int depth) {
		int nextLevel = depth + 1;
		if(curr.getRight() != null) printTree(curr.getRight(), nextLevel);
		for(int i=0; i<depth; i++) {
			System.out.print("\t");
		}
		System.out.println(curr.getElement());
		if(curr.getLeft() != null) printTree(curr.getLeft(), nextLevel);
		
	}
	
	public void printTree() {
		printTree(root, 0);
	}
}
