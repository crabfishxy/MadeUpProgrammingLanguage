import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;   
import java.util.Stack;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class calculate {  
  
    public static String expression(String s) {  
        // TODO Auto-generated method stub   
      
    	//delete the space and tab
    	String[] word = s.split("\\s++");
    	s = "";
    	
    	for(int i = 0; i < word.length; i ++) {
    		if(word[i] == "add" || word[i] == "sub" || word[i] == "mul" || word[i] == "div" || word[i] == "mod") {
    			
    		}
    		s += word[i];
    	}
    	for(int i = 0; i < s.length(); i ++) {
    		//if there is such :a , we have to read the number first
    		int j;
    		if(s.charAt(i) == ':') {
    			String tmp = new String();
    			for(j = i+1; j < s.length(); j ++) {
    				char c = s.charAt(j);
    				if(Character.isDigit(c) || Character.isLetter(c)) {//is letter or number
    					tmp += c;
    				}else {
    					break;
    				}
    			}
    			//tmp is a word
    			tmp = findnum(tmp);
    			s= s.substring(0, i) + tmp + s.substring(j, s.length());
    		}
    	}
       List<Object> list = trans(s);  
              
       Stack<Double>  result=new Stack<Double>();  
       String res=String.valueOf(f(list,result));  
       return res;
            
    }  
    
    private static String findnum(String word) {
    	String num;
    	num = mua.v.get(word);
    	return num;
    }
  
    private static double f(List<Object> list, Stack<Double> result) {  
        // TODO Auto-generated method stub  
        Iterator it=list.iterator();  
        while(it.hasNext()){  
            String m=it.next().toString();  
            if (m.equals("+")||m.equals("-")||m.equals("*")||m.equals("/")||m.equals("%")) {  
                double b=result.pop();  
                  
                double a=result.pop();  
                double v=g(a,b,m);  
                result.push(v);  
            }else {  
                result.push(Double.valueOf(m));  
            }  
        }  
        return(result.pop());  
    }  
  
    private static double g(double a, double b, String m) {  
        // TODO Auto-generated method stub  
  
        double v=0;  
        switch (m)  
        {  
        case "+":  
            v=a+b;  
            break;  
        case "-":  
            v=a-b;  
            break;  
        case "*":  
            v=a*b;  
            break;  
        case "/":  
            v=a/b;  
            break;  
        case "%":
        	v=a%b;
        	break;
        }  
        return v;  
      
    }  
  
    private static List<Object> trans(String s) {  
        // TODO Auto-generated method stub  
        Stack<Character> op=new Stack<Character>();  
          
          
        ArrayList<Object> list=new ArrayList<Object>();  
        Pattern P=Pattern.compile("[0-9]+(\\.[0-9]+)?");   //正则表达式来处理带小数点的数字  
        int i=0;  
          
        while(i<s.length()){  
            char c=s.charAt(i);  
            if (c>='0'&&c<='9') {  
                String s1=s.substring(i);  
                Matcher m =P.matcher(s1);  
                if (m.find()) {    //取匹配到的第一个数字  
                    s1=m.group();  
                      
                    list.add(s1);  
                }  
                i=i+s1.length();  
                continue;  
            }else if (c=='(') {  
                op.push(c);  
            }else if (c==')') {  
                char p=op.pop();  
                while(p!='('){  
                    list.add(p);  
                    p=op.pop();  
                }  
            }else if (c=='+'||c=='-') {  
                while(!op.isEmpty()&&(op.peek()=='+'||op.peek()=='-'||  
                        op.peek()=='*'||op.peek()=='/'||op.peek() == '%')){  
                      
                    list.add(op.pop());  
                }  
                op.push(c);  
            }else if (c=='*'||c=='/'||c=='%') {  
                while(!op.isEmpty()&&(op.peek()=='*'||op.peek()=='/'||op.peek()=='%')){  
                    list.add(op.pop());  
                }  
                op.push(c);  
            }  
            i++;  
        }  
          
        while(!op.isEmpty()){  
            list.add(op.pop());  
        }  
        return list;  
    }  
  
}  
