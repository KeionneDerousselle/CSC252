package edu.neumont.csc252.lab2;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import edu.neumont.io.Bits;

public class HuffmanTreeTest {
	
	public static final int UNCOMPRESSED_IMAGE_SIZE = 54679;

	
	@Test 
	public void compressTest()
	{
		byte [] data = "Four score and seven years ago, our forefathers stood upon this native soil and did some cool stuff.".getBytes();
		
		HuffmanTree tree = new HuffmanTree(data);
		
		HuffmanCompressor compressor = new HuffmanCompressor();
	
		byte[] compressedData = compressor.compress(tree, data);
		
		byte[] decompressedData  = compressor.decompress(tree, data.length, compressedData);
		System.out.println(Arrays.toString(decompressedData));
		System.out.println(new String(decompressedData));
		
		assertTrue(decompressedData.length == data.length);

		assertTrue(compressedData.length < data.length);
	}
//	
//	@Test
//	public void getSecretImage()
//	{
//		int[] frequencyChart = {423, 116, 145, 136, 130, 165, 179, 197, 148, 125, 954, 156, 143, 145, 164, 241, 107, 149, 176, 153, 121, 164, 144, 166, 100, 138, 157, 140, 119, 138, 178, 289, 360, 120, 961, 195, 139, 147, 129, 192, 119, 146, 138, 184, 137, 196, 163, 331, 115, 160, 127, 172, 176, 181, 149, 194, 138, 154, 163, 167, 196, 174, 250, 354, 142, 169, 170, 209, 205, 179, 147, 245, 108, 179, 148, 186, 131, 160, 112, 219, 118, 204, 164, 154, 154, 175, 189, 239, 126, 145, 185, 179, 149, 167, 152, 244, 189, 257, 234, 208, 179, 170, 171, 178, 184, 189, 203, 184, 204, 208, 187, 163, 335, 326, 206, 189, 210, 204, 230, 202, 415, 240, 275, 295, 375, 308, 401, 608, 2099, 495, 374, 160, 130, 331, 107, 181, 117, 133, 476, 129, 137, 106, 107, 237, 184, 143, 122, 143, 1596, 205, 121, 170, 123, 124, 150, 132, 143, 133, 178, 308, 96, 102, 114, 176, 159, 149, 123, 199, 1156, 119, 144, 237, 131, 155, 143, 225, 92, 125, 117, 138, 135, 154, 124, 137, 121, 143, 149, 141, 177, 159, 247, 384, 302, 120, 95, 140, 87, 1460, 155, 199, 111, 198, 147, 182, 91, 148, 119, 233, 445, 1288, 138, 133, 122, 170, 156, 257, 143, 149, 180, 174, 132, 151, 193, 347, 91, 119, 135, 182, 124, 152, 109, 175, 152, 159, 166, 224, 126, 169, 145, 220, 119, 148, 133, 158, 144, 185, 139, 168, 244, 145, 167, 167, 262, 214, 293, 402};
//		
//		HuffmanTree tree = new HuffmanTree(frequencyChart);
//		HuffmanCompressor compressor = new HuffmanCompressor();
//		
//		Path path = Paths.get("src/CompressedData/compressed.huff");
//		
//		byte[] decompressedData = null;
//		
//		try {
//			
//			byte[] compressedData = Files.readAllBytes(path);
//			decompressedData = compressor.decompress(tree, UNCOMPRESSED_IMAGE_SIZE, compressedData);
//
//			System.out.println(Arrays.toString(decompressedData));
//			ByteArrayInputStream bais = new ByteArrayInputStream(decompressedData);
//			showImage(bais);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	@Test
	public void getImage()
	{
		Path path = Paths.get("src/Images/cat4.jpg");
		
		byte[] decompressedData = null;
		
		try {
			
			byte[] data = Files.readAllBytes(path);
			
			HuffmanTree tree = new HuffmanTree(data);
			HuffmanCompressor compressor = new HuffmanCompressor();
			
			byte[] compressedData = compressor.compress(tree, data);
			
			decompressedData = compressor.decompress(tree, data.length, compressedData);

			assertTrue(decompressedData.length == data.length);
			assertTrue(compressedData.length < data.length);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(decompressedData);
			showImage(bais);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void showImage(InputStream input) throws IOException
	{
		BufferedImage image  = ImageIO.read(input);
		JFrame f = new JFrame();
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(image));
		f.add(l);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
