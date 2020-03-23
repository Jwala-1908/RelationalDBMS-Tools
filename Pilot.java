import java.util.*;

public class Pilot {
	public static void main(String[] args) {
		Vector<String> vec = new Vector<String>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the relation:");
		String name = sc.nextLine();
		System.out.println("Enter the attributes (space separated):");
		
		// space separated attributes taken as 1 string input
		String attr = sc.nextLine();
		
		// separating single string into each attribute
		StringTokenizer st = new StringTokenizer(attr," ");
		while(st.hasMoreTokens())
		{
			vec.add(st.nextToken().toUpperCase());
		}
		Relation rel = new Relation(name, vec);
		System.out.println(rel);
		
		System.out.println("The atomic functional dependencies are:");
		for (FunDep fd : rel.funcs)
			System.out.println(fd);
		sc.close();
	}
}

