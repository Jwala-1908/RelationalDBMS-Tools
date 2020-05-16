/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */
class utility

{
    static int p = 1000000007;
    static int power(int x, int y) 
    { 
        if (x == 0) return 0;  
        // Initialize result 
        int res = 1;      
       x = (x % p + p) % p;
        while (y > 0) 
        { 
            if((y & 1)==1) 
                res = (res * x) % p; 
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res; 
    }
    
    static int hash(int val)
    {
        val += Math.random()%p + 1;
        val %= p;
        
      return power(val, (int) (Math.random()%p + 1));
    }
}
public class driver {
    
    public static void main(String[] args)
    { 
        // new directory with bucket size 3 and global depth 2
        
         System.out.println(utility.hash(100));
//       Directory d = new Directory(4,1);  
//       d.insert(32);
//       d.insert(28);
//       d.insert(43);
//      d.insert(15);
//       d.insert(66);
//       d.insert(27);
//       d.insert(86);
//       d.insert(54);
//       d.insert(35);
//        d.insert(10);
//       d.insert(10);
//       d.insert(10);
//   d.insert(10);
//       d.insert(10);
//       d.print();
//       

       
    }
}
