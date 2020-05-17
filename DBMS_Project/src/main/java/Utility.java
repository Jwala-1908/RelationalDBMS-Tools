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
public class Utility
{
      static Integer p = 1229827;
    static Integer power(int x, int y) 
    { 
        if (x == 0) return 0;  
        // Initialize result 
        Integer res = 1;      
       x = (x % p + p) % p;
        while (y > 0) 
        { 
            if((y & 1)==1) 
                res = (res * x) % p; 
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res%p; 
    }
    

    public static int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
}
	
public static int generateRandomInt(int upperRange){
    Random random = new Random();
    return random.nextInt(upperRange);
}
    static int hash(int val)
    {

        val += Math.abs((generateRandomIntIntRange(2,p)));
        int pow =(generateRandomIntIntRange(2,p));
      return Math.abs(power(val, pow));
    }
}
