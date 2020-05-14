package dbms_proj;

public class InvalidFunctionalDependencyException extends Exception{
	String attr;
	public InvalidFunctionalDependencyException(String s) {
		super();
		attr = s;
	}
	
	public String toString(){
		String errmsg = "The input " + attr + " in not valid.";
		return errmsg;
	}

}
