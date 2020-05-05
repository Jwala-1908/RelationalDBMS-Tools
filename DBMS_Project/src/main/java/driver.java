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
       
       d.insert(1);
       d.insert(14);
       d.insert(6);
       d.insert(4);
       d.insert(0);
       d.insert(15);
       d.insert(13);
       d.print();
     
       
       
    }
}
