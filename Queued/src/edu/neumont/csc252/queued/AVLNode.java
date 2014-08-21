package edu.neumont.csc252.queued;

import javax.xml.soap.Node;

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

        balance();

        return added;
    }

    public boolean removeNode(AVLNode<T> oldChild)
    {
        boolean removed = true;

        if(oldChild.getValue().compareTo(this.value) == 0)
        {
            remove(oldChild, NodeHierarchy.ROOT);
        }
        else if(oldChild.getValue().compareTo(this.getValue()) < 0)
        {

            if(oldChild.equals(this.getLeftChild()))
            {
                remove(oldChild, NodeHierarchy.LEFT_CHILD);
            }
            else
            {
                this.getLeftChild().removeNode(oldChild);
            }
        }
        else if (oldChild.getValue().compareTo(this.getValue()) > 0)
        {
            if(oldChild.equals(this.getRightChild()))
            {
                remove(oldChild, NodeHierarchy.RIGHT_CHILD);
            }
            else
            {
                this.getRightChild().removeNode(oldChild);
            }
        }

         balance();

        return removed;
    }

    private boolean remove(AVLNode<T> node, NodeHierarchy hierarchy)
    {

        if(isLeaf(node))
        {
            if(hierarchy.equals(NodeHierarchy.LEFT_CHILD))
            {
                this.setLeftChild(null);
            }
            else if(hierarchy.equals(NodeHierarchy.RIGHT_CHILD))
            {
                this.setRightChild(null);
            }
            else if(hierarchy.equals(NodeHierarchy.ROOT))
            {
                this.setValue(null);
            }

        }
        else if(node.getLeftChild() == null && node.getRightChild() != null)
        {
            if(hierarchy.equals(NodeHierarchy.LEFT_CHILD))
            {
                this.setLeftChild(node.getRightChild());
            }
            else if(hierarchy.equals(NodeHierarchy.RIGHT_CHILD))
            {
                this.setRightChild(node.getRightChild());
            }
            else if(hierarchy.equals(NodeHierarchy.ROOT))
            {
                this.setLeftChild(this.getRightChild().getLeftChild());
                this.setValue(this.getRightChild().getValue());
                this.setRightChild(this.getRightChild().getRightChild());
            }
        }
        else if(node.getRightChild() == null && node.getLeftChild()!= null)
        {
            if(hierarchy.equals(NodeHierarchy.LEFT_CHILD))
            {
                this.setLeftChild(node.getLeftChild());
            }
            else if(hierarchy.equals(NodeHierarchy.RIGHT_CHILD))
            {
                this.setRightChild(node.getLeftChild());
            }
            else if(hierarchy.equals(NodeHierarchy.ROOT))
            {
                this.setLeftChild(this.getLeftChild().getLeftChild());
                this.setValue(this.getLeftChild().getValue());
                this.setRightChild(this.getLeftChild().getRightChild());
            }
        }
        else
        {
            AVLNode<T> successorParent = getInOrderSuccesorParent(node);
            AVLNode<T> successor = getInOrderSuccesor(node.getRightChild());
            node.setValue(successor.getValue());
            successorParent.setLeftChild(successor.getRightChild());

            if(hierarchy.equals(NodeHierarchy.ROOT))
            {
                this.setValue(node.getValue());
                this.setRightChild(node.getRightChild());
                this.setLeftChild(node.getLeftChild());
            }
        }

        return true;

    }
    private AVLNode<T> getInOrderSuccesor(AVLNode<T> parent)
    {
        if(parent.getLeftChild() == null)
        {
            return parent;
        }
        else
        {
            return getInOrderSuccesor(parent.getLeftChild());
        }
    }

    private AVLNode<T> getInOrderSuccesorParent(AVLNode<T> parent)
    {
        if(parent.getRightChild().getLeftChild() == null)
        {
            return parent;
        }
        else
        {
            parent = parent.getRightChild();

            while(parent.getLeftChild().getLeftChild() != null)
            {
                parent = parent.getLeftChild();
            }
        }

        return parent;
    }
    private boolean isLeaf(AVLNode<T> node)
    {
        return (node.getRightChild() == null && node.getLeftChild() == null);
    }

    private void balance()
    {
        int balanceFactor = getBalanceFactor();

        if(balanceFactor < -1 || balanceFactor > 1)
        {
            boolean rightSideHeavy = balanceFactor < 0;
            boolean isAStick;

            if(rightSideHeavy) // if the right side is heavy a left rotation is necessary
            {
                isAStick = getRightChild().getBalanceFactor() < 0;

                if(!isAStick)
                {
                    getRightChild().rotateRight();
                }

                rotateLeft();
            }
            else // if the left side is heavy a right rotation is necessary
            {

                isAStick = getLeftChild().getBalanceFactor() > 0;

                if(!isAStick)
                {
                    getLeftChild().rotateLeft();
                }
                rotateRight();
            }
        }
    }

    private void rotateRight()
    {
        AVLNode<T> newRoot  = getLeftChild();
        setLeftChild(newRoot.getRightChild());

        AVLNode<T> currentRoot = new AVLNode<T>(getValue());
        currentRoot.setLeftChild(getLeftChild());
        currentRoot.setRightChild(getRightChild());

        newRoot.setRightChild(currentRoot);

        setValue(newRoot.getValue());
        setLeftChild(newRoot.getLeftChild());
        setRightChild(newRoot.getRightChild());


    }
    private void rotateLeft()
    {

        AVLNode<T> newRoot = getRightChild();
        setRightChild(newRoot.getLeftChild());

        AVLNode<T> currentRoot = new AVLNode<T>(getValue(), getLeftChild(), getRightChild());

        newRoot.setLeftChild(currentRoot);

        setValue(newRoot.getValue());
        setLeftChild(newRoot.getLeftChild());
        setRightChild(newRoot.getRightChild());

    }

    private int getBalanceFactor()
    {
        return findHeight(getLeftChild()) - findHeight(getRightChild());
    }

    private int findHeight(AVLNode<T> root)
    {
        if(root == null)
        {
            return -1;
        }

        return 1 + Math.max(findHeight(root.getLeftChild()), findHeight(root.getRightChild()));
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
