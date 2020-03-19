public class Relation 
{
	Relation parent;
	String name;
	public Attribute[] atr_List;
	public Attribute[] prim_key;
	public int NF;
	// Construct New Relation
	public Relation(String[] rel)
	{
		atr_List = toAttribute(rel);
		parent = null;
		name = "DEFAULT";
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
	String[] getFDs() 
	{
		String ret[] = new String[5];
		return ret;
	}
	private int findNF() 
	{
		return 1;
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
