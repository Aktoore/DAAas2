package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long arrayAccess = 0;

    public void incrementComparisons() {comparisons++;}
    public void incrementArrayAccess() {arrayAccess++;}

    public long getComparisons() {return comparisons;}
    public long getArrayAccesses() {return arrayAccess;}

    public void reset() {
        comparisons = 0;
        arrayAccess = 0;
    }
}
