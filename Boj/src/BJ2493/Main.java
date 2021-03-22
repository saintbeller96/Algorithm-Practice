package BJ2493;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int N = Integer.parseInt(br.readLine());
		int[] towers = new int[N];
		int[] answer = new int[N];
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			towers[i] = Integer.parseInt(stk.nextToken());
		}
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		answer[0] = 0;
		for (int i = 1; i < N; i++) {
			int idx = stack.peek();
			while(towers[idx] < towers[i]) {
				stack.pop();
				if(stack.isEmpty()) break;
				idx = stack.peek();
			}
			if(stack.isEmpty()) {
				answer[i] = 0;
				stack.push(i);
			}else {
				answer[i] = idx+1;
				stack.push(i);
			}			
		}
		for (int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}
}
