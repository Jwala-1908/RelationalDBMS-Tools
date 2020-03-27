
public class Attribute {
	Relation parent;
	String name;
	String PK;
	boolean pk;
	
	public Attribute(String str, Relation r){
		parent = r;
		name = str;
		pk = false;
	}

	public String toString() {
		if (isPrimaryKey("xyz"))
			return name+"*";
		return name;
	}
	
	public boolean isPrimaryKey(String att) {
		return pk;
	}
	
//	public boolean isPrimaryKey(String att)
//	{	
//		if(att.equals(PK))
//			return true;
//		return false;
//	}
}
