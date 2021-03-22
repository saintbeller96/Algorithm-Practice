package P1234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static String s;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		for (int t = 1; t <= 10; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			s = stk.nextToken();
			
			Stack<Integer> stack = new Stack<>();
			stack.push(s.charAt(0) - '0');
			for (int i = 1; i < s.length(); i++) {
				if(!stack.isEmpty() && stack.peek() == s.charAt(i) - '0') {
					stack.pop();
				}else {
					stack.push(s.charAt(i) - '0');
				}
			}
			long answer = 0L;
			long v = 1L;
			while(!stack.isEmpty()) {
				answer += (long)stack.pop()*v;
				v *= 10L;
			}			
			System.out.println("#" + t +" " +answer);
		}
	}

}
