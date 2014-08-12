package edu.neumont.csc252.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import edu.neumont.io.Bits;

public class HuffmanTree
{
	private Node<Byte> root;
	private byte[] data;
	
	private HashMap<Byte, Float> byteFrequencies = new HashMap<Byte, Float>();
	
	private PriorityQueue<Node<Byte>> frequencyQueue = new PriorityQueue<Node<Byte>>();
	
	public HuffmanTree(byte[] data)
	{
		this.data = data;
		collectFrequencies();
		prioritizeFrequencies();
		createTree();
	}
	
	public byte[] getData() {
		return data;
	}
	
	private void collectFrequencies()
	{
		if(data != null && data.length > 0)
		{
			for(int i = 0; i< data.length; i++)
			{
				if(byteFrequencies.containsKey(data[i]))
				{
					float currentCount = byteFrequencies.get(data[i]);
					byteFrequencies.put(data[i], currentCount + (1.0f/data.length));
				}
				else
				{
					byteFrequencies.put(data[i], (1.0f/data.length));
				}
			}
		}
	}
	
	private void prioritizeFrequencies()
	{
		if(byteFrequencies!= null && byteFrequencies.size() > 0)
		{
			for(Byte b : byteFrequencies.keySet())
			{
				ArrayList<Byte> value = new ArrayList<Byte>();
				value.add(b);
				
				frequencyQueue.add(new Node<Byte>(value,byteFrequencies.get(b)));
			}
		}
	}
	private void createTree()
	{
		if(frequencyQueue != null && !frequencyQueue.isEmpty())
		{
			while(!frequencyQueue.isEmpty())
			{
				if(frequencyQueue.size() == 1)
				{
					this.root = frequencyQueue.poll();
				}
				else
				{
					Node<Byte> leftChild = frequencyQueue.poll();
					Node<Byte> rightChild = frequencyQueue.poll();
					
					ArrayList<Byte> values = new ArrayList<Byte>();
					
					values.addAll(leftChild.getValue());
					values.addAll(rightChild.getValue());
					
					Node<Byte> parent = new Node<Byte>(values, rightChild.getFrequency() + leftChild.getFrequency(), leftChild, rightChild);
					
					frequencyQueue.add(parent);
				}
				
			}
		}
	}
	
	public byte toByte(Bits bits)
	{
		//throw an exception if there is only one root node in the tree
		return toByteHelper(bits, this.root);
	}
	
	private byte toByteHelper(Bits bits, Node<Byte> root)
	{		
		if(!bits.isEmpty())
		{
			boolean bit = bits.poll();
			
			root = (bit)? root.getRightChild() : root.getLeftChild();
			
			if(root.getLeftChild() == null && root.getRightChild() == null)
			{
				return root.getValue().get(0);
			}
			else
			{	
				return toByteHelper(bits, root);	
			}
		}
		else
			return 0;
		//throw an exception
		
	}
	
	public void fromByte(byte b, Bits bits)
	{
		fromByteHelper(b, this.root, bits);
	}
	
	private void fromByteHelper(byte b, Node<Byte> root, Bits bits)
	{
		if(root != null)
		{
			if(root.getValue().contains(b))
			{
				if(root.getRightChild()!=null || root.getLeftChild()!= null)
				{
					bits.add(root.getRightChild().getValue().contains(b));
					
					root = (root.getRightChild().getValue().contains(b)) ? root.getRightChild() : root.getLeftChild();
					
					fromByteHelper(b, root, bits);
				}
				
			}
		}
	}
	
	public void preorderTraverse()
	{
		if(root!= null)
		{
			preorderTraverseHelper(this.root);	
		}
	}
	
	private void preorderTraverseHelper(Node<Byte> root)
	{
		if(root != null)
		{
			System.out.println(root);
			preorderTraverseHelper(root.getLeftChild());
			preorderTraverseHelper(root.getRightChild());
		}
	}

}
