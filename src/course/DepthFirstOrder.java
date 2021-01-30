package course;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private int[] post;
	private Queue<Integer> postorder;
	private int postCounter;
	
	public DepthFirstOrder(Digraph G) {
		post   = new int[G.V()];
		postorder = new Queue<Integer>();
		marked    = new boolean[G.V()];

		for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
	}
	
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        
        postorder.enqueue(v);
        post[v] = postCounter++;
    }
    
    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }
	
	public static void main(String[] args) {

	}

}
