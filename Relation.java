package dbms_proj;

import java.util.*;
//import exceptions.*;

public class Relation {
	public static String RName = "Relation";
	public static int cnt = 0; 
	public AlgoPack alg;
	static int count = 0;
	public String name;
	public Vector<String> atr_List;
	public Vector<String> keyAttr;
	public Vector<FunDep> funcs;
	public Vector<Vector<String>> all_Keys;
	public Vector<Vector<String>> cand_Keys;
	public Vector<String> prim_key;
	public Vector<Relation> decomposed;
	public int NF = 1;
	
	// Construct New Relation
//	public Relation() {
//		Scanner sc = new Scanner(System.in);
//		name = getName(sc);
//		atr_List = new Vector<String>();
//		getAttr(sc);
//		RName = name;
//		boolean fd_exist = getFDs(sc);
//		if (fd_exist)
//			fd_exist = checkTriv();
//		if (fd_exist) {
//			alg = new AlgoPack(this);
//			fillIn(this);
////			System.out.println("Candidate_Keys:\n" + cand_Keys);
//			NF = findNF();
//			tempPrintFD();
//			if (NF != 4) {
////				if (askDecompose(sc)) {
//					decomposeRel();
////				}
//			}
//			else {
//				decomposed = new Vector<Relation>();
//				decomposed.add(this);
//				System.out.println("The relation is already in BCNF");
//			}
//			cnt++;
//		}
//		else
//			zeroFD(this);
//		sc.close();
//	}
	
	@SuppressWarnings("unchecked")
	public Relation(String nm, Vector<String> attr) {
		name = nm;
		atr_List = attr;
		prim_key = (Vector<String>)attr.clone();
		funcs = new Vector<FunDep>();
		all_Keys = new Vector<Vector<String>>();
		all_Keys.add(attr);
		cand_Keys = new Vector<Vector<String>>();
		cand_Keys.add(attr);
	}
	
	public Relation (String nm, FunDep fd){
		name = nm;
		atr_List = new Vector<String>();
		atr_List.addAll(fd.X);
		atr_List.addAll(fd.Y);
		fd.pnt = this;
		funcs = new Vector<FunDep>();
		funcs.add(fd);
		alg = new AlgoPack(this);
		prim_key = new Vector<String>();
		prim_key.addAll(fd.X);
		all_Keys = new Vector<Vector<String>>();
		cand_Keys = new Vector<Vector<String>>();
	}
	public Relation(NewJFrame nj) throws InvalidAttributeException,InvalidFunctionalDependencyException
        {
            funcs=new Vector<FunDep>();
            name=nj.name;
            atr_List=nj.attri;
            boolean check=getGuiFds(nj.fun);
            if(check==true)
                check=checkTriv();
            if (check) {
			alg = new AlgoPack(this);
			fillIn(this);
//			System.out.println("Candidate_Keys:\n" + cand_Keys);
			NF = findNF();
			tempPrintFD();
			if (NF != 4) {
//				if (askDecompose(sc)) {
					decomposeRel();
//				}
			}
			else {
				decomposed = new Vector<Relation>();
				decomposed.add(this);
				System.out.println("The relation is already in BCNF");
			}
			cnt++;
		}
		else
			zeroFD(this);
        }
	@SuppressWarnings("unchecked")
	public Relation(Relation r) {
		this.name = r.name;
		this.atr_List = (Vector<String>)r.atr_List.clone();
		this.funcs = (Vector<FunDep>)r.funcs.clone();
		fillIn(this);
		NF = findNF();
	}
	
	public Relation(String name, Vector<String> vctr, Vector<FunDep> fds) {
		this.name = name;
		atr_List = vctr;
		funcs = new Vector<FunDep>();
		this.alg = new AlgoPack(this);
		for (FunDep f : fds) {
			FunDep fdNew = new FunDep(f);
			fdNew.pnt = this;
			funcs.add(fdNew);
		}
		cnt++;
	}

//	public Relation(String name, Vector<String> vctr, FunDep fds) {
//		atr_List = vctr;
//		this.name = name;
//		funcs = new Vector<FunDep>();
//		fds.pnt = this;
//		funcs.add(fds);
//		Relation.fillIn(this);
//		NF = findNF();
//	}
	
	public boolean checkTriv() {
		for (int i = 0; i < funcs.size(); i++) {
			FunDep fd = funcs.elementAt(i);
			if (fd.X.containsAll(fd.Y)) {
				funcs.remove(i--);
			}
		}
		if (funcs.size()>0)
			return true;
		return false;
	}
        boolean getGuiFds(Vector<String> fdsi) throws InvalidAttributeException,InvalidFunctionalDependencyException 
        {
            int n=fdsi.size(),balu=0;
            if(n==0)
                return false;
            String str;
            while (balu<n){
			//str = scan.nextLine();
			str=fdsi.elementAt(balu++);
                        str = str.toUpperCase();
			//try {
				StringTokenizer strtok = new StringTokenizer(str,"->");
				if (strtok.countTokens()!=2) {
					throw new InvalidFunctionalDependencyException(str);
				}
				FunDep fdtemp = new FunDep(str, this);
				FunDep[] fds = FunDep.decompose(fdtemp);
				
				boolean insert = true;
				for (int j = 0; j < fds.length; j++) {
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
				//n--;
			//} catch (InvalidAttributeException iae) {
			//	System.out.println(iae);
			//	System.out.println("Please re-enter the proper functional dependency and continue:");
			//	continue;
			//} catch(InvalidFunctionalDependencyException ifde) {
			//	System.out.println(ifde);
			//	System.out.println("Please re-enter the proper functional dependency and continue:");
			//	continue;
			//}
		}
            return true;
        }
	boolean askDecompose(Scanner sc) {
		if (NF == 4)
		System.out.println("The relation is in " + NF + "NF.");
		System.out.println("Would you like to decompose the relation to "  + (NF+1) + "NF? Y/N:");
		String ans = sc.next().toUpperCase();
		if (ans.equals("Y"))
			return true;
		else if (ans.equals("N"))
			return false;
		System.out.println("Invalid response. Answer only as 'Y' or 'N'");
		return askDecompose(sc);
	}
	
	public void filterRelations(Vector<Relation> inpt) {
//		for (int i = 0; i < inpt.size(); i++){
//			Relation r = inpt.elementAt(i);
//			if (r.atr_List.containsAll(this.atr_List))
//				inpt.remove(i--);				
//		}
		Vector<FunDep> fdRemove = new Vector<FunDep>();
		for (int i = 0; i < inpt.size(); i++) {
			Relation r = inpt.elementAt(i);
			Vector<Vector<String>> subs = r.alg.getSubsets(r.prim_key);
			for (int j = 0; j < r.funcs.size(); j++) {
				FunDep fd = r.funcs.elementAt(j);
				if (subs.contains(fd.X)) {
					if (!fdRemove.contains(fd))
						fdRemove.add(fd);
					r.atr_List.removeAll(fd.Y);
				}
			}
			r.funcs.removeAll(fdRemove);
		}
		
		String relname;
		for (FunDep fd : fdRemove) {
			relname = Relation.RName + "_" + (++Relation.cnt);
			Relation z = new Relation(relname, fd);
			inpt.add(z);
		}
		
		for (Relation r : inpt) {
			Relation.fillIn(r);
			r.NF = r.findNF();
		}
	}
	public Vector<Relation> breakRelations(Relation inpt, int nf) {
		Vector<Relation> ret = new Vector<Relation>();
		Vector<FunDep> fdRemove = new Vector<FunDep>();
		for (FunDep fd : inpt.funcs) {
			if (fd.NF < nf) {
				fdRemove.add(fd);
			}
		}
		inpt.funcs.removeAll(fdRemove);
		inpt.atr_List.clear();
		for (int i = 0; i < inpt.funcs.size(); i++) {
			FunDep fd = inpt.funcs.elementAt(i);
			for (String s : fd.X)
				if (!inpt.atr_List.contains(s))
					inpt.atr_List.add(s);
			if (!inpt.atr_List.containsAll(fd.Y))
				inpt.atr_List.addAll(fd.Y);
		}
		ret.add(inpt);
		String relname;
		for (FunDep fd : fdRemove) {
			relname = Relation.RName + "_" + (++Relation.cnt);
			FunDep newFD = new FunDep(fd);
			Relation r = new Relation(relname, newFD);
			newFD.pnt = r;
			ret.add(r);
		}
		for (Relation r : ret) {
			Relation.fillIn(r);
			r.NF = r.findNF();
		}
		
		return ret;
	}
	
	public void checkRelations(Vector<Relation> inpt, int nf) {
		Vector<Relation> newrels = new Vector<Relation>();
		for (int i = 0; i < inpt.size(); i++) {
			Relation r = inpt.elementAt(i);
			if (r.NF < nf) {
				newrels = breakRelations(r,nf);
				inpt.remove(r);
				inpt.addAll(newrels);
			}
		}
		Vector<Relation> relRemove = new Vector<Relation>();
		for (int i = 0; i < inpt.size(); i++){
			Relation a = inpt.elementAt(i);
			for (int j = i+1; j < inpt.size(); j++){
				Relation b = inpt.elementAt(j);
				if (a != b && a.atr_List.size() == b.atr_List.size() && b.atr_List.containsAll(a.atr_List)) {
					relRemove.add(b);
				}
			}
		}
		inpt.removeAll(relRemove);
	}
	
	public void decomposeRel() {
		if (NF == 1) {
			decomposed = alg.decomp2NF();
			filterRelations(decomposed);
			checkRelations(decomposed, 2);
		}
		else if (NF == 2) {
			decomposed = alg.decomp3NF();
			filterRelations(decomposed);
			checkRelations(decomposed, 3);
		}
		else if (NF == 3) {
			decomposed = alg.decompBCNF();
			filterRelations(decomposed);
			checkRelations(decomposed, 4);
		}
		else
			decomposed.add(this);
		
		for (int i = 0; i < decomposed.size(); i++) {
			Relation r = decomposed.elementAt(i);
			if (r.atr_List.size() == 0)
				decomposed.remove(i--);
		}
	}
	
	String getName(Scanner sc) {
		System.out.println("Enter the name of the Relation:");
		String nm = sc.nextLine();
		return nm;
	}
	
	void tempPrintFD() {
		System.out.println("The atomic functional dependencies are:");
		for (FunDep fd : funcs) {
			System.out.println(fd + " NF: " + fd.NF);
		}
		System.out.println("Primary Key: " + prim_key);
	}
	
	void getAttr(Scanner sc) {
		System.out.println("Enter the attributes (space separated):");
		// space separated attributes taken as 1 string input
		boolean flag = false;
		while(!flag) {
			try {
				String attr = sc.nextLine();
				attr = attr.toUpperCase();
				StringTokenizer st = new StringTokenizer(attr," ");
				while(st.hasMoreTokens()) {
					attr = st.nextToken();
					if (atr_List.contains(attr))
						throw new RepeatedAttributeException(attr);
					else
						atr_List.add(attr);
				}
				flag = true;
			} catch (RepeatedAttributeException rae) {
				System.out.println(rae);
				atr_List.clear();
				flag = false;
				continue;
			}
		}
	}
	
	public static void fillIn(Relation rel) {
		if (rel.funcs.size() == 0) {
			zeroFD(rel);
			return;
		}
		rel.alg.fillKeys();
		rel.alg.fillNF();
//		System.out.println(rel.funcs.firstElement().NF);
	}
	
	static void zeroFD(Relation r) {
		r.all_Keys = new Vector<Vector<String>>();
		Vector<String> K = new Vector<String>(r.atr_List);
		r.all_Keys.add(K);
		r.prim_key = r.all_Keys.elementAt(0);
		r.cand_Keys = r.all_Keys;
		r.NF = 4;
		cnt++;
	}
	
	boolean getFDs(Scanner scan){
		funcs = new Vector<FunDep>();
		System.out.println("Enter number of functional dependencies:");
		int n = scan.nextInt();
		if (n == 0) {
			scan.close();
			return false;
		}
		System.out.println("Enter the functional dependencies: ");
		String str = scan.nextLine();
		while (n!=0){
			str = scan.nextLine();
			str = str.toUpperCase();
			try {
				StringTokenizer strtok = new StringTokenizer(str,"->");
				if (strtok.countTokens()!=2) {
					throw new InvalidFunctionalDependencyException(str);
				}
				FunDep fdtemp = new FunDep(str, this);
				FunDep[] fds = FunDep.decompose(fdtemp);
				
				boolean insert = true;
				for (int j = 0; j < fds.length; j++) {
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
			} catch (InvalidAttributeException iae) {
				System.out.println(iae);
				System.out.println("Please re-enter the proper functional dependency and continue:");
				continue;
			} catch(InvalidFunctionalDependencyException ifde) {
				System.out.println(ifde);
				System.out.println("Please re-enter the proper functional dependency and continue:");
				continue;
			}
		}
		return true;
	}
	
	public Vector<String> findPK(){
		Vector<String> pk = new Vector<String>();
		int min = atr_List.size();
		for (int i = 0; i < all_Keys.size(); i++)
			if (min > all_Keys.elementAt(i).size()) {
				pk = all_Keys.elementAt(i);
				min = pk.size();
			}
		return pk;
	}
	
	//pass to this function the set of FDs in this relation
	//and get the minimum NF of the relation
	public int findNF() {
		int min = 4;
		for(int i = 0; i < funcs.size(); i++)
			min = (min<=funcs.elementAt(i).NF)?min:funcs.elementAt(i).NF;
		return min;
	}
	
	public String toString() {
		String ret = "Table: " + name + "\n| ";
		for(int i = 0; i < atr_List.size(); i++)
			ret += atr_List.elementAt(i) + " | ";
		
		if (NF == 4) {
			ret += "\nThe relation " + name + " is in BCNF\n";
		}
		else
			ret += "\nThe relation " + name + " is in " + NF + "NF\n";
		ret += "Primary Key: " + prim_key + "\n";
                ret +="Candidate Keys:"+"\n"+ cand_Keys +"\n";
		return ret;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Relation newR = new Relation(this);
		return newR;
	}
}
