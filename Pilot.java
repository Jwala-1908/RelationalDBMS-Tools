
public class Pilot 
{
	public static void main(String[] args) 
	{
		String arr[] = {"A","B","C","D","E"};
		Relation rel = new Relation(arr);
		System.out.println(rel);
		
		String fdtest = "{A,B} -> {C,D,E}";
		FunDep fd = new FunDep(fdtest,rel);
		System.out.println(fd);
	}
}
