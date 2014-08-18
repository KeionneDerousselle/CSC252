package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/14/14.
 */
public class HeapBasedPriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T>
{
    private T[] heap;
    private int initialSize;
    private int currentIndex = 1;
    private int itemsInHeap = 0;

    public HeapBasedPriorityQueue(int initialHeapSize)
    {
        this.initialSize = initialHeapSize;
        this.heap = (T[]) new Comparable[initialHeapSize];
    }

    @Override
    public boolean offer(T data)
    {
        if(exceededHeapSize())
        {
            increaseHeapSize();
        }
        this.heap[currentIndex] = data;

        topDownBalance(currentIndex);

        currentIndex++;
        itemsInHeap++;

        return this.heap[currentIndex - 1].equals(data);
    }

    private void topDownBalance(int currentIndex)
    {
        T parent = getParent(currentIndex);
        if(parent != null)
        {
            if(this.heap[currentIndex].compareTo(parent) > 0)
            {
                int parentIndex = currentIndex / 2;

                this.heap[parentIndex] = this.heap[currentIndex];
                this.heap[currentIndex] = parent;

                topDownBalance(parentIndex);
            }
        }
    }

    private T getParent(int childIndex)
    {
        return this.heap[childIndex/2];
    }

    private void increaseHeapSize()
    {
        T[] newHeap = (T[]) new Comparable[this.heap.length * 2];
        System.arraycopy(this.heap, 0, newHeap, 0, this.heap.length);
        this.heap = newHeap;
    }

    private boolean exceededHeapSize()
    {
        return currentIndex == this.heap.length;
    }

    private void incrementItemsInHeap()
    {
        itemsInHeap++;
    }



    @Override
    public T poll() {
        T data = null;

        if(itemsInHeap != 0 )
        {
            T root = this.heap[1];
            this.heap[1] = this.heap[--itemsInHeap];
            data = root;

        }

        return data;
    }

    @Override
    public T peek() {
        return this.heap[1];
    }

    public T[] getHeap() {
        return heap;
    }
}
