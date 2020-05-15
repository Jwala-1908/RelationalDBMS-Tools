package dbms_proj;

import java.util.Vector;

public class AlgoPack {
	Relation rltn;
	
	AlgoPack(Relation rel){
		rltn = rel;
	}
	// This function decomposes 3NF into BCNF
	// A new relation is made for all FDs not satisfying the BCNF state 
	@SuppressWarnings("unchecked")
	public Vector<Relation> decompBCNF(){
		System.out.println(rltn.cand_Keys);
		Vector<Relation> ret = new Vector<Relation>();
		Vector<FunDep> fdList = (Vector<FunDep>)rltn.funcs.clone();
		Vector<FunDep> fdNew = new Vector<FunDep>();
		// Identification and separation of non-BCNF fds
		for (FunDep fd : fdList) {
			if (fd.NF != 4) {
				fdNew.add(fd);
			}
		}
		fdList.removeAll(fdNew);
		// Creating a new relation with the remaining FDs
		String relname;
		if (fdList.size()!= 0) {
			relname = Relation.RName + "_" + (++Relation.cnt);
			ret.add(new Relation(relname, getFDAttrs(fdList), fdList));
			Relation.fillIn(ret.lastElement());
			ret.lastElement().NF = ret.lastElement().findNF();
		}
		// New relation for every fd that was removed.
		for (FunDep fd : fdNew) {
			relname = Relation.RName + "_" + (++Relation.cnt);
			FunDep f = new FunDep(fd); 
			ret.add(new Relation(relname,f));
			f.pnt = ret.lastElement();
			Relation.fillIn(ret.lastElement());
			ret.lastElement().NF = ret.lastElement().findNF();
		}
		
		return ret;
	}
	
	// This function is used in the decomposition from 3NF to BCNF
	// Given a vector of FDs, it returns a vector that contains all
	// attributes occuring in the set of FDs
	public Vector<String> getFDAttrs(Vector<FunDep> fds){
		Vector<String> attr = new Vector<String>();
		for (FunDep fd : fds) {
			for (String a : fd.X)
				if (!attr.contains(a))
					attr.add(a);
			if (!attr.contains(fd.Y.elementAt(0)))
					attr.add(fd.Y.elementAt(0));
		}
		
		return attr;
	}
	
	// RULE FOR 3NF
//	Satisfy 2NF
//	no non-prime attribute of R is transitively dependent on the primary key.
	// This function decomposes a relation in 2NF to 3NF
	// For all FDs containing any key attribute in X, its Y is stored
	@SuppressWarnings("unchecked")
	public Vector<Relation> decomp3NF(){
		System.out.println(rltn.cand_Keys);
		Vector<Relation> ret = new Vector<Relation>();
		Vector<FunDep> fdrem = (Vector<FunDep>)rltn.funcs.clone();
		Vector<String> atrs = new Vector<String>();
		boolean flag = false;
		// Store Y of all FDs having key attributes in 'atrs'
		for (int i = 0; i < fdrem.size(); i++) {
			FunDep fd = fdrem.elementAt(i);
			for (String a : rltn.keyAttr) {
				if (fd.X.contains(a)) {
					if(!atrs.contains(fd.Y.elementAt(0)))
						atrs.add(fd.Y.elementAt(0));
					break;
				}
			}
		}
		flag = false;
		// Filter out FDs containing elements in 'atrs' in their LHS since all atrs will be removed from current relation
		Vector<String> atrCopy = (Vector<String>)rltn.atr_List.clone();
		Vector<FunDep> newFDR = new Vector<FunDep>();
		for (int i = 0; i < atrs.size(); i++) {
			String a = atrs.elementAt(i);
			flag = false;
			for (int j = 0; j < fdrem.size(); j++) {
				FunDep fd = fdrem.elementAt(j);
				if (fd.X.contains(a)) {
					fdrem.remove(fd);
					newFDR.add(fd);
					atrCopy.remove(fd.Y.elementAt(0));
					flag = true;
				}
			}
		}
		// Checking if attributes present in FDs remaining are all present in the list of attributes
		// Then adding the set of relations to new relations
		String relname;
		relname = Relation.RName + "_" + (++Relation.cnt);
		Vector<FunDep> fdnew = checkFDAttrs(atrCopy,fdrem);
		Vector<FunDep> finFD = new Vector<FunDep>();
		for (FunDep fd : fdnew) {
			finFD.add(new FunDep(fd));
		}
		if (fdnew.size() != 0) {
			ret.add(new Relation(relname, atrCopy, finFD));
			Relation.fillIn(ret.lastElement());
			ret.lastElement().NF = ret.lastElement().findNF();
		}
		
		// If there were any FDs removed in the second loop, they are put in new relations
		if (flag) {
			for (FunDep fd : newFDR) {
				relname = Relation.RName + "_" + (++Relation.cnt);
				ret.add(new Relation(relname, new FunDep(fd)));
				Relation.fillIn(ret.lastElement());
				ret.lastElement().NF = ret.lastElement().findNF();
			}
		}
		checkAllAttributes(ret);
		
		return ret;
	}
	
	// returns the FDs that the attributes present can support.
	// removes the FDs any attribute of which is not present in the list atr1
	public Vector<FunDep> checkFDAttrs(Vector<String> atr, Vector<FunDep> fds){
		for (int i = 0; i < fds.size(); i++) {
			FunDep fd = fds.elementAt(i);
			if (!atr.containsAll(fd.X) || !atr.containsAll(fd.Y)){
				fds.remove(fd);
				i--;
			}
		}
		
		return fds;
	}
	
	@SuppressWarnings("unchecked")
	public void funcsFilter(Relation re, Vector<FunDep> fdList, Vector<FunDep> fdRemove) {
		Vector<FunDep> fdRel = (Vector<FunDep>)re.funcs.clone();
		for (FunDep fd : fdRel) {
			Vector<String> left = fd.X;
			for (FunDep fdz : fdList) {
				Vector<Vector<String>> subs1 = getSubsets(left);
				Vector<Vector<String>> subs2 = getSubsets(fdz.X);
				if (subs1.contains(fdz.X) || subs2.contains(left)) {
					if (fdz.Y.elementAt(0).equals(fd.Y.elementAt(0))) {
						fdRemove.add(fdz);
					}
				}
			}
		}
	}
	
	// checks if all attributes have been assigned to some relation
	// if any attributes have been left out, any of the candidate keys can be made a relation
	@SuppressWarnings("unchecked")
	public void checkAllAttributes(Vector<Relation> inpt) {
		Vector<String> attr = new Vector<String>();
		for(Relation r : inpt) {
			for (String a : r.atr_List) {
				if (!attr.contains(a)) {
					attr.add(a);
				}
			}
		}
		if (rltn.atr_List.size()==attr.size()) {
			return;
		}
		Vector<String> attrExc = (Vector<String>)rltn.atr_List.clone();
		attrExc.removeAll(attr);
		String relname = Relation.RName + "_" + (++Relation.cnt);
		if (rltn.prim_key.containsAll(attrExc))
			inpt.add(new Relation(relname, rltn.prim_key));
		else {
			attrExc.addAll(rltn.prim_key);
			inpt.add(new Relation(relname,attrExc));
		}
		Relation.fillIn(inpt.lastElement());
		inpt.lastElement().NF = inpt.lastElement().findNF();
	}
	
	// Decomposes function from 1NF to 2NF
	@SuppressWarnings("unchecked")
	public Vector<Relation> decomp2NF(){
		System.out.println(rltn.cand_Keys);
		Vector<Relation> ret = new Vector<Relation>();
		Vector<FunDep> fdList = (Vector<FunDep>)rltn.funcs.clone();
		Vector<FunDep> fdRemove = new Vector<FunDep>();
		String relname;
		for (Vector<String> ck : rltn.cand_Keys) {
			for (FunDep fd : fdList) {
				if (fdRemove.contains(fd))
					continue;
				Vector<Vector<String>> subs = getSubsets(ck);
				if (subs.size()==0)
					break;
				for (Vector<String> subset : subs) {
					if (subset.size() == fd.X.size() && subset.containsAll(fd.X)) {
						Vector<String> attrs = new Vector<String>();
						attrs.addAll(fd.X);
						attrs.addAll(fd.Y);
						relname = Relation.RName + "_" + (++Relation.cnt);
						Relation r = new Relation(relname,attrs);
						ret.add(r);
						FunDep fdNew = new FunDep(fd);
						fdNew.pnt = r;
						r.funcs.add(fdNew);
						// removes from original fds those which contain proper subsets of the X of FDs already inserted 
						funcsFilter(r, fdList, fdRemove);
						fdRemove.add(fd);
						for (FunDep fdx : fdList) {
							if (r.atr_List.containsAll(fdx.X) && !r.atr_List.contains(fdx.Y.elementAt(0))) {
								fdNew = new FunDep(fdx);
								fdNew.pnt = r;
								r.atr_List.add(fdNew.Y.elementAt(0));
								r.funcs.add(fdNew);
								fdRemove.add(fdx);
							}
						}
					}
				}
			}
		}

		int i = 0;
		while(fdRemove.size() != fdList.size()) {
			if (i >= fdList.size()) {
				break;
			}
			FunDep fd = fdList.elementAt(i++);
			if (fdRemove.contains(fd))
				continue;
			relname = Relation.RName + "_" + (++Relation.cnt);
			FunDep fdNew = new FunDep(fd);
			Relation r = new Relation(relname,fdNew);
			fdNew.pnt = r;
			fdRemove.add(fd);
			ret.add(r);
			for (FunDep fdx : fdList) {
				if (fdRemove.contains(fdx))
					continue;
				if (r.atr_List.containsAll(fdx.X) && !r.atr_List.contains(fdx.Y.elementAt(0))) {
					fdNew = new FunDep(fdx);
					fdNew.pnt = r;
					r.atr_List.add(fdNew.Y.elementAt(0));
					r.funcs.add(fdNew);
					funcsFilter(r,fdList,fdRemove);
					fdRemove.add(fdx);
				}
			}
		}
		
		checkAllAttributes(ret);

		for (Relation r : ret) {
			r.alg = new AlgoPack(r);
			Relation.fillIn(r);
			r.NF = r.findNF();
		}
		return ret;
	}
	
	// returns all proper subsets of the input vector
	public Vector<Vector<String>> getSubsets(Vector<String> inpt){
		Vector<Vector<String>> fin = new Vector<Vector<String>>();
		int n = inpt.size();
		for (int i = 0; i < (1<<n); i++){
       		Vector<String> temp = new Vector<String>();
       		boolean flag = false;
            for (int j = 0; j < n; j++)
            	if ((i & (1 << j)) > 0) {
            		temp.add(inpt.elementAt(j));
            		flag = true;
            	}
            if (temp.size()>0)
            	fin.add(temp);
	    }
		for (int i = 0; i < fin.size(); i++) {
			Vector<String> str = fin.elementAt(i);
			if (str.size() == inpt.size())
				fin.remove(str);
		}
		
		return fin;
	}
	
	// fills all variables of the relation related to its keys
	public void fillKeys() {
		rltn.all_Keys = getKeys();
		rltn.prim_key = rltn.findPK();
		rltn.cand_Keys = getCKs();
		rltn.keyAttr = fillKeyAttr();
		
	}
	
	// Returns Vector of key attributes, i.e. attributes that occur in candidate keys.
	public Vector<String> fillKeyAttr() {
		Vector<String> keyAttr = new Vector<String>();
		for (Vector<String> ck : rltn.cand_Keys) {
			for(String s : ck) {
				if (!keyAttr.contains(s))
					keyAttr.add(s);
			}
		}
		
		return keyAttr;
	}
	
	// Finds and assigns NF to each FD of the relation
	public void fillNF() {
		for (FunDep fd : rltn.funcs)
			fd.NF = fd.findNF();
	}
	
	// Finds Candidate Keys
	// Candidate keys are those superkeys none of whose proper subsets are keys.
	public Vector<Vector<String>> getCKs(){
		Vector<Vector<String>> retCK = new Vector<Vector<String>>();
		for (Vector<String> supkey : rltn.all_Keys) {
			Vector<Vector<String>> subs = getSubsets(supkey);
			boolean flag = false;
			for (Vector<String> testck : subs) {
				if (rltn.all_Keys.contains(testck)) {
					flag = true;
					break;
				}
			}
			if (!flag)
				retCK.add(supkey);
		}
		removeDupli(retCK);
		return retCK;
	}
	
	// Finds super keys
	public Vector<Vector<String>> getKeys(){
		int N = rltn.atr_List.size();
		Vector<String> K = new Vector<String>(rltn.atr_List);
		Vector<Vector<String>> keyset = new Vector<Vector<String>>();
		keyset.add(K);
		keyDo(K, keyset, N);
		removeDupli(keyset);
		return keyset;
	}
	
	// Finds closure of vector of input attributes
	public Vector<String> closure(Vector<String> inpt, Vector<FunDep> fds){
		Vector<String> clsr = new Vector<String>(inpt);
		Vector<String> oldC;
		boolean flag = true;
		do {
			oldC = new Vector<String>(clsr);
			for (FunDep fd : fds){
				flag = true;
				for (String at : fd.X) {
					if (!clsr.contains(at)) {
						flag = false;
						break;
					}
				}
				if (fd.X.size()>0 && flag){
					if (!clsr.contains(fd.Y.elementAt(0)))
						clsr.add(fd.Y.elementAt(0));
				}
			}
		} while (!oldC.equals(clsr));
		return clsr;
	}
	
	// removes duplicate keys
	public Vector<Vector<String>> removeDupli(Vector<Vector<String>> inpt){
		for (int i = 0; i < inpt.size()-1; i++)
			for (int j = i+1; j < inpt.size(); j++)
				if (inpt.elementAt(i).equals(inpt.elementAt(j)))
					inpt.remove(j--);
		
		return inpt;
	}
	
	// Algo 16.2a in textbook
	public void keyDo(Vector<String> inpt, Vector<Vector<String>> pks, int N){
		if (inpt.size() <= 1)
			return;
		Vector<String> K = new Vector<String>(inpt);
		Vector<String> temp = new Vector<String>();
		String str;
		int ind = 0;
		for (int i = 0; i < inpt.size(); i++) {
			str = K.elementAt(i);
			ind = i;
			K.remove(str);
			temp = closure(K,rltn.funcs);
			if (temp.size() == N) {
				pks.add(0,new Vector<String>(K));
				keyDo(pks.elementAt(0), pks, N);
				K.add(ind,str);
			}
			else
				K.add(ind,str);
		}
	}
}
