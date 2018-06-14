
public class NodePriorityQueue {
	int length;
	ListNode head;
	private class ListNode{
		private Node<Integer> element;
		private ListNode next;
		
		public ListNode(Node<Integer> element, ListNode next) {
			this.element = element;
			this.next = next;
		}
	}
	
	public NodePriorityQueue() {
		head = null;
		length = 0;
	}
	
	public void push(Node<Integer> element) {
		if(head!=null) {
			ListNode listNode = new ListNode(element, head); 
			head = listNode;
		}else {
			head = new ListNode(element, head);
		}
		length++;
	}
	
	public Node<Integer> pop() {
		ListNode node = null;
		if(head!=null) {
			node = head;
			head = node.next;		
		}	
		length--;
		if(node == null) {
			return null;
		}else {
			return node.element;
		}		
	}
	
	public void insert(Node<Integer> element) {
		ListNode curr = head;
		ListNode prev = null;
		while(curr != null) {
			if(element.getElement() < curr.element.getElement()) {
				if(prev == null) {
					push(element);
				}else{
					ListNode listNode = new ListNode(element, curr);
					prev.next = listNode;
					length++;
				}
				
				return;
			}
			prev = curr;
			curr = curr.next;
		}
		if(prev == null) {
			push(element);
		}else{
			ListNode listNode = new ListNode(element, curr);
			prev.next = listNode;
			length++;
		}
	}
	
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public int getLength() {
		return length;
	}
	public String toString() {
		String string = "\t\t";
		ListNode curr = head;
		while(curr != null) {
			string += curr.element.getElement()+"\t";
			curr = curr.next;
		}
		string += "length: "+length;
		return string;
	}
	
	public String debugPrint(ListNode newly) {
		String string = "\t\t";
		ListNode curr = head;
		while(curr != null) {
			if(newly.equals(curr)) string += "new: ";
			string += curr.element.getElement()+"\t";
			curr = curr.next;
		}
		string += "length: "+length;
		return string;
	}
}
