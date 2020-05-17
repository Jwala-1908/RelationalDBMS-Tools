package dbms_proj;

import java.util.*;

public class FunDep implements Cloneable{
	public int NF = 1;
	public Relation pnt;
	String rep;
	Vector<String> X = new Vector<String>();
	Vector<String> Y = new Vector<String>();
	
	public FunDep(String str, Relation rel) throws InvalidAttributeException {
		pnt = rel;
		rep = str.trim();
		StringTokenizer st = new StringTokenizer(str,",{}");
		boolean flag = false;
		while(st.hasMoreTokens()){
			String temp = st.nextToken();
			if (temp.equals("->")){
				flag = true;
				continue;
			}
			if (!flag) {
				if (pnt.atr_List.contains(temp))
					X.add(temp);
				else
					throw new InvalidAttributeException(temp);	
			}
			else {
				if (pnt.atr_List.contains(temp))
					Y.add(temp);
				else
					throw new InvalidAttributeException(temp);
			}
		}
		// to extract attributes and work with them,
		// tokenize "{A,B} -> {C,D,E}" (str) with delimiters ',' , '{', and '}'
	}
	
	@SuppressWarnings("unchecked")
	public FunDep(FunDep fd) {
		this.pnt = null;
		this.X = (Vector<String>)fd.X.clone();
		this.Y = (Vector<String>)fd.Y.clone();
		this.rep = fd.rep;
	}

	public FunDep(FunDep f, String y) {
		this.pnt = f.pnt;
		this.rep = "{";
		for (int i = 0; i < f.X.size(); i++){
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
	//	returns 4 if relation is in the Boyce-Codd normal form (BCNF)
	public int findNF() {
		if (check2NF()) {
			if (check3NF()) {
				if (checkBCNF())
					return 4;
				else
					return 3;
			}
			else {
				return 2;
			}
		}
		else
			return 1;
	}
	
	//Check if a relation is in the second normal form (2NF)
	// A table R is in 2NF if for every non-trivial FD A->b, either A is not a proper subset of any candidate
	// key, or b is a key attribute.
	boolean check2NF(){
		if (pnt.keyAttr.contains(Y.elementAt(0)))
			return true;
		for (Vector<String> ck : pnt.cand_Keys) {
			Vector<Vector<String>> subs = pnt.alg.getSubsets(ck);
			for (Vector<String> subset : subs) {
				if (X.size() == subset.size()) {
					if (X.containsAll(subset))
						return false;
				}
			}
		}
		return true;
	}
	
	
	
	//Check if a relation is in the third normal form (3NF)
//	Satisfies 2 NF &
//	no non-key attribute of R is transitively dependent on any candidate key
	boolean check3NF() {
		int n = pnt.all_Keys.size();
		boolean flag = false;
		for (int i = 0; i < n; i++)
			if (pnt.all_Keys.elementAt(i).containsAll(X) && X.containsAll(pnt.all_Keys.elementAt(i))) {
				flag = true;
				break;
			}
		
		if (flag)
			return true;
		if (pnt.keyAttr.contains(Y.elementAt(0)))
			return true;
		
		return false;
	}

	//Check if a relation is in Boyce-Codd normal form (BCNF)
	boolean checkBCNF(){
		for (Vector<String> supkey : pnt.all_Keys) {
			if (X.size() == supkey.size() && X.containsAll(supkey))
				return true;
		}
		return false;
	}
	
	public static FunDep[] decompose(FunDep f) {
		int n = f.Y.size();
		FunDep[] ret = new FunDep[n];
		if (n == 1) {
			ret[0] = f;
			return ret;
		}
		int k = 0;
		for (int i = 0; i < n; i++)
			ret[k++] = new FunDep(f,f.Y.elementAt(i));
		return ret;
	}
	
	public String toString() {
		rep = X.toString();
		rep += "->" + Y.toString();
		return rep;
	}
	
	public int compare(FunDep b) {
		return Integer.compare(this.X.size(), b.X.size());
	}
}
