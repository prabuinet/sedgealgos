package course;

import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class StackOfStrings {
	
	static class Stack<Type> implements Iterable<Type> {
			
		private Node first = null;
		
		private class Node {
			Type item;
			Node next;
		}
		
		public Stack() {
			
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public Type pop() {
			if(first == null)
				return null;
			Type s = first.item;
			first = first.next;
			return s;
		}
		
		public void push(Type s) {
			Node node = new Node();
			node.item = s;
			node.next = first;
			first = node;
		}
		
		public class StackIterator implements Iterator<Type> {
			
			private Node current = first;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Type next() {
				Type item = current.item;
				current = current.next;
				return item;
			}
		}

		@Override
		public Iterator<Type> iterator() {
			return new StackIterator();
		}
	}

	public static void main(String[] args) {

		Stack<String> stack = new Stack<String>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(s.equals("-")) StdOut.println(stack.pop());
			else stack.push(s);
		}
	}

}
