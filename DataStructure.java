public class DataStructure {
	//变量的字面量
	String name;
	//if type == 1, the varible bounded to a number, if type == 2 bounded to a word, if type == 3 bounded to a list 
	int type;
	//绑定的内容
	String value;
	
	//if it bounded to a int ,return the int
	public int ToInt() {
		if(type == 1) {
			int x;
			x = Integer.parseInt(value, 10);
			return x;
		}else {
			return 1;
		}
	}
	
	public String ToString() {
		return value;
	}
	
	
}