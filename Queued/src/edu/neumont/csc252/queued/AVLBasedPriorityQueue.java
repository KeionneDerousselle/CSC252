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
        AVLNode<T> node = getHighestPriority();
        T data = (node == null)? null : node.getValue();

        if(node != null)
        {
            this.tree.removeNode(node);
        }

        return data;
    }

    @Override
    public T peek() {
        return getHighestPriority().getValue();
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
