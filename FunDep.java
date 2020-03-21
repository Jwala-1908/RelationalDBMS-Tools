import java.util.*;
public class FunDep 
{
	public int NF;
	String rep;
	String rep1[]=new String[2];
	public FunDep(String str, Relation rel) 
	{
		rep = str;
		StringTokenizer stt=new StringTokenizer(rep,"->");
		
		int t=0;
		//for now assumed that FD is simply tokenized by ->
		while(stt.hasMoreTokens())
		   rep1[t++]=stt.nextToken();

		//someone please address the below
		// to extract attributes and work with them,
		// tokenize "{A,B} -> {C,D,E}" (str) with delimiters ',' , '{', and '}' 
		NF = findNF();
	}
	
	//Check if a relation is in the second normal form (2NF)
	boolean check2NF()
	{
		if(isPrimaryKey(rep1[0]))
		return true;
		return false;
	}

	//Check if a relation is in the third normal form (3NF)
	boolean check3NF()
	{
		if(isPrimaryKey(rep1[0])||(isPrimaryKey(rep1[1])))
		return true;
		return false;
	}


	//Check if a relation is in Boyce-Codd normal form (BCNF)
	boolean checkBCNF()
	{
		if(isPrimaryKey(rep1[0]))
		return true;
		return false;
	}

	// returns the normal form of the given relation 
	//	returns 1 if relation is in the first normal form (1NF) 
	//	returns 2 if relation is in the second normal form (2NF)
	//	returns 3 if relation is in the third normal form (3NF)
	//	returns 4 if relation is in the Boyce-Codd normal form (BCNF)
	int findNF() 
	{
		if(checkBCNF()) return 0;
		if(check3NF()) return 3;
		if(check2NF()) return 2;
		return 1;
	}
	/*
	private FunDep[] decompose()
	{	
	}
	*/
	public String toString() 
	{
		return rep;
	}
}
