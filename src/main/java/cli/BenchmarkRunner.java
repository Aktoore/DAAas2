package cli;

import algorithms.BoyerMooreMajorityVote;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    private static int[] generateArray(int n, String type) {
        Random rand = new Random();
        int[] arr = new int[n];

        switch (type) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reverse_sorted":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly_sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                for (int i = 0; i < n / 10; i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int tmp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = tmp;
                }
                break;
            case "worst_case":
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(2);
                break;
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(100);
        }

        return arr;
    }

    public static void main(String[] args) {
        String[] distributions = {"random", "sorted", "reverse_sorted", "nearly_sorted", "worst_case"};
        int[] sizes = {100, 1000, 10000};

        try (FileWriter writer = new FileWriter("data/boyer_moore_benchmarks.csv")) {
            writer.write("distribution,input_size,average_ns,min_ns,max_ns\n");

            for (String dist : distributions) {
                for (int n : sizes) {
                    long totalTime = 0;
                    long min = Long.MAX_VALUE;
                    long max = Long.MIN_VALUE;

                    for (int trial = 0; trial < 5; trial++) {
                        int[] arr = generateArray(n, dist);
                        PerformanceTracker tracker = new PerformanceTracker();
                        BoyerMooreMajorityVote bm = new BoyerMooreMajorityVote(tracker);

                        long start = System.nanoTime();
                        bm.findMajority(arr);
                        long end = System.nanoTime();

                        long time = end - start;
                        totalTime += time;
                        min = Math.min(min, time);
                        max = Math.max(max, time);
                    }

                    long avg = totalTime / 5;
                    writer.write(String.format("%s,%d,%d,%d,%d\n", dist, n, avg, min, max));
                    System.out.printf("Done: %s, n=%d → avg=%d ns%n", dist, n, avg);
                }
            }

            System.out.println("\n✅ Results saved to data/boyer_moore_benchmarks.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
