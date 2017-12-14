import java.util.*;

public class execute{
	public static int make(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//check if the first argument is a word
		
		if(cmd.get(index+1).charAt(0) == '\"') {
			String name = cmd.get(index+1).substring(1, cmd.get(index+1).length());
			//check whether there exists such varibles
			for(Map.Entry<String, String> entry: mua.v.entrySet()) {
				String tmpname = entry.getKey();
				if(tmpname.equals(name)) {
					String tmpvalue = cmd.get(index + 2);
					mua.v.put(name, tmpvalue);
					cmd.remove(index);
					cmd.remove(index);
					cmd.remove(index);
					function.checkFunc(name);
					return 1;
				}else continue;
			}
			String tmpname = name;
			String tmpvalue = cmd.get(index+2);
			mua.v.put(name, tmpvalue);
			//update the cmd
			cmd.remove(index);
			cmd.remove(index);
			cmd.remove(index);
			function.checkFunc(name);
			return 1;
		}else {
			Parse.clear();
			return 0;
		}
	}

	public static int thing(List<String> cmd, int index) {
		int i,j,k;
		String name = cmd.get(index+1);
		name = name.substring(1, name.length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			//DataStructure tmp = (DataStructure) mua.v.get(i);
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				String tmpvalue = entry.getValue();
				//update the cmd
				if(tmpvalue.charAt(0) == '[') {//the value is a list;
					//check if there is a list in this list
					
					String []word = tmpvalue.split("\\s++");
					cmd.remove(index);//remove thing
					List<String>  list= new ArrayList<String>();
					for(i = 1; i < word.length-1 ; i ++) {
						list.add(word[i]);
					}
					
					int bn = 0;
					for(i = 0; i <list.size(); i ++) {
						if(list.get(i).charAt(0) == '[' ) {
							bn ++;
							if(list.get(i).length() > 1) {
								//transform [xxx to [ xxx
								list.add(i+1, list.get(i).substring(1,list.get(i).length()));
								list.set(i, "[");
							}
							for(j = i+1; j < list.size() ; j ++) {
								if(list.get(j).charAt(0) == '[' ) {
									bn ++;
									if(list.get(j).length() > 1) {
										
										list.add(j, list.get(j).substring(1,list.get(j).length()));
										list.set(j + 1, "[");
									}
									continue;
								}
								if(list.get(j).charAt(list.get(j).length()-1) == ']'){
									if(list.get(j).length() > 1) {
										list.set(j, list.get(j).substring(0,list.get(j).length()-1) );
										list.add(j + 1,"]");
										j --;
										continue;
									}
									bn --;
									if(bn == 0)
									break;
								}
							}
							for(k = i + 1; k <= j; k ++) {
								list.set(i, list.get(i) + " " + list.get(k));
								list.remove(k);
								k --;
								j --;
							}
						}
					}
					cmd.remove(index);
					for(i = 0; i < list.size(); i ++) {
						String tmp = list.get(i);
						cmd.add(index+i,tmp);
					}

				}else{//the value is other
					cmd.set(index, tmpvalue);
					cmd.remove(index+1);
				}

				return 1;//success
			}
		}
		Parse.clear();
		return 0;//fail
	}

	public static int isname(List<String> cmd, int index) {
		int i;
		String name = cmd.get(index+1).substring(1, cmd.get(index+1).length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				cmd.set(index, "true");
				cmd.remove(index + 1);
				return 1;//
			}
		}
		cmd.set(index, "false");
		cmd.remove(index + 1);
		return 1;//
	}



	public static int erase(List<String> cmd, int index) {
		int i;
		String name = cmd.get(index+1).substring(1, cmd.get(index+1).length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				mua.v.remove(tmpname);
				cmd.remove(index);
				cmd.remove(index);
				return 1;//success
			}
		}
		cmd.remove(index);
		cmd.remove(index);
		return 0;//failed
	}

	public static int print(List<String> cmd, int index) {
		int i;
		if(cmd.get(index+1).charAt(0) == '(') {
			//an expression
			System.out.println(calculate.expression(cmd.get(index+1)));
			cmd.remove(index);
			cmd.remove(index);
			return 1;
		}
		if(cmd.get(index+1).charAt(0) == '\"') {
			//the value is a word
			System.out.println(cmd.get(index+1).substring(1, cmd.get(index+1).length()));
			cmd.remove(index);
			cmd.remove(index);
			return 1;
		}else {
			//the value is other thing
			System.out.println(cmd.get(index+1));
			cmd.remove(index);
			cmd.remove(index);
			return 1;
		}
	}

	public static int add(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//transform word to number
		if(!CheckNum(cmd.get(index+1)) || !CheckNum(cmd.get(index+2))) {
			Parse.clear();
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			Float sum;
			sum = num1 + num2;
			String s = String.valueOf(sum);
			cmd.set(index, s);
			cmd.remove(index+1);
			cmd.remove(index+1);
		}

		return 1;
	}

	public static int sub(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//transform word to number
		if(!CheckNum(cmd.get(index+1)) || !CheckNum(cmd.get(index+2))) {
			Parse.clear();
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			Float sum;
			sum = num1 - num2;
			String s = String.valueOf(sum);
			cmd.set(index, s);
			cmd.remove(index+1);
			cmd.remove(index+1);
		}

		return 1;
	}

	public static int mul(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//transform word to number
		if(!CheckNum(cmd.get(index+1)) || !CheckNum(cmd.get(index+2))) {
			Parse.clear();
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			Float sum;
			sum = num1 * num2;
			String s = String.valueOf(sum);
			cmd.set(index, s);
			cmd.remove(index+1);
			cmd.remove(index+1);
		}

		return 1;
	}
	
	public static int div(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//transform word to number
		if(!CheckNum(cmd.get(index+1)) || !CheckNum(cmd.get(index+2))) {
			Parse.clear();
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			Float sum;
			sum = num1 / num2;
			String s = String.valueOf(sum);
			cmd.set(index, s);
			cmd.remove(index+1);
			cmd.remove(index+1);
		}

		return 1;
	}
	
	public static int mod(List<String> cmd, int index) {
		dealExpression(cmd, index);
		int i;
		//transform word to number
		if(!CheckNum(cmd.get(index+1)) || !CheckNum(cmd.get(index+2))) {
			Parse.clear();
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			Float sum;
			sum = num1 % num2;
			String s = String.valueOf(sum);
			cmd.set(index, s);
			cmd.remove(index+1);
			cmd.remove(index+1);
		}

		return 1;
	}

	public static int eq(List<String> cmd, int index) {
		dealExpression(cmd, index);
		String str1 = cmd.get(index+1);
		String str2 = cmd.get(index+2);
		if(CheckNum(cmd.get(index+1)) && CheckNum(cmd.get(index+2))) {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			if(num1.equals(num2)){
				cmd.set(index, "true");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}else {
				cmd.set(index, "false");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(cmd.get(index+1))) {
			str1 = "\"" + cmd.get(index+1);
			str2 = cmd.get(index+2);
		}else if(CheckNum(cmd.get(index+2))) {
			str1 = cmd.get(index+1);
			str2 = "\"" + cmd.get(index+2);
		}else {
			str1 = cmd.get(index+1);
			str2 = cmd.get(index+2);
		}

		int result = str1.compareTo(str2);
		if(result == 0) {
			cmd.set(index, "true");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}else {
			cmd.set(index, "false");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}
		return 1;
	}

	public static int gt(List<String> cmd, int index) {
		dealExpression(cmd, index);
		String str1 = cmd.get(index+1);
		String str2 = cmd.get(index+2);
		if(CheckNum(cmd.get(index+1)) && CheckNum(cmd.get(index+2))) {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			if(num1 > num2){
				cmd.set(index, "true");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}else {
				cmd.set(index, "false");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(cmd.get(index+1))) {
			str1 = "\"" + cmd.get(index+1);
			str2 = cmd.get(index+2);
		}else if(CheckNum(cmd.get(index+2))) {
			str1 = cmd.get(index+1);
			str2 = "\"" + cmd.get(index+2);
		}else {
			str1 = cmd.get(index+1);
			str2 = cmd.get(index+1);
		}
		int result = str1.compareTo(str2);
		if(result > 0) {
			cmd.set(index, "true");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}else {
			cmd.set(index, "false");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}
		return 1;
	}

	public static int lt(List<String> cmd, int index) {
		dealExpression(cmd, index);
		String str1 = cmd.get(index+1);
		String str2 = cmd.get(index+2);
		if(CheckNum(cmd.get(index+1)) && CheckNum(cmd.get(index+2))) {
			Float num1 = Float.parseFloat(cmd.get(index+1));
			Float num2 = Float.parseFloat(cmd.get(index+2));
			if(num1 < num2){
				cmd.set(index, "true");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}else {
				cmd.set(index, "false");
				cmd.remove(index+1);
				cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(cmd.get(index+1))) {
			str1 = "\"" + cmd.get(index+1);
			str2 = cmd.get(index+2);
		}else if(CheckNum(cmd.get(index+2))) {
			str1 = cmd.get(index+1);
			str2 = "\"" + cmd.get(index+2);
		}else {
			str1 = cmd.get(index+1);
			str2 = cmd.get(index+2);
		}
		int result = str1.compareTo(str2);
		if(result < 0) {
			cmd.set(index, "true");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}else {
			cmd.set(index, "false");
			cmd.remove(index+1);
			cmd.remove(index+1);
		}
		return 1;
	}

	public static int and(List<String> cmd, int index) {
		if(cmd.get(index+1).equals("true") && cmd.get(index+2).equals("true")) {
			cmd.set(index, "true");
			cmd.remove(index + 1);
			cmd.remove(index + 1);
			return 1;//
		}else {
			cmd.set(index, "false");
			cmd.remove(index + 1);
			cmd.remove(index + 1);
			return 0;
		}
	}

	public static int or(List<String> cmd, int index) {
		if(cmd.get(index+1).equals("true") || cmd.get(index+2).equals("true")) {
			cmd.set(index, "true");
			cmd.remove(index + 1);
			cmd.remove(index + 1);
			return 1;//
		}else {
			cmd.set(index, "false");
			cmd.remove(index + 1);
			cmd.remove(index + 1);
			return 0;
		}
	}
	
	public static int not(List<String> cmd, int index) {
		if(cmd.get(index+1).equals("true") ) {
			cmd.set(index, "false");
			cmd.remove(index + 1);
			return 1;
		}else if(cmd.get(index+1).equals("false")) {
			cmd.set(index, "true");
			cmd.remove(index + 1);
			return 1;
		}else {
			Parse.clear();
			return 0;
		}
	}
	
	public static int read(List<String> cmd, int index) {
		String tmp;
		Scanner input=new Scanner(System.in);
		tmp = input.next();
		cmd.remove(index);
		cmd.add(index, tmp);
		
		return 1;
	}
	
	public static int readlinst(List<String> cmd, int index) {
		List<String>  tmpcmd= new ArrayList<String>();//store the cmd
		for(int i = 0; i < cmd.size(); i ++) {
			String tmp = cmd.get(i);
			tmpcmd.add(tmp);
		}
		tmpcmd.remove(index);
		cmd.clear();
		Parse.Read();
		String List;
		List = "[";
		for(int i = 0; i < cmd.size();  i++) {
			List = List + " ";
			List = List + cmd.get(i);
		}
		List = List + " ]";
//		for(int i = 0; i < cmd.size(); i ++) {
//			tmpcmd.add(index + i, cmd.get(i));
//		}
		cmd.clear();
		for(int i = 0; i < tmpcmd.size(); i ++) {
			cmd.add( tmpcmd.get(i));
		}
		cmd.add(index,List);
		return 1;
	}
	
	public static int repeat(List<String> cmd, int index) {
		String repeat = cmd.get(index + 2);
		String[] word = repeat.split("\\s++");
		Integer count = Integer.valueOf(cmd.get(index + 1));
		List<String> repeatCmd = new ArrayList<String>();
		for(int i = 1; i < word.length-1; i ++) {
			repeatCmd.add(word[i]);
		}
		cmd.remove(index);
		cmd.remove(index);
		cmd.remove(index);
		for(int i = 0; i < count; i ++) {
			for(int j = 0; j < repeatCmd.size(); j ++) {
				cmd.add(index+j+i*repeatCmd.size(), repeatCmd.get(j));
			}
		}
		return 1;
	}

	public static Boolean CheckNum(String str) {
		String tmp;
		int i;
		int point = 0;
		if(str.charAt(0) == '\"') tmp = str.substring(1, str.length());
		else tmp = str;
		//negative number
		if(tmp.charAt(0) == '-') {
			for(i = 1; i < tmp.length(); i ++) {
				if (!Character.isDigit(tmp.charAt(i)) && tmp.charAt(i) != '.') {
					return false;
				}else if(tmp.charAt(i) == '.') {
					if(point == 1)return false;
					else point = 1;
				}else if(Character.isDigit(tmp.charAt(i))){
					continue;
				}
			}
		}else {
			for(i = 0; i < tmp.length(); i ++) {
				if (!Character.isDigit(tmp.charAt(i)) && tmp.charAt(i) != '.') {
					return false;
				}else if(tmp.charAt(i) == '.') {
					if(point == 1)return false;
					else point = 1;
				}else if(Character.isDigit(tmp.charAt(i))){
					continue;
				}
			}
		}
		return true;
	}
	public static void dealExpression(List<String> cmd, int index) {
		String op1 = "";
		String op2 = "";
		int countLeft = 0;
		int countRight = 0;
		if(cmd.get(index+1).charAt(0) == '(') {// there is an expression
			
			for(int i = 0; ;) {
				op1 += cmd.get(index+1);
				countLeft += countCh(cmd.get(index+1), '(');
				countRight += countCh(cmd.get(index+1), ')');
				cmd.remove(index+1);
				if(countLeft == countRight) {
					break;
				}
			}
			cmd.add(index+1, op1);
			cmd.set(index+1, calculate.expression(cmd.get(index+1)));

		}
		countLeft = 0;
		countRight = 0;
		if(index + 2 >= cmd.size())return;
		if(cmd.get(index+2).charAt(0) == '(') {// there is an expression
			
			for(int i = 0; ;) {
				op2 += cmd.get(index+2);
				countLeft += countCh(cmd.get(index+1), '(');
				countRight += countCh(cmd.get(index+1), ')');
				cmd.remove(index+2);
				if(countLeft == countRight) {
					break;
				}
			}
			cmd.add(index+2, op2);
			cmd.set(index+2, calculate.expression(cmd.get(index+2)));
		}
		
		
		return;
	}
	public static int countCh(String str, char c) {
		int a = str.indexOf(c);
		int count = 0;
		if(a == -1)return 0;
		while(a != -1) {
			count ++;
			a = str.indexOf(c,a+1);
		}
		return count;
	}
}
