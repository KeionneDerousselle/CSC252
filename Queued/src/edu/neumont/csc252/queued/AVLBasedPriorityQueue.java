package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/14/14.
 */

public class AVLBasedPriorityQueue< T extends Comparable<T>> implements IPriorityQueue<T>
{
    private AVLTree<T> tree = new AVLTree<T>();

    @Override
    public boolean offer(T data)
    {
        return this.tree.addNode(data);
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return getHighestPriority();
    }

    private T getHighestPriority()
    {
        T highestPriority = null;

        if(this.tree.getRoot() != null)
        {
            highestPriority = getHighestPriority(this.tree.getRoot());
        }

        return highestPriority;
    }

    private T getHighestPriority(AVLNode<T> root)
    {
        if(root.getLeftChild() == null)
        {
            return root.getValue();
        }
        else
        {
            return getHighestPriority(root.getLeftChild());
        }
    }
}
