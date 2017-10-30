import java.util.*;

public class execute{
	public static int make(int index) {
		int i;
		//check if the first argument is a word
		if(mua.cmd.get(index+1).charAt(0) == '\"') {
			String name = mua.cmd.get(index+1).substring(1, mua.cmd.get(index+1).length());
			//check whether there exists such varibles
			for(Map.Entry<String, String> entry: mua.v.entrySet()) {
				String tmpname = entry.getKey();
				if(tmpname.equals(name)) {
					String tmpvalue = mua.cmd.get(index + 2);
					mua.v.put(name, tmpvalue);
					mua.cmd.remove(index);
					mua.cmd.remove(index);
					mua.cmd.remove(index);
					return 1;
				}else continue;
			}
			String tmpname = name;
			String tmpvalue = mua.cmd.get(index+2);
			mua.v.put(name, tmpvalue);
			//update the cmd
			mua.cmd.remove(index);
			mua.cmd.remove(index);
			mua.cmd.remove(index);
			return 1;
		}
		return 0;
	}

	public static int thing(int index) {
		int i;
		String name = mua.cmd.get(index+1);
		name = name.substring(1, name.length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			//DataStructure tmp = (DataStructure) mua.v.get(i);
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				String tmpvalue = entry.getValue();
				//update the cmd
				if(tmpvalue.charAt(0) == '[') {//the value is a list;
					String []word = tmpvalue.split("\\s++");
					mua.cmd.remove(index);
					//check if there is a list in this list

				}else{//the value is other
					mua.cmd.set(index, tmpvalue);
					mua.cmd.remove(index+1);
				}

				return 1;//success
			}
		}
		return 0;//fail
	}

	public static int isname(int index) {
		int i;
		String name = mua.cmd.get(index+1).substring(1, mua.cmd.get(index+1).length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				mua.cmd.set(index, "true");
				mua.cmd.remove(index + 1);
				return 1;//
			}
		}
		mua.cmd.set(index, "false");
		mua.cmd.remove(index + 1);
		return 1;//
	}



	public static int erase(int index) {
		int i;
		String name = mua.cmd.get(index+1).substring(1, mua.cmd.get(index+1).length());
		for(Map.Entry<String, String> entry: mua.v.entrySet()) {
			String tmpname = entry.getKey();
			if(tmpname.equals(name)) {
				mua.v.remove(tmpname);
				mua.cmd.remove(index);
				mua.cmd.remove(index);
				return 1;//success
			}
		}
		mua.cmd.remove(index);
		mua.cmd.remove(index);
		return 0;//failed
	}

	public static int print(int index) {
		int i;
		if(mua.cmd.get(index+1).charAt(0) == '\"') {
			//the value is a word
			System.out.println(mua.cmd.get(index+1).substring(1, mua.cmd.get(index+1).length()));
			mua.cmd.remove(index);
			mua.cmd.remove(index);
			return 1;
		}else {
			//the value is other thing
			System.out.println(mua.cmd.get(index+1));
			mua.cmd.remove(index);
			mua.cmd.remove(index);
			return 1;
		}
	}

	public static int add(int index) {
		int i;
		//transform word to number
		if(!CheckNum(mua.cmd.get(index+1)) || !CheckNum(mua.cmd.get(index+2))) {
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			Float sum;
			sum = num1 + num2;
			String s = String.valueOf(sum);
			mua.cmd.set(index, s);
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}

		return 1;
	}

	public static int sub(int index) {
		int i;
		//transform word to number
		if(!CheckNum(mua.cmd.get(index+1)) || !CheckNum(mua.cmd.get(index+2))) {
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			Float sum;
			sum = num1 - num2;
			String s = String.valueOf(sum);
			mua.cmd.set(index, s);
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}

		return 1;
	}

	public static int mul(int index) {
		int i;
		//transform word to number
		if(!CheckNum(mua.cmd.get(index+1)) || !CheckNum(mua.cmd.get(index+2))) {
			return 0;//false: the add string is not a number
		}else {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			Float sum;
			sum = num1 * num2;
			String s = String.valueOf(sum);
			mua.cmd.set(index, s);
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}

		return 1;
	}

	public static int eq(int index) {
		String str1 = mua.cmd.get(index+1);
		String str2 = mua.cmd.get(index+2);
		if(CheckNum(mua.cmd.get(index+1)) && CheckNum(mua.cmd.get(index+2))) {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			if(num1 == num2){
				mua.cmd.set(index, "true");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}else {
				mua.cmd.set(index, "false");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(mua.cmd.get(index+1))) {
			str1 = "\"" + mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+2);
		}else if(CheckNum(mua.cmd.get(index+2))) {
			str1 = mua.cmd.get(index+1);
			str2 = "\"" + mua.cmd.get(index+2);
		}else {
			str1 = mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+1);
		}

		int result = str1.compareTo(str2);
		if(result == 0) {
			mua.cmd.set(index, "true");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}else {
			mua.cmd.set(index, "false");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}
		return 1;
	}

	public static int gt(int index) {
		String str1 = mua.cmd.get(index+1);
		String str2 = mua.cmd.get(index+2);
		if(CheckNum(mua.cmd.get(index+1)) && CheckNum(mua.cmd.get(index+2))) {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			if(num1 > num2){
				mua.cmd.set(index, "true");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}else {
				mua.cmd.set(index, "false");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(mua.cmd.get(index+1))) {
			str1 = "\"" + mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+2);
		}else if(CheckNum(mua.cmd.get(index+2))) {
			str1 = mua.cmd.get(index+1);
			str2 = "\"" + mua.cmd.get(index+2);
		}else {
			str1 = mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+1);
		}
		int result = str1.compareTo(str2);
		if(result > 0) {
			mua.cmd.set(index, "true");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}else {
			mua.cmd.set(index, "false");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}
		return 1;
	}

	public static int lt(int index) {
		String str1 = mua.cmd.get(index+1);
		String str2 = mua.cmd.get(index+2);
		if(CheckNum(mua.cmd.get(index+1)) && CheckNum(mua.cmd.get(index+2))) {
			Float num1 = Float.parseFloat(mua.cmd.get(index+1));
			Float num2 = Float.parseFloat(mua.cmd.get(index+2));
			if(num1 < num2){
				mua.cmd.set(index, "true");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}else {
				mua.cmd.set(index, "false");
				mua.cmd.remove(index+1);
				mua.cmd.remove(index+1);
				return 1;
			}

		}else if(CheckNum(mua.cmd.get(index+1))) {
			str1 = "\"" + mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+2);
		}else if(CheckNum(mua.cmd.get(index+2))) {
			str1 = mua.cmd.get(index+1);
			str2 = "\"" + mua.cmd.get(index+2);
		}else {
			str1 = mua.cmd.get(index+1);
			str2 = mua.cmd.get(index+2);
		}
		int result = str1.compareTo(str2);
		if(result < 0) {
			mua.cmd.set(index, "true");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}else {
			mua.cmd.set(index, "false");
			mua.cmd.remove(index+1);
			mua.cmd.remove(index+1);
		}
		return 1;
	}

	public static int and(int index) {
		if(mua.cmd.get(index+1).equals("true") && mua.cmd.get(index+2).equals("true")) {
			mua.cmd.set(index, "true");
			mua.cmd.remove(index + 1);
			mua.cmd.remove(index + 1);
			return 1;//
		}else {
			mua.cmd.set(index, "false");
			mua.cmd.remove(index + 1);
			mua.cmd.remove(index + 1);
			return 0;
		}
	}

	public static int or(int index) {
		if(mua.cmd.get(index+1).equals("true") || mua.cmd.get(index+2).equals("true")) {
			mua.cmd.set(index, "true");
			mua.cmd.remove(index + 1);
			mua.cmd.remove(index + 1);
			return 1;//
		}else {
			mua.cmd.set(index, "false");
			mua.cmd.remove(index + 1);
			mua.cmd.remove(index + 1);
			return 0;
		}
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
}
