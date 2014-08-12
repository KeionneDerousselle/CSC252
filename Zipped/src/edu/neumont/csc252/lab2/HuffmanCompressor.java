package edu.neumont.csc252.lab2;

import edu.neumont.io.Bits;

public class HuffmanCompressor 
{
	private static final int BITS_IN_A_BYTE = 8;
	public byte[] compress(HuffmanTree tree, byte[] data)
	{
		byte[] compressedData = null;

		if(tree != null)
		{

			Bits messageBits = new Bits();

			for(int i = 0; i < data.length; i++)
			{
				tree.fromByte(data[i], messageBits);
			}

			int compressedSize = (messageBits.size() % BITS_IN_A_BYTE != 0) ?( messageBits.size() / BITS_IN_A_BYTE) + 1 :  (messageBits.size() / BITS_IN_A_BYTE);
			compressedData = new byte[compressedSize];


			int bitIndex = 0, byteIndex = 0;
			while(!messageBits.isEmpty())
			{
				if(bitIndex == BITS_IN_A_BYTE)
				{
					bitIndex = 0;
					byteIndex++;
				}

				boolean bit = messageBits.poll();


				if(bit)
				{
					compressedData[byteIndex] = (byte) (compressedData[byteIndex] |(1 << bitIndex));//set bit at bitIndex to 1
				}
				else
				{
					compressedData[byteIndex] = (byte) (compressedData[byteIndex] & ~(1 << bitIndex)); // set bit at bitIndex to 0
				}

				bitIndex++;
			}

		}
		return compressedData;
	}
	
	public byte[] decompress(HuffmanTree tree, int uncompressedLength, byte[] data)
	{
		byte[] decompressedData = new byte[uncompressedLength];
		
		Bits messageBits = new Bits();
	
		for(int i = 0; i< data.length; i++)
		{
			for(int bitIndex = 0; bitIndex < BITS_IN_A_BYTE ; bitIndex++)
			{
				messageBits.add((data[i] & (1 << bitIndex)) != 0);
			}
		}
		
		for(int i = 0; i < decompressedData.length; i++)
		{
			decompressedData[i] = tree.toByte(messageBits);
		}		
		
		return decompressedData;
		
	}

}
