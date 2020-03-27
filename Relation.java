import java.util.*;

public class Relation {
	static int count = 0;
	Relation parent;
	String name;
	public Vector<Attribute> atr_List;
	public Vector<FunDep> funcs;
	public Attribute[] prim_key;
	
	public int NF;
	// Construct New Relation
	public Relation(String name, Vector<String> vctr) {
		atr_List = new Vector<Attribute>();
		toAttribute(vctr);
		parent = null;
		this.name = name;
		getFDs();
//		prim_key = findPK();
		NF = findNF();
	}
	
	// Construct New Relation with parent relation
	public Relation(Vector<String> rel, Relation pnt) {
		toAttribute(rel);
		parent = pnt;
	}
	
	public void toAttribute(Vector<String> vc) {
		int n = vc.size();
		for (int i = 0; i < n; i++)
			atr_List.add(new Attribute(vc.elementAt(i),this));
	}
	
	void getFDs() {
		funcs = new Vector<FunDep>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of functional dependencies: ");
		int n = sc.nextInt();
		System.out.println("Enter the functional dependencies: ");
		String str = sc.nextLine();
		
		while (n!=0){
			str = sc.nextLine();
			FunDep fdtemp = new FunDep(str, this);
			FunDep[] fds = FunDep.decompose(fdtemp);
			
			boolean insert = true;
			System.out.println(fds.length);
			for (int j = 0; j < fds.length; j++){
				insert = false;
				if (funcs.size() > 0){
					for(int i = 0; i < funcs.size(); i++)
						if (!funcs.elementAt(i).toString().equals(fds[j].toString()))
							insert = true;
					if (insert)
						funcs.add(fds[j]);
				}
				else {
					funcs.add(fds[j]);
					insert = true;
				}
			}
			
			if (!insert)
				n++;
			n--;
		}
		
		sc.close();
	}
	
	//pass to this function the set of FDs in this relation
	//and get the minimum NF of the relation
	private int findNF() 
	{
		int min=500;
		for(int i=0;i<funcs.size();i++)
			min = (min<=funcs.elementAt(i).NF)?min:funcs.elementAt(i).NF;
		return min;
	}
	/*
	private Attribute[] findPK() {
		Attribute ret[] = new Attribute[5];
		return ret;
	}
	*/
	
	/*
	FunDep[] findFDClosure() {
		
	}
	*/
	
	public String toString() {
		Iterator<Attribute> itr = atr_List.iterator();
		String ret = "Table: " + name + "\n| ";
		while(itr.hasNext())
			ret+=itr.next().toString() + " | ";

		return ret;
	}
}
