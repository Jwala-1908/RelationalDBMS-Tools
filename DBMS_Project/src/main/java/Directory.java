/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author Radhesh Sarma
 */
public class Directory
{
        private HashMap<Integer,Bucket> directory;
        private final int bucketSize;
	private int globalDepth;
        private ArrayList<Integer> elements = new ArrayList();
        
        /**
	 * Constructs a Directory for Extendible Hashing with given global depth and fixed bucketsize.
     * @param bucketSize
     * @param globalDepth
	 */
        public Directory(int bucketSize,int globalDepth)
	{
		this.directory = new HashMap<>();
		this.globalDepth = globalDepth;
                this.bucketSize = bucketSize;   

	}
        
        public int getDepth()
	{
          return this.globalDepth;
	}
	
	public int getLength()
	{
		return this.directory.size();
	}
        
        public void insert(int value)
        {

            
             int key = BitUtility.getRightMostBits(value, this.globalDepth);
             

              if(!directory.containsKey(key))
              {
                  // if no bucket, then make a new bucket, add element and insert it in directory
                   Bucket b = new Bucket(bucketSize);
                   b.arr.add(value);
                   directory.put(key,b);
                   System.out.println(" Creating new Bucket and Inserting " + value + " to directory["+key+"]");
                   return;
              }
              
              Bucket b = directory.get(key);
              
              if(b.arr.size() < bucketSize)
              {
                  // bucket is not full , so just add to bucket
                  b.arr.add(value);
                  directory.put(key, b);
                  System.out.println("Inserting " + value + " to bucket pointed by directory["+key+"]");
                  return;
              }
              else 
              {
                  
              }
              
              
               
//                  Hashmap from int to vector of buckets implimentation, now discarded               
//               ArrayList<Bucket> arr = directory.get(key);
//               

//               if(arr.isEmpty())
//               {
//                   Bucket b = new Bucket(bucketSize);
//                   b.arr.add(value);
//                   arr.add(b);
//                   directory.put(key,arr);
//                   return;
//               }
//                
//               ArrayList<Bucket> ans = new ArrayList();
//               
//               for(int i = 0; i < arr.size(); i++)
//               {
//                   Bucket b = arr.get(i);
//                   if(b.arr.size() < bucketSize)
//                   {
//                       // put value in the directory 
//                       b.arr.add(value);
//                       ans.add(b);
//                       directory.put(key, ans);
//                       return;
//                   }
//                   else
//                   {
//                       ans.add(b);
//                   }
//                   
//                  
//               }
//               
//               Bucket b = new Bucket(bucketSize);
//               b.arr.add(value);
//               ans.add(b);
//               directory.put(key, ans);
               
           
        } 
        
        
        public void expand()
        {
            this.globalDepth++;

        }
         /*
	 * Prints the contents of directory
	 */
         public void print()
	{
                
		System.out.println("\n Global Depth: " + this.globalDepth);
                
                int len = (int) Math.pow(2, this.globalDepth);
                
                for(int i = 0; i < len; i++)
                {
                    System.out.print(i + "=[" );
                    
                    if(directory.containsKey(i))
                    {
                        Bucket b = directory.get(i);
                        b.print();
                    }
                    
                    System.out.println("]");
                }
                
	} 
       
        
}
