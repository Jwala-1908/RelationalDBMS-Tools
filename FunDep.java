import java.util.*;

public class FunDep {
	public int NF;
	public Relation parent;
	String rep;
	Vector<String> X = new Vector<String>();
	Vector<String> Y = new Vector<String>();
	public FunDep(String str, Relation rel) {
		rep = str;
		StringTokenizer st = new StringTokenizer(str,",{}");
		boolean flag = false;
		while(st.hasMoreTokens())
		{
			String temp = st.nextToken();
			if (temp.equals("->"))
			{
				flag = true;
				continue;
			}
			if (!flag) {
				X.add(temp);
			}
			else {
				Y.add(temp);
			}
		}
		// to extract attributes and work with them,
		// tokenize "{A,B} -> {C,D,E}" (str) with delimiters ',' , '{', and '}' 
		NF = findNF();
	}
	public FunDep(FunDep f, String y) {
		this.parent = f.parent;
		this.rep = "{";
		for (int i = 0; i < f.X.size(); i++) {
			this.rep += f.X.elementAt(i);
			if (i != f.X.size()-1)
				this.rep += ",";
		}
		this.rep += "}->{" + y + "}";
		this.X = f.X;
		Y.add(y);
	}
	
	// returns the normal form of the given relation 
	//	returns 1 if relation is in the first normal form (1NF) 
	//	returns 2 if relation is in the second normal form (2NF)
	//	returns 3 if relation is in the third normal form (3NF)
	//	returns 0 if relation is in the Boyce-Codd normal form (BCNF)
	public int findNF() {
		if (checkBCNF()) return 0;
		if (check3NF()) return 3;
		if (check2NF()) return 2;
		return 1;
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
	
	
	public static FunDep[] decompose(FunDep f) {
		int n = f.Y.size();
		FunDep[] ret = new FunDep[n];
		if (n == 1) {
			ret[0] = f;
			return ret;
		}
		int k = 0;
		for (int i = 0; i < n; i++) {
			ret[k++] = new FunDep(f,f.Y.elementAt(i));
		}
		return ret;
	}
	
	public String toString() {
		return rep;
	}
	
}
