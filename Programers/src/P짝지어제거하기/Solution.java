package P짝지어제거하기;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	public int solution(String s){
        int answer = 0;
        
        Stack<Character> stack = new Stack<>();
        
        for(char c : s.toCharArray()) {
        	if(stack.isEmpty()) stack.push(c);
        	else {
        		if(stack.peek() == c) {
        			stack.pop();
        		}else {
        			stack.push(c);
        		}
        	}
        }
        if(stack.isEmpty()) answer = 1;
        return answer;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		System.out.println(s.solution("cdcd"));
	}
}
