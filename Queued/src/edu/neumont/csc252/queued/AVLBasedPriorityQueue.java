package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/14/14.
 */

public class AVLBasedPriorityQueue< T extends Comparable<T>> implements IPriorityQueue<T>
{
    private AVLTree<T> tree = new AVLTree<T>();
    public int size;

    @Override
    public boolean offer(T data)
    {
        if(this.tree.addNode(data))
        {
            size++;
            return  true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public T poll() {
        AVLNode<T> node = getHighestPriority();
        T data = (node == null)? null : node.getValue();

        if(node != null)
        {
            if(this.tree.removeNode(node))
            {
                size--;
            }
        }

        return data;
    }

    @Override
    public T peek() {
        return getHighestPriority().getValue();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0 || this.tree.getRoot() == null;
    }

    private AVLNode<T> getHighestPriority()
    {
        AVLNode<T> highestPriority = null;

        if(this.tree.getRoot() != null)
        {
            highestPriority = getHighestPriority(this.tree.getRoot());
        }

        return highestPriority;
    }

    private AVLNode<T> getHighestPriority(AVLNode<T> root)
    {
        if(root.getLeftChild() == null)
        {
            return root;
        }
        else
        {
            return getHighestPriority(root.getLeftChild());
        }
    }

    @Override
    public String toString()
    {
        return this.tree.toString();
    }
}
