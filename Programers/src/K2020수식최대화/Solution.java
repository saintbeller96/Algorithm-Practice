package K2020수식최대화;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution {
	public char[] operation = {'+', '-', '*'};
	public long answer;
	public ArrayList<Long> nums;
	public ArrayList<Character> ops;
	public long solution(String expression) {
        answer = 0;
        int[] input = {0, 1, 2};
        int[] perm = new int[3];
        nums = new ArrayList<Long>();
        ops = new ArrayList<Character>();
        
        StringBuilder num = new StringBuilder();
        for(int i = 0; i<expression.length(); i++) {
        	char c = expression.charAt(i);
        	if(c >= '0' && c<='9') {
        		num.append(c);
        	}else {
        		nums.add(Long.parseLong(num.toString()));
        		num = new StringBuilder();
        		ops.add(c);
        	}
        }
        nums.add(Long.parseLong(num.toString()));
        permutation(expression, perm, input, 0);
        return answer;
    }
	
	public void permutation(String expression, int[] perm, int[] input, int depth) {
		if(depth == 3) {
			ArrayList<Long> newNums = new ArrayList<Long>();
			for(long l : nums) newNums.add(l);
			ArrayList<Character> newOps = new ArrayList<Character>();
			for(char c : ops) newOps.add(c);
			
			for(int p = 0; p <3; p++) {
				char op = operation[perm[p]];
				Iterator<Character> iter = newOps.iterator();
				int i = 0;
				while(iter.hasNext()) {
					char c = iter.next();
					if(c == op) {
						long n1 = newNums.get(i);
						long n2 = newNums.get(i+1);
						long result = calculate(n1, n2, op);
						newNums.set(i, result);
						newNums.remove(i+1);
						iter.remove();
						i--;
					}
					i++;
				}
			}
			answer = Math.max(Math.abs(newNums.get(0)), answer);
			return;
		}
		for(int i = depth; i<3; i++) {
			perm[depth] = input[i];
			swap(input, i, depth);
			permutation(expression, perm, input, depth+1);
			swap(input, i, depth);
		}
	}
	private static void swap(int[] perm, int i, int j) {
		int t = perm[i];
		perm[i] = perm[j];
		perm[j] = t;
	}
	private long calculate(long n1, long n2, char op) {
		switch(op) {
		case '+':
			return n1+n2;
		case '-':
			return n1-n2;
		case '*':
			return n1*n2;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("50*6-3*2"));

	}

}
