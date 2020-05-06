/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */

import java.util.*; 
/**
 * Represents a bucket pointed at by an index in the Directory in Extendible Hashing.
 */


public class Bucket {
    
    private int localDepth,bitPattern,remainingSize,numWords,startOfBuffer;
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
		this.localDepth = newDepth;
		this.bitPattern = 0;
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
		this.localDepth = b.getLocalDepth();
		this.bitPattern = b.getBitPattern();
		this.numWords = 0;
		this.nextBucket = null;
		this.arr = b.arr;
		this.id = Bucket.ID++;
	}
        
        
	
	/**
	 * Gets the depth of the bucket.
	 * 
	 * @return the local depth of the bucket.
	 */
	public int getLocalDepth()
	{
		return this.localDepth;
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
        

        public int getID()
        {
            return this.id;
        }
        
      	/**
	 * Increments the depth of the bucket. Appends 0.
	 */
	public void incDepth0()
	{
		this.localDepth++;
		
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
		this.localDepth++;
		
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
            if(arr.size() >= getLocalDepth())return false;
            
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
                if(s.charAt(this.localDepth-1)==(s2.charAt(this.localDepth-1)))
                {
                    ans.add(x);
                }
                
            }
            
            this.arr = ans;
        }
        

        public String print()
        {
             StringBuilder sb = new StringBuilder();
            // sb.append("Bucket id :").append(this.id).append(" localdepth : ").append(this.localDepth).append(" Contents ");
             
            //System.out.print("Bucket id :" + this.id + " localdepth : " + this.localDepth + " Contents "); 
            for(int i = 0; i < arr.size(); i++)
            {
              // System.out.print(arr.get(i) + " ");
                sb.append(arr.get(i)).append(" ");
            }
            
            //System.out.print("BitString " + getBitString() + " ");
            String str;
        str = sb.toString();
                return str;
        }
        
        
        
        
         public String getBitString()
        {
                String bitStr;
		if (this.bitPattern == -1 || this.localDepth == 0)
			bitStr = "_";
		else
			bitStr = String.format("%"+this.localDepth+"s", Integer.toBinaryString(this.bitPattern));
		bitStr = bitStr.replace(' ', '0');  
                
                StringBuilder input1 = new StringBuilder(); 
                input1.append(bitStr);
                input1 = input1.reverse(); 
                return input1.toString();
        }
        

        
    
}
