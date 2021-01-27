package course;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.*;

public class SymbolTable {
	
	public static class ST<Key extends Comparable, Val> {
		private Node root = null;
		
		private class Node {
			Key key;
			Val value;
			Node left, right;
			
			public Node(Key k, Val v) {
				this.key = k;
				this.value = v;
			}
		}
		
		public Val get(Key key) {
			Node x = root;
			while(x != null) {
				int cmp = x.key.compareTo(key);
				if(cmp < 0) x = x.left;
				else if(cmp > 0) x = x.right;
				else return x.value;
			}
			
			return null;
		}
		
		public void put(Key key, Val value) {
			root = put(root, key, value);
		}
		
		private Node put(Node x, Key key, Val value) {
			if(x == null) return new Node(key, value);
			
			int cmp = key.compareTo(x.key);
			if(cmp < 0) 
				x.left = put(x.left, key, value);
			else if(cmp > 0)
				x.right = put(x.right, key, value);
			else
				x.value = value;
			return x;
		}
	}
	
	public static void main(String[] args) {
		//ST<String, Integer> st = new ST<>();
		//for(int i = 0; !StdIn.isEmpty(); i++) {
			//String key = StdIn.readString();
			//st.put(key, i);
		//}

//		for(String s: st) {
//			StdOut.println(s + " = " + s.get(s));
//		}
		

	}

}
