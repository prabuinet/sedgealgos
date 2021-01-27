package course;

import edu.princeton.cs.algs4.*;

public class Graph {

	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Graph(In in) {
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
	
	public Graph(int v) {
		this.V = v;
		
		adj = new Bag[v];
		
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	private static class DepthFirstPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int s;
		
		public DepthFirstPaths(Graph G, int s) {
			this.marked = new boolean[G.V()];
			this.edgeTo = new int[G.V()];
			dfs(G, s);
		}
		
		private void dfs(Graph G, int v) {
			marked[v] = true;
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					dfs(G, w);
					edgeTo[w] = v;
				}
			}
		}
		
		public boolean hasPathTo(int v) {
			return marked[v];
		}
		
		public Iterable<Integer> pathTo(int v) {
			if(!hasPathTo(v)) return null;
			
			Stack<Integer> path = new Stack<Integer>();
			for(int x = v; x != s; x = edgeTo[x])
				path.push(x);
			
			path.push(s);
			return path;
		}
	}
	
	private static class BreathFirstPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int[] distTo;
		
		public BreathFirstPaths(Graph G, int s) {
	        marked = new boolean[G.V()];
	        distTo = new int[G.V()];
	        edgeTo = new int[G.V()];
			bfs(G, s);
		}
		
		private void bfs(Graph G, int s) {
			Queue<Integer> q = new Queue<Integer>();
			q.enqueue(s);
			marked[s] = true;
			distTo[s] = 0;

			
			while(!q.isEmpty()) {
				int v = q.dequeue();
				for(int w : G.adj(v)) {
					if(!marked[w]) {
						q.enqueue(w);
						marked[w] = true;
						edgeTo[w] = v;
						distTo[w] = distTo[v] + 1;
					}
				}
			}
		}
		
	    public boolean hasPathTo(int v) {
	        return marked[v];
	    }
	    
	    public int distTo(int v) {
	        return distTo[v];
	    }
	    
	    public Iterable<Integer> pathTo(int v) {
	        if (!hasPathTo(v)) return null;
	        Stack<Integer> path = new Stack<Integer>();
	        int x;
	        for (x = v; distTo[x] != 0; x = edgeTo[x])
	            path.push(x);
	        path.push(x);
	        return path;
	    }
	}
	
	private static class CC {
	    private boolean[] marked;   // marked[v] = has vertex v been marked?
	    private int[] id;           // id[v] = id of connected component containing v
	    private int[] size;         // size[id] = number of vertices in given component
	    private int count;          // number of connected components

		public CC(Graph G) {
	        marked = new boolean[G.V()];
	        id = new int[G.V()];
	        size = new int[G.V()];
	        for (int v = 0; v < G.V(); v++) {
	            if (!marked[v]) {
	                dfs(G, v);
	                count++;
	            }
	        }
		}
		
	    private void dfs(Graph G, int v) {
	        marked[v] = true;
	        id[v] = count;
	        size[count]++;
	        for (int w : G.adj(v)) {
	            if (!marked[w]) {
	                dfs(G, w);
	            }
	        }
	    }
		
		boolean connected(int v, int w) {
			return id(v) == id(w);
		}
		
		int count() {
			return count;
		}
		
		int id(int v) {
			return id[v];
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		Graph G = new Graph(in);
		
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v))
				StdOut.println(v + "-" + w);
		
		DepthFirstPaths p = new DepthFirstPaths(G, 0);
		Iterable<Integer> path = p.pathTo(3);
		for(int w : path)
			StdOut.println(w);
		
	}
}
