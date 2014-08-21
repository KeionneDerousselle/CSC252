package edu.neumont.csc252.lab2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import edu.neumont.csc252.queued.HeapBasedPriorityQueue;
import edu.neumont.csc252.queued.IPriorityQueue;
import edu.neumont.io.Bits;

public class HuffmanTree
{
	private Node<Byte> root;
	private byte[] data;
	private final static int BYTE_MIN = -128, BYTE_MAX = 127;
	
	//private HashMap<Byte, Float> byteFrequencies = new HashMap<Byte, Float>();
	
	private float[] byteFrequencies = new float[(BYTE_MAX + 1)* 2];
	
	private IPriorityQueue<Node<Byte>> frequencyQueue = new HeapBasedPriorityQueue<Node<Byte>>(7);
	//private IPriorityQueue<Node<Byte>> frequencyQueue = new AVLBasedPriorityQueue<Node<Byte>>();
	//private PriorityQueue<Node<Byte>> frequencyQueue = new PriorityQueue<Node<Byte>>();
	public HuffmanTree(byte[] data)
	{
		this.data = data;
		collectFrequencies();
		prioritizeFrequencies();
		createTree();
	}
	
	public HuffmanTree(int[] frequencyChart)
	{
		recordFrequencies(frequencyChart);
		prioritizeFrequencies();
		createTree();
	}
	
	
	public byte[] getData() {
		return data;
	}
	
	private void recordFrequencies(int[] frequencyChart)
	{
		int total = 0;
		
		for(int i = 0; i < frequencyChart.length; i++)
		{
			total += frequencyChart[i];
		}
		for( int i = BYTE_MIN; i <= BYTE_MAX ; i++)
		{
			byteFrequencies[i + BYTE_MAX + 1] = (frequencyChart[i+BYTE_MAX + 1]/(float)total);
			
		}
		
	}
	
	private void collectFrequencies()
	{
		if(data != null && data.length > 0)
		{
			for(int i = 0; i< data.length; i++)
			{
				byteFrequencies[data[i] + BYTE_MAX  + 1] += (1.0f/data.length);
			}
		}
	}
	
	private void prioritizeFrequencies()
	{
		if(byteFrequencies!= null && byteFrequencies.length > 0)
		{
			for(int i = 0; i< byteFrequencies.length; i++)
			{
				float frequency = byteFrequencies[i];
				
				if(frequency != 0.0f)
				{
					ArrayList<Byte> value = new ArrayList<Byte>();
					value.add((byte)(i + BYTE_MIN));
					frequencyQueue.offer(new Node<Byte>(value, frequency));
				}
				
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

				
//					if(leftChild.compareTo(rightChild) == 0)
//					{
//						if(leftChild.isLeaf() && rightChild.isLeaf())
//						{
//							if(leftChild.getValue().get(0).compareTo(rightChild.getValue().get(0)) > 0)
//							{
//								Node<Byte> temp = rightChild;
//								rightChild = leftChild;
//								leftChild = temp;
//								
//							}
//								
//						}
//					}
					
					ArrayList<Byte> values = new ArrayList<Byte>();
					
					values.addAll(leftChild.getValue());
					values.addAll(rightChild.getValue());
					
					Node<Byte> parent = new Node<Byte>(values, rightChild.getFrequency() + leftChild.getFrequency(), leftChild, rightChild);
					
					frequencyQueue.offer(parent);
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

