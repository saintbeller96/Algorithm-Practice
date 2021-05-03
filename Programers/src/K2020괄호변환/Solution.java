package K2020괄호변환;

import java.util.*;

public class Solution {
	public String solution(String p) {
        
        if(isRight(p)) return p;
        
        String answer = func(p);
        return answer;
    }
	
	private String func(String w) {
		if(w.equals("")) return w;
		String[] s = div(w);
		String u = s[0];
		String v = s[1];
		if(isRight(u)) {
			return u + func(v);
		}
		StringBuilder sb = new StringBuilder("(");
		sb.append(func(v));
		sb.append(")");
		String temp = u.substring(1, u.length()-1);
		for(char c : temp.toCharArray()) {
			if(c=='(') sb.append(')');
			else sb.append('(');
		}
		
		return sb.toString();
	}
	private String[] div(String w) {
		int cnt = 0;
		int idx = 0;
		for(char c : w.toCharArray()) {
			if(c == '(') cnt++;
			else cnt--;
			if(cnt == 0) break;
			idx++;
		}
		String[] ret = new String[2];
		ret[0] = w.substring(0, idx+1);
		ret[1] = w.substring(idx+1, w.length());	
		return ret;
	}
	private boolean isRight(String s) {
		int cnt = 0;
		for(char c : s.toCharArray()) {
			if(c == '(') cnt++;
			else cnt--;
			if(cnt < 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String p = "()))((()";
		System.out.println((s.solution(p)));
	}
}
