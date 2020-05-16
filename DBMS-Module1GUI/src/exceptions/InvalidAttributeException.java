package exceptions;

public class InvalidAttributeException extends Exception {
	String attr;
	public InvalidAttributeException(String s) {
		super();
		attr = s;
	}
	
        @Override
	public String toString(){
		String errmsg = "The attribute " + attr + " does not exist in the Relation.";
		return errmsg;
	}
}