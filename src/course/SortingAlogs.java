package course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SortingAlogs {

	public static class SelectionSort {
		public void sort(Comparable[] arr) {
			int n = arr.length;
			
			for(int i = 0; i < n; i++) {
				int min = i;
				
				for(int j = i + 1; j < n; j++)
					if(less(arr[j], arr[min]))
						min = j;
				
				exch(arr, i, min);
			}
		}
	}
	
	public static class InsertionSort {
		public void sort(Comparable[] arr) {
			int n = arr.length;
			for(int i = 0; i < n; i++) {
				for(int j = i; j > 0 && less(arr[j], arr[j-1]); j--)
					exch(arr, j, j - 1);
			}
		}
	}
	
	public static class ShellSort {
		public void sort(Comparable[] arr) {
			int n = arr.length;
			
			int h = 1;
			while(h < n / 3) h = 3 * h  + 1;
			
			while(h >= 1) {
				
				for(int i = h; i < n; i++) {
					
					for(int j = i; j >= h && less(arr[j], arr[j - h]); j -= h)
						exch(arr, j, j-h);
					
				}
				
				h = h / 3;
			}
		}
	}
	
	public static class MergeSort {
		private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
			
			// copy to aux array
			for(int i = lo; i <= hi; i++)
				aux[i] = a[i];
			
			int i = lo, j = mid + 1;
			for(int k = lo; k <= hi; k++) {
				if(i > mid) a[k] = aux[j++];
				else if(j > hi) a[k] = aux[i++];
				else if(less(aux[i], aux[j])) a[k] = aux[i++];
				else a[k] = aux[j++];
			}

		}
		
		private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
			if(hi <= lo) return;
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid);
			sort(a, aux, mid+1, hi);
			merge(a, aux, lo, mid, hi);
		}
		
		public void sort(Comparable[] a) {
			Comparable[] aux = new Comparable[a.length];
			sort(a, aux, 0, a.length - 1);
		}
		
	}
	
	public static class MergeSortBU {
		private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
			
			// copy to aux array
			for(int i = lo; i <= hi; i++)
				aux[i] = a[i];
			
			int i = lo, j = mid + 1;
			for(int k = lo; k <= hi; k++) {
				if(i > mid) a[k] = aux[j++];
				else if(j > hi) a[k] = aux[i++];
				else if(less(aux[i], aux[j])) a[k] = aux[i++];
				else a[k] = aux[j++];
			}

		}
		
		/*
		private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
			if(hi <= lo) return;
			int mid = lo + (hi - lo) / 2;
			sort(a, aux, lo, mid);
			sort(a, aux, mid+1, hi);
			merge(a, aux, lo, mid, hi);
		}
		*/
		
		public void sort(Comparable[] a) {
			Comparable[] aux = new Comparable[a.length];
			int n = a.length;
			
			for(int sz = 1; sz < n; sz = sz + sz) {
				for(int lo = 0; lo < n-sz; lo += sz+sz) {
					merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
				}
			}
		}
		
	}
	
	private static class QuickSort {
		//3,3,2,4,5,8
		private static int partition(Comparable[] a, int lo, int hi) {
			int i = lo, j = hi + 1;
			
			while(true) {
				
				//find item on left to swap
				while(less(a[++i], a[lo]))
					if(i == hi) break;
				
				//find item on right to swap
				while(less(a[lo], a[--j]))
					if(j == lo) break;
				
				//check if pointers crossed
				if(i >= j) break;
				
				//swap
				exch(a, i, j);
			}
			
			//swap with partitioning item
			exch(a, lo, j);
			
			return j;
		}
		
		public static void sort(Comparable[] a, int lo, int hi) {
			if(hi <= lo) return;
			int j = partition(a, lo, hi);
			sort(a, lo, j - 1);
			sort(a, j + 1, hi);
		}
		
		public static void sort(Comparable[] a) {
			StdRandom.shuffle(a);
			sort(a, 0, a.length - 1);
		}
		
		public static Comparable select(Comparable[] a, int k) {
			StdRandom.shuffle(a);
			
			int lo = 0, hi = a.length - 1;
			while(hi > lo) {
				int j = partition(a, lo, hi);
				
				if (j < k) lo = j + 1;
				else if (j > k) hi = j - 1;
				else return a[k];
			}
			
			return a[k];
		}
		
		public static void sort3way(Comparable[] a, int lo, int hi) {
			if(hi <= lo) return;
			int lt = lo, gt = hi;
			Comparable v = a[lo];
			
			int i = lo;
			
			while(i <= gt) {
				int cmp = a[i].compareTo(v);
				if(cmp < 0) exch(a, lt++, i++);
				if(cmp > 0) exch(a, i, gt++);
				else i++;
			}
			
			sort3way(a, lo, lt - 1);
			sort3way(a, gt + 1, hi);
		}
	}
	
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Comparable[] a = (Comparable[]) new Integer[] {3, 5, 8, 4, 1, 2};
		//QuickSort.partition(a, 0, 5);

		HashMap<String, String> map = new HashMap<String, String>(10);
		//HashSet<String> set = new HashSet<String>();
		//set.con
		map.put("Test", "Hello");
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(10);
		
	}

}
