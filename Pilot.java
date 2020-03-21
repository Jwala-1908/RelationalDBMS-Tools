import java.util.*;
import java.io.*;

public class Pilot 
{
	public static void main(String[] args) 
	{
		String arr[] = {"A","B","C","D","E"};
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		//n is the number of FDs to be entered
		Relation rel = new Relation(arr,n);
		System.out.println(rel);
		int num=rel.length();
		
		String fdtest = "{A,B} -> {C,D,E}";
		FunDep fd = new FunDep(fdtest,rel);
		System.out.println(fd);
	}
}
