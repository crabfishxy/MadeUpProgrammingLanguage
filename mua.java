import java.util.*;
public class mua{
	public static Hashtable<String, String> v = new Hashtable();
	public static Hashtable<String, Integer> reserve = new Hashtable();
	//public static Vector v = new Vector();//store the objects
	public static List<String>  cmd= new ArrayList<String>();//store the cmd
	public static void main(String []args) {
		
		//System.out.println(v.size());
		init();
		while(true) {
			System.out.print(">>>");
			Parse.Read();
			//Parse.Print();
			Parse.Interpret(cmd);
		}
		
	}
	
	public static void init() {
		 reserve.put("make", 2);
	     reserve.put("add", 2);
	     reserve.put("thing",1);
	     reserve.put("erase",1);
	     reserve.put("isname",1);
	     reserve.put("print",1);
	     reserve.put("read",0);
	     reserve.put("readlinst",0);
	     reserve.put("add",2);
	     reserve.put("sub",2);
	     reserve.put("mul",2);
	     reserve.put("div",2);
	     reserve.put("mod",2);
	     reserve.put("eq",2);
	     reserve.put("gt",2);
	     reserve.put("lt",2);
	     reserve.put("and",2);
	     reserve.put("or",2);
	     reserve.put("repeat",2);
	     reserve.put("not",2);
	     reserve.put("output", 1);
	     reserve.put("stop", 0);
	}
}