import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList1 {
	
	public static String[] data;
	public static int size;
	
	private static Node head;
	private static Node tail;
	
	public SimpleLinkedList1() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public SimpleLinkedList1(List<String> list) {
		head = new Node (null, null, null);
		tail = new Node (null, null, null);
		Node currentNode = head;
		for (int x = 0; x < list.size(); x++) {
			currentNode.data = list.get(x);
			Node prevNode = currentNode;
			currentNode = new Node (currentNode, null, null);
			prevNode.next = currentNode;
		}
		currentNode.data = list.get(list.size()-1);
		tail = currentNode;
		size = list.size();
	}
	
	public static void main(String[] args) {

	}
	
	
	public void add (int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		if (index == 0) {
			addFirst(s);
		} else if (index == size - 1) {
			addLast(s);
		} else {
			Node currentNode = getNode(index);
			Node newNode = new Node (currentNode.prev, s, currentNode);
			currentNode.prev.next = newNode;
			currentNode.prev = newNode;
			size++;
		}
	}

	public void addFirst (String s) {
		Node currentNode = head;
		Node newNode = new Node (null, s, currentNode);
		head = newNode;
		if (size == 0) {
			tail = newNode;
		} else {
			currentNode.prev = newNode;
		}
		size++;
	}
	
	public void addLast (String s) {
		Node currentNode = tail;
		Node newNode = new Node (currentNode, s, null);
		tail = newNode;
		if (size == 0) {
			head = newNode;
		} else {
			currentNode.next = newNode;
		}
		size++;
	}
	
	public void clear () {
		head = new Node (null, null, null);
		tail = new Node (null, null, null);
		size = 0;
		}
	
	public boolean contains (String s) {
		for(int x = 0; x < size; x++) {
			if (getNode(x).data == s) {
				return true;
			}
		}
		return false;
	}
	
	public String get (int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return getNode(index).data;
	}
	
	public String getFirst () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return head.data;
	}
	
	public String getLast () {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}
	
	public int indexOf (String s) {
		for(int x = 0; x < size; x++) {
			if (getNode(x).data == s) {
				return x;
			}
		}
		return -1;
	}
	
	public String remove (int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node removedNode = getNode(index);
			removedNode.prev.next = removedNode.next;
			removedNode.next.prev = removedNode.prev;
			String removed = removedNode.data;
			size--;
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
			} else if (index == size) {
				removeLast();
				return true;
			} else {
				Node removedNode = getNode(index);
				removedNode.prev.next = removedNode.next;
				removedNode.next.prev = removedNode.prev;
				size--;
				return true;
			}
		}
	}
	
	public String removeFirst () {
		String removed = head.data;
		Node removedNode = head;
		head = removedNode.next;
		head.prev = null;
		size--;
		return removed;
	}
	
	public String removeLast () {
		String removed = tail.data;
		Node removedNode = tail;
		tail = removedNode.prev;
		tail.next = null;
		size--;
		return removed;
	}
	
	public String set (int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node currentNode = getNode(index);
		String replaced = currentNode.data;
		if (s == null) s = "null";
		currentNode.data = s;
		return replaced;
	}
	
	public int size () {
		return size;
	}
	
	public String toString () {
		String concat = "[";
		Node currentNode = head;
		if (size != 0) {
			for (int x = 0; x < size - 1; x++) {
				concat += currentNode.data + ", ";
				currentNode = currentNode.next;
			}
			concat += currentNode.data + "]";
		} else {
			return "[]";
		}
		return concat;
	}
	
	public Node getNode(int index) {
		Node currentNode = head;
		for (int x = 0; x < index; x++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	public static class Node {
		
		Node prev;
		String data;
		Node next;
		
		public Node(Node prev, String data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}
 }