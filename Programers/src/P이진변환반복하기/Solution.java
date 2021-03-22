package P이진변환반복하기;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	public int[] solution(String s) {
        int[] answer = {0, 0};
        while(!s.equals("1")) {
        	int cnt = 0;
        	for(char c: s.toCharArray()) {
        		if(c == '1') cnt++;        		
        	}
        	answer[1] += s.length()-cnt;
        	answer[0]++;
        	s = Integer.toBinaryString(cnt);      	
        }
        return answer;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		System.out.println(s.solution("110010101001"));
	}
}
