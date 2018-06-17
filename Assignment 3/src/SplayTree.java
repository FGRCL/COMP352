import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SplayTree<E extends Comparable<E>> extends Tree<E>{
	
	public static void main(String[] args) {
		Scanner operations = null;
		try {
			operations = new Scanner(new File(args[0]));
			int displayNb = 0;
			boolean allowDebug = false;
			if(args.length > 1) {
				displayNb = Integer.parseInt(args[1]);
				allowDebug = true;
			}
			SplayTree<Integer> tree = new SplayTree<Integer>();
			int count = 0;
			while(operations.hasNext()) {
				if(count == displayNb && allowDebug) {
					System.out.println("Traversal at "+count+": "+tree.postorder());
					System.out.println(tree.getCompares()+" compares");
					System.out.println(tree.getZigzig()+" Zig-zigs");
					System.out.println(tree.getZigzag()+" Zig-zag");
				}
				String line = operations.nextLine();
				char operand = line.charAt(0);
				int value = Integer.parseInt(line.substring(1));
				switch (operand) {
					case 'a':
						tree.add(value);
						break;
					case 'f':
						tree.find(value);
						break;
					case 'r':
						tree.remove(value);
						break;
				}
				count++;
			}
			System.out.println(tree.postorder());
			operations.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//instance variables
	private int nbCompares;
	private int nbZigzig;
	private int nbZigzag;
	
	//constructors
	public SplayTree() {
		super();
		nbCompares = 0;
		nbZigzig = 0;
		nbZigzag = 0;
	}
	
	//find methods
	public Node<E> find(E elem) {
		return find(root, elem);
	}
	
	private Node<E> find(Node<E> curr, E elem) {
		nbCompares++;
		if(curr == null) {
			return curr;
		}else if(curr.getElement().compareTo(elem)==0) {
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
	
	//add methods
	public void add(E elem) {
		if(root == null) {
			nbCompares++;
			root = new Node<E>(elem);
		}else {
			add(root, elem);
		}
		
	}
	
	private void add(Node<E> curr, E elem) {
		nbCompares++;
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
	
	//remove methods
	public void remove(E elem) {
		if(root != null) {
			if(root.getElement().compareTo(elem) == 0) {
				nbCompares++;
				if(root.getLeft() == null && root.getRight() == null) {
					root = null;
				}else if(root.getLeft() != null && root.getRight() == null && root.getLeft().isLeaf()) {
					root = root.getLeft();
				}else if(root.getLeft() == null && root.getRight() != null && root.getRight().isLeaf()) {
					root = root.getRight();
				}else {
					Node<E> minNode = getMin(root.getRight());
					root.setElement(minNode.getElement());
					remove(minNode.getParent(), minNode.getElement());
				}
			}else {
				remove(root, elem);
			}
		}
	}
	
	private void remove(Node<E> curr, E elem) {
		nbCompares++;
		if(curr.getLeft() !=null && curr.getLeft().getElement().compareTo(elem) == 0) {//The node to delete is at the left of the current node
			Node<E> deleteNode = curr.getLeft();
			if(deleteNode.getLeft() == null && deleteNode.getRight() == null) {
				curr.setLeft(null);
			}else if(deleteNode.getLeft() != null && deleteNode.getRight() == null) {
				curr.setLeft(deleteNode.getLeft());
				curr.getLeft().setParent(curr);
			}else if(deleteNode.getLeft() == null && deleteNode.getRight() != null) {
				curr.setLeft(deleteNode.getRight());
				curr.getLeft().setParent(curr);
			}else {
				Node<E> minNode = getMin(deleteNode.getRight());
				deleteNode.setElement(minNode.getElement());
				remove(minNode.getParent(), minNode.getElement());
			}
			splay(curr);
		}else if(curr.getRight() != null && curr.getRight().getElement().compareTo(elem) == 0) {//The node to delete is at the right of the current node
			Node<E> deleteNode = curr.getRight();
			if(deleteNode.getLeft() == null && deleteNode.getRight() == null) {
				curr.setRight(null);
			}else if(deleteNode.getLeft() != null && deleteNode.getRight() == null) {
				curr.setRight(deleteNode.getLeft());
				curr.getRight().setParent(curr);
			}else if(deleteNode.getLeft() == null && deleteNode.getRight() != null) {
				curr.setRight(deleteNode.getRight());
				curr.getRight().setParent(curr);
			}else {
				Node<E> minNode = getMin(deleteNode.getRight());
				deleteNode.setElement(minNode.getElement());
				remove(minNode.getParent(), minNode.getElement());
			}
			splay(curr);
		}else if(curr.getElement().compareTo(elem)>0) {//continue to traverse
			if(curr.getLeft() == null) {
			}else {
				remove(curr.getLeft(), elem);
			}
		}else if(curr.getElement().compareTo(elem)<0) {//continue to traverse
			if(curr.getRight() == null) {
			}else {
				remove(curr.getRight(), elem);
			}
		}
	}
	
	private Node<E> getMin(Node<E> curr) {
		if(curr.getLeft() == null) {
			return curr;
		}else {
			return getMin(curr.getLeft());
		}
	}
	
	//splaying methods
	private void splay(Node<E> accessed) {
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
		nbZigzig++;
		rotateLeft(grandparent, parent);
		rotateLeft(parent, child);
	}
	
	private void zigzigLeft(Node<E> grandparent, Node<E> parent, Node<E> child) {
		nbZigzig++;
		rotateRight(grandparent, parent);
		rotateRight(parent, child);
	}
	
	private void zigzagRight(Node<E> grandparent, Node<E> parent, Node<E> child) {
		nbZigzag++;
		rotateRight(parent, child);
		rotateLeft(grandparent, child);
	}
	
	private void zigzagLeft(Node<E> grandparent, Node<E> parent, Node<E> child) {
		nbZigzag++;
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
	
	//getters and setters
	public int getCompares() {
		return nbCompares;
	}
	
	public int getZigzig() {
		return nbZigzig;
	}
	
	public int getZigzag() {
		return nbZigzag;
	}
}
