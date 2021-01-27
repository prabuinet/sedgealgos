package course;

import edu.princeton.cs.algs4.*;

public class ThreeSum {

	public static int count(int[] a) {
		int n = a.length;
		
		int count = 0;
		
		for(int i = 0; i < n; i++)
			for(int j = i + 1; j < n; j++)
				for(int k = j + 1; k < n; k++) 
					if(a[i] + a[j] + a[k] == 0)
						count++;
		
		return count;
	}
	
	public static void main(String[] args) {

		int[] a = StdIn.readAllInts();
		Stopwatch sw = new Stopwatch();
		
		count(a);
		
		double time = sw.elapsedTime();
		StdOut.println(time);

	}

}
