package cli;

import algorithms.BoyerMooreMajorityVote;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int n = 20;
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(5);
        }
        PerformanceTracker tracker = new PerformanceTracker();
        BoyerMooreMajorityVote bm = new BoyerMooreMajorityVote(tracker);

        Integer majority = bm.findMajority(arr);

        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nMajority element: " + majority);
        System.out.println("Comparisons: " + tracker.getComparisons());
        System.out.println("Array accesses: " + tracker.getArrayAccesses());
    }
}
