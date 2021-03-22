package P연산자우선순위;

import java.util.ArrayList;

public class Solution {
	
	private long answer;
	
	public long solution(String expression) {
		answer = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<expression.length(); i++) {
			char c = expression.charAt(i);
			if(c== '*' || c == '+' || c == '-') {
				sb.append(c);
			}
		}
		expression = expression.replace('*', '_');
		expression = expression.replace('+', '_');
		expression = expression.replace('-', '_');
		
		
		ArrayList<String> strs = new ArrayList<>();
		String[] temp = expression.split("_");
		
		for(int i = 0; i< 2*sb.length()+1; i++) {
			if(i%2==0) {
				strs.add(temp[i/2]);
			}else {
				strs.add(sb.substring(i/2, i/2+1));
			}
		}
		permutation(0, new String[] {"*", "+", "-"}, new String[3], strs);
		
		return answer;
	}
	
	private void permutation(int depth, String[] input, String[] perm, ArrayList<String> ex) {
		if(depth == 3) {
			ArrayList<String> copy = new ArrayList<String>();
			for(String s: ex) {
				copy.add(s);
			}
			int n = copy.size();
			for(String op : perm) {
				for(int i = 0; i<n; i++) {
					if(copy.get(i).equals(op)) {
						long result = calc(copy.get(i-1), op, copy.get(i+1));
						copy.remove(i-1);
						copy.remove(i-1);
						copy.remove(i-1);
						copy.add(i-1, Long.toString(result));
						i--;
						n-=2;
					}
				}
			}
			answer = Math.max(answer, Math.abs(Long.parseLong(copy.get(0))));
			
			return;
		}
		for(int i = depth; i<3; i++) {
			perm[depth] = input[i];
			swap(depth, i, input);
			permutation(depth+1, input, perm, ex);
			swap(depth, i, input);
		}
		
	}
	private void swap(int a, int b, String[] arr) {
		String s = arr[a];
		arr[a] = arr[b];
		arr[b] = s;
	}
	private long calc(String a, String op, String b) {
		switch(op){
		case "*":
			return Long.parseLong(a)*Long.parseLong(b);
		case "+":
			return Long.parseLong(a)+Long.parseLong(b);
		case "-":
			return Long.parseLong(a)-Long.parseLong(b);
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		
		System.out.println(s.solution("100-200*300-500+20"));
		
		
	}
}
