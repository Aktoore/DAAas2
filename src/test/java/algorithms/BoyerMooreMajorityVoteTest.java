package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreMajorityVoteTest {
    @Test
    void testEmptyArray() {
        PerformanceTracker tracker = new PerformanceTracker();
        BoyerMooreMajorityVote bm = new BoyerMooreMajorityVote(tracker);
        assertNull(bm.findMajority(new int[]{}));
    }
    @Test
    void testSingleElement() {
        PerformanceTracker tracker = new PerformanceTracker();
        BoyerMooreMajorityVote bm = new BoyerMooreMajorityVote(tracker);
        assertEquals(5, bm.findMajority(new int[]{5}));
    }
    @Test
    void testMajorityExists() {
        PerformanceTracker tracker = new PerformanceTracker();
        BoyerMooreMajorityVote bm = new BoyerMooreMajorityVote(tracker);
        assertNull(bm.findMajority(new int[]{1,2,3,4,5}));
    }
}
