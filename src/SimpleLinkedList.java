import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {
	public static String[] data;
	public static int length;
	private static Node top;
	private static Node bottom;
	
	public SimpleLinkedList() {
		top = null;
		bottom = null;
		length = 0;
	}
	public SimpleLinkedList(List<String> list) {
		top = new Node (null, null, null);
		bottom = new Node (null, null, null);
		Node activeNode = top;
		for (int x = 0; x < list.size(); x++) {
			activeNode.data = list.get(x);
			Node nodeZero = activeNode;
			activeNode = new Node (activeNode, null, null);
			nodeZero.next = activeNode;
		}
		activeNode.data = list.get(list.size()-1);
		bottom = activeNode;
		length = list.size();
	}
	public static void main(String[] args) {
	}
	public void add (int index, String s) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		if (index == 0) {
			addFirst(s);
		} else if (index == length - 1) {
			addLast(s);
		} else {
			Node activeNode = getNode(index);
			Node nodeOne = new Node (activeNode.previous, s, activeNode);
			activeNode.previous.next = nodeOne;
			activeNode.previous = nodeOne;
			length++;
		}
	}
	public void addFirst (String s) {
		Node activeNode = top;
		Node nodeOne = new Node (null, s, activeNode);
		top = nodeOne;
		if (length == 0) {
			bottom = nodeOne;
		} else {
			activeNode.previous = nodeOne;
		}
		length++;
	}
	public void addLast (String s) {
		Node activeNode = bottom;
		Node nodeOne = new Node (activeNode, s, null);
		bottom = nodeOne;
		if (length == 0) {
			top = nodeOne;
		} else {
			activeNode.next = nodeOne;
		}
		length++;
	}
	public void clear () {
		top = new Node (null, null, null);
		bottom = new Node (null, null, null);
		length = 0;
		}
	public boolean contains (String s) {
		for(int x = 0; x < length; x++) {
			if (getNode(x).data == s) {
				return true;
			}
		}
		return false;
	}
	public String get (int index) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		return getNode(index).data;
	}
	public String getFirst () {
		if (length == 0) {
			throw new NoSuchElementException();
		}
		return top.data;
	}
	public String getLast () {
		if (length == 0) {
			throw new NoSuchElementException();
		}
		return bottom.data;
	}
	public int indexOf (String s) {
		for(int x = 0; x < length; x++) {
			if (getNode(x).data == s) {
				return x;
			}
		}
		return -1;
	}
	public String remove (int index) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		} else if (index == 0) {
			return removeFirst();
		} else if (index == length - 1) {
			return removeLast();
		} else {
			Node removedNode = getNode(index);
			removedNode.previous.next = removedNode.next;
			removedNode.next.previous = removedNode.previous;
			String removed = removedNode.data;
			length--;
			return removed;
		}
	}
	public boolean remove (String s) {
		boolean isFound = contains(s);
		if (isFound == false) {
			return false;
		} else {
			int index = indexOf(s);
			if (index == 0) {
				removeFirst();
				return true;
			} else if (index == length) {
				removeLast();
				return true;
			} else {
				Node removedNode = getNode(index);
				removedNode.previous.next = removedNode.next;
				removedNode.next.previous = removedNode.previous;
				length--;
				return true;
			}
		}
	}
	public String removeFirst () {
		String removed = top.data;
		Node removedNode = top;
		top = removedNode.next;
		top.previous = null;
		length--;
		return removed;
	}
	public String removeLast () {
		String removed = bottom.data;
		Node removedNode = bottom;
		bottom = removedNode.previous;
		bottom.next = null;
		length--;
		return removed;
	}
	public String set (int index, String s) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		Node activeNode = getNode(index);
		String replaced = activeNode.data;
		if (s == null) s = "null";
		activeNode.data = s;
		return replaced;
	}
	public int size () {
		return length;
	}
	public String toString () {
		String concat = "[";
		Node activeNode = top;
		if (length != 0) {
				for (int x = 0; x < length - 1; x++) {
				concat += activeNode.data + ", ";
				activeNode = activeNode.next;
			}
			concat += activeNode.data + "]";
		} 
			else {
			return "[]";
		}
		return concat;
	}
	public Node getNode(int index) {
		Node activeNode = top;
		for (int x = 0; x < index; x++) {
			activeNode = activeNode.next;
		}
		return activeNode;
	}
	public static class Node {
		Node previous;
		String data;
		Node next;	
		public Node(Node previous, String data, Node next) {
			this.previous = previous;
			this.data = data;
			this.next = next;
		}
	}
 }