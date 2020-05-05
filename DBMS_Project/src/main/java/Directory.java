/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */
public class Directory
{
    	private Bucket[] directory;
	private int depth;
        
        public Directory(int bucketSizeInBytes)
	{
		this.directory = new Bucket[] {new Bucket(bucketSizeInBytes)};
		this.depth = 0;
	}
        
        public int getDepth()
	{
          return this.depth;
	}
	
	public int getLength()
	{
		return this.directory.length;
	}
        
        public void insert(int value)
        {
            int key = BitUtility.getLeftMostBits(value, this.depth);
            Bucket b = this.directory[key];
            System.out.println("Inserting " + value + " to directory["+key+"] (Bucket: "+b.id+")");
            
            boolean inserted = b.insert(value);
            
            while(!inserted)
            {
                if( b.getDepth() < this.depth)
                {
                    System.out.println("Bucket " + b.id + " is full! Hence spliting");
                    // local depth is less than global depth hence only splitting takes place
                    Bucket b2 = new Bucket(b);
		     b2.incDepth1();
		     b.incDepth0();
                     
                     b.filter();
                     b2.filter();
                   //  directory.add(b2);
                     
                     
                     
                }
                else if(b.getDepth() == this.depth)
                {
                    
                }
                
                key = BitUtility.getLeftMostBits(key, this.depth);
		b = this.directory[key];
		inserted = b.insert(value);
            }
            
        } 
       
        
}
