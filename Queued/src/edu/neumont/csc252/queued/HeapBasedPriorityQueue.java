package edu.neumont.csc252.queued;

import java.util.Arrays;

/**
 * Created by kderousselle on 8/14/14.
 */
public class HeapBasedPriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T>
{
    private T[] heap;
    private int initialSize;
    private int currentIndex = 0;
    private int itemsInHeap = 0;

    public HeapBasedPriorityQueue(int initialHeapSize)
    {
        this.initialSize = initialHeapSize;
        this.heap = (T[]) new Comparable[initialHeapSize];
    }

    @Override
    public boolean offer(T data)
    {
        currentIndex++;

        if(exceededHeapSize())
        {
            increaseHeapSize();
        }

        this.heap[currentIndex] = data;
        topDownBalance(currentIndex);
        itemsInHeap++;

        return this.heap[currentIndex].equals(data);
    }

    private void topDownBalance(int currentIndex)
    {
        T parent = getParent(currentIndex);
        if(parent != null)
        {
            if(this.heap[currentIndex].compareTo(parent) < 0)
            {
                int parentIndex = currentIndex / 2;

                this.heap[parentIndex] = this.heap[currentIndex];
                this.heap[currentIndex] = parent;

                topDownBalance(parentIndex);
            }
        }
    }

    private void bottomUpBalance()
    {
        int lastParentIndex = getParentIndex(currentIndex);
        while(lastParentIndex > 0 && this.heap[lastParentIndex] != null)
        {
            bottomUpBalance(lastParentIndex);
            lastParentIndex--;
        }
    }

    private void bottomUpBalance(int parentIndex)
    {
        int leftIndex = parentIndex * 2;
        int rightIndex = (parentIndex * 2) + 1;

        T parent =((parentIndex < this.heap.length && parentIndex > 0) && this.heap[parentIndex]!= null)? this.heap[parentIndex] : null;

        if(parent != null)
        {
            T left = ((leftIndex < this.heap.length && leftIndex > 0) && this.heap[leftIndex] != null)? this.heap[leftIndex] : null;
            T right = ((rightIndex < this.heap.length && rightIndex > 0) && this.heap[rightIndex] != null)? this.heap[rightIndex] : null;

            if((left != null && right!= null) && (right.compareTo(parent) < 0) && (left.compareTo(parent) < 0))
            {
                if(left.compareTo(right) <= 0)
                {
                    swapKey(leftIndex, parentIndex);
                    bottomUpBalance(leftIndex);
                }
                else if(right.compareTo(left) < 0)
                {
                    swapKey(rightIndex, parentIndex);
                    bottomUpBalance(rightIndex);
                }
            }
            else if((left != null) && (left.compareTo(parent) < 0))
            {
                swapKey(leftIndex, parentIndex);
                bottomUpBalance(leftIndex);
            }


        }
    }

    private void swapKey(int key1, int key2)
    {
        T value1 = this.heap[key1];
        this.heap[key1] = this.heap[key2];
        this.heap[key2] = value1;
    }

    private int getParentIndex(int childIndex)
    {
        return childIndex/2;
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

            data = this.heap[1];

            swapKey(1, currentIndex);

            this.heap[currentIndex] = null;

            itemsInHeap--;
            currentIndex--;

            bottomUpBalance();
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

    @Override
    public String toString()
    {
        return Arrays.toString(getHeap());
    }
}
