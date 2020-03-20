
public class FunDep 
{
	public int NF;
	String rep;
	public FunDep(String str, Relation rel) 
	{
		rep = str;
		// to extract attributes and work with them,
		// tokenize "{A,B} -> {C,D,E}" (str) with delimiters ',' , '{', and '}' 
		NF = findNF();
	}
	
	//Check if a relation is in the second normal form (2NF)
	boolean check2NF()
	{
		return true;
	}

	//Check if a relation is in the third normal form (3NF)
	boolean check3NF()
	{
		return true;
	}


	//Check if a relation is in Boyce-Codd normal form (BCNF)
	boolean checkBCNF()
	{
		return true;
	}

	// returns the normal form of the given relation 
	//	returns 1 if relation is in the first normal form (1NF) 
	//	returns 2 if relation is in the second normal form (2NF)
	//	returns 3 if relation is in the third normal form (3NF)
	//	returns 4 if relation is in the Boyce-Codd normal form (BCNF)
	int findNF() 
	{
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
