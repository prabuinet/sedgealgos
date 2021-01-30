package course;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class MinimumSpanning {

	private static class KruskalsMST {
		
		// sort the edges according to weight
		// add next edge to the tree unless it create a cycle
		
		private Queue<Edge> mst = new Queue<Edge>();
		
		public KruskalsMST(EdgeWeightedGraph G) {
			
			MinPQ<Edge> pq = new MinPQ<Edge>();
			for(Edge e: G.edges()) 
				pq.insert(e);
			
			UF uf = new UF(G.V());
			
			while(!pq.isEmpty() && mst.size() < G.V() - 1) {
				Edge e = pq.delMin();
				
				int v = e.either(), w = e.other(v);
				if(!uf.connected(v, w)) {
					uf.union(v, w);
					mst.enqueue(e);
				}
			}
		}
		
		public Iterable<Edge> edges() {
			return mst;
		}
	}
	
	
	private static class Prims {
		// start with vertex 0 and greedily grow tree T
		// add to T the min weight edge with exactly one endpoint in T
		// repeat until V-1 edges
		
		
	}
	
	
	public static void main(String[] args) {
		
	}
}
