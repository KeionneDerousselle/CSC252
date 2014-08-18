package edu.neumont.csc252.queued;

/**
 * Created by kderousselle on 8/16/14.
 */
public class AVLNode< T extends Comparable<T>>
{
    private T value;

    AVLNode<T> leftChild;
    AVLNode<T> rightChild;

    public AVLNode(T value)
    {
        this.value = value;
    }

    public AVLNode(T value, AVLNode<T> leftChild, AVLNode<T> rightChild)
    {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public boolean addNode(AVLNode<T> potentialChild)
    {
        boolean added = true;

        if(potentialChild.getValue().compareTo(this.value) == -1)
        {
            if(this.leftChild == null)
            {
                this.leftChild = potentialChild;
            }
            else
            {
                leftChild.addNode(potentialChild);
            }
        }
        else if(potentialChild.getValue().compareTo(this.value) == 1)
        {
            if(rightChild == null)
            {
                rightChild = potentialChild;
            }
            else
            {
                getRightChild().addNode(potentialChild);
            }
        }

        return added;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public AVLNode<T> getLeftChild() {
        return leftChild;
    }

    public AVLNode<T> getRightChild() {
        return rightChild;
    }

    public T getValue() {
        return value;
    }
}
