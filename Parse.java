import java.util.*;

public class Parse{
	public static List<String> Read() {
		int i, j,k;
		int length = 0;
		Scanner input=new Scanner(System.in);
		String line = input.nextLine();
		while(checkEnd(line) == false) {
			line += " ";
			line += input.nextLine();
		}
		String[] word = line.split("\\s++");
		//deal with "//"
		for(i = 0; i < word.length; i ++) {
			if(word[i].substring(0, 1) == "\\") {
				length = i;
			}
			length = i;
		}

		//transform :a to thing "a
		for(i = 0; i <= length; i ++) {
			//translate :<word> to thing <word>
			if(word[i].charAt(0) == ':') {
				mua.cmd.add("thing");
				mua.cmd.add("\"" + word[i].substring(1, word[i].length()));
				continue;
			}else if(word[i].charAt(0) == '[') {
				for(j = 0; j < word[i].length();j++) {
					if(word[i].charAt(j) == ':') {
						mua.cmd.add(word[i].substring(0, j));
						mua.cmd.add("thing");
						mua.cmd.add("\"" + word[i].substring(j+1, word[i].length()));
						continue;
					}
				}
			}
			mua.cmd.add(word[i]);
		}
		dealList(mua.cmd);
		//find lilst, transform the list to [ word1 word2 ... wordn ]
		//we suppose the bracket is matched
//		int bn = 0;
//		for(i = 0; i < mua.cmd.size(); i ++) {
//			if(mua.cmd.get(i).charAt(0) == '[' ) {
//				bn ++;
//				if(mua.cmd.get(i).length() > 1) {
//					//transform [xxx to [ xxx
//					mua.cmd.add(i+1, mua.cmd.get(i).substring(1,mua.cmd.get(i).length()));
//					mua.cmd.set(i, "[");
//				}
//				for(j = i+1; j < mua.cmd.size() ; j ++) {
//					if(mua.cmd.get(j).charAt(0) == '[' ) {
//						bn ++;
//						if(mua.cmd.get(j).length() > 1) {
//							
//							mua.cmd.add(j, mua.cmd.get(j).substring(1,mua.cmd.get(j).length()));
//							mua.cmd.set(j + 1, "[");
//						}
//						continue;
//					}
//					if(mua.cmd.get(j).charAt(mua.cmd.get(j).length()-1) == ']'){
//						if(mua.cmd.get(j).length() > 1) {
//							mua.cmd.set(j, mua.cmd.get(j).substring(0,mua.cmd.get(j).length()-1) );
//							mua.cmd.add(j + 1,"]");
//							j --;
//							continue;
//						}
//						bn --;
//						if(bn == 0)
//						break;
//					}
//				}
//				for(k = i + 1; k <= j; k ++) {
//					mua.cmd.set(i, mua.cmd.get(i) + " " + mua.cmd.get(k));
//					mua.cmd.remove(k);
//					k --;
//					j --;
//				}
//			}
//		}
//		
		
		return mua.cmd;
	}
	

	
	public static void Interpret(List<String>  cmd) {
		int fail = 0;
		while(fail == 0 && cmd.size() != 0) {
			if(Find(cmd) == -1)fail = 1;
			else execute(cmd, Find(cmd));
		}
		if(fail == 1) {
			clear();
		}
	}
	
	//find the first operator that can be done
	public static int Find(List<String> cmd) {
		int i;
		for(i = 0; i < cmd.size(); i ++) {
			if(IsOP(cmd.get(i))) {
				if(Check(cmd,i)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	//check whether the operation can be done
	public static Boolean Check(List<String> cmd,int index) {
		
		if(mua.reserve.containsKey(cmd.get(index))) {
			for(int i = 0; i < mua.reserve.get(cmd.get(index)); i ++) {
				//make sure that the following words are not reserved words
				if(cmd.size() <= (index+1+i)) {
					Parse.clear();
					return false;
				}
				if(mua.reserve.containsKey(cmd.get(index+i+1))) {
					return false;
				}
			}
			return true;
		}
//		switch(cmd.get(index)) {
//		case "make":
//		case "add":
//		case "sub":
//		case "mul":
//		case "div":
//		case "mod":
//		case "eq":
//		case "gt":
//		case "lt":
//		case "and":
//		case "or":
//		case "repeat":
//		if(index < cmd.size()-2) {
//			if(!IsOP(cmd.get(index+1)) && !IsOP(cmd.get(index+2)))return true;
//			else return false;
//		}
//		break;
//		case "thing":
//		case "erase":
//		case "isname":
//		case "not":
//		case "print":
//			if(index < cmd.size()-1) {
//			if(!IsOP(cmd.get(index+1)))return true;
//			else return false;
//		   }
//			break;
//		case "read":
//		case "readlinst":
//		return true;
//		}
		return false;
		
	}
	
	public static Boolean IsOP(String str) {
		if(mua.reserve.containsKey(str))return true;
		else return false;
	}


	public static int execute(List<String> cmd, int index) {
		int success = 1;
		switch(cmd.get(index)) {
		case "make":
			success = execute.make(cmd,index);
			break;
		case "thing":
			success = execute.thing(cmd,index);
			break;
		case "print":
			success = execute.print(cmd,index);
			break;
		case "isname":
			success = execute.isname(cmd,index);
			break;
		case "erase":
			success = execute.erase(cmd,index);
			break;
		case "add":
			success = execute.add(cmd,index);
			break;
		case "sub":
			success = execute.sub(cmd,index);
			break;
		case "mul":
			success = execute.mul(cmd,index);
			break;
		case "div":
			success = execute.div(cmd, index);
			break;
		case "mod":
			success = execute.mod(cmd,index);
			break;
		case "eq":
			success = execute.eq(cmd, index);
			break;
		case "gt":
			success = execute.gt(cmd,index);
			break;
		case "lt":
			success = execute.lt(cmd,index);
			break;
		case "and":
			success = execute.and(cmd, index);
			break;
		case "or":
			success = execute.or(cmd, index);
			break;
		case "not":
			success = execute.not(cmd,index);
			break;
		case "read":
			success = execute.read(cmd, index);
		    break;
		case "readlinst":
			success = execute.readlinst(cmd, index);
			break;
		case "repeat":
			success = execute.repeat(cmd, index);
			break;
		case "output":
			success = function.output(cmd, index);
			break;
		case "stop":
			success = function.stop(cmd, index);
			break;
		default:
			success = function.exeFunc(cmd, index);
		}
		return success;
	}
	
	public static void clear() {
		mua.cmd.clear();
	}
	
	//for test
	public static void Print() {
		int i;
		for(i = 0; i < mua.cmd.size(); i ++) {
			System.out.println(mua.cmd.get(i));
		}
		return;
	}
	
	public static void dealList(List<String>  cmd) {
		String line = new String();
		for(int i = 0; i < cmd.size(); i ++) {
			line += cmd.get(i);
			line += " ";
		}
		StringBuilder tmp = new StringBuilder(line);
		List<Integer> leftb = new ArrayList<Integer>();//find the left bracket index
		List<Integer> rightb = new ArrayList<Integer>();
	    int a = tmp.indexOf("[");//*第一个出现的索引位置
	    if(a == -1)return;
	    while (a != -1) {
	         tmp.insert(a, " ");
	         tmp.insert(a+2, " ");
	         a = tmp.indexOf("[", a + 3);//*从这个索引往后开始第一个出现的位置
	    }
		a = tmp.indexOf("]");
		while(a != -1) {
	         tmp.insert(a, " ");
	         tmp.insert(a+2, " ");
	         a = tmp.indexOf("]", a + 3);
		}
		line = tmp.toString();
		String[] word = line.split("\\s++");
		cmd.clear();
		for(int i = 0; i < word.length; i ++) {
			cmd.add(word[i]);
		}
		a = cmd.indexOf("[");
		int b = cmd.lastIndexOf("]");
		String tmp2 = new String();
		tmp2 = cmd.get(a);
		for(int i = a+1; i <= b; i ++) {
			tmp2 += " ";
			tmp2 += cmd.get(i);
	}
		cmd.set(a, tmp2);
		for(int i = a+1; i <= b; i ++) {
			cmd.remove(a+1);
		}
		return;
	}
	public static Boolean checkEnd(String line) {
		int countLeft = 0;
		int countRight = 0;
		int a = line.indexOf('[');
		if(a == -1)return true;
		while(a != -1) {
			countLeft ++;
			a = line.indexOf('[', a + 1);
		}
		int b = line.indexOf(']');
		while(b != -1) {
			countRight ++;
			b = line.indexOf(']', b+1);
		}
		if(countLeft == countRight)return true;
		return false;
	}
}