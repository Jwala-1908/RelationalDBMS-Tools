
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
