public class Attribute 
{
	Relation parent;
	String name;
	boolean pk;
	public Attribute(String str, Relation r)
	{
		parent = r;
		name = str;
		pk = false;
		// TODO Auto-generated constructor stub
	}
	public String toString() 
	{
		if (isPrimaryKey())
			return name+"*";
		return name;
	}
	public boolean isPrimaryKey()
	{	
		return pk;
	}
	public static void main(String[] args) 
	{

	}
}
