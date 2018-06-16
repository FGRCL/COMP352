import java.util.Random;

public class SplayTree<E extends Comparable<E>> extends Tree<E>{
	
	public static void main(String[] args) {
		SplayTree<Integer> tree = new SplayTree<Integer>();
		tree.add(87);
		tree.add(1);
		tree.add(71);
		tree.add(74);
		tree.add(16);
		tree.add(20);
		tree.add(55);
		tree.add(8);
		tree.add(100);
		tree.printTree();
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		tree.add(20);
		tree.printTree();
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		tree.find(71);
		tree.printTree();
		System.out.println("----------------------------------------------------------------------------------------------------------------");
	}
	
	public Node<E> find(E elem) {
		return find(root, elem);
	}
	
	private Node<E> find(Node<E> curr, E elem) {
		if(curr.getElement().compareTo(elem)==0) {
			splay(curr);
			return curr;
		}else {
			if(!curr.isLeaf()) {
				if(curr.getElement().compareTo(elem)>0) {
					find(curr.getLeft(), elem);
				}else if(curr.getElement().compareTo(elem)<0) {
					find(curr.getRight(), elem);
				}
			}
			return null;
		}
	}
	
	public void add(E elem) {
		if(root == null) {
			root = new Node<E>(elem);
		}else {
			add(root, elem);
		}
		
	}
	
	public void add(Node<E> curr, E elem) {
		if(curr.getElement().compareTo(elem)>0) {
			if(curr.getLeft() == null) {
				Node<E> newNode = new Node<E>(elem, curr);
				curr.setLeft(newNode);
				splay(newNode);
			}else {
				add(curr.getLeft(), elem);
			}		
		}else if(curr.getElement().compareTo(elem)<0) {
			if(curr.getRight() == null) {
				Node<E> newNode = new Node<E>(elem, curr);
				curr.setRight(newNode);
				splay(newNode);
			}else {
				add(curr.getRight(), elem);
			}
		}
	}
	
	public boolean remove(E elem) {
		return remove(root, elem);
	}
	
	private boolean remove(Node<E> curr, E elem) {
		if(!curr.isLeaf()) {
			if(curr.getLeft()!=null && curr.getLeft().getElement().compareTo(elem)==0) {
				Node<E> minNode = getMin(curr.getLeft().getRight());
				curr.getLeft().setElement(minNode.getElement());
				splay(curr);
				return true;
			}else if(curr.getRight()!=null &&curr.getRight().getElement().compareTo(elem)==0) {
				Node<E> minNode = getMin(curr.getRight().getRight());
				curr.getRight().setElement(minNode.getElement());
				splay(curr);
				return true;
			}else if(curr.getElement().compareTo(elem)>0) {
				remove(curr.getLeft(), elem);
			}else if(curr.getElement().compareTo(elem)<0) {
				remove(curr.getRight(), elem);
			}
		}
		return false;
	}
	
	private Node<E> getMin(Node<E> curr) {
		if(curr.getLeft() == null) {
			return curr;
		}else {
			return getMin(curr.getLeft());
		}
	}
	
	private void delete(Node<E> deleted) {
		deleted.getParent().setLeft(deleted.getRight());
		if (deleted.getRight() != null) deleted.getRight().setParent(deleted.getParent());
	}
	private void splay(Node<E> accessed) {
		//verify these conditions
		while( !(accessed.equals(root) || accessed.equals(root.getLeft()) || accessed.equals(root.getRight())) ) {
			Node<E> grandparent = accessed.getParent().getParent();
			if(grandparent.getElement().compareTo(accessed.getElement())>0) {
				if(grandparent.getLeft().getElement().compareTo(accessed.getElement())>0) {
					zigzigLeft(grandparent, accessed.getParent(), accessed);
				}else if(grandparent.getLeft().getElement().compareTo(accessed.getElement())<0) {
					zigzagLeft(grandparent, accessed.getParent(), accessed);
				}
			}else if(grandparent.getElement().compareTo(accessed.getElement())<0) {
				if(grandparent.getRight().getElement().compareTo(accessed.getElement())>0) {
					zigzagRight(grandparent, accessed.getParent(), accessed);		
				}else if(grandparent.getRight().getElement().compareTo(accessed.getElement())<0) {
					zigzigRight(grandparent, accessed.getParent(), accessed);			
				}
			}
			if(grandparent.equals(root)) {
				root = accessed;
			}
		}
	}
	
	private void zigzigRight(Node<E> grandparent, Node<E> parent, Node<E> child) {
		rotateLeft(grandparent, parent);
		rotateLeft(parent, child);
	}
	
	private void zigzigLeft(Node<E> grandparent, Node<E> parent, Node<E> child) {
		rotateRight(grandparent, parent);
		rotateRight(parent, child);
	}
	
	private void zigzagRight(Node<E> grandparent, Node<E> parent, Node<E> child) {
		rotateRight(parent, child);
		rotateLeft(grandparent, child);
	}
	
	private void zigzagLeft(Node<E> grandparent, Node<E> parent, Node<E> child) {
		rotateLeft(parent, child);
		rotateRight(grandparent, child);
	}

	private void rotateRight(Node<E> parent, Node<E> child) {
		Node<E> rightTemp = child.getRight();
		child.setRight(parent);
		parent.setLeft(rightTemp);
		if(parent.getLeft() != null) parent.getLeft().setParent(parent);
		updateAncestorNode(parent, child);
		child.setParent(parent.getParent());
		parent.setParent(child);
	}
	
	private void rotateLeft(Node<E> parent, Node<E> child) {
		Node<E> leftTemp = child.getLeft();
		child.setLeft(parent);
		parent.setRight(leftTemp);
		if(parent.getRight() != null) parent.getRight().setParent(parent);
		updateAncestorNode(parent, child);
		child.setParent(parent.getParent());
		parent.setParent(child);
	}
	
	private void updateAncestorNode(Node<E> parent, Node<E> child) {
		if(parent.getParent() != null) {
			if(parent.getParent().getLeft()!=null && parent.getParent().getLeft().equals(parent)) {
				parent.getParent().setLeft(child);
			}else if(parent.getParent().getRight()!=null && parent.getParent().getRight().equals(parent)) {
				parent.getParent().setRight(child);
			}
		}
	}
}
