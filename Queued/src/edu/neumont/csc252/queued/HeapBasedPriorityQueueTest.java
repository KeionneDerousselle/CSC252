package edu.neumont.csc252.queued;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by kderousselle on 8/14/14.
 */
public class HeapBasedPriorityQueueTest {
    @Test
    public void testOffer() throws Exception {
        HeapBasedPriorityQueue<Integer> priorityQueue = new HeapBasedPriorityQueue<Integer>(7);
        priorityQueue.offer(1);
        priorityQueue.offer(20);
        priorityQueue.offer(35);
        priorityQueue.offer(48);
        priorityQueue.offer(8);
        priorityQueue.offer(7);
        priorityQueue.offer(9);
        priorityQueue.offer(59);

        System.out.println(Arrays.toString(priorityQueue.getHeap()));

    }

    @Test
    public void testPoll() throws Exception {

    }

    @Test
    public void testPeek() throws Exception {

    }
}
