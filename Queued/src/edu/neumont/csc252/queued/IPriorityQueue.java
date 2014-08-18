package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/14/14.
 */
public interface IPriorityQueue<T extends Comparable<T>>
{
    public boolean offer(T data);

    public T poll();

    public T peek();
}
