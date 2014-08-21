package edu.neumont.csc252.queued;

import org.junit.Test;

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

        System.out.println(priorityQueue);

    }

    @Test
    public void testPoll() throws Exception
    {
        HeapBasedPriorityQueue<Integer> priorityQueue = new HeapBasedPriorityQueue<Integer>(7);
        priorityQueue.offer(1);
        priorityQueue.offer(20);
        priorityQueue.offer(35);
        priorityQueue.offer(48);
        priorityQueue.offer(8);
        priorityQueue.offer(7);
        priorityQueue.offer(9);
        priorityQueue.offer(59);

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue + "\r\n");

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue + "\r\n");
    }

    @Test
    public void testPeek() throws Exception
    {
        HeapBasedPriorityQueue<Integer> priorityQueue = new HeapBasedPriorityQueue<Integer>(7);
       // priorityQueue.offer(1);
        priorityQueue.offer(20);
        priorityQueue.offer(35);
        priorityQueue.offer(48);
        priorityQueue.offer(8);
        priorityQueue.offer(7);
        priorityQueue.offer(9);
        priorityQueue.offer(59);

        System.out.println(priorityQueue.peek());
    }
}
