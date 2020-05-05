/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */

import java.io.*; 
import java.util.*; 
/**
 * Represents a bucket pointed at by an index in the Directory in Extendible Hashing.
 */


public class Bucket {
    
    private char[] bucket;
    private int depth,bitPattern,remainingSize,numWords,startOfBuffer;
    private Bucket nextBucket;
    
    
    private static int ID;
   public int id;
   ArrayList<Integer> arr;
   
   
   
   /**
	 * Constructs a new bucket.
	 * 
	 * @param capacity the maximum size of the bucket.
	 */
	public Bucket(int capacity)
	{
		this(capacity, 0);
	}
        
        
        	/**
	 * Constructs a new bucket.
	 * 
	 * @param capacity the maximum size of the bucket.
	 * @param newDepth the initial depth of the bucket.
	 */
	public Bucket(int capacity, int newDepth)
	{
		this.bucket = new char[capacity];
		this.depth = newDepth;
		this.bitPattern = -1;
		this.remainingSize = capacity;
		this.numWords = 0;
		this.startOfBuffer = capacity;
		this.nextBucket = null;
                arr = new ArrayList<Integer>();
		this.id = Bucket.ID++;
	}
        
        	/**
	 * Creates an empty Bucket with the state of the input Bucket.
	 * 
	 * @param b bucket to copy state, but not the bucket header/buffer (contents) itself.
	 */
	public Bucket(Bucket b)
	{
		this.bucket = new char[b.getCapacity()];
		this.depth = b.getDepth();
		this.bitPattern = b.getBitPattern();
		this.remainingSize = this.bucket.length;
		this.numWords = 0;
		this.startOfBuffer = this.bucket.length;
		this.nextBucket = null;
		this.arr = b.arr;
		this.id = Bucket.ID++;
	}
        
        
        	/**
	 * Gets the capacity of the bucket.
	 * 
	 * @return the capacity of the bucket.
	 */
	public int getCapacity()
	{
		return this.bucket.length;
	}
	
	/**
	 * Gets the depth of the bucket.
	 * 
	 * @return the local depth of the bucket.
	 */
	public int getDepth()
	{
		return this.depth;
	}
	
	/**
	 * Gets the bit pattern of the bucket.
	 *
	 * @return the bit pattern of the bucket.
	 */
	public int getBitPattern()
	{
		return this.bitPattern;
	}
        

        
        
      	/**
	 * Increments the depth of the bucket. Appends 0.
	 */
	public void incDepth0()
	{
		this.depth++;
		
		if (this.bitPattern == -1)
			this.bitPattern = 0;
		else
			this.bitPattern = BitUtility.append0(this.bitPattern);
	}
	
	/**
	 * Increments the depth of the bucket. Appends 1.
	 */
	public void incDepth1()
	{
		this.depth++;
		
		if (this.bitPattern == -1)
			this.bitPattern = 1;
		else
			this.bitPattern = BitUtility.append1(this.bitPattern);
	}
	
	/**
	 * Gets the remaining size of the bucket.
	 * 
	 * @return the remaining size of the bucket.
	 */
	public int getRemainingSize()
	{
		return this.remainingSize;
	}
        
         /**
	 * Inserts a value into the bucket.
	 * 
	 * @param value The value to insert.
	 * @return true if insert succeeded, false if there is not enough room to insert.
	 */
        
        public boolean insert(int value)
        {
            if(arr.size() >= getDepth())return false;
            
            this.depth++;
            arr.add(value);
            
            return true;
        }
        
        	/**
	 * Searches for the given key in the bucket.
	 * 
	 * @param key The key to search for.
	 * @return Index of key if found. -1 otherwise.
	 */
        
        public int search(int key)
        {
               for(int i = 0; i < arr.size(); i++)
               {
                   if(arr.get(i) == key)return i;
               }
               
            return -1;
        }
        
        public void filter()
        {
            ArrayList<Integer> ans  = new ArrayList<Integer>();
            String s = Integer.toBinaryString(this.bitPattern);
            
            for(int i = 0; i < arr.size(); i++)
            {
                int x = arr.get(i);
                String s2 = Integer.toBinaryString(x);
                if(s.charAt(this.depth-1)==(s2.charAt(this.depth-1)))
                {
                    ans.add(x);
                }
                
            }
            
            this.arr = ans;
        }
        

        
    
}
