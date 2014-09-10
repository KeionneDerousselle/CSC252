

import java.util.Iterator;

public class Hashtable<K, V> {
	private Entry<K,V>[] values;
	private int size;
	
	public Hashtable(int initialCapacity) {
		values = (Entry<K,V>[])new Entry[initialCapacity];
	}
	
	/**
	 * #3b. Implement this (1 point)
	 * 
	 * @param key
	 * @param value
	 */
	public void put(K key, V value)
	{
		
		if(needsToResize())
		{
			resizeTable();
		}
		
		int keyHashCode = key.hashCode();
		int keyIndex = (keyHashCode >= values.length) ? keyHashCode % values.length : keyHashCode;
		
		if(values[keyIndex] !=null)
		{
			boolean entryFound = false;
			Entry<K,V> current = values[keyIndex];
			
			while(current!= null && !entryFound)
			{
				if(current.key.equals(key))
				{
					current.data = value;
					entryFound = true;
				}
				
				current = current.next;
					
				if(current == null)
				{
					current = new Entry<K,V>(key, value);
				}
				
			}
		}
		else
		{
			values[keyIndex] = new Entry<K,V>(key, value);
		}

		size++;
	}
	
	private boolean needsToResize()
	{
		return this.size + 1 >= this.values.length;
	}
	
	private void resizeTable()
	{
		Entry<K,V>[] newTable = (Entry<K, V>[])new Entry[values.length *2];
		
		System.arraycopy(this.values, 0, newTable, 0, this.values.length);
		
		this.values = newTable;
	}
	
	/**
	 * #3b. Implement this (1 point)
	 * @param key
	 * @return
	 */
	public V get(K key) {
		
		int keyHashCode = key.hashCode();
		int keyIndex = (keyHashCode >= values.length) ? keyHashCode % values.length : keyHashCode;
		
		V value = null;
		
		boolean entryFound = false;
		Entry<K,V> current = values[keyIndex];
		
		while(current!= null && !entryFound)
		{
			if(current.key.equals(key))
			{
				value = current.data;
				entryFound = true;
			}
			
			current = current.next;			
		}
		
		return value;
	}

	/**
	 * #3c.  Implement this. (1 point)
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key)
	{
		int keyHashCode = key.hashCode();
		int keyIndex = (keyHashCode >= values.length) ? keyHashCode % values.length : keyHashCode;
		
		V value = null;
		
		boolean entryFound = false;
		Entry<K,V> previous = null;
		Entry<K,V> current = values[keyIndex];
		
		while(current!= null && !entryFound)
		{
			if(current.key.equals(key))
			{
				value = current.data;
				
				if(previous == null)
				{
					values[keyIndex] = current.next;
				}
				else
				{
					previous.next = current.next;
				}		
				
				entryFound = true;
			}
			
			previous = current;
			current = current.next;			
		}
		 size = (entryFound) ? size -1 : size;
		return value;
	}
	
	public int size() {
		return size;
	}
	
	public boolean containsKey(K key) {
		return this.get(key) != null; 
	}

	public Iterator<V> values() {
		return new Iterator<V>() {
			private int count = 0;
			private Entry<K, V> currentEntry;
			
			{
				while ( ( currentEntry = values[count] ) == null && count < values.length ) {
					count++;
				}
			}

			@Override
			public boolean hasNext() {
				return count < values.length;
			}

			@Override
			public V next() {
				V toReturn = currentEntry.data;
				currentEntry = currentEntry.next;
				while ( currentEntry == null && ++count < values.length && (currentEntry = values[count]) == null );
				return toReturn;
			}

			@Override
			public void remove() {
			}
			
		};
	}
	
	private static class Entry<K, V> {
		private K key;
		private V data;
		private Entry<K,V> next;
		
		public Entry(K key, V data) {
			this.key = key;
			this.data = data;
		}
		
		public String toString() {
			return "{" + key + "=" + data + "}";
		}
	}
}