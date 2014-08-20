package edu.neumont.csc252.queued;

import java.util.ArrayList;

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

    public boolean removeNode(AVLNode<T> oldNode)
    {
        boolean removed = true;

        if(this.root != null && oldNode != null)
        {
            this.root.removeNode(oldNode);
        }
        else
        {
            removed = false;
        }
        return removed;
    }
    public AVLNode<T> getRoot() {
        return root;
    }

    public ArrayList<T> preorderTraverse()
    {
        ArrayList<T> preorderList = new ArrayList<T>();
        if(root!= null)
        {
            preorderTraverseHelper(this.root, preorderList);
        }

        return preorderList;
    }

    private void preorderTraverseHelper(AVLNode<T> root, ArrayList<T> preorderList)
    {
        if(root != null)
        {
            preorderList.add(root.getValue());
            preorderTraverseHelper(root.getLeftChild(), preorderList);
            preorderTraverseHelper(root.getRightChild(), preorderList);
        }
    }

    @Override
    public String toString()
    {
        return preorderTraverse().toString();
    }
}
