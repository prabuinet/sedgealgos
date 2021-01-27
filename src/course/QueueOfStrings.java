package course;


public class QueueOfStrings {
	
	static class Queue {
		
		private Node first = null;
		private Node last = null;
		
		private class Node {
			String item;
			Node next;
		}
		
		public Queue() {
			
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public void enqueue(String s) {
			Node oldlast = last;
			last = new Node();
			last.item = s;
			last.next = null;
			if(isEmpty()) first = last;
			else oldlast.next = last;
		}
		
		public String dequeue() {
			String s = first.item;
			first = first.next;
			if(isEmpty()) last = null;
			return s;
		}
	}

	public static void main(String[] args) {

	}

}
