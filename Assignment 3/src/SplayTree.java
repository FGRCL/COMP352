
public class SplayTree<E extends Comparable<E>> extends Tree<E>{
	
	
	public boolean find(E elem) {
		return find(root, elem);
	}
	
	private boolean find(Node<E> curr, E elem) {
		if(curr.getElement().compareTo(elem)==0) {
			splay(curr);
			return true;
		}else {
			if(!curr.isLeaf()) {
				if(curr.getElement().compareTo(elem)>0) {
					find(curr.getLeft(), elem);
				}else if(curr.getElement().compareTo(elem)<0) {
					find(curr.getRight(), elem);
				}
			}
			return false;
		}
	}
	
	public void add(Node<E> curr, E elem) {
		if(curr.getElement().compareTo(elem)>0) {
			if(curr.getLeft() == null) {
				Node<E> newNode = new Node(elem, curr);
				curr.setLeft(newNode);
				splay(newNode);
			}else {
				add(curr.getLeft(), elem);
			}		
		}else if(curr.getElement().compareTo(elem)<0) {
			if(curr.getRight() == null) {
				Node<E> newNode = new Node(elem, curr);
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
			if(curr.getLeft().getElement().compareTo(elem)==0) {
				 
			}else if(curr.getRight().getElement().compareTo(elem)==0) {
				
			}else if(curr.getElement().compareTo(elem)>0) {
				
			}else if(curr.getElement().compareTo(elem)<0) {
				
			}
		}else {
			return true;
		}
	}
	
	private void splay(Node<E> accessed) {
		//verify these conditions
		while( !(accessed.equals(root) || accessed.equals(root.getLeft()) || accessed.equals(root.getRight())) ) {
			Node<E> parent = accessed.getParent();
			Node<E> grandparent = parent.getParent();
			if(grandparent.getLeft().getLeft().equals(accessed)) {
				zigzigLeft(grandparent, parent, accessed);
			}else if(grandparent.getRight().getRight().equals(accessed)) {
				zigzigRight(grandparent, parent, accessed);
			}else if(grandparent.getLeft().getRight().equals(accessed)) {
				zigzagLeft(grandparent, parent, accessed);
			}else if(grandparent.getRight().getLeft().equals(accessed)) {
				zigzagRight(grandparent, parent, accessed);
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
		parent.getLeft().setParent(parent);
		parent.getParent().setLeft(child);
		child.setParent(parent.getParent());
		parent.setParent(child);
	}
	
	private void rotateLeft(Node<E> parent, Node<E> child) {
		Node<E> leftTemp = child.getLeft();
		child.setLeft(parent);
		parent.setRight(leftTemp);
		parent.getRight().setParent(parent);
		parent.getParent().setRight(child);
		child.setParent(parent.getParent());
		parent.setParent(child);
	}
}
