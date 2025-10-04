package algorithms;

import metrics.PerformanceTracker;

public class BoyerMooreMajorityVote {
    private PerformanceTracker tracker;

    public BoyerMooreMajorityVote(PerformanceTracker tracker) {
        this.tracker = tracker;
    }
    public Integer findMajority(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        int candidate = arr[0];
        int count = 1;
        tracker.incrementArrayAccess();

        for (int i = 0; i < arr.length; i++) {
            tracker.incrementArrayAccess();
            tracker.incrementComparisons();
            if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            }
        }
        count = 0;
        for (int num : arr) {
            tracker.incrementArrayAccess();
            tracker.incrementComparisons();
            if (num == candidate) count++;
        }

        return count > arr.length / 2 ? candidate : null;
    }
}
