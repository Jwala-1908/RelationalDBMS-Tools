package exceptions;

public class RepeatedAttributeException extends Exception {
	String attr;
	
	public RepeatedAttributeException(String s) {
		super();
		attr = s;
	}
	
	public String toString() {
		String errmsg = "The attribute " + attr + " has been entered more than once.\nPlease re-enter the correct set of attributes:";
		return errmsg;
	}
}