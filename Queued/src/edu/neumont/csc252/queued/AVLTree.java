package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/16/14.
 */
public class AVLTree<T extends Comparable<T>>
{
    private AVLNode<T> root;

    public AVLTree(AVLNode<T> root)
    {
        this.root = root;
    }

    public AVLTree(){}

    public AVLTree(T value)
    {
        this.root = new AVLNode<T>(value);
    }

    public boolean addNode(T value)
    {
        return addNode(new AVLNode<T>(value));
    }

    public boolean addNode(AVLNode<T> newNode)
    {
        boolean added = true;

        if(this.root == null)
        {
            this.root = newNode;
        }
        else
        {
            this.root.addNode(newNode);
        }
        return  added;
    }

    public AVLNode<T> getRoot() {
        return root;
    }
}
