/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */

public class driver {
    
    public static void main(String[] args)
    { 
        // new directory with bucket size 3 and global depth 2
        
 
       Directory d = new Directory(3,2);  
       
       
       int[] input = new int[]{24,23,16,45,57,31,89};
       
       
       
       for(int i = 0; i <input.length; i++ )
       {
           d.insert(input[i]);
       }
       
       
       
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
       

        d.print();


    }
}
