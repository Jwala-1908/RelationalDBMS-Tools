import java.util.*;
public class Attribute 
{
	Relation parent;
	String name;
	String PK;
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
		if (isPrimaryKey(String att))
			return name+"*";
		return name;
	}
	public boolean isPrimaryKey(String att)
	{	
		if(att.equals(PK))
		return true;
		return false;
	}
	public static void main(String[] args) 
	{

	}
}
