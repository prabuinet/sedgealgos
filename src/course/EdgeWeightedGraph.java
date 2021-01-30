package course;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EdgeWeightedGraph {

	public static class Edge implements Comparable<Edge> {
		final int v;
		final int w; 
		final double weight;
		
		public Edge(int v, int w, double weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
		
		public double weight() { return this.weight; }
		
		public int either() { return this.v; }
		
		public int other(int x) {
			if(x == v) return w;
			else return v;
		}
		
		public int compareTo(Edge that) {
			if(this.weight() < that.weight()) return -1;
			else if(this.weight() > that.weight()) return 1;
			else return 0;
		}
		
		public String toString() {
			return String.format("%d-%d %.2f", v, w, weight);
		}
	}
	
	
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(In in) {
        this.V = in.readInt();
        
        adj = (Bag<Edge>[]) new Bag[V];
        
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
        
        int E = in.readInt();
        
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return E;
	}
	
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	
	public Iterable<Edge> edges() {
		Bag<Edge> b = new Bag<Edge>();
		for(int v = 0; v < V; v++)
			for(Edge e : adj[v])
				if(e.other(v) > v)
					b.add(e);
		return b;
	}
	
	public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
	}
	
	public static void main(String[] args) {
		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		for(Edge e : G.edges())
			StdOut.println(e);
	}

}
