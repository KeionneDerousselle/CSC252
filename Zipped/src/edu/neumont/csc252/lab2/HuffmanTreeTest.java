package edu.neumont.csc252.lab2;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import edu.neumont.io.Bits;

public class HuffmanTreeTest {

	@Test
	public void testTreeBulding() {
		HuffmanTree tree = new HuffmanTree(new byte[]{1,1,1,2,2,3,3,4,5,6});
		tree.preorderTraverse();
	}
	
	@Test
	public void getByteTest()
	{
		HuffmanTree tree = new HuffmanTree(new byte[]{1,1,1,2,2,3,3,4,5,6});
		Bits bits = new Bits();
		
		bits.add(true);
		bits.add(false);
		bits.add(true);
		bits.add(true);
		bits.add(false);
		bits.add(false);
		
		System.out.println(tree.toByte(bits));
	}
	
	@Test
	public void fromByteTest()
	{
		HuffmanTree tree = new HuffmanTree(new byte[]{1,1,1,2,2,3,3,4,5,6});
		Bits bits = new Bits();
		byte node = (byte)6;
		tree.fromByte(node, bits);
		
		System.out.println("Byte: " + node + ", Bit Value : " + bits +"\r\n");		
	}
	
	@Test 
	public void compressTest()
	{
		byte [] data = new byte[]{1,1,1,2,2,3,3,4,5,6};
		HuffmanTree tree = new HuffmanTree(data);
		
		HuffmanCompressor compressor = new HuffmanCompressor();
		byte[] compressedData = compressor.compress(tree, data);
		
		System.out.println(Arrays.toString(compressedData));
		
		byte[] decompressedData  = compressor.decompress(tree, data.length, compressedData);
		
		System.out.println(Arrays.toString(decompressedData));
		
		assertTrue(decompressedData.length == data.length);
		assertTrue(compressedData.length < data.length);
		
		
	}

}
