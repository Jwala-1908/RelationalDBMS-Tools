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
        private HashMap<Integer,Integer> index;
        private final int k = 10;
        private final int bucketSize;
	private int globalDepth;
        private ArrayList<Bucket> buckets = new ArrayList();

        
        /**
	 * Constructs a Directory for Extendible Hashing with given global depth and fixed bucketsize.
     * @param bucketSize
     * @param globalDepth
	 */
        public Directory(int bucketSize,int globalDepth)
	{
		this.index = new HashMap<>();
		this.globalDepth = globalDepth;
                this.bucketSize = bucketSize;  
                int len = (int) Math.pow(2, this.globalDepth);
                
                for(int i = 0; i < len; i++)
                {
                    index.put(i,-1);
                    // -1 indicates that the directory points to no bucket currently
                }
                

	}
        
        public int getDepth()
	{
          return this.globalDepth;
	}
	
	public int getLength()
	{
		return this.index.size();
	}
        
        public void insert(int value)
        {

            
             int key = BitUtility.getRightMostBits(value%k, this.globalDepth);
             
             if(index.get(key) == -1)
             {
                 if(findbucket(key) == null)
                 {
                  Bucket b = new Bucket(bucketSize);
                    if(BitUtility.endsWith0(value%k))
                    {
                         b.incDepth0();
                    }
                    else
                    {
                        b.incDepth1();
                    }

                    b.arr.add(value);
                    buckets.add(b);

                    index.put(key, b.getID());
                 }
                 else
                 {
                     System.out.println(value);
                             
            
                     Bucket b = findbucket(key);

          
                     if(b.arr.size() < bucketSize)
                     {
                         b.arr.add(value);
                         index.put(key,b.getID());
                         return;
                     }
                  
                 }
             }
             else
             {
                   Bucket b = buckets.get(index.get(key));
                   Bucket newbucket = b;
                   
                 // if(value == 86)b.print();
                                     
                   if(b.arr.size() < bucketSize)
                   {
                        newbucket.arr.add(value);
                       
                       for(int i = 0; i < buckets.size();i++)
                       {
                           if(buckets.get(i) == b)buckets.set(i,newbucket);
                       }
                   }
                   else
                   {
                       
                       split(b,value);
                       
                   }
             }
             
             
        } 
        
         public Bucket findbucket(int key)
        {
            for(int i = 0; i < buckets.size(); i++)
            {
               // buckets.get(i).print();
                for(int j = 1; j <= globalDepth; j++)
                {
                    if(buckets.get(i).getBitPattern() == BitUtility.getRightMostBits(key,j))
                    {
                        return buckets.get(i);
                    }
                }
            }
            
            return null;
        }
         
         public void split(Bucket b,int value)
         {
             
            Bucket b1 = new Bucket(b);
         b.incDepth0();
         
         //b1.print();
         b1.incDepth1();
               
           b = filter(b);
           
            b1 = filter(b1);
             buckets.add(b1);
             
          //   b1.print();
             
              int len = (int) Math.pow(2, this.globalDepth);
              
             for(int i = 0; i < len; i++)
             {
                 String str = Integer.toBinaryString(BitUtility.getRightMostBits(i,b.getLocalDepth()));
                 StringBuilder sb = new StringBuilder();

                   for (int toPrepend=b.getLocalDepth()-str.length(); toPrepend>0; toPrepend--)  sb.append('0');
                   
                 sb.append(str);
                  str = sb.toString();
                // System.out.println(str);
                 if(str.equals(b.getBitString()))
                 {
                     index.put(i,b.id);
                 }
             }
             
             for(int i = 0; i < len; i++)
             {
                 String str = Integer.toBinaryString(BitUtility.getRightMostBits(i,b1.getLocalDepth()));
                 StringBuilder sb = new StringBuilder();

                   for (int toPrepend=b1.getLocalDepth()-str.length(); toPrepend>0; toPrepend--)  sb.append('0');
                   
                 sb.append(str);
                  str = sb.toString();
                // System.out.println(str);
                 if(str.equals(b1.getBitString()))
                 {
                     index.put(i,b1.id);
                 }
             }
            insert(value);
             
          // System.out.println(b.getBitPattern() + " " + BitUtility.getRightMostBits(1,b.getLocalDepth()));
           //  System.out.println(b1.getBitPattern());
           //  
//             for(int i = 0; i < buckets.size(); i++)
//             {
//                 System.out.println(buckets.get(i).getBitPattern()+ " " + BitUtility.getRightMostBits(i,b.getLocalDepth()));
//                if(buckets.get(i).getBitPattern() == BitUtility.getRightMostBits(i,b.getLocalDepth()))
//                {
//                    buckets.set(i,b);
//                }
//             }
             
           //  b1.print();
         }
         public Bucket filter(Bucket b)
         {
            // System.out.println("Radhesh");
             
             ArrayList ans = new ArrayList<Integer>();
             
             for(int i = 0; i < b.arr.size(); i++)
             {
                 int value = b.arr.get(i);
                 //int key = BitUtility.getRightMostBits(value%k, this.globalDepth);
                 
                 String str = Integer.toBinaryString(BitUtility.getRightMostBits(value%k,b.getLocalDepth()));
                 StringBuilder sb = new StringBuilder();

                   for (int toPrepend=b.getLocalDepth()-str.length(); toPrepend>0; toPrepend--)  sb.append('0');
                   
                 sb.append(str);
                  str = sb.toString();
             
              //  System.out.println(b.getBitString() + " " + value + " " + str);
                 
                 if(b.getBitString().equals(str))
                 {
                     ans.add(value);
                 }
                       
             }
             b.arr = ans;
             return b;
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
            
                System.out.println("Printing the Directory");
		System.out.println("Global Depth: " + this.globalDepth);
                
                int len = (int) Math.pow(2, this.globalDepth);
                
                for(int i = 0; i < len; i++)
                {
                    System.out.print(i + "=[" );

                    if(index.get(i) !=-1)
                    {
                        buckets.get(index.get(i)).print();
                    }
                    System.out.println("]");
                }
                
	}
         
  
       
        
}
