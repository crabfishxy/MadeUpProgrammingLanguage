
import java.util.*;
import java.util.logging.Logger;

public class function{
	
	public static List<String> outputList = new ArrayList<String>();
	public static void checkFunc(String word) {//after make operation, to check if this word is a function
		//Logger logger = Logger.getLogger(function.class.getName());
		String value = mua.v.get(word);
		if(value.length() < 11) return;
		if(value.charAt(0) != '[')return;
		if(value.charAt(1) != ' ' || value.charAt(2) != '[') return;
		List<String> list = parseList(value);
		//if is a function, we should add it to reserved word list, regard it as a operation
		if(list.size() == 2) {//has two list, it is a list
			//logger.warning("It is a function");
			int argcount = 0;
			String arglist = list.get(0);
			String []arg = arglist.split("\\s++");
			argcount = arg.length-2;
			//we have to store the number of arg this function need
			mua.reserve.put(word, Integer.valueOf(argcount));
		}

		return;
		
	}
	public static List<String> parseList(String value) {
		List<String> list = new ArrayList<String>();
		int a = value.indexOf('[', 1);
		int b = 0;
		if(a == -1)return list;
		int count = 0;
		//find the first list
		for(int i = a; i < value.length();  i++) {
			if(value.charAt(i) == '[')count ++;
			else if(value.charAt(i) == ']')count --;
			if (count == 0) {
				b = i;
				break;
			}
		}
		String list1 = value.substring(a, b+1);
		list.add(list1);
		if(b >= value.length())return list;
		
		//find the second list
		a = value.indexOf('[', b);
		//no other list
		if(a == -1)return list;
		//there is other list but not contiguous
		if(a != b+2 || value.charAt(b+1) != ' ')return list;
		//get the second list
		count = 0;
		for(int i = a; i < value.length(); i ++) {
			if(value.charAt(i) == '[')count ++;
			else if(value.charAt(i) == ']')count --;
			if (count == 0) {
				b = i;
				break;
			}
		}
		String list2 = value.substring(a, b+1);
		list.add(list2);
		//check if the second list is the end list
		if(value.length() == b + 3 && value.charAt(b+2) == ']' && value.charAt(b+1) == ' ') {
			return list;
		}
		list.add(" ");
		//if there is other list or any other thing, it is not a legal function
		return list;
	}
	
	public static int exeFunc(List<String >cmd, int index) {
		
		//get the arguments number
		int argcount = mua.reserve.get(cmd.get(index));
		//we have to create a new hash table to save the variables
		Hashtable<String, String> funcV = new Hashtable<String, String>();
		String value = mua.v.get(cmd.get(index));
		List<String> list = parseList(value);
		String arg = list.get(0);
		String[] tmp = arg.split("\\s++");
		String[] arglist = new String[tmp.length-2];
		for(int i = 0; i < tmp.length-2;  i++) {
			arglist[i] = tmp[i+1];
		}
		
		
		for(int i = 0; i < argcount; i ++) {
			//bind the number to the arguments
			funcV.put(arglist[i], cmd.get(index+1+i));
		}
		//save the hashtable , use the functions's hashtable
		Hashtable<String, String> tmp_v = mua.v;
		mua.v = funcV;
		
		String func = list.get(1);
		String[] tmp2 = func.split("\\s++");
		//get the function's cmd
		List<String> funcCmd = new ArrayList<String>();
		for(int i = 0; i < tmp2.length-2; i ++) {
			funcCmd.add(tmp2[i+1]);
		}
		
		for(int i = 0; i < funcCmd.size(); i ++) {
			String word = funcCmd.get(i);
			if(word.charAt(0) == ':' ) {
				funcCmd.add(i, "thing");
				funcCmd.set(i+1, "\"" + funcCmd.get(i+1).substring(1,funcCmd.get(i+1).length() ));
			}
		}
		Parse.Interpret(funcCmd);
		cmd.remove(index);
		for(int i = 0; i < argcount; i ++) {
			cmd.remove(index);
		}
		for(int i = 0; i < outputList.size(); i ++) {
			cmd.add(index+i, outputList.get(i));
		}
		mua.v = tmp_v;
		outputList.clear();
		return 1;
	}
	
	public static int output(List<String >cmd, int index) {
		execute.dealExpression(cmd, index);
		outputList.add(cmd.get(index+1));
		cmd.remove(index);
		cmd.remove(index);
		return 1;
	}
	
	public static int stop(List<String>cmd, int index) {
		cmd.clear();
		return 1;
	}
}

