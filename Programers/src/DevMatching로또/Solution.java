package DevMatching로또;

import java.util.*;

public class Solution {
	
	public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int cnt = 0;
        for(int lotto : lottos) {
        	for(int num : win_nums) {
        		if(lotto == num) {
        			cnt++;
        		}
        	}
        }
        int zeros = 0;
        for(int lotto : lottos) {
    		if(lotto == 0) {
    			zeros++;
    		}
    	}
        
        answer[0] = 7-(cnt+zeros);
        answer[1] = 7-(cnt);
        if(cnt == 0) answer[1]--;
        return answer;
    }
	
	public static void main(String[] args){
		Solution sol = new Solution();
		int[] lottos = {44, 1, 0, 0, 31, 25};	
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		System.out.println(Arrays.toString(sol.solution(lottos, win_nums)));
	}

}
