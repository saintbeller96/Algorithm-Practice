package P9935문자열폭발;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(br.readLine());		
		String bombStr = br.readLine();
		
		StringBuilder stack = new StringBuilder();
		int len = bombStr.length();
		for(int i = 0; i<sb.length(); i++) {
			char c = sb.charAt(i);
			stack.append(c);
			if(stack.length() >= len && c == bombStr.charAt(len-1)) {
				int s = stack.length() - len;
				int idx = 0;
				boolean flag = true;
				for(int k = s; k< stack.length(); k++) {
					if(stack.charAt(k) != bombStr.charAt(idx++)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					stack.delete(s, stack.length());
				}
			}
		}
		
		if(stack.length() == 0) {
			System.out.println("FRULA");
		}else {
			System.out.println(stack);
		}
	}
}
