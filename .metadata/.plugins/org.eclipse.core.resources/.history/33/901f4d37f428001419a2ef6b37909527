package edu.neumont.csc252.queued;

import org.junit.Test;

/**
 * Created by kderousselle on 8/18/14.
 */
public class AVLBasedPriorityQueueTest {
    @Test
    public void testOffer() throws Exception
    {
        AVLBasedPriorityQueue<Integer> priorityQueue = new AVLBasedPriorityQueue<Integer>();
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
        AVLBasedPriorityQueue<Integer> priorityQueue = new AVLBasedPriorityQueue<Integer>();

        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(4);
        priorityQueue.offer(5);

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue + "\r\n");

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue + "\r\n");

        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue + "\r\n");

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
        AVLBasedPriorityQueue<Integer> priorityQueue = new AVLBasedPriorityQueue<Integer>();
        priorityQueue.offer(1);
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
