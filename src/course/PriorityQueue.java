package course;

import edu.princeton.cs.algs4.*;

public class PriorityQueue {
	
	public static class MaxPQ<Key extends Comparable<Key>> {
		Key[] pq;
		int n;
		
		public MaxPQ(int capacity) {
			pq = (Key[]) new Comparable[capacity + 1];
		}
		
		public boolean isEmpty() {
			return n == 0;
		}
		
		private void swim(int k) {
			while(k > 1 && less(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			}
		}
		
		public void insert(Key k) {
			pq[++n] = k;
			swim(n);
		}
		
		public Key delMax() {
			Key max = pq[1];
			exch(1, n--);
			sink(1);
			pq[n+1] = null;
			return max;
		}
		
		private void sink(int k) {
			while(2*k <= n) {
				int j = 2*k;
				if (j < n && less(j, j+1)) j++;
				if (!less(k, j)) break;
				exch(k, j);
				k = j;
			}
		}
		
		private boolean less(int i, int j) {
			return pq[i].compareTo(pq[j]) < 0;
		}
		
	    private void exch(int i, int j) {
	        Key t = pq[i];
	        pq[i] = pq[j];
	        pq[j] = t;
	    }
	    
	    public int size() {
	    	return n;
	    }
		
	}

	public static void main(String[] args) {
		
		int M = Integer.parseInt(args[0]);
		MaxPQ<Transaction> pq = new MaxPQ<Transaction>(M+1);
		
		while(StdIn.hasNextLine()) {
			pq.insert(new Transaction(StdIn.readLine()));
			if(pq.size() > M)
				pq.delMax();
		}
		
		Stack<Transaction> stack = new Stack<Transaction>();
		while(!pq.isEmpty()) stack.push(pq.delMax());
		for(Transaction t: stack) StdOut.println(t);
	}

}
