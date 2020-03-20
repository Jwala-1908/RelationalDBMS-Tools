import java.util.*;
public class Relation 
{
	Scanner sc=new Scanner(System.in);
	Relation parent;
	String name;
	public Attribute[] atr_List;
	public Attribute[] prim_key;
	//let n be the number of dependancies to be entered;
	public FunDep[] fds;
	public int NF;
	// Construct New Relation
	public Relation(String[] rel,int n)
	{
		atr_List = toAttribute(rel);
		parent = null;
		name = "DEFAULT";
		fds=getFDs(n);
		// TODO Functional Dependencies
		prim_key = findPK();
		NF = findNF();
	}
	// Construct New Relation with parent relation
	public Relation(String[] rel, Relation pnt) 
	{
		atr_List = toAttribute(rel);
		parent = pnt;
	}
	Attribute[] toAttribute(String[] rel) 
	{
		int n = rel.length;
		Attribute[] ret = new Attribute[n];
		for (int i = 0; i < n; i++) 
		{
			ret[i] = new Attribute(rel[i], this);
		}
		return ret;		
	}
	
	FunDep[] getFDs(int n) 
	{
		FunDep ret[] = new FunDep[n];
		int i=0;
		while(n>=0)
		{
			ret[i].rep=sc.nextLine();
			i++;
			n--;
		}
		
		return ret;
	}
	//pass to this function the set of FDs in this relation
	//and get the minimum NF of the relation
	private int findNF() 
	{

		int min=500;
		for(int i=0;i<fds.length;i++)
		{
			min=min<=fds[i].NF ?min: fds[i].NF;
		}
		return min;
		
	}
    
	private Attribute[] findPK()
	{
		Attribute ret[] = new Attribute[5];
		return ret;
	}
	/*
	FunDep[] findFDClosure() 
	{
	}
	*/
	public String toString()
	{
		String ret = "Table: " + name + "\n| ";
		for (Attribute a : atr_List)
			ret+= a.toString()+" | ";
		return ret;
	}
}
