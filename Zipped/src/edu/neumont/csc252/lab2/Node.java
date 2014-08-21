package edu.neumont.csc252.lab2;

import java.util.ArrayList;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> 
{
	private ArrayList<T> value;
	
	private Node<T> leftChild;
	private Node<T> rightChild;
	
	private boolean isRoot;
	
	private float frequency;
	
	
	public Node(ArrayList<T> value, float frequency)
	{
		this.value = value;
		this.frequency = frequency;
	}
	
	public Node(ArrayList<T> value, Node<T> leftChild, Node<T> rightChild)
	{
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node(ArrayList<T> value, float frequency, Node<T> leftChild, Node<T> rightChild)
	{
		this.value = value;
		this.frequency = frequency;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public Node<T> getLeftChild() {
		return leftChild;
	}
	public Node<T> getRightChild() {
		return rightChild;
	}
	public ArrayList<T> getValue() {
		return value;
	}
	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}
	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}
	public void setValue(ArrayList<T> value) {
		this.value = value;
	}
	
	public boolean isRoot()
	{
		return isRoot;
	}
	
	public void makeRoot(boolean isRoot)
	{
		this.isRoot = isRoot;
	}
	
	
	@Override
	public String toString()
	{
		String toString = "";
		
		if(this!= null)
		{
			toString = "Parent: " + " Freq: " + frequency + " Value: " + value.toString();
			
			if(this.leftChild != null)
			{
				toString += "\r\nLeft Child : "  + " Freq: " + this.leftChild.getFrequency() + " Value: " + this.leftChild.getValue().toString();
			}
			
			if(this.rightChild != null)
			{
				toString += "\r\nRight Child : " + " Freq: " + this.rightChild.getFrequency() + " Value: " + this.rightChild.getValue().toString();
			}
		}
		toString += "\r\n\r\n";
		return toString;		}

	@Override
	public int compareTo(Node<T> o) {
		int compare = 0;
		
		if(this.frequency > o.frequency)
		{
			compare = 1;
		}
		else if(this.frequency < o.frequency)
		{
			compare = -1;
		}
		
		return compare;
	}
	
	public boolean isLeaf()
	{
		return getLeftChild() == null && getRightChild() == null;
	}
	
	public float getFrequency() {
		return frequency;
	}
	

}

