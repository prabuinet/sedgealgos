package course;

import edu.princeton.cs.algs4.StdOut;

public class HeapSort {
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		
		for(int k = N/2; k >= 1; k++)
			sink(a, k, N);
		
		while(N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}
	
	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
	}
	
	public static void sink(Comparable[] a, int k, int N) {
		while(2*k <= N) {
			int j = 2*k;
			if (j < N && less(a, j, j+1)) j++;
			if (!less(a, k, j)) break;
			exch(a, k, j);
			k = j;
		}
	}
	
	public static boolean less(Comparable[] a, int i, int j) {
		return a[i].compareTo(a[j]) < 0;
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hell");
		sb.append("o");
		
		String s1 = "Hello";
		String s2 = sb.toString();
		
		boolean v = s1 == s2;
		StdOut.println(v);
	}

}
