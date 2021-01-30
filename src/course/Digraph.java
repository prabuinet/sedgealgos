package course;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
	
	private final int V;
	private int E;
	private final Bag<Integer>[] adj;
	
	Digraph(int V) {
		this.V = V;
		this.adj = new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	Digraph(In in) {
        this.V = in.readInt();
        
        adj = (Bag<Integer>[]) new Bag[V];
        
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        
        int E = in.readInt();
        
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w); 
        }
	}
	
	Iterable<Integer> adj(int v) {
		return this.adj[v];
	}
	
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public int V() {
		return this.V;
	}
	
	public int E() {
		return E;
	}
	
	public Digraph reverse() {
		Digraph r = new Digraph(V);
		for(int v = 0; v < V; v++)
			for(int w: adj[v])
				r.addEdge(w, v);
		return r;
	}
	
	private static class DirectedDFS
	{
		private boolean[] marked;
		
		public DirectedDFS(Digraph G, int s) {
			marked = new boolean[G.V()];
			dfs(G, s);
		}
		
		private void dfs(Digraph G, int v) { 
			marked[v] = true;
			for(int w : G.adj(v)) 
				if(!marked[w]) dfs(G, w);
		}
		
		public boolean visited(int v) {
			return marked[v];
		}
	}
	
	private static class DirectedCycle
	{
		private boolean[] marked;
		private int[] edgeTo;
		private Stack<Integer> cycle;
		private boolean[] onStack;
		
		public DirectedCycle(Digraph G) {
			marked = new boolean[G.V()];
			onStack = new boolean[G.V()];
			edgeTo = new int[G.V()];
			for(int v = 0; v < G.V(); v++)
				if(!marked[v]) dfs(G, v);
		}
		
		private void dfs(Digraph G, int v) { 
			onStack[v] = true;
			marked[v] = true;
			
			for(int w : G.adj(v)) { 
				
				if(this.hasCycle()) return;
				
				else if(!marked[w]) { 
					edgeTo[w] = v;
					dfs(G, w);
				}
				else if(onStack[w]) {
					cycle = new Stack<Integer>();
					for(int x = v; x != w; x = edgeTo[x])
						cycle.push(x);
					cycle.push(w);
					cycle.push(v);
				}
			}
			
			onStack[v] = false;
		}
		
		public Iterable<Integer> cycle() {
			return cycle;
		}
		
		public boolean hasCycle() {
			return cycle != null;
		}
		
		public boolean visited(int v) {
			return marked[v];
		}
	}
	
	
	private static class TopologicalSort {
		// This works only on DAG (Directed Acyclic Graph)
		// If there is cycle its not possible to sort
		
		// Working mechanism:
		// Run DFS 
		// Return vertices in reverse post order.
		
		private boolean[] marked;
		private Stack<Integer> reversePost;
		
		public TopologicalSort(Digraph G) {
			marked = new boolean[G.V()];
			reversePost = new Stack<Integer>();
			for(int v = 0; v < G.V(); v++)
				if(!marked[v]) dfs(G, v);
		}
		
		private void dfs(Digraph G, int v) { 
			marked[v] = true;
			for(int w : G.adj(v)) 
				if(!marked[w]) dfs(G, w);
			reversePost.push(v);
		}
		
		public Iterable<Integer> reversePost() {
			return this.reversePost;
		}
	}
	
	public static void main(String[] args) {

		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		
		for(int v = 0; v < G.V(); v++) 
			for(int w : G.adj(v))
				StdOut.println(v + "->" + w);
		
		DirectedCycle cycle = new DirectedCycle(G);
		if(cycle.hasCycle())
			for(int v : cycle.cycle())
				StdOut.print(v + " ");
	
		StdOut.println();
		
		TopologicalSort ts = new TopologicalSort(G);
		for(int v: ts.reversePost())
			StdOut.print(v + " ");
	}

}
