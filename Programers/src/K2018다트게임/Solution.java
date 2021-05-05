package K2018다트게임;

import java.util.*;

public class Solution {
	public int solution(String dartResult) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        int len = dartResult.length();
        for(int i = 0; i<len; i++) {
        	char c = dartResult.charAt(i);
        	if(c>='0' && c<='9') {
        		int n = c - '0';
        		if(c == '1' && i+1<len && dartResult.charAt(i+1) == '0') {
        			n = 10;
        			char op = dartResult.charAt(i+2);
        			int score = calculate(n, op);
        			list.add(score);
        			i = i+2;
        		}else {
        			char op = dartResult.charAt(i+1);
        			int score = calculate(n, op);
        			list.add(score);
        			i = i+1;
        		}
        	}else if(c == '*') {
        		int idx = list.size()-1;
        		list.set(idx, 2*list.get(idx));
        		if(idx > 0) {
        			list.set(idx-1, 2*list.get(idx-1));
        		}
        	}else if(c == '#') {
        		int idx = list.size()-1;
        		list.set(idx, -list.get(idx));
        	}
        }
        for(int score : list) {
        	answer += score;
        }
        return answer;
    }
	private int calculate(int n, char op) {
		switch(op) {
		case 'S': break;
		case 'D': n = n*n;break;
		case 'T': n = n*n*n;break;
		}
		return n;
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String dartResult = "1T2D3D#";
		System.out.println(sol.solution(dartResult));
	}
}
