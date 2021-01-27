package course;
import edu.princeton.cs.algs4.*;

public class UnionFind {
	
	public static class UFEager {
		int[] id;
		
		public UFEager(int n) {
			id = new int[n];
			for(int i = 0; i < n; i++)
				id[i] = i;
		}
		
		void union(int p, int q) {
			int pid = id[p];
			int qid = id[q];
			for(int i = 0; i < id.length; i++) {
				if(id[i] == pid)
					id[i] = qid;
			}
		}
		
		boolean connected(int p, int q) {
			return id[p] == id[q];
		}
	}
	
	public static class UFLazy {
		int[] id;
		
		public UFLazy(int n) {
			id = new int[n];
			for(int i = 0; i < n; i++)
				id[i] = i;
		}
		
		private int root(int i) {
			while(i != id[i])
				i = id[i];
			return i;
		}
		
		void union(int p, int q) {
			int pid = root(p);
			int qid = root(q);
			id[pid] = qid;
		}
		
		boolean connected(int p, int q) {
			return root(p) == root(q);
		}
	}
	
	public static class UFWeighted {
		int[] id;
		int[] sz;
		
		public UFWeighted(int n) {
			id = new int[n];
			sz = new int[n];
			
			for(int i = 0; i < n; i++) {
				id[i] = i;
				sz[i] = 0;
			}
		}
		
		private int root(int i) {
			while(i != id[i])
				i = id[i];
			return i;
		}
		
		void union(int p, int q) {
			int i = root(p);
			int j = root(q);
			if(i == j)
				return;
			if(sz[i] < sz[j]) {
				id[i] = j;
				sz[j] += sz[i];
			} else {
				id[j] = i;
				sz[i] += sz[j];
			}
		}
		
		boolean connected(int p, int q) {
			return root(p) == root(q);
		}
	}
	
	public static class UFWeightedPathCompression {
		int[] id;
		int[] sz;
		
		public UFWeightedPathCompression(int n) {
			id = new int[n];
			sz = new int[n];
			
			for(int i = 0; i < n; i++) {
				id[i] = i;
				sz[i] = 1;
			}
		}
		
		private int root(int i) {
			while(i != id[i]) {
				id[i] = id[id[i]];
				i = id[i];
			}
			
			return i;
		}
		
		void union(int p, int q) {
			int i = root(p);
			int j = root(q);
			if(i == j)
				return;
			if(sz[i] < sz[j]) {
				id[i] = j;
				sz[j] += sz[i];
			} else {
				id[j] = i;
				sz[i] += sz[j];
			}
		}
		
		boolean connected(int p, int q) {
			return root(p) == root(q);
		}
	}

	public static void main(String[] args) {

		int n = StdIn.readInt();
		//UFEager uf = new UFEager(n);
		//UFLazy uf = new UFLazy(n);
		//UFWeighted uf = new UFWeighted(n);
		UFWeightedPathCompression uf = new UFWeightedPathCompression(n);
		
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			
			if(!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " " + q);
			}
		}
	}

}
