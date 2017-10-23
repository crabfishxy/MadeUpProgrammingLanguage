import java.util.*;

public class Parse{
	public static List<String> Read() {
		int i, j,k;
		Scanner input=new Scanner(System.in);
		String line = input.nextLine();
		String[] word = line.split("\\s++");
		

		//transform :a to thing "a
		for(i = 0; i < word.length; i ++) {
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
		
		//find list, transform the list to [ word1 word2 ... wordn ]
		//we suppose the bracket is matched
		int bn = 0;
		for(i = 0; i < mua.cmd.size(); i ++) {
			if(mua.cmd.get(i).charAt(0) == '[' ) {
				bn ++;
				if(mua.cmd.get(i).length() > 1) {
					//transform [xxx to [ xxx
					mua.cmd.add(i+1, mua.cmd.get(i).substring(1,mua.cmd.get(i).length()));
					mua.cmd.set(i, "[");
				}
				for(j = i+1; j < mua.cmd.size() ; j ++) {
					if(mua.cmd.get(j).charAt(0) == '[' ) {
						bn ++;
						if(mua.cmd.get(j).length() > 1) {
							
							mua.cmd.add(j, mua.cmd.get(j).substring(1,mua.cmd.get(j).length()));
							mua.cmd.set(j + 1, "[");
						}
						continue;
					}
					if(mua.cmd.get(j).charAt(mua.cmd.get(j).length()-1) == ']'){
						if(mua.cmd.get(j).length() > 1) {
							mua.cmd.set(j, mua.cmd.get(j).substring(0,mua.cmd.get(j).length()-1) );
							mua.cmd.add(j + 1,"]");
							j --;
							continue;
						}
						bn --;
						if(bn == 0)
						break;
					}
				}
				for(k = i + 1; k <= j; k ++) {
					mua.cmd.set(i, mua.cmd.get(i) + " " + mua.cmd.get(k));
					mua.cmd.remove(k);
					k --;
					j --;
				}
			}
		}
		
		
		return mua.cmd;
	}
	

	
	public static void Interpret() {
		int fail = 0;
		while(fail == 0 && mua.cmd.size() != 0) {
			if(Find() == -1)fail = 1;
			else execute(Find());
		}
		
	}
	
	//find the first operator that can be done
	public static int Find() {
		int i;
		for(i = 0; i < mua.cmd.size(); i ++) {
			if(IsOP(mua.cmd.get(i))) {
				if(Check(i)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	//check whether the operation can be done
	public static Boolean Check(int index) {
		switch(mua.cmd.get(index)) {
		case "make":
		case "add":
		case "sub":
		case "mul":
		case "mod":
		case "eq":
		case "gt":
		case "lt":
		case "and":
		case "or":
		if(index < mua.cmd.size()-2) {
			if(!IsOP(mua.cmd.get(index+1)) && !IsOP(mua.cmd.get(index+2)))return true;
			else return false;
		}
		case "thing":
		case "erase":
		case "isname":
		case "not":
		case "print":
			if(index < mua.cmd.size()-1) {
			if(!IsOP(mua.cmd.get(index+1)))return true;
			else return false;
		}
		}
		return false;
		
	}
	
	public static Boolean IsOP(String str) {
		switch(str) {
		case "make":
		case "thing":
		case "erase":
		case "isname":
		case "print":
		case "read":
		case "readlinst":
		case "add":
		case "sub":
		case "mul":
		case "mod":
		case "eq":
		case "gt":
		case "lt":
		case "and":
		case "or":
		case "not":return true;
		default: return false;
		}
	}


	public static int execute(int index) {
		int success = 1;
		switch(mua.cmd.get(index)) {
		case "make":
			success = execute.make(index);
			break;
		case "thing":
			success = execute.thing(index);
			break;
		case "print":
			success = execute.print(index);
			break;
		case "isname":
			success = execute.isname(index);
			break;
		case "erase":
			success = execute.erase(index);
			break;
		case "add":
			success = execute.add(index);
			break;
		case "sub":
			success = execute.sub(index);
			break;
		case "mul":
			success = execute.mul(index);
			break;
		case "eq":
			success = execute.eq(index);
			break;
		case "gt":
			success = execute.gt(index);
			break;
		case "lt":
			success = execute.lt(index);
			break;
		case "and":
			success = execute.and(index);
			break;
		case "or":
			success = execute.or(index);
			break;
		}
		return success;
	}
	
	//for test
	public static void Print() {
		int i;
		for(i = 0; i < mua.cmd.size(); i ++) {
			System.out.println(mua.cmd.get(i));
		}
		return;
	}
}