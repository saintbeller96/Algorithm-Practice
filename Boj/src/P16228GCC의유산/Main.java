package P16228GCC의유산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static HashMap<Character, Integer> priority;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(br.readLine().replaceAll(">\\?", "#").replaceAll("<\\?", "@"));
		//@최소 #최대
		priority = new HashMap<>();
		priority.put('+', 0);
		priority.put('-', 0);
		priority.put('@', 1);
		priority.put('#', 1);
		
		while(true) {
			int e = sb.indexOf(")");
			if(e == -1) break;
			int s = sb.lastIndexOf("(", e);
			
			String result = calculation(sb.substring(s+1,e));
			sb.replace(s, e+1, result);
		}
		System.out.println(calculation(sb.toString()));
	}
	static String calculation(String str) {
		Stack<Character> stack = new Stack<>();
		ArrayList<String> result = new ArrayList<>();
		StringBuilder num = new StringBuilder();
		
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c >='0' && c<='9') num.append(c);
			else {
				//num에는 숫자가 저장된 상태
				result.add(num.toString());
				num = new StringBuilder();
				if(stack.isEmpty()) stack.push(c);
				else {
					char top = stack.peek();
					if(priority.get(top) >= priority.get(c)) {
						while(priority.get(top) >= priority.get(c)) {
							result.add(stack.pop()+"");
							if(stack.isEmpty()) break;
							top = stack.peek();
						}
						stack.push(c);
					}else {
						stack.push(c);
					}
				}
			}
		}
		result.add(num.toString());
		while(!stack.isEmpty()) {
			result.add(stack.pop()+"");
		}
		
		Stack<String> stack2 = new Stack<>();
		for(String s : result) {
			if(s.equals("+") || s.equals("-") || s.equals("#") || s.equals("@")) {
				String b = stack2.pop();
				String a = stack2.pop();
				String r = operation(a, b, s) + "";
				stack2.push(r);
			}else {
				stack2.push(s);
			}
		}
		return stack2.pop();
	}
	
	static int operation(String a, String b, String op) {
		int n = Integer.parseInt(a);
		int m = Integer.parseInt(b);
		switch(op) {
		case "+": return n + m;
		case "-": return n - m;
		case "#": return Math.max(n, m);
		case "@": return Math.min(n, m);
		}
		return 0;
	}
}
