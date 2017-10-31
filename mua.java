import java.util.*;
public class mua{
	public static Hashtable<String, String> v = new Hashtable();
	//public static Vector v = new Vector();//store the objects
	public static List<String>  cmd= new ArrayList<String>();//store the cmd
	public static void main(String []args) {
		
		//System.out.println(v.size());
		while(true) {
			System.out.print(">>>");
			Parse.Read();
			//Parse.Print();
			Parse.Interpret();
		}
		
	}
}